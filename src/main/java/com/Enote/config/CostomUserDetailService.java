package com.Enote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Enote.entity.User;
import com.Enote.repository.UserRepository;

@Service
public class CostomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CostomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new CostomUser(user);
        }
    }
}
