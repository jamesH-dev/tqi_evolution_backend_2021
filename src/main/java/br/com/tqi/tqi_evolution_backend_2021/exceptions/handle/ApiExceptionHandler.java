package br.com.tqi.tqi_evolution_backend_2021.exceptions.handle;

import br.com.tqi.tqi_evolution_backend_2021.exceptions.AlreadyExistsException;
import br.com.tqi.tqi_evolution_backend_2021.exceptions.ClientNotFoundException;
import br.com.tqi.tqi_evolution_backend_2021.exceptions.LoanDateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFoundException(
            ClientNotFoundException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(LoanDateException.class)
    public ResponseEntity<String> handleLoanDateException(
            LoanDateException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(
            AlreadyExistsException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
