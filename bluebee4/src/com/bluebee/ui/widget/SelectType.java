package com.bluebee.ui.widget;

import com.bluebee.moudle.CustomtTypeMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.OptionMoudle;
import com.bluebee.moudle.UserMoudle;
import com.bluebee.pojo.CustomType;
import com.bluebee.pojo.Option;
import com.bluebee.pojo.User;

import java.util.List;

public class SelectType
{
  public static Object[] getOptions(String type)
  {
    Object[] ob = new Object[0];
    OptionMoudle optionMoudle = MoudleContentFactry.getOptionMoudle();
    List list = optionMoudle.getOption(type);
    if ((list != null) && (list.size() > 0)) {
      ob = new Object[list.size()];
      for (int i = 0; i < list.size(); i++) {
        Option option = (Option)list.get(i);
        ob[i] = new Options(option.getId(), option.getText());
      }
    }
    return ob;
  }

  public static Object[] getOptionsWithAll(String type) {
    Object[] ob = new Object[0];
    OptionMoudle optionMoudle = MoudleContentFactry.getOptionMoudle();
    List list = optionMoudle.getOption(type);
    if ((list != null) && (list.size() > 0)) {
      ob = new Object[list.size() + 1];
      ob[0] = new Options("all", "全部类型");
      for (int i = 0; i < list.size(); i++) {
        Option option = (Option)list.get(i);
        ob[(i + 1)] = new Options(option.getId(), option.getText());
      }
    }
    return ob;
  }

  public static Object[] getCustomType() {
    Object[] ob = new Object[0];
    CustomtTypeMoudle typeMoudle = MoudleContentFactry.getCustomtTypeMoudle();
    List list = typeMoudle.getCustomtTypes();
    if ((list != null) && (list.size() > 0)) {
      ob = new Object[list.size()];

      for (int i = 0; i < list.size(); i++) {
        CustomType option = (CustomType)list.get(i);
        ob[i] = new Options(option.getTypename(), option.getTypename());
      }
    }
    return ob;
  }

  public static Object[] getUserList() {
    Object[] ob = new Object[0];
    List list = MoudleContentFactry.getUserMoudle().getUser("Manage");
    if (!list.isEmpty()) {
      ob = new Object[list.size()];
      for (int i = 0; i < list.size(); i++) {
        User user = (User)list.get(i);
        if ("管理员".equals(user.getUsernmae())) {
          ob[0] = user;
        }
        else if (i == 0)
          ob[(i + 1)] = user;
        else {
          ob[i] = user;
        }
      }
    }

    return ob;
  }

  public static Object[] getStockAlarmTypes() {
    Options option1 = new Options("<", "库存不足");
    Options option2 = new Options(">", "库存过多");
    return new Object[] { option1, option2 };
  }

  public static boolean isequals(String text, String type) {
    OptionMoudle optionMoudle = MoudleContentFactry.getOptionMoudle();
    List list = optionMoudle.getOption(type);
    boolean is = false;
    if ((list != null) && (list.size() > 0)) {
      for (int i = 0; i < list.size(); i++) {
        Option option = (Option)list.get(i);
        if (text.equals(option.getText())) {
          is = true;
          break;
        }
      }
    }
    return is;
  }
}