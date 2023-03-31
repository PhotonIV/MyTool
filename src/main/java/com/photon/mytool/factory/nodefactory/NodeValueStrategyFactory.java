package com.photon.mytool.factory.nodefactory;

import com.photon.mytool.factory.nodefactory.impl.AttributeNodeValueStrategy;
import com.photon.mytool.factory.nodefactory.impl.ElementNodeValueStrategy;
import com.photon.mytool.factory.nodefactory.impl.TextNodeValueStrategy;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzx
 * @description 获取标签值工厂
 * @date 2023/03/28/ 14:40
 */
public class NodeValueStrategyFactory {
    private static final Map<Short, NodeValueStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(Node.ATTRIBUTE_NODE, new AttributeNodeValueStrategy());
        strategyMap.put(Node.ELEMENT_NODE, new ElementNodeValueStrategy());
        strategyMap.put(Node.TEXT_NODE, new TextNodeValueStrategy());
        // 添加其他类型的策略
    }

    public static String generateRandomValue(Node node) {
        short nodeType = node.getNodeType();
        NodeValueStrategy strategy = getStrategy(nodeType);
        return strategy.getNodeValue(node);
    }

    private static NodeValueStrategy getStrategy(short nodeType) {
        NodeValueStrategy strategy = strategyMap.get(nodeType);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for " + nodeType);
        }
        return strategy;
    }
}
