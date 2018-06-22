package com.zsw.util.file;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PropertiesUtilTest {

	@Test
	public void test001() {
		PropertiesUtil pu = new PropertiesUtil("C:\\Users\\zsw\\Desktop\\xyz2333.txt");
		pu.writeProperties("gg", "what the fuck|dog|0.000");
		String hahaha = pu.get("gg");
		System.out.println("hahaha:" + hahaha);

	}

}
