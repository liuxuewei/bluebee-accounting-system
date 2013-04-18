package com.bluebee.action;

import com.bluebee.ui.frame.KuCTjPanel;
import com.bluebee.ui.frame.PageContainer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class KCStockAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(KuCTjPanel.getInstance());
  }
}