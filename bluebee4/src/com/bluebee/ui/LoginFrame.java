package com.bluebee.ui;

import com.bluebee.ui.frame.BaseFrame;
import com.bluebee.ui.frame.LoginPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class LoginFrame extends BaseFrame
{
  private static LoginFrame mainWindows = new LoginFrame();

  public static LoginFrame getInstance()
  {
    if (mainWindows != null) {
      mainWindows.setContentPane(new LoginPane());
    }

    return mainWindows;
  }

  private LoginFrame()
  {
    setAlwaysOnTop(true);
    setTitle("µÇÂ¼BlueBeeÀ¶ÃÛ·äÁ÷Ë®¼ÇÕË");
    setSize(260, 145);
    setResizable(false);

    Dimension screSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = screSize.width;
    int y = screSize.height;
    int appW = (int)getSize().getWidth();
    int appH = (int)getSize().getHeight();

    setLocation((x - appW) / 2, (y - appH) / 2);
  }

  public void actionPerformed(ActionEvent e)
  {
  }
}