package com.bluebee;

import com.bluebee.dao.JdbcConnectionPoolHelper;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Config;
import com.bluebee.ui.LoginFrame;
import com.bluebee.ui.skin.ColorDefinitions;
import com.bluebee.ui.skin.LookAndFeelSelector;
import com.bluebee.util.LanguageLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.SwingUtilities;
import org.h2.Driver;

public class Launcher
{
  public static void main(String[] args)
  {
    Launcher launcher = new Launcher();

    launcher.run();
  }

  private void run() {
    try {
      initJDBCConnect();
      LanguageLoader.setLanguage(Locale.getDefault());
      try
      {
        String skin = SysEnv.getInstance().getSkin();
        LookAndFeelSelector.setLookAndFeel(skin);
        ColorDefinitions.initColors();
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            float vnew = Float.parseFloat(Version.getInstance().getVersion());
            float vold = Float.parseFloat(SysEnv.getInstance().getVersion());
            if (vold < vnew) {
              Patch.pachExexcute(vold, vnew);
              Config config = new Config();
              config.setKey("VERSION");
              config.setValue(Version.getInstance().getVersion());
              MoudleContentFactry.getConfigMoudle().saveOrUpdateConfig(config);
            }
            LoginFrame login = LoginFrame.getInstance();
            login.setDefaultCloseOperation(3);
            login.setVisible(true);
          } } );
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  }

  private static void initJDBCConnect() throws SQLException {
    String url = "jdbc:h2:file:" + Constant.DATA_PATH;
    Connection conn = null;
    Driver.load();
    String user = "sa";
    String password = "ffflow88s";
    try {
      conn = DriverManager.getConnection(url, user, password);
      JdbcConnectionPoolHelper.getInstance().setConnection(conn);
    } catch (SQLException e) {
      conn = DriverManager.getConnection(url, user, "sa");
      JdbcConnectionPoolHelper.getInstance().setConnection(conn);
    }
  }
}