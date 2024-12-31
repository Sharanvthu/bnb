package com.airbnb.bnb.controller;

import com.airbnb.bnb.payload.AppUserDto;
import com.airbnb.bnb.payload.JWTToken;
import com.airbnb.bnb.payload.LoginDto;
import com.airbnb.bnb.repository.AppUserRepository;
import com.airbnb.bnb.service.AppUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private AppUserServiceImpl appUserService;



    public AuthController(AppUserRepository appUserRepository, AppUserServiceImpl appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/createUser")
    public ResponseEntity<AppUserDto> createUser(@RequestBody AppUserDto dto) {

        dto.setRoll("ROLL_USER");

        AppUserDto appUser = appUserService.createUser(dto);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }




    @PostMapping("/createpropertyowner")
    public ResponseEntity<AppUserDto> createPropertyOwner(@RequestBody AppUserDto dto) {

        dto.setRoll("ROLL_USER");

        AppUserDto appUser = appUserService.createUser(dto);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }




    @PostMapping("/createpropertymanager")
    public ResponseEntity<AppUserDto> createPropertyManager(@RequestBody AppUserDto dto) {

        dto.setRoll("ROLL_USER");

        AppUserDto appUser = appUserService.createUser(dto);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }









    //login is a user and serch in the hotel and booking  --Example

//    @PostMapping("/createuser")
//    public ResponseEntity<AppUser> createUser(
//            @RequestBody AppUser user
//
//    ){
//
//        Optional<AppUser>opEmail =appUserRepository.findByEmail(user.getEmail());
//
//        if(opEmail .isPresent()){
//
//            throw new UserExists("Email Id Exists");
//        }
//
//
//
//        Optional<AppUser>opUsername =appUserRepository.findByUsername(user.getUsername());
//
//        if(opUsername .isPresent()){
//
//            throw new UserExists("username  Exists");
//        }
//
//        user.setRoll("ROLL_USER");
//        AppUser savedUser = appUserService.createUser(user);
//
//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//
//    }
//




    //Create Property Oner

    //hotel details and hotel booking in security is puerpuse  --- Example

//    @PostMapping("/createpropertyowner")
//    public ResponseEntity<AppUser> createPropertyOwner(
//            @RequestBody AppUser user
//
//    ){
//
//        Optional<AppUser>opEmail =appUserRepository.findByEmail(user.getEmail());
//
//        if(opEmail .isPresent()){
//
//            throw new UserExists("Email Id Exists");
//        }
//
//
//
//        Optional<AppUser>opUsername =appUserRepository.findByUsername(user.getUsername());
//
//        if(opUsername .isPresent()){
//
//            throw new UserExists("username  Exists");
//        }
//
//        user.setRoll("ROLL_OWNER");
//        AppUser savedUser = appUserService.createUser(user);
//
//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//
//    }






//ADmin


    //@PostMapping("/createpropertymanager")





    @PostMapping("/login")
    public ResponseEntity<?>signIn(@RequestBody LoginDto loginDto){

        String token=appUserService.verifyLogin(loginDto);
        JWTToken jwtToken=new JWTToken();

        if(token!=null){
            jwtToken.setTokenType("JWT");
            jwtToken.setToken(token);

            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid username/Password",HttpStatus.UNAUTHORIZED);

        }
    }




}
