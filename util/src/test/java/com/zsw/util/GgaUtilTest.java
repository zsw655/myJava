package com.zsw.util;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GgaUtilTest {

	@Test
	public void test() {
		String s = null;
		GgaUtil.getCheck("$GPGGA,033135,2307.61716,N,11320.14075,E,5,13,1.4,21.50,M,0.00,M,0,*77");
		GgaUtil.getCheck("$GPGGA,033158,2307.61925,N,11320.13301,E,5,13,3.3,-6.45,M,0.00,M,0,*6C");

		s = GgaUtil.createGga("$GPGGA,033158,2307.61925,N,11320.13301,E,5,13,3.3,-6.45,M,0.00,M,0,*");
		System.out.println("create:" + s);

		s = GgaUtil.createGga("$GPGGA,033158,2357.41220,N,11313.27494,E,5,13,3.3,-6.45,M,0.00,M,0,*");
		System.out.println("create:" + s);

		s = GgaUtil.createGga("$GPGGA,033158,2305.8608,N,11314.42190,E,5,13,3.3,-6.45,M,0.00,M,0,*");
		System.out.println("create:" + s);
		
		System.out.println("now:");
		s = GgaUtil.createGga("$GPGGA,033158,2307.32529,N,11320.73940,E,5,13,3.3,-6.45,M,0.00,M,0,*");
		System.out.println("create:" + s);
	}
	
	@Test
	public void test002() {
		String s = null;
		s = GgaUtil.createGga("$GPGGA,033158,2307.63749,N,11320.06813,E,5,13,3.3,-6.45,M,0.00,M,0,*");
		System.out.println("create:" + s);
	}
	
	@Test
	public void test003() {
		String s = null;
		s = GgaUtil.createGga("$GPGGA,033158,2400.63749,N,11320.06813,E,5,13,3.3,-6.45,M,0.00,M,0,*");
		System.out.println("create:" + s);
	}
	
	
	
	
	@Test
	public void test004() {
		String s = null;
		s = GgaUtil.createGga("$GPGGA,123422.00,2307.35299033,N,11319.96518956,E,5,04,4.0,16.559,M,-6.703,M,5.0,0000*");
		System.out.println("create:" + s);
	}
	
	@Test
	public void test005() {
		String s = null;
		s = GgaUtil.createGga("$GPGGA,022452.00,2307.33219658,N,11320.71725607,E,2,05,1.4,0.0,M,-6.655,M,4.0,0000*");
		System.out.println("create:" + s);
	}
	
	@Test
	public void test006() {
		String s = null;
		s = GgaUtil.createGga("$GPGGA,022452.00,2307.33219658,N,11320.71725607,E,2,05,1.4,2333.0,M,-6.655,M,4.0,0000*");
		System.out.println("create:" + s);
	}
	
	@Test
	public void test007() {
		String s = null;
		s = GgaUtil.createGga("$GPGGA,022452.00,2307.33219658,N,11320.71725607,E,2,05,1.4,0.0,M,0.0,M,4.0,0000*");
		System.out.println("create:" + s);
	}
	
	@Test
	public void test008() {
		String s = null;
		s = GgaUtil.createGga("GNGGA,115107.00,2307.35013019,N,11319.96464490,E,1,23,0.6,66.400,M,-6.703,M,,*");
		System.out.println("create:" + s);
	}
	@Test
	public void test009() {
		String s = null;
		s = GgaUtil.createGga("$GNGGA,061407.00,2307.33303126,N,11320.72461419,E,2,13,1.0,0.0,M,-6.655,M,4.0,0000*");
		System.out.println("create:" + s);
	}
	@Test
	public void test010() {
		String s = null;
		s = GgaUtil.createGga("$GNGGA,061452.00,2307.33300028,N,11320.72462630,E,2,11,1.1,0.0,M,-6.655,M,4.0,0000*");
		System.out.println("create:" + s);
	}
	@Test
	public void test011() {
		String s = null;
		s = GgaUtil.createGga("$GNGGA,090832.00,2303.96294268,N,11323.88225237,E,5,14,1.0,0.0,M,-6.416,M,9.0,0000*");
		System.out.println("create:" + s);
	}
//	佛山
	@Test
	public void test012() {
		String s = null;
		s = GgaUtil.createGga("$GNGGA,023922.00,2300.44444466,N,11307.07159319,E,2,14,1.0,0.0,M,-6.645,M,4.0,0000*");
		System.out.println("create:" + s);
	}
//	狮岭
	@Test
	public void test013() {
		String s = null;
		s = GgaUtil.createGga("$GNGGA,023922.00,2328.55112,N,11308.5008,E,2,14,1.0,0.0,M,-6.645,M,4.0,0000*");
		System.out.println("create:" + s);
	}
	
	@Test
	public void test014() {
		String s = null;
		s = GgaUtil.createGga("$GNGGA,090832.00,2303.96294268,N,11323.88225237,E,5,14,1.0,0.0,M,-6.416,M,9.0,0000*");
		System.out.println("create:" + s);
	}
	
	@Test
	public void test015() {
		String s = null;
		s = GgaUtil.createGga("$GNGGA,082257.00,2303.75000012,N,11322.40435516,E,2,12,1.0,0.0,M,-6.647,M,3.0,0000*");
		System.out.println("create:" + s);
	}
	
	
	
	//科韵路附近
	@Test
	public void test016() {
		String s = null;
		s = GgaUtil.createGga("$GNGGA,014302.00,2307.32258588,N,11322.14437197,E,1,19,0.7,0.0,M,-6.660,M,,*");
		System.out.println("create:" + s);
	}

	
	@Test
	public void test017() {
		String s = null;
		s = GgaUtil.createGga("$GNGGA,023922.00,2300.44444466,N,11307.07159319,E,2,14,1.0,26.58397,M,-6.645,M,4.0,0000*");
		System.out.println("create:" + s);
	}
	
	
	@Test
	public void test018() {
		String s = null;
		s = GgaUtil.createGga("$GNGGA,090832.00,2303.96294268,N,11323.88225237,E,5,14,1.0,0.0,M,0.0,M,9.0,0000*");
		System.out.println("create:" + s);
	}
}
