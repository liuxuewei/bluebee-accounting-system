package com.bluebee.ui.widget;

import com.bluebee.moudle.CustomMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Custom;
import com.bluebee.ui.LimitedDocument;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CustomDialog extends JDialog
{
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_4;
  private JTextField textField_2;
  private JTextField textField_3;
  private JLabel label_8;
  private JLabel label_9;
  private JLabel label_11;
  private JCheckBox checkBox;
  private JCheckBox checkBox_1;
  private JCheckBox checkBox_2;
  private JComboBox comboBox;
  private JTextArea textArea;
  private JTable jTable;
  private Custom customold;
  private JLabel label_12;
  private String intString = "1234567890";

  public CustomDialog(Component owner, String tilte, Custom custom, JTable table) {
    setResizable(false);
    setTitle(tilte);
    this.customold = custom;
    this.jTable = table;
    setSize(new Dimension(455, 372));
    setLocationRelativeTo(owner);

    setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    setDefaultCloseOperation(2);
    getContentPane().setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "客户资料", 4, 2, null, null));
    getContentPane().add(panel, "Center");
    panel.setLayout(null);

    JLabel label = new JLabel("客户号");
    label.setBounds(38, 24, 36, 15);
    panel.add(label);

    this.textField = new JTextField();
    this.textField.setBounds(79, 21, 156, 21);
    panel.add(this.textField);
    this.textField.setColumns(10);

    JLabel label_1 = new JLabel("姓名");
    label_1.setBounds(50, 50, 24, 15);
    panel.add(label_1);

    this.textField_1 = new JTextField();
    this.textField_1.setBounds(79, 47, 156, 21);
    panel.add(this.textField_1);
    this.textField_1.setColumns(10);

    JLabel label_2 = new JLabel("级别");
    label_2.setBounds(50, 75, 24, 15);
    panel.add(label_2);

    JLabel label_3 = new JLabel("性别");
    label_3.setBounds(50, 103, 24, 15);
    panel.add(label_3);

    this.checkBox = new JCheckBox("男");
    this.checkBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CustomDialog.this.checkBox.setSelected(true);
        CustomDialog.this.checkBox_1.setSelected(false);
      }
    });
    this.checkBox.setBounds(79, 99, 51, 23);
    panel.add(this.checkBox);

    this.checkBox_1 = new JCheckBox("女");
    this.checkBox_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CustomDialog.this.checkBox.setSelected(false);
        CustomDialog.this.checkBox_1.setSelected(true);
      }
    });
    this.checkBox_1.setBounds(132, 99, 51, 23);
    panel.add(this.checkBox_1);

    JLabel label_4 = new JLabel("生日");
    label_4.setBounds(50, 130, 24, 15);
    panel.add(label_4);

    this.textField_4 = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));

    this.textField_4.setBounds(79, 128, 156, 21);
    panel.add(this.textField_4);
    this.textField_4.setColumns(10);

    this.comboBox = new JComboBox();
    this.comboBox.setModel(new DefaultComboBoxModel(SelectType.getCustomType()));
    this.comboBox.setEditable(false);
    this.comboBox.setBounds(79, 73, 156, 21);
    panel.add(this.comboBox);

    JLabel label_5 = new JLabel("电话");
    label_5.setBounds(50, 155, 24, 15);
    panel.add(label_5);

    JLabel label_6 = new JLabel("联系地址");
    label_6.setBounds(26, 180, 48, 15);
    panel.add(label_6);

    this.textField_2 = new JTextField();
    this.textField_2.setBounds(79, 177, 314, 21);
    panel.add(this.textField_2);
    this.textField_2.setColumns(10);

    JLabel label_7 = new JLabel("备注");
    label_7.setBounds(50, 205, 24, 15);
    panel.add(label_7);

    this.textArea = new JTextArea();
    this.textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
    this.textArea.setBounds(79, 205, 314, 63);
    panel.add(this.textArea);

    this.textField_3 = new JTextField();
    this.textField_3.setDocument(new LimitedDocument(20, this.intString));
    this.textField_3.setBounds(79, 152, 156, 21);
    panel.add(this.textField_3);
    this.textField_3.setColumns(10);

    this.checkBox_2 = new JCheckBox("录入完成后继续录入");
    this.checkBox_2.setBounds(74, 286, 156, 23);
    panel.add(this.checkBox_2);

    JButton button = new JButton("保存");
    button.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent arg0) {
        CustomDialog.this.submmit();
      }
    });
    button.setBounds(248, 286, 66, 23);
    panel.add(button);
    JButton button_1 = new JButton("取消");
    button_1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent arg0) {
        CustomDialog.this.dispose();
      }
    });
    button_1.setBounds(324, 286, 69, 23);
    panel.add(button_1);

    this.label_8 = new JLabel("");
    this.label_8.setForeground(Color.RED);
    this.label_8.setBounds(245, 24, 194, 15);
    panel.add(this.label_8);

    this.label_9 = new JLabel("");
    this.label_9.setForeground(Color.RED);
    this.label_9.setBounds(245, 50, 194, 15);
    panel.add(this.label_9);

    this.label_11 = new JLabel("");
    this.label_11.setForeground(Color.RED);
    this.label_11.setBounds(245, 103, 194, 15);
    panel.add(this.label_11);

    this.label_12 = new JLabel("输入格式1988-08-08");
    this.label_12.setBounds(245, 130, 178, 15);
    panel.add(this.label_12);

    JButton button_2 = new JButton("");
    button_2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {
        CustomTypeDialog customTypeDialog = new CustomTypeDialog(arg0.getComponent().getParent(), "客户级别设置", CustomDialog.this.comboBox);
        customTypeDialog.setVisible(true);
      }
    });
    button_2.setIcon(new ImageIcon(CustomDialog.class.getResource("/com/bluebee/resource/image/list-add.png")));
    button_2.setBounds(245, 71, 24, 23);
    panel.add(button_2);
    if (custom != null)
      update();
  }

  private void update()
  {
    this.textField.setEditable(false);
    this.textField.setText(this.customold.getId());
    this.textField_1.setText(this.customold.getName());

    if ("男".equals(this.customold.getSex()))
      this.checkBox.setSelected(true);
    else {
      this.checkBox_1.setSelected(true);
    }
    this.textField_4.setText(this.customold.getBirthday());
    this.textField_3.setText(this.customold.getTelephone());
    this.textField_2.setText(this.customold.getAddress());
    this.textArea.setText(this.customold.getNote());
    this.comboBox.getEditor().setItem(this.customold.getType());
  }

  private void submmit() {
    String id = this.textField.getText();
    if (id.trim().length() == 0) {
      this.label_8.setText("请输入客户号");
      this.textField.requestFocus();
      return;
    }
    this.label_8.setText("");
    String name = this.textField_1.getText();
    if (name.trim().length() == 0) {
      this.label_9.setText("请输入客户姓名");
      this.textField_1.requestFocus();
      return;
    }
    this.label_9.setText("");
    String sex = "男";
    if ((!this.checkBox.isSelected()) && (!this.checkBox_1.isSelected())) {
      this.label_11.setText("请选择性别");
      return;
    }
    if (this.checkBox.isSelected())
      sex = "男";
    else if (this.checkBox_1.isSelected()) {
      sex = "女";
    }

    this.label_11.setText("");

    CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
    if (this.customold == null) {
      Custom customis = customMoudle.getCustomById(id);
      if (customis != null) {
        this.label_8.setText("客户号[" + id + "]已经存在,请重新设定");
        return;
      }
    }
    Custom custom = new Custom();

    if (this.customold != null)
      custom.setId(this.customold.getId());
    else {
      custom.setId(id);
    }

    custom.setName(name);
    custom.setSex(sex);
    if ((this.comboBox.getItemCount() > 0) && (this.comboBox.getSelectedItem() != null)) {
      String type = this.comboBox.getSelectedItem().toString();
      if (type.trim().length() > 0) {
        custom.setType(type);
      }
    }

    String birthday = this.textField_4.getText();
    if (birthday.trim().length() > 0) {
      custom.setBirthday(birthday);
    }
    String telephone = this.textField_3.getText();
    if (telephone.trim().length() > 0) {
      custom.setTelephone(telephone);
    }
    String addres = this.textField_2.getText();
    if (addres.trim().length() > 0) {
      custom.setAddress(addres);
    }
    String note = this.textArea.getText();
    if (note.trim().length() > 0) {
      custom.setNote(note);
    }
    if (this.customold != null) {
      customMoudle.update(custom);
      int row = this.jTable.getSelectedRow();
      DefaultTableModel tableModel = (DefaultTableModel)this.jTable.getModel();
      tableModel.removeRow(row);
      insertRow(custom, row);
    } else {
      customMoudle.add(custom);
      int rows = this.jTable.getRowCount();
      insertRow(custom, rows);
    }

    if (this.checkBox_2.isSelected())
      clear();
    else
      dispose();
  }

  private void clear()
  {
    this.textField.setText("");
    this.label_8.setText("");
    this.textField_1.setText("");
    this.label_9.setText("");
    this.label_11.setText("");
    this.checkBox.setSelected(false);
    this.checkBox_1.setSelected(false);
    this.textField_4.setText("");
    this.textField_3.setText("");
    this.textField_2.setText("");
    this.textArea.setText("");
    this.comboBox.getEditor().setItem("");
  }

  private void insertRow(Custom custom, int row)
  {
    DefaultTableModel tableModel = (DefaultTableModel)this.jTable.getModel();
    Object[] rowData = { 
      custom.getId(), 
      custom.getName(), 
      custom.getType(), 
      Double.valueOf(custom.getIntegration()), 
      custom.getAmount(), 
      Integer.valueOf(custom.getFrequency()), 
      custom.getSex(), 
      custom.getBirthday(), 
      custom.getTelephone(), 
      custom.getAddress(), 
      custom.getNote() };

    tableModel.insertRow(row, rowData);
    this.jTable.getSelectionModel().setSelectionInterval(row, row);
  }
}