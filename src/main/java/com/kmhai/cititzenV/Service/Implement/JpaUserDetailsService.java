package com.kmhai.cititzenV.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kmhai.cititzenV.Repository.UserRepo;
import com.kmhai.cititzenV.Security.SecurityUser;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByUsername(username);
        
        return user.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }
}
