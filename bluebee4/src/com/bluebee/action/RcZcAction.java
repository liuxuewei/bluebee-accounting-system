package com.bluebee.action;

import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.frame.RcZcPanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class RcZcAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(RcZcPanel.getInstance());
  }
}