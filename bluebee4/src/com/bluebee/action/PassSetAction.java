package com.bluebee.action;

import com.bluebee.ui.frame.AuthorityPanel;
import com.bluebee.ui.frame.PageContainer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class PassSetAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(AuthorityPanel.getInstance());
  }
}