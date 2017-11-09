package com.springsecurity.basic.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springsecurity.basic.domain.Roles;
import com.springsecurity.basic.domain.User;
import com.springsecurity.basic.repository.UserRepository;
import com.springsecurity.basic.repository.UserRolesRepository;

@Component
public class DataLoader implements ApplicationRunner {
	

	   @Autowired private UserRepository userRepository;
	   @Autowired private UserRolesRepository userRolesRepo;
	   private  PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
 

	@Transactional
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		User user = new User();
		Roles rl = new Roles("ADMIN");
		userRolesRepo.save(rl);
        user.setFirstName("admin");
        user.setLastName("admin");
         
        user.setPassword(passwordEncoder.encode("123456"));
        user.setLogin("admin@admin.com");
        user.setEmail("admin@admin.com");
       	List<Roles>  roles = new ArrayList<Roles>();
       	roles.add(new Roles("ADMIN"));
        user.setAuthorities(roles);
        userRepository.save(user);

		
	}
}