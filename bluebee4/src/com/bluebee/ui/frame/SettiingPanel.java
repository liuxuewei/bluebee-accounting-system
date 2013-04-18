package com.bluebee.ui.frame;

import com.bluebee.SysEnv;
import com.bluebee.moudle.ConfigMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Config;
import com.bluebee.ui.MainWindows;
import com.bluebee.ui.skin.ColorDefinitions;
import com.bluebee.ui.skin.LookAndFeelSelector;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class SettiingPanel extends JPanel
  implements IPage
{
  private static SettiingPanel settiingPanel = new SettiingPanel();

  private boolean isDefaultPage = false;
  private JTextField textField;
  private JCheckBox checkBox1;
  private JCheckBox checkBox2;
  private JCheckBox checkBox4;
  private JCheckBox checkBox5;
  private JCheckBox checkBox6;
  private JLabel label;

  private SettiingPanel()
  {
    initComponents();
    initData();
  }

  public static SettiingPanel getInstance() {
    return settiingPanel;
  }

  private void initComponents()
  {
    setLayout(null);

    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBorder(new TitledBorder(null, "销售策略", 4, 2, null, null));
    panel.setBounds(22, 10, 544, 100);
    add(panel);

    JLabel label = new JLabel("低于定价");
    label.setBounds(27, 45, 58, 17);
    panel.add(label);

    this.textField = new JTextField();
    this.textField.setBounds(97, 42, 37, 23);
    panel.add(this.textField);
    this.textField.setColumns(10);

    JLabel label1 = new JLabel("%也可以出售");
    label1.setBounds(146, 45, 93, 17);
    panel.add(label1);

    JLabel label2 = new JLabel("提示：默认按照2%");
    label2.setForeground(Color.RED);
    label2.setBounds(284, 45, 125, 17);
    panel.add(label2);

    String skin = SysEnv.getInstance().getSkin();

    JPanel panel1 = new JPanel();
    panel1.setLayout(null);
    panel1.setBorder(new TitledBorder(null, "界面风格", 4, 2, null, null));
    panel1.setBounds(22, 120, 544, 176);
    add(panel1);

    this.checkBox1 = new JCheckBox("墨黑");
    this.checkBox1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SettiingPanel.this.checkBox2.setSelected(false);

        SettiingPanel.this.checkBox4.setSelected(false);
        SettiingPanel.this.checkBox5.setSelected(false);
        SettiingPanel.this.checkBox6.setSelected(false);
        SettiingPanel.this.checkBox1.setSelected(true);
      }
    });
    if (skin.equals("aTunes Dark")) {
      this.checkBox1.setSelected(true);
    }

    this.checkBox1.setBounds(23, 31, 67, 25);
    panel1.add(this.checkBox1);

    this.checkBox2 = new JCheckBox("墨绿");
    this.checkBox2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SettiingPanel.this.checkBox1.setSelected(false);

        SettiingPanel.this.checkBox4.setSelected(false);
        SettiingPanel.this.checkBox5.setSelected(false);
        SettiingPanel.this.checkBox6.setSelected(false);
        SettiingPanel.this.checkBox2.setSelected(true);
      }
    });
    if (skin.equals("Sahara")) {
      this.checkBox2.setSelected(true);
    }
    this.checkBox2.setBounds(23, 69, 67, 25);
    panel1.add(this.checkBox2);

    JLabel label_1 = new JLabel("");
    label_1.setIcon(new ImageIcon(SettiingPanel.class.getResource("/com/bluebee/resource/image/skin1.png")));
    label_1.setBounds(98, 31, 114, 24);
    panel1.add(label_1);

    JLabel label_2 = new JLabel("");
    label_2.setIcon(new ImageIcon(SettiingPanel.class.getResource("/com/bluebee/resource/image/skin2.png")));
    label_2.setBounds(98, 67, 105, 28);
    panel1.add(label_2);

    this.checkBox4 = new JCheckBox("墨蓝");
    this.checkBox4.setBounds(23, 147, 58, 23);
    this.checkBox4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SettiingPanel.this.checkBox1.setSelected(false);
        SettiingPanel.this.checkBox2.setSelected(false);

        SettiingPanel.this.checkBox5.setSelected(false);
        SettiingPanel.this.checkBox6.setSelected(false);
        SettiingPanel.this.checkBox4.setSelected(true);
      }
    });
    if (skin.equalsIgnoreCase("OfficeBlue2007")) {
      this.checkBox4.setSelected(true);
    }
    panel1.add(this.checkBox4);

    JLabel label_4 = new JLabel("");
    label_4.setIcon(new ImageIcon(SettiingPanel.class.getResource("/com/bluebee/resource/image/skin4.png")));
    label_4.setBounds(98, 144, 114, 26);
    panel1.add(label_4);

    this.checkBox5 = new JCheckBox("墨白");
    this.checkBox5.setBounds(267, 32, 58, 23);
    this.checkBox5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SettiingPanel.this.checkBox1.setSelected(false);
        SettiingPanel.this.checkBox2.setSelected(false);

        SettiingPanel.this.checkBox4.setSelected(false);
        SettiingPanel.this.checkBox6.setSelected(false);
        SettiingPanel.this.checkBox5.setSelected(true);
      }
    });
    if (skin.equalsIgnoreCase("Creme")) {
      this.checkBox5.setSelected(true);
    }
    panel1.add(this.checkBox5);

    JLabel label_5 = new JLabel("");
    label_5.setIcon(new ImageIcon(SettiingPanel.class.getResource("/com/bluebee/resource/image/skin5.png")));
    label_5.setBounds(331, 31, 105, 25);
    panel1.add(label_5);

    this.checkBox6 = new JCheckBox("橙黄");
    this.checkBox6.setBounds(23, 107, 58, 23);
    this.checkBox6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SettiingPanel.this.checkBox1.setSelected(false);
        SettiingPanel.this.checkBox2.setSelected(false);

        SettiingPanel.this.checkBox4.setSelected(false);
        SettiingPanel.this.checkBox5.setSelected(false);
        SettiingPanel.this.checkBox6.setSelected(true);
      }
    });
    if (skin.equalsIgnoreCase("NebulaBrickWall")) {
      this.checkBox6.setSelected(true);
    }
    panel1.add(this.checkBox6);

    JLabel label_6 = new JLabel("New label");
    label_6.setIcon(new ImageIcon(SettiingPanel.class.getResource("/com/bluebee/resource/image/skin6.png")));
    label_6.setBounds(98, 106, 105, 25);
    panel1.add(label_6);

    JPanel panel2 = new JPanel();
    panel2.setLayout(null);
    panel2.setBounds(22, 310, 544, 67);
    add(panel2);

    JButton button = new JButton("保 存");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SettiingPanel.this.submmit();
      }
    });
    button.setBounds(443, 28, 101, 25);
    panel2.add(button);

    this.label = new JLabel("");
    this.label.setBounds(33, 348, 325, 17);
    add(this.label);
    this.label.setForeground(Color.RED);
  }

  private void initData()
  {
    Config configskin = MoudleContentFactry.getConfigMoudle().getConfig("skin");
    Config config = MoudleContentFactry.getConfigMoudle().getConfig("policy");
    Config configdz = MoudleContentFactry.getConfigMoudle().getConfig("mydzj");
    Config configdf = MoudleContentFactry.getConfigMoudle().getConfig("mydf");
    if (config != null)
      this.textField.setText(config.getValue());
    else {
      this.textField.setText("2");
    }

    if (configskin != null) {
      if (configskin.getValue().equalsIgnoreCase("aTunes Dark"))
        this.checkBox1.setSelected(true);
      else if (configskin.getValue().equalsIgnoreCase("Sahara"))
        this.checkBox2.setSelected(true);
      else if (configskin.getValue().equalsIgnoreCase("OfficeBlue2007"))
        this.checkBox4.setSelected(true);
      else if (configskin.getValue().equalsIgnoreCase("Creme"))
        this.checkBox5.setSelected(true);
      else if (configskin.getValue().equalsIgnoreCase("NebulaBrickWall")) {
        this.checkBox6.setSelected(true);
      }
    }
    else
      this.checkBox1.setSelected(true);
  }

  private void submmit()
  {
    String t1 = this.textField.getText();

    Config[] configs = new Config[2];
    Config config1 = new Config();
    config1.setKey("policy");
    if ((t1 == null) || (t1.trim().length() == 0)) {
      config1.setValue("8");
    } else {
      double fg = Double.parseDouble(t1);
      if (fg > 1.0D)
        config1.setValue(String.valueOf(t1));
      else {
        config1.setValue(String.valueOf(fg));
      }
    }
    SysEnv.getInstance().setPolicy(config1.getValue());
    configs[0] = config1;
    Config config2 = new Config();
    config2.setKey("skin");
    if (this.checkBox1.isSelected())
      config2.setValue("aTunes Dark");
    else if (this.checkBox2.isSelected())
      config2.setValue("Sahara");
    else if (this.checkBox4.isSelected())
      config2.setValue("OfficeBlue2007");
    else if (this.checkBox5.isSelected())
      config2.setValue("Creme");
    else if (this.checkBox6.isSelected()) {
      config2.setValue("NebulaBrickWall");
    }

    SysEnv.getInstance().setSkin(config2.getValue());
    LookAndFeelSelector.setLookAndFeel(config2.getValue());

    ColorDefinitions.initColors();
    SwingUtilities.updateComponentTreeUI(MainWindows.getInstance());
    configs[1] = config2;

    MoudleContentFactry.getConfigMoudle().addConfigs(configs);
  }

  public void disposePage()
  {
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>系统设置";
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