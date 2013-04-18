package com.bluebee.ui.frame;

import com.bluebee.Version;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class AboutPanel extends JPanel
  implements IPage
{
  private static AboutPanel aboutPanel = new AboutPanel();

  private boolean isDefaultPage = false;

  private AboutPanel()
  {
    setLayout(null);

    JPanel copyrightpanel = new JPanel();
    copyrightpanel.setLayout(null);
    copyrightpanel.setBorder(new TitledBorder(null, "版权", 4, 2, null, null));
    copyrightpanel.setBounds(22, 10, 544, 98);
    add(copyrightpanel);

    JLabel label = new JLabel(" 本软件版本" + Version.getInstance().getVersion() + Version.getInstance().getBuild());
    label.setFont(new Font("宋体", 0, 12));
    label.setBounds(21, 21, 431, 17);
    copyrightpanel.add(label);

    JLabel lblCopy = new JLabel("Copyright(C)2009-2013 All Rights Reserved");
    lblCopy.setFont(new Font("宋体", 0, 12));
    lblCopy.setBounds(30, 73, 346, 15);
    copyrightpanel.add(lblCopy);

    JLabel lblsys = new JLabel("BlueBee蓝蜜蜂版权所有");
    lblsys.setBounds(31, 48, 135, 15);
    copyrightpanel.add(lblsys);

    JPanel supportpanel = new JPanel();
    supportpanel.setBorder(new TitledBorder(null, "技术支持", 4, 2, null, null));
    supportpanel.setLayout(null);
    supportpanel.setBounds(22, 110, 544, 98);
    add(supportpanel);

    JLabel lblQqlycvipgmailcom = new JLabel("QQ群:149012385");
    lblQqlycvipgmailcom.setFont(new Font("宋体", 0, 12));
    lblQqlycvipgmailcom.setBounds(30, 21, 300, 17);
    supportpanel.add(lblQqlycvipgmailcom);

    JLabel lblwwwsyscom = new JLabel("<html>网址:<u>bluebee-accounting-system</u></html>");
    lblwwwsyscom.setCursor(Cursor.getPredefinedCursor(12));
    lblwwwsyscom.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        Desktop desktop = Desktop.getDesktop();
        URI netSite = null;
        try {
          netSite = new URI("http://code.google.com/p/bluebee-accounting-system/");
        } catch (URISyntaxException ex) {
          ex.printStackTrace();
        }
        try {
          desktop.browse(netSite);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });
    lblwwwsyscom.setFont(new Font("宋体", 0, 12));
    lblwwwsyscom.setBounds(30, 71, 300, 17);
    supportpanel.add(lblwwwsyscom);

    JLabel lblQqlycvipgmailcom_1 = new JLabel("邮箱:liu.xuewei@hotmail.com");
    lblQqlycvipgmailcom_1.setFont(new Font("宋体", 0, 12));
    lblQqlycvipgmailcom_1.setBounds(30, 47, 300, 17);
    supportpanel.add(lblQqlycvipgmailcom_1);
  }

  public static AboutPanel getInstance() {
    return aboutPanel;
  }

  public void disposePage() {
  }

  public String getPageId() {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>关于BlueBee蓝蜜蜂";
  }

  public boolean isDefaultPage()
  {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool)
  {
    this.isDefaultPage = bool;
  }
}