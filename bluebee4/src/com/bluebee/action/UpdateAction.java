package com.bluebee.action;

import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.frame.UpdatePanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class UpdateAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(UpdatePanel.getInstance());
  }
}