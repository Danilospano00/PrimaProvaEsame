package org.esame.models;

public class Message {

    private String testo;
    private User autore;
    private String id;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Message() {
    }
    public Message(String testo, User autore) {
        this.testo = testo;
        this.autore = autore;
    }
    public String getTesto() {
        return testo;
    }
    public void setTesto(String testo) {
        this.testo = testo;
    }
    public User getAutore() {
        return autore;
    }
    public void setAutore(User autore) {
        this.autore = autore;
    }

    

    
}
