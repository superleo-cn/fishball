package controllers;

import com.avaje.ebean.annotation.Transactional;
import constants.Pages;
import models.Employee;
import utils.Pagination;

import java.util.HashMap;
import java.util.Map;

public class Employees extends Basic {

	public static void index() {
		render(Pages.EMPLOYEE_LIST);
	}

    public static void add() {
        render(Pages.EMPLOYEE_FORM);
    }

	public static void list(String queryName, Pagination pagination) {
		pagination = Employee.search(queryName, pagination);
		renderJSON(pagination);
	}

	public static void view(Integer id) {
		Employee employee = Employee.view(id);
		render(Pages.EMPLOYEE_FORM, employee);
	}
	
	@Transactional
	public static void store(Employee employee) {
		Employee.store(employee);
		Map data = new HashMap();
		data.put("messages", "Store Employee Successfully.");
		renderJSON(data);
	}

	@Transactional
	public static void delete(Integer id) {
		Map data = new HashMap();
		if (Employee.delete(id)) {
			data.put("messages", "Delete Employee Successfully.");
		} else {
			data.put("messages", "Delete Employee failed.");
		}
		renderJSON(data);
	}

}