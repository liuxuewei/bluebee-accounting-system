package com.bluebee.ui.substance;

import org.jvnet.substance.api.ComponentState;
import org.jvnet.substance.api.SubstanceColorScheme;
import org.jvnet.substance.api.SubstanceColorSchemeBundle;
import org.jvnet.substance.api.SubstanceSkin;
import org.jvnet.substance.colorscheme.DarkGrayColorScheme;
import org.jvnet.substance.colorscheme.EbonyColorScheme;
import org.jvnet.substance.colorscheme.TintColorScheme;
import org.jvnet.substance.painter.border.ClassicBorderPainter;
import org.jvnet.substance.painter.decoration.ArcDecorationPainter;
import org.jvnet.substance.painter.decoration.DecorationAreaType;
import org.jvnet.substance.painter.gradient.GlassGradientPainter;
import org.jvnet.substance.painter.highlight.ClassicHighlightPainter;
import org.jvnet.substance.shaper.ClassicButtonShaper;

public class SubstanceATunesDarkSkin extends SubstanceSkin
{
  public SubstanceATunesDarkSkin()
  {
    SubstanceColorScheme activeScheme = new TintColorScheme(new EbonyColorScheme(), 0.1000000014901161D);
    SubstanceColorScheme defaultScheme = new EbonyColorScheme();
    SubstanceColorScheme disabledScheme = new TintColorScheme(new DarkGrayColorScheme(), 0.2D);

    SubstanceColorSchemeBundle defaultSchemeBundle = new SubstanceColorSchemeBundle(activeScheme, defaultScheme, disabledScheme);
    defaultSchemeBundle.registerHighlightColorScheme(activeScheme, 0.7F, new ComponentState[] { ComponentState.SELECTED });
    registerDecorationAreaSchemeBundle(defaultSchemeBundle, new DecorationAreaType[] { DecorationAreaType.NONE });

    SubstanceColorSchemeBundle specialSchemeBundle = new SubstanceColorSchemeBundle(activeScheme, activeScheme, disabledScheme);
    registerDecorationAreaSchemeBundle(specialSchemeBundle, new DecorationAreaType[] { DecorationAreaType.PRIMARY_TITLE_PANE, DecorationAreaType.SECONDARY_TITLE_PANE, DecorationAreaType.TOOLBAR, 
      DecorationAreaType.FOOTER, DecorationAreaType.HEADER, DecorationAreaType.GENERAL });

    this.buttonShaper = new ClassicButtonShaper();
    this.gradientPainter = new GlassGradientPainter();
    this.borderPainter = new ClassicBorderPainter();
    this.decorationPainter = new ArcDecorationPainter();
    this.highlightPainter = new ClassicHighlightPainter();
  }

  public String getDisplayName()
  {
    return "aTunes Dark skin";
  }
}