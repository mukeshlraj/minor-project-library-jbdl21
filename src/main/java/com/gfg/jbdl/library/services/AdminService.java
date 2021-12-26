package com.gfg.jbdl.library.services;

import com.gfg.jbdl.library.models.Admin;
import com.gfg.jbdl.library.models.User;
import com.gfg.jbdl.library.repositories.AdminRepository;
import com.gfg.jbdl.library.repositories.UserRepository;
import com.gfg.jbdl.library.requests.AdminCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminService {

    @Value("${app.security.admin_role}")
    String ADMIN_ROLE;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Admin getAdmin(int adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    @Transactional
    public void saveAdmin(AdminCreateRequest adminCreateRequest) {
        Admin admin = Admin.builder()
                .email(adminCreateRequest.getEmail())
                .name(adminCreateRequest.getName())
                .build();

        adminRepository.save(admin);

        User user = User.builder()
                .username(admin.getEmail())
                .password(passwordEncoder.encode(adminCreateRequest.getPassword()))
                .authorities(ADMIN_ROLE)
                .createdOn(admin.getCreatedOn())
                .build();

        userRepository.save(user);
    }

}
