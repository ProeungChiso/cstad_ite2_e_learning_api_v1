package co.istad.course_elearning_homework1.exception;

import co.istad.course_elearning_homework1.base.BasedError;
import co.istad.course_elearning_homework1.base.BasedErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ServiceException {
    @ExceptionHandler(ResponseStatusException.class)

    ResponseEntity<?> handleServerException(ResponseStatusException ex) {
        BasedError<String> basedError = new BasedError<>();
        basedError.setCode(ex.getStatusCode().toString());
        basedError.setDescription(ex.getReason());

        BasedErrorResponse basedErrorResponse = new BasedErrorResponse();
        basedErrorResponse.setError(basedError);

        return ResponseEntity.status(ex.getStatusCode())
                .body(basedErrorResponse);
    }
}
