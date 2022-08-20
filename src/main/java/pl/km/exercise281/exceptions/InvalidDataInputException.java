package pl.km.exercise281.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "request with invaid data ")
public class InvalidDataInputException extends RuntimeException {
    public InvalidDataInputException(String message) {
        super(message);
    }
}
