package com.mist;


import com.mist.domain.entity.LinnuxServer;
import com.mist.infra.utils.redis.RedisHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SshLinuxApplication.class)
public class SshLinuxApplicationTests {
    @Autowired
    private RedisHelper redisHelper;

    @Test
    public void contextLoads() {
        LinnuxServer ssh = new LinnuxServer("39.105.125.176",22,"root","Hh12141996");
        List<String> list = ssh.execute("pwd");
        System.out.println("list----: "+list);
        ssh.execute("exit");
        //ssh.execute("exit");
    }

    @Test
    public void redisLoads(){
        String prefix = "mist:test";
        redisHelper.hshPut(prefix,"12","hahahha");
        String s = redisHelper.hshGet(prefix, "12");
        redisHelper.hshDelete(prefix,"12");
        System.out.println(s);
    }

}
