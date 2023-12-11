package com.memegen.memegeneratorbackend.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }



    //To add User to database
    @Override
    public User addUser(User user){
         String passwordCurrent= user.getPassword();
        user.setPassword(passwordEncoder.encode(passwordCurrent));
       return  userRepository.save(user);

    }



    @Override
    public boolean checkUserExist(String email){

        Long count = userRepository.existsByEmail(email);

        return count==1;
    }

}











