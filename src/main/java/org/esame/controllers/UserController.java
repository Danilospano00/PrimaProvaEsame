package org.esame.controllers;

import org.esame.exceptions.ErroreUtenteGiaEsistenteException;
import org.esame.models.User;
import org.esame.models.dtos.ErrorMessageDTO;
import org.esame.models.dtos.UserDTO;
import org.esame.utils.Path;
import org.esame.utils.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.esame.utils.RequestUtil.getUserDTOFromRequest;


public class UserController {

    /**
     * register - GET
     * mostra a video la form di registrazione utente.
     * Rimuove l'utente dalla sessione per evitare conflitti.
     */
    public static Route register = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("userDto", new UserDTO());
        return ViewUtil.render(request, model, Path.Template.REGISTER);
    };


    /**
     * handleRegisterPost - POST
     * Esegue il processo di registrazione dell'utente.
     * Collabora con UserService.
     */
    public static Route handleRegisterPost = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        ErrorMessageDTO errorMessageDTO;
        UserDTO userDTO = getUserDTOFromRequest(request);
        model.put("userDto", userDTO);
        try {
            //portalService.getUserService().registraUtente(userDTO);
            model.put("user", userDTO);
            throw new ErroreUtenteGiaEsistenteException();
        } catch(ErroreUtenteGiaEsistenteException e) {
            errorMessageDTO = new ErrorMessageDTO("Utente", "utente");
            model.put("errorMessage", errorMessageDTO);
            return ViewUtil.render(request, model, Path.Template.REGISTER);
        }

        //return LoginController.serveLoginPage.handle(request, response);
    };


    public static Route handleGetAllUsersGET = (Request request, Response response) -> {
        List<UserDTO> result = new ArrayList<>();
        /*
        List<User> users = portalService.getUserService().getAllRegisteredUsers();
        for(User user: users) {
            result.add(UserTransformer.toDTO(user, false));
        }
         */
        return result;
    };

}
