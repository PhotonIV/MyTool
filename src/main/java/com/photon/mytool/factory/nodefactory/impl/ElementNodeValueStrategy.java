package com.photon.mytool.factory.nodefactory.impl;

import com.photon.mytool.factory.nodefactory.NodeValueStrategy;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author lzx
 * @description element类型的标签
 * @date 2023/03/28/ 11:52
 */
public class ElementNodeValueStrategy implements NodeValueStrategy {
    @Override
    public String getNodeValue(Node node) {
        Element element = (Element) node;
        String nodeValue = element.getTextContent();
        return nodeValue;
    }
}
