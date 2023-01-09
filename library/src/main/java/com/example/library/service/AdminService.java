package com.example.library.service;

import com.example.library.dto.AdminDto;
import com.example.library.model.Admin;

public interface AdminService {
    Admin findByUserName(String username);
    Admin save (AdminDto adminDto);
}
