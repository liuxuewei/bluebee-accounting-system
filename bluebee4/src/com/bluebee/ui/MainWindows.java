package com.bluebee.ui;

import com.bluebee.Version;
import com.bluebee.ui.frame.BaseFrame;
import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.skin.ImageManager;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainWindows extends BaseFrame
{
  private static MainWindows mainWindows = new MainWindows();
  public static TrayIcon trayIcon1;
  private SystemTray systemTray;
  private PopupMenu popup;

  private MainWindows()
  {
    String title = new String("BlueBee蓝蜜蜂" + Version.getInstance().getVersion() + "                                  生意兴隆     财源滚滚");
    try {
      setTitle(new String(title.getBytes("utf-8"), "utf-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    setSize(800, 600);

    Dimension screSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = screSize.width;
    int y = screSize.height;
    int appW = (int)getSize().getWidth();
    int appH = (int)getSize().getHeight();

    setLocation((x - appW) / 2, (y - appH) / 2);
    if (SystemTray.isSupported()) {
      this.systemTray = SystemTray.getSystemTray();

      this.popup = new PopupMenu();

      MenuItem show = null;
      try {
        show = new MenuItem(new String("显示界面".getBytes("utf-8"), "utf-8"));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      show.setFont(new Font("宋体", 1, 12));
      ActionListener showListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (LoginFrame.getInstance().isVisible())
            MainWindows.this.setVisible(false);
          else
            MainWindows.this.setVisible(true);
        }
      };
      MenuItem logout = null;
      try {
        logout = new MenuItem(new String("注销".getBytes("utf-8"), "utf-8"));
      }
      catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      show.setFont(new Font("宋体", 1, 12));
      ActionListener logoutListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          MainWindows.this.setVisible(false);
          LoginFrame login = LoginFrame.getInstance();
          login.setDefaultCloseOperation(3);
          login.setVisible(true);
        }
      };
      MenuItem exit = new MenuItem("退出");
      ActionListener exitListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.exit(0);
        }
      };
      show.addActionListener(showListener);
      logout.addActionListener(logoutListener);
      exit.addActionListener(exitListener);

      this.popup.add(show);
      this.popup.add(logout);
      this.popup.add(exit);
      try
      {
        trayIcon1 = new TrayIcon(ImageManager.getImageIconByShortName("yygl.png").getImage(), "BlueBee蓝蜜蜂", this.popup);
      }
      catch (Exception e1) {
        e1.printStackTrace();
      }
      trayIcon1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e) {
          MainWindows.this.setVisible(true);
        }
      });
      try {
        this.systemTray.add(trayIcon1);
      }
      catch (AWTException localAWTException)
      {
      }
      addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          if (SystemTray.isSupported()) {
            MainWindows.this.setVisible(false);
          }
          else
            System.exit(0);
        }
      });
    }
  }

  public static MainWindows getInstance()
  {
    if (mainWindows != null) {
      JPanel jPanel = mainWindows.getWindow();
      mainWindows.setContentPane(jPanel);
    }
    return mainWindows;
  }

  private JPanel getWindow() {
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(getLeftFrame(), "West");
    mainPanel.add(getRightFrame(), "Center");

    return mainPanel;
  }

  private JPanel getLeftFrame() {
    return NavigationMenu.getInstance().getMenu();
  }

  private JPanel getRightFrame() {
    return PageContainer.getInstance();
  }

  public void actionPerformed(ActionEvent e)
  {
  }
}