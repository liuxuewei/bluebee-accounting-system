package com.bluebee.ui.frame;

import com.bluebee.moudle.DailyExpensesMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.OptionMoudle;
import com.bluebee.pojo.DailyExpenses;
import com.bluebee.pojo.Option;
import com.bluebee.ui.LimitedDocument;
import com.bluebee.ui.widget.SelectType;
import com.bluebee.util.DateHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXDatePicker;

public class RcZcPanel extends JPanel
  implements IPage
{
  private static RcZcPanel rcZcPanel = new RcZcPanel();

  private boolean isDefaultPage = false;
  private JTextField textField;
  private JComboBox comboBox;
  private JXDatePicker datePicker;
  private final JPanel panel_2 = new JPanel();
  private JLabel label_7;
  private JLabel label_8;
  private String dubString = "1234567890.";
  private JLabel label_6;
  private JCheckBox zhichu;
  private JCheckBox shouru;
  private JTable table;
  private JTable table_1;
  private JLabel label_9;
  private JTabbedPane tabbedPane;

  public static RcZcPanel getInstance()
  {
    if (rcZcPanel != null) {
      DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel)rcZcPanel.comboBox.getModel();
      if (comboBoxModel.getSize() > 0) {
        comboBoxModel.removeAllElements();
      }
      rcZcPanel.comboBox.setModel(new DefaultComboBoxModel(SelectType.getOptions("ZCLX")));
      if (rcZcPanel.comboBox.getItemCount() > 0) {
        rcZcPanel.comboBox.setSelectedIndex(0);
      }

      JComboBox comboBox1 = new JComboBox();
      comboBox1.setModel(new DefaultComboBoxModel(SelectType.getOptions("ZCLX")));
      rcZcPanel.table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox1));
    }
    return rcZcPanel;
  }

  private RcZcPanel() {
    setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "录入收支", 4, 2, null, null));
    add(panel, "North");
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[] { 37, 0, 50, 37, 100, 46, 0, 0, 96 };
    gbl_panel.rowHeights = new int[] { 27, 0, 34 };
    gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel.setLayout(gbl_panel);

    this.label_7 = new JLabel("");
    this.label_7.setForeground(Color.RED);
    this.label_7.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_label_7 = new GridBagConstraints();
    gbc_label_7.anchor = 17;
    gbc_label_7.gridwidth = 7;
    gbc_label_7.insets = new Insets(0, 0, 5, 5);
    gbc_label_7.gridx = 1;
    gbc_label_7.gridy = 0;
    panel.add(this.label_7, gbc_label_7);
    this.zhichu = new JCheckBox("支出");
    this.zhichu.setSelected(true);
    this.zhichu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        RcZcPanel.this.tabbedPane.setSelectedIndex(0);
        RcZcPanel.this.shouru.setSelected(false);
        RcZcPanel.this.zhichu.setSelected(true);
        RcZcPanel.this.comboBox.setModel(new DefaultComboBoxModel(SelectType.getOptions("ZCLX")));
      }
    });
    GridBagConstraints gbc_checkBox = new GridBagConstraints();
    gbc_checkBox.anchor = 17;
    gbc_checkBox.insets = new Insets(0, 0, 5, 5);
    gbc_checkBox.gridx = 2;
    gbc_checkBox.gridy = 1;
    panel.add(this.zhichu, gbc_checkBox);

    JLabel label_t = new JLabel("时间");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.anchor = 13;
    gbc_label.insets = new Insets(0, 0, 5, 5);
    gbc_label.gridx = 3;
    gbc_label.gridy = 1;
    panel.add(label_t, gbc_label);
    gbc_label.gridx = 7;
    gbc_label.gridy = 0;

    this.datePicker = new JXDatePicker();
    this.datePicker.getEditor().setEditable(false);
    this.datePicker.getEditor().setFont(new Font("宋体", 1, 12));
    this.datePicker.getEditor().setColumns(10);
    this.datePicker.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker.setDate(DateHelper.currentDate());
    GridBagConstraints gbc_datePicker = new GridBagConstraints();
    gbc_datePicker.fill = 2;
    gbc_datePicker.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker.gridx = 4;
    gbc_datePicker.gridy = 1;
    panel.add(this.datePicker, gbc_datePicker);

    JLabel label_2 = new JLabel("金额");
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.insets = new Insets(0, 0, 5, 5);
    gbc_label_2.anchor = 13;
    gbc_label_2.gridx = 5;
    gbc_label_2.gridy = 1;
    panel.add(label_2, gbc_label_2);

    this.textField = new JTextField();
    this.textField.setDocument(new LimitedDocument(30, this.dubString));
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.insets = new Insets(0, 0, 5, 5);
    gbc_textField.fill = 2;
    gbc_textField.gridx = 6;
    gbc_textField.gridy = 1;
    panel.add(this.textField, gbc_textField);
    this.textField.setColumns(10);

    this.shouru = new JCheckBox("收入");

    this.shouru.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        RcZcPanel.this.shouru.setSelected(true);
        RcZcPanel.this.zhichu.setSelected(false);
        RcZcPanel.this.tabbedPane.setSelectedIndex(1);
        RcZcPanel.this.comboBox.setModel(new DefaultComboBoxModel(SelectType.getOptions("SRLX")));
      }
    });
    GridBagConstraints gbc_checkBox_1 = new GridBagConstraints();
    gbc_checkBox_1.anchor = 17;
    gbc_checkBox_1.insets = new Insets(0, 0, 0, 5);
    gbc_checkBox_1.gridx = 2;
    gbc_checkBox_1.gridy = 2;
    panel.add(this.shouru, gbc_checkBox_1);

    JLabel label_1 = new JLabel("项目");
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.anchor = 13;
    gbc_label_1.insets = new Insets(0, 0, 0, 5);
    gbc_label_1.gridx = 3;
    gbc_label_1.gridy = 2;
    panel.add(label_1, gbc_label_1);

    this.comboBox = new JComboBox();
    this.comboBox.setMaximumSize(new Dimension(27, 21));
    this.comboBox.setMinimumSize(new Dimension(27, 21));
    this.comboBox.setEditable(true);

    GridBagConstraints gbc_comboBox = new GridBagConstraints();
    gbc_comboBox.fill = 2;
    gbc_comboBox.insets = new Insets(0, 0, 0, 5);
    gbc_comboBox.gridx = 4;
    gbc_comboBox.gridy = 2;
    panel.add(this.comboBox, gbc_comboBox);

    JButton button = new JButton("  保存  ");

    button.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        RcZcPanel.this.submmit();
      }
    });
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.insets = new Insets(0, 0, 0, 5);
    gbc_button.anchor = 13;
    gbc_button.gridx = 6;
    gbc_button.gridy = 2;
    panel.add(button, gbc_button);

    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    add(this.tabbedPane, "Center");

    JPanel panel_1 = new JPanel();
    panel_1.setBorder(null);

    ImageIcon imageIcon1 = new ImageIcon(OptionsTypePanel.class.getResource("/com/bluebee/resource/image/zhichu.png"));
    this.tabbedPane.addTab("支出", imageIcon1, panel_1, null);
    panel_1.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBorder(null);
    panel_1.add(scrollPane, "Center");

    this.table = new JTable();
    this.table.setBorder(null);
    this.table.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "id", "支出日期", "支出项目", "支出金额", "操作" })
    {
      boolean[] columnEditables = new boolean[5];

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        RcZcPanel.this.clickedTable("确定删除此条数据?", "删除支出数据", RcZcPanel.this.table);
      }
    });
    this.table.getColumnModel().getColumn(0).setResizable(false);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(0).setMinWidth(0);
    this.table.getColumnModel().getColumn(0).setMaxWidth(0);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(257);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(107);
    this.table.getColumnModel().getColumn(4).setPreferredWidth(60);
    this.table.getColumnModel().getColumn(4).setMaxWidth(60);
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(0);
    TableColumn tc = this.table.getColumn("操作");
    tc.setPreferredWidth(60);
    tc.setCellRenderer(renderer);
    scrollPane.setViewportView(this.table);

    JPanel panel_3 = new JPanel();
    panel_3.setBorder(null);
    ImageIcon imageIcon2 = new ImageIcon(OptionsTypePanel.class.getResource("/com/bluebee/resource/image/shouru.png"));
    this.tabbedPane.addTab("收入", imageIcon2, panel_3, null);
    panel_3.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBorder(null);
    panel_3.add(scrollPane_1, "Center");

    this.table_1 = new JTable();
    this.table_1.setBorder(null);
    this.table_1.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "id", "收入日期", "收入项目", "收入金额", "操作" })
    {
      boolean[] columnEditables = new boolean[5];

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table_1.getColumnModel().getColumn(0).setResizable(false);
    this.table_1.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table_1.getColumnModel().getColumn(0).setMinWidth(0);
    this.table_1.getColumnModel().getColumn(0).setMaxWidth(0);
    this.table_1.getColumnModel().getColumn(2).setPreferredWidth(259);
    this.table_1.getColumnModel().getColumn(3).setPreferredWidth(107);
    this.table_1.getColumnModel().getColumn(4).setPreferredWidth(60);
    this.table_1.getColumnModel().getColumn(4).setMaxWidth(60);
    this.table_1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        RcZcPanel.this.clickedTable("确定删除此条数据?", "删除收入数据", RcZcPanel.this.table);
      }
    });
    TableColumn tcs = this.table_1.getColumn("操作");
    tcs.setPreferredWidth(60);
    tcs.setCellRenderer(renderer);
    scrollPane_1.setViewportView(this.table_1);
    this.panel_2.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    add(this.panel_2, "South");
    GridBagLayout gbl_panel_2 = new GridBagLayout();
    gbl_panel_2.columnWidths = new int[] { 0, 53, 0, 0, 72, 0, 70, 104 };
    gbl_panel_2.rowHeights = new int[2];
    gbl_panel_2.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_2.rowWeights = new double[] { 0.0D, 4.9E-324D };
    this.panel_2.setLayout(gbl_panel_2);

    JLabel label_3 = new JLabel("时间：");
    GridBagConstraints gbc_label_3 = new GridBagConstraints();
    gbc_label_3.insets = new Insets(0, 0, 0, 5);
    gbc_label_3.gridx = 0;
    gbc_label_3.gridy = 0;
    this.panel_2.add(label_3, gbc_label_3);

    JLabel label_4 = new JLabel("<html><u>" + DateHelper.getNowDateTime() + "</u></html>");
    label_4.setHorizontalAlignment(2);
    label_4.setVerticalAlignment(1);
    label_4.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_label_4 = new GridBagConstraints();
    gbc_label_4.anchor = 17;
    gbc_label_4.insets = new Insets(0, 0, 0, 5);
    gbc_label_4.gridx = 1;
    gbc_label_4.gridy = 0;
    this.panel_2.add(label_4, gbc_label_4);

    JLabel label_5 = new JLabel("支出总金额：");
    GridBagConstraints gbc_label_5 = new GridBagConstraints();
    gbc_label_5.insets = new Insets(0, 0, 0, 5);
    gbc_label_5.gridx = 3;
    gbc_label_5.gridy = 0;
    this.panel_2.add(label_5, gbc_label_5);

    this.label_6 = new JLabel("<html><u>0</u></html>");
    this.label_6.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_label_6 = new GridBagConstraints();
    gbc_label_6.insets = new Insets(0, 0, 0, 5);
    gbc_label_6.gridx = 4;
    gbc_label_6.gridy = 0;
    this.panel_2.add(this.label_6, gbc_label_6);

    JLabel label = new JLabel("收入总金额");
    GridBagConstraints gbc_label1 = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 0, 5);
    gbc_label.gridx = 5;
    gbc_label.gridy = 0;
    this.panel_2.add(label, gbc_label1);

    this.label_9 = new JLabel("0");
    GridBagConstraints gbc_label_9 = new GridBagConstraints();
    gbc_label_9.insets = new Insets(0, 0, 0, 5);
    gbc_label_9.gridx = 6;
    gbc_label_9.gridy = 0;
    this.panel_2.add(this.label_9, gbc_label_9);

    this.label_8 = new JLabel("");
    this.label_8.setFont(new Font("宋体", 1, 12));
    this.label_8.setForeground(Color.RED);
    GridBagConstraints gbc_label11 = new GridBagConstraints();
    gbc_label1.anchor = 17;
    gbc_label1.gridx = 7;
    gbc_label1.gridy = 0;
    this.panel_2.add(this.label_8, gbc_label11);
    initTadayFow();
  }

  private void submmit() {
    DailyExpenses dailyExpenses = getFormdata();
    if (dailyExpenses != null) {
      DailyExpensesMoudle dailyExpensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
      dailyExpensesMoudle.add(dailyExpenses);
      Object[] rowData = { 
        dailyExpenses.getId(), 
        dailyExpenses.getDate(), 
        dailyExpenses.getType(), 
        dailyExpenses.getPay(), 
        "删除" };

      if (this.zhichu.isSelected()) {
        JComboBox comboBox1 = new JComboBox();
        comboBox1.setModel(new DefaultComboBoxModel(SelectType.getOptions("ZCLX")));
        this.table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox1));
        DefaultTableCellRenderer renderer1 = 
          new DefaultTableCellRenderer();
        renderer1.setToolTipText("点击选择下列表");
        this.table.getColumnModel().getColumn(2).setCellRenderer(renderer1);
        DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
        tableModel.insertRow(0, rowData);
      } else if (this.shouru.isSelected()) {
        DefaultTableModel tableModel = (DefaultTableModel)this.table_1.getModel();
        tableModel.insertRow(0, rowData);
      }
      deltail();
    }
  }

  public DailyExpenses getFormdata() {
    String date = this.datePicker.getEditor().getText();
    String type = this.comboBox.getEditor().getItem().toString();
    String je = this.textField.getText();
    if ((!this.zhichu.isSelected()) && (!this.shouru.isSelected())) {
      this.label_7.setText("请先选择支出或收入类型");
      return null;
    }
    DailyExpenses dailyExpenses = null;
    if (this.zhichu.isSelected()) {
      boolean isCheck = check("支出");
      if (!isCheck) {
        return null;
      }
      dailyExpenses = new DailyExpenses();
      dailyExpenses.setMode("expenses");
    } else if (this.shouru.isSelected()) {
      boolean isCheck = check("收入");
      if (!isCheck) {
        return null;
      }
      dailyExpenses = new DailyExpenses();
      dailyExpenses.setMode("income");
    }

    this.label_7.setText("");
    dailyExpenses.setId(UUID.randomUUID().toString().replace("-", ""));
    dailyExpenses.setDate(date);
    dailyExpenses.setPay(new BigDecimal(je));
    dailyExpenses.setRecorddate(DateHelper.getNowDateTime());
    dailyExpenses.setType(type);
    return dailyExpenses;
  }

  private void clickedTable(String message, String title, JTable tableObj) {
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
        DailyExpensesMoudle dailyExpensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        dailyExpensesMoudle.deleteById(catno);
        tableModel.removeRow(nRow);
      case -1:
      case 1:
      }
    }
  }

  private boolean check(String typename)
  {
    String date = this.datePicker.getEditor().getText();
    String type = this.comboBox.getEditor().getItem().toString();
    String je = this.textField.getText();
    if ((date == null) || (date.trim().length() == 0)) {
      this.label_7.setText("请输" + typename + "时间");
      return false;
    }
    if ((type.trim().length() == 0) && (this.comboBox.getItemCount() == 0)) {
      this.label_7.setText("请设置" + typename + "类型!");
      return false;
    }
    if ((type == null) || (type.trim().length() == 0)) {
      this.label_7.setText("请设置" + typename + "类型!");
      return false;
    }
    if (this.zhichu.isSelected()) {
      boolean is = SelectType.isequals(type, "ZCLX");
      if (!is) {
        OptionMoudle optionMoudle = MoudleContentFactry.getOptionMoudle();
        Option option = new Option();
        option.setId(String.valueOf(System.currentTimeMillis()));
        option.setText(type);
        option.setType("ZCLX");
        boolean isd = optionMoudle.addOption(option);
        if (isd) {
          DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel)this.comboBox.getModel();
          comboBoxModel.insertElementAt(type, 0);
        }
      }
    }
    if (this.shouru.isSelected()) {
      boolean is = SelectType.isequals(type, "SRLX");
      if (!is) {
        OptionMoudle optionMoudle = MoudleContentFactry.getOptionMoudle();
        Option option = new Option();
        option.setId(String.valueOf(System.currentTimeMillis()));
        option.setText(type);
        option.setType("SRLX");
        boolean isd = optionMoudle.addOption(option);
        if (isd) {
          DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel)this.comboBox.getModel();
          comboBoxModel.insertElementAt(type, 0);
        }

      }

    }

    if ((je == null) || (je.trim().length() == 0)) {
      this.label_7.setText("请输入" + typename + "金额");
      return false;
    }

    double amounts = Double.parseDouble(je);
    if (amounts == 0.0D) {
      this.label_7.setText("输入" + typename + "金额大于零！");
      this.textField.setText("");
      this.textField.requestFocus();
      return false;
    }

    String nows = DateHelper.getNowDateTime();
    if ((date == null) || (date.trim().length() == 0)) {
      date = DateHelper.getNowDateTime();
    }
    long now = Long.parseLong(nows.replaceAll("-", ""));
    long e = Long.parseLong(date.replaceAll("-", ""));

    if (e > now) {
      this.label_7.setText("输入时间错误！只能录入今天以前的" + typename + "金额");
      return false;
    }
    return true;
  }

  public void deltail() {
    BigDecimal totalcost = new BigDecimal(0);
    int rows = this.table.getModel().getRowCount();
    DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
    for (int i = 0; i < rows; i++)
    {
      Object sell = tableModel.getValueAt(i, 3);
      BigDecimal pay = (sell instanceof Double) ? new BigDecimal(((Double)sell).doubleValue()) : (BigDecimal)sell;
      totalcost = totalcost.add(pay);
    }
    this.label_6.setText(totalcost.toString());
    BigDecimal totalcost_1 = new BigDecimal(0);
    int rowss = this.table_1.getModel().getRowCount();
    DefaultTableModel tableMode_2 = (DefaultTableModel)this.table_1.getModel();
    for (int i = 0; i < rowss; i++)
    {
      Object sell = tableMode_2.getValueAt(i, 3);
      BigDecimal pay = (sell instanceof Double) ? new BigDecimal(((Double)sell).doubleValue()) : (BigDecimal)sell;
      totalcost_1 = totalcost_1.add(pay);
    }
    this.label_9.setText(totalcost_1.toString());
  }

  private void initTadayFow() {
    DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
    DefaultTableModel tableModel_1 = (DefaultTableModel)this.table_1.getModel();
    if (tableModel.getRowCount() == 0) {
      DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
      List list = expensesMoudle.getDailyExpensesByTaday();
      if ((list != null) && (list.size() > 0)) {
        for (int i = 0; i < list.size(); i++) {
          DailyExpenses dailyExpenses = (DailyExpenses)list.get(i);
          Object[] rowData = { 
            dailyExpenses.getId(), 
            dailyExpenses.getDate(), 
            dailyExpenses.getType(), 
            dailyExpenses.getPay(), 
            "删除" };

          if ((dailyExpenses.getMode() == null) || ("expenses".equals(dailyExpenses.getMode())) || 
            ("null".equalsIgnoreCase(dailyExpenses.getMode())))
            tableModel.insertRow(0, rowData);
          else if ("income".equals(dailyExpenses.getMode())) {
            tableModel_1.insertRow(0, rowData);
          }
        }
      }

      deltail();
    }
  }

  public void disposePage() {
  }

  public String getPageId() { return getClass().getName(); }

  public String getPageName()
  {
    return ">>>日常收支记账";
  }

  public boolean isDefaultPage() {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool) {
    this.isDefaultPage = bool;
  }
}