package com.photon.mytool.test.xmltest;

import com.photon.mytool.utils.io.IOUtil;
import com.photon.mytool.utils.xml.XmlUtil;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * @author lzx
 * @description
 * @date 2023/03/28/ 14:47
 */
public class Test2 {
    public static void main(String[] args) {
        String xml = IOUtil.readXmlData("20013000002_17.xml");
        NodeList labelFromXml = XmlUtil.getLabelFromXml(xml, "CertNo");
        List<String> labelValueFromXml = XmlUtil.getLabelValueFromXml(labelFromXml);
        System.out.println(labelValueFromXml);
    }
}
