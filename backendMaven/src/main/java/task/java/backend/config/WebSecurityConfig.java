/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import task.java.backend.service.UserService;

/**
 *
 * @author illyasviel
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCrPasswdEnc() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.csrf().disable();
        security.authorizeRequests().antMatchers("/reg").not().fullyAuthenticated();
        security.authorizeRequests().antMatchers("/", "/resources/**").permitAll();
        security.authorizeRequests()
                .antMatchers("/add_audio", "/audios/**", "/users", "/users/**")
                .hasRole("ADMIN");
        security.authorizeRequests().anyRequest().authenticated();
        security.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
        security.logout().permitAll().logoutSuccessUrl("/");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCrPasswdEnc());
    }
}
