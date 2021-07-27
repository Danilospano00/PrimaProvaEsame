package org.esame.models.dtos;

import java.util.List;

public class ErrorMessageDTO {
    private String message;
    private String target;
    private List<StackTraceElement> stackTraceElementList;

    public ErrorMessageDTO() {

    }

    public ErrorMessageDTO(String message, String target) {
        this.message = message;
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<StackTraceElement> getStackTraceElementList() {
        return stackTraceElementList;
    }

    public void setStackTraceElementList(List<StackTraceElement> stackTraceElementList) {
        this.stackTraceElementList = stackTraceElementList;
    }
}
