package com.bluebee.action;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.AbstractAction;

public class HelpAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
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
}