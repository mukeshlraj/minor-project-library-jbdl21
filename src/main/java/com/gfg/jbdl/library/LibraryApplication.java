package com.gfg.jbdl.library;

import com.gfg.jbdl.library.models.Admin;
import com.gfg.jbdl.library.requests.AdminCreateRequest;
import com.gfg.jbdl.library.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	@Autowired
	AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		AdminCreateRequest admin = AdminCreateRequest.builder()
//				.name("Mukesh")
//				.email("mukesh@gmail.com")
//				.password("mukesh123")
//				.build();
//
//		adminService.saveAdmin(admin);
	}
}
