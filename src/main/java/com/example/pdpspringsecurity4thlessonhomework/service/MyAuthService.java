package com.example.pdpspringsecurity4thlessonhomework.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {

    List<User> userList = new ArrayList<>(
            Arrays.asList(
                    new User("pdp","pdpUz",new ArrayList<>()),
                    new User("King123","king",new ArrayList<>()),
                    new User("KFC","kfc123",new ArrayList<>()),
                    new User("Spring","java",new ArrayList<>())
            )
     );
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (User user : userList) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }

        throw new UsernameNotFoundException("User topilmadi");
    }
}
