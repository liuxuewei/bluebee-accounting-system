package com.bluebee.ui.widget;

import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.pojo.Stock;
import com.bluebee.ui.LimitedDocument;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class SellDialog extends JDialog
{
  private String dubString = "1234567890.";
  private DecimalFormat df = new DecimalFormat("##.00");
  private JTextField textField;
  private JTextField textField_1;
  private JTable jTable;
  private JLabel label_16;
  private JLabel lbll;
  private JLabel label_7;
  private JTextField textField_2 = new JTextField();

  public SellDialog(Component owner, JTable jTablex, JLabel label_16x, JLabel lbllx)
  {
    setTitle("销售修改");
    setResizable(false);
    setSize(new Dimension(294, 320));
    setLocationRelativeTo(owner);
    setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    setDefaultCloseOperation(2);
    getContentPane().setLayout(new BorderLayout(0, 0));
    this.jTable = jTablex;
    this.label_16 = label_16x;
    this.lbll = lbllx;
    int row = this.jTable.getSelectedRow();
    JPanel panel = new JPanel();
    Border loweredetched = 
      BorderFactory.createEtchedBorder(0);
    panel.setBorder(loweredetched);
    panel.setPreferredSize(new Dimension(10, 100));
    getContentPane().add(panel, "North");
    panel.setLayout(null);

    JLabel label = new JLabel("货号");
    label.setFont(new Font("宋体", 0, 16));
    label.setBounds(70, 11, 32, 15);
    panel.add(label);
    final String cartno = String.valueOf(this.jTable.getValueAt(row, 0));
    JLabel label_1 = new JLabel(cartno);
    label_1.setBounds(112, 11, 147, 15);
    panel.add(label_1);

    JLabel label_2 = new JLabel("名称");
    label_2.setFont(new Font("宋体", 0, 15));
    label_2.setBounds(70, 36, 32, 15);
    panel.add(label_2);
    String name = String.valueOf(this.jTable.getValueAt(row, 1));
    JLabel label_3 = new JLabel(name);
    label_3.setBounds(112, 36, 147, 15);
    panel.add(label_3);

    JLabel label_6 = new JLabel("原售价");
    label_6.setHorizontalAlignment(4);
    label_6.setFont(new Font("宋体", 0, 15));
    label_6.setBounds(34, 62, 68, 18);
    panel.add(label_6);

    StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
    Stock stock = stockMoudle.getLastStockByNo(cartno);

    this.label_7 = new JLabel(String.valueOf(stock.getSellprice()));
    this.label_7.setBounds(112, 65, 147, 15);
    panel.add(this.label_7);

    JPanel panel_1 = new JPanel();
    getContentPane().add(panel_1, "Center");
    panel_1.setLayout(null);

    JLabel label_4 = new JLabel("数量");
    label_4.setFont(new Font("宋体", 0, 20));
    label_4.setBounds(40, 24, 40, 20);
    panel_1.add(label_4);

    JLabel label_5 = new JLabel("售价");
    label_5.setFont(new Font("宋体", 0, 20));
    label_5.setBounds(40, 57, 40, 29);
    panel_1.add(label_5);
    String shul = String.valueOf(this.jTable.getValueAt(row, 2));
    this.textField = new JTextField();
    this.textField.setDocument(new LimitedDocument(20, this.dubString));
    this.textField.setFont(new Font("宋体", 0, 15));
    this.textField.setText(shul);
    this.textField.setBounds(94, 18, 136, 29);
    panel_1.add(this.textField);
    this.textField.setColumns(10);

    String shoujia = String.valueOf(this.jTable.getValueAt(row, 3));
    this.textField_1 = new JTextField();

    this.textField_1.addFocusListener(new FocusAdapter()
    {
      public void focusLost(FocusEvent arg0) {
        try {
          BigDecimal orgSellPrice = new BigDecimal(SellDialog.this.label_7.getText());
          BigDecimal nowSellPrice = new BigDecimal(SellDialog.this.textField_1.getText());

          BigDecimal zhekou = nowSellPrice.divide(orgSellPrice, 4, 4).multiply(new BigDecimal("10")).setScale(2, 4);
          SellDialog.this.textField_2.setText(String.valueOf(zhekou));
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
      }
    });
    this.textField_1.setDocument(new LimitedDocument(20, this.dubString));
    this.textField_1.setFont(new Font("宋体", 0, 15));
    this.textField_1.setBounds(94, 57, 136, 29);
    this.textField_1.setText(shoujia);
    panel_1.add(this.textField_1);
    this.textField_1.setColumns(10);

    String zhekou = String.valueOf(this.jTable.getValueAt(row, 4));
    this.textField_2.addFocusListener(new FocusAdapter()
    {
      public void focusLost(FocusEvent arg0) {
        try {
          BigDecimal orgSellPrice = new BigDecimal(SellDialog.this.label_7.getText());
          BigDecimal zhekou = new BigDecimal(SellDialog.this.textField_2.getText());

          BigDecimal sellPrice = orgSellPrice.multiply(zhekou).divide(new BigDecimal("10"), 2, 4);
          SellDialog.this.textField_1.setText(String.valueOf(sellPrice));
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
      }
    });
    this.textField_2.setDocument(new LimitedDocument(20, this.dubString));
    this.textField_2.setFont(new Font("宋体", 0, 15));
    this.textField_2.setColumns(4);
    this.textField_2.setBounds(94, 97, 136, 29);
    panel_1.add(this.textField_2);
    this.textField_2.setText(zhekou);

    JButton button = new JButton("修改");
    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        String sltext = SellDialog.this.textField.getText().trim();
        if (sltext.length() == 0) {
          sltext = "0";
        }

        double sl = Double.parseDouble(sltext);

        String sj = SellDialog.this.textField_1.getText().trim();
        if (sj.length() == 0) {
          sj = "0";
        }
        int row = SellDialog.this.jTable.getSelectedRow();
        int rowCount = SellDialog.this.jTable.getRowCount();
        DefaultTableModel defaultTableModel = (DefaultTableModel)SellDialog.this.jTable.getModel();
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        Stock stock = stockMoudle.getLastStockByNo(cartno);

        double oldsl = ((Double)SellDialog.this.jTable.getValueAt(row, 2)).doubleValue();
        Double d = Double.valueOf(SellDialog.this.maxStock(cartno) - oldsl);
        if (d.doubleValue() < stock.getSyamount()) {
          if (sl > stock.getSyamount())
          {
            sl = stock.getSyamount() - d.doubleValue();
          }
          else if (sl + d.doubleValue() > stock.getSyamount())
            sl = stock.getSyamount() - d.doubleValue();
          else {
            sl = sl;
          }

        }

        BigDecimal sell = new BigDecimal(sj);

        BigDecimal discount = new BigDecimal(SellDialog.this.textField_2.getText());

        BigDecimal sum = sell.multiply(new BigDecimal(sl));
        sum = sum.setScale(2, 4);

        defaultTableModel.setValueAt(Double.valueOf(Double.parseDouble(SellDialog.this.df.format(sl))), 
          row, 2);
        defaultTableModel.setValueAt(sell, row, 3);
        defaultTableModel.setValueAt(discount, row, 4);
        defaultTableModel.setValueAt(sum, row, 5);
        double ashul = 0.0D;
        BigDecimal hezji = new BigDecimal(0);
        hezji = hezji.setScale(2, 4);
        for (int i = 0; i < rowCount; i++) {
          ashul += ((Double)defaultTableModel.getValueAt(i, 2)).doubleValue();
          hezji = hezji.add(
            (BigDecimal)defaultTableModel
            .getValueAt(i, 5));
        }

        SellDialog.this.label_16.setText(String.valueOf(hezji));
        SellDialog.this.lbll.setText(SellDialog.this.df.format(ashul));
        SellDialog.this.dispose();
      }
    });
    button.setFont(new Font("新宋体", 0, 12));
    button.setBounds(94, 156, 63, 29);
    panel_1.add(button);

    JButton button_1 = new JButton("放弃");
    button_1.setFont(new Font("宋体", 0, 12));
    button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        SellDialog.this.dispose();
      }
    });
    button_1.setBounds(167, 156, 63, 29);
    panel_1.add(button_1);

    JLabel label_8 = new JLabel("折扣");
    label_8.setFont(new Font("宋体", 0, 20));
    label_8.setBounds(40, 97, 40, 29);
    panel_1.add(label_8);
  }

  private double maxStock(String cno)
  {
    int row = this.jTable.getRowCount();
    double a = 0.0D;
    if (row > 0) {
      for (int i = 0; i < row; i++) {
        String ccno = String.valueOf(this.jTable.getValueAt(i, 0));
        if (ccno.equals(cno)) {
          a += ((Double)this.jTable.getValueAt(i, 2)).doubleValue();
        }
      }
    }
    return a;
  }

  public static void main(String[] args) {
    String ddd = "3456.440000000000004545";
    BigDecimal hezji = new BigDecimal(ddd);
    hezji.setScale(2, 4);
    NumberFormat format = NumberFormat.getInstance();
    System.out.println(new BigDecimal(hezji.doubleValue()));
  }
}