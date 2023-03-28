package com.photon.mytool.test.xmltest;

import com.photon.mytool.utils.io.IOUtil;
import com.photon.mytool.utils.xml.XmlUtil;
import org.w3c.dom.NodeList;

/**
 * @author lzx
 * @description xml测试类
 * @date 2023/03/28/ 11:23
 */
public class Test1 {

    public static void main(String[] args) {
        String xml = IOUtil.readXmlData("20013000002_17.xml");
        NodeList labelFromXml = XmlUtil.getLabelFromXml(xml, "IdCardOverdueDt");
        for(int i = 0; i < labelFromXml.getLength(); i++) {
            System.out.println(labelFromXml.item(i).getTextContent());
//            NodeList childNodes = labelFromXml.item(i).getChildNodes();
//            for(int j = 0; j < childNodes.getLength(); j++) {
//                Node childNode = childNodes.item(j);
//                System.out.println(childNode.getNodeValue());
//
//            }
        }
    }
}
