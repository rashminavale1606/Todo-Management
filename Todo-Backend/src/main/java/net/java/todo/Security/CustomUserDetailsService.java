package net.java.todo.Security;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//import org.springframework.data.jpa.repository.query.EqlParser.New_valueContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.java.todo.Repository.UserRepository;
import net.java.todo.entity.User;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(()-> new UsernameNotFoundException(" username not exist by"
						+ " username or email"));
		
		Set<GrantedAuthority> authorities= user.getRoles().stream()
				.map((role)-> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toSet());
		
		return new org.springframework.security.core.
				userdetails.User(usernameOrEmail, user.getPassword(), authorities); 
	}

}
