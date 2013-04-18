package com.bluebee.ui.skin;

import com.bluebee.Constant;
import com.bluebee.util.ResouceLoader;

import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

public class ImageManager
{
  private static HashMap<String, ImageIcon> imageRegistry = new HashMap();

  public static HashMap<String, ImageIcon> getImageRegistry() {
    if (imageRegistry == null) {
      imageRegistry = new HashMap();
    }
    return imageRegistry;
  }

  public static ImageIcon getImageIcon(String imageName) {
    return getImageIcon(imageName, null);
  }

  public static ImageIcon getImageIcon(String imageName, String description) {
    ImageIcon getImageIcon = (ImageIcon)imageRegistry.get(imageName);
    if (getImageIcon == null) {
      getImageIcon = description == null ? new ImageIcon(
        ResouceLoader.getResouce(imageName)) : new ImageIcon(
        ResouceLoader.getResouce(imageName), description);
      imageRegistry.put(imageName, getImageIcon);
    }
    return getImageIcon;
  }

  public static Image getImage(String imageName) {
    return getImageIcon(imageName).getImage();
  }

  public static ImageIcon getImageIconByShortName(String imageName)
  {
    return getImageIcon(Constant.ICON_DIR + Constant.getSlash() + imageName);
  }

  public static Image getImageByShortName(String imageName)
  {
    return getImageIcon(Constant.ICON_DIR + Constant.getSlash() + imageName)
      .getImage();
  }
}