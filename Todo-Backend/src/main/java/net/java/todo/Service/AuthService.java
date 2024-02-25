package net.java.todo.Service;

import net.java.todo.Dto.JwtAuthResponse;
import net.java.todo.Dto.LoginDto;
import net.java.todo.Dto.RegisterDto;

public interface AuthService {
	
	String register(RegisterDto registerDto);
	
	JwtAuthResponse login(LoginDto loginDto);
}
