package controllers;

import play.mvc.Controller;
import constants.Pages;

public class Application extends Controller {

	public static void index() {
		render(Pages.LOGIN);
	}

}