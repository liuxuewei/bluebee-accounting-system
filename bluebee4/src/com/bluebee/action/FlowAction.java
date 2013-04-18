package com.bluebee.action;

import com.bluebee.ui.frame.FlowPanel;
import com.bluebee.ui.frame.PageContainer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class FlowAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(FlowPanel.getInstance());
  }
}