package se.nithya.poller.management.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.management.RuntimeOperationsException;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    // 404
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
                                                                   final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.name(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());

        return new ResponseEntity<>(apiError, new HttpHeaders(), status);
    }

    // 500
    @ExceptionHandler({RuntimeOperationsException.class})
    public ResponseEntity<Object> handleRuntimeOperationsException(
            final RuntimeOperationsException ex, final WebRequest request) {

        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                getErrorType(ex.getMessage()), getErrorMessage(ex.getCause()));
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({PollerException.class})
    public ResponseEntity<Object> handleSimpleApiException(final PollerException ex,
                                                           final WebRequest request) {

        ApiError apiError = new ApiError(ex.getCode(), ex.getReason(), ex.getMessage());

        return new ResponseEntity<>(apiError, new HttpHeaders(), ex.getStatus());
    }

    private static String getErrorMessage(Throwable cause) {
        return cause == null ? null : cause.getMessage();
    }

    private static String getErrorType(String errorKey) {
        return errorKey != null ? errorKey : "Unknown error type";
    }

}
