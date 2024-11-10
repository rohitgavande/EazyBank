package com.eazybank.Accounts.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eazybank.Accounts.Dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(
			Exception exception, WebRequest webRequest) {

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	Map<String, String> errorMap = new HashMap<>();
	
	List<ObjectError> errors = ex.getBindingResult().getAllErrors();
	
	errors.stream().forEach((err) -> 
	{
		String field = ((FieldError)err).getField();
		String validationMsg = err.getDefaultMessage();
		errorMap.put(field, validationMsg);
	});
		return new ResponseEntity<>(errorMap , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExsitsException(
			CustomerAlreadyExistsException exception, WebRequest webRequest) {

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
				HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExsitsException(
			ResourceNotFoundException exception, WebRequest webRequest) {

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
				HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}
}
