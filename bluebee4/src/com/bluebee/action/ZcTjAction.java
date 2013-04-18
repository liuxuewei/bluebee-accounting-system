package com.bluebee.action;

import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.frame.ZcTjPanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ZcTjAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(ZcTjPanel.getInstance());
  }
}