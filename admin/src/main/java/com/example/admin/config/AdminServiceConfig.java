package com.example.admin.config;

import com.example.library.model.Admin;
import com.example.library.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class AdminServiceConfig implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findAdminByUserName(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Could not found username !");
        }
        return new User(admin.getUserName(), admin.getPassword(), admin.getRolesCollection()
                .stream().map(roles -> new SimpleGrantedAuthority(roles.getNameRoles()))
                .collect(Collectors.toList()));
    }
}
