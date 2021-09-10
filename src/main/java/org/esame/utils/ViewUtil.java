package org.esame.utils;

import org.apache.velocity.app.VelocityEngine;
import org.eclipse.jetty.http.HttpStatus;
import org.esame.Application;
import org.esame.models.dtos.UserDTO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class ViewUtil {

    public static String render(Request request, Map<String, Object> model, String templatePath) {
        model.put("msg", new MessageBundle(RequestUtil.getSessionLocale(request)));
        model.put("currentUser", getSessionCurrentUser(request));
        model.put("Utils", CustomUtils.Utils.class);
        model.put("WebPath", Path.Web.class); // Access application URLs from templates
        model.put("appVersion", Application.appVersion);
        return strictVelocityEngine().render(new ModelAndView(model, templatePath));
    }


    public static Route notFound = (Request request, Response response) -> {
        response.status(HttpStatus.NOT_FOUND_404);
        return render(request, new HashMap<>(), Path.Template.NOT_FOUND);
    };

    private static VelocityTemplateEngine strictVelocityEngine() {
        VelocityEngine configuredEngine = new VelocityEngine();
        configuredEngine.setProperty("runtime.references.strict", true);
        configuredEngine.setProperty("resource.loader", "class");
        configuredEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return new VelocityTemplateEngine(configuredEngine);
    }

    public static UserDTO getSessionCurrentUser(Request request) {
        return request.session().attribute("currentUser");
    }
}
