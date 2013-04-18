package com.bluebee.action;

import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.frame.SellHelperPanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class SellHelperActoin extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(SellHelperPanel.getInstance());
  }
}