package br.edu.ufpel.mapacultural.Config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<Map<String, String>> tratarEmailDuplicad( EmailJaCadastradoException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body( Map.of("erro", ex.getMessage()) );
    }
}
