package com.bluebee.action;

import com.bluebee.ui.frame.CustomPanel;
import com.bluebee.ui.frame.PageContainer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class CustomAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(CustomPanel.getInstance());
  }
}