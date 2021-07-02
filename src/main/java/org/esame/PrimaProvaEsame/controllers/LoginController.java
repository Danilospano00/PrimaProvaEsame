package org.esame.PrimaProvaEsame.controllers;


import org.esame.PrimaProvaEsame.utils.Path;
import org.esame.PrimaProvaEsame.utils.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static org.esame.PrimaProvaEsame.utils.RequestUtil.removeSessionAttrLoggedOut;
import static org.esame.PrimaProvaEsame.utils.RequestUtil.removeSessionAttrLoginRedirect;


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
}
