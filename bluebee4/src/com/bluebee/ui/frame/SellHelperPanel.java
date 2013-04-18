package com.bluebee.ui.frame;

import com.bluebee.moudle.CustomMoudle;
import com.bluebee.moudle.CustomtTypeMoudle;
import com.bluebee.moudle.FlowLogMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.pojo.Custom;
import com.bluebee.pojo.CustomType;
import com.bluebee.pojo.Flowlog;
import com.bluebee.pojo.Stock;
import com.bluebee.ui.widget.CashDialog;
import com.bluebee.ui.widget.SellDialog;
import com.bluebee.ui.widget.SuggestTextField;
import com.bluebee.util.DateHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
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
import java.util.UUID;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;

public class SellHelperPanel extends JPanel
  implements IPage
{
  public static SellHelperPanel sellHelperPanel = new SellHelperPanel();

  public boolean isDefaultPage = false;
  private JLabel label_10;
  private JTable table;
  private JTextField textField;
  private JTextField textField_1;
  private JLabel label_8;
  private JLabel label_11;
  private JLabel label_13;
  private JLabel label_16;
  private JLabel label_1;
  private JLabel lbll;
  private JXDatePicker datePicker;
  private final JTextField discountHiddenTextFiled = new JTextField();

  private SellHelperPanel()
  {
    this.discountHiddenTextFiled.setColumns(10);
    setLayout(new BorderLayout());

    JPanel panel_1 = new JPanel();
    panel_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    panel_1.setPreferredSize(new Dimension(10, 60));
    panel_1.setSize(new Dimension(0, 110));
    Border loweredetched = 
      BorderFactory.createEtchedBorder(1);

    TitledBorder titledBorder = BorderFactory.createTitledBorder(
      loweredetched, "售货帮助提示");
    panel_1.setBorder(titledBorder);
    add(panel_1, "North");
    panel_1.setLayout(null);

    this.label_10 = new JLabel("");
    this.label_10.setBounds(0, 0, 0, 0);
    panel_1.add(this.label_10);

    JLabel label_6 = new JLabel("客户号:");
    label_6.setFont(new Font("宋体", 0, 12));
    label_6.setBounds(10, 27, 42, 15);
    panel_1.add(label_6);

    this.textField = new SuggestTextField(new CustomSuggestDataImpl(), 
      new SuggestTextField.MUIManager() {
      public void updateView(String[] args) {
        String discount = null;
        if ((args != null) && (args.length > 0)) {
          if ((args[1] != null) && (!"null".equals(args[1]))) {
            SellHelperPanel.this.label_8.setText(args[1]);
          }
          if ((args[3] != null) && (!"null".equals(args[3]))) {
            String customType = args[3];

            SellHelperPanel.this.label_11.setText(customType);

            CustomtTypeMoudle customtTypeMoudle = MoudleContentFactry.getCustomtTypeMoudle();

            CustomType customTypeBean = customtTypeMoudle.get(customType);
            discount = String.valueOf(customTypeBean.getDiscount());
          }

          if ((args[4] != null) && (!"null".equals(args[4]))) {
            SellHelperPanel.this.label_13.setText(args[4]);
          }
        }

        if ((discount == null) && ("".equals(discount)))
          SellHelperPanel.this.discountHiddenTextFiled.setText("10");
        else {
          SellHelperPanel.this.discountHiddenTextFiled.setText(discount);
        }

        SellHelperPanel.this.updateViewByCustomId();
      }
    }
    , new Dimension(250, 200), null);

    String toolTip = new String(
      "<html>提示&nbsp;&nbsp;&nbsp;<br>输入客户号/姓名/电话号码 按回车键<br>&nbsp;&nbsp;&nbsp;</html>");
    this.textField.setToolTipText(toolTip);
    this.textField.setFont(new Font("宋体", 0, 14));
    this.textField.setHorizontalAlignment(2);
    this.textField.setBounds(52, 22, 125, 25);
    panel_1.add(this.textField);
    this.textField.setColumns(10);

    JLabel label_7 = new JLabel("姓名：");
    label_7.setFont(new Font("宋体", 0, 12));
    label_7.setBounds(197, 27, 36, 15);
    panel_1.add(label_7);

    this.label_8 = new JLabel("");
    this.label_8.setBounds(233, 27, 54, 15);
    panel_1.add(this.label_8);

    JLabel label_9 = new JLabel("级别：");
    label_9.setFont(new Font("宋体", 0, 12));
    label_9.setBounds(286, 27, 42, 15);
    panel_1.add(label_9);

    this.label_11 = new JLabel("");
    this.label_11.setBounds(321, 27, 79, 15);
    panel_1.add(this.label_11);

    JLabel label_12 = new JLabel("积分：");
    label_12.setBounds(398, 27, 36, 15);
    panel_1.add(label_12);

    this.label_13 = new JLabel("");
    this.label_13.setBounds(431, 27, 42, 15);
    panel_1.add(this.label_13);

    JLabel label = new JLabel("日期：");
    label.setBounds(475, 27, 36, 15);
    panel_1.add(label);

    this.datePicker = new JXDatePicker();

    this.datePicker.getEditor().setFocusTraversalKeysEnabled(false);

    this.datePicker.setFont(new Font("宋体", 0, 12));
    this.datePicker.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker.getEditor().setFont(new Font("宋体", 0, 12));
    this.datePicker.getEditor().setColumns(8);
    this.datePicker.setDate(DateHelper.currentDate());
    this.datePicker.setBounds(510, 23, 93, 23);
    panel_1.add(this.datePicker);

    JPanel panel_2 = new JPanel();
    add(panel_2, "Center");
    panel_2.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setFont(new Font("宋体", 0, 12));

    TitledBorder titledBordert = BorderFactory.createTitledBorder(
      loweredetched, "商品列表");
    scrollPane.setBorder(titledBordert);
    panel_2.add(scrollPane, "Center");

    this.table = new JTable();
    this.table.setFont(new Font("宋体", 0, 12));
    this.table.setModel(new DefaultTableModel(null, new String[] { 
      "货号", "名称", "数量", "售价", 
      "折扣", "合计", "cost", "type" }) {
      Class[] columnTypes = { String.class, String.class, 
        Double.class, BigDecimal.class, Float.class, 
        BigDecimal.class, BigDecimal.class, String.class };

      boolean[] columnEditables = new boolean[8];

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table.getColumnModel().getColumn(0).setPreferredWidth(112);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(155);
    this.table.getColumnModel().getColumn(1).setMinWidth(80);
    this.table.getColumnModel().getColumn(5).setPreferredWidth(94);
    this.table.getColumnModel().getColumn(6).setResizable(false);
    this.table.getColumnModel().getColumn(6).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(6).setMinWidth(0);
    this.table.getColumnModel().getColumn(6).setMaxWidth(0);
    this.table.getColumnModel().getColumn(7).setResizable(false);
    this.table.getColumnModel().getColumn(7).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(7).setMinWidth(0);
    this.table.getColumnModel().getColumn(7).setMaxWidth(0);
    this.table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int clicked = e.getClickCount();
        if (clicked == 2) {
          SellDialog sellDialog = new SellDialog(SellHelperPanel.sellHelperPanel, 
            SellHelperPanel.this.table, SellHelperPanel.this.label_16, SellHelperPanel.this.lbll);
          sellDialog.setVisible(true);
        }
      }
    });
    this.table.getTableHeader().setReorderingAllowed(false);
    this.table.setSelectionMode(0);
    this.table.getSelectionModel().setSelectionInterval(0, 0);
    scrollPane.setViewportView(this.table);

    JPanel panel_4 = new JPanel();

    panel_4.setFont(new Font("新宋体", 0, 12));

    TitledBorder titledBorderth = BorderFactory.createTitledBorder(
      loweredetched, "");
    panel_4.setBorder(titledBorderth);
    panel_4.setPreferredSize(new Dimension(10, 80));
    panel_2.add(panel_4, "South");
    GridBagLayout gbl_panel_4 = new GridBagLayout();
    gbl_panel_4.columnWidths = new int[] { 27, 62, 129, 57, 62, 0, 0, 90, 136 };

    gbl_panel_4.rowHeights = new int[] { 31, 28 };
    gbl_panel_4.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_4.rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
    panel_4.setLayout(gbl_panel_4);

    JLabel label_14 = new JLabel("货 号:");
    label_14.setHorizontalAlignment(4);
    GridBagConstraints gbc_label_14 = new GridBagConstraints();
    gbc_label_14.anchor = 13;
    gbc_label_14.fill = 3;
    gbc_label_14.insets = new Insets(0, 0, 5, 5);
    gbc_label_14.gridx = 1;
    gbc_label_14.gridy = 0;
    panel_4.add(label_14, gbc_label_14);
    final CashDialog.CallBack callBack = new CashDialog.CallBack() {
      public void updateView() {
        DefaultTableModel defaultTableModel = (DefaultTableModel)SellHelperPanel.this.table
          .getModel();
        int rowcount = SellHelperPanel.this.table.getRowCount();
        Flowlog[] flowlogs = new Flowlog[rowcount];
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        CustomMoudle customMoudle = 
          MoudleContentFactry.getCustomMoudle();
        String cno = SellHelperPanel.this.textField.getText();
        Custom custom = customMoudle.getCustomById(cno);

        BigDecimal temp = new BigDecimal(0);

        for (int i = 0; i < rowcount; i++) {
          String catno = String.valueOf(defaultTableModel.getValueAt(
            0, 0));
          String name = String.valueOf(defaultTableModel.getValueAt(
            0, 1));
          double amount = ((Double)defaultTableModel.getValueAt(0, 2)).doubleValue();
          BigDecimal sell = (BigDecimal)defaultTableModel
            .getValueAt(0, 3);
          BigDecimal costprice = (BigDecimal)defaultTableModel
            .getValueAt(0, 6);
          String type = String.valueOf(defaultTableModel.getValueAt(
            0, 7));
          Flowlog flowlog = new Flowlog();
          temp = temp.add(sell.multiply(new BigDecimal(amount)));
          String id = String.valueOf(UUID.randomUUID().toString()
            .replaceAll("-", ""));
          flowlog.setFlowno(id);
          flowlog.setCatno(catno);
          flowlog.setAmount(amount);
          BigDecimal dd = sell.subtract(costprice).multiply(
            new BigDecimal(amount));

          flowlog.setLrprice(dd);
          flowlog.setType(type);
          flowlog.setDate(SellHelperPanel.this.datePicker.getEditor().getText());
          flowlog.setCostprice(costprice);
          flowlog.setSellprice(sell);
          flowlog.setRecorddate(DateHelper.getNowDateTime());
          flowlog.setFlowflag("sell");
          flowlog.setStockname(name);

          if (custom != null) {
            flowlog.setCustomNo(custom.getId());
            flowlog.setCustomName(custom.getName());
          }
          flowlogs[i] = flowlog;
          stockMoudle.updateSyAmount(flowlog.getCatno(), flowlog
            .getAmount(), "-");
          defaultTableModel.removeRow(0);
        }
        FlowLogMoudle flowLogMoudle = 
          MoudleContentFactry.getFlowLogMoudle();
        flowLogMoudle.add(flowlogs);
        if (custom != null) {
          BigDecimal dd = custom.getAmount().add(temp);
          custom.setAmount(dd);
          custom.setIntegration(dd.doubleValue());
          custom.setFrequency(custom.getFrequency() + 1);
          customMoudle.update(custom);
        }
        SellHelperPanel.this.textField.setText("");
        SellHelperPanel.this.discountHiddenTextFiled.setText("10");
        SellHelperPanel.this.label_8.setText("");
        SellHelperPanel.this.label_11.setText("");
        SellHelperPanel.this.label_13.setText("");
        SellHelperPanel.this.textField_1.setText("");
        SellHelperPanel.this.label_16.setText("0.00");
        SellHelperPanel.this.lbll.setText("0");
      }
    };
    this.textField_1 = new SuggestTextField(new StockSuggestDataImpl(), 
      new SuggestTextField.MUIManager() {
      public void updateView(String[] args) {
        SellHelperPanel.this.label_1.setText("");
      }
    }
    , null, new SuggestTextField.MUIKeyEvent() {
      public void vkEnterENTER() {
        int rows = SellHelperPanel.this.table.getRowCount();

        String catno = SellHelperPanel.this.textField_1.getText();
        if (catno.trim().length() > 0) {
          DefaultTableModel defaultTableModel = (DefaultTableModel)SellHelperPanel.this.table
            .getModel();
          StockMoudle stockMoudle = 
            MoudleContentFactry.getStockMoudle();
          Stock stock = stockMoudle.getLastStockByNo(catno);
          if (stock == null) {
            SellHelperPanel.this.textField_1.setText("");
            SellHelperPanel.this.label_1.setText("库存中没有[" + catno + "]货物");
            return;
          }
          if (stock.getSyamount() <= 0.0D) {
            SellHelperPanel.this.label_1.setText("货物[" + catno + 
              "]库存为零，此货物不能出售");
            return;
          }
          SellHelperPanel.this.label_1.setText("");

          double suma = SellHelperPanel.this.maxStock(catno);
          double sl = 1.0D;
          if (suma + 1.0D > stock.getSyamount()) {
            double tempsy = suma + 1.0D - stock.getSyamount();
            if (tempsy + suma == stock.getSyamount()) {
              sl = tempsy;
            } else {
              SellHelperPanel.this.label_1.setText("货物[" + catno + 
                "]库存为零，此货物不能出售");
              return;
            }
          }

          BigDecimal discount = new BigDecimal("10");
          if ((SellHelperPanel.this.discountHiddenTextFiled.getText() != null) && (!"".equals(SellHelperPanel.this.discountHiddenTextFiled.getText()))) {
            discount = new BigDecimal(SellHelperPanel.this.discountHiddenTextFiled.getText());
          }

          BigDecimal sell = stock.getSellprice().multiply(discount).divide(new BigDecimal("10")).setScale(2, 4);

          BigDecimal sum = sell.multiply(new BigDecimal(1));

          Object[] rowData = { catno, 
            stock.getStockname(), Double.valueOf(sl), 
            sell, discount, sum, 
            stock.getCostprice(), stock.getType() };
          SellHelperPanel.this.textField_1.setText("");

          defaultTableModel.insertRow(rows, rowData);

          SellHelperPanel.this.table
            .setSelectionMode(0);
          SellHelperPanel.this.table.getSelectionModel().setSelectionInterval(
            rows, rows);

          if (SellHelperPanel.this.label_16.getText().trim().length() > 0) {
            String old = SellHelperPanel.this.label_16.getText().trim();
            BigDecimal sumold = new BigDecimal(old);
            SellHelperPanel.this.label_16.setText(
              String.valueOf(sum.add(sumold)));
          } else {
            SellHelperPanel.this.label_16.setText(String.valueOf(sum));
          }
          if (SellHelperPanel.this.lbll.getText().trim().length() > 0) {
            String olda = SellHelperPanel.this.lbll.getText().trim();
            double sumold = Double.parseDouble(olda) + 1.0D;
            SellHelperPanel.this.lbll.setText(String.valueOf(sumold));
          } else {
            SellHelperPanel.this.lbll.setText(String.valueOf(1));
          }
        } else if (rows > 0) {
          String zj = SellHelperPanel.this.label_16.getText();
          CashDialog cashDialog = new CashDialog(
            SellHelperPanel.sellHelperPanel, zj, zj, callBack);
          cashDialog.setVisible(true);
        }
      }
    });
    this.textField_1.setFocusable(true);
    String toolTipt = new String(
      "<html>提示&nbsp;&nbsp;<br>输入货号  按回车键<br>&nbsp;&nbsp;</html>");
    this.textField_1.setToolTipText(toolTipt);
    this.textField_1.setFont(new Font("宋体", 0, 14));
    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
    gbc_textField_1.fill = 1;
    gbc_textField_1.insets = new Insets(0, 0, 5, 5);
    gbc_textField_1.gridx = 2;
    gbc_textField_1.gridy = 0;
    panel_4.add(this.textField_1, gbc_textField_1);
    this.textField_1.setColumns(10);

    JButton button = new JButton("收银");
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
      }
    });
    button.setPreferredSize(new Dimension(57, 50));
    button.setSelectedIcon(new ImageIcon(SellHelperPanel.class
      .getResource("/com/bluebee/resource/image/50.png")));
    button.setPreferredSize(new Dimension(57, 50));
    button.setCursor(Cursor.getPredefinedCursor(12));
    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        int rows = SellHelperPanel.this.table.getRowCount();
        if (rows > 0) {
          String zj = SellHelperPanel.this.label_16.getText();
          CashDialog cashDialog = new CashDialog(SellHelperPanel.sellHelperPanel, zj, 
            zj, callBack);
          cashDialog.setVisible(true);
        }
      }
    });
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.fill = 3;
    gbc_button.anchor = 17;
    gbc_button.insets = new Insets(0, 0, 5, 5);
    gbc_button.gridx = 3;
    gbc_button.gridy = 0;
    panel_4.add(button, gbc_button);

    JButton button_1 = new JButton("修改");
    button_1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
      }
    });
    button_1.setCursor(Cursor.getPredefinedCursor(12));
    button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        int rows = SellHelperPanel.this.table.getRowCount();
        if (rows > 0) {
          SellDialog sellDialog = new SellDialog(SellHelperPanel.sellHelperPanel, 
            SellHelperPanel.this.table, SellHelperPanel.this.label_16, SellHelperPanel.this.lbll);
          sellDialog.setVisible(true);
        }
      }
    });
    GridBagConstraints gbc_button_1 = new GridBagConstraints();
    gbc_button_1.fill = 1;
    gbc_button_1.insets = new Insets(0, 0, 5, 5);
    gbc_button_1.gridx = 4;
    gbc_button_1.gridy = 0;
    panel_4.add(button_1, gbc_button_1);

    JButton button_2 = new JButton("删除");
    button_2.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int nRow = SellHelperPanel.this.table.getSelectedRow();
        int rowCount = SellHelperPanel.this.table.getRowCount();
        if ((nRow > -1) && (rowCount > nRow)) {
          DefaultTableModel tableModel = (DefaultTableModel)SellHelperPanel.this.table
            .getModel();

          double amount = ((Double)tableModel.getValueAt(nRow, 2)).doubleValue();
          BigDecimal sellSum = (BigDecimal)tableModel
            .getValueAt(nRow, 5);
          if (SellHelperPanel.this.label_16.getText().trim().length() > 0) {
            String old = SellHelperPanel.this.label_16.getText().trim();
            BigDecimal sumold = new BigDecimal(old);
            SellHelperPanel.this.label_16.setText(
              String.valueOf(sumold.add(sellSum.negate()).setScale(2)));
          } else {
            SellHelperPanel.this.label_16.setText("0.00");
          }
          if (SellHelperPanel.this.lbll.getText().trim().length() > 0) {
            String olda = SellHelperPanel.this.lbll.getText().trim();
            double sumold = Double.parseDouble(olda) - amount;
            SellHelperPanel.this.lbll.setText(String.valueOf(sumold));
          } else {
            SellHelperPanel.this.lbll.setText("0");
          }

          tableModel.removeRow(nRow);
          int newrowCount = SellHelperPanel.this.table.getRowCount();
          SellHelperPanel.this.table.setSelectionMode(0);
          if (newrowCount == 1)
            SellHelperPanel.this.table.getSelectionModel().setSelectionInterval(0, 0);
          else
            SellHelperPanel.this.table.getSelectionModel().setSelectionInterval(
              nRow - 1, nRow - 1);
        }
      }
    });
    button_2.setCursor(Cursor.getPredefinedCursor(12));
    GridBagConstraints gbc_button_2 = new GridBagConstraints();
    gbc_button_2.fill = 3;
    gbc_button_2.anchor = 17;
    gbc_button_2.insets = new Insets(0, 0, 5, 5);
    gbc_button_2.gridx = 5;
    gbc_button_2.gridy = 0;
    panel_4.add(button_2, gbc_button_2);

    JLabel label_15 = new JLabel("总计:");
    label_15.setFont(new Font("华文中宋", 0, 25));
    GridBagConstraints gbc_label_15 = new GridBagConstraints();
    gbc_label_15.anchor = 13;
    gbc_label_15.fill = 3;
    gbc_label_15.insets = new Insets(0, 0, 5, 5);
    gbc_label_15.gridx = 7;
    gbc_label_15.gridy = 0;
    panel_4.add(label_15, gbc_label_15);

    this.label_16 = new JLabel("0.00");
    this.label_16.setFont(new Font("华文中宋", 0, 24));
    GridBagConstraints gbc_lblL1 = new GridBagConstraints();
    gbc_lblL1.anchor = 17;
    gbc_lblL1.insets = new Insets(0, 0, 5, 0);
    gbc_lblL1.gridx = 8;
    gbc_lblL1.gridy = 0;
    panel_4.add(this.label_16, gbc_lblL1);

    this.label_1 = new JLabel("");
    this.label_1.setForeground(Color.RED);
    this.label_1.setFont(new Font("宋体", 0, 15));
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.anchor = 17;
    gbc_label_1.gridwidth = 4;
    gbc_label_1.insets = new Insets(0, 0, 0, 5);
    gbc_label_1.gridx = 2;
    gbc_label_1.gridy = 1;
    panel_4.add(this.label_1, gbc_label_1);

    JLabel label_18 = new JLabel("数量:");
    label_18.setFont(new Font("华文中宋", 0, 24));
    GridBagConstraints gbc_label_18 = new GridBagConstraints();
    gbc_label_18.anchor = 13;
    gbc_label_18.fill = 3;
    gbc_label_18.insets = new Insets(0, 0, 0, 5);
    gbc_label_18.gridx = 7;
    gbc_label_18.gridy = 1;
    panel_4.add(label_18, gbc_label_18);

    this.lbll = new JLabel("0");
    this.lbll.setFont(new Font("华文中宋", 0, 25));
    GridBagConstraints gbc_lbll = new GridBagConstraints();
    gbc_lbll.fill = 1;
    gbc_lbll.gridx = 8;
    gbc_lbll.gridy = 1;
    panel_4.add(this.lbll, gbc_lbll);
    addAncestorListener(new AncestorListener() {
      public void ancestorAdded(AncestorEvent evt) {
        SellHelperPanel.this.textField_1.requestFocus();
      }

      public void ancestorRemoved(AncestorEvent evt)
      {
      }

      public void ancestorMoved(AncestorEvent evt)
      {
      }
    });
    JPanel panel_3 = new JPanel();
    TitledBorder titledBordertd = BorderFactory.createTitledBorder(
      loweredetched, "");
    panel_3.setBorder(titledBordertd);
    add(panel_3, "South");
    GridBagLayout gbl_panel_3 = new GridBagLayout();
    gbl_panel_3.columnWidths = new int[] { 26, 54, 135 };
    gbl_panel_3.rowHeights = new int[] { 17, 15 };
    gbl_panel_3.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 
      4.9E-324D };
    gbl_panel_3.rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
    panel_3.setLayout(gbl_panel_3);

    JLabel lblqq = new JLabel(
      "欢迎使用BlueBee蓝蜜蜂记账系统，BlueBee蓝蜜蜂记账系统是完全免费使用的软件，如需帮助请加入BlueBee蓝蜜蜂QQ群:149012385");
    lblqq.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_lblqq = new GridBagConstraints();
    gbc_lblqq.anchor = 17;
    gbc_lblqq.gridwidth = 4;
    gbc_lblqq.insets = new Insets(0, 0, 5, 0);
    gbc_lblqq.gridx = 1;
    gbc_lblqq.gridy = 0;
    panel_3.add(lblqq, gbc_lblqq);

    JLabel label_17 = new JLabel("免费、好用、灵巧");
    label_17.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_label_17 = new GridBagConstraints();
    gbc_label_17.anchor = 13;
    gbc_label_17.gridx = 4;
    gbc_label_17.gridy = 1;
    panel_3.add(label_17, gbc_label_17);
  }

  public static SellHelperPanel getInstance()
  {
    return sellHelperPanel;
  }

  private double maxStock(String cno) {
    int row = this.table.getRowCount();
    double a = 0.0D;
    if (row > 0) {
      for (int i = 0; i < row; i++) {
        String ccno = String.valueOf(this.table.getValueAt(i, 0));
        if (ccno.equals(cno)) {
          a += ((Double)this.table.getValueAt(i, 2)).doubleValue();
        }
      }
    }
    return a;
  }

  private void updateViewByCustomId() {
    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table
      .getModel();
    int rowcount = this.table.getRowCount();
    StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();

    BigDecimal sumAmount = new BigDecimal("0");
    BigDecimal sumSell = new BigDecimal("0");

    for (int i = 0; i < rowcount; i++) {
      String catno = String.valueOf(defaultTableModel.getValueAt(i, 0));

      double amount = ((Double)defaultTableModel.getValueAt(i, 2)).doubleValue();

      Stock stock = stockMoudle.getLastStockByNo(catno);

      BigDecimal discount = new BigDecimal("10");
      if ((this.discountHiddenTextFiled.getText() != null) && (!"".equals(this.discountHiddenTextFiled.getText()))) {
        discount = new BigDecimal(this.discountHiddenTextFiled.getText());
      }

      BigDecimal sell = stock.getSellprice().multiply(discount).divide(new BigDecimal("10")).setScale(2, 4);

      BigDecimal sum = sell.multiply(new BigDecimal(amount));

      defaultTableModel.setValueAt(sell, i, 3);
      defaultTableModel.setValueAt(discount, i, 4);
      defaultTableModel.setValueAt(sum, i, 5);

      sumAmount = sumAmount.add(new BigDecimal(amount));
      sumSell = sumSell.add(sum);
    }

    this.label_16.setText(String.valueOf(sumSell.setScale(2)));
    this.lbll.setText(String.valueOf(sumAmount));
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>售货帮手";
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