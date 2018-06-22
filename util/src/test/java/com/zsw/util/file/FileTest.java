package com.zsw.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.zsw.util.Byte2Hex;

public class FileTest {

	@Test
	public void test001() {

		WriteFile wf = new WriteFile("C:\\Users\\zsw\\Desktop\\mt6737\\1\\gg1262304266738allout.txt");

		File file = new File("C:\\Users\\zsw\\Desktop\\mt6737\\1\\gg1262304266738all.txt");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if (tempString.startsWith("location.getLatitude")) {
					String[] ss = tempString.split(":");
					// System.out.println(ss[1]);
					String kk = reader.readLine();
					String[] ss2 = kk.split(":");
					wf.writeFile(ss2[1].trim() + "," + ss[1].trim());
				}
				// wf.writeFile("");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	@Test
	public void test002gga2pos() {

		WriteFile wf = new WriteFile("C:\\Users\\zsw\\Desktop\\mx906-1-dtx-dt.pos");

		File file = new File("C:\\Users\\zsw\\Desktop\\mx906-1-dtx-dt.txt");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if (tempString.startsWith("$GNGGA") || tempString.startsWith("$GPGGA")) {
					String[] ss = tempString.split(",");
					System.out.println(ss[1]);
					String time_h = ss[1].substring(0, 2);
					String time_m = ss[1].substring(2, 4);
					String time_s = ss[1].substring(4, ss[1].length());

					StringBuilder sb = new StringBuilder();
					sb.append(String.format("%-23s", "2017/12/05 " + time_h + ":" + time_m + ":" + time_s));
					{
						String s[] = ss[2].split("\\.");
						String degreeString = s[0].substring(0, s[0].length() - 2);
						String minuteString = s[0].substring(s[0].length() - 2, s[0].length()) + "." + s[1];
						double degree = Double.parseDouble(degreeString.trim());
						double minute = Double.parseDouble(minuteString.trim());
						double degreeFull = degree + minute / 60.0;
						System.out.println("la:" + degreeFull);
						sb.append(String.format("%15.9f", degreeFull));
					}
					{
						String s[] = ss[4].split("\\.");
						String degreeString = s[0].substring(0, s[0].length() - 2);
						String minuteString = s[0].substring(s[0].length() - 2, s[0].length()) + "." + s[1];
						double degree = Double.parseDouble(degreeString.trim());
						double minute = Double.parseDouble(minuteString.trim());
						double degreeFull = degree + minute / 60.0;
						System.out.println("lo:" + degreeFull);
						sb.append(String.format("%15.9f", degreeFull));
					}
					sb.append(String.format("%11.4f", 1.000));
					if (ss[6].equals("5"))
						ss[6] = "4";
					sb.append(String.format("%4s", ss[6]));
					sb.append(String.format("%4d", 8));
					sb.append(String.format("%9.4f", 0.6559));
					sb.append(String.format("%9.4f", 0.6253));
					sb.append(String.format("%9.4f", 1.5384));
					sb.append(String.format("%9.4f", 0.1071));
					sb.append(String.format("%9.4f", 0.4236));
					sb.append(String.format("%9.4f", -0.3497));
					sb.append(String.format("%7.2f", 0.00));
					sb.append(String.format("%7.1f", 0.0));

					wf.writeFile(sb.toString());
				}

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	@Test
	public void test003phone2pos() {

		WriteFile wf = new WriteFile("C:\\Users\\zsw\\Desktop\\gg1262304266738all.pos");

		File file = new File("C:\\Users\\zsw\\Desktop\\gg1262304100882all.txt");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {

				if (tempString.startsWith("location.getLatitude")) {
					String[] ss = tempString.split(":");
					String kk = reader.readLine();
					String[] ss2 = kk.split(":");
					StringBuilder sb = new StringBuilder();
					sb.append(String.format("%-23s", "2017/12/05 03:31:31.000"));
					double latitude = Double.valueOf(ss2[1].trim());
					double longitude = Double.valueOf(ss[1].trim());

					sb.append(String.format("%15f", longitude));
					sb.append(String.format("%15f", latitude));

					sb.append(String.format("%11.4f", 1.000));
					sb.append(String.format("%4d", 4));
					sb.append(String.format("%4d", 8));
					sb.append(String.format("%9.4f", 0.6559));
					sb.append(String.format("%9.4f", 0.6253));
					sb.append(String.format("%9.4f", 1.5384));
					sb.append(String.format("%9.4f", 0.1071));
					sb.append(String.format("%9.4f", 0.4236));
					sb.append(String.format("%9.4f", -0.3497));
					sb.append(String.format("%7.2f", 0.00));
					sb.append(String.format("%7.1f", 0.0));

					wf.writeFile(sb.toString());
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

	/**
	 * p10手机数据转HGCODE格式
	 */
	@Test
	public void test004pTen2HGCODE() {

		WriteFile wf = new WriteFile("C:\\Users\\zsw\\Desktop\\gnss_log_2018_06_22_14_47_24_hgcode.txt");

		File file = new File("E:\\qq数据\\1607114262\\FileRecv\\gnss_log_2018_06_22_14_47_24.txt");

		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;

			boolean restart = true;// 进行下一个HGCODE转换的标识

			// 一次读入一行，直到读入null为文件结束

			List<String> list = null;
			long TimeNanos = 0;
			long FullBiasNanos= 0;
			long BiasNanos= 0;
			

			StringBuilder sb = null;
			while ((tempString = reader.readLine()) != null) {

				if (restart) {
					// 开始HGCODE转换
					sb = new StringBuilder();
					list = new LinkedList<>();
					restart = false;
				}

				if (tempString.startsWith("Raw")) {

					// 加入某容器中，然后统计长度，再进行处理
					String[] ss = tempString.split(",");
					TimeNanos = Long.valueOf(ss[2]);
					FullBiasNanos = Long.valueOf(ss[5]);
					BiasNanos = 0;
					
					String ConstellationType=ss[28];
					if(ConstellationType.equals("1")||ConstellationType.equals("6")){
						
						long ReceivedSvTimeNanos = Long.valueOf(ss[14]);
						if(ReceivedSvTimeNanos<1E13){
							
						}else
							list.add(tempString);
					}
				

				} 
//				else if (tempString.startsWith("NMEA,$GPGGA"))
				else if (!tempString.startsWith("Raw"))
//				else if (tempString.startsWith("Fix"))
				
				{
					restart = true;

					if(list.size()==0)
					{
						continue;
					}
					
					{//头部

						int len = list.size();// gggggggggggggggggggggggggggggggggggg
						String head = String.format("$$HGCODE,%d,%f,%d", TimeNanos - (FullBiasNanos + BiasNanos), 0.0, len);
						wf.writeFileWithoutN(head);
					}
					
					for (String listStr : list) {

						String[] ss = listStr.split(",");
						TimeNanos = Long.valueOf(ss[2]);
						String ConstellationType=ss[28];
						String GPS_BDS = null;
						if(ConstellationType.equals("1")){
							GPS_BDS = "G";
						}else if(ConstellationType.equals("6")){
							GPS_BDS = "B";
						}else{
							continue;
						}

//						double CarrierFrequencyHz = 0.0;
//						if (ss[22] != null && ss[22].length() > 1)
//							CarrierFrequencyHz = Double.valueOf(ss[22]);
//
//						String GPS_BDS = null;
//						if (CarrierFrequencyHz < 1.57E9) {
//							GPS_BDS = "B";
//						} else {
//							GPS_BDS = "G";
//						}
						long ReceivedSvTimeNanos = Long.valueOf(ss[14]);

						double PseudorangeRateMetersPerSecond = Double.valueOf(ss[17]);

						long ggg = ReceivedSvTimeNanos % 1000_000_000;
						long time = TimeNanos % 1000_000_000;
						
						long lightTime = 1000_000_000 - ggg + time;
						
						long TimeOffsetNanos = 0;
						FullBiasNanos = Long.valueOf(ss[5]);
						BiasNanos = 0;

//						double PseudorangeInMeters = 299792458 * lightTime / 1000_000_000.0;
//						double DopplerShiftInHz = -PseudorangeRateMetersPerSecond;
						
//						System.out.println("TimeNanos:"+TimeNanos+"|||FullBiasNanos:"+FullBiasNanos+"|||ReceivedSvTimeNanos:"+ReceivedSvTimeNanos);
						
						
						
						
						double PseudorangeInMeters=299792458.0 * ((TimeNanos + TimeOffsetNanos - FullBiasNanos - BiasNanos) - ReceivedSvTimeNanos -((long)((-1*FullBiasNanos)/604800E9)*604800E9)) / 1000000000.0;
						double fc=0.0;
						if(GPS_BDS.equals("G"))
						{
							fc=1575420000.0;
						}else{
							fc=1561098000.0;
						}
						double DopplerShiftInHz = (-1 * PseudorangeRateMetersPerSecond) / (299792458.0 / fc) ;

						int State = Integer.valueOf(ss[13]);
						int AccumulatedDeltaRangeState = Integer.valueOf(ss[19]);
						int Svid = Integer.valueOf(ss[11]);

						double AccumulatedDeltaRangeMeters = Double.valueOf(ss[20]);

						// double SnrInDb=Double.valueOf(ss[27]);
						double Cn0DbHz=Double.valueOf(ss[16]);
						
						String gg = String.format("|%d %s %d %f %f %f %d", Svid, GPS_BDS, AccumulatedDeltaRangeState, PseudorangeInMeters,
								AccumulatedDeltaRangeMeters, DopplerShiftInHz, (int)(Cn0DbHz+0.5));
						sb.append(gg);

						
					}
					wf.writeFileWithoutN(sb.toString());
					wf.writeFileWithoutN("\r\n");
					list.clear();
				}

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}
	
	
	
	
	
	
	
	
	
	//施垒串口转换
	@Test
	public void test005adfasfdasfasd() {

		WriteFile wf = new WriteFile("E:\\qq数据\\1607114262\\FileRecv\\aaaa.txt");

		File file = new File("E:\\qq数据\\1607114262\\FileRecv\\a.txt");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				String[] ss = tempString.split(" ");
				String h=null;
				String l=null;
				for(int i=1;i<ss.length;i++){
					if(i%2==1){
						//高位
						h=ss[i];
					}else{
						//低位
						l=ss[i];
						//转换，放到文件中去
						byte[] hh=Byte2Hex.HexString2Bytes(h);
						byte[] ll=Byte2Hex.HexString2Bytes(l);
						
						int gg=(int)(((int)(hh[0]&0xff)<<8)+(ll[0]&0xff));
						
						wf.writeFileWithoutN(gg+" ");
					}
				}
				wf.writeFileWithoutN("\n");
				
				
				
				
					
				
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}
	
	
	
	
	
	

	class WriteFile {
		private FileOutputStream fos = null;

		public WriteFile(String filePath) {

			File file = new File(filePath);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				fos = new FileOutputStream(file, false);// true表示追加打开
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		}

		// 写文件。不使用log4j

		protected void writeFile(String msg) {
			try {
				System.out.print(msg);
				fos.write((msg + "\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		protected void writeFileWithoutN(String msg) {
			try {
				System.out.print(msg);
				fos.write((msg).getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void close() {

			// close前可以做一些统计指标的打印。

			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
