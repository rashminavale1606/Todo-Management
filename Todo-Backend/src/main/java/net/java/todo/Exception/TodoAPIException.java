package net.java.todo.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter

public class TodoAPIException extends RuntimeException{
	
	private HttpStatus status;
	private String message;

}
