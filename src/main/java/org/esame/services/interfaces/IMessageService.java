package org.esame.services.interfaces;

import java.util.List;

import org.esame.models.Message;
import org.esame.models.User;

public interface IMessageService{
    List<Message> getAllMessages();
    List<Message> getAllUserMessages(User owner);
    void nuovoMessage(User proprietario, String testo);
    Message findPostById(String id);
    void eliminaMessage(User utenteProprietario, Message message);
}
