package com.zsw.util.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileUtilTest {

	@Test
	public void test001() {
		String file = "C:\\Users\\zsw\\Desktop\\xyz.txt";
		List<String[]> gg = FileUtil.readFileToList(file);
		PropertiesUtil pu = new PropertiesUtil("C:\\Users\\zsw\\Desktop\\xyz2.txt");
		Map<String, double[]> map = new HashMap<>();

		for (String[] ss : gg) {
			String kk = pu.get(ss[1]);
			if (kk != null) {
				double[] d = new double[3];
				d[0] = Double.valueOf(ss[2]);
				d[1] = Double.valueOf(ss[3]);
				d[2] = Double.valueOf(ss[4]);
				map.put(kk, d);
			}
		}
		{//打印
			for (Entry<String, double[]> entry : map.entrySet()) {
				double[] ff = entry.getValue();
				System.out.println("" + entry.getKey() + "|" + ff[0] + "|" + ff[1] + "|" + ff[2]);
			}
		}
		pu.release();

	}

}
