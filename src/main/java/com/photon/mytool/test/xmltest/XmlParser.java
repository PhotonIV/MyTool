package com.photon.mytool.test.xmltest;

/**
 * @author lzx
 * @description xml解析
 * @date 2023/03/30/ 10:59
 */
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {
    public static void main(String[] args) throws Exception {
        String filePath = "R:\\work\\roadtel\\project\\csr\\rdworkmanag\\RDWork\\src\\main\\resources\\com.roadtel.csr.sao.mock\\30013000001_10.xml";
        String tagName = "BODY";
        String result = "";

        // 创建DocumentBuilder对象
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        // 解析XML文件为Document对象
        Document doc = dBuilder.parse(filePath);

        // 获取指定标签名的节点列表
        NodeList bodyList = doc.getElementsByTagName(tagName);

        // 遍历节点列表，获取节点内的文本内容
        for (int i = 0; i < bodyList.getLength(); i++) {
            Node bodyNode = bodyList.item(i);
            if (bodyNode.getNodeType() == Node.ELEMENT_NODE) {
                Element bodyElement = (Element) bodyNode;
                result += nodeToString(bodyElement);
            }
        }

        System.out.println(result);
    }

    // 将节点及其子节点转换为字符串
    private static String nodeToString(Node node) {
        String result = "";
        result += "<" + node.getNodeName() + ">";
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                result += nodeToString(childNode);
            } else if (childNode.getNodeType() == Node.TEXT_NODE) {
                result += childNode.getTextContent();
            }
        }
        result += "</" + node.getNodeName() + ">";
        return result;
    }
}


