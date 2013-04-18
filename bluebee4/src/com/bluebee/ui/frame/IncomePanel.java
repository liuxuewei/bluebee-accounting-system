package com.bluebee.ui.frame;

import com.bluebee.ui.widget.Page;
import com.bluebee.util.DateHelper;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;

public class IncomePanel extends JPanel
  implements IPage
{
  private static IncomePanel incomePanel = new IncomePanel();
  private boolean isDefaultPage;
  private JTable table;
  private JTextField textField;

  public static IncomePanel getInstance()
  {
    return incomePanel;
  }

  private IncomePanel() {
    setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "录入其它收入", 4, 2, null, null));
    add(panel, "North");
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[] { 51, 0, 107, 0, 141 };
    gbl_panel.rowHeights = new int[4];
    gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel.setLayout(gbl_panel);

    JLabel label = new JLabel("时间");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 5, 5);
    gbc_label.gridx = 1;
    gbc_label.gridy = 1;
    panel.add(label, gbc_label);

    JXDatePicker datePicker = new JXDatePicker();
    datePicker.getEditor().setEditable(false);
    datePicker.getEditor().setFont(new Font("宋体", 1, 12));
    datePicker.getEditor().setColumns(10);
    datePicker.setFormats(new String[] { "yyyy-MM-dd" });
    datePicker.setDate(DateHelper.currentDate());
    GridBagConstraints gbc_datePicker = new GridBagConstraints();
    gbc_datePicker.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker.gridx = 2;
    gbc_datePicker.gridy = 1;
    panel.add(datePicker, gbc_datePicker);

    JLabel label_1 = new JLabel("类型");
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.anchor = 13;
    gbc_label_1.insets = new Insets(0, 0, 5, 5);
    gbc_label_1.gridx = 3;
    gbc_label_1.gridy = 1;
    panel.add(label_1, gbc_label_1);

    JComboBox comboBox = new JComboBox();
    comboBox.setEditable(true);
    GridBagConstraints gbc_comboBox = new GridBagConstraints();
    gbc_comboBox.insets = new Insets(0, 0, 5, 5);
    gbc_comboBox.fill = 2;
    gbc_comboBox.gridx = 4;
    gbc_comboBox.gridy = 1;
    panel.add(comboBox, gbc_comboBox);

    JLabel label_2 = new JLabel("金额");
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.anchor = 13;
    gbc_label_2.insets = new Insets(0, 0, 5, 5);
    gbc_label_2.gridx = 5;
    gbc_label_2.gridy = 1;
    panel.add(label_2, gbc_label_2);

    this.textField = new JTextField();
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.insets = new Insets(0, 0, 5, 0);
    gbc_textField.fill = 2;
    gbc_textField.gridx = 6;
    gbc_textField.gridy = 1;
    panel.add(this.textField, gbc_textField);
    this.textField.setColumns(10);

    JButton button = new JButton(" 保存 ");
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.anchor = 13;
    gbc_button.gridx = 6;
    gbc_button.gridy = 2;
    panel.add(button, gbc_button);

    Page page = new Page(null);
    add(page, "South");

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBorder(new TitledBorder(null, "收入列表", 4, 2, null, null));
    add(scrollPane, "Center");

    this.table = new JTable();
    this.table.setModel(new DefaultTableModel(
      new Object[][] { 
      new Object[5] }, 
      new String[] { 
      "id", "收入日期", "类型", "金额", "操作" })
    {
      boolean[] columnEditables = new boolean[5];

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table.getColumnModel().getColumn(0).setResizable(false);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(0).setMinWidth(0);
    this.table.getColumnModel().getColumn(0).setMaxWidth(0);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(154);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(137);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(125);
    this.table.getColumnModel().getColumn(4).setResizable(false);
    this.table.getColumnModel().getColumn(4).setPreferredWidth(117);
    this.table.getTableHeader().setReorderingAllowed(false);
    scrollPane.setViewportView(this.table);
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>其它收入";
  }

  public void disposePage()
  {
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