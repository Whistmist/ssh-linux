package com.mist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SshLinuxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SshLinuxApplication.class, args);
    }

}
