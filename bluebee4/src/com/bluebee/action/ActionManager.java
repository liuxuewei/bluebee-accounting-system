package com.bluebee.action;

import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;

public class ActionManager
{
  private static Map<Class<? extends Action>, Action> map = new HashMap();

  public static Action getAction(Class<? extends Action> clazz)
  {
    Action action = (Action)map.get(clazz);
    if (action == null) {
      try {
        action = (Action)clazz.newInstance();
        map.put(clazz, action);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return action;
  }

  protected static void error(Object obj)
  {
  }

  public static void setEnabledActions(boolean enable, Class<? extends Action>[] actions)
  {
    for (Class action : actions)
      getAction(action).setEnabled(enable);
  }
}