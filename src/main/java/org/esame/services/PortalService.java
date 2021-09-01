package org.esame.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.esame.exceptions.InvalidCredentialsException;
import org.esame.models.Message;
import org.esame.models.User;
import org.esame.models.dtos.UserDTO;
import org.esame.services.interfaces.IMessageService;
import org.esame.services.interfaces.IPortalService;
import org.esame.services.interfaces.IUserService;

public class PortalService implements IPortalService{

    private static PortalService portalService;
    private Set<User> utentiRegistrati;
    private Map<User, ArrayList<Message>> utentiMessage;

    private IUserService userService;
    private IMessageService messageService;
    
    private PortalService() {
        this.utentiRegistrati = new HashSet<>();
        this.utentiMessage = new HashMap<>();
    }

    public static PortalService getInstance() {
        if (portalService==null) {
            portalService = new PortalService();
            portalService.userService = UserService.getInstance();
            portalService.messageService = MessageService.getInstance();
        }
        return portalService;
    }

    @Override
    public UserDTO login(String email, String password) throws InvalidCredentialsException {
        if (email == null | password == null)
        throw new InvalidCredentialsException();
        
                // chiedo a UserService di effettuare il login


        User user = userService.userLogin(email, password);
 /*
         * cerca l'utente che si vuole loggare nel DB, se non lo trova viene lanciata
         * un'eccezione dal metodo getUserByUsernameAndPassowrd che viene catturata e
         * gestita
         */
        if (user == null)
        throw new InvalidCredentialsException();

    /*
     * se tutto è andato bene, quindi l'utente esiste nel DB e c'è la corrispondenza
     * con la password, allora produco il suo codice hash
     */
    return new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
    }

    public Set<User> getUtentiRegistrati() {
        return utentiRegistrati;
    }

    public void setUtentiRegistrati(Set<User> utentiRegistrati) {
        this.utentiRegistrati = utentiRegistrati;
    }

    public IMessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }

    public Map<User, ArrayList<Message>> getUtentiMessage() {
        return utentiMessage;
    }

    public void setUtentiMessage(Map<User, ArrayList<Message>> utentiMessage) {
        this.utentiMessage = utentiMessage;
    }


    @Override
    public IUserService getUserService() {
        return userService;
    }

    
}
