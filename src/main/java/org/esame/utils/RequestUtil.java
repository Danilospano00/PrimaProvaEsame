package org.esame.utils;

import org.esame.models.dtos.UserDTO;
import spark.Request;

public class RequestUtil {

    public static UserDTO getUserDTOFromRequest(Request request) {
        UserDTO result = new UserDTO();
        result.setFirstName(request.queryParams("firstname"));
        result.setLastName(request.queryParams("lastname"));
        result.setUsername(request.queryParams("username"));
        result.setEmail(request.queryParams("email"));
        result.setPassword(request.queryParams("password"));
        return result;
    }

    public static String getQueryUsername(Request request) {
        return request.queryParams("username");
    }

    public static String getQueryPassword(Request request) {
        return request.queryParams("password");
    }

    public static String getQueryLoginRedirect(Request request) {
        return request.queryParams("loginRedirect");
    }

    public static String getSessionLocale(Request request) {
        return request.session().attribute("locale");
    }


    public static String getSessionJwtToken(Request request) {
        if (request.session().attribute("jwtToken")!=null)
            return request.session().attribute("jwtToken");
        else
            return null;
    }

    public static String getSessionCurrentSFInstanceUrl(Request request) {
        if (request.session().attribute("currentSFInstUrl")!=null)
            return request.session().attribute("currentSFInstUrl").toString();
        return null;
    }

    public static String getSessionCurrentSFAccessToken(Request request) {
        if (request.session().attribute("currentSFAC")!=null)
            return request.session().attribute("currentSFAC").toString();
        return null;
    }

    public static void removeSessionSFConnection(Request request) {
        request.session().removeAttribute("currentSFAC");
        request.session().removeAttribute("currentSFInstUrl");
        request.session().removeAttribute("jwtToken");
    }

    public static boolean removeSessionAttrLoggedOut(Request request) {
        Object loggedOut = request.session().attribute("loggedOut");
        request.session().removeAttribute("loggedOut");
        return loggedOut != null;
    }

    public static String removeSessionAttrLoginRedirect(Request request) {
        String loginRedirect = request.session().attribute("loginRedirect");
        request.session().removeAttribute("loginRedirect");
        return loginRedirect;
    }
}
