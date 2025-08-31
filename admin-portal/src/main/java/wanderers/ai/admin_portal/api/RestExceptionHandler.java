package wanderers.ai.admin_portal.api;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice(assignableTypes = QuestApiController.class)
public class RestExceptionHandler {

    public record ApiError(String code, String message) {}

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFound(IllegalArgumentException ex) {
        return new ApiError("NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError validation(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException manv &&
                !manv.getBindingResult().getFieldErrors().isEmpty()) {
            var fe = manv.getBindingResult().getFieldErrors().get(0);
            return new ApiError("VALIDATION_ERROR", fe.getField() + " " + fe.getDefaultMessage());
        }
        return new ApiError("VALIDATION_ERROR", "Validation error");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError generic(Exception ex) {
        return new ApiError("INTERNAL_ERROR", "Something went wrong");
    }
}
