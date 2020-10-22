package com.mist.controller.v1;

import com.mist.domain.entity.LinnuxServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面跳转
 *
 * @author heng.huang@hand-china.com 2020/10/18
 */
@RestController
public class SettingController {


    @PostMapping("/addSetting")
    public LinnuxServer addSetting(@RequestBody LinnuxServer linnuxServer) throws Exception {
        System.out.println(linnuxServer);

        LinnuxServer ssh = new LinnuxServer(linnuxServer.getHostname(),linnuxServer.getPort(),linnuxServer.getUsername(),linnuxServer.getPassword());
        if(ssh.initSession()){
            return linnuxServer;
        }else {
            throw new Exception("error");
        }
    }



}
