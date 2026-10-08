package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

/**
 * 终端服务启动程序
 * 启动类放在com.ruoyi根包下，保证能扫描到ruoyi-framework、ruoyi-system中的Bean
 *
 * @author trae
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiClientApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiClientApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  终端服务启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
