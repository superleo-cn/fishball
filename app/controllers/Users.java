package controllers;

import java.util.HashMap;
import java.util.Map;

import com.avaje.ebean.annotation.Transactional;

import models.User;
import utils.Pagination;
import constants.Pages;

public class Users extends Basic {

	public static void index() {
		render(Pages.USER_LIST);
	}

	public static void list(String queryName, Pagination pagination) {
		pagination = User.search(queryName, pagination);
		renderJSON(pagination);
	}

	public static void view(Integer id) {
		render(Pages.USER_FORM, User.view(id));
	}
	
	public static void store(User user) {
		Users.store(user);
		index();
	}

	@Transactional
	public static void delete(Integer id) {
		Map data = new HashMap();
		if (User.delete(id)) {
			data.put("messages", "Delete User Successfully.");
		} else {
			data.put("messages", "Delete User failed.");
		}
		renderJSON(data);
	}

}