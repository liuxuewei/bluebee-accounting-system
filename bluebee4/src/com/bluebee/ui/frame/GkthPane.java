package com.bluebee.ui.frame;

import com.bluebee.moudle.FlowLogMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.pojo.Flowlog;
import com.bluebee.pojo.Stock;
import com.bluebee.ui.LimitedDocument;
import com.bluebee.ui.widget.SuggestTextField;
import com.bluebee.util.DateHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXDatePicker;

public class GkthPane extends JPanel
  implements IPage
{
  private static GkthPane cgthPane = new GkthPane();
  private SuggestTextField suggestTextField;
  private JXDatePicker datePicker;
  private String dubString = "-1234567890.";
  private boolean isDefaultPage = false;
  private JTable table;
  private JTextField textField;
  private JTextField textField_1;
  private JLabel label_10;
  private JLabel label_6;
  private JLabel label_8;

  public static GkthPane getInstance()
  {
    if (cgthPane != null) {
      cgthPane.clear();
      FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
      List list = flowLogMoudle.getFlowlogByToday("tuihuo");
      if ((list != null) && (list.size() > 0)) {
        for (int i = 0; i < list.size(); i++) {
          Flowlog flowlog = (Flowlog)list.get(i);
          Object[] rowData = { 
            flowlog.getFlowno(), 
            flowlog.getCatno(), 
            flowlog.getSellprice(), 
            flowlog.getLrprice(), 
            Double.valueOf(flowlog.getAmount()), 
            flowlog.getType(), 
            flowlog.getCostprice(), 
            flowlog.getDate(), 
            "删除" };

          DefaultTableModel defaultTableModel = (DefaultTableModel)cgthPane.table.getModel();
          defaultTableModel.insertRow(0, rowData);
        }
      }
      cgthPane.deltail();
    }
    return cgthPane;
  }

  private GkthPane() {
    setLayout(new BorderLayout(0, 0));
    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    add(panel, "North");
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[] { 40, 0, 107, 39, 72, 23, 40, 0, 54, 67 };
    gbl_panel.rowHeights = new int[] { 20, 31 };
    gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 4.9E-324D };
    gbl_panel.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel.setLayout(gbl_panel);

    this.label_10 = new JLabel("");
    this.label_10.setForeground(Color.RED);
    this.label_10.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_label_10 = new GridBagConstraints();
    gbc_label_10.anchor = 17;
    gbc_label_10.gridwidth = 8;
    gbc_label_10.insets = new Insets(0, 0, 5, 5);
    gbc_label_10.gridx = 1;
    gbc_label_10.gridy = 0;
    panel.add(this.label_10, gbc_label_10);

    JLabel label = new JLabel("退货时间");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 5, 5);
    gbc_label.anchor = 13;
    gbc_label.gridx = 1;
    gbc_label.gridy = 1;
    panel.add(label, gbc_label);

    this.datePicker = new JXDatePicker();
    this.datePicker.setDate(DateHelper.currentDate());
    this.datePicker.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker.getEditor().setFont(new Font("宋体", 1, 12));
    this.datePicker.getEditor().setEditable(false);
    this.datePicker.getEditor().setColumns(10);
    GridBagConstraints gbc_datePicker = new GridBagConstraints();
    gbc_datePicker.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker.gridx = 2;
    gbc_datePicker.gridy = 1;
    panel.add(this.datePicker, gbc_datePicker);

    JLabel label_1 = new JLabel("货号");
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.anchor = 13;
    gbc_label_1.insets = new Insets(0, 0, 5, 5);
    gbc_label_1.gridx = 3;
    gbc_label_1.gridy = 1;
    panel.add(label_1, gbc_label_1);

    this.suggestTextField = new SuggestTextField(new FlowSuggestDataImpl(), null, null, null);
    this.suggestTextField.addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent e) {
        String ds = GkthPane.this.suggestTextField.getText();
        if (ds.trim().length() > 0) {
          FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
          List flowlist = flowLogMoudle.getFlowlog(ds);
          if ((flowlist != null) && (flowlist.size() > 0)) {
            Flowlog flowlog = (Flowlog)flowlist.get(0);
            GkthPane.this.textField_1.setText(String.valueOf(flowlog.getSellprice()));
          }
        }
      }
    });
    this.suggestTextField.setFont(new Font("宋体", 1, 12));
    this.suggestTextField.setColumns(7);
    GridBagConstraints gbc_suggestTextField = new GridBagConstraints();
    gbc_suggestTextField.insets = new Insets(0, 0, 5, 5);
    gbc_suggestTextField.gridx = 4;
    gbc_suggestTextField.gridy = 1;
    panel.add(this.suggestTextField, gbc_suggestTextField);

    JLabel label_2 = new JLabel("数量");
    label_2.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.anchor = 13;
    gbc_label_2.insets = new Insets(0, 0, 5, 5);
    gbc_label_2.gridx = 5;
    gbc_label_2.gridy = 1;
    panel.add(label_2, gbc_label_2);

    this.textField = new JTextField();
    this.textField.setFont(new Font("宋体", 1, 12));
    this.textField.setDocument(new LimitedDocument(20, this.dubString));
    this.textField.setText("1");
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.insets = new Insets(0, 0, 5, 5);
    gbc_textField.fill = 2;
    gbc_textField.gridx = 6;
    gbc_textField.gridy = 1;
    panel.add(this.textField, gbc_textField);
    this.textField.setColumns(3);

    JLabel label_3 = new JLabel("退货售价");
    GridBagConstraints gbc_label_3 = new GridBagConstraints();
    gbc_label_3.anchor = 13;
    gbc_label_3.insets = new Insets(0, 0, 5, 5);
    gbc_label_3.gridx = 7;
    gbc_label_3.gridy = 1;
    panel.add(label_3, gbc_label_3);

    this.textField_1 = new JTextField();
    this.textField_1.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10)
          GkthPane.this.submmit();
      }
    });
    this.textField_1.setFont(new Font("宋体", 1, 12));
    this.textField_1.setDocument(new LimitedDocument(20, this.dubString));
    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
    gbc_textField_1.insets = new Insets(0, 0, 5, 5);
    gbc_textField_1.fill = 2;
    gbc_textField_1.gridx = 8;
    gbc_textField_1.gridy = 1;
    panel.add(this.textField_1, gbc_textField_1);
    this.textField_1.setColumns(5);

    JLabel label_4 = new JLabel("输入完退货售价按回车键进行保存");
    label_4.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_label_4 = new GridBagConstraints();
    gbc_label_4.anchor = 17;
    gbc_label_4.gridwidth = 3;
    gbc_label_4.insets = new Insets(0, 0, 0, 5);
    gbc_label_4.gridx = 1;
    gbc_label_4.gridy = 2;
    panel.add(label_4, gbc_label_4);

    JPanel panel_1 = new JPanel();
    add(panel_1, "Center");
    panel_1.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    panel_1.add(scrollPane, "Center");

    this.table = new JTable();
    this.table.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "ID", "货号", "售价", "利润", "数量", "类型", "名称", "退货时间", "操作" })
    {
      Class[] columnTypes = { 
        Object.class, String.class, BigDecimal.class, BigDecimal.class, Double.class, String.class, String.class, String.class, String.class };

      boolean[] columnEditable = new boolean[9];

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex)
      {
        return this.columnEditable[columnIndex];
      }
    });
    this.table.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(0).setMinWidth(0);
    this.table.getColumnModel().getColumn(0).setMaxWidth(0);
    this.table.getColumnModel().getColumn(1).setResizable(false);
    this.table.getColumnModel().getColumn(2).setResizable(false);
    this.table.getColumnModel().getColumn(3).setResizable(false);
    this.table.getColumnModel().getColumn(4).setResizable(false);
    this.table.getColumnModel().getColumn(5).setResizable(false);
    this.table.getColumnModel().getColumn(6).setResizable(false);
    this.table.getColumnModel().getColumn(7).setPreferredWidth(80);
    this.table.getColumnModel().getColumn(7).setMinWidth(80);
    this.table.getColumnModel().getColumn(7).setMaxWidth(80);

    DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer() {
      public void setValue(Object value) {
        setForeground(Color.green);
        setText(value == null ? "" : value.toString());
      }
    };
    this.table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        GkthPane.this.clickedTable();
      }
    });
    this.table.getColumn("售价").setCellRenderer(fontColor);
    this.table.getColumn("利润").setCellRenderer(fontColor);
    this.table.getColumn("数量").setCellRenderer(fontColor);

    this.table.getTableHeader().setReorderingAllowed(false);
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(0);
    TableColumn tc = this.table.getColumn("操作");
    tc.setPreferredWidth(60);
    tc.setCellRenderer(renderer);
    scrollPane.setViewportView(this.table);
    JPanel panel_2 = new JPanel();
    add(panel_2, "South");
    GridBagLayout gbl_panel_2 = new GridBagLayout();
    gbl_panel_2.columnWidths = new int[] { 26, 54, 52, 0, 49 };
    gbl_panel_2.rowHeights = new int[] { 16 };
    gbl_panel_2.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_2.rowWeights = new double[] { 0.0D, 4.9E-324D };
    panel_2.setLayout(gbl_panel_2);

    JLabel label_5 = new JLabel("今日退货金额");
    GridBagConstraints gbc_label_5 = new GridBagConstraints();
    gbc_label_5.fill = 3;
    gbc_label_5.insets = new Insets(0, 0, 0, 5);
    gbc_label_5.anchor = 17;
    gbc_label_5.gridx = 1;
    gbc_label_5.gridy = 0;
    panel_2.add(label_5, gbc_label_5);

    this.label_6 = new JLabel("0");
    GridBagConstraints gbc_label_6 = new GridBagConstraints();
    gbc_label_6.fill = 3;
    gbc_label_6.insets = new Insets(0, 0, 0, 5);
    gbc_label_6.gridx = 2;
    gbc_label_6.gridy = 0;
    panel_2.add(this.label_6, gbc_label_6);

    JLabel label_7 = new JLabel("今日退货数量");
    GridBagConstraints gbc_label_7 = new GridBagConstraints();
    gbc_label_7.fill = 3;
    gbc_label_7.insets = new Insets(0, 0, 0, 5);
    gbc_label_7.gridx = 3;
    gbc_label_7.gridy = 0;
    panel_2.add(label_7, gbc_label_7);

    this.label_8 = new JLabel("0");
    GridBagConstraints gbc_label_8 = new GridBagConstraints();
    gbc_label_8.insets = new Insets(0, 0, 0, 5);
    gbc_label_8.fill = 3;
    gbc_label_8.gridx = 4;
    gbc_label_8.gridy = 0;
    panel_2.add(this.label_8, gbc_label_8);

    JLabel label_9 = new JLabel("");
    GridBagConstraints gbc_label_9 = new GridBagConstraints();
    gbc_label_9.insets = new Insets(0, 0, 0, 5);
    gbc_label_9.gridwidth = 3;
    gbc_label_9.gridx = 6;
    gbc_label_9.gridy = 0;
    panel_2.add(label_9, gbc_label_9);
  }

  private void clickedTable() {
    int nCol = this.table.getSelectedColumn();
    int nRow = this.table.getSelectedRow();
    Object objSel = this.table.getValueAt(nRow, nCol);
    if ((objSel != null) && ((objSel instanceof String)) && 
      ("删除".equals(String.valueOf(objSel)))) {
      DefaultTableModel defaultTableModel = (DefaultTableModel)this.table.getModel();
      int response = JOptionPane.showConfirmDialog(null, 
        "确定删除此条数据?", "删除退货数据", 0);
      switch (response)
      {
      case 0:
        String flowno = String.valueOf(defaultTableModel.getValueAt(nRow, 0));

        FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        Flowlog flowlog = flowLogMoudle.getFlowByflowno(flowno);
        if (flowlog != null)
        {
          double am = flowlog.getAmount();
          if (am < 0.0D) {
            am *= -1.0D;
          }
          flowLogMoudle.delete(flowno);
          stockMoudle.updateSyAmount(flowlog.getCatno(), am, "-");
          defaultTableModel.removeRow(nRow);
        }

        deltail();
      case -1:
      case 1:
      }
    }
  }

  public void submmit()
  {
    Flowlog flowlog = getFormData();
    if (flowlog != null) {
      int response = JOptionPane.showConfirmDialog(null, 
        "确定退货[" + flowlog.getCatno() + "]数据?", "采购退货", 0);
      switch (response) {
      case 0:
        setFormData(flowlog);
        clear();
      case -1:
      case 1:
      }
    }
  }

  public Flowlog getFormData()
  {
    String date = this.datePicker.getEditor().getText();
    if (date.trim().length() > 0) {
      if (!DateHelper.isDate(date)) {
        this.label_10.setText("请输入正确日期格式！例如2008-08-08!");
        return null;
      }
    }
    else date = DateHelper.getNowDateTime();

    String nows = DateHelper.getNowDateTime();
    long now = Long.parseLong(nows.replaceAll("-", ""));
    long e = Long.parseLong(date.replaceAll("-", ""));

    if (e > now) {
      this.label_10.setText("输入时间错误！只能记录今天以前的退货流水");
      return null;
    }
    String t1 = this.suggestTextField.getText();

    if ((t1 == null) || (t1.trim().length() == 0)) {
      this.label_10.setText("请输入货物号码!");
      this.suggestTextField.requestFocus();
      return null;
    }
    String t2 = this.textField.getText();
    if ((t2 == null) || (t2.trim().length() == 0)) {
      this.label_10.setText("请输入退货货物数量!");
      this.textField.requestFocus();
      return null;
    }

    String thjg = this.textField_1.getText();
    if ((thjg == null) || (thjg.trim().length() == 0)) {
      this.label_10.setText("请输入货物销售价格!");
      this.textField_1.requestFocus();
      return null;
    }
    BigDecimal sell = new BigDecimal(thjg);
    BigDecimal selltemp = new BigDecimal(0);

    if (sell.compareTo(new BigDecimal("0")) == 0) {
      this.label_10.setText("输入货物销售价格错误!");
      this.textField_1.requestFocus();
      return null;
    }if (sell.compareTo(new BigDecimal(-1)) == 1) {
      selltemp = sell.multiply(new BigDecimal(-1));
    }
    StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
    Stock stock = stockMoudle.getStockByNo(t1);
    FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
    double th = flowLogMoudle.sumFlow(t1, "tuihuo") + Double.parseDouble(t2);

    double s1 = stockMoudle.sumStockAmount(t1);
    if (th > s1) {
      this.label_10.setText("退货数量超出库存数量!");
      return null;
    }
    if (stock == null) {
      this.label_10.setText("输入货号错误，库存中没有这种货物的历史记录!");
      return null;
    }

    Flowlog flowlog = new Flowlog();
    String id = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));
    flowlog.setFlowno(id);
    flowlog.setCatno(t1);
    flowlog.setSellprice(selltemp);
    flowlog.setLrprice(stock.getCostprice().subtract(sell).multiply(new BigDecimal(Double.parseDouble(t2))));
    flowlog.setType(stock.getType());
    flowlog.setDate(date);
    flowlog.setCostprice(stock.getCostprice());
    flowlog.setRecorddate(DateHelper.getNowDateTime());
    flowlog.setStockname(stock.getStockname());
    flowlog.setFlowflag("tuihuo");
    flowlog.setAmount(Double.parseDouble(t2));
    return flowlog;
  }

  public void setFormData(Flowlog flowlog) {
    Object[] rowData = { 
      flowlog.getFlowno(), 
      flowlog.getCatno(), 
      flowlog.getSellprice(), 
      flowlog.getLrprice(), 
      Double.valueOf(flowlog.getAmount() * -1.0D), 
      flowlog.getType(), 
      flowlog.getCostprice(), 
      flowlog.getDate(), 
      "删除" };

    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table.getModel();
    defaultTableModel.insertRow(0, rowData);
    deltail();
    StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
    stockMoudle.updateSyAmount(flowlog.getCatno(), flowlog.getAmount(), "+");
    FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
    flowlog.setAmount(flowlog.getAmount() * -1.0D);
    flowLogMoudle.add(flowlog);
    clear();
  }

  public void clear()
  {
    this.suggestTextField.setText("");
    this.textField.setText("");
    this.textField_1.setText("");
  }

  private void deltail()
  {
    BigDecimal flowtotalcost = new BigDecimal(0);

    double num = 0.0D;
    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table.getModel();
    int rows = defaultTableModel.getRowCount();
    for (int i = 0; i < rows; i++)
    {
      BigDecimal sell = (BigDecimal)defaultTableModel.getValueAt(i, 2);
      Double aa = (Double)defaultTableModel.getValueAt(i, 4);
      BigDecimal amount = BigDecimal.valueOf(aa.doubleValue());
      flowtotalcost = sell.multiply(amount).add(flowtotalcost);

      double amounts = ((Double)defaultTableModel.getValueAt(i, 4)).doubleValue();
      num += amounts;
    }
    this.label_6.setText(flowtotalcost.toString());
    this.label_8.setText(String.valueOf(num));
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>顾客退货";
  }

  public void disposePage()
  {
  }

  public boolean isDefaultPage() {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool) {
    this.isDefaultPage = bool;
  }
}