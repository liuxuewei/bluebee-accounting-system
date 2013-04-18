package com.bluebee.util;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class GuiUtils
{
  private static HashMap<String, Color> lookAndFeelColorRegister = new HashMap();
  private static ComponentOrientation componentOrientation;
  private static Method setWindowShapeMethod;
  private static Method setWindowOpacityMethod;
  private static Method setWindowOpaqueMethod;
  private static Rectangle mainDeviceBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
  public static final int MAX_COMPONENTS_WIDTH = 1280;
  public static final Color errorMessageColor = Color.RED;
  public static final Color AlertMessageColor = Color.RED;

  public static int getDeviceWidth()
  {
    return mainDeviceBounds.width;
  }

  public static int getDeviceHeight()
  {
    return mainDeviceBounds.height;
  }

  public static void setLocation(Window window)
  {
    window.setLocation(mainDeviceBounds.width / 2 - window.getWidth() / 2, mainDeviceBounds.height / 2 - window.getHeight() / 2);
  }

  public static void addCloseActionWithEscapeKey(final JDialog dialog)
  {
    KeyStroke escape = KeyStroke.getKeyStroke(27, 0, false);
    Action escapeAction = new AbstractAction() {
      private static final long serialVersionUID = 0L;

      public void actionPerformed(ActionEvent e) {
    	  dialog.setVisible(false);
      }
    };
    dialog.getRootPane().getInputMap(2).put(escape, "ESCAPE");
    dialog.getRootPane().getActionMap().put("ESCAPE", escapeAction);
  }

  public static void addCloseActionWithEscapeKey(final JFrame frame)
  {
    KeyStroke escape = KeyStroke.getKeyStroke(27, 0, false);
    Action escapeAction = new AbstractAction() {
      private static final long serialVersionUID = 0L;

      public void actionPerformed(ActionEvent e) {
    	  frame.setVisible(false);
      }
    };
    frame.getRootPane().getInputMap(2).put(escape, "ESCAPE");
    frame.getRootPane().getActionMap().put("ESCAPE", escapeAction);
  }

  public static void addDisposeActionWithEscapeKey(final JDialog dialog)
  {
    KeyStroke escape = KeyStroke.getKeyStroke(27, 0, false);
    Action disposeAction = new AbstractAction() {
      private static final long serialVersionUID = 0L;

      public void actionPerformed(ActionEvent e) {
    	  dialog.dispose();
      }
    };
    dialog.getRootPane().getInputMap(2).put(escape, "ESCAPE");
    dialog.getRootPane().getActionMap().put("ESCAPE", disposeAction);
  }

  public static void addDisposeActionWithEscapeKey(final JFrame frame)
  {
    KeyStroke escape = KeyStroke.getKeyStroke(27, 0, false);
    Action disposeAction = new AbstractAction() {
      private static final long serialVersionUID = 0L;

      public void actionPerformed(ActionEvent e) {
    	  frame.dispose();
      }
    };
    frame.getRootPane().getInputMap(2).put(escape, "ESCAPE");
    frame.getRootPane().getActionMap().put("ESCAPE", disposeAction);
  }

  public static void applyComponentOrientation(Container[] containers)
  {
    if (componentOrientation == null) {
      setComponentOrientation();
    }
    Container[] arrayOfContainer = containers; int j = containers.length; for (int i = 0; i < j; i++) { Container container = arrayOfContainer[i];
      container.applyComponentOrientation(componentOrientation);
    }
  }

  public static void collapseTree(JTree tree)
  {
    for (int i = tree.getRowCount() - 1; i > 0; i--) {
      tree.collapseRow(i);
    }
    tree.setSelectionRow(0);
  }

  public static void expandTree(JTree tree)
  {
    for (int i = 1; i < tree.getRowCount(); i++) {
      tree.expandRow(i);
    }
    tree.setSelectionRow(0);
  }

  public static Color getBackgroundColor()
  {
    return (Color)UIManager.get("Panel.background");
  }

  public static Color getForegroundColor()
  {
    return (Color)UIManager.get("Label.foreground");
  }

  public static Color getLookAndFeelColor(String colorName)
  {
    Color c = (Color)lookAndFeelColorRegister.get(colorName != null ? colorName.trim() : " ");

    return c;
  }

  public static void putLookAndFeelColor(String colorName, Color c)
  {
    lookAndFeelColorRegister.put(colorName, c);
  }

  public static ComponentOrientation getComponentOrientation()
  {
    if (componentOrientation == null) {
      setComponentOrientation();
    }
    return componentOrientation;
  }

  public static int getComponentOrientationAsSwingConstant()
  {
    if (componentOrientation == null) {
      setComponentOrientation();
    }
    return componentOrientation.isLeftToRight() ? 2 : 4;
  }

  public static int getComponentWidthForResolution(int screenWidth, int desiredWidth)
  {
    int currentScreenWidth = mainDeviceBounds.width > 1280 ? 1280 : mainDeviceBounds.width;
    int result = desiredWidth * currentScreenWidth / screenWidth;
    return result;
  }

  public static int getComponentHeightForResolution(int screenHeight, int desiredHeight)
  {
    int currentScreenHeight = mainDeviceBounds.height;
    int result = desiredHeight * currentScreenHeight / screenHeight;
    return result;
  }

  private static void setComponentOrientation()
  {
    componentOrientation = ComponentOrientation.getOrientation(Locale.getDefault());
  }

  public static void setUIFont(FontUIResource f)
  {
    Enumeration keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if ((value instanceof FontUIResource))
        UIManager.put(key, f);
    }
  }

  public static void setWindowShape(Window window, Shape mask)
  {
    if (setWindowShapeMethod != null)
      try {
        setWindowShapeMethod.invoke(null, new Object[] { window, mask });
      } catch (Exception e) {
        e.printStackTrace();
      }
  }

  public static void setWindowOpacity(Window window, float opacity)
  {
    if (setWindowOpacityMethod != null)
      try {
        setWindowOpacityMethod.invoke(null, new Object[] { window, Float.valueOf(opacity) });
      } catch (Exception e) {
        e.printStackTrace();
      }
  }

  public static void setWindowOpaque(Window window, boolean opaque)
  {
    if (setWindowOpaqueMethod != null)
      try {
        setWindowOpaqueMethod.invoke(null, new Object[] { window, Boolean.valueOf(opaque) });
      } catch (Exception e) {
        e.printStackTrace();
      }
  }

  private Color createRandomColor()
  {
    return Color.BLUE;
  }
}