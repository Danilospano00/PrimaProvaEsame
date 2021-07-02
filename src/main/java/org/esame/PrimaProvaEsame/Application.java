package org.esame.PrimaProvaEsame;


import org.esame.PrimaProvaEsame.controllers.IndexController;
import org.esame.PrimaProvaEsame.controllers.LoginController;
import org.esame.PrimaProvaEsame.utils.Path;
import org.esame.PrimaProvaEsame.utils.ViewUtil;
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

        get("*",                       ViewUtil.notFound);
    }


    /**
     * Defininsce la configurazione per:
     */
    private static void appConfig() {


    }
}
