package com.bluebee.ui.substance;

import org.jvnet.substance.SubstanceLookAndFeel;

public class SubstanceATunesDarkLookAndFeel extends SubstanceLookAndFeel
{
  private static final long serialVersionUID = -3907225219153995877L;

  public SubstanceATunesDarkLookAndFeel()
  {
    super(new SubstanceATunesDarkSkin());
  }

  public String getID()
  {
    return "Substance aTunes Dark";
  }

  public String getName()
  {
    return "Substance aTunes Dark";
  }
}