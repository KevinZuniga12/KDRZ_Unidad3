package mx.edu.utez.unidad3.security;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import mx.edu.utez.unidad3.utils.APIResponse;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerValidationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse>  handleException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());

        });
        APIResponse response = new APIResponse("Favor de revisar la informacion",errors,true,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response,response.getStatus());

    }
}