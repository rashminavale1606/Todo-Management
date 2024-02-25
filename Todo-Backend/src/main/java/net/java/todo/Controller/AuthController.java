package net.java.todo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.java.todo.Dto.JwtAuthResponse;
import net.java.todo.Dto.LoginDto;
import net.java.todo.Dto.RegisterDto;
import net.java.todo.Service.AuthService;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {


	private AuthService authService;
	
	//build register rest api
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody  RegisterDto registerDto){
		
		String response = authService.register(registerDto);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
				
	}
	
	
	//build login rest api
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
		
		JwtAuthResponse jwtAuthResponse=authService.login(loginDto);
		
//		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
//		
//		jwtAuthResponse.setAccessToken(token);
		
		return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
	}
	
}
