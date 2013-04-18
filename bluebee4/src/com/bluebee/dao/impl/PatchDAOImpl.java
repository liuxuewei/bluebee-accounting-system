package com.bluebee.dao.impl;

import com.bluebee.dao.PatchDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PatchDAOImpl extends BaseDAO
  implements PatchDAO
{
  public void patch(float oldversion, float newversion)
  {
    if (oldversion == 1.3F) {
      path1_4(oldversion, newversion);
    }
    else if (oldversion == 1.4F)
      path1_5(oldversion, newversion);
  }

  private void path1_4(float oldversion, float newversion)
  {
    String sqluser = "ALTER USER SA SET PASSWORD 'ffflow88s'";
    String ql = "ALTER TABLE FLOW_LOG ADD \"CUSTOMID\" VARCHAR (20);";
    String qls = "ALTER TABLE FLOW_LOG ADD \"CUSTOMNAME\" VARCHAR (50);";
    String sqlstock = "DROP TABLE IF EXISTS CUSTOM;\nCREATE  TABLE CUSTOM(\n    CUSTOMID VARCHAR(20),\n    CUSTOMNAME VARCHAR(50) ,\n    CUSTOMTYPE VARCHAR(50) ,\n    SEX VARCHAR(8),\n    BIRTHDAY DATE,\n    INTEGRATION DOUBLE,\n    AMOUNT DECIMAL,\n    FREQUENCY INTEGER,\n    TELEPHONE VARCHAR(20),\n    ADDRESS VARCHAR(200),\n    NODTE VARCHAR(2000),\n    REGDATE DATE\n);";

    String CUSTOMTYPE = "DROP TABLE IF EXISTS CUSTOMTYPE;\nCREATE  TABLE CUSTOMTYPE(\n    CUSTOMNAME VARCHAR(50) ,\n    INTEGRATION DOUBLE ,\n    DISCOUNT DOUBLE\n);";

    String USERRIGHTS = "DROP TABLE IF EXISTS USERRIGHTS ;\nCREATE  TABLE USERRIGHTS(\n    USERNAME VARCHAR(250) ,\n    RIGHTS VARCHAR(150)\n);";

    String USERS = "DROP TABLE IF EXISTS USERS;\nCREATE  TABLE USERS(\n    USERID VARCHAR(20) ,\n    USERNAME VARCHAR(250) ,\n    USERTYPE VARCHAR(10),\n    PASSWORD VARCHAR(10)\n);";

    String userM = "INSERT INTO USERS VALUES ( '10000','π‹¿Ì‘±','Manage','')";
    Statement stat2 = null;
    Statement stat1 = null;
    Connection conn = super.getConnection();
    try {
      stat2 = conn.createStatement();
      stat2.execute(sqluser);
      stat2.execute(ql);
      stat2.execute(qls);
      stat2.execute(sqlstock);
      stat2.execute(CUSTOMTYPE);
      stat2.execute(USERRIGHTS);
      stat2.execute(USERS);

      stat2.close();
      conn.commit();
      stat1 = conn.createStatement();
      stat1.execute(userM);
      stat1.close();
      conn.commit();
    } catch (SQLException e) {
      e.printStackTrace();

      if (stat2 != null)
        try {
          stat2.close();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      if (stat1 != null)
        try {
          stat1.close();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
    }
    finally
    {
      if (stat2 != null)
        try {
          stat2.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      if (stat1 != null)
        try {
          stat1.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
  }

  private void path1_5(float oldversion, float newversion)
  {
    String crsql = "CREATE  TABLE SUPPLIERS(\nsuppliersno VARCHAR(50) NOT NULL,\nsuppliersName VARCHAR(1000),\ncontact VARCHAR(100),\nphone VARCHAR(50),\nemail VARCHAR(50),\nqq VARCHAR(50),\naddress VARCHAR(2000),\nfax VARCHAR(50),\nzipcode VARCHAR(50),\nremarks VARCHAR(2000)\n);";

    String sql1 = "ALTER TABLE FLOW_LOG ALTER COLUMN AMOUNT DOUBLE";
    String sql2 = "ALTER TABLE STOCK ALTER COLUMN AMOUNT DOUBLE";
    String sql3 = "ALTER TABLE STOCK ALTER COLUMN SYAMOUNT DOUBLE";
    String sql4 = "ALTER TABLE STOCK ADD \"SUPPLIERSNAME\"  VARCHAR (1000)";
    String sql5 = "ALTER TABLE DAILYEXPENSES ADD \"MODE\" VARCHAR (20)";
    String sql6 = "UPDATE DAILYEXPENSES SET MODE='expenses'";
    Statement stat2 = null;
    Statement stat1 = null;
    Connection conn = super.getConnection();
    try {
      stat2 = conn.createStatement();
      stat2.execute(sql1);
      stat2.execute(sql2);
      stat2.execute(sql3);
      stat2.execute(sql4);
      stat2.execute(sql5);
      stat2.execute(sql6);
      stat2.execute(crsql);
      stat2.close();
      conn.commit();
    } catch (SQLException e) {
      e.printStackTrace();

      if (stat2 != null)
        try {
          stat2.close();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      if (stat1 != null)
        try {
          stat1.close();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
    }
    finally
    {
      if (stat2 != null)
        try {
          stat2.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      if (stat1 != null)
        try {
          stat1.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
  }
}