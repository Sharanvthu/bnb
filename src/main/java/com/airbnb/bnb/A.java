package com.airbnb.bnb;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class A {

    public static  void main(String[]args){
    //    PasswordEncoder en=new BCryptPasswordEncoder();
      //  System.out.println(en.encode("testing"));

//        String encodedPassword = BCrypt.hashpw("testing",BCrypt.gensalt(10));
//
//        System.out.println(encodedPassword);


        String password = BCrypt.hashpw("testing1", BCrypt.gensalt(10));

        System.out.println(password);


    }

}
