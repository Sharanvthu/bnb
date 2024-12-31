package com.airbnb.bnb.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


import org.modelmapper.ModelMapper;
@Configuration
public class SecurityConfig {

    @Bean

    public ModelMapper getmodelMapper(){
        return new ModelMapper();
    }




    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        //h(cd)2
        http.csrf().disable().cors().disable();

        // haap
       http.authorizeHttpRequests().anyRequest().permitAll();

        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);


        //      http.authorizeHttpRequests()
//
//                .requestMatchers("/api/v1/auth/createuser","/api/v1/auth/createpropertyowner","/api/v1/auth/login")
//                .permitAll()
//                .requestMatchers("/api/v1/property/addProperty").hasAnyRole("OWNER","ADMIN","MANAGER")
//                 .requestMatchers("/api/v1/auth/createpropertymanager").hasRole("ADMIN")
//                  .anyRequest().authenticated();

      return http.build();

    }

}
