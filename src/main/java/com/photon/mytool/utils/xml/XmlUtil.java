package com.photon.mytool.utils.xml;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.photon.mytool.factory.nodefactory.NodeValueStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.*;

@Slf4j
public class XmlUtil {

    public static String toXML(Object obj, Class[] valueTypes) {
        StringWriter writer = null;
        try {
            JAXBContext context = JAXBContext.newInstance(valueTypes);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xm头声明信息
            writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXML(String xml, Class<T>... valueType) {
        StringReader stringReader = null;
        try {
            JAXBContext context = JAXBContext.newInstance(valueType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            stringReader = new StringReader(xml);
            return (T) unmarshaller.unmarshal(stringReader);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (stringReader != null) {
                stringReader.close();
            }
        }
    }

    public static String documentToString(final Document doc) {
        // outputs a DOM structure to plain String

        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

    public static NodeList getLabelFromXml(String xml, String label) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xml));
            Document doc = db.parse(inputSource);
            NodeList nodeList = doc.getElementsByTagName(label);
            return nodeList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("你获取xml标签方法出错了", e);
        }
    }

    public static List<String> getLabelValueFromXml(NodeList nodeList) {
        List<String> valueList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            String value = NodeValueStrategyFactory.generateRandomValue(nodeList.item(i));
            valueList.add(value);
        }
        return valueList;
    }

    public static <T> List<T> parseRetDataListWithType(Document doc, String infoKey, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        try {
            NodeList nodeList = null;
            if (StringUtils.isBlank(infoKey)) {
                nodeList = doc.getChildNodes();
            } else {
                nodeList = doc.getElementsByTagName(infoKey);
            }
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NodeList children = node.getChildNodes();
                Map<String, String> itemMap = new LinkedHashMap<>();
                Constructor<T> constructor = tClass.getConstructor();
                T t = constructor.newInstance();


                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    //防止出现前后大段空格的情况
                    itemMap.put(child.getNodeName(), child.getTextContent().trim());
                }
                CopyOptions opt = CopyOptions.create();
                opt.ignoreCase();
                BeanUtil.fillBeanWithMap(itemMap, t, opt);
                list.add(t);
            }
        } catch (Exception e) {
            log.error("error when parse xml", e);
        }

        return list;
    }

    public static Map<String, Object> parseRetDataList(Document doc, String infoKey) {
        Map<String, Object> itemMap = new LinkedHashMap<>();

        try {
            NodeList nodeList = null;
            if (StringUtils.isBlank(infoKey)) {
                nodeList = doc.getChildNodes();
            } else {
                nodeList = doc.getElementsByTagName(infoKey);
            }
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                itemMap.put(node.getNodeName() + i, getChileNodes2(node));

            }
        } catch (Exception e) {
            log.error("error when parse xml", e);
        }

        return itemMap;
    }

    public static Map<String, Object> getChileNodes(Node child) {
        NodeList childList = child.getChildNodes();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < childList.getLength(); i++) {
            Node childNode = childList.item(i);
            if (childNode.hasChildNodes() && !"#text".equals(childNode.getNodeName())) {
                map.put(childNode.getNodeName(), getChileNodes(childNode));
            } else {
                String nodeName = childNode.getNodeName();
                boolean equals = "#text".equals(childNode.getNodeName());

                map.put(childNode.getNodeName(), childNode.getTextContent().trim());
            }
        }
        return map;
    }

    public static Object getChileNodes2(Node child) {
        NodeList childList = child.getChildNodes();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < childList.getLength(); i++) {
            Node childNode = childList.item(i);

            if (childNode.hasChildNodes()) {
                map.put(childNode.getNodeName(), getChileNodes2(childNode));

            } else {
                map.put(childNode.getNodeName(), childNode.getTextContent().trim());
            }
        }

        return map;
    }

    public static String getLabelAndValue(Document doc, String label) {
        Map<String, Object> itemMap = XmlUtil.parseRetDataList(doc, label);
        String s = itemMap.toString();
        String replace = s.replace("#text=, ", "").replace("#text=", "");
        return replace;
    }

    public static String getLabelAndInside(String path,String label) throws Exception {
        String filePath =path;
        String tagName = label;
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
        return result;
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