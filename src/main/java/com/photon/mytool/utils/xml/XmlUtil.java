package com.photon.mytool.utils.xml;

import com.photon.mytool.factory.nodefactory.NodeValueStrategyFactory;
import org.w3c.dom.Document;
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
import java.util.ArrayList;
import java.util.List;

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
}