package com.springsecurity.basic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsecurity.basic.Util.UserPrincipal;
import com.springsecurity.basic.Util.Util;
import com.springsecurity.basic.domain.Roles;
import com.springsecurity.basic.domain.User;
import com.springsecurity.basic.dto.UserDTO;
import com.springsecurity.basic.exception.UserAlreadyExistException;
import com.springsecurity.basic.repository.UserRolesRepository;
import com.springsecurity.basic.repository.UserRepository;
@Service
@Transactional
public class UserService implements UserDetailsService{
	private final org.slf4j.Logger log = LoggerFactory.getLogger(UserService.class);

		@Autowired private UserRepository userRepository;
	    @Autowired private UserRolesRepository userRolesRepository;
	    @Autowired   private  PasswordEncoder passwordEncoder;
	    @Override
	    @Transactional
	    public UserDetails loadUserByUsername(String login)  {
	        User user = userRepository.findOneByLogin(login);
	        if (user == null) {
	            return null;
	        }
	      /*  List<Roles> authorities = getAuthorities(user);
	        List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
	                .map(this::mapToSimpleGrantedAuthority)
	                .collect(toList());*/
	        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	        for (Roles roles : getAuthorities(user)) {
	            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roles.getName());
	            grantedAuthorities.add(grantedAuthority);
	        }

	        return new UserPrincipal(user);
	    }


	    private List<Roles> getAuthorities(User user) {
	       
	            return user.getAuthorities();
	       
	    }

	    private List<Roles> findAuthorities(List<String> set) {
		     
	    	List<Roles> lstRoles = new ArrayList<Roles>();
	    	for (String name : set) {
	    		lstRoles.add(userRolesRepository.findByName(name));
				
			}
            return lstRoles;
       
    }

	    public User registeruser(UserDTO userDTO) throws Exception {
	        if (emailExist(userDTO.getEmail())) {
	            throw new UserAlreadyExistException(
	              "There is an account with that email adress:" + userDTO.getEmail());
	        }
	        User user = new User();
	        user.setFirstName(userDTO.getFirstName());
	        user.setLastName(userDTO.getLastName());
	        user.setLogin(userDTO.getLogin());
	        user.setPassword(passwordEncoder.encode(Util.generatePassword()));
	         
	        user.setEmail(userDTO.getEmail());
	        user.setAuthorities(findAuthorities(userDTO.getAuthorities()));
	        return userRepository.save(user);
	    }
	    private boolean emailExist(final String email) {
	        return userRepository.findOneByEmail(email) != null;
	    }
}
