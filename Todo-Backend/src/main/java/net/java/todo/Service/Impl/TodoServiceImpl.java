package net.java.todo.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.authenticator.SavedRequest;
import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.java.todo.Dto.TodoDto;
import net.java.todo.Exception.ResourceNotFoundException;
import net.java.todo.Repository.TodoRepository;
import net.java.todo.Service.TodoService;
import net.java.todo.entity.Todo;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepository;

	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodoDto(TodoDto todoDto) {
		// TODO Auto-generated method stub

//	//convert todoDto into Todo Jpa Entity
//		Todo todo = new Todo();
//		
//		//objectof todo entity class
//		todo.setTitle(todoDto.getTitle());
//		todo.setDescription(todoDto.getDescription());
//		todo.setCompleted(todoDto.isCompleted());
//		
//		//save todo entity object into db

		// convert savedTodo jpa entity obj into /todo Object

		// ******************************************************

		// TodoDto into todo jpa entity

		Todo todo = modelMapper.map(todoDto, Todo.class);

//		Todo Jpa entity
		Todo saveTodo = todoRepository.save(todo);

		TodoDto saveTodoDto = modelMapper.map(saveTodo, TodoDto.class);

		return saveTodoDto;

//		TodoDto saveTodoDto = new TodoDto();
//		
//		saveTodoDto.setId(saveTodo.getId());
//		saveTodoDto.setTitle(saveTodo.getTitle());
//		saveTodoDto.setDescription(saveTodo.getDescription());
//		saveTodoDto.setCompleted(saveTodo.isCompleted());

		// convert save todo jpa entity obj into todoDto obj

	}

	@Override
	public TodoDto getTodoDto(long id) {
		// TODO Auto-generated method stub
		
//		Todo todo = todoRepository.findById(id).get();
		Todo todo = todoRepository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Todo not found with id: "+id));

		
		return modelMapper.map(todo, TodoDto.class);
	

	}

	@Override
	public List<TodoDto> getAllTodos() {
		// TODO Auto-generated method stub
		List<Todo> todos = todoRepository.findAll();
	
		
		return todos.stream().map((todo) -> modelMapper
				.map(todo, TodoDto.class)).collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, long id) {
		// TODO Auto-generated method stub
		
		Todo todo = todoRepository.findById(id).orElseThrow(()->new 
				ResourceNotFoundException("Todo not found with id: "+id));
		
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		
		Todo updateTodo=todoRepository.save(todo);
		return modelMapper.map(updateTodo, TodoDto.class);
	}

	@Override
	public void deleteTodo(long id) {
		// TODO Auto-generated method stub
		
		Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo "
				+ "not found with given id: "+id));
		
		todoRepository.deleteById(id);
	}

	@Override
	public TodoDto completeTodo(long id) {
		// TODO Auto-generated method stub
		Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo"
				+ " not found with given id: "+id));
		todo.setCompleted(Boolean.TRUE);
		
		Todo updatedTodo=todoRepository.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(long id) {
		// TODO Auto-generated method stub
		Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo"
				+ " not found with given id: "+id));
		todo.setCompleted(Boolean.FALSE);
		
		Todo updatedTodo=todoRepository.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

}
