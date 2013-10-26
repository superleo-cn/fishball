package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Supplier;
import models.Supplier;
import utils.Pagination;

import com.avaje.ebean.annotation.Transactional;

import constants.Pages;

public class Suppliers extends Basic {

	public static void index() {
		render(Pages.SUPPLIER_LIST);
	}

	public static void list(String queryName, Pagination pagination) {
		pagination = Supplier.search(queryName, pagination);
		renderJSON(pagination);
	}

	public static void view(Integer id) {
		Supplier supplier = Supplier.view(id);
		render(Pages.SUPPLIER_FORM, supplier);
	}
	
	@Transactional
	public static void store(Supplier supplier) {
		Supplier.store(supplier);
		Map data = new HashMap();
		data.put("messages", "Store Supplier Successfully.");
		renderJSON(data);
	}

	@Transactional
	public static void delete(Integer id) {
		Map data = new HashMap();
		if (Supplier.delete(id)) {
			data.put("messages", "Delete Supplier Successfully.");
		} else {
			data.put("messages", "Delete Supplier failed.");
		}
		renderJSON(data);
	}

}