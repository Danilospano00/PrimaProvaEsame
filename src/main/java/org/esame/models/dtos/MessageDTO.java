package org.esame.models.dtos;

public class MessageDTO {
    

    private String testo;

    

    
    public MessageDTO() {
    }

    public MessageDTO(String testo) {
        this.testo = testo;
    }
    
    public String getTesto() {
        return testo;
    }
    public void setTesto(String testo) {
        this.testo = testo;
    }

}
