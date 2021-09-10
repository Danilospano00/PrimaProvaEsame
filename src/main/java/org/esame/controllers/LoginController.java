package org.esame.controllers;


import org.esame.models.dtos.UserDTO;
import org.esame.services.PortalService;
import org.esame.services.interfaces.IPortalService;
import org.esame.utils.Path;
import org.esame.utils.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static org.esame.utils.RequestUtil.removeSessionAttrLoggedOut;
import static org.esame.utils.RequestUtil.removeSessionAttrLoginRedirect;


public class LoginController {

    /**
     * serveLoginPage
     * Mostra la home page.
     */
    public static Route serveLoginPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("loggedOut", removeSessionAttrLoggedOut(request));
        model.put("loginRedirect", removeSessionAttrLoginRedirect(request));
        return ViewUtil.render(request, model, Path.Template.LOGIN);
    };


    /**
     * handleLoginPost - POST
     * Esegue il processo di verifica dei dati dalla login page.
     * Esegue l'aggiunta dell'utente corrente nella sessione.
     * Esegue il redirect sulla home page se non vi sono errori.
     */
    public static Route handleLoginPost = (Request request, Response response) -> {
        IPortalService portalService = PortalService.getInstance();
        UserDTO userDTO = portalService.login(request.queryParams("username"), request.queryParams("password"));
        if (userDTO!=null)
            request.session().attribute("currentUser", userDTO);
        Map<String, Object> model = new HashMap<>();

        return ViewUtil.render(request, model, Path.Template.INDEX);
    };


    /**
     * handleLogoutPost - POST
     * Esegue il logout rimuovendo dalla sessione l'utente corrente
     * Esegue il redirect sulla login page.
     */
    public static Route handleLogoutPost = (Request request, Response response) -> {
        request.session().removeAttribute("currentUser");
        request.session().attribute("loggedOut", true);
        response.redirect(Path.Web.LOGIN);
        return null;
    };

    public static void ensureUserIsLoggedIn(Request request, Response response){
        if (request.session().attribute("currentUser") == null) {
            request.session().attribute("loginRedirect", request.pathInfo());
            response.redirect(Path.Web.LOGIN);
        }
    };
}
