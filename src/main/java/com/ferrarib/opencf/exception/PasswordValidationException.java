package com.ferrarib.opencf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by bruno on 4/3/16.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordValidationException extends RuntimeException {
}
