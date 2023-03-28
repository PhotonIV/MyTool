package com.photon.mytool.factory.nodefactory;

import org.w3c.dom.Node;

/**
 * @author lzx
 * @description 获取标签值工厂
 * @date 2023/03/28/ 11:51
 */
public interface NodeValueStrategy {
   String getNodeValue(Node node);
}
