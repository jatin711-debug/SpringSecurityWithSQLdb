package com.sheridan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginAccessDenied denyhandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable();
    http.headers().frameOptions().disable();
    http.authorizeRequests()
        .antMatchers("/secure/**").hasRole("USER")
            .antMatchers("/", "/js/**", "/css/**", "/images/**", "/permission-denied").permitAll()
                .antMatchers("/h2-console/**").permitAll()           
                    .antMatchers("/**").denyAll()
                        .anyRequest().authenticated()
                            .and().formLogin()
                                .loginPage("/login").permitAll()
                                    .and().logout()
                                        .invalidateHttpSession(true)
                                            .clearAuthentication(true)
                                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                                    .logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling()
                                                        .accessDeniedHandler(denyhandler);

    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // auth.inMemoryAuthentication()
    //     .passwordEncoder(NoOpPasswordEncoder.getInstance())
    //         .withUser("frank@frank.com").password("1234").roles("USER")
    //             .and().withUser("guest@guest.com").password("password").roles("GUEST");
    // }
}
