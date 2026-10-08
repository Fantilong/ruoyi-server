package com.ruoyi.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ruoyi.common.config.RuoYiConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * 终端服务接口文档配置
 *
 * @author trae
 */
@Configuration
public class ClientSwaggerConfig
{
    /** 系统基础配置 */
    @Autowired
    private RuoYiConfig ruoyiConfig;

    /**
     * 自定义终端接口文档信息
     */
    @Bean
    public OpenAPI clientOpenApi()
    {
        return new OpenAPI().info(new Info()
            // 设置标题
            .title("图书借阅终端_接口文档")
            // 描述
            .description("供囚犯终端使用的接口，包括浏览图书、发起借阅申请等")
            // 作者信息
            .contact(new Contact().name(ruoyiConfig.getName()))
            // 版本
            .version("版本号:" + ruoyiConfig.getVersion()));
    }
}
