package com.photon.mytool.controller;

import com.photon.mytool.utils.io.IOUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author lzx
 * @description IO相关的控制器
 * @date 2023/03/16/ 16:49
 */
//@RestController
@RequestMapping("/io")
public class IoController {

    @PostMapping("/getFileName")
    public String getAllFileNames(@RequestParam("path") String path, Model model) throws UnsupportedEncodingException {
//        path = URLDecoder.decode(path, "UTF-8");
        List<String> fileNames = IOUtil.getFileNames(path);
        model.addAttribute("fileNames", fileNames);
        return "fileName";
    }

    @GetMapping("/change")
    public String change(Model model) throws UnsupportedEncodingException {
//        path = URLDecoder.decode(path, "UTF-8");
//        model.addAttribute("fileNames", fileNames);
        return "fileName";
    }
}
