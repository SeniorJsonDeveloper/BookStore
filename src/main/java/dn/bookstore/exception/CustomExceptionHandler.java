package dn.bookstore.exception;

import dn.bookstore.entity.BookEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<BookEntity>> handleAlreadyExistsException(AlreadyExistsException e) {
        return new ResponseEntity<>(new ApiResponseDto<>(HttpStatus.BAD_REQUEST,
                "BAD PARAMETER! TRY AGAIN!",e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<BookEntity>> handleException(Exception e) {
        return new ResponseEntity<>(new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL SERVER ERROR",e),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
