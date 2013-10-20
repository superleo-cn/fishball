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

	public static void checkLogin(User user) {
		User result = User.login(user);
		if (result != null) {
			Logger.info("Login successfully");
			session.put(Constants.CURRENT_USERID, user.id);
			session.put(Constants.CURRENT_USERNAME, user.username);
			session.put(Constants.CURRENT_USER_REALNAME, user.realname);
			Cache.set(session.getId(), user);
			render(Pages.HOME);
		}
		Logger.info("Login unsuccesfully");
		render(Pages.LOGIN);
	}

	public static void logout() {
		Cache.delete(session.getId());
		session.clear();
		index();
	}
}