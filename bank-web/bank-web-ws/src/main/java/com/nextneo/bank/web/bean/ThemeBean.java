package com.nextneo.bank.web.bean;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;

@Controller
@SessionScoped
public class ThemeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String theme = "bootstrap";

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String[] getThemes() {
		return new String[] { "afterdark", "afternoon", "afterwork", "aristo",
				"black-tie", "blitzer", "bluesky", "bootstrap", "casablanca",
				"cupertino", "cruze", "dark-hive", "delta", "dot-luv",
				"eggplant", "excite-bike", "flick", "glass-x", "home",
				"hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
				"overcast", "pepper-grinder", "redmond", "rocket", "sam",
				"smoothness", "south-street", "start", "sunny", "swanky-purse",
				"trontastic", "ui-darkness", "ui-lightness", "vader" };
	}

}
