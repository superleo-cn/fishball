package controllers;

import inteceptors.ConstantsInterceptor;
import inteceptors.Secure;
import inteceptors.TimeInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import play.Logger;
import play.Play;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Catch;
import play.mvc.Controller;
import play.mvc.Finally;
import play.mvc.With;
import constants.Constants;

@With(value = { TimeInterceptor.class, ConstantsInterceptor.class })
public class Basic extends Controller {

	@Before(unless = { "Auth.login", "Auth.logout" })
	static void checkAuthentification() {
		if (StringUtils.isEmpty(session.get(Constants.CURRENT_USERNAME))) {
			//flash.put("error", "You need login first");
			//Auth.index();
		}
		//TODO: refactor
		//Cache.safeSet(Constants.GLOBLE_FACILITIES, Facility.findAll(), "365d");
	}

	@Catch(Exception.class)
	public static void exception(Throwable e) {
		Logger.error("error happend.", e);
	}

	@Finally
	public static void finalExecute() {

	}
	
	public static void uploadFile(File imgFile) {
		File file = Play.getFile("/public/uploads");
		String filename = imgFile.getName();
		Map map = new HashMap();
		try {
			FileUtils.copyFileToDirectory(imgFile, file);
			map.put("error", 0);
			map.put("url", Play.ctxPath + "/public/uploads/" + filename);
		} catch (IOException e) {
			Logger.error("upload file error", e);
			map.put("error", 1);
			map.put("url", Play.ctxPath + "/public/uploads/error.jpg");
		}
		renderJSON(map);
	}

}