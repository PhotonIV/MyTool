package com.photon.mytool.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.photon.mytool.utils.io.IOUtil;
import com.photon.mytool.utils.xml.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzx
 * @description xml相关controller
 * @date 2023/03/29/ 15:46
 */
@Controller
@RequestMapping("/xml")
public class XmlController {


    @ResponseBody
    @GetMapping("/getLabel")
    public JSON getLabel(@RequestParam("path") String path, @RequestParam("label") String label) {
        JSON result = null;
        try {
            String xml = XmlUtil.getLabelAndInside(path,label);
            result = JSONUtil.parseFromXml(xml);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
