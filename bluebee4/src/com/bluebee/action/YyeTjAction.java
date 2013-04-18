package com.bluebee.action;

import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.frame.YyeTjPanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class YyeTjAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(YyeTjPanel.getInstance());
  }
}