package org.esame.models.dtos;

public class MessageDTO {
    

  
    private String testo;
    private UserDTO autore;
    

    
    public MessageDTO() {
    }

    public MessageDTO(String testo, UserDTO autore) {
        this.testo = testo;
        this.autore = autore;
    }
    
    public String getTesto() {
        return testo;
    }
    public void setTesto(String testo) {
        this.testo = testo;
    }
    public UserDTO getAutore() {
        return autore;
    }
    public void setAutore(UserDTO autore) {
        this.autore = autore;
    }

    
}
