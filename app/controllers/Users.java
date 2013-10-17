package controllers;

import models.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;
import sun.net.www.protocol.http.AuthCache;
import utils.PaginationList;
import constants.Constants;
import constants.Pages;

public class Users extends Basic {
	
	public static void index() {
		render(Pages.USER_LIST);
	}
	
	public static void list(String queryName, PaginationList paginationList) {
		paginationList = User.search(queryName, paginationList);
		renderJSON(paginationList);
	}
   
}