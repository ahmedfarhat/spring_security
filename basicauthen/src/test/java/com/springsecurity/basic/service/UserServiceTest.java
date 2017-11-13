package com.springsecurity.basic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.springsecurity.basic.BasicauthenApplication;
import com.springsecurity.basic.domain.Roles;
import com.springsecurity.basic.dto.UserDTO;
import com.springsecurity.basic.repository.UserRolesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasicauthenApplication.class)
@Transactional
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRolesRepository userRolesRepository;
	private final org.slf4j.Logger log = LoggerFactory.getLogger(UserServiceTest.class);

	private final Long id = null;

	private static final String login = "guest@example.com";
	private static final String firstName = "guest";
	private static final String lastName = "guest";
	private static final String email = "guest@example.com";
	private static final String imageUrl = "image.gif";
	private static final boolean activated = false;
	private static final String langKey = "FR";
	private static final List<String> authorities = Arrays.asList("USER", "GUEST");

	@Test
	public void rolesTest() {
		List<Roles> lstroles = new ArrayList<Roles>();
		Roles role1 = new Roles("USER");
		Roles role2 = new Roles("GUEST");
		lstroles.add(role1);
		lstroles.add(role2);

		userRolesRepository.save(lstroles);
	}

	@Test
	public void registerTest() throws Exception {
		/**
		 * public UserDTO(Long id, String login, String firstName, String lastName,
		 * String email, boolean activated, String imageUrl, String langKey,
		 * List<String> authorities)
		 */
		UserDTO userDTO = new UserDTO(null, login, firstName, lastName, email, activated, imageUrl, langKey,
				authorities);
		log.info(userService.registeruser(userDTO).toString());
		log.info(userService.loadUserByUsername(login).toString());
	}

	
}
