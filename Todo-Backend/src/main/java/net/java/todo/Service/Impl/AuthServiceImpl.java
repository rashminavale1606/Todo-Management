package net.java.todo.Service.Impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import net.java.todo.Dto.JwtAuthResponse;
import net.java.todo.Dto.LoginDto;
import net.java.todo.Dto.RegisterDto;
import net.java.todo.Exception.TodoAPIException;
import net.java.todo.Repository.RoleRepository;
import net.java.todo.Repository.UserRepository;
import net.java.todo.Security.JwtTokenProvider;
import net.java.todo.Service.AuthService;
import net.java.todo.entity.Role;
import net.java.todo.entity.User;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public String register(RegisterDto registerDto) {
		// TODO Auto-generated method stub
		
		//chevk if username is already exist in db
		
		if(userRepository.existsByUsername(registerDto.getUsername())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST,"username already exist");
		}
		
		//check if email is already exist
		
		if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "email is already exist");
		}
		
		User user = new User();
		
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		
		Role userRole = roleRepository.findByName("ROLE_USER");
		
		roles.add(userRole);
		
		user.setRoles(roles);
		userRepository.save(user);
		
		return "user is successfully registered!";
	}

	@Override
	public JwtAuthResponse login(LoginDto loginDto) {
		// TODO Auto-generated method stub
	
			Authentication authentication=	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						loginDto.getUsernameOrEmail(), 
						loginDto.getPassword()
						));
//	Authentication authentication	= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
//				(loginDto.getUsernameOrEmail(), 
//						loginDto.getPassword()));
		
	
	SecurityContextHolder.getContext().setAuthentication(authentication);
	
	String token= jwtTokenProvider.generateToken(authentication);
	
	
	Optional<User> userOptional = userRepository.findByUsernameOrEmail(
			loginDto.getUsernameOrEmail(),
			loginDto.getUsernameOrEmail());
	
	String role=null;
	
	if(userOptional.isPresent()) {
		
		User loggedInUser = userOptional.get();
		
		Optional<Role> roleOptional = loggedInUser.getRoles().stream().findFirst();
		
		if(roleOptional.isPresent()) {
			
			Role userRole= roleOptional.get();
			
			role=userRole.getName();
		}
	}
	
	JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
	
	jwtAuthResponse.setRole(role);
	
	jwtAuthResponse.setAccessToken(token);
	
			return jwtAuthResponse;
	}

}
