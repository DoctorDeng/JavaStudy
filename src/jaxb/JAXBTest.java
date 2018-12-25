package jaxb;

import java.util.ArrayList;
import java.util.List;

import jaxb.parameter.HurtDoctorParameter;
import jaxb.parameter.WebServiceParameter;
import jaxb.result.DataResult;
import jaxb.result.WebServiceResultBuilder;
import jaxb.util.JaxbUtil;

public class JAXBTest {
	public static void testBeanToXml() {
		HurtDoctorVO h1 = new HurtDoctorVO("何敏", "sssss", null, "sAs使得开发商的看法", "爱上对方过后就哭了", "适当放宽");
		HurtDoctorVO h2 = new HurtDoctorVO("何敏2", "sssss", "男", "sAs使得开发商的看法", "爱上对<>方过后就哭了", "适当放宽");
		
		List<HurtDoctorVO> list = new ArrayList<HurtDoctorVO>();
		list.add(h1);
		list.add(h2);
		
		DataResult<HurtDoctorVO> obj = new DataResult<HurtDoctorVO>(list);
		obj.setResultCode("1");
		
		WebServiceResultBuilder builder = new WebServiceResultBuilder();
		System.out.println(builder.build("1", list, HurtDoctorVO.class));
	}
	
	public static void testXmlToBean() {
		WebServiceParameter<HurtDoctorParameter> t = null;
		String xml = "<data><datainfo><userName /><password><![CDATA[mingke123]]></password> <caseName>何敏</caseName> <idCard>330621198403170010</idCard><orgCode>000004</orgCode> <serviceType>01</serviceType> </datainfo></data>";
		try {
			t =  JaxbUtil.xmlStringToBean(xml, WebServiceParameter.class, HurtDoctorParameter.class);
			System.out.println(t.getParam().toString());
		} catch (Exception e) {
			e.printStackTrace();
	     }
	}
	
	
	public static void main(String[] args) {
		testXmlToBean();
		//testBeanToXml();
	}
}
