package com.zsw.util;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RTKUtilTest {

	
	@Test
	public void test001() {
		double[][] cors = new double[30][3];
		cors[2] = new double[]{-2321224.4884,5391151.6591,2487312.8576};//corsId=2
		cors[3] = new double[]{-2328572.1739,5390905.9509,2481005.1279};
		cors[4] = new double[]{-2331324.6312,5372555.1933,2517787.5601};
		cors[6] = new double[]{-2350919.4874,5377416.3786,2489202.7881};
		cors[7] = new double[]{-2348190.6776,5370542.0457,2506389.6781};
		cors[8] = new double[]{-2322167.2313,5387935.0027,2493398.3518};
		cors[9] = new double[]{-2356882.0480,5344436.3017,2553822.1546};
		cors[10] = new double[]{-2369964.0147,5335767.9993,2559730.3863};
		cors[11] = new double[]{-2324296.9496,5365308.9624,2539478.4433};
		cors[12] = new double[]{-2345349.2708,5354895.5122,2542120.4390};
		cors[13] = new double[]{-2323579.5888,5399960.8364,2466035.3886};
		cors[19] = new double[]{-2311545.7133,5387105.3525,2504914.8611};
		cors[20] = new double[]{-2312125.5408,5396360.2529,2484500.2614};
		cors[22] = new double[]{-2370590.4204,5366731.0285,2493482.0521};
		cors[24] = new double[]{-2334221.3951,5383571.1512,2491557.2698};
		for(int i=0;i<30;i++)
		{
			if(cors[i][0]!=0)
			{
				System.out.println("计算站"+i+"   ..........................................");
				count(cors[i]);
			}
		}
		

		
	}
	
	@Test
	public void test002() {
		count(-2332221.3951, 5381571.1512, 2485557.2698);
		count(new double[]{-2332221.3951, 5381571.1512, 2485557.2698});
	}
	
	
	private void count(double ...input)
	{
		double[] gg=RTKUtil.ecef2pos(input);
		double a = gg[0] * 180 / Math.PI ;
		System.out.println("经度："+a);
		double b = gg[1] * 180 / Math.PI ;
		System.out.println("纬度："+b);
		System.out.println("高度："+gg[2]);
	}
	
	@Test
	public void test003() {
		count(-2324461.361864062,
				5388857.17606967,
				2489213.0281267487);
//		count(new double[]{-2332221.3951, 5381571.1512, 2485557.2698});
	}
	{
		
	}
	
	//经纬度转xyz
	@Test
	public void test004() {
		double[] gg=RTKUtil.pos2ecef(22.84078719*Math.PI/180.0,113.67060423*Math.PI/180.0,0.0);
		System.out.println(gg[0]+","+gg[1]+","+gg[2]);
	}
	
	
	@Test
	public void test005() {
		double[] gg=RTKUtil.ecef2pos(-2325652.298200, 5388376.534300,2489190.591420233);
		System.out.println(gg[0]*180.0/Math.PI+","+gg[1]*180.0/Math.PI+","+gg[2]);
	}
	
}






