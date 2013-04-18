package com.bluebee.action;

import com.bluebee.ui.frame.GkthPane;
import com.bluebee.ui.frame.PageContainer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class GkThAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(GkthPane.getInstance());
  }
}