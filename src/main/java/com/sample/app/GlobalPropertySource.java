package com.sample.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
    @PropertySource( value = "file:C:/sts-workspace/exSpringboot_01/extra.properties", ignoreResourceNotFound = true )
})
public class GlobalPropertySource {
    
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    
    @Value("${spring.datasource.url}")
    private String url;
    
    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;
 
    @Value("${system.key}")
    private String key;
    
    public String getDriverClassName() {
        return driverClassName;
    }
 
    public String getUrl() {
        return url;
    }
 
    public String getUsername() {
        return username;
    }
 
    public String getPassword() {
        try {
            return CryptoUtil.decryptAES256(password, key);
        } catch (Exception e) {
            System.out.println("복호화 실패");
            System.out.println(e);
            return "";
        }
    }
}
