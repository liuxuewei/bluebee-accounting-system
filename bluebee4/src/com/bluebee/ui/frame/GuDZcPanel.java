package com.bluebee.ui.frame;

import com.bluebee.moudle.ConfigMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Config;
import com.bluebee.ui.LimitedDocument;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class GuDZcPanel extends JPanel
  implements IPage
{
  private static GuDZcPanel guDZcPanel = new GuDZcPanel();

  private boolean isDefaultPage = false;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField textField_4;
  private JTextField textField_5;
  private String dubString = "1234567890.";
  private JTextField textField_6;
  private JLabel label_14;

  private GuDZcPanel()
  {
    setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "固定支出项", 4, 2, null, null));
    add(panel, "North");
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[] { 0, 78, 0, 126, 45 };
    gbl_panel.rowHeights = new int[10];
    gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel.setLayout(gbl_panel);

    this.label_14 = new JLabel("");
    GridBagConstraints gbc_label_14 = new GridBagConstraints();
    gbc_label_14.anchor = 17;
    gbc_label_14.gridwidth = 2;
    gbc_label_14.insets = new Insets(0, 0, 5, 5);
    gbc_label_14.gridx = 2;
    gbc_label_14.gridy = 0;
    panel.add(this.label_14, gbc_label_14);

    JLabel label = new JLabel("每月");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 5, 5);
    gbc_label.anchor = 13;
    gbc_label.gridx = 2;
    gbc_label.gridy = 1;
    panel.add(label, gbc_label);

    this.textField = new JTextField();
    this.textField.setDocument(new LimitedDocument(30, this.dubString));
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.insets = new Insets(0, 0, 5, 5);
    gbc_textField.fill = 2;
    gbc_textField.gridx = 3;
    gbc_textField.gridy = 1;
    panel.add(this.textField, gbc_textField);
    this.textField.setColumns(10);

    JLabel label_3 = new JLabel("店租");
    GridBagConstraints gbc_label_3 = new GridBagConstraints();
    gbc_label_3.insets = new Insets(0, 0, 5, 0);
    gbc_label_3.gridx = 4;
    gbc_label_3.gridy = 1;
    panel.add(label_3, gbc_label_3);

    JLabel label_1 = new JLabel("每月");
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.anchor = 13;
    gbc_label_1.insets = new Insets(0, 0, 5, 5);
    gbc_label_1.gridx = 2;
    gbc_label_1.gridy = 2;
    panel.add(label_1, gbc_label_1);

    this.textField_1 = new JTextField();
    this.textField_1.setDocument(new LimitedDocument(30, this.dubString));
    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
    gbc_textField_1.insets = new Insets(0, 0, 5, 5);
    gbc_textField_1.fill = 2;
    gbc_textField_1.gridx = 3;
    gbc_textField_1.gridy = 2;
    panel.add(this.textField_1, gbc_textField_1);
    this.textField_1.setColumns(10);

    JLabel label_4 = new JLabel("税金");
    GridBagConstraints gbc_label_4 = new GridBagConstraints();
    gbc_label_4.insets = new Insets(0, 0, 5, 0);
    gbc_label_4.gridx = 4;
    gbc_label_4.gridy = 2;
    panel.add(label_4, gbc_label_4);

    JLabel label_2 = new JLabel("每月");
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.anchor = 13;
    gbc_label_2.insets = new Insets(0, 0, 5, 5);
    gbc_label_2.gridx = 2;
    gbc_label_2.gridy = 3;
    panel.add(label_2, gbc_label_2);

    this.textField_2 = new JTextField();
    this.textField_2.setDocument(new LimitedDocument(30, this.dubString));
    GridBagConstraints gbc_textField_2 = new GridBagConstraints();
    gbc_textField_2.insets = new Insets(0, 0, 5, 5);
    gbc_textField_2.fill = 2;
    gbc_textField_2.gridx = 3;
    gbc_textField_2.gridy = 3;
    panel.add(this.textField_2, gbc_textField_2);
    this.textField_2.setColumns(10);

    JLabel label_5 = new JLabel("管理费");
    GridBagConstraints gbc_label_5 = new GridBagConstraints();
    gbc_label_5.insets = new Insets(0, 0, 5, 0);
    gbc_label_5.gridx = 4;
    gbc_label_5.gridy = 3;
    panel.add(label_5, gbc_label_5);

    JLabel label_6 = new JLabel("每月");
    GridBagConstraints gbc_label_6 = new GridBagConstraints();
    gbc_label_6.anchor = 13;
    gbc_label_6.insets = new Insets(0, 0, 5, 5);
    gbc_label_6.gridx = 2;
    gbc_label_6.gridy = 4;
    panel.add(label_6, gbc_label_6);

    this.textField_3 = new JTextField();
    this.textField_3.setDocument(new LimitedDocument(30, this.dubString));
    GridBagConstraints gbc_textField_3 = new GridBagConstraints();
    gbc_textField_3.insets = new Insets(0, 0, 5, 5);
    gbc_textField_3.fill = 2;
    gbc_textField_3.gridx = 3;
    gbc_textField_3.gridy = 4;
    panel.add(this.textField_3, gbc_textField_3);
    this.textField_3.setColumns(10);

    JLabel label_7 = new JLabel("电费");
    GridBagConstraints gbc_label_7 = new GridBagConstraints();
    gbc_label_7.insets = new Insets(0, 0, 5, 0);
    gbc_label_7.gridx = 4;
    gbc_label_7.gridy = 4;
    panel.add(label_7, gbc_label_7);

    JLabel label_8 = new JLabel("每月");
    GridBagConstraints gbc_label_8 = new GridBagConstraints();
    gbc_label_8.anchor = 13;
    gbc_label_8.insets = new Insets(0, 0, 5, 5);
    gbc_label_8.gridx = 2;
    gbc_label_8.gridy = 5;
    panel.add(label_8, gbc_label_8);

    this.textField_4 = new JTextField();
    this.textField_4.setDocument(new LimitedDocument(30, this.dubString));
    GridBagConstraints gbc_textField_4 = new GridBagConstraints();
    gbc_textField_4.insets = new Insets(0, 0, 5, 5);
    gbc_textField_4.fill = 2;
    gbc_textField_4.gridx = 3;
    gbc_textField_4.gridy = 5;
    panel.add(this.textField_4, gbc_textField_4);
    this.textField_4.setColumns(10);

    JLabel label_9 = new JLabel("水费");
    GridBagConstraints gbc_label_9 = new GridBagConstraints();
    gbc_label_9.insets = new Insets(0, 0, 5, 0);
    gbc_label_9.gridx = 4;
    gbc_label_9.gridy = 5;
    panel.add(label_9, gbc_label_9);

    JLabel label_12 = new JLabel("每月");
    GridBagConstraints gbc_label_12 = new GridBagConstraints();
    gbc_label_12.anchor = 13;
    gbc_label_12.insets = new Insets(0, 0, 5, 5);
    gbc_label_12.gridx = 2;
    gbc_label_12.gridy = 6;
    panel.add(label_12, gbc_label_12);

    this.textField_6 = new JTextField();
    GridBagConstraints gbc_textField_6 = new GridBagConstraints();
    gbc_textField_6.insets = new Insets(0, 0, 5, 5);
    gbc_textField_6.fill = 2;
    gbc_textField_6.gridx = 3;
    gbc_textField_6.gridy = 6;
    panel.add(this.textField_6, gbc_textField_6);
    this.textField_6.setColumns(10);

    JLabel label_13 = new JLabel("工资");
    GridBagConstraints gbc_label_13 = new GridBagConstraints();
    gbc_label_13.insets = new Insets(0, 0, 5, 0);
    gbc_label_13.gridx = 4;
    gbc_label_13.gridy = 6;
    panel.add(label_13, gbc_label_13);

    JLabel label_10 = new JLabel("每月");
    GridBagConstraints gbc_label_10 = new GridBagConstraints();
    gbc_label_10.anchor = 13;
    gbc_label_10.insets = new Insets(0, 0, 5, 5);
    gbc_label_10.gridx = 2;
    gbc_label_10.gridy = 7;
    panel.add(label_10, gbc_label_10);

    this.textField_5 = new JTextField();
    this.textField_5.setDocument(new LimitedDocument(30, this.dubString));
    GridBagConstraints gbc_textField_5 = new GridBagConstraints();
    gbc_textField_5.insets = new Insets(0, 0, 5, 5);
    gbc_textField_5.fill = 2;
    gbc_textField_5.gridx = 3;
    gbc_textField_5.gridy = 7;
    panel.add(this.textField_5, gbc_textField_5);
    this.textField_5.setColumns(10);

    JLabel label_11 = new JLabel("其它支出");
    GridBagConstraints gbc_label_11 = new GridBagConstraints();
    gbc_label_11.insets = new Insets(0, 0, 5, 0);
    gbc_label_11.gridx = 4;
    gbc_label_11.gridy = 7;
    panel.add(label_11, gbc_label_11);

    JButton button = new JButton("保  存");

    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        GuDZcPanel.this.submmit();
      }
    });
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.insets = new Insets(0, 0, 0, 5);
    gbc_button.gridx = 3;
    gbc_button.gridy = 8;
    panel.add(button, gbc_button);
    init();
  }

  public static GuDZcPanel getInstance()
  {
    return guDZcPanel;
  }

  private void submmit() {
    BigDecimal bigDecimal = new BigDecimal(0);
    Config[] configs = new Config[8];

    String mydzj = this.textField.getText();
    if (mydzj.trim().length() > 0) {
      Config configsmydzj = new Config();
      configsmydzj.setKey("mydzj");
      configsmydzj.setValue(mydzj);
      bigDecimal = bigDecimal.add(new BigDecimal(mydzj));
      configs[0] = configsmydzj;
    }

    String mysj = this.textField_1.getText();
    if (mysj.trim().length() > 0) {
      Config configsmysj = new Config();
      configsmysj.setKey("gdzc_mysj");
      configsmysj.setValue(mysj);
      bigDecimal = bigDecimal.add(new BigDecimal(mysj));
      configs[1] = configsmysj;
    }

    String myglf = this.textField_2.getText();
    if (myglf.trim().length() > 0) {
      Config configmyglf = new Config();
      configmyglf.setKey("gdzc_myglf");
      configmyglf.setValue(myglf);
      bigDecimal = bigDecimal.add(new BigDecimal(myglf));
      configs[2] = configmyglf;
    }

    String mydf = this.textField_3.getText();
    if (mydf.trim().length() > 0) {
      Config configsmydf = new Config();
      configsmydf.setKey("mydf");
      configsmydf.setValue(mydf);
      bigDecimal = bigDecimal.add(new BigDecimal(mydf));
      configs[3] = configsmydf;
    }

    String mysff = this.textField_4.getText();
    if (mysff.trim().length() > 0) {
      Config configsmysff = new Config();
      configsmysff.setKey("gdzc_mysf");
      configsmysff.setValue(mysff);
      bigDecimal = bigDecimal.add(new BigDecimal(mysff));
      configs[4] = configsmysff;
    }

    String mygz = this.textField_6.getText();
    if (mygz.trim().length() > 0) {
      Config configsmysff = new Config();
      configsmysff.setKey("gdzc_mygz");
      configsmysff.setValue(mygz);
      bigDecimal = bigDecimal.add(new BigDecimal(mygz));
      configs[5] = configsmysff;
    }

    String myqt = this.textField_5.getText();
    if (myqt.trim().length() > 0) {
      Config configsmyqt = new Config();
      configsmyqt.setKey("gdzc_myqt");
      configsmyqt.setValue(myqt);
      bigDecimal = bigDecimal.add(new BigDecimal(myqt));
      configs[6] = configsmyqt;
    }
    Config configsmyqt = new Config();
    configsmyqt.setKey("gdzc_ZH");
    configsmyqt.setValue(String.valueOf(new BigDecimal(myqt)));

    configs[7] = configsmyqt;
    MoudleContentFactry.getConfigMoudle().addConfigs(configs);
    this.label_14.setText("保存成功！");
  }

  private void init() {
    Config configmydzj = MoudleContentFactry.getConfigMoudle().getConfig("mydzj");
    if (configmydzj != null) {
      this.textField.setText(configmydzj.getValue());
    }

    Config configMYSJ = MoudleContentFactry.getConfigMoudle().getConfig("gdzc_mysj");
    if (configMYSJ != null) {
      this.textField_1.setText(configMYSJ.getValue());
    }

    Config configMYGLF = MoudleContentFactry.getConfigMoudle().getConfig("gdzc_myglf");
    if (configMYGLF != null) {
      this.textField_2.setText(configMYGLF.getValue());
    }

    Config configMYDF = MoudleContentFactry.getConfigMoudle().getConfig("mydf");
    if (configMYDF != null) {
      this.textField_3.setText(configMYDF.getValue());
    }

    Config configMYSFF = MoudleContentFactry.getConfigMoudle().getConfig("gdzc_mysf");
    if (configMYSFF != null) {
      this.textField_4.setText(configMYSFF.getValue());
    }
    Config configMYGZ = MoudleContentFactry.getConfigMoudle().getConfig("gdzc_mygz");
    if (configMYGZ != null) {
      this.textField_6.setText(configMYGZ.getValue());
    }

    Config configMYQT = MoudleContentFactry.getConfigMoudle().getConfig("gdzc_myqt");
    if (configMYQT != null)
      this.textField_5.setText(configMYQT.getValue());
  }

  public void disposePage()
  {
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>固定支出";
  }

  public boolean isDefaultPage()
  {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool)
  {
    this.isDefaultPage = bool;
  }
}