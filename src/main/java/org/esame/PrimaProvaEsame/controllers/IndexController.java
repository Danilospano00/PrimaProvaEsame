package org.esame.PrimaProvaEsame.controllers;


import org.esame.PrimaProvaEsame.utils.Path;
import org.esame.PrimaProvaEsame.utils.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class IndexController {


    public static Route serveIndexPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        ensureUserIsLoggedIn(request, response);
        return ViewUtil.render(request, model, Path.Template.INDEX);
    };

    public static void ensureUserIsLoggedIn(Request request, Response response) {
        if (request.session().attribute("currentUser") == null) {
            request.session().attribute("loginRedirect", request.pathInfo());
            response.redirect(Path.Web.LOGIN);
        }
    };
}
