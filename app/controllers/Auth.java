package controllers;

import models.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;
import sun.net.www.protocol.http.AuthCache;
import constants.Constants;
import constants.Pages;

public class Auth extends Basic {
	

    public static void index() {
        render(Pages.LOGIN);
    }
    
    public static void checkLogin(User user) {
    	User result = User.login(user);
    	if(result != null){
    		Logger.info("Login successfully");
    		session.put(Constants.CURRENT_USERID, user.id);
			session.put(Constants.CURRENT_USERNAME, user.username);
			session.put(Constants.CURRENT_USER_REALNAME, user.realname);
			Cache.set(session.getId(), user);
			redirect("/home");
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