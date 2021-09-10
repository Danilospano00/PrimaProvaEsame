package org.esame.models.dtos;


public class SimpleMessageDTO {
    private String message;
    private String target;

    public SimpleMessageDTO() {

    }

    public SimpleMessageDTO(String message, String target) {
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
}
