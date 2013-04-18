package com.bluebee.ui.frame;

import com.bluebee.action.PageAction;
import com.bluebee.moudle.DailyExpensesMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Config;
import com.bluebee.pojo.DailyExpenses;
import com.bluebee.ui.widget.Page;
import com.bluebee.ui.widget.SelectType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXDatePicker;

public class ZcTjPanel extends JPanel
  implements IPage
{
  private boolean isDefaultPage = false;

  private static ZcTjPanel zcTjPanel = new ZcTjPanel();
  private JXDatePicker datePicker_1;
  private JXDatePicker datePicker;
  private JXDatePicker datePicker_2;
  private JXDatePicker datePicker_3;
  private JTable table;
  private JComboBox comboBox;
  private JComboBox comboBox_1;
  private JLabel label_4;
  private JLabel label_6;
  private JLabel label_8;
  private JLabel label_10;
  private JLabel label_12;
  private JLabel label_14;
  private JLabel label_16;
  private JLabel label_18;
  private String stime;
  private String etime;
  private String stype;
  private String shourustime;
  private String shouruetime;
  private String shourustype;
  private JTable table_1;

  private ZcTjPanel()
  {
    setLayout(new BorderLayout(0, 0));

    JTabbedPane tabbedPane = new JTabbedPane(1);
    add(tabbedPane, "Center");
    ImageIcon imageIcon1 = new ImageIcon(OptionsTypePanel.class.getResource("/com/bluebee/resource/image/tab.png"));
    JPanel panel = new JPanel();
    tabbedPane.addTab("日常支出统计", imageIcon1, panel, null);
    panel.setLayout(new BorderLayout(0, 0));

    JPanel panel_1 = new JPanel();
    panel_1.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    panel.add(panel_1, "North");
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[] { 51, 0, 89, 13, 0, 89, 35, 0, 61 };
    gbl_panel.rowHeights = new int[] { 24, 0, 0, 18 };
    gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel_1.setLayout(gbl_panel);

    final JLabel label_19 = new JLabel("");
    label_19.setForeground(Color.RED);
    label_19.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_label_19 = new GridBagConstraints();
    gbc_label_19.anchor = 17;
    gbc_label_19.gridwidth = 7;
    gbc_label_19.insets = new Insets(0, 0, 5, 5);
    gbc_label_19.gridx = 1;
    gbc_label_19.gridy = 0;
    panel_1.add(label_19, gbc_label_19);

    JLabel label = new JLabel("开始时间");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 5, 5);
    gbc_label.anchor = 13;
    gbc_label.gridx = 1;
    gbc_label.gridy = 1;
    panel_1.add(label, gbc_label);

    this.datePicker = new JXDatePicker();
    this.datePicker.getEditor().setEditable(false);
    this.datePicker.getEditor().setColumns(10);
    this.datePicker.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker.getEditor().setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_datePicker = new GridBagConstraints();
    gbc_datePicker.anchor = 17;
    gbc_datePicker.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker.gridx = 2;
    gbc_datePicker.gridy = 1;
    panel_1.add(this.datePicker, gbc_datePicker);

    JLabel label_1 = new JLabel("结束时间");
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.insets = new Insets(0, 0, 5, 5);
    gbc_label_1.anchor = 13;
    gbc_label_1.gridx = 4;
    gbc_label_1.gridy = 1;
    panel_1.add(label_1, gbc_label_1);

    this.datePicker_1 = new JXDatePicker();
    this.datePicker_1.getEditor().setEditable(false);
    this.datePicker_1.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker_1.getEditor().setFont(new Font("宋体", 1, 12));
    this.datePicker_1.getEditor().setColumns(10);
    GridBagConstraints gbc_datePicker_1 = new GridBagConstraints();
    gbc_datePicker_1.anchor = 17;
    gbc_datePicker_1.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker_1.gridx = 5;
    gbc_datePicker_1.gridy = 1;
    panel_1.add(this.datePicker_1, gbc_datePicker_1);

    JLabel label_2 = new JLabel("支出类型");
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.anchor = 13;
    gbc_label_2.insets = new Insets(0, 0, 5, 5);
    gbc_label_2.gridx = 1;
    gbc_label_2.gridy = 2;
    panel_1.add(label_2, gbc_label_2);

    this.comboBox = new JComboBox();
    GridBagConstraints gbc_comboBox = new GridBagConstraints();
    gbc_comboBox.insets = new Insets(0, 0, 5, 5);
    gbc_comboBox.fill = 2;
    gbc_comboBox.gridx = 2;
    gbc_comboBox.gridy = 2;
    panel_1.add(this.comboBox, gbc_comboBox);

    final Page page = new Page(new PageAction() {
      public void pageFirst() {
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        List list = null;
        if ((ZcTjPanel.this.stime != null) || (ZcTjPanel.this.etime != null) || (ZcTjPanel.this.stype != null))
          list = expensesMoudle.getList("expenses", ZcTjPanel.this.stype, ZcTjPanel.this.stime, ZcTjPanel.this.etime, 0, 20);
        else {
          list = expensesMoudle.getList("expenses", null, null, null, 0, 20);
        }
        ZcTjPanel.this.pageData(list, ZcTjPanel.this.table);
      }

      public void pagePrev(int pagenum) {
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        List list = null;
        if ((ZcTjPanel.this.stime != null) || (ZcTjPanel.this.etime != null) || (ZcTjPanel.this.stype != null))
          list = expensesMoudle.getList("expenses", ZcTjPanel.this.stype, ZcTjPanel.this.stime, ZcTjPanel.this.etime, pagenum, 20);
        else {
          list = expensesMoudle.getList("expenses", null, null, null, pagenum, 20);
        }
        ZcTjPanel.this.pageData(list, ZcTjPanel.this.table);
      }

      public void pageNext(int pagenum) {
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        List list = null;
        if ((ZcTjPanel.this.stime != null) || (ZcTjPanel.this.etime != null) || (ZcTjPanel.this.stype != null))
          list = expensesMoudle.getList("expenses", ZcTjPanel.this.stype, ZcTjPanel.this.stime, ZcTjPanel.this.etime, pagenum, 20);
        else {
          list = expensesMoudle.getList("expenses", null, null, null, pagenum, 20);
        }
        ZcTjPanel.this.pageData(list, ZcTjPanel.this.table);
      }

      public void pageLast(int pagenum) {
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        List list = null;
        if ((ZcTjPanel.this.stime != null) || (ZcTjPanel.this.etime != null) || (ZcTjPanel.this.stype != null))
          list = expensesMoudle.getList("expenses", ZcTjPanel.this.stype, ZcTjPanel.this.stime, ZcTjPanel.this.etime, pagenum, 20);
        else {
          list = expensesMoudle.getList("expenses", null, null, null, pagenum, 20);
        }
        ZcTjPanel.this.pageData(list, ZcTjPanel.this.table);
      }

      public void itemStateChanged(int pagenum) {
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        List list = null;
        if ((ZcTjPanel.this.stime != null) || (ZcTjPanel.this.etime != null) || (ZcTjPanel.this.stype != null))
          list = expensesMoudle.getList("expenses", ZcTjPanel.this.stype, ZcTjPanel.this.stime, ZcTjPanel.this.etime, pagenum, 20);
        else {
          list = expensesMoudle.getList("expenses", null, null, null, pagenum, 20);
        }
        ZcTjPanel.this.pageData(list, ZcTjPanel.this.table);
      }

      public void export(MouseEvent e) {
        try {
          DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
          List list = null;
          if ((ZcTjPanel.this.stime != null) || (ZcTjPanel.this.etime != null) || (ZcTjPanel.this.stype != null))
            list = expensesMoudle.getList("expenses", ZcTjPanel.this.stype, ZcTjPanel.this.stime, ZcTjPanel.this.etime);
          else {
            list = expensesMoudle.getList("expenses", null, null, null);
          }
          if ((list != null) && (list.size() > 0)) {
            JFileChooser jfc = new JFileChooser("d:/");
            jfc.setFileSelectionMode(2);
            File fileff = new File("日常支出清单.csv");
            jfc.setSelectedFile(fileff);
            int result = jfc.showSaveDialog(ZcTjPanel.this.table);
            if (result == 1) return;
            File savedFile = jfc.getSelectedFile();
            if (savedFile.exists()) {
              int overwriteSelect = JOptionPane.showConfirmDialog(ZcTjPanel.this.table, "<html><font size=3>文件" + savedFile.getName() + "已存在，是否覆盖?</font><html>", "是否覆盖?", 
                0, 
                2);
              if (overwriteSelect != 0) {
                return;
              }
            }
            OutputStream out = new FileOutputStream(savedFile);
            out = new BufferedOutputStream(out, 4096);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(out, "gbk"));
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("支出日期");
            stringBuffer.append(",");
            stringBuffer.append("支出类型");
            stringBuffer.append(",");
            stringBuffer.append("支出金额");
            stringBuffer.append("\r\n");
            output.write(String.valueOf(stringBuffer));
            for (int i = 0; i < list.size(); i++) {
              DailyExpenses dailyExpenses = (DailyExpenses)list.get(i);
              StringBuilder sb = new StringBuilder(128);
              sb.append(dailyExpenses.getDate());
              sb.append(",");
              sb.append(dailyExpenses.getType());
              sb.append(",");
              sb.append(dailyExpenses.getPay());
              sb.append("\r\n");
              output.write(String.valueOf(sb));
            }
            output.close();

            JOptionPane.showMessageDialog(null, "文件导出成功");
          } else {
            JOptionPane.showMessageDialog(null, "没数据导出", null, 2);
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    panel.add(page, "South");

    JButton button = new JButton(" 查询 ");
    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        String type = ZcTjPanel.this.comboBox.getEditor().getItem().toString();
        if ("all".equals(type)) {
          ZcTjPanel.this.stype = null;
          type = null;
        }
        String starttime = ZcTjPanel.this.datePicker.getEditor().getText();
        String endtime = ZcTjPanel.this.datePicker_1.getEditor().getText();
        ZcTjPanel.this.stime = starttime;
        ZcTjPanel.this.etime = endtime;
        ZcTjPanel.this.stype = type;
        ZcTjPanel.this.submmit(ZcTjPanel.this.datePicker, ZcTjPanel.this.datePicker_1, "expenses", ZcTjPanel.this.table, label_19, page, type, ZcTjPanel.this.label_4);
      }
    });
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.anchor = 13;
    gbc_button.insets = new Insets(0, 0, 5, 5);
    gbc_button.gridx = 5;
    gbc_button.gridy = 2;
    panel_1.add(button, gbc_button);

    JLabel label_3 = new JLabel("支出金额总计");
    GridBagConstraints gbc_label_3 = new GridBagConstraints();
    gbc_label_3.insets = new Insets(0, 0, 5, 5);
    gbc_label_3.gridx = 7;
    gbc_label_3.gridy = 2;
    panel_1.add(label_3, gbc_label_3);

    this.label_4 = new JLabel("0");
    this.label_4.setFont(new Font("宋体", 1, 14));
    GridBagConstraints gbc_label_4 = new GridBagConstraints();
    gbc_label_4.insets = new Insets(0, 0, 5, 0);
    gbc_label_4.gridx = 8;
    gbc_label_4.gridy = 2;
    panel_1.add(this.label_4, gbc_label_4);

    JPanel panel_3 = new JPanel();
    panel_3.setBorder(null);
    panel.add(panel_3, "Center");
    panel_3.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    panel_3.add(scrollPane, "Center");

    this.table = new JTable();
    this.table.setAutoCreateRowSorter(true);
    this.table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        ZcTjPanel.this.clickedTable(ZcTjPanel.this.table, "删除日常支出数据", "expenses");
      }
    });
    this.table.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "id", "支出日期", "支出类型", "支出金额", "操作" })
    {
      Class[] columnTypes = { 
        String.class, String.class, String.class, Double.class, String.class };

      boolean[] columnEditables = { 
        true };

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table.getTableHeader().setReorderingAllowed(false);
    this.table.getColumnModel().getColumn(0).setResizable(false);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(0).setMinWidth(0);
    this.table.getColumnModel().getColumn(0).setMaxWidth(0);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(100);
    this.table.getColumnModel().getColumn(1).setMinWidth(100);
    this.table.getColumnModel().getColumn(1).setMaxWidth(100);
    this.table.getColumnModel().getColumn(2).setResizable(false);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(150);
    this.table.getColumnModel().getColumn(3).setResizable(false);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
    this.table.getColumnModel().getColumn(4).setResizable(false);
    this.table.getColumnModel().getColumn(4).setPreferredWidth(60);
    this.table.getColumnModel().getColumn(4).setMaxWidth(60);

    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(0);
    TableColumn tc = this.table.getColumn("操作");
    tc.setPreferredWidth(60);
    tc.setCellRenderer(renderer);
    scrollPane.setViewportView(this.table);

    JPanel panel_2 = new JPanel();
    tabbedPane.addTab("固定支出统计", imageIcon1, panel_2, null);
    GridBagLayout gbl_panel_2 = new GridBagLayout();
    gbl_panel_2.columnWidths = new int[] { 41, 0, 120 };
    gbl_panel_2.rowHeights = new int[] { 34, 27, 26, 26, 25, 23, 26, 36 };
    gbl_panel_2.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_2.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel_2.setLayout(gbl_panel_2);

    JLabel label_5 = new JLabel("每月店租:");
    GridBagConstraints gbc_label_5 = new GridBagConstraints();
    gbc_label_5.anchor = 13;
    gbc_label_5.insets = new Insets(0, 0, 5, 5);
    gbc_label_5.gridx = 1;
    gbc_label_5.gridy = 1;
    panel_2.add(label_5, gbc_label_5);

    this.label_6 = new JLabel("0");
    GridBagConstraints gbc_label_6 = new GridBagConstraints();
    gbc_label_6.insets = new Insets(0, 0, 5, 0);
    gbc_label_6.gridx = 2;
    gbc_label_6.gridy = 1;
    panel_2.add(this.label_6, gbc_label_6);

    JLabel label_7 = new JLabel("每月税金:");
    GridBagConstraints gbc_label_7 = new GridBagConstraints();
    gbc_label_7.anchor = 13;
    gbc_label_7.insets = new Insets(0, 0, 5, 5);
    gbc_label_7.gridx = 1;
    gbc_label_7.gridy = 2;
    panel_2.add(label_7, gbc_label_7);

    this.label_8 = new JLabel("0");
    GridBagConstraints gbc_label_8 = new GridBagConstraints();
    gbc_label_8.insets = new Insets(0, 0, 5, 0);
    gbc_label_8.gridx = 2;
    gbc_label_8.gridy = 2;
    panel_2.add(this.label_8, gbc_label_8);

    JLabel label_9 = new JLabel("每月管理费:");
    GridBagConstraints gbc_label_9 = new GridBagConstraints();
    gbc_label_9.anchor = 13;
    gbc_label_9.insets = new Insets(0, 0, 5, 5);
    gbc_label_9.gridx = 1;
    gbc_label_9.gridy = 3;
    panel_2.add(label_9, gbc_label_9);

    this.label_10 = new JLabel("0");
    GridBagConstraints gbc_label_10 = new GridBagConstraints();
    gbc_label_10.insets = new Insets(0, 0, 5, 0);
    gbc_label_10.gridx = 2;
    gbc_label_10.gridy = 3;
    panel_2.add(this.label_10, gbc_label_10);

    JLabel label_11 = new JLabel("每月电费:");
    GridBagConstraints gbc_label_11 = new GridBagConstraints();
    gbc_label_11.anchor = 13;
    gbc_label_11.insets = new Insets(0, 0, 5, 5);
    gbc_label_11.gridx = 1;
    gbc_label_11.gridy = 4;
    panel_2.add(label_11, gbc_label_11);

    this.label_12 = new JLabel("0");
    GridBagConstraints gbc_label_12 = new GridBagConstraints();
    gbc_label_12.insets = new Insets(0, 0, 5, 0);
    gbc_label_12.gridx = 2;
    gbc_label_12.gridy = 4;
    panel_2.add(this.label_12, gbc_label_12);

    JLabel label_13 = new JLabel("每月水费:");
    GridBagConstraints gbc_label_13 = new GridBagConstraints();
    gbc_label_13.anchor = 13;
    gbc_label_13.insets = new Insets(0, 0, 5, 5);
    gbc_label_13.gridx = 1;
    gbc_label_13.gridy = 5;
    panel_2.add(label_13, gbc_label_13);

    this.label_14 = new JLabel("0");
    GridBagConstraints gbc_label_14 = new GridBagConstraints();
    gbc_label_14.insets = new Insets(0, 0, 5, 0);
    gbc_label_14.gridx = 2;
    gbc_label_14.gridy = 5;
    panel_2.add(this.label_14, gbc_label_14);

    JLabel label_15 = new JLabel("其他费用:");
    GridBagConstraints gbc_label_15 = new GridBagConstraints();
    gbc_label_15.anchor = 13;
    gbc_label_15.insets = new Insets(0, 0, 5, 5);
    gbc_label_15.gridx = 1;
    gbc_label_15.gridy = 6;
    panel_2.add(label_15, gbc_label_15);

    this.label_16 = new JLabel("0");
    GridBagConstraints gbc_label_16 = new GridBagConstraints();
    gbc_label_16.insets = new Insets(0, 0, 5, 0);
    gbc_label_16.gridx = 2;
    gbc_label_16.gridy = 6;
    panel_2.add(this.label_16, gbc_label_16);

    JLabel label_17 = new JLabel("每月支出总计");
    label_17.setFont(new Font("宋体", 0, 20));
    GridBagConstraints gbc_label_17 = new GridBagConstraints();
    gbc_label_17.insets = new Insets(0, 0, 0, 5);
    gbc_label_17.gridx = 1;
    gbc_label_17.gridy = 8;
    panel_2.add(label_17, gbc_label_17);

    this.label_18 = new JLabel("0");
    this.label_18.setFont(new Font("宋体", 1, 20));
    GridBagConstraints gbc_label_18 = new GridBagConstraints();
    gbc_label_18.gridx = 2;
    gbc_label_18.gridy = 8;
    panel_2.add(this.label_18, gbc_label_18);

    JPanel panel_4 = new JPanel();
    tabbedPane.addTab("其他收入统计", imageIcon1, panel_4, null);
    panel_4.setLayout(new BorderLayout(0, 0));

    final Page page_1 = new Page(new PageAction() {
      public void pageFirst() {
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        List list = null;
        if ((ZcTjPanel.this.shourustime != null) || (ZcTjPanel.this.shouruetime != null) || (ZcTjPanel.this.shourustype != null))
          list = expensesMoudle.getList("income", ZcTjPanel.this.shourustype, ZcTjPanel.this.shourustime, ZcTjPanel.this.shouruetime, 0, 20);
        else {
          list = expensesMoudle.getList("income", null, null, null, 0, 20);
        }
        ZcTjPanel.this.pageData(list, ZcTjPanel.this.table_1);
      }

      public void pagePrev(int pagenum) {
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        List list = null;
        if ((ZcTjPanel.this.shourustime != null) || (ZcTjPanel.this.shouruetime != null) || (ZcTjPanel.this.shourustype != null))
          list = expensesMoudle.getList("income", ZcTjPanel.this.shourustype, ZcTjPanel.this.shourustime, ZcTjPanel.this.shouruetime, pagenum, 20);
        else {
          list = expensesMoudle.getList("income", null, null, null, pagenum, 20);
        }
        ZcTjPanel.this.pageData(list, ZcTjPanel.this.table_1);
      }

      public void pageNext(int pagenum) {
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        List list = null;
        if ((ZcTjPanel.this.shourustime != null) || (ZcTjPanel.this.shouruetime != null) || (ZcTjPanel.this.shourustype != null))
          list = expensesMoudle.getList("income", ZcTjPanel.this.shourustype, ZcTjPanel.this.shourustime, ZcTjPanel.this.shouruetime, pagenum, 20);
        else {
          list = expensesMoudle.getList("income", null, null, null, pagenum, 20);
        }
        ZcTjPanel.this.pageData(list, ZcTjPanel.this.table_1);
      }

      public void pageLast(int pagenum) {
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        List list = null;
        if ((ZcTjPanel.this.shourustime != null) || (ZcTjPanel.this.shouruetime != null) || (ZcTjPanel.this.shourustype != null))
          list = expensesMoudle.getList("income", ZcTjPanel.this.shourustype, ZcTjPanel.this.shourustime, ZcTjPanel.this.shouruetime, pagenum, 20);
        else {
          list = expensesMoudle.getList("income", null, null, null, pagenum, 20);
        }
        ZcTjPanel.this.pageData(list, ZcTjPanel.this.table_1);
      }

      public void itemStateChanged(int pagenum) {
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        List list = null;
        if ((ZcTjPanel.this.shourustime != null) || (ZcTjPanel.this.shouruetime != null) || (ZcTjPanel.this.shourustype != null))
          list = expensesMoudle.getList("income", ZcTjPanel.this.shourustype, ZcTjPanel.this.shourustime, ZcTjPanel.this.shouruetime, pagenum, 20);
        else {
          list = expensesMoudle.getList("income", null, null, null, pagenum, 20);
        }
        ZcTjPanel.this.pageData(list, ZcTjPanel.this.table_1);
      }

      public void export(MouseEvent e) {
        try {
          DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
          List list = null;
          if ((ZcTjPanel.this.shourustime != null) || (ZcTjPanel.this.shouruetime != null) || (ZcTjPanel.this.shourustype != null))
            list = expensesMoudle.getList("income", ZcTjPanel.this.shourustype, ZcTjPanel.this.shourustime, ZcTjPanel.this.shouruetime);
          else {
            list = expensesMoudle.getList("income", null, null, null);
          }
          if ((list != null) && (list.size() > 0)) {
            JFileChooser jfc = new JFileChooser("d:/");
            jfc.setFileSelectionMode(2);
            File fileff = new File("日常收入清单.csv");
            jfc.setSelectedFile(fileff);
            int result = jfc.showSaveDialog(ZcTjPanel.this.table);
            if (result == 1) return;
            File savedFile = jfc.getSelectedFile();
            if (savedFile.exists()) {
              int overwriteSelect = JOptionPane.showConfirmDialog(ZcTjPanel.this.table, "<html><font size=3>文件" + savedFile.getName() + "已存在，是否覆盖?</font><html>", "是否覆盖?", 
                0, 
                2);
              if (overwriteSelect != 0) {
                return;
              }
            }
            OutputStream out = new FileOutputStream(savedFile);
            out = new BufferedOutputStream(out, 4096);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(out, "gbk"));
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("收入日期");
            stringBuffer.append(",");
            stringBuffer.append("收入类型");
            stringBuffer.append(",");
            stringBuffer.append("收入金额");
            stringBuffer.append("\r\n");
            output.write(String.valueOf(stringBuffer));
            for (int i = 0; i < list.size(); i++) {
              DailyExpenses dailyExpenses = (DailyExpenses)list.get(i);
              StringBuilder sb = new StringBuilder(128);
              sb.append(dailyExpenses.getDate());
              sb.append(",");
              sb.append(dailyExpenses.getType());
              sb.append(",");
              sb.append(dailyExpenses.getPay());
              sb.append("\r\n");
              output.write(String.valueOf(sb));
            }
            output.close();

            JOptionPane.showMessageDialog(null, "文件导出成功");
          } else {
            JOptionPane.showMessageDialog(null, "没数据导出", null, 2);
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    panel_4.add(page_1, "South");

    JPanel panel_5 = new JPanel();
    panel_5.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    panel_4.add(panel_5, "North");
    GridBagLayout gbl_panel_5 = new GridBagLayout();
    gbl_panel_5.columnWidths = new int[] { 51, 0, 98, 78, 97, 50, 0, 73 };
    gbl_panel_5.rowHeights = new int[] { 24, 0, 0, 18 };
    gbl_panel_5.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_5.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel_5.setLayout(gbl_panel_5);

    final JLabel label_24 = new JLabel("");
    label_24.setForeground(Color.RED);
    GridBagConstraints gbc_label_24 = new GridBagConstraints();
    gbc_label_24.anchor = 17;
    gbc_label_24.gridwidth = 4;
    gbc_label_24.insets = new Insets(0, 0, 5, 5);
    gbc_label_24.gridx = 2;
    gbc_label_24.gridy = 0;
    panel_5.add(label_24, gbc_label_24);

    JLabel label_20 = new JLabel("开始时间");
    GridBagConstraints gbc_label_20 = new GridBagConstraints();
    gbc_label_20.insets = new Insets(0, 0, 5, 5);
    gbc_label_20.gridx = 1;
    gbc_label_20.gridy = 1;
    panel_5.add(label_20, gbc_label_20);

    this.datePicker_2 = new JXDatePicker();
    this.datePicker_2.getEditor().setColumns(12);
    this.datePicker_2.getEditor().setEditable(false);

    this.datePicker_2.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker_2.getEditor().setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_datePicker_2 = new GridBagConstraints();
    gbc_datePicker_2.anchor = 17;
    gbc_datePicker_2.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker_2.gridx = 2;
    gbc_datePicker_2.gridy = 1;
    panel_5.add(this.datePicker_2, gbc_datePicker_2);

    JLabel label_21 = new JLabel("结束时间");
    GridBagConstraints gbc_label_21 = new GridBagConstraints();
    gbc_label_21.anchor = 13;
    gbc_label_21.insets = new Insets(0, 0, 5, 5);
    gbc_label_21.gridx = 3;
    gbc_label_21.gridy = 1;
    panel_5.add(label_21, gbc_label_21);

    this.datePicker_3 = new JXDatePicker();
    this.datePicker_3.getEditor().setColumns(12);
    this.datePicker_3.getEditor().setEditable(false);
    this.datePicker_3.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker_3.getEditor().setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_datePicker_3 = new GridBagConstraints();
    gbc_datePicker_3.anchor = 17;
    gbc_datePicker_3.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker_3.gridx = 4;
    gbc_datePicker_3.gridy = 1;
    panel_5.add(this.datePicker_3, gbc_datePicker_3);

    JLabel lblel = new JLabel("收入项目");
    GridBagConstraints gbc_lblel = new GridBagConstraints();
    gbc_lblel.anchor = 13;
    gbc_lblel.insets = new Insets(0, 0, 5, 5);
    gbc_lblel.gridx = 1;
    gbc_lblel.gridy = 2;
    panel_5.add(lblel, gbc_lblel);

    this.comboBox_1 = new JComboBox();
    GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
    gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
    gbc_comboBox_1.fill = 2;
    gbc_comboBox_1.gridx = 2;
    gbc_comboBox_1.gridy = 2;
    panel_5.add(this.comboBox_1, gbc_comboBox_1);

    JButton button_1 = new JButton("查询");

    GridBagConstraints gbc_button_1 = new GridBagConstraints();
    gbc_button_1.anchor = 13;
    gbc_button_1.insets = new Insets(0, 0, 5, 5);
    gbc_button_1.gridx = 4;
    gbc_button_1.gridy = 2;
    panel_5.add(button_1, gbc_button_1);

    JLabel label_22 = new JLabel("收入金额总计");
    GridBagConstraints gbc_label_22 = new GridBagConstraints();
    gbc_label_22.insets = new Insets(0, 0, 5, 5);
    gbc_label_22.gridx = 6;
    gbc_label_22.gridy = 2;
    panel_5.add(label_22, gbc_label_22);

    final JLabel label_23 = new JLabel("0");
    label_23.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_label_23 = new GridBagConstraints();
    gbc_label_23.insets = new Insets(0, 0, 5, 0);
    gbc_label_23.gridx = 7;
    gbc_label_23.gridy = 2;
    panel_5.add(label_23, gbc_label_23);
    button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        String type = ZcTjPanel.this.comboBox.getEditor().getItem().toString();
        if ("all".equals(type)) {
          ZcTjPanel.this.shourustype = null;
          type = null;
        }
        String starttime = ZcTjPanel.this.datePicker.getEditor().getText();
        String endtime = ZcTjPanel.this.datePicker_1.getEditor().getText();
        ZcTjPanel.this.shourustime = starttime;
        ZcTjPanel.this.shouruetime = endtime;
        ZcTjPanel.this.shourustype = type;
        ZcTjPanel.this.submmit(ZcTjPanel.this.datePicker_2, ZcTjPanel.this.datePicker_3, "income", ZcTjPanel.this.table_1, label_24, page_1, type, label_23);
      }
    });
    JScrollPane scrollPane_1 = new JScrollPane();
    panel_4.add(scrollPane_1, "Center");

    this.table_1 = new JTable();
    this.table_1.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "ID", "收入日期", "收入项目", "收入金额", "操作" })
    {
      boolean[] columnEditables = new boolean[5];

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table_1.getColumnModel().getColumn(0).setResizable(false);
    this.table_1.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table_1.getColumnModel().getColumn(0).setMinWidth(0);
    this.table_1.getColumnModel().getColumn(0).setMaxWidth(0);
    this.table_1.getColumnModel().getColumn(1).setResizable(false);
    this.table_1.getColumnModel().getColumn(2).setPreferredWidth(238);
    this.table_1.getColumnModel().getColumn(3).setPreferredWidth(248);
    this.table_1.getColumnModel().getColumn(4).setResizable(false);
    this.table_1.getColumnModel().getColumn(4).setPreferredWidth(60);
    this.table_1.getColumnModel().getColumn(4).setMaxWidth(60);
    TableColumn tcs = this.table_1.getColumn("操作");
    tcs.setPreferredWidth(60);
    tcs.setCellRenderer(renderer);
    this.table_1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        ZcTjPanel.this.clickedTable(ZcTjPanel.this.table, "删除日常收入数据", "income");
      }
    });
    scrollPane_1.setViewportView(this.table_1);
  }

  public static ZcTjPanel getInstance()
  {
    if (zcTjPanel != null) {
      zcTjPanel.comboBox.setModel(new DefaultComboBoxModel(SelectType.getOptionsWithAll("ZCLX")));
      zcTjPanel.comboBox_1.setModel(new DefaultComboBoxModel(SelectType.getOptionsWithAll("SRLX")));
      zcTjPanel.init();
    }
    return zcTjPanel;
  }

  private void init() {
    double total = 0.0D;
    Config configmydzj = MoudleContentFactry.getConfigMoudle().getConfig("mydzj");
    if (configmydzj != null) {
      total = Double.parseDouble(configmydzj.getValue());
      this.label_6.setText(configmydzj.getValue());
    }

    Config configMYSJ = MoudleContentFactry.getConfigMoudle().getConfig("gdzc_mysj");
    if (configMYSJ != null) {
      total += Double.parseDouble(configMYSJ.getValue());
      this.label_8.setText(configMYSJ.getValue());
    }

    Config configMYGLF = MoudleContentFactry.getConfigMoudle().getConfig("gdzc_myglf");
    if (configMYGLF != null) {
      total += Double.parseDouble(configMYGLF.getValue());
      this.label_10.setText(configMYGLF.getValue());
    }

    Config configMYDF = MoudleContentFactry.getConfigMoudle().getConfig("mydf");
    if (configMYDF != null) {
      total += Double.parseDouble(configMYDF.getValue());
      this.label_12.setText(configMYDF.getValue());
    }

    Config configMYSFF = MoudleContentFactry.getConfigMoudle().getConfig("gdzc_mysf");
    if (configMYSFF != null) {
      total += Double.parseDouble(configMYSFF.getValue());
      this.label_14.setText(configMYSFF.getValue());
    }

    Config configMYQT = MoudleContentFactry.getConfigMoudle().getConfig("gdzc_myqt");
    if (configMYQT != null) {
      total += Double.parseDouble(configMYQT.getValue());
      this.label_16.setText(configMYQT.getValue());
    }
    this.label_18.setText(String.valueOf(total));
  }

  public void submmit(JXDatePicker startObj, JXDatePicker endObj, String mode, JTable jTableObj, JLabel msg, Page page, String type, JLabel totalLabel)
  {
    String starttime = startObj.getEditor().getText();
    String endtime = endObj.getEditor().getText();
    if ((starttime.length() == 0) && (endtime.length() > 0)) {
      msg.setText("请选择开始时间");
      return;
    }
    if ((endtime.length() == 0) && (starttime.length() > 0)) {
      msg.setText("请选择结束时间");
      return;
    }
    if ((starttime.length() > 0) && (endtime.length() > 0)) {
      long s = Long.parseLong(starttime.replaceAll("-", ""));
      long e = Long.parseLong(endtime.replaceAll("-", ""));
      if (s > e) {
        msg.setText("输入时间错误！开始时间应该小于结束时间");
        return;
      }
    }

    DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
    BigDecimal bigDecimal = expensesMoudle.sumDailyExpensesPay(mode, type, starttime, endtime);
    if (bigDecimal != null) {
      totalLabel.setText(bigDecimal.toString());
    }
    int total = expensesMoudle.getListSize(mode, type, starttime, endtime);
    page.setPageInfo(total);
    List list = expensesMoudle.getList(mode, type, starttime, endtime, 0, 20);
    pageData(list, jTableObj);
  }

  private void pageData(List list, JTable jTableObj) {
    if ((list != null) && (list.size() > 0)) {
      clear(jTableObj);
      DefaultTableModel tableModel = (DefaultTableModel)jTableObj.getModel();
      for (int i = 0; i < list.size(); i++) {
        DailyExpenses dailyExpenses = (DailyExpenses)list.get(i);
        Object[] rowData = { dailyExpenses.getId(), dailyExpenses.getDate(), dailyExpenses.getType(), dailyExpenses.getPay(), 
          "删除" };
        tableModel.insertRow(i, rowData);
      }
    }
  }

  public void clear(JTable jTableObj) {
    DefaultTableModel tableModel = (DefaultTableModel)jTableObj.getModel();
    if (tableModel.getRowCount() > 0) {
      int rows = tableModel.getRowCount();
      for (int i = 0; i < rows; i++)
        tableModel.removeRow(0);
    }
  }

  private void clickedTable(JTable jTableObj, String title, String mode)
  {
    int nCol = jTableObj.getSelectedColumn();
    int nRow = jTableObj.getSelectedRow();
    Object objSel = jTableObj.getValueAt(nRow, nCol);
    if ((objSel != null) && ((objSel instanceof String)) && 
      ("删除".equals(String.valueOf(objSel)))) {
      int response = JOptionPane.showConfirmDialog(null, 
        "确定删除此条数据?", title, 0);
      switch (response) {
      case 0:
        DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();
        DefaultTableModel tableModel = (DefaultTableModel)jTableObj.getModel();
        String id = String.valueOf(tableModel.getValueAt(nRow, 0));
        expensesMoudle.deleteById(id);
        tableModel.removeRow(nRow);

        BigDecimal bigDecimal = expensesMoudle.sumDailyExpensesPay(mode, this.stype, this.stime, this.etime);
        if (bigDecimal != null)
          this.label_4.setText(bigDecimal.toString());
        break;
      case -1:
      case 1:
      }
    }
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>收支统计";
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