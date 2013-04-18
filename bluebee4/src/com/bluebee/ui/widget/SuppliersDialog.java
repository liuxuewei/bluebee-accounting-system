package com.bluebee.ui.widget;

import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.SuppliersMoudle;
import com.bluebee.pojo.Suppliers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class SuppliersDialog extends JDialog
{
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField textField_4;
  private JTextField textField_5;
  private JTextField textField_6;
  private JTextField textField_7;
  private JTextField textField_8;
  private JLabel msg;
  private JTable jTable;

  public SuppliersDialog(Component owner, String tilte, final Suppliers suppliers, JTable table)
  {
    setResizable(false);
    setTitle(tilte);
    setSize(new Dimension(464, 364));
    setLocationRelativeTo(owner);
    setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    setDefaultCloseOperation(2);
    getContentPane().setLayout(new BorderLayout(0, 0));
    this.jTable = table;
    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "供应商资料", 4, 2, null, null));
    getContentPane().add(panel, "Center");
    panel.setLayout(null);

    JLabel label = new JLabel("供应商名称");
    label.setBounds(28, 47, 71, 17);
    panel.add(label);

    this.textField = new JTextField();
    this.textField.setBounds(100, 44, 309, 23);
    panel.add(this.textField);
    this.textField.setColumns(10);

    JLabel label_1 = new JLabel("供应商地址");
    label_1.setBounds(28, 78, 71, 17);
    panel.add(label_1);

    this.textField_1 = new JTextField();
    this.textField_1.setBounds(100, 75, 309, 23);
    panel.add(this.textField_1);
    this.textField_1.setColumns(10);

    JLabel label_2 = new JLabel("联系人");
    label_2.setBounds(52, 109, 47, 17);
    panel.add(label_2);

    this.textField_2 = new JTextField();
    this.textField_2.setBounds(100, 106, 116, 23);
    panel.add(this.textField_2);
    this.textField_2.setColumns(10);

    JLabel label_3 = new JLabel("联系电话");
    label_3.setBounds(239, 109, 58, 17);
    panel.add(label_3);

    this.textField_3 = new JTextField();
    this.textField_3.setBounds(293, 106, 116, 23);
    panel.add(this.textField_3);
    this.textField_3.setColumns(10);

    JLabel lblQq = new JLabel("QQ");
    lblQq.setBounds(65, 143, 34, 17);
    panel.add(lblQq);

    this.textField_4 = new JTextField();
    this.textField_4.setBounds(100, 140, 116, 23);
    panel.add(this.textField_4);
    this.textField_4.setColumns(10);

    JLabel lblEmail = new JLabel("Email");
    lblEmail.setBounds(256, 143, 41, 17);
    panel.add(lblEmail);

    this.textField_5 = new JTextField();
    this.textField_5.setBounds(293, 143, 116, 23);
    panel.add(this.textField_5);
    this.textField_5.setColumns(10);

    JLabel label_4 = new JLabel("传真");
    label_4.setHorizontalTextPosition(4);
    label_4.setBounds(65, 180, 34, 17);
    panel.add(label_4);

    this.textField_6 = new JTextField();
    this.textField_6.setBounds(100, 177, 116, 23);
    panel.add(this.textField_6);
    this.textField_6.setColumns(10);

    JLabel label_5 = new JLabel("邮政编码");
    label_5.setBounds(239, 180, 58, 17);
    panel.add(label_5);

    this.textField_7 = new JTextField();
    this.textField_7.setBounds(293, 178, 116, 23);
    panel.add(this.textField_7);
    this.textField_7.setColumns(10);

    JLabel label_6 = new JLabel("备注");
    label_6.setBounds(65, 217, 34, 17);
    panel.add(label_6);

    this.textField_8 = new JTextField();
    this.textField_8.setBounds(100, 214, 309, 23);
    panel.add(this.textField_8);
    this.textField_8.setColumns(10);

    JButton button = new JButton("保存");
    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        SuppliersDialog.this.submmit(suppliers);
      }
    });
    button.setBounds(231, 268, 80, 25);
    panel.add(button);

    JButton button_1 = new JButton("取消");
    button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        SuppliersDialog.this.dispose();
      }
    });
    button_1.setBounds(329, 268, 80, 25);
    panel.add(button_1);
    this.msg = new JLabel("");
    this.msg.setForeground(Color.RED);
    this.msg.setBounds(68, 16, 341, 17);
    panel.add(this.msg);
    if (suppliers != null)
      setValue(suppliers);
  }

  private void submmit(Suppliers oldsuppliers)
  {
    String suppliersName = this.textField.getText();
    String address = this.textField_1.getText();
    String ccontact = this.textField_2.getText();
    String phone = this.textField_3.getText();
    String qq = this.textField_4.getText();
    String email = this.textField_5.getText();
    String fax = this.textField_6.getText();
    String zipcode = this.textField_7.getText();
    String note = this.textField_8.getText();
    if ("".equals(suppliersName.trim())) {
      this.msg.setText("请输入供应商名称");
      return;
    }
    if ("".equals(address.trim())) {
      this.msg.setText("请输入供应商地址");
      return;
    }
    Suppliers suppliers = null;

    if (oldsuppliers == null)
      suppliers = new Suppliers();
    else {
      suppliers = oldsuppliers;
    }
    suppliers.setSuppliersName(suppliersName);
    suppliers.setAddress(address);
    suppliers.setContact(ccontact);
    suppliers.setPhone(phone);
    suppliers.setQq(qq);
    suppliers.setEmail(email);
    suppliers.setFax(fax);
    suppliers.setZipcode(zipcode);
    suppliers.setRemarks(note);
    SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();
    if (oldsuppliers == null)
    {
      suppliers.setSuppliersno(UUID.randomUUID().toString().replaceAll("-", ""));
      suppliersMoudle.insertSuppliers(suppliers);
      int rows = this.jTable.getRowCount();
      insertRow(suppliers, rows);
      clear();
      this.msg.setText("添加供应商资料成功");
    } else {
      suppliersMoudle.updateSuppliers(oldsuppliers);
      int rows = this.jTable.getSelectedRow();
      DefaultTableModel tableModel = (DefaultTableModel)this.jTable.getModel();
      tableModel.removeRow(rows);
      insertRow(suppliers, rows);
      this.msg.setText("修改供应商资料成功");
    }
  }

  private void setValue(Suppliers suppliers) { this.textField.setText(suppliers.getSuppliersName());
    this.textField_1.setText(suppliers.getAddress());
    this.textField_2.setText(suppliers.getContact());
    this.textField_3.setText(suppliers.getPhone());
    this.textField_4.setText(suppliers.getQq());
    this.textField_5.setText(suppliers.getEmail());
    this.textField_6.setText(suppliers.getFax());
    this.textField_7.setText(suppliers.getZipcode());
    this.textField_8.setText(suppliers.getRemarks()); }

  private void insertRow(Suppliers suppliers, int row) {
    DefaultTableModel tableModel = (DefaultTableModel)this.jTable.getModel();
    Object[] rowData = { 
      suppliers.getSuppliersno(), 
      suppliers.getSuppliersName(), 
      suppliers.getAddress(), 
      suppliers.getContact(), 
      suppliers.getPhone(), 
      suppliers.getQq(), 
      suppliers.getFax(), 
      suppliers.getEmail(), 
      suppliers.getZipcode(), 
      suppliers.getRemarks() };

    tableModel.insertRow(row, rowData);
    this.jTable.getSelectionModel().setSelectionInterval(row, row);
  }

  private void clear() {
    this.textField.setText("");
    this.textField_1.setText("");
    this.textField_2.setText("");
    this.textField_3.setText("");
    this.textField_4.setText("");
    this.textField_5.setText("");
    this.textField_6.setText("");
    this.textField_7.setText("");
    this.textField_8.setText("");
  }
}