package org.esame.PrimaProvaEsame.services.interfaces;


import org.esame.PrimaProvaEsame.exceptions.ErroreUtenteGiaEsistenteException;
import org.esame.PrimaProvaEsame.exceptions.ErroreUtenteInesistenteException;
import org.esame.PrimaProvaEsame.models.User;
import org.esame.PrimaProvaEsame.models.dtos.UserDTO;

import java.util.List;

public interface IUserService {
    void registraUtente(UserDTO nuovoUtente)throws ErroreUtenteGiaEsistenteException;
    User cercaUtentePerUsername(String username)throws ErroreUtenteInesistenteException;
    User userLogin(String username, String password);
    List<User> getAllRegisteredUsers();
}
