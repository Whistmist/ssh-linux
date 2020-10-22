package com.mist.controller.v1;

import com.mist.domain.entity.LinnuxServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务器接口
 *
 * @author heng.huang@hand-china.com 2020/10/18
 */
@RestController
public class LinuxServerController {


    @RequestMapping("/addWindow")
    public List<String> goNewAddServer(HttpServletRequest req) {
        List<String> list = null;
//        //ssh连接linux---------从xml配置文件中读取参数
//        ApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Permission per = (Permission) ct.getBean("linux");
//        String hostname = per.getIp();
//        int port  = per.getPort();
//        String username = per.getUsername();
//        String password = per.getPassword();
//        System.out.println("ssh连接linux---------Permission="+per.toString());

        try {
            LinnuxServer ssh = new LinnuxServer("39.105.125.176",22,"root","Hh12141996");
            list = ssh.execute("tail -1000 /usr/src/nginx/logs/access.log");
            //或者将账号写死
            //ssh = new SSHLinux("192.168.1.12",22,"root","123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
