package com.bluebee.ui.frame;

import com.bluebee.moudle.FlowLogMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.pojo.Flowlog;
import com.bluebee.pojo.Stock;
import com.bluebee.ui.LimitedDocument;
import com.bluebee.ui.widget.FlowDialog;
import com.bluebee.ui.widget.SuggestTextField;
import com.bluebee.util.DateHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jdesktop.swingx.JXDatePicker;

public class FlowPanel extends JPanel
  implements IPage
{
  private static FlowPanel flowPanel = new FlowPanel();

  private String dubString = "1234567890.";

  private boolean isDefaultPage = false;
  private JPanel panel1;
  private JLabel label1;
  private JLabel label2;
  private JTextField textField2;
  private JTextField textField3;
  private JPanel panel2;
  private JScrollPane scrollPane1;
  private JTable table1;
  private JPanel panel;
  private JLabel label;
  private JLabel label_1;
  private JLabel label_2;
  private JLabel label_3;
  private JLabel label_4;
  private JLabel label_5;
  private JLabel label_6;
  private JLabel label_7;
  private JLabel label_8;
  private JXDatePicker datePicker;
  private SuggestTextField suggestTextField;
  private JLabel label_9;
  private JLabel label_11;

  private FlowPanel()
  {
    setLayout(new BorderLayout());

    initComponents();
  }

  public static FlowPanel getInstance()
  {
    if (flowPanel != null) {
      flowPanel.initTadayFow();
    }
    return flowPanel;
  }

  private void initComponents() {
    this.panel1 = new JPanel();

    setLayout(new BorderLayout());

    this.panel1.setBorder(new TitledBorder("录入今天流水"));
    GridBagLayout gbl_panel1 = new GridBagLayout();
    gbl_panel1.columnWidths = new int[] { 51, 32 };
    gbl_panel1.rowHeights = new int[] { 17, 0, 24 };
    gbl_panel1.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 4.9E-324D };
    gbl_panel1.rowWeights = new double[] { 0.0D, 4.9E-324D, 0.0D };
    this.panel1.setLayout(gbl_panel1);
    this.label_6 = new JLabel("");
    this.label_6.setFont(new Font("宋体", 0, 12));
    this.label_6.setForeground(Color.red);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = 17;
    gbc.gridwidth = 4;
    gbc.insets = new Insets(0, 0, 5, 5);
    gbc.gridx = 2;
    gbc.gridy = 0;
    this.panel1.add(this.label_6, gbc);
    this.label_7 = new JLabel("日期");
    GridBagConstraints gbc_label_7 = new GridBagConstraints();
    gbc_label_7.anchor = 17;
    gbc_label_7.insets = new Insets(0, 0, 5, 5);
    gbc_label_7.gridx = 1;
    gbc_label_7.gridy = 1;
    this.panel1.add(this.label_7, gbc_label_7);

    this.datePicker = new JXDatePicker();
    this.datePicker.getEditor().setFont(new Font("宋体", 1, 12));

    this.datePicker.getEditor().setEditable(false);

    this.datePicker.setFormats(new String[] { "yyyy-MM-dd" });

    this.datePicker.setDate(DateHelper.currentDate());
    GridBagConstraints gbc_datePicker = new GridBagConstraints();
    gbc_datePicker.fill = 2;
    gbc_datePicker.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker.gridx = 2;
    gbc_datePicker.gridy = 1;
    this.panel1.add(this.datePicker, gbc_datePicker);
    this.label1 = new JLabel();

    this.label1.setText("货号");
    this.panel1.add(this.label1, new GridBagConstraints(3, 1, 1, 1, 0.0D, 0.0D, 
      13, 3, 
      new Insets(0, 0, 5, 5), 0, 0));

    this.suggestTextField = new SuggestTextField(new StockSuggestDataImpl(), null, null, null);
    this.suggestTextField.setFont(new Font("宋体", 1, 12));
    this.suggestTextField.setColumns(10);
    GridBagConstraints gbc_suggestTextField = new GridBagConstraints();
    gbc_suggestTextField.insets = new Insets(0, 0, 5, 5);
    gbc_suggestTextField.fill = 2;
    gbc_suggestTextField.gridx = 4;
    gbc_suggestTextField.gridy = 1;
    this.panel1.add(this.suggestTextField, gbc_suggestTextField);

    this.label_8 = new JLabel();

    this.label_8.setText("数量");
    this.panel1.add(this.label_8, new GridBagConstraints(5, 1, 1, 1, 0.0D, 0.0D, 
      10, 1, 
      new Insets(0, 0, 5, 5), 0, 0));
    this.textField3 = new JTextField("1");
    this.textField3.setFont(new Font("宋体", 1, 12));

    this.textField3.setDocument(new LimitedDocument(20, this.dubString));
    this.textField3.setColumns(5);
    this.textField3.setText("1");
    this.panel1.add(this.textField3, new GridBagConstraints(6, 1, 1, 1, 0.0D, 0.0D, 
      10, 1, 
      new Insets(0, 0, 5, 5), 0, 0));

    this.label2 = new JLabel();

    this.label2.setText("售价");
    this.panel1.add(this.label2, new GridBagConstraints(7, 1, 1, 1, 0.0D, 0.0D, 
      10, 1, 
      new Insets(0, 0, 5, 5), 0, 0));
    this.textField2 = new JTextField();
    this.textField2.setFont(new Font("宋体", 1, 12));
    this.textField2.setToolTipText("输入售价后按回车键进行保存");
    this.textField2.setDocument(new LimitedDocument(20, this.dubString));
    this.textField2.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10)
          FlowPanel.this.submmit();
      }
    });
    this.textField2.setColumns(7);
    this.panel1.add(this.textField2, new GridBagConstraints(8, 1, 1, 1, 0.0D, 0.0D, 
      10, 1, 
      new Insets(0, 0, 5, 5), 0, 0));

    add(this.panel1, "South");

    this.label_11 = new JLabel("输入售价后按回车键进行保存");
    GridBagConstraints gbc_label_11 = new GridBagConstraints();
    gbc_label_11.anchor = 17;
    gbc_label_11.gridwidth = 4;
    gbc_label_11.insets = new Insets(0, 0, 0, 5);
    gbc_label_11.gridx = 1;
    gbc_label_11.gridy = 2;
    this.panel1.add(this.label_11, gbc_label_11);
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[1];
    gbl_panel.rowHeights = new int[1];
    gbl_panel.columnWeights = new double[] { 4.9E-324D };
    gbl_panel.rowWeights = new double[] { 4.9E-324D };

    this.panel2 = new JPanel();
    this.scrollPane1 = new JScrollPane();
    this.table1 = new JTable();
    this.table1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int clicked = e.getClickCount();
        int nCol = FlowPanel.this.table1.getSelectedColumn();
        int nRow = FlowPanel.this.table1.getSelectedRow();
        Object objSel = FlowPanel.this.table1.getValueAt(nRow, nCol);
        if (objSel != null)
          if ("删除".equals(String.valueOf(objSel))) {
            FlowPanel.this.clickedTable();
          }
          else if (clicked == 2) {
            DefaultTableModel defaultTableModel = (DefaultTableModel)FlowPanel.this.table1.getModel();
            String flowno = String.valueOf(defaultTableModel.getValueAt(nRow, 0));
            String catno = String.valueOf(defaultTableModel.getValueAt(nRow, 1));
            String shoujia = String.valueOf(defaultTableModel.getValueAt(nRow, 2));
            String shul = String.valueOf(defaultTableModel.getValueAt(nRow, 4));

            FlowDialog flowDialog = new FlowDialog(FlowPanel.flowPanel, FlowPanel.this.table1, flowno, catno, shoujia, shul);
            flowDialog.setVisible(true);
          }
      }
    });
    this.panel2.setBorder(new TitledBorder("今天流水"));

    this.table1.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "流水号", "货号", "售价", "利润", "数量", "成本", "类型", "客户号", "客户名称", "时间", "操作" })
    {
      Class[] columnTypes = { 
        String.class, String.class, BigDecimal.class, BigDecimal.class, Double.class, BigDecimal.class, String.class, String.class, String.class, String.class, String.class };

      boolean[] columnEditables = { 
        true };

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table1.getColumnModel().getColumn(7).setResizable(false);
    this.table1.getTableHeader().setReorderingAllowed(false);
    TableColumn tcflow = this.table1.getColumn("流水号");
    tcflow.setResizable(false);
    tcflow.setPreferredWidth(0);
    tcflow.setWidth(0);
    tcflow.setMinWidth(0);
    tcflow.setMaxWidth(0);

    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(0);
    TableColumn tc = this.table1.getColumn("操作");
    tc.setPreferredWidth(60);
    tc.setCellRenderer(renderer);
    this.scrollPane1.setViewportView(this.table1);

    this.panel2.setLayout(new BorderLayout(0, 0));
    this.panel2.add(this.scrollPane1);

    add(this.panel2, "Center");

    this.panel = new JPanel();
    this.panel.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 0, 89, 51, 0, 60, 0, 57, 90 };
    gridBagLayout.rowHeights = new int[2];
    gridBagLayout.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0001D };
    gridBagLayout.rowWeights = new double[] { 0.0D, 0.0001D };
    this.panel.setLayout(gridBagLayout);
    add(this.panel, "North");

    this.label = new JLabel("今日流水总计：");
    GridBagConstraints gbc_label_12 = new GridBagConstraints();
    gbc_label_12.anchor = 17;
    gbc_label_12.insets = new Insets(0, 0, 0, 5);
    gbc_label_12.gridx = 1;
    gbc_label_12.gridy = 0;
    this.panel.add(this.label, gbc_label_12);

    this.label_1 = new JLabel("0");
    this.label_1.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_label_13 = new GridBagConstraints();
    gbc_label_13.insets = new Insets(0, 0, 0, 5);
    gbc_label_13.gridx = 2;
    gbc_label_13.gridy = 0;
    this.panel.add(this.label_1, gbc_label_13);

    this.label_2 = new JLabel("今日利润：");
    GridBagConstraints gbc_label_14 = new GridBagConstraints();
    gbc_label_14.anchor = 17;
    gbc_label_14.insets = new Insets(0, 0, 0, 5);
    gbc_label_14.gridx = 3;
    gbc_label_14.gridy = 0;
    this.panel.add(this.label_2, gbc_label_14);

    this.label_3 = new JLabel("0");
    this.label_3.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_label_15 = new GridBagConstraints();
    gbc_label_15.insets = new Insets(0, 0, 0, 5);
    gbc_label_15.gridx = 4;
    gbc_label_15.gridy = 0;
    this.panel.add(this.label_3, gbc_label_15);

    this.label_4 = new JLabel("今日销售数量:");
    GridBagConstraints gbc_label_16 = new GridBagConstraints();
    gbc_label_16.insets = new Insets(0, 0, 0, 5);
    gbc_label_16.gridx = 5;
    gbc_label_16.gridy = 0;
    this.panel.add(this.label_4, gbc_label_16);

    this.label_5 = new JLabel("0");
    this.label_5.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_label_17 = new GridBagConstraints();
    gbc_label_17.insets = new Insets(0, 0, 0, 5);
    gbc_label_17.gridx = 6;
    gbc_label_17.gridy = 0;
    this.panel.add(this.label_5, gbc_label_17);

    this.label_9 = new JLabel("查询以往流水记录，可在营业额统计中查看");
    GridBagConstraints gbc_label_18 = new GridBagConstraints();
    gbc_label_18.gridwidth = 3;
    gbc_label_18.anchor = 17;
    gbc_label_18.insets = new Insets(0, 0, 0, 5);
    gbc_label_18.gridx = 7;
    gbc_label_18.gridy = 0;
    this.panel.add(this.label_9, gbc_label_18);
  }

  public void submmit()
  {
    Flowlog flowlog = getFormData();
    if (flowlog != null) {
      setFormData(flowlog);
      this.label_6.setText("流水保存成功！");
      clear();
    }
  }

  public Flowlog getFormData() {
    String date = this.datePicker.getEditor().getText();
    if (date.trim().length() > 0) {
      if (!DateHelper.isDate(date)) {
        this.label_6.setText("请输入正确日期格式！例如2008-08-08!");
        return null;
      }
    }
    else date = DateHelper.getNowDateTime();

    String nows = DateHelper.getNowDateTime();
    long now = Long.parseLong(nows.replaceAll("-", ""));
    long e = Long.parseLong(date.replaceAll("-", ""));

    if (e > now) {
      this.label_6.setText("输入时间错误！只能记录今天以前的流水");
      return null;
    }
    String t1 = this.suggestTextField.getText();
    String t2 = this.textField2.getText();
    if ((t1 == null) || (t1.trim().length() == 0)) {
      this.label_6.setText("请输入货物号码!");
      this.suggestTextField.requestFocus();
      return null;
    }

    if ((t2 == null) || (t2.trim().length() == 0)) {
      this.label_6.setText("请输入货物销售价格!");
      this.textField2.requestFocus();
      return null;
    }
    BigDecimal sell = new BigDecimal(t2);

    if (sell.compareTo(new BigDecimal("0")) == 0) {
      this.label_6.setText("输入货物销售价格错误!");
      this.textField2.requestFocus();
      return null;
    }

    StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
    Stock stock = stockMoudle.getLastStockByNo(t1);
    Flowlog flowlog = new Flowlog();
    if (this.textField3.getText() != null)
      flowlog.setAmount(Double.parseDouble(this.textField3.getText()));
    else {
      flowlog.setAmount(1.0D);
    }
    if (stock == null) {
      this.label_6.setText("输入货物号码不存在或者已经售完,请重新输入!");
      return null;
    }
    double syamount = stock.getSyamount();
    if (flowlog.getAmount() > syamount) {
      this.label_6.setText("输入数量大于库存剩余数量，库存目前剩余" + syamount);
      return null;
    }

    if ((this.label_6.getText() != null) && (this.label_6.getText().trim().length() > 0)) {
      this.label_6.setText("");
    }
    BigDecimal cost = stock.getCostprice();

    BigDecimal lr = sell.subtract(cost).multiply(BigDecimal.valueOf(flowlog.getAmount()));

    String id = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));
    flowlog.setFlowno(id);
    flowlog.setCatno(t1);
    flowlog.setSellprice(sell);
    flowlog.setLrprice(lr);
    flowlog.setType(stock.getType());
    flowlog.setDate(date);
    flowlog.setCostprice(stock.getCostprice());
    flowlog.setRecorddate(DateHelper.getNowDateTime());
    flowlog.setFlowflag("sell");
    flowlog.setStockname(stock.getStockname());
    return flowlog;
  }

  public void setFormData(Flowlog flowlog) {
    Object[] rowData = { 
      flowlog.getFlowno(), 
      flowlog.getCatno(), 
      flowlog.getSellprice(), 
      flowlog.getLrprice(), 
      Double.valueOf(flowlog.getAmount()), 
      flowlog.getCostprice(), 
      flowlog.getType(), 
      flowlog.getCustomNo(), 
      flowlog.getCustomName(), 
      flowlog.getDate(), 
      "删除" };

    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table1.getModel();
    defaultTableModel.insertRow(0, rowData);
    deltail();
    FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
    flowLogMoudle.add(flowlog);

    StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
    stockMoudle.updateSyAmount(flowlog.getCatno(), flowlog.getAmount(), "-");
  }

  private void clickedTable() {
    int nCol = this.table1.getSelectedColumn();
    int nRow = this.table1.getSelectedRow();
    Object objSel = this.table1.getValueAt(nRow, nCol);
    if ((objSel != null) && ((objSel instanceof String)) && 
      ("删除".equals(String.valueOf(objSel)))) {
      int response = JOptionPane.showConfirmDialog(null, 
        "确定删除此条数据?", "删除流水数据", 0);
      switch (response) {
      case 0:
        DefaultTableModel defaultTableModel = (DefaultTableModel)this.table1.getModel();
        String flowno = String.valueOf(defaultTableModel.getValueAt(nRow, 0));
        String catno = String.valueOf(defaultTableModel.getValueAt(nRow, 1));
        double amount = ((Double)defaultTableModel.getValueAt(nRow, 4)).doubleValue();
        FlowLogMoudle stockMoudle = MoudleContentFactry.getFlowLogMoudle();
        stockMoudle.delete(flowno, catno, amount);
        defaultTableModel.removeRow(nRow);
        deltail();
      case -1:
      case 1:
      }
    }
  }

  public void deltail()
  {
    BigDecimal flowtotalcost = new BigDecimal(0);

    BigDecimal lrtotalcost = new BigDecimal(0);
    double num = 0.0D;
    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table1.getModel();
    int rows = defaultTableModel.getRowCount();
    for (int i = 0; i < rows; i++)
    {
      BigDecimal sell = (BigDecimal)defaultTableModel.getValueAt(i, 2);
      double aa = ((Double)defaultTableModel.getValueAt(i, 4)).doubleValue();
      BigDecimal amount = BigDecimal.valueOf(aa);
      flowtotalcost = sell.multiply(amount).add(flowtotalcost);

      Object lrtext = defaultTableModel.getValueAt(i, 3);

      BigDecimal lr = (lrtext instanceof String) ? new BigDecimal(String.valueOf(lrtext)) : (BigDecimal)lrtext;
      lrtotalcost = lrtotalcost.add(lr);

      double amounts = ((Double)defaultTableModel.getValueAt(i, 4)).doubleValue();
      num += amounts;
    }
    flowtotalcost = flowtotalcost.setScale(2, 4);
    this.label_1.setText(flowtotalcost.toString());
    this.label_5.setText(String.valueOf(num));
    lrtotalcost.setScale(2, 4);
    this.label_3.setText(lrtotalcost.toString());
  }

  private void initTadayFow() {
    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table1.getModel();
    if (defaultTableModel.getRowCount() > 0) {
      int count = defaultTableModel.getRowCount();
      for (int i = 0; i < count; i++) {
        defaultTableModel.removeRow(0);
      }
    }
    FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
    List list = flowLogMoudle.getFlowlogByToday("sell");
    if ((list != null) && (list.size() > 0)) {
      for (int i = 0; i < list.size(); i++) {
        Flowlog flowlog = (Flowlog)list.get(i);
        Object[] rowData = { 
          flowlog.getFlowno(), 
          flowlog.getCatno(), 
          flowlog.getSellprice(), 
          flowlog.getLrprice(), 
          Double.valueOf(flowlog.getAmount()), 
          flowlog.getCostprice(), 
          flowlog.getType(), 
          flowlog.getCustomNo(), 
          flowlog.getCustomName(), 
          flowlog.getDate(), 
          "删除" };

        defaultTableModel.insertRow(0, rowData);
      }
    }
    deltail();
  }

  public void clear() {
    this.suggestTextField.setText("");
    this.textField2.setText("");
  }

  public String getPageId() {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>每天流水帐";
  }

  public void disposePage() {
  }

  public boolean isDefaultPage() {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool) {
    this.isDefaultPage = bool;
  }
}