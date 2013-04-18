package com.bluebee.ui.frame;

import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.pojo.Stock;
import com.bluebee.ui.LimitedDocument;
import com.bluebee.ui.widget.SuggestTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CgthPane extends JPanel
  implements IPage
{
  private static CgthPane cgthPane = new CgthPane();

  private String intString = "1234567890.";
  private JLabel label_1;
  private SuggestTextField suggestTextField;
  private boolean isDefaultPage = false;
  private JTable table;
  private JTextField textField_1;

  public static CgthPane getInstance()
  {
    return cgthPane;
  }

  private CgthPane() {
    setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    add(panel, "North");
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[] { 49, 0, 108, 13, 61, 0, 98 };
    gbl_panel.rowHeights = new int[] { 19, 31, 8 };
    gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel.setLayout(gbl_panel);

    this.label_1 = new JLabel("");
    this.label_1.setForeground(Color.RED);
    this.label_1.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.anchor = 17;
    gbc_label_1.gridwidth = 6;
    gbc_label_1.insets = new Insets(0, 0, 5, 0);
    gbc_label_1.gridx = 1;
    gbc_label_1.gridy = 0;
    panel.add(this.label_1, gbc_label_1);

    JLabel label = new JLabel("货号");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 5, 5);
    gbc_label.anchor = 13;
    gbc_label.gridx = 1;
    gbc_label.gridy = 1;
    panel.add(label, gbc_label);

    this.suggestTextField = new SuggestTextField(new StockSuggestDataImpl(), null, null, null);
    this.suggestTextField.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_suggestTextField = new GridBagConstraints();
    gbc_suggestTextField.insets = new Insets(0, 0, 5, 5);
    gbc_suggestTextField.fill = 2;
    gbc_suggestTextField.gridx = 2;
    gbc_suggestTextField.gridy = 1;
    panel.add(this.suggestTextField, gbc_suggestTextField);

    JLabel label_2 = new JLabel("数量");
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.anchor = 13;
    gbc_label_2.insets = new Insets(0, 0, 5, 5);
    gbc_label_2.gridx = 3;
    gbc_label_2.gridy = 1;
    panel.add(label_2, gbc_label_2);

    this.textField_1 = new JTextField();
    this.textField_1.setText("1");
    this.textField_1.setFont(new Font("宋体", 1, 12));
    this.textField_1.setDocument(new LimitedDocument(10, this.intString));
    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
    gbc_textField_1.insets = new Insets(0, 0, 5, 5);
    gbc_textField_1.fill = 2;
    gbc_textField_1.gridx = 4;
    gbc_textField_1.gridy = 1;
    panel.add(this.textField_1, gbc_textField_1);
    this.textField_1.setColumns(10);

    JButton button = new JButton("  退货  ");
    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        String nosss = CgthPane.this.suggestTextField.getText();
        if (nosss.trim().length() == 0) {
          CgthPane.this.label_1.setText("请输入退货货号");
          return;
        }
        String num = CgthPane.this.textField_1.getText();
        if (num.trim().length() == 0) {
          CgthPane.this.label_1.setText("请输入退货数量");
          return;
        }
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        Stock tStock = stockMoudle.getStockByNo(nosss);
        double amount = stockMoudle.sumStockAmount(nosss);
        double syamount = stockMoudle.sumStockSyAmount(nosss);
        if (tStock == null) {
          CgthPane.this.label_1.setText("输入货号库存中不存在!请重新输入");
          return;
        }
        if ((Double.parseDouble(num) > amount) || (Double.parseDouble(num) > syamount)) {
          CgthPane.this.label_1.setText("输入退货数量大于库存货物数量！ 请重新输入");
          return;
        }

        int response = JOptionPane.showConfirmDialog(null, 
        		 "确定退货[" + nosss + "]数据?", "采购退货", 0);
        switch (response) {
        case 0:
          Object[] rowData = { 
            tStock.getId(), 
            tStock.getCatno(), 
            Double.valueOf(tStock.getSyamount()), 
            tStock.getCostprice(), 
            tStock.getType(), 
            tStock.getStockname(), 
            tStock.getDate(), 
          "删除" };

          DefaultTableModel tableModel = (DefaultTableModel)CgthPane.this.table.getModel();
          tableModel.insertRow(0, rowData);
          tStock.setAmount(tStock.getAmount() - Double.parseDouble(num));
          tStock.setSyamount(tStock.getSyamount() - Double.parseDouble(num));
          stockMoudle.updateStock(tStock);
          CgthPane.this.label_1.setText("退货入库成功");
        case -1:
        case 1:
        }
      }
    });
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.insets = new Insets(0, 0, 5, 0);
    gbc_button.gridx = 6;
    gbc_button.gridy = 1;
    panel.add(button, gbc_button);

    JLabel label_3 = new JLabel("");
    GridBagConstraints gbc_label_3 = new GridBagConstraints();
    gbc_label_3.gridwidth = 3;
    gbc_label_3.insets = new Insets(0, 0, 0, 5);
    gbc_label_3.gridx = 1;
    gbc_label_3.gridy = 2;
    panel.add(label_3, gbc_label_3);

    JPanel panel_1 = new JPanel();
    add(panel_1, "Center");
    panel_1.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    panel_1.add(scrollPane, "Center");

    this.table = new JTable();
    this.table.setModel(new DefaultTableModel(
      null, 
      new String[] { 
    		  "ID", "货号", "剩余数量", "成本", "类型", "名称", "进货时间", "操作" }));

    this.table.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(0).setMinWidth(0);
    this.table.getColumnModel().getColumn(0).setMaxWidth(0);
    scrollPane.setViewportView(this.table);
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
	  return ">>>采购退货";
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