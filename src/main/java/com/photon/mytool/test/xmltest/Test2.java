package com.photon.mytool.test.xmltest;

import com.photon.mytool.utils.io.IOUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzx
 * @description
 * @date 2023/03/28/ 14:47
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        String xml = IOUtil.readXmlData("10023000001_04.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xml));
        Document doc = db.parse(inputSource);
        NodeList body = doc.getElementsByTagName("BODY");
        List<Element> elementList = new ArrayList<>();
        List<Node> labelList = new ArrayList<>();
        for (int i = 0; i < body.getLength(); i++) {
            Element label = (Element) body.item(i);

            NodeList childList = label.getChildNodes();

            for (int j = 0; j < childList.getLength(); j++) {
                Node child = childList.item(j);
                // 判断是否为元素节点
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) child;

                    // 获取标签名和标签值
                    String name = childElement.getNodeName();
                    String value = childElement.getTextContent();

                    // 输出标签名和标签值
                    System.out.println(name + ": " + value);
                }
            }
            elementList.add(label);
        }

        System.out.println(elementList);

    }
}
