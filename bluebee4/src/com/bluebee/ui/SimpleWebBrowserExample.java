package com.bluebee.ui;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.NSOption;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SimpleWebBrowserExample extends JPanel
{
  public SimpleWebBrowserExample()
  {
    super(new BorderLayout());
    JPanel webBrowserPanel = new JPanel(new BorderLayout());

    JWebBrowser webBrowser = new JWebBrowser(new NSOption[0]);
    webBrowser.setBarsVisible(false);
    webBrowser.setStatusBarVisible(false);
    webBrowser.navigate("http://www.sohu.com");
    webBrowserPanel.add(webBrowser, "Center");
    webBrowser.addWebBrowserListener(new WebBrowserAdapter()
    {
      public void locationChanging(WebBrowserNavigationEvent e) {
        String newResourceLocation = e.getNewResourceLocation();
      }

      public void windowWillOpen(WebBrowserWindowWillOpenEvent e)
      {
        e.getNewWebBrowser().addWebBrowserListener(new WebBrowserAdapter()
        {
          public void locationChanging(WebBrowserNavigationEvent e) {
            final JWebBrowser webBrowser = e.getWebBrowser();
            webBrowser.removeWebBrowserListener(this);
            String newResourceLocation = e.getNewResourceLocation();
            Desktop desktop = Desktop.getDesktop();

            URI netSite = null;
            try {
              netSite = new URI(newResourceLocation);
            } catch (URISyntaxException ex) {
              ex.printStackTrace();
            }
            try {
              desktop.browse(netSite);
            } catch (IOException ex) {
              ex.printStackTrace();
            }
            e.consume();
            SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                webBrowser.getWebBrowserWindow().dispose();
              }
            });
          }
        });
      }
    });
    add(webBrowserPanel, "Center");
  }
  public static void main(String[] args) {
    UIUtils.setPreferredLookAndFeel();
    NativeInterface.open();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("DJ Native Swing Test");
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().add(new SimpleWebBrowserExample(), "Center");
        frame.setSize(800, 600);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
      }
    });
    NativeInterface.runEventPump();
  }
}