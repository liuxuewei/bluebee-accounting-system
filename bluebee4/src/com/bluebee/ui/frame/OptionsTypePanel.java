package com.bluebee.ui.frame;

import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.OptionMoudle;
import com.bluebee.pojo.Option;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class OptionsTypePanel extends JPanel
  implements IPage
{
  private static OptionsTypePanel optionsTypePanel = new OptionsTypePanel();
  private boolean isDefaultPage;
  private JTabbedPane tabbedPane;
  private JTextField textField_1;
  private JTable table_1;
  private JTextField textField_2;
  private JTable table_2;
  private JTable table;
  private JTextField textField;

  public static OptionsTypePanel getInstance()
  {
    if (optionsTypePanel != null) {
      optionsTypePanel.initData();
    }
    return optionsTypePanel;
  }

  public OptionsTypePanel()
  {
    setLayout(new BorderLayout(0, 0));

    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    this.tabbedPane.setRequestFocusEnabled(false);
    add(this.tabbedPane, "Center");

    JPanel panel = new JPanel();
    ImageIcon imageIcon1 = new ImageIcon(OptionsTypePanel.class.getResource("/com/bluebee/resource/image/tab.png"));
    this.tabbedPane.addTab("货物类型", imageIcon1, panel, null);
    panel.setLayout(new BorderLayout(0, 0));

    JPanel panel_2 = new JPanel();
    panel.add(panel_2, "North");
    GridBagLayout gbl_panel_2 = new GridBagLayout();
    gbl_panel_2.columnWidths = new int[] { 83, 0, 149 };
    gbl_panel_2.rowHeights = new int[] { 42 };
    gbl_panel_2.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_2.rowWeights = new double[] { 0.0D, 4.9E-324D };
    panel_2.setLayout(gbl_panel_2);

    JLabel label = new JLabel("货物类型");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 0, 5);
    gbc_label.anchor = 13;
    gbc_label.gridx = 1;
    gbc_label.gridy = 0;
    panel_2.add(label, gbc_label);

    this.textField_1 = new JTextField();
    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
    gbc_textField_1.insets = new Insets(0, 0, 0, 5);
    gbc_textField_1.fill = 2;
    gbc_textField_1.gridx = 2;
    gbc_textField_1.gridy = 0;
    panel_2.add(this.textField_1, gbc_textField_1);
    this.textField_1.setColumns(10);

    JButton button = new JButton("   保 存   ");
    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        OptionsTypePanel.this.submmit_1();
      }
    });
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.gridx = 3;
    gbc_button.gridy = 0;
    panel_2.add(button, gbc_button);

    JScrollPane scrollPane = new JScrollPane();
    panel.add(scrollPane, "Center");

    this.table_1 = new JTable();
    this.table_1.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "ID", "货物类型名称", "操作" })
    {
      Class[] columnTypes = { 
        String.class, String.class, String.class };

      boolean[] columnEditables = new boolean[3];

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table_1.getColumnModel().getColumn(0).setResizable(false);
    this.table_1.getColumnModel().getColumn(0).setPreferredWidth(110);
    this.table_1.getColumnModel().getColumn(0).setMaxWidth(200);
    this.table_1.getColumnModel().getColumn(1).setResizable(false);
    this.table_1.getColumnModel().getColumn(1).setPreferredWidth(419);
    this.table_1.getColumnModel().getColumn(1).setMinWidth(50);
    this.table_1.getColumnModel().getColumn(1).setMaxWidth(1222490);
    this.table_1.getColumnModel().getColumn(2).setResizable(false);
    this.table_1.getColumnModel().getColumn(2).setMaxWidth(75);
    this.table_1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        OptionsTypePanel.this.clickedTable("确定删除此条数据?", "删除货物类型数据", OptionsTypePanel.this.table_1);
      }
    });
    this.table_1.getTableHeader().setReorderingAllowed(false);
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(0);
    TableColumn tc = this.table_1.getColumn("操作");
    tc.setPreferredWidth(60);
    tc.setCellRenderer(renderer);

    scrollPane.setViewportView(this.table_1);

    JPanel panel_1 = new JPanel();
    this.tabbedPane.addTab("支出项目", imageIcon1, panel_1, null);
    panel_1.setLayout(new BorderLayout(0, 0));

    JPanel panel_3 = new JPanel();
    panel_1.add(panel_3, "North");
    GridBagLayout gbl_panel_3 = new GridBagLayout();
    gbl_panel_3.columnWidths = new int[] { 83, 0, 148 };
    gbl_panel_3.rowHeights = new int[] { 42 };
    gbl_panel_3.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_3.rowWeights = new double[] { 0.0D, 4.9E-324D };
    panel_3.setLayout(gbl_panel_3);

    JLabel label_1 = new JLabel("支出类型");
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.insets = new Insets(0, 0, 0, 5);
    gbc_label_1.anchor = 13;
    gbc_label_1.gridx = 1;
    gbc_label_1.gridy = 0;
    panel_3.add(label_1, gbc_label_1);

    this.textField_2 = new JTextField();
    GridBagConstraints gbc_textField_2 = new GridBagConstraints();
    gbc_textField_2.insets = new Insets(0, 0, 0, 5);
    gbc_textField_2.fill = 2;
    gbc_textField_2.gridx = 2;
    gbc_textField_2.gridy = 0;
    panel_3.add(this.textField_2, gbc_textField_2);
    this.textField_2.setColumns(10);

    JButton button_1 = new JButton("   保 存   ");
    button_1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
      }
    });
    button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        OptionsTypePanel.this.submmit_2();
      }
    });
    GridBagConstraints gbc_button_1 = new GridBagConstraints();
    gbc_button_1.gridx = 3;
    gbc_button_1.gridy = 0;
    panel_3.add(button_1, gbc_button_1);

    JScrollPane scrollPane_1 = new JScrollPane();
    panel_1.add(scrollPane_1, "Center");

    this.table_2 = new JTable();
    this.table_2.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "ID", "支出类型名称", "操作" })
    {
      Class[] columnTypes = { 
        String.class, String.class, String.class };

      boolean[] columnEditables = new boolean[3];

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table_2.getColumnModel().getColumn(0).setResizable(false);
    this.table_2.getColumnModel().getColumn(0).setPreferredWidth(110);
    this.table_2.getColumnModel().getColumn(0).setMinWidth(80);
    this.table_2.getColumnModel().getColumn(0).setMaxWidth(200);
    this.table_2.getColumnModel().getColumn(1).setResizable(false);
    this.table_2.getColumnModel().getColumn(1).setPreferredWidth(403);
    this.table_2.getColumnModel().getColumn(2).setResizable(false);
    this.table_2.getColumnModel().getColumn(2).setPreferredWidth(60);
    this.table_2.getColumnModel().getColumn(2).setMinWidth(60);
    this.table_2.getColumnModel().getColumn(2).setMaxWidth(60);
    this.table_2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        OptionsTypePanel.this.clickedTable("确定删除此条数据?", "删除支出类型数据", OptionsTypePanel.this.table_2);
      }
    });
    this.table_2.getTableHeader().setReorderingAllowed(false);

    TableColumn tcs = this.table_2.getColumn("操作");
    tcs.setPreferredWidth(60);
    tcs.setCellRenderer(renderer);
    scrollPane_1.setViewportView(this.table_2);

    JPanel panel_4 = new JPanel();
    this.tabbedPane.addTab("收入项目", imageIcon1, panel_4, null);
    panel_4.setLayout(new BorderLayout(0, 0));

    JPanel panel_5 = new JPanel();
    panel_4.add(panel_5, "North");
    GridBagLayout gbl_panel_5 = new GridBagLayout();
    gbl_panel_5.columnWidths = new int[] { 83 };
    gbl_panel_5.rowHeights = new int[] { 42 };
    gbl_panel_5.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_5.rowWeights = new double[] { 0.0D, 4.9E-324D };
    panel_5.setLayout(gbl_panel_5);

    JLabel label_2 = new JLabel("收入类型");
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.insets = new Insets(0, 0, 0, 5);
    gbc_label_2.anchor = 13;
    gbc_label_2.gridx = 1;
    gbc_label_2.gridy = 0;
    panel_5.add(label_2, gbc_label_2);

    this.textField = new JTextField();
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.insets = new Insets(0, 0, 0, 5);
    gbc_textField.fill = 2;
    gbc_textField.gridx = 2;
    gbc_textField.gridy = 0;
    panel_5.add(this.textField, gbc_textField);
    this.textField.setColumns(10);

    JButton button_2 = new JButton("   保 存   ");
    button_2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        OptionsTypePanel.this.submmit_3();
      }
    });
    GridBagConstraints gbc_button_2 = new GridBagConstraints();
    gbc_button_2.gridx = 3;
    gbc_button_2.gridy = 0;
    panel_5.add(button_2, gbc_button_2);

    JScrollPane scrollPane_2 = new JScrollPane();
    panel_4.add(scrollPane_2, "Center");

    this.table = new JTable();
    this.table.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "ID", "类型", "操作" })
    {
      boolean[] columnEditables = new boolean[3];

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table.getColumnModel().getColumn(0).setResizable(false);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(110);
    this.table.getColumnModel().getColumn(0).setMinWidth(80);
    this.table.getColumnModel().getColumn(0).setMaxWidth(200);
    this.table.getColumnModel().getColumn(1).setResizable(false);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(403);
    this.table.getColumnModel().getColumn(2).setResizable(false);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(60);
    this.table.getColumnModel().getColumn(2).setMinWidth(60);
    this.table.getColumnModel().getColumn(2).setMaxWidth(60);
    this.table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        OptionsTypePanel.this.clickedTable("确定删除此条数据?", "删除收入类型数据", OptionsTypePanel.this.table);
      }
    });
    this.table.getTableHeader().setReorderingAllowed(false);
    TableColumn tcss = this.table.getColumn("操作");
    tcss.setPreferredWidth(60);
    tcss.setCellRenderer(renderer);
    scrollPane_2.setViewportView(this.table);
  }

  private void initData()
  {
    clear();
    OptionMoudle optionMoudle = MoudleContentFactry.getOptionMoudle();
    List list = optionMoudle.getOption();
    if ((list != null) && (list.size() > 0)) {
      DefaultTableModel tableModel = (DefaultTableModel)this.table_1.getModel();
      DefaultTableModel tableModel_2 = (DefaultTableModel)this.table_2.getModel();
      DefaultTableModel tableModel_3 = (DefaultTableModel)this.table.getModel();
      for (int i = 0; i < list.size(); i++) {
        Option option = (Option)list.get(i);
        if ("HWLX".equals(option.getType())) {
          Object[] rowData = { option.getId(), option.getText(), "删除" };
          tableModel.insertRow(0, rowData);
        } else if ("ZCLX".equals(option.getType())) {
          Object[] rowData = { option.getId(), option.getText(), "删除" };
          tableModel_2.insertRow(0, rowData);
        } else if ("SRLX".equals(option.getType())) {
          Object[] rowData = { option.getId(), option.getText(), "删除" };
          tableModel_3.insertRow(0, rowData);
        }
      }
    }
  }

  private void submmit_1()
  {
    if (this.textField_1.getText().trim().length() > 0) {
      String text = this.textField_1.getText();
      OptionMoudle optionMoudle = MoudleContentFactry.getOptionMoudle();
      Option option = new Option();
      option.setId(String.valueOf(System.currentTimeMillis()));
      option.setType("HWLX");
      option.setText(text);
      boolean isd = optionMoudle.addOption(option);
      if (isd) {
        DefaultTableModel tableModel = (DefaultTableModel)this.table_1.getModel();
        Object[] rowData = { option.getId(), option.getText(), "删除" };
        tableModel.insertRow(0, rowData);
      }
    }
  }

  private void submmit_2() {
    if (this.textField_2.getText().trim().length() > 0) {
      String text = this.textField_2.getText();
      OptionMoudle optionMoudle = MoudleContentFactry.getOptionMoudle();
      Option option = new Option();
      option.setId(String.valueOf(System.currentTimeMillis()));
      option.setType("ZCLX");
      option.setText(text);
      boolean isd = optionMoudle.addOption(option);
      if (isd) {
        DefaultTableModel tableModel = (DefaultTableModel)this.table_2.getModel();
        Object[] rowData = { option.getId(), option.getText(), "删除" };
        tableModel.insertRow(0, rowData);
      }
    }
  }

  private void submmit_3() {
    if (this.textField.getText().trim().length() > 0) {
      String text = this.textField.getText();
      OptionMoudle optionMoudle = MoudleContentFactry.getOptionMoudle();
      Option option = new Option();
      option.setId(String.valueOf(System.currentTimeMillis()));
      option.setType("SRLX");
      option.setText(text);
      boolean isd = optionMoudle.addOption(option);
      if (isd) {
        DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
        Object[] rowData = { option.getId(), option.getText(), "删除" };
        tableModel.insertRow(0, rowData);
      }
    }
  }

  private void clear() {
    DefaultTableModel tableModel = (DefaultTableModel)this.table_1.getModel();
    if (tableModel.getRowCount() > 0) {
      int rows = tableModel.getRowCount();
      for (int i = 0; i < rows; i++) {
        tableModel.removeRow(0);
      }
    }
    DefaultTableModel tableModel2 = (DefaultTableModel)this.table_2.getModel();
    if (tableModel2.getRowCount() > 0) {
      int rows = tableModel2.getRowCount();
      for (int i = 0; i < rows; i++)
        tableModel2.removeRow(0);
    }
  }

  private void clickedTable(String message, String title, JTable tableObj)
  {
    int nCol = tableObj.getSelectedColumn();
    int nRow = tableObj.getSelectedRow();
    Object objSel = tableObj.getValueAt(nRow, nCol);

    if ((objSel != null) && ((objSel instanceof String)) && 
      ("删除".equals(String.valueOf(objSel)))) {
      int response = JOptionPane.showConfirmDialog(null, message, 
        title, 0);
      switch (response) {
      case 0:
        DefaultTableModel tableModel = (DefaultTableModel)tableObj.getModel();
        String catno = String.valueOf(tableModel.getValueAt(nRow, 0));
        OptionMoudle optionMoudle = MoudleContentFactry.getOptionMoudle();
        boolean isd = optionMoudle.deleteOption(catno);
        if (isd)
          tableModel.removeRow(nRow);
        break;
      case -1:
      case 1:
      }
    }
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>类型设置";
  }

  public void disposePage()
  {
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