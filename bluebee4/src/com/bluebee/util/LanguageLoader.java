package com.bluebee.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LanguageLoader {
	private static ResourceBundle languageBundle;
	private static Locale languageSelected = new Locale("zh", "CN");

	private static final ClassLoader WD_CLASS_LOADER = (ClassLoader) AccessController
			.doPrivileged(new PrivilegedAction() {
				public ClassLoader run() {
					return new ClassLoader() {
						public URL getResource(String name) {
							try {
								URL result = super.getResource(name);
								if (result != null) {
									return result;
								}
								return new File(name).toURI().toURL();
							} catch (MalformedURLException ex) {
							}
							return null;
						}
					};
				}
			});

	public static void setLanguage(Locale locale) {
		languageSelected = new Locale("zh", "CN");
	}

	public static Locale getLanguageSelected() {
		return languageSelected;
	}

	public static String getString(String key) {
		String result;
		if (languageBundle != null) {
			try {
				result = languageBundle.getString(key);
			} catch (MissingResourceException e) {
				return key;
			}
			return result;
		}
		return key;
	}
}