package com.photon.mytool.factory.nodefactory.impl;

import com.photon.mytool.factory.nodefactory.NodeValueStrategy;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**
 * @author lzx
 * @description Attribute标签
 * @date 2023/03/28/ 14:36
 */
public class AttributeNodeValueStrategy implements NodeValueStrategy {
    @Override
    public String getNodeValue(Node node) {
        Attr attribute = (Attr) node;
        String value = attribute.getValue();
        return value;
    }
}
