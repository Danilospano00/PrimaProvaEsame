package org.esame;


import org.esame.controllers.IndexController;
import org.esame.controllers.LoginController;
import org.esame.controllers.UserController;
import org.esame.utils.Path;
import org.esame.utils.ViewUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

/**
 * Questa classe esegue il bootstrap di SparkJava.
 * Vengono dichiarate tutte le rotte e le relative classi Controller associate.
 *
 */
public class Application {
    public static String appVersion = "";
    public static final Integer DEFAULT_PORT = 4567;

    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        appConfig();

        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen();

        get(Path.Web.BASE_INDEX,            LoginController.serveLoginPage);
        get(Path.Web.LOGIN,                 LoginController.serveLoginPage);
        get(Path.Web.INDEX,                 IndexController.serveIndexPage);

        get("/register/",              UserController.register);
        post("/register/",             UserController.handleRegisterPost);

        get("*",                       ViewUtil.notFound);
    }


    /**
     * Defininsce la configurazione per:
     */
    private static void appConfig() {


    }
}
