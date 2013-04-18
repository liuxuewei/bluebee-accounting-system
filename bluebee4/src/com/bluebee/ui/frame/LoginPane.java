package com.bluebee.ui.frame;

import com.bluebee.SysEnv;
import com.bluebee.pojo.User;
import com.bluebee.ui.LoginFrame;
import com.bluebee.ui.MainWindows;
import com.bluebee.ui.widget.SelectType;
import com.bluebee.ui.widget.TCPopupEventQueue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class LoginPane extends JPanel
{
  private JPasswordField passwordField;
  private JLabel lblSss;
  private JLabel label_2;
  private JComboBox comboBox;

  public LoginPane()
  {
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 32, 0, 153 };
    gridBagLayout.rowHeights = new int[] { 28, 27, 0, 0, 17 };
    gridBagLayout.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gridBagLayout.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    setLayout(gridBagLayout);

    this.lblSss = new JLabel("");
    this.lblSss.setForeground(Color.RED);
    GridBagConstraints gbc_lblSss = new GridBagConstraints();
    gbc_lblSss.anchor = 17;
    gbc_lblSss.insets = new Insets(0, 0, 5, 5);
    gbc_lblSss.gridx = 2;
    gbc_lblSss.gridy = 0;
    add(this.lblSss, gbc_lblSss);

    this.label_2 = new JLabel("用户");
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.fill = 3;
    gbc_label_2.anchor = 13;
    gbc_label_2.insets = new Insets(0, 0, 5, 5);
    gbc_label_2.gridx = 1;
    gbc_label_2.gridy = 1;
    add(this.label_2, gbc_label_2);
    this.comboBox = new JComboBox();
    this.comboBox.setModel(new DefaultComboBoxModel(SelectType.getUserList()));
    GridBagConstraints gbc_comboBox = new GridBagConstraints();
    gbc_comboBox.insets = new Insets(0, 0, 5, 5);
    gbc_comboBox.fill = 1;
    gbc_comboBox.gridx = 2;
    gbc_comboBox.gridy = 1;
    add(this.comboBox, gbc_comboBox);

    JLabel label = new JLabel("密码");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.anchor = 13;
    gbc_label.insets = new Insets(0, 0, 5, 5);
    gbc_label.gridx = 1;
    gbc_label.gridy = 2;
    add(label, gbc_label);

    this.passwordField = new JPasswordField();
    this.passwordField.setFont(new Font("宋体", 0, 12));
    this.passwordField.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10)
          LoginPane.this.submmit();
      }
    });
    GridBagConstraints gbc_passwordField = new GridBagConstraints();
    gbc_passwordField.insets = new Insets(0, 0, 5, 5);
    gbc_passwordField.fill = 2;
    gbc_passwordField.gridx = 2;
    gbc_passwordField.gridy = 2;
    add(this.passwordField, gbc_passwordField);
    JLabel label_1 = new JLabel("输入密码完成按回车键登录");
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.anchor = 17;
    gbc_label_1.insets = new Insets(0, 0, 5, 5);
    gbc_label_1.gridx = 2;
    gbc_label_1.gridy = 3;
    add(label_1, gbc_label_1);

    addAncestorListener(new AncestorListener() {
      public void ancestorAdded(AncestorEvent evt) {
        LoginPane.this.passwordField.requestFocus();
      }

      public void ancestorRemoved(AncestorEvent evt) {
      }

      public void ancestorMoved(AncestorEvent evt) {
      }
    });
  }

  private void submmit() {
    String pass = String.valueOf(this.passwordField.getPassword());
    final User option = (User)this.comboBox.getSelectedItem();
    String passh = option.getPassword();
    
    String username = option.getUsernmae();
    if (passh.equals(pass)) {
      this.passwordField.setText("");
      LoginFrame.getInstance().setVisible(false);
      EventQueue.invokeLater(new Runnable() {
        public void run() {
          SysEnv.getInstance().setLoginUser(option.getId());
          MainWindows frame = MainWindows.getInstance();
          frame.setDefaultCloseOperation(1);
          frame.setVisible(true);

          Toolkit.getDefaultToolkit().getSystemEventQueue().push(new TCPopupEventQueue());
        }
      });
    }
    else {
      this.passwordField.setText("");
      this.lblSss.setText("输入密码错误,请重新输入");
    }
  }
}