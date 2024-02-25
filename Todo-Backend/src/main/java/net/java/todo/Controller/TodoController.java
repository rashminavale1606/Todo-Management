package net.java.todo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.java.todo.Dto.TodoDto;
import net.java.todo.Service.TodoService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor

public class TodoController {

	// create instance variable
	private TodoService todoService;

	// BUILD ADD TODO REST API

	
	@PreAuthorize("hasRole('ADMIN')")

	@PostMapping
	
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

		TodoDto saveTodo = todoService.addTodoDto(todoDto);

		return new ResponseEntity<>(saveTodo, HttpStatus.CREATED);
	}

	// GET REST API
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("{id}")
	

	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") long todoId) {

		TodoDto todoDto = todoService.getTodoDto(todoId);
		return new ResponseEntity<>(todoDto, HttpStatus.OK);

	}

	// GET ALL
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	

	public ResponseEntity<List<TodoDto>> getAllTodos() {

		List<TodoDto> todos = todoService.getAllTodos();

		return ResponseEntity.ok(todos);
		// returns list of todos along with http status
	}

	// Update
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	

	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") long todoId) {

		TodoDto updateTodo = todoService.updateTodo(todoDto, todoId);

		return ResponseEntity.ok(updateTodo);

	}

//	/Delete
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	

	public ResponseEntity<String> deleteTodo( @PathVariable("id") long todoId){
		
		todoService.deleteTodo(todoId);
		return ResponseEntity.ok("Todo deleted successfully");
	}
	
	// Complete todo 
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/complete")
	

	public ResponseEntity<TodoDto> completeTodo( @PathVariable("id") long id){
		
		TodoDto updatedTodo=todoService.completeTodo(id);
		
		return ResponseEntity.ok(updatedTodo);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/in-complete")
	

	public ResponseEntity<TodoDto> incompleteTodo( @PathVariable("id") long id){
		
		TodoDto updatedTodo=todoService.inCompleteTodo(id);
		
		return ResponseEntity.ok(updatedTodo);
	}
}
