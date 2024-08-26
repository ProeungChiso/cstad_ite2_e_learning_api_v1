package co.istad.course_elearning_homework1.exception;

import co.istad.course_elearning_homework1.base.BasedError;
import co.istad.course_elearning_homework1.base.BasedErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BasedErrorResponse handleValidationException(MethodArgumentNotValidException ex) {

        BasedError<List<?>> basedError = new BasedError<>();
        List<Map<String, Object>> errors = new ArrayList<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            Map<String, Object> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("message", fieldError.getDefaultMessage());
            errors.add(error);
        }

        basedError.setCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
        basedError.setDescription(errors);

        return new BasedErrorResponse(basedError);
    }
}
