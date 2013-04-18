package com.bluebee.ui.frame;

import com.bluebee.Constant;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.UserMoudle;
import com.bluebee.moudle.UserRightMoudle;
import com.bluebee.pojo.User;
import com.bluebee.pojo.UserRight;
import com.bluebee.ui.widget.AuthorityDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class AuthorityPanel extends JPanel
  implements IPage
{
  private static AuthorityPanel authorityPanel = new AuthorityPanel();

  private boolean isDefaultPage = false;
  private JTable table;
  private JCheckBox xiaohelper;
  private JCheckBox flow;
  private JCheckBox guketh;
  private JCheckBox caigjh;
  private JCheckBox caigth;
  private JCheckBox riczc;
  private JCheckBox gudzc;
  private JCheckBox guctj;
  private JCheckBox zhictj;
  private JCheckBox yingyeetj;
  private JCheckBox xiaoshph;
  private JCheckBox kehzl;
  private JLabel label_9;
  private JCheckBox xtsz;
  private JCheckBox quanx;
  private JCheckBox leixsz;
  private JCheckBox gyszl;
  private JCheckBox rcsr;
  private JButton button;
  private JButton button_1;
  private JCheckBox kehugmtj;

  public static AuthorityPanel getInstance()
  {
    if (authorityPanel != null) {
      authorityPanel.init();
    }
    return authorityPanel;
  }

  public AuthorityPanel() {
    setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    add(panel, "West");
    panel.setLayout(new BorderLayout(0, 0));

    JPanel panel_4 = new JPanel();
    panel_4.setPreferredSize(new Dimension(250, 150));
    panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "用户设置", 4, 2, null, new Color(0, 70, 213)));
    panel.add(panel_4, "South");
    panel_4.setLayout(null);

    this.button = new JButton("增加");
    this.button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        AuthorityDialog authorityDialog = new AuthorityDialog(AuthorityPanel.authorityPanel, "增加用户", null, null, null, AuthorityPanel.this.table);
        authorityDialog.setVisible(true);
      }
    });
    this.button.setBounds(10, 36, 81, 31);
    panel_4.add(this.button);

    this.button_1 = new JButton("修改");
    this.button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        int srow = AuthorityPanel.this.table.getSelectedRow();
        if (srow > -1) {
          String id = String.valueOf(AuthorityPanel.this.table.getValueAt(srow, 0));
          String pass = String.valueOf(AuthorityPanel.this.table.getValueAt(srow, 1));
          String name = String.valueOf(AuthorityPanel.this.table.getValueAt(srow, 2));

          AuthorityDialog authorityDialog = new AuthorityDialog(AuthorityPanel.authorityPanel, "修改用户", id, name, pass, AuthorityPanel.this.table);
          authorityDialog.setVisible(true);
        }
      }
    });
    this.button_1.setBounds(136, 36, 81, 31);
    panel_4.add(this.button_1);

    JPanel panel_1 = new JPanel();
    panel_1.setBorder(new TitledBorder(null, "用户列表", 4, 2, null, null));
    panel.add(panel_1, "Center");
    panel_1.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();

    panel_1.add(scrollPane, "Center");

    this.table = new JTable();
    this.table.setPreferredScrollableViewportSize(new Dimension(122, 400));

    this.table.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "id", "password", "用户名", "删除" })
    {
      Class[] columnTypes = { 
        String.class, String.class, String.class, String.class };

      boolean[] columnEditables = new boolean[4];

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int nRow = AuthorityPanel.this.table.getSelectedRow();
        String id = String.valueOf(AuthorityPanel.this.table.getValueAt(nRow, 0));
        String pass = String.valueOf(AuthorityPanel.this.table.getValueAt(nRow, 1));
        String name = String.valueOf(AuthorityPanel.this.table.getValueAt(nRow, 2));
        int nCol = AuthorityPanel.this.table.getSelectedColumn();
        int c = e.getClickCount();
        if (c == 2) {
          AuthorityDialog authorityDialog = new AuthorityDialog(AuthorityPanel.authorityPanel, "修改用户", id, name, pass, AuthorityPanel.this.table);
          authorityDialog.setVisible(true);
        } else {
          Object objSel = AuthorityPanel.this.table.getValueAt(nRow, nCol);
          if ("删除".equals(String.valueOf(objSel))) {
            AuthorityPanel.this.clickedTable();
          }
          else if ("管理员".equals(name)) {
            String[] rights = Constant.RIGHT;
            AuthorityPanel.this.seletAll(rights);
          } else {
            UserRightMoudle userRightMoudle = MoudleContentFactry.getUserRightMoudle();
            UserRight userRight = userRightMoudle.get(id);
            if (userRight != null)
              AuthorityPanel.this.seletAll(userRight.getRight().split(","));
          }
        }
      }
    });
    this.table.getColumnModel().getColumn(0).setResizable(false);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(0).setMinWidth(0);
    this.table.getColumnModel().getColumn(0).setMaxWidth(0);
    this.table.getColumnModel().getColumn(1).setResizable(false);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(1).setMinWidth(0);
    this.table.getColumnModel().getColumn(1).setMaxWidth(0);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(157);
    this.table.getColumnModel().getColumn(2).setMaxWidth(157);
    this.table.getTableHeader().setReorderingAllowed(false);

    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(0);
    this.table.getTableHeader().setReorderingAllowed(false);

    TableColumn tcs = this.table.getColumn("删除");
    tcs.setCellRenderer(renderer);
    this.table.setSelectionMode(0);
    this.table.getSelectionModel().setSelectionInterval(0, 0);
    scrollPane.setViewportView(this.table);

    JPanel panel_2 = new JPanel();
    panel_2.setPreferredSize(new Dimension(10, 50));
    add(panel_2, "North");
    panel_2.setLayout(null);

    JLabel label_1 = new JLabel("用户权限：可以设置不同用户使用功能。");
    label_1.setBounds(31, 10, 567, 30);
    panel_2.add(label_1);

    JPanel panel_3 = new JPanel();
    panel_3.setBorder(new TitledBorder(null, "操作权限", 4, 2, null, null));
    add(panel_3, "Center");
    panel_3.setLayout(null);

    JLabel label_2 = new JLabel("销售");
    label_2.setBounds(42, 26, 54, 15);
    panel_3.add(label_2);

    this.xiaohelper = new JCheckBox("销售帮手");
    this.xiaohelper.setBounds(42, 47, 103, 23);
    panel_3.add(this.xiaohelper);

    this.flow = new JCheckBox("每天流水账");
    this.flow.setBounds(163, 47, 103, 23);
    panel_3.add(this.flow);

    this.guketh = new JCheckBox("顾客退货");
    this.guketh.setBounds(275, 47, 103, 23);
    panel_3.add(this.guketh);

    JLabel label_3 = new JLabel("进货");
    label_3.setBounds(42, 101, 54, 15);
    panel_3.add(label_3);

    this.caigjh = new JCheckBox("采购进货");
    this.caigjh.setBounds(42, 122, 103, 23);
    panel_3.add(this.caigjh);

    this.caigth = new JCheckBox("采购退货");
    this.caigth.setBounds(163, 122, 103, 23);
    panel_3.add(this.caigth);

    JLabel label_5 = new JLabel("支出");
    label_5.setBounds(42, 151, 54, 15);
    panel_3.add(label_5);
    this.riczc = new JCheckBox("日常支出");
    this.riczc.setBounds(42, 172, 103, 23);
    panel_3.add(this.riczc);

    this.gudzc = new JCheckBox("固定支出");
    this.gudzc.setBounds(163, 172, 103, 23);
    panel_3.add(this.gudzc);

    JLabel label_6 = new JLabel("统计");
    label_6.setBounds(42, 201, 54, 15);
    panel_3.add(label_6);

    this.guctj = new JCheckBox("库存统计");
    this.guctj.setBounds(42, 222, 103, 23);
    panel_3.add(this.guctj);

    this.zhictj = new JCheckBox("收支统计");
    this.zhictj.setBounds(163, 222, 103, 23);
    panel_3.add(this.zhictj);

    this.yingyeetj = new JCheckBox("营业额统计");
    this.yingyeetj.setBounds(275, 222, 92, 23);
    panel_3.add(this.yingyeetj);

    this.xiaoshph = new JCheckBox("销售排行统计");
    this.xiaoshph.setBounds(42, 247, 103, 23);
    panel_3.add(this.xiaoshph);

    this.kehzl = new JCheckBox("客户资料");
    this.kehzl.setBounds(42, 72, 103, 23);
    panel_3.add(this.kehzl);

    this.label_9 = new JLabel("设置");
    this.label_9.setBounds(42, 276, 54, 15);
    panel_3.add(this.label_9);

    this.xtsz = new JCheckBox("系统设置");
    this.xtsz.setBounds(42, 297, 103, 23);
    panel_3.add(this.xtsz);

    this.quanx = new JCheckBox("用户权限");
    this.quanx.setBounds(163, 297, 103, 23);
    panel_3.add(this.quanx);

    this.leixsz = new JCheckBox("类型设置");
    this.leixsz.setBounds(275, 297, 103, 23);
    panel_3.add(this.leixsz);
    this.kehugmtj = new JCheckBox("客户购买统计");
    this.kehugmtj.setBounds(163, 247, 115, 25);
    panel_3.add(this.kehugmtj);

    this.rcsr = new JCheckBox("日常收入");
    this.rcsr.setBounds(275, 172, 103, 23);
    panel_3.add(this.rcsr);

    this.gyszl = new JCheckBox("供应商资料");
    this.gyszl.setBounds(275, 122, 103, 23);
    panel_3.add(this.gyszl);
  }

  private void init() {
    clearTABLE();
    UserMoudle userMoudle = MoudleContentFactry.getUserMoudle();
    List list = userMoudle.getUser("Manage");
    if (!list.isEmpty()) {
      User t = null;
      for (int i = 0; i < list.size(); i++) {
        User user = (User)list.get(i);
        if (user.getUsernmae().equals("管理员"))
          t = user;
        else {
          insertRow(user, i);
        }
      }

      insertRow(t, 0);
      this.table.setSelectionMode(0);
      this.table.getSelectionModel().setSelectionInterval(0, 0);
      String[] rights = Constant.RIGHT;
      seletAll(rights);
      seletAllEnabled(false);
    }
  }

  public void clearTABLE() {
    DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
    if (tableModel.getRowCount() > 0) {
      int rows = tableModel.getRowCount();
      for (int i = 0; i < rows; i++)
        tableModel.removeRow(0);
    }
  }

  public void clickedTable()
  {
    int nCol = this.table.getSelectedColumn();
    int nRow = this.table.getSelectedRow();
    Object objSel = this.table.getValueAt(nRow, nCol);
    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table.getModel();
    if ((objSel != null) && ((objSel instanceof String)) && 
      ("删除".equals(String.valueOf(objSel)))) {
      int response = JOptionPane.showConfirmDialog(null, "确定删除此条数据?", 
    		  "删除用户数据", 0);
      switch (response) {
      case 0:
        String id = String.valueOf(defaultTableModel.getValueAt(
          nRow, 0));

        UserMoudle userMoudle = 
          MoudleContentFactry.getUserMoudle();
        userMoudle.deleteUser(id);
        UserRightMoudle rightMoudle = MoudleContentFactry.getUserRightMoudle();
        rightMoudle.delete(id);
        defaultTableModel.removeRow(nRow);
      case -1:
      case 1:
      }
    }
  }

  private void insertRow(User user, int row)
  {
    DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();

    if (user.getUsernmae().equals("管理员")) {
      Object[] rewdata = { 
        user.getId(), 
        user.getPassword(), 
        user.getUsernmae(), 
        "" };

      tableModel.insertRow(0, rewdata);
    } else {
      Object[] rewdata = { 
        user.getId(), 
        user.getPassword(), 
        user.getUsernmae(), 
        "删除" };

      tableModel.insertRow(this.table.getRowCount(), rewdata);
    }
  }

  private void seletAll(String[] aa) {
    if (aa != null)
    {
      this.xiaohelper.setSelected(aa[0].equals("true"));
      this.flow.setSelected(aa[1].equals("true"));
      this.guketh.setSelected(aa[2].equals("true"));
      this.caigjh.setSelected(aa[3].equals("true"));
      this.caigth.setSelected(aa[4].equals("true"));
      this.riczc.setSelected(aa[5].equals("true"));
      this.gudzc.setSelected(aa[6].equals("true"));
      this.guctj.setSelected(aa[7].equals("true"));
      this.zhictj.setSelected(aa[8].equals("true"));
      this.yingyeetj.setSelected(aa[9].equals("true"));
      this.xiaoshph.setSelected(aa[10].equals("true"));
      this.kehzl.setSelected(aa[11].equals("true"));
      this.xtsz.setSelected(aa[12].equals("true"));
      this.quanx.setSelected(aa[13].equals("true"));
      this.leixsz.setSelected(aa[14].equals("true"));

      if (aa.length == 16) {
        this.gyszl.setSelected(aa[15].equals("true"));
      }
      if (aa.length == 17) {
        this.rcsr.setSelected(aa[16].equals("true"));
      }
      if (aa.length == 18)
        this.kehugmtj.setSelected(aa[17].equals("true"));
    }
  }

  private void seletAllEnabled(boolean isEnabled)
  {
    this.xiaohelper.setEnabled(isEnabled);
    this.flow.setEnabled(isEnabled);
    this.guketh.setEnabled(isEnabled);
    this.caigjh.setEnabled(isEnabled);
    this.caigth.setEnabled(isEnabled);
    this.riczc.setEnabled(isEnabled);
    this.gudzc.setEnabled(isEnabled);
    this.guctj.setEnabled(isEnabled);
    this.zhictj.setEnabled(isEnabled);
    this.yingyeetj.setEnabled(isEnabled);
    this.xiaoshph.setEnabled(isEnabled);
    this.kehzl.setEnabled(isEnabled);
    this.xtsz.setEnabled(isEnabled);
    this.quanx.setEnabled(isEnabled);
    this.leixsz.setEnabled(isEnabled);
    this.gyszl.setEnabled(isEnabled);
    this.rcsr.setEnabled(isEnabled);
    this.kehugmtj.setEnabled(isEnabled);
  }

  private void clear()
  {
  }

  public void disposePage()
  {
  }

  public String getPageId() {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>用户权限";
  }

  public boolean isDefaultPage()
  {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool)
  {
    this.isDefaultPage = bool;
  }
}