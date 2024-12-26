package org.aguzman.apiservlet.webapp.headers.services;

public class ServiceJdbcException extends RuntimeException {

    public ServiceJdbcException(Throwable cause) {
        super(cause);
    }

    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
