package net.java.todo.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
		
		System.out.println(passwordEncoder.encode("rashmi"));
		System.out.println(passwordEncoder.encode("admin"));
	}

}
