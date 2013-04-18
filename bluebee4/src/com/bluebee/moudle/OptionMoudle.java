package com.bluebee.moudle;

import com.bluebee.dao.OptionDAO;
import com.bluebee.pojo.Option;

import java.util.List;

public abstract interface OptionMoudle
{
  public abstract void setOptionDAO(OptionDAO paramOptionDAO);

  public abstract boolean addOption(Option paramOption);

  public abstract boolean deleteOption(String paramString);

  public abstract List getOption();

  public abstract List getOption(String paramString);
}