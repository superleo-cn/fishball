package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Customer;
import models.Customer;
import utils.Pagination;

import com.avaje.ebean.annotation.Transactional;

import constants.Pages;

public class Customers extends Basic {

	public static void index() {
		render(Pages.CUSTOMER_LIST);
	}

	public static void list(String queryName, Pagination pagination) {
		pagination = Customer.search(queryName, pagination);
		renderJSON(pagination);
	}

	public static void view(Integer id) {
		Customer customer = Customer.view(id);
		render(Pages.CUSTOMER_FORM, customer);
	}
	
	@Transactional
	public static void store(Customer customer) {
		Customer.store(customer);
		Map data = new HashMap();
		data.put("messages", "Store Customer Successfully.");
		renderJSON(data);
	}

	@Transactional
	public static void delete(Integer id) {
		Map data = new HashMap();
		if (Customer.delete(id)) {
			data.put("messages", "Delete Customer Successfully.");
		} else {
			data.put("messages", "Delete Customer failed.");
		}
		renderJSON(data);
	}

}