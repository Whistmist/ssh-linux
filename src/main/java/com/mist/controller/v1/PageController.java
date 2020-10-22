package com.mist.controller.v1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 *
 * @author heng.huang@hand-china.com 2020/10/18
 */
@Controller
public class PageController {

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model){
        return "/page/welcome-0.html";
    }


}
