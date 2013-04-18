package com.bluebee.ui.widget;

import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.OptionMoudle;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.pojo.Option;
import com.bluebee.pojo.Stock;
import com.bluebee.ui.LimitedDocument;
import com.bluebee.util.DateHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;

public class KuCunDialog extends JDialog
{
  private String intString = "1234567890";
  private String dubString = "1234567890.";
  private JXDatePicker datePicker;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField textField_4;
  private JTextField textField_5;
  private JTextField textField_6;
  private JComboBox comboBox;
  private JLabel label_8;
  private JLabel label_9;
  private JTable tablex;
  private Stock stockx;
  private CallBack callBackx;
  private JTextField textField_7;

  public KuCunDialog(Component owner, String tilte, Stock stock, JTable table, CallBack callBack)
  {
    setResizable(false);
    setTitle(tilte);
    this.stockx = stock;
    this.tablex = table;
    this.callBackx = callBack;
    setSize(new Dimension(434, 381));
    setLocationRelativeTo(owner);

    setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    setDefaultCloseOperation(2);
    getContentPane().setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "货物信息", 4, 2, null, null));
    getContentPane().add(panel, "Center");
    panel.setLayout(null);

    JLabel label = new JLabel("货号：");
    label.setBounds(57, 74, 36, 15);
    panel.add(label);

    this.textField = new JTextField();
    if (this.stockx != null) {
      this.textField.setEditable(false);
    }

    this.textField.setBounds(97, 71, 125, 21);
    this.textField.setColumns(10);
    panel.add(this.textField);

    JLabel label_1 = new JLabel("名称：");
    label_1.setBounds(57, 102, 36, 15);
    panel.add(label_1);

    this.textField_1 = new JTextField();
    this.textField_1.setBounds(97, 99, 209, 21);
    this.textField_1.setColumns(10);
    panel.add(this.textField_1);

    JLabel label_2 = new JLabel("类别：");
    label_2.setBounds(57, 131, 36, 15);
    panel.add(label_2);

    this.comboBox = new JComboBox();
    this.comboBox.setEditable(true);
    this.comboBox.setModel(new DefaultComboBoxModel(SelectType.getOptions("HWLX")));
    this.comboBox.setBounds(97, 128, 209, 21);
    panel.add(this.comboBox);

    JLabel label_3 = new JLabel("颜色：");
    label_3.setBounds(57, 159, 36, 15);
    panel.add(label_3);

    this.textField_2 = new JTextField();
    this.textField_2.setBounds(97, 156, 125, 21);
    this.textField_2.setColumns(10);
    panel.add(this.textField_2);

    JLabel label_4 = new JLabel("规格：");
    label_4.setBounds(57, 190, 36, 15);
    panel.add(label_4);

    this.textField_3 = new JTextField();
    this.textField_3.setBounds(97, 187, 125, 21);
    this.textField_3.setColumns(10);
    panel.add(this.textField_3);

    JLabel label_5 = new JLabel("成本：");
    label_5.setBounds(57, 221, 36, 15);
    panel.add(label_5);

    JLabel label_6 = new JLabel("售价：");
    label_6.setBounds(202, 221, 36, 15);
    panel.add(label_6);

    JLabel label_7 = new JLabel("进货数量：");
    label_7.setBounds(33, 253, 60, 15);
    panel.add(label_7);

    this.textField_4 = new JTextField();
    this.textField_4.setBounds(97, 218, 66, 21);
    this.textField_4.setDocument(new LimitedDocument(20, this.dubString));
    this.textField_4.setColumns(10);
    panel.add(this.textField_4);

    this.textField_5 = new JTextField();
    this.textField_5.setBounds(240, 218, 66, 21);
    this.textField_5.setDocument(new LimitedDocument(20, this.dubString));
    this.textField_5.setColumns(10);
    panel.add(this.textField_5);

    this.textField_6 = new JTextField();
    this.textField_6.setBounds(97, 250, 66, 21);
    this.textField_6.setDocument(new LimitedDocument(20, this.intString));
    this.textField_6.setColumns(10);
    panel.add(this.textField_6);

    JButton button = new JButton("保存");
    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        ((JButton)e.getSource()).setEnabled(false);
        KuCunDialog.this.submmit();
        ((JButton)e.getSource()).setEnabled(true);
      }
    });
    button.setBounds(146, 297, 93, 30);
    panel.add(button);

    JButton button_1 = new JButton("取消");
    button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        KuCunDialog.this.dispose();
      }
    });
    button_1.setBounds(259, 297, 93, 30);
    panel.add(button_1);

    this.label_8 = new JLabel("");
    this.label_8.setForeground(Color.RED);
    this.label_8.setBounds(57, 20, 335, 15);
    panel.add(this.label_8);

    this.label_9 = new JLabel("");
    this.label_9.setBounds(232, 74, 131, 15);
    panel.add(this.label_9);

    JLabel label_10 = new JLabel("时间：");
    label_10.setBounds(57, 45, 36, 15);
    panel.add(label_10);

    this.datePicker = new JXDatePicker();
    this.datePicker.getEditor().setFont(new Font("宋体", 1, 12));
    this.datePicker.getEditor().setEditable(false);
    this.datePicker.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker.setDate(DateHelper.currentDate());
    this.datePicker.setBounds(97, 41, 104, 23);
    panel.add(this.datePicker);

    JLabel label_11 = new JLabel("剩余数量：");
    label_11.setBounds(178, 253, 60, 15);
    panel.add(label_11);

    this.textField_7 = new JTextField();
    this.textField_7.setBounds(240, 250, 66, 21);
    panel.add(this.textField_7);
    this.textField_7.setColumns(10);
    if (this.stockx != null)
      setValues();
  }

  private void submmit()
  {
    Stock stock = getFormdata();
    if (stock != null) {
      setFormdata(stock);
      this.label_8.setText("进货数据保存成功!");
      clear();
      dispose();
    }
  }

  private void setValues() {
    this.textField.setText(this.stockx.getCatno());
    this.textField_1.setText(this.stockx.getStockname());
    this.textField_2.setText(this.stockx.getColor());
    this.textField_3.setText(this.stockx.getSpecif());
    this.textField_4.setText(this.stockx.getCostprice().toString());
    this.textField_5.setText(this.stockx.getSellprice().toString());
    this.textField_6.setText(String.valueOf(this.stockx.getAmount()));
    this.textField_7.setText(String.valueOf(this.stockx.getSyamount()));

    String type = this.stockx.getType();
    ComboBoxModel comboBoxModel = this.comboBox.getModel();
    int n = comboBoxModel.getSize();
    for (int i = 0; i < n; i++) {
      Object ob = comboBoxModel.getElementAt(i);
      if (String.valueOf(ob).equals(type)) {
        this.comboBox.setSelectedIndex(i);
        break;
      }
    }
  }

  private void clear()
  {
    this.textField.setText("");
    this.textField_1.setText("");
    this.textField_2.setText("");
    this.textField_3.setText("");
    this.textField_4.setText("");
    this.textField_6.setText("");
    this.textField_5.setText("");
    this.label_9.setText("");
  }

  private Stock getFormdata() {
    String catno = this.textField.getText();
    String name = this.textField_1.getText();
    String type = this.comboBox.getEditor().getItem().toString();
    String color = this.textField_2.getText();
    String specif = this.textField_3.getText();
    String costprice = this.textField_4.getText();
    String amount = this.textField_6.getText();
    String syamount = this.textField_7.getText();
    String sellprice = this.textField_5.getText();
    String date = this.datePicker.getEditor().getText();

    String nows = DateHelper.getNowDateTime();
    if ((date == null) || (date.trim().length() == 0)) {
      date = DateHelper.getNowDateTime();
    }
    long now = Long.parseLong(nows.replaceAll("-", ""));
    long e = Long.parseLong(date.replaceAll("-", ""));
    if (e > now) {
      this.label_8.setText("输入时间错误！只能录入今天以前的进货数据");
      return null;
    }
    if ((catno == null) || (catno.trim().length() == 0)) {
      this.label_8.setText("请输入货物号码");
      return null;
    }
    if ((name == null) || (name.trim().length() == 0)) {
      this.label_8.setText("请输入货物名称");
      return null;
    }

    if ((type.trim().length() == 0) && (this.comboBox.getItemCount() == 0)) {
      this.label_8.setText("请设置货物类型!");
      return null;
    }
    if ((type == null) || (type.trim().length() == 0)) {
      this.label_8.setText("请设置货物类型!");
      return null;
    }

    boolean is = SelectType.isequals(type, "HWLX");
    if (!is) {
      OptionMoudle optionMoudle = 
        MoudleContentFactry.getOptionMoudle();
      Option option = new Option();
      option.setId(String.valueOf(System.currentTimeMillis()));
      option.setText(type);
      option.setType("HWLX");
      boolean isd = optionMoudle.addOption(option);
      if (isd) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel)this.comboBox.getModel();
        comboBoxModel.insertElementAt(type, 0);
      }

    }

    if ((costprice == null) || (costprice.trim().length() == 0)) {
      this.label_8.setText("请成本价格");
      return null;
    }

    if ((sellprice == null) || (sellprice.trim().length() == 0)) {
      this.label_8.setText("请输入货物销售价格");
      return null;
    }
    if ((amount == null) || (amount.trim().length() == 0)) {
      this.label_8.setText("请输入数量");
      return null;
    }
    if ((syamount == null) || (syamount.trim().length() == 0)) {
      this.label_8.setText("请输入剩余数量");
      return null;
    }
    BigDecimal bigDecostprice = new BigDecimal(costprice);
    BigDecimal bigDesellprice = new BigDecimal(sellprice);
    if (bigDecostprice.compareTo(new BigDecimal(0)) == 0) {
      this.label_8.setText("成本价格应该大于零,请重新输入!");
      this.textField_4.selectAll();
      this.textField_4.requestFocus();
      return null;
    }
    if (bigDesellprice.compareTo(bigDecostprice) == -1) {
      this.label_8.setText("输入货物销售价格小于成本价,请重新输入!");
      this.textField_5.selectAll();
      this.textField_5.requestFocus();
      return null;
    }
    if ((this.label_8.getText() != null) && (this.label_8.getText().trim().length() > 0)) {
      this.label_8.setText("");
    }

    double amounts = Double.parseDouble(amount);

    if (amounts == 0.0D) {
      this.label_8.setText("输入数量应该大于零！");
      this.textField_6.selectAll();
      this.textField_6.requestFocus();
      return null;
    }
    double syamounts = Double.parseDouble(syamount);
    if (syamounts < 0.0D) {
      this.label_8.setText("输入剩余数量应该大于等于零！");
      this.textField_7.selectAll();
      this.textField_7.requestFocus();
      return null;
    }
    if (syamounts > amounts) {
      this.label_8.setText("输入剩余数量应该小于等于进货数量！");
      this.textField_7.selectAll();
      this.textField_7.requestFocus();
      return null;
    }

    BigDecimal costps = bigDecostprice.multiply(BigDecimal.valueOf(amounts));
    Stock stock = new Stock();
    stock.setId(this.stockx.getId());
    stock.setCatno(catno);
    stock.setAmount(amounts);
    stock.setSyamount(syamounts);
    stock.setCostprice(bigDecostprice);
    stock.setSellprice(bigDesellprice);
    stock.setTotal(costps);
    stock.setType(type);
    stock.setDate(date);
    if (specif.trim().length() > 0) {
      stock.setSpecif(specif);
    }
    if (color.trim().length() > 0) {
      stock.setColor(color);
    }
    stock.setStockname(name);
    stock.setStockFlag("JINHUO");
    stock.setRecorddate(DateHelper.getNowDateTime());
    return stock;
  }

  public void setFormdata(Stock stock) {
    Object[] rowData = { this.stockx.getId(), stock.getCatno(), 
      Double.valueOf(stock.getSyamount()), Double.valueOf(stock.getAmount()), stock.getType(), stock.getSellprice(), stock.getCostprice(), 
      stock.getStockname(), stock.getSpecif(), stock.getColor(), stock.getDate(), "删除" };
    StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
    if (this.stockx != null) {
      stockMoudle.updateStock(stock);
      int row = this.tablex.getSelectedRow();
      DefaultTableModel tableModel = (DefaultTableModel)this.tablex.getModel();
      tableModel.removeRow(row);
      tableModel.insertRow(row, rowData);
    }

    if (this.callBackx != null)
      this.callBackx.updateView();
  }

  public static abstract interface CallBack
  {
    public abstract void updateView();
  }
}