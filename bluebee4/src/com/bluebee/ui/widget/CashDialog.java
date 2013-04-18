package com.bluebee.ui.widget;

import com.bluebee.ui.LimitedDocument;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class CashDialog extends JDialog
{
  private String dubString = "1234567890.";
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private CallBack callBack;

  public CashDialog(Component owner, String ys, String ss, CallBack callBackx)
  {
    setResizable(false);

    setSize(new Dimension(325, 262));
    setLocationRelativeTo(owner);
    setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    setDefaultCloseOperation(2);

    setTitle("结算");
    getContentPane().setLayout(null);
    this.callBack = callBackx;
    JLabel label = new JLabel("应收");
    label.setFont(new Font("宋体", 0, 25));
    label.setBounds(33, 34, 54, 28);
    getContentPane().add(label);

    JLabel label_1 = new JLabel("实收");
    label_1.setFont(new Font("宋体", 0, 25));
    label_1.setBounds(33, 85, 54, 31);
    getContentPane().add(label_1);

    JLabel label_2 = new JLabel("找零");
    label_2.setFont(new Font("宋体", 0, 25));
    label_2.setBounds(33, 137, 54, 28);
    getContentPane().add(label_2);
    NumberFormat amountFormat = NumberFormat.getNumberInstance();

    this.textField = new JFormattedTextField(amountFormat);
    this.textField.setDocument(new LimitedDocument(20, this.dubString));
    this.textField.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent e) {
        CashDialog.this.change();
      }

      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
          CashDialog.this.submmit();
      }
    });
    this.textField.setText(ys);
    this.textField.setBorder(BorderFactory.createEmptyBorder());
    this.textField.setFont(new Font("宋体", 1, 25));
    this.textField.setBounds(97, 32, 183, 31);
    getContentPane().add(this.textField);
    this.textField.setColumns(10);
    this.textField_1 = new JFormattedTextField(amountFormat);
    this.textField_1.setDocument(new LimitedDocument(20, this.dubString));
    this.textField_1.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent e) {
        CashDialog.this.change();
      }

      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
          CashDialog.this.submmit();
      }
    });
    this.textField_1.setText(ss);
    this.textField_1.setBorder(BorderFactory.createEmptyBorder());
    this.textField_1.setFont(new Font("宋体", 1, 25));
    this.textField_1.setBounds(97, 84, 183, 31);
    getContentPane().add(this.textField_1);
    this.textField_1.setColumns(10);

    this.textField_2 = new JFormattedTextField(amountFormat);
    this.textField_2.setBorder(BorderFactory.createEmptyBorder());
    this.textField_2.setText("0");
    this.textField_2.setForeground(Color.RED);
    this.textField_2.setEditable(false);
    this.textField_2.setFont(new Font("微软雅黑", 0, 25));
    this.textField_2.setBounds(97, 135, 183, 31);
    getContentPane().add(this.textField_2);
    this.textField_2.setColumns(10);

    JToggleButton button = new JToggleButton("确定");
    button.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10)
          CashDialog.this.submmit();
      }
    });
    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        CashDialog.this.submmit();
      }
    });
    button.setBounds(72, 189, 93, 31);
    getContentPane().add(button);

    JToggleButton button_1 = new JToggleButton("取消");
    button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        CashDialog.this.dispose();
      }
    });
    button_1.setBounds(187, 189, 93, 31);
    getContentPane().add(button_1);
    addWindowListener(new WindowAdapter() {
      public void windowOpened(WindowEvent e) {
        CashDialog.this.textField_1.requestFocus();
        CashDialog.this.textField_1.selectAll();
      }
    });
  }

  private void submmit() {
    this.callBack.updateView();
    dispose();
  }

  private void change()
  {
    if ((this.textField.getText().trim().length() > 0) && (this.textField_1.getText().trim().length() > 0)) {
      String ystext = this.textField.getText().trim().replaceAll("\\,", "");
      String shishoutext = this.textField_1.getText().trim().replaceAll("\\,", "");
      BigDecimal ys = new BigDecimal(ystext);
      BigDecimal shishou = new BigDecimal(shishoutext);
      this.textField_2.setText(String.valueOf(shishou.subtract(ys)));
    }
  }

  public static abstract interface CallBack
  {
    public abstract void updateView();
  }
}