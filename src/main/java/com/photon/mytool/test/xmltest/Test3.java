package com.photon.mytool.test.xmltest;

import com.photon.mytool.utils.io.IOUtil;
import com.photon.mytool.utils.xml.XmlUtil;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Map;

/**
 * @author lzx
 * @description
 * @date 2023/03/29/ 16:43
 */
public class Test3 {
    public static void main(String[] args) throws Exception {
        String xml = IOUtil.readXmlData("10023000001_04.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xml));
        Document doc = db.parse(inputSource);
        Map<String, Object> itemMap = XmlUtil.parseRetDataList(doc, "BODY");
        String s = itemMap.toString();
        String replace = s.replace("#text=, ", "").replace("#text=", "");
        System.out.println(replace);

    }
}
