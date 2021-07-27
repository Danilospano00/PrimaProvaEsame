package org.esame.services.interfaces;


import org.esame.models.User;
import org.esame.models.dtos.UserDTO;
import org.esame.exceptions.ErroreUtenteGiaEsistenteException;
import org.esame.exceptions.ErroreUtenteInesistenteException;

import java.util.List;

public interface IUserService {
    void registraUtente(UserDTO nuovoUtente)throws ErroreUtenteGiaEsistenteException;
    User cercaUtentePerEmail(String email)throws ErroreUtenteInesistenteException;
    User userLogin(String username, String password);
    List<User> getAllRegisteredUsers();
}
