package com.bluebee.action;

import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.widget.PasswordDialog;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JPanel;

public class PasswordAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PasswordDialog passwordDialog = new PasswordDialog((JPanel)PageContainer.getInstance().getDefaultPage());
    passwordDialog.setVisible(true);
  }
}