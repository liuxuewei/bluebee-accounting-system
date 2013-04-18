package com.bluebee.ui.frame;

import com.bluebee.moudle.CustomMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Custom;
import com.bluebee.ui.widget.SuggestTextField.SuggestData;

import java.util.ArrayList;
import java.util.List;

public class CustomSuggestDataImpl
  implements SuggestData
{
  public List<String> getData(String value)
  {
    CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();

    List list = customMoudle.getCustoms(value, 10);
    if (list != null) {
      ArrayList s = new ArrayList();
      for (int i = 0; i < list.size(); i++) {
        Custom custom = (Custom)list.get(i);
        s.add(custom.toString());
      }
      list = s;
    }

    return list;
  }
}