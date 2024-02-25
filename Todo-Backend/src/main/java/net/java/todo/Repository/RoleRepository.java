package net.java.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.java.todo.entity.Role;
import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role  findByName(String name);

}
