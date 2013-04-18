package com.bluebee.action;

import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.frame.SuppliersPanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class SuppliersAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(SuppliersPanel.getInstance());
  }
}