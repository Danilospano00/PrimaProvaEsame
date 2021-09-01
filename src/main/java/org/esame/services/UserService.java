package org.esame.services;

import org.esame.models.dtos.UserDTO;
import org.esame.exceptions.ErroreUtenteGiaEsistenteException;
import org.esame.exceptions.ErroreUtenteInesistenteException;
import org.esame.models.User;
import org.esame.services.interfaces.IUserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

/**
 * Compiti: - consentire la registrazione/iscrizione di un utente - consentire
 * la deregistrazione/rimozione di un utente - tornare elenco di tutti gli
 * utenti iscritti - tornare un Utente tramite un indirizzo email - tornare un
 * Utente tramite (Username e Password)
 *
 * 
 * Collabora con : nessuno
 */

// TODO
public class UserService implements IUserService {

    private static UserService userService;
    private static String defaultUserAdminEmail;

    private List<User> listaUtentiRegistrati;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    // TODO
    private UserService() {
        // utente defult per test interfaccia web
        // this.getUtentiRegistrati().add(new User("Remo", "Candeli", "remo", "test",
        // "remo.candeli@gmail.com"));

        //qui devo aggiungere la lista di getUtentiRegistrati
        listaUtentiRegistrati = new ArrayList<>();
    }

    public static String getDefaultUserAdminEmail() {
        return defaultUserAdminEmail;
    }

    public static void setDefaultUserAdminEmail(String defaultUserAdminEmail) {
        UserService.defaultUserAdminEmail = defaultUserAdminEmail;
    }

    // TODO
    public User userLogin(String username, String password) {
        User result = null;
        /*
         * scorro la lista verificando se l'utente che si vuole loggare esiste o meno
         * nel DB
         */
        for (User item : getUtentiRegistrati())
            // se lo trovo lo assegno ad userLogin e aggiorno trovato
            if (item.getUsername().equals(username)) {
                if (BCrypt.checkpw(password, item.getPassword())) {
                    result = item;
                    break;
                }
            }

        return result;
    }

    // TODO
    // registra un utente e lo aggiunge alla lista
    public void registraUtente(UserDTO nuovoUtente) throws ErroreUtenteGiaEsistenteException {
        User userNuovo = new User(nuovoUtente.getFirstName(), nuovoUtente.getLastName(), nuovoUtente.getUsername(),
                nuovoUtente.getPassword(), nuovoUtente.getEmail());
        for (User utente : getUtentiRegistrati()) {
            if (utente.equals(userNuovo))
                throw new ErroreUtenteGiaEsistenteException();
        }
        getUtentiRegistrati().add(userNuovo);
    }

    // TODO
    //annulla la registrazione di un utente e lo rimuove dalla lista
    public void annullaRegistrazioneUtente(UserDTO utenteDaCancellare) throws ErroreUtenteInesistenteException {
        User user = cercaUtentePerEmail(utenteDaCancellare.getEmail());
        getUtentiRegistrati().remove(user);

    }

    // TODO
    //ritorna un utente già inserito nella lista cercando per username e password
    public User cercaUtentePerUsernameEPassword(String username, String password) throws ErroreUtenteInesistenteException {
        for(User user : getUtentiRegistrati()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        throw new ErroreUtenteInesistenteException();
    }

    @Override
    //ritorna un utente già inserito nella lista cercando per email
    public User cercaUtentePerEmail(String email)throws ErroreUtenteInesistenteException {
        for(User user : getUtentiRegistrati()){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        throw new ErroreUtenteInesistenteException();
    }        

    // TODO
    @Override
    public List<User> getUtentiRegistrati() {
        return listaUtentiRegistrati;
    }
}
