/*
 * Copyright 2006 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the Mozilla Public License, Version 1.1, or any later version. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.mozilla.org/MPL/MPL-1.1.txt. The Original Code is the Pentaho 
 * BI Platform.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
 *
 * @created Jul 19, 2005 
 * @author James Dixon
 * 
 */
package org.pentaho.pac.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.pentaho.messages.MessageUtil;
import org.pentaho.messages.util.LocaleHelper;

public class Messages {
  private static final String BUNDLE_NAME = "org.pentaho.pac.server.messages";//$NON-NLS-1$

  private static final Map<Locale,ResourceBundle> locales = Collections.synchronizedMap(new HashMap<Locale,ResourceBundle>());

  protected static Map<Locale,ResourceBundle> getLocales() {
    return locales;
  }

  private static ResourceBundle getBundle() {
    Locale locale = LocaleHelper.getLocale();
    ResourceBundle bundle = locales.get(locale);
    if (bundle == null) {
      bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
      locales.put(locale, bundle);
    }
    return bundle;
  }

  public static String getEncodedString(String rawValue) {
    if (rawValue == null) {
      return (""); //$NON-NLS-1$
    }

    StringBuffer value = new StringBuffer();
    for (int n = 0; n < rawValue.length(); n++) {
      int charValue = rawValue.charAt(n);
      if (charValue >= 0x80) {
        value.append("&#x"); //$NON-NLS-1$
        value.append(Integer.toString(charValue, 0x10));
        value.append(";"); //$NON-NLS-1$
      } else {
        value.append((char) charValue);
      }
    }
    return value.toString();

  }

  public static String getXslString(String key) {
    String rawValue = getString(key);
    return getEncodedString(rawValue);
  }

  public static String getString(String key) {
    try {
      return getBundle().getString(key);
    } catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }

  public static String getString(String key, String param1) {
    return MessageUtil.getString(getBundle(), key, param1);
  }

  public static String getString(String key, String param1, String param2) {
    return MessageUtil.getString(getBundle(), key, param1, param2);
  }

  public static String getString(String key, String param1, String param2, String param3) {
    return MessageUtil.getString(getBundle(), key, param1, param2, param3);
  }

  public static String getString(String key, String param1, String param2, String param3, String param4) {
    return MessageUtil.getString(getBundle(), key, param1, param2, param3, param4);
  }

  public static String getErrorString(String key) {
    return MessageUtil.formatErrorMessage(key, getString(key));
  }

  public static String getErrorString(String key, String param1) {
    return MessageUtil.getErrorString(getBundle(), key, param1);
  }

  public static String getErrorString(String key, String param1, String param2) {
    return MessageUtil.getErrorString(getBundle(), key, param1, param2);
  }

  public static String getErrorString(String key, String param1, String param2, String param3) {
    return MessageUtil.getErrorString(getBundle(), key, param1, param2, param3);
  }

}