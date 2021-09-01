package org.esame.services.interfaces;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.esame.models.User;
import org.esame.exceptions.InvalidCredentialsException;
import org.esame.models.Message;
import org.esame.models.dtos.UserDTO;

public interface IPortalService {
    UserDTO login(String email, String password) throws InvalidCredentialsException;
    Map<User, ArrayList<Message>> getUtentiMessage();
    void setUtentiRegistrati(Set<User> utentiRegistrati);
    IUserService getUserService();
    IMessageService getMessageService();
}
