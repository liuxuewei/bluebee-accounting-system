package com.bluebee.action;

import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.frame.SettiingPanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class SettingAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(SettiingPanel.getInstance());
  }
}