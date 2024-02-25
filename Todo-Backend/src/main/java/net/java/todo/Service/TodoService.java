package net.java.todo.Service;

import java.util.List;

import net.java.todo.Dto.TodoDto;

public interface TodoService {

	TodoDto addTodoDto(TodoDto todoDto);
	
	TodoDto getTodoDto(long id);
	
	List<TodoDto> getAllTodos();
	
	TodoDto updateTodo(TodoDto todoDto,long id);
	
	void deleteTodo(long id);
	
	TodoDto completeTodo(long id);
	
	TodoDto inCompleteTodo(long id);
}
