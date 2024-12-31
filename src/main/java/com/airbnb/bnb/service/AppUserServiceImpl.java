package com.airbnb.bnb.service;

import com.airbnb.bnb.Exception.UserExists;
import com.airbnb.bnb.entity.AppUser;
import com.airbnb.bnb.entity.Property;
import com.airbnb.bnb.payload.AppUserDto;
import com.airbnb.bnb.payload.LoginDto;
import com.airbnb.bnb.repository.AppUserRepository;
import com.airbnb.bnb.repository.PropertyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.Optional;

@Service
public class AppUserServiceImpl {


    private AppUserRepository appUserRepository;

    private ModelMapper modelMapper;
    private PropertyRepository propertyRepository;


    private JWTService jwtService;

    public AppUserServiceImpl(AppUserRepository appUserRepository, ModelMapper modelMapper, PropertyRepository propertyRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.jwtService = jwtService;
    }

    public AppUserDto createUser(AppUserDto dto) {

        AppUser user = mapToEntity(dto);

        //Convert to Entiry

        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());

        if (opEmail.isPresent()) {
            throw new UserExists("Email ID exists");
        }

        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());

        if (opUsername.isPresent()) {
            throw new UserExists("Username  Exists ");
        }

        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);

        AppUser savedUser = appUserRepository.save(user);
        return EntityToDto(savedUser);
    }


    //MapToEntity
    public AppUser mapToEntity(AppUserDto dto) {

        AppUser map = modelMapper.map(dto, AppUser.class);

        return map;
    }
//
//
//    //Entity To Dto

    public AppUserDto EntityToDto(AppUser user) {
        AppUserDto map = modelMapper.map(user, AppUserDto.class);

        return map;
    }




    public String verifyLogin(LoginDto loginDto) {

        //Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());

        Optional<AppUser> opUser = appUserRepository.findByEmailOrUsername(loginDto.getUsername(), loginDto.getUsername());

        if (opUser.isPresent()) {
            AppUser appUser = opUser.get();

            System.out.println(opUser);

            if (BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())) {

                return jwtService.generateToken(appUser);
            }
        }
return null;

    }
}
