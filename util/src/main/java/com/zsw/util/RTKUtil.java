package com.zsw.util;

/**
 * 具体的原理搞不清楚，先别管是怎么计算的，反正是对的就是啦。
 * @author zsw
 *
 */
public class RTKUtil {
	public static final double FE_WGS84 = 1.0 / 298.257223563;// 地球扁平率
	public static final double RE_WGS84 = 6378137.0;// 地球长半轴
	public static final double OMGE = 7.2921151467E-5;// 地球角速度 
	public static final double CLIGHT = 299792458.0;// 光速
	public static final double[][] SNR_MASK = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //每个频段从5到85，间隔10度
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	/**
	 * 将大地坐标转换成经纬度坐标
	 * @param ecef_pos（x,y,z）
	 * @return 经纬度、高度（lan,lon,height）单位：弧度
	 */
	public static double [] ecef2pos(double ... ecef_pos){
		double e2 = FE_WGS84 * (2.0 - FE_WGS84);
		double r2 = ecef_pos[0] * ecef_pos[0] + ecef_pos[1] * ecef_pos[1];
		double v = RE_WGS84, sinp;
		double z, zk;
		//纬度需要迭代计算
		for (z = ecef_pos[2], zk = 0.0; Math.abs(z - zk) > 1e-4;) {
			zk = z;
			sinp = z / Math.sqrt(r2 + z * z);
			v = RE_WGS84 / Math.sqrt(1.0 - e2 * sinp * sinp);
			z = ecef_pos[2] + v * e2 * sinp;
		}
		double[] pos = new double[3];
		pos[0] = r2 > 1E-12 ? Math.atan(z / Math.sqrt(r2))
				: (ecef_pos[2] > 0.0 ? Math.PI / 2.0 : -Math.PI / 2.0);
		pos[1] = r2 > 1E-12 ? Math.atan2(ecef_pos[1], ecef_pos[0]) : 0.0;//经度
		pos[2] = Math.sqrt(r2 + z * z) - v;
		return pos;
	}
	
	
	/**
	 * 大地位置坐标转换成ECEF的XYZ坐标
	 * @author zenghu
	 * @date 2017年8月17日
	 * @param geodetic_pos（纬度、经度、高度）单位为：弧度
	 * @return
	 */
	public static double[] pos2ecef(double... geodetic_pos) {
		double[] r = new double[3];
		double sinp = Math.sin(geodetic_pos[0]);//纬度
		double cosp = Math.cos(geodetic_pos[0]);
		double sinl = Math.sin(geodetic_pos[1]);//经度
		double cosl = Math.cos(geodetic_pos[1]);
		double e2 = FE_WGS84 * (2.0 - FE_WGS84);
		double v = RE_WGS84 / Math.sqrt(1.0 - e2 * sinp * sinp);
		System.out.println("sinp:"+sinp+"\tsinl:"+sinl);
		r[0] = (v + geodetic_pos[2]) * cosp * cosl;
		r[1] = (v + geodetic_pos[2]) * cosp * sinl;
		r[2] = (v * (1.0 - e2) + geodetic_pos[2]) * sinp;
		return r;
	}

	/**
	 * 计算站星距离
	 * @author zenghu
	 * @date 2017年8月17日
	 * @param satPos 卫星的位置（x,y,z）
	 * @param recvPos 站的位置（x,y,z）
	 * @param e 站星方向的单位向量
	 * @return
	 */
	public static double geodist(double []satPos,double []recvPos,double []e){
		//求卫星到地心测距离
		double r = Math.sqrt(satPos[0] * satPos[0] +satPos[1] * satPos[1] + satPos[2] * satPos[2]);
		//卫星到地心的距离小于地球的长半轴，则错误
		if(r < RE_WGS84){
			return  -1.0D;
		}
		//求卫星到观测站的距离
		for (int i = 0; i < 3; i++) {
			e[i] = satPos[i] - recvPos[i];
		}	
		r = Math.sqrt(e[0]*e[0] + e[1]*e[1] + e[2]*e[2]);
		for (int i = 0; i < 3; i++) {
			e[i] /= r;
		}
		//更正了地球自转影响
		return r + OMGE * (satPos[0]*recvPos[1] - satPos[1]*recvPos[0]) / CLIGHT;
	}
	
	/**
	 * 将度转换成度、分、秒
	 * @author zenghu
	 * @date 2017年8月18日
	 * @param deg
	 * @return
	 */
	public double[] deg2dms(double deg){	
		double []dms = new double[3];
		double sign = deg < 0 ? -1.0 : 1.0;//判断符号
		double a = Math.abs(deg); //取绝对值
		dms[0] = Math.floor(a); //向下取整，得到度
		a = (a - dms[0]) * 60;
		dms[1] = Math.floor(a);//得到分	
		a = (a - dms[1]) * 60;
		dms[2] = a; //得到秒		
		dms[0] *=sign;		
		return dms;
	}
	
	
	/**
	 * 将度、分、秒转换成度
	 * @author zenghu
	 * @date 2017年8月18日
	 * @param dms
	 * @return
	 */
	public double dms2de(double []dms){
		double sign = dms[0] < 0 ? -1.0 : 1.0;		
		return sign * (Math.abs(dms[0]) + dms[1] / 60.0 + dms[2] / 3600.0);		
	}

	/**
	 * 计算卫星的高度角和方位角
	 * @author zenghu
	 * @date 2017年8月18日
	 * @param sat_pos 卫星的地心坐标（x,y,z）
	 * @param recv_pos cors站的坐标（lat,lon,h），单位：度
	 * @return
	 */
	public static double[] satazel(double []sat_pos, double []recv_pos){
		//将经纬度转换成弧度
		recv_pos[0] = recv_pos[0] * Math.PI / 180; //纬度
		recv_pos[1] = recv_pos[1] * Math.PI / 180; //经度
		double sinP = Math.sin(recv_pos[0]); //纬度的正弦值
		double cosP = Math.cos(recv_pos[0]); //纬度的余弦值
		double sinL = Math.sin(recv_pos[1]); //经度的正弦值
		double cosL = Math.cos(recv_pos[1]); //经度的余弦值	
		double [][] H={ {        -sinL,         cosL,    0 },
				        { -sinP * cosL, -sinP * sinL, cosP },
		                {  cosP * cosL,  cosP * sinL, sinP }};

		//将cors站的经纬度转换成地心坐标
		double[] recv_ecef = pos2ecef(recv_pos);
		double []azel = new double[2];//azel[0]为方位角,azel[1]为高度角
		azel[0] = 0.0; //初始化
		azel[1] = Math.PI / 2.0; //初始化
		//求单位向量e
		double []e = new double[3];
		geodist(sat_pos, recv_ecef, e);
		if(recv_ecef[2] > -RE_WGS84){
			//H和e相乘
			double []enu = new double[3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					enu[i] += H[i][j] * e[j];
				}
			}
			azel[0] = enu[0]*enu[0] + enu[1]*enu[1] + enu[2]*enu[2] < 1e-12?0.0:Math.atan2(enu[0], enu[1]);
			if(azel[0] < 0.0){
				azel[0] += 2*Math.PI;
			}
			azel[1] = Math.asin(enu[2]);		
		}
		return azel;
	}
	
	/**
	 * 判断信噪比是否满足最小阈值
	 * @author zenghu
	 * @date 2017年8月18日
	 * @param freq 频数
	 * @param el 高度角,弧度
	 * @param snr
	 * @return 0：满足要求，1：不满足要求
	 */
	public static int testSNR(int freq, double el, double snr){
		if(freq < 0 || freq > 3){
			return 0;
		}
		snr *= 0.25;
		//将弧度转换成度
		double a = (el * 180 / Math.PI + 5.0) / 10.0;
		//向下取整
		int i =(int) Math.floor(a);
		//得到小数部分
		a -= i;
		double minsnr;
		if(i < 1){
			minsnr = SNR_MASK[freq][0];
		}else if(i > 8){
			minsnr = SNR_MASK[freq][8];
		}else{
			minsnr = (1.0 - a) * SNR_MASK[freq][i - 1] + a * SNR_MASK[freq][i];
		}
		int flag = snr < minsnr ? 1 : 0;
		return flag;
	}

}
