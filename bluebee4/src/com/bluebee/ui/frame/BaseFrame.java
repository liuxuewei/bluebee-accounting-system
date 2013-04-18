package com.bluebee.ui.frame;

import com.bluebee.Constant;
import com.bluebee.ui.skin.ImageManager;

import java.awt.Component;
import java.awt.event.ActionListener;
import org.jdesktop.swingx.JXFrame;

public abstract class BaseFrame extends JXFrame
  implements ActionListener
{
  private static final long serialVersionUID = -3172874700605827735L;

  public BaseFrame()
  {
    setIconImage(ImageManager.getImage(Constant.APP_ICON));
  }

  public BaseFrame(String title)
  {
    super(title);
    setIconImage(ImageManager.getImage(Constant.APP_ICON));
  }

  public BaseFrame(String title, int width, int height, Component owner)
  {
    super(title);
    setSize(width, height);
    setLocationRelativeTo(owner);
    setIconImage(ImageManager.getImage(Constant.APP_ICON));
  }
}