package com.bluebee.moudle.impl;

import com.bluebee.dao.OptionDAO;
import com.bluebee.moudle.OptionMoudle;
import com.bluebee.pojo.Option;

import java.util.List;

public class OptionMoudleImpl
  implements OptionMoudle
{
  private OptionDAO optionDAO;

  public void setOptionDAO(OptionDAO optionDAO)
  {
    this.optionDAO = optionDAO;
  }

  public boolean addOption(Option option) {
    return this.optionDAO.addOption(option);
  }

  public boolean deleteOption(String id)
  {
    return this.optionDAO.deleteOption(id);
  }

  public List getOption(String type) {
    return this.optionDAO.getOption(type);
  }

  public List getOption() {
    return this.optionDAO.getOption();
  }
}