package com.bluebee.ui;

import com.bluebee.Constant;
import com.bluebee.SysEnv;
import com.bluebee.action.AboutAction;
import com.bluebee.action.ActionManager;
import com.bluebee.action.CgThAction;
import com.bluebee.action.CustomAction;
import com.bluebee.action.FlowAction;
import com.bluebee.action.GkJlAction;
import com.bluebee.action.GkThAction;
import com.bluebee.action.GuDZcPanelAction;
import com.bluebee.action.HelpAction;
import com.bluebee.action.KCStockAction;
import com.bluebee.action.OptionTypeAction;
import com.bluebee.action.PassSetAction;
import com.bluebee.action.PasswordAction;
import com.bluebee.action.RcZcAction;
import com.bluebee.action.SellHelperActoin;
import com.bluebee.action.SellOrderAction;
import com.bluebee.action.SettingAction;
import com.bluebee.action.StockAction;
import com.bluebee.action.SuppliersAction;
import com.bluebee.action.UpdateAction;
import com.bluebee.action.YyeTjAction;
import com.bluebee.action.ZcTjAction;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.UserRightMoudle;
import com.bluebee.pojo.UserRight;
import com.bluebee.ui.frame.AuthorityPanel;
import com.bluebee.ui.frame.CgthPane;
import com.bluebee.ui.frame.CurtainPane;
import com.bluebee.ui.frame.CustomPanel;
import com.bluebee.ui.frame.FlowPanel;
import com.bluebee.ui.frame.GkJLPanel;
import com.bluebee.ui.frame.GkthPane;
import com.bluebee.ui.frame.GuDZcPanel;
import com.bluebee.ui.frame.KuCTjPanel;
import com.bluebee.ui.frame.ListPane;
import com.bluebee.ui.frame.OptionsTypePanel;
import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.frame.RcZcPanel;
import com.bluebee.ui.frame.SellHelperPanel;
import com.bluebee.ui.frame.SellOrderpane;
import com.bluebee.ui.frame.SettiingPanel;
import com.bluebee.ui.frame.StockPanel;
import com.bluebee.ui.frame.YyeTjPanel;
import com.bluebee.ui.frame.ZcTjPanel;
import com.bluebee.ui.skin.ImageManager;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class NavigationMenu
{
  private static NavigationMenu navigationMenu = new NavigationMenu();

  public static NavigationMenu getInstance()
  {
    return navigationMenu;
  }

  public JPanel getMenu() {
    JPanel cutainPane = new JPanel(new BorderLayout());
    CurtainPane cp = getCurtainPane();
    cp.setAnimated(true);
    cp.setSelectedPane(0);
    cutainPane.add(cp, "Center");
    return cutainPane;
  }

  private CurtainPane getCurtainPane()
  {
    CurtainPane fp = new CurtainPane();
    fp.setAnimated(true);
    String longinname = SysEnv.getInstance().getLoginUser();
    String[] right = (String[])null;
    if ("10000".equals(longinname)) {
      right = Constant.RIGHT;
    } else {
      UserRightMoudle rightMoudle = MoudleContentFactry.getUserRightMoudle();
      UserRight userRight = rightMoudle.get(longinname);
      right = userRight.getRight().split(",");
    }
    boolean isxiaos = ("true".equals(right[0])) || ("true".equals(right[1])) || ("true".equals(right[2])) || ("true".equals(right[11]));
    if (isxiaos) {
      fp.addPane("销售", ImageManager.getImageIconByShortName("money_dollar.png"), getSellPane(right), isxiaos);
    }
    boolean isjinh = ("true".equals(right[3])) || ("true".equals(right[4])) || ((right.length == 16) && ("true".equals(right[15])));
    if (isjinh) {
      fp.addPane("进货", ImageManager.getImageIconByShortName("69.png"), getCgjhPane(right), isjinh);
    }
    boolean iszhicu = ("true".equals(right[5])) || ("true".equals(right[6])) || ((right.length == 17) && ("true".equals(right[16])));
    if (iszhicu) {
      fp.addPane("收支", ImageManager.getImageIconByShortName("coins_delete.png"), getZCPane(right), iszhicu);
    }
    boolean istongj = ("true".equals(right[7])) || ("true".equals(right[8])) || ("true".equals(right[9])) || 
      ("true".equals(right[10])) || (
      (right.length > 18) && ("true".equals(right[17])));
    if (istongj) {
      fp.addPane("统计", ImageManager.getImageIconByShortName("calculator.png"), getTJPane(right), istongj);
    }

    fp.addPane("设置", ImageManager.getImageIconByShortName("applications-system.png"), getSetinngPane(right), true);

    fp.addPane("帮助", ImageManager.getImageIconByShortName("help.png"), getHelpPane(), true);
    setDefaultPage(right);
    return fp;
  }

  private ListPane getSellPane(String[] right) {
    ListPane p = new ListPane();
    p.addItem("售货帮手", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(SellHelperActoin.class), "true".equals(right[0]));
    p.addItem("每天流水", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(FlowAction.class), "true".equals(right[1]));
    p.addItem("顾客退货", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(GkThAction.class), "true".equals(right[2]));
    p.addItem("客户资料", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(CustomAction.class), "true".equals(right[11]));

    p.setSize(185, 86);
    return p;
  }

  private ListPane getCgjhPane(String[] right)
  {
    ListPane p = new ListPane();
    p.addItem("采购进货", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(StockAction.class), "true".equals(right[3]));
    p.addItem("采购退货", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(CgThAction.class), "true".equals(right[4]));
    p.addItem("供应商资料", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(SuppliersAction.class), "true".equals(right[15]));
    p.setSize(185, 86);
    return p;
  }

  private ListPane getZCPane(String[] right) {
    ListPane p = new ListPane();
    p.addItem("日常收支", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(RcZcAction.class), "true".equals(right[5]));
    p.addItem("固定支出", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(GuDZcPanelAction.class), "true".equals(right[6]));

    p.setSize(185, 86);
    return p;
  }

  private ListPane getTJPane(String[] right) {
    ListPane p = new ListPane();
    p.addItem("库存统计", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(KCStockAction.class), "true".equals(right[7]));
    p.addItem("收支统计", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(ZcTjAction.class), "true".equals(right[8]));
    p.addItem("营业额统计", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(YyeTjAction.class), "true".equals(right[9]));
    p.addItem("销售排行统计", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(SellOrderAction.class), "true".equals(right[10]));
    p.addItem("客户购买统计", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(GkJlAction.class), "true".equals(right[17]));
    p.setSize(185, 86);
    return p;
  }

  private ListPane getSetinngPane(String[] right) {
    ListPane p = new ListPane();
    p.addItem("系统设置", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(SettingAction.class), "true".equals(right[12]));
    p.addItem("用户权限", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(PassSetAction.class), "true".equals(right[13]));
    p.addItem("密码修改", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(PasswordAction.class), true);
    p.addItem("类型设置", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(OptionTypeAction.class), "true".equals(right[14]));
    p.setSize(185, 86);
    return p;
  }

  private ListPane getHelpPane() {
	    ListPane p = new ListPane();
	    p.addItem("软件升级", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(UpdateAction.class), true);
	    p.addItem("如何使用", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(HelpAction.class), true);
	    p.addItem("BlueBee蓝蜜蜂", ImageManager.getImageIconByShortName("table_go.png"), ActionManager.getAction(AboutAction.class), true);
	    p.setSize(185, 86);
	    return p;
	  }
  private void setDefaultPage(String[] right)
  {
    if ("true".equals(right[0]))
      PageContainer.getInstance().setDefaultPage(SellHelperPanel.getInstance());
    else if ("true".equals(right[1])) {
      PageContainer.getInstance().setDefaultPage(FlowPanel.getInstance());
    }
    else if ("true".equals(right[2])) {
      PageContainer.getInstance().setDefaultPage(GkthPane.getInstance());
    }
    else if ("true".equals(right[3])) {
      PageContainer.getInstance().setDefaultPage(StockPanel.getInstance());
    }
    else if ("true".equals(right[4])) {
      PageContainer.getInstance().setDefaultPage(CgthPane.getInstance());
    }
    else if ("true".equals(right[5])) {
      PageContainer.getInstance().setDefaultPage(RcZcPanel.getInstance());
    }
    else if ("true".equals(right[6])) {
      PageContainer.getInstance().setDefaultPage(GuDZcPanel.getInstance());
    }
    else if ("true".equals(right[7])) {
      PageContainer.getInstance().setDefaultPage(KuCTjPanel.getInstance());
    }
    else if ("true".equals(right[8])) {
      PageContainer.getInstance().setDefaultPage(ZcTjPanel.getInstance());
    }
    else if ("true".equals(right[9])) {
      PageContainer.getInstance().setDefaultPage(YyeTjPanel.getInstance());
    }
    else if ("true".equals(right[10])) {
      PageContainer.getInstance().setDefaultPage(SellOrderpane.getInstance());
    }
    else if ("true".equals(right[11])) {
      PageContainer.getInstance().setDefaultPage(CustomPanel.getInstance());
    }
    else if ("true".equals(right[12])) {
      PageContainer.getInstance().setDefaultPage(SettiingPanel.getInstance());
    }
    else if ("true".equals(right[13])) {
      PageContainer.getInstance().setDefaultPage(AuthorityPanel.getInstance());
    }
    else if ("true".equals(right[14]))
      PageContainer.getInstance().setDefaultPage(OptionsTypePanel.getInstance());
    else if ("true".equals(right[15]))
      PageContainer.getInstance().setDefaultPage(GkJLPanel.getInstance());
    else if ("true".equals(right[15]))
      PageContainer.getInstance().setDefaultPage(GkJLPanel.getInstance());
  }
}