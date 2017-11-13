package com.springsecurity.basic.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.springsecurity.basic.domain.Roles;
import com.springsecurity.basic.domain.User;



public class UserDTO {
	
		private Long id;
	    @Size(min = 1, max = 50)
	    private String login;
	    @Size(max = 50)
	    private String firstName;
	    @Size(max = 50)
	    private String lastName;
	    @Email
	    @Size(min = 5, max = 100)
	    private String email;
	    @Size(max = 256)
	    private String imageUrl;
	    private boolean activated = false;
	    @Size(min = 2, max = 5)
	    private String langKey;
	    private String createdBy;
	    private List<String> authorities;

	    public UserDTO() {
	        // Empty constructor needed for MapStruct.
	    }

	    public UserDTO(User user) {
	        this(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(),
	            user.getEmail(), user.getActivated(), user.getImageUrl(), user.getLangKey(),
	            user.getAuthorities().stream().map(Roles::getName)
	                .collect(Collectors.toList()));
	    }

	    public UserDTO(Long id, String login, String firstName, String lastName,
	        String email, boolean activated, String imageUrl, String langKey,
	        List<String> authorities) {

	        this.id = id;
	        this.login = login;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.activated = activated;
	        this.imageUrl = imageUrl;
	        this.langKey = langKey;

	        this.authorities = authorities;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getLogin() {
	        return login;
	    }

	    public void setLogin(String login) {
	        this.login = login;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getImageUrl() {
	        return imageUrl;
	    }

	    public boolean isActivated() {
	        return activated;
	    }

	    public String getLangKey() {
	        return langKey;
	    }

	    public String getCreatedBy() {
	        return createdBy;
	    }


	    public List<String> getAuthorities() {
	        return authorities;
	    }

	    @Override
	    public String toString() {
	        return "UserDTO{" +
	            "login='" + login + '\'' +
	            ", firstName='" + firstName + '\'' +
	            ", lastName='" + lastName + '\'' +
	            ", email='" + email + '\'' +
	            ", imageUrl='" + imageUrl + '\'' +
	            ", activated=" + activated +
	            ", langKey='" + langKey + '\'' +
	            ", createdBy=" + createdBy +
	            ", authorities=" + authorities +
	            "}";
	    }

}
