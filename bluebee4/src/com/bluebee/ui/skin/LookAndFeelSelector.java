package com.bluebee.ui.skin;

import com.bluebee.util.GuiUtils;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.jvnet.lafwidget.utils.LafConstants;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceConstants;

public class LookAndFeelSelector
{
  private static Map<String, String> skins = setListOfSkins();
  public static final String DEFAULT_SKIN = "aTunes Blue";

  public static List<String> getListOfSkins()
  {
    List result = new ArrayList(skins.keySet());
    Collections.sort(result, new Comparator<String>() {
		public int compare(String str1, String str2) {
			return str1.toLowerCase().compareTo(str2.toLowerCase());
		}
	});
    return result;
  }

  private static Map<String, String> setListOfSkins()
  {
    Map result = new HashMap();

    result.put("BusinessBlackSteel", "org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
    result.put("Creme", "org.jvnet.substance.skin.SubstanceCremeLookAndFeel");
    result.put("Business", "org.jvnet.substance.skin.SubstanceBusinessLookAndFeel");
    result.put("BusinessBlueSteel", "org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel");
    result.put("CremeCoffee", "org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel");
    result.put("Sahara", "org.jvnet.substance.skin.SubstanceSaharaLookAndFeel");
    result.put("Moderate", "org.jvnet.substance.skin.SubstanceModerateLookAndFeel");
    result.put("OfficeSilver2007", "org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");
    result.put("Nebula", "org.jvnet.substance.skin.SubstanceNebulaLookAndFeel");
    result.put("NebulaBrickWall", "org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel");
    result.put("Autumn", "org.jvnet.substance.skin.SubstanceAutumnLookAndFeel");
    result.put("MistSilver", "org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel");
    result.put("MistAqua", "org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel");
    result.put("RavenGraphite", "org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel");
    result.put("RavenGraphiteGlass", "org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel");
    result.put("Raven", "org.jvnet.substance.skin.SubstanceRavenLookAndFeel");
    result.put("Magma", "org.jvnet.substance.skin.SubstanceMagmaLookAndFeel");
    result.put("ChallengerDeep", "org.jvnet.substance.skin.SubstanceChallengerDeepLookAndFeel");
    result.put("EmeraldDusk", "org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel");

    result.put("OfficeBlue2007", "org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");

    result.put("aTunes Blue", "com.lyc.yygl.ui.substance.SubstanceATunesBlueLookAndFeel");

    result.put("aTunes Dark", "org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel");
    result.put("aTunes Gray", "com.lyc.yygl.ui.substance.SubstanceATunesGrayLookAndFeel");

    for (UIManager.LookAndFeelInfo lf : UIManager.getInstalledLookAndFeels()) {
      result.put(lf.getName(), lf.getClassName());
    }

    return result;
  }

  public static void setLookAndFeel(String theme)
  {
    try
    {
      if (skins.containsKey(theme)) {
        UIManager.setLookAndFeel((String)skins.get(theme));
      }
      else {
        UIManager.setLookAndFeel((String)skins.get("aTunes Blue"));
      }

      if (SwingUtilities.isEventDispatchThread())
    	  LookAndFeelSelector.fixFontBug();
      else {
        try {
          SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
            	LookAndFeelSelector.fixFontBug();
            }
          });
        }
        catch (Exception localException1)
        {
        }

      }

      if (!isDefaultLookAndFeel(theme))
      {
        try {
          GuiUtils.putLookAndFeelColor("borderColor", 
            SubstanceLookAndFeel.getCurrentSkin().getMainActiveColorScheme().getMidColor());
          GuiUtils.putLookAndFeelColor("lightColor", 
            SubstanceLookAndFeel.getCurrentSkin()
            .getMainActiveColorScheme()
            .getLightColor());
          GuiUtils.putLookAndFeelColor("lightBackgroundFillColor", 
            SubstanceLookAndFeel.getCurrentSkin()
            .getMainActiveColorScheme()
            .getLightBackgroundFillColor());
          GuiUtils.putLookAndFeelColor("darkColor", 
            SubstanceLookAndFeel.getCurrentSkin()
            .getMainActiveColorScheme().getDarkColor());
          GuiUtils.putLookAndFeelColor("backgroundFillColor", 
            SubstanceLookAndFeel.getCurrentSkin()
            .getMainActiveColorScheme()
            .getBackgroundFillColor());
          GuiUtils.putLookAndFeelColor("lineColor", 
            SubstanceLookAndFeel.getCurrentSkin()
            .getMainActiveColorScheme().getLineColor());
          GuiUtils.putLookAndFeelColor("selectionForegroundColor", 
            SubstanceLookAndFeel.getCurrentSkin()
            .getMainActiveColorScheme()
            .getSelectionForegroundColor());
          GuiUtils.putLookAndFeelColor("selectionBackgroundColor", 
            SubstanceLookAndFeel.getCurrentSkin()
            .getMainActiveColorScheme()
            .getSelectionBackgroundColor());
          GuiUtils.putLookAndFeelColor("foregroundColor", 
            SubstanceLookAndFeel.getCurrentSkin()
            .getMainActiveColorScheme()
            .getForegroundColor());
          GuiUtils.putLookAndFeelColor("focusRingColor", 
            SubstanceLookAndFeel.getCurrentSkin()
            .getMainActiveColorScheme()
            .getFocusRingColor());
        }
        catch (Exception e) {
          e.printStackTrace();
        }

        UIManager.put("lafwidgets.animationKind",
				LafConstants.AnimationKind.NONE);
		UIManager
				.put("substancelaf.tabbedPaneContentBorderKind",
						SubstanceConstants.TabContentPaneBorderKind.SINGLE_FULL);

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static boolean isDefaultLookAndFeel(String theme)
  {
    boolean defaultLF = false;
    for (UIManager.LookAndFeelInfo lf : UIManager.getInstalledLookAndFeels())
    {
      if (theme.equals(lf.getName())) {
        defaultLF = true;
      }
    }
    return defaultLF;
  }

  private static void fixFontBug() {
    int sizeOffset = 0;
    Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if ((value instanceof Font)) {
        Font oldFont = (Font)value;

        Font newFont = new Font("Dialog", oldFont.getStyle(), oldFont
          .getSize() + 
          sizeOffset);
        UIManager.put(key, newFont);
      }
    }
  }
}