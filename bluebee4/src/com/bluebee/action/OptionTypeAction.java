package com.bluebee.action;

import com.bluebee.ui.frame.OptionsTypePanel;
import com.bluebee.ui.frame.PageContainer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class OptionTypeAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(OptionsTypePanel.getInstance());
  }
}