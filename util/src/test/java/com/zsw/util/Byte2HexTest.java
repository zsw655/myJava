package com.zsw.util;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Byte2HexTest {
	@Test
	public void test() {
		byte[] b;
		b= Byte2Hex.HexString2Bytes("556E617574686F72697A65640D0A");
		System.out.println("create:"+new String(b));
		b= Byte2Hex.HexString2Bytes("49435920323030204F4B0D0A");
		System.out.println("create:"+new String(b));
	
		b= Byte2Hex.HexString2Bytes("534F555243455441424C4520323030204F4B0D0A5365727665723A20776E6C62732D6167656E742F312E302E300D0A4E747269702D466C6167733A2073745F66696C7465722C73745F617574682C73745F6D617463682C73745F7374726963742C727473700D0A446174653A205475652C2032392041756720323031372031333A31313A353920474D540D0A436F6E6E656374696F6E3A20636C6F73650D0A436F6E74656E742D547970653A20676E73732F736F757263657461626C650D0A436F6E74656E742D4C656E6774683A203536380D0A0D0A5354523B41474E53533B61676E73733B5254434D20322E333B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A5354523B44474E53533B64676E73733B5254434D20322E343B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A5354523B44474E535333323B64676E73733B5254434D20332E323B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A5354523B4E455452544B33313B6E657472746B3B5254434D20332E313B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A5354523B4E455452544B33323B6E657472746B3B5254434D20332E323B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A5354523B4550483B6570683B5254434D20322E333B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A454E44534F555243455441424C450D0A");
		System.out.println("create:"+new String(b));
		
		
		
	}
	
	@Test
	public void test002() {//这个是ICY OK
		byte[] b;
		b= Byte2Hex.HexString2Bytes("49435920323030204F4B0D0A");
		System.out.println("create:"+new String(b));

		
	}
	
	@Test
	public void test003() {//这个是他们挂载点
		byte[] b;
		b= Byte2Hex.HexString2Bytes("534F555243455441424C4520323030204F4B0D0A5365727665723A20776E6C62732D6167656E742F312E302E300D0A4E747269702D466C6167733A2073745F66696C7465722C73745F617574682C73745F6D617463682C73745F7374726963742C727473700D0A446174653A205765642C2033302041756720323031372030383A34393A323920474D540D0A436F6E6E656374696F6E3A20636C6F73650D0A436F6E74656E742D547970653A20676E73732F736F757263657461626C650D0A436F6E74656E742D4C656E6774683A203536380D0A0D0A5354523B41474E53533B61676E73733B5254434D20322E333B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A5354523B44474E53533B64676E73733B5254434D20322E343B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A5354523B44474E535333323B64676E73733B5254434D20332E323B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A5354523B4E455452544B33313B6E657472746B3B5254434D20332E313B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A5354523B4E455452544B33323B6E657472746B3B5254434D20332E323B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A5354523B4550483B6570683B5254434D20322E333B5254434D2831293B323B4750532B4244533B434D4F4E4F433B43484E3B303B303B313B313B54727565436F72733B6E6F6E653B423B4E3B353030303B6E6F6E650D0A454E44534F555243455441424C450D0A");
		System.out.println("create:"+new String(b));

		
	}
	
	@Test
	public void test004() {//这个是他们挂载点
		byte[] b;
		b= Byte2Hex.HexString2Bytes("D3006D4320004656C66200002405080400000000202000007FFA3A4A1A223A343627FD4510D39287DA0E040BA41387D02F8A626504986E58DB901F2C3C38280EA0772E01F41806A53FFEA5E00A4F02DEEA0C691FC2407F1EC788E9F808DBDFFFFFFFFFFFF800634673EB7F196546B6002D7FE5");
		System.out.println("create:"+new String(b));

		
	}
	@Test
	public void test005() {//这个不知道是啥
		byte[] b;
		b= Byte2Hex.HexString2Bytes("7E557E55D300364090000E4144564E554C4C414E54454E4E410004303030320D5452494D424C45204E4554523904302E30300A353334324B3436343935D37479");
		System.out.println("create:"+new String(b));

		
	}
	
	
}
