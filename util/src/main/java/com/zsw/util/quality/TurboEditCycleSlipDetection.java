package com.zsw.util.quality;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class TurboEditCycleSlipDetection {
	// 不同卫星的频段值不一样啊！。
	public static final double C = 299792458;

	public static final double L1 = 1574.42;
	public static final double L2 = 1227.6;
	public static final double L5 = 1176.45;


	public static final double B1 = 1561.098;
	public static final double B2 = 1207.52;
	public static final double B3 = 1268.52;


	public static final double GLO1 = 1602.5625;
	public static final double GLO2 = 1246.4375;


	public static final int NO_CYCLE_SLIP = 0;
	public static final int CYCLE_SLIP = 1;

	public static final int GPS = 1;
	public static final int GLO = 2;
	public static final int BD = 3;
	public int type;

	public TurboEditCycleSlipDetection(int type) {
		this.type = type;
	}


	private final static int HISTO_LENGTH = 30;
	protected double[] Nw = new double[HISTO_LENGTH];//保存NW值就行了，递推的NW值每次都要重算。

	protected int i;//当前Nw和recurrentNw的下标。
	protected int currentLength;
	private LinkedList<Double> queue =new LinkedList<Double>();
	
	protected boolean flag = false;

	/**
	 * . TODO 很多计算可以在type指定的时候先算好
	 * 
	 * @param pseudoRange1
	 * @param pseudoRange2
	 * @param phase1
	 * @param phase2
	 * @return
	 */
	public int CycleSlipDetection(double pseudoRange1, double pseudoRange2, double phase1, double phase2) {
		double f1 = 0, f2 = 0;
		switch (type) {
		case GPS:
			f1 = L1;
			f2 = L2;
			break;
		case BD:
			f1 = B1;
			f2 = B2;
			break;
		case GLO:
			f1 = GLO1;
			f2 = GLO2;
			break;
		default:
			throw new RuntimeException("SingleCycleSlipDetection error");
		}
		double nd1 = C / f1 / 1000000, nd2 = C / f2 / 1000000;
		double NwNow = countNW(phase1, phase2, f1, f2, pseudoRange1, pseudoRange2, nd1, nd2);		
		return CycleSlipDetectionResult(NwNow);
	}

	/**
	 * 在算周跳结果之前，Nw还没有入队列的，i指向要入队列的位置。
	 * @return
	 */
	private int CycleSlipDetectionResult(double NwNow) {
		if(queue.size() > HISTO_LENGTH){
			queue.removeFirst();
		}
		
		if(queue.size()<3){
			queue.addLast(NwNow);
			return NO_CYCLE_SLIP;
		}
		
		Iterator<Double> iterator = queue.iterator();
		int i = 1;
		double variance=0.0D;//方差初始为0
		double recurrentNwLast = queue.getFirst();
		while(iterator.hasNext()){
			double tempNw=iterator.next();
			//在这个循环里面把方差也算了。
			variance=variance+1.0/i*(Math.pow((tempNw-recurrentNwLast),2)-variance);
			recurrentNwLast=recurrentNwLast+1.0/i*(tempNw-recurrentNwLast);
			if(i == queue.size() - 1){
				break;
			}
			i++;
		}
		double tempNw=iterator.next();
		variance=variance+1.0/queue.size()*(Math.pow((tempNw-recurrentNwLast),2)-variance);	
		double data=NwNow-recurrentNwLast;
		double fourVariance=4*Math.sqrt(variance);
		if(!flag)
		{
			if(data<fourVariance && data> -fourVariance)
			{
				// 把值存入队列中。
				queue.addLast(NwNow);
				return NO_CYCLE_SLIP;
			}else{
				flag=true;//下次再测是否有周跳。
				queue.addLast(NwNow);
				return NO_CYCLE_SLIP;//这次暂且认为没有周跳。
			}			
		}
		else{
			double interpolation=NwNow-queue.getLast();			
			if(interpolation>-1 &&interpolation<1 &&(interpolation>=fourVariance || interpolation<=-fourVariance))
			{
				clear();//重置所有。
				return CYCLE_SLIP;//周跳；
			}else{
				// 野值，剔除旧的,把值存入队列中。即覆盖以前的那个值。
				queue.removeLast();
				queue.addLast(NwNow);
				flag = false;
				return CYCLE_SLIP;
			}
		}
	}

	/**
	 * @param w1
	 * @param w2
	 * @param f1
	 * @param f2
	 * @param P1
	 * @param P2
	 * @param nd1
	 * @param nd2
	 * @return
	 */
	private static double countNW(double w1, double w2, double f1, double f2, double P1, double P2, double nd1,
			double nd2) {
		double Nw = (w1 / nd1 - w2 / nd2) - (f1 - f2) / (f1 + f2) * (P1 / nd1 + P2 / nd2);
		return Nw;
	}

	public void clear()
	{
		Nw = new double[HISTO_LENGTH];
		i=0;
		currentLength=0;
		flag = false;
		queue.clear();
	}
	
	@Override
	public String toString() {
		return "Nw data:"+Arrays.toString(this.Nw)+"\ti="+i+"\tcurrentLength:"+currentLength;
	}
}