package com.bluebee.ui.frame;

import com.bluebee.moudle.FlowLogMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.ui.widget.SuggestTextField.SuggestData;

import java.util.List;

public class FlowSuggestDataImpl
  implements SuggestData
{
  public List<String> getData(String value)
  {
    FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();

    return flowLogMoudle.getFlowlog(value, 10);
  }
}