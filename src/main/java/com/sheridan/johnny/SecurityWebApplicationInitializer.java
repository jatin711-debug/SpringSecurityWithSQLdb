package com.sheridan.johnny;
import com.sheridan.security.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
    public SecurityWebApplicationInitializer(){
        super(SecurityConfig.class);
    }
}
