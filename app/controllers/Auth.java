package controllers;

import models.User;
import play.Logger;
import play.cache.Cache;
import play.i18n.Lang;
import constants.Constants;
import constants.Pages;

public class Auth extends Basic {

	public static void index() {
		render(Pages.LOGIN);
	}

	public static void login(User user) {
		User result = User.login(user);
		if (result != null) {
			Logger.info("Login successfully");
			session.put(Constants.CURRENT_USERID, user.id);
			session.put(Constants.CURRENT_USERNAME, user.username);
			session.put(Constants.CURRENT_USER_REALNAME, user.realname);
			Cache.set(session.getId(), user);
			Logger.info(flash.get("errors"));
			Home.index();
		}else{
			Logger.info("Login unsuccesfully");
			flash.put("errors", "login failed.");
			render(Pages.LOGIN);
		}
		
	}

	public static void logout() {
		Cache.delete(session.getId());
		session.remove(Constants.CURRENT_USER_REALNAME);
		session.remove(Constants.CURRENT_USERNAME);
		session.remove(Constants.CURRENT_USERID);
		index();
	}
}