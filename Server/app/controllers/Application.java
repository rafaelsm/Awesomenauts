package controllers;

import models.Awesomenaut;
import play.data.Form;
import play.mvc.*;

public class Application extends Controller {

    static Form<Awesomenaut> awesomenautForm = Form.form(Awesomenaut.class);

    public static Result index() {
        return redirect(routes.Application.awesomenauts());
    }

    public static Result awesomenauts() {
        return ok(views.html.index.render(Awesomenaut.all(),awesomenautForm));
    }

    public static Result newAwesomenaut() {

        Form<Awesomenaut> filledForm = awesomenautForm.bindFromRequest();

        if (filledForm.hasErrors()){
            return badRequest(views.html.index.render(Awesomenaut.all(), filledForm));
        }else{
            Awesomenaut.create(filledForm.get());
            return redirect(routes.Application.awesomenauts());
        }

    }

    public static Result deleteAwesomenaut(Long id) {
        Awesomenaut.delete(id);
        return redirect(routes.Application.awesomenauts());
    }
}
