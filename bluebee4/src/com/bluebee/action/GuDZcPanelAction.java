package com.bluebee.action;

import com.bluebee.ui.frame.GuDZcPanel;
import com.bluebee.ui.frame.PageContainer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class GuDZcPanelAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(GuDZcPanel.getInstance());
  }
}