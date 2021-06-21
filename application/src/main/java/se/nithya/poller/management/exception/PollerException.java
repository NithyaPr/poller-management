package se.nithya.poller.management.exception;

import org.springframework.http.HttpStatus;

public class PollerException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    private final String reason;

    public PollerException(HttpStatus status, String code, String reason, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
        this.reason = reason;
    }

    public PollerException(HttpStatus status, String code, String reason, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.reason = reason;
    }

    public PollerException(HttpStatus status, String reason, String message) {
        this(status, status.name(), reason, message);
    }

    public PollerException(HttpStatus status, String message) {
        this(status, status.name(), status.getReasonPhrase(), message);
    }

    public PollerException(HttpStatus status, String message, Throwable cause) {
        this(status, status.name(), status.getReasonPhrase(), message, cause);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
}
