package com.gfg.jbdl.library.controllers;

import com.gfg.jbdl.library.models.Admin;
import com.gfg.jbdl.library.models.User;
import com.gfg.jbdl.library.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin")
    public Admin getAdminDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        int adminId = user.getId();
        return adminService.getAdmin(adminId);
    }
}
