package controllers;

import com.avaje.ebean.annotation.Transactional;
import constants.Pages;
import models.Appraisal;
import utils.Pagination;

import java.util.HashMap;
import java.util.Map;

public class Appraisals extends Basic {

	public static void index() {
		render(Pages.APPRAISAL_LIST);
	}

	public static void add() {
        render(Pages.APPRAISAL_FORM);
    }

	public static void list(String queryName, Pagination pagination) {
		pagination = Appraisal.search(queryName, pagination);
		renderJSON(pagination);
	}

	public static void view(Integer id) {
		Appraisal appraisal = Appraisal.view(id);
		render(Pages.APPRAISAL_FORM, appraisal);
	}

	@Transactional
	public static void store(Appraisal appraisal) {
		Appraisal.store(appraisal);
		Map data = new HashMap();
		data.put("messages", "Store Appraisal Successfully.");
		renderJSON(data);
	}

	@Transactional
	public static void delete(Integer id) {
		Map data = new HashMap();
		if (Appraisal.delete(id)) {
			data.put("messages", "Delete Appraisal Successfully.");
		} else {
			data.put("messages", "Delete Appraisal failed.");
		}
		renderJSON(data);
	}

}