package com.photon.mytool.factory.nodefactory.impl;

import com.photon.mytool.factory.nodefactory.NodeValueStrategy;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author lzx
 * @description text标签
 * @date 2023/03/28/ 14:38
 */
public class TextNodeValueStrategy implements NodeValueStrategy {
    @Override
    public String getNodeValue(Node node) {
        Text text = (Text) node;
        String nodeValue = text.getNodeValue();
        return nodeValue;
    }
}
