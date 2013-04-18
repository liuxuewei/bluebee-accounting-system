package com.bluebee.dao;

import com.bluebee.pojo.Option;

import java.util.List;

public abstract interface OptionDAO
{
  public abstract boolean addOption(Option paramOption);

  public abstract boolean deleteOption(String paramString);

  public abstract List getOption();

  public abstract List getOption(String paramString);
}