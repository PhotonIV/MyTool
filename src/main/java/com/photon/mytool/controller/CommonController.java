package com.photon.mytool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lzx
 * @description 通用controller
 * @date 2023/03/30/ 9:35
 */
@Controller
public class CommonController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
