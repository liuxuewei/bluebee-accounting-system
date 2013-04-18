package com.bluebee.ui.frame;

import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.ui.widget.SuggestTextField.SuggestData;

import java.util.List;

public class StockSuggestDataImpl
  implements SuggestData
{
  public List<String> getData(String value)
  {
    StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();

    return stockMoudle.getStockSuggestByCatNo(value, 10);
  }
}