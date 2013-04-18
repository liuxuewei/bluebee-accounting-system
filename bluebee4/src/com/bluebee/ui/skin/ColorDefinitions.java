package com.bluebee.ui.skin;

import com.bluebee.util.GuiUtils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class ColorDefinitions
{
  public static final Color TITLE_DIALOG_FONT_COLOR = Color.WHITE;

  public static final Color WARNING_COLOR = Color.RED;

  public static final Color GENERAL_UNKNOWN_ELEMENT_FOREGROUND_COLOR = Color.RED;

  public static void initColors()
  {
    UIManager.put("ToolTip.border", BorderFactory.createLineBorder(GuiUtils.getLookAndFeelColor("borderColor")));
    UIManager.put("ToolTip.background", new ColorUIResource(Color.WHITE));
    UIManager.put("ToolTip.foreground", new ColorUIResource(Color.BLACK));
    Font font = new Font("ËÎÌו", 0, 12);
    UIManager.put("Frame.titleFont", font);
    UIManager.put("InternalFrame.titleFont", font);
    UIManager.put("Frame.font", font);
    UIManager.put("Menu.font", font);
    UIManager.put("MenuItem.font", font);
    UIManager.put("TitledBorder.font", font);
    UIManager.put("InternalFrame.font", font);
    UIManager.put("InternalFrame.titleFont", font);
    UIManager.put("Table.font", font);
    UIManager.put("TableHeader.font", font);
    UIManager.put("Button.font", font);
    UIManager.put("Label.font", font);
  }
}