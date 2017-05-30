package com.fikt.seminarski.views;

import io.dropwizard.views.View;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorView extends View {
    private final Exception exception;
    private final String stackTrace;

    public ErrorView(Exception e, String template) {
        super(template);
        this.exception = e;

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        exception.printStackTrace(pw);

        this.stackTrace = sw.getBuffer().toString();
    }

    public Exception getException() {
        return exception;
    }
    
    public String getExceptionMessage() {
        return exception.getMessage();
    }

    public String getStackTrace() {
        return stackTrace;
    }
}