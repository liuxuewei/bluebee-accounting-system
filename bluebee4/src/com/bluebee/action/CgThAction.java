package com.bluebee.action;

import com.bluebee.ui.frame.CgthPane;
import com.bluebee.ui.frame.PageContainer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class CgThAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(CgthPane.getInstance());
  }
}