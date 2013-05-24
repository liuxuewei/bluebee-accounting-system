package com.bluebee;

public class Constant
{
  public static final String VERSION = "VERSION";
  public static final String DOMAIN_NAME = "com";
  public static String PROJECT_NAME = "bluebee";

  public static String PACKAGE_ROOT = "com" + getDot() + PROJECT_NAME;
  public static final String PROTOCOL_FILE = "file:";
  public static final String LOG4J_FILE = "config/log4j.properties";
  public static String PACKAGE_RESOURSE = PACKAGE_ROOT + getDot() + "resource";

  public static final String PACKAGE_IMAGE = PACKAGE_RESOURSE + getDot() + "image";

  public static final String DATA_PATH = "config" + getSlash() + "data" + getSlash() + "bluebee";

  public static String ICON_DIR = dotToSlash(PACKAGE_IMAGE);

  public static String APP_ICON = ICON_DIR + getSlash() + "yygl.png";
  public static final String SKIN = "skin";
  public static final String SELLPOLICY = "policy";
  public static final String PASSWORD = "password";
  public static final String MYDZJ = "mydzj";
  public static final String MYDF = "mydf";
  public static final String MYSJ = "gdzc_mysj";
  public static final String MYGLF = "gdzc_myglf";
  public static final String MYSFF = "gdzc_mysf";
  public static final String MYGZ = "gdzc_mygz";
  public static final String MYQT = "gdzc_myqt";
  public static final String MYGDZCZH = "gdzc_ZH";
  public static final String TYPE_HW = "HWLX";
  public static final String TYPE_ZC = "ZCLX";
  public static final String TYPE_SR = "SRLX";
  public static final String FLOW_TYPE_SELL = "sell";
  public static final String FLOW_TYPE_TH = "tuihuo";
  public static final String STCOK_TYPE_JINHUO = "JINHUO";
  public static final String STCOK_TYPE_TH = "tuihuo";
  public static final String USER_TYPE_M = "Manage";
  public static final String USER_TYPE_P = "Personnel";
  public static final String ADMIN = "10000";
  public static final String ADMIN_NAME = "π‹¿Ì‘±";
  public static final String INCOME = "income";
  public static final String EXPENSES = "expenses";
  public static final String[] RIGHT = {
    "true", "true", "true", "true", "true", 
    "true", "true", "true", "true", "true", 
    "true", "true", "true", "true", "true", 
    "true", "true", "true" };

  public static String dotToSlash(String oldString)
  {
    return oldString.replace(getDot(), getSlash());
  }

  public static String getDot()
  {
    return ".";
  }

  public static String getSlash()
  {
    return "/";
  }
}