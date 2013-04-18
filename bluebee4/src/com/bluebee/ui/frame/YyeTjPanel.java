package com.bluebee.ui.frame;

import com.bluebee.action.PageAction;
import com.bluebee.moudle.ConfigMoudle;
import com.bluebee.moudle.DailyExpensesMoudle;
import com.bluebee.moudle.FlowLogMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Config;
import com.bluebee.pojo.Flowlog;
import com.bluebee.ui.widget.Options;
import com.bluebee.ui.widget.Page;
import com.bluebee.ui.widget.SelectType;
import com.bluebee.util.DateHelper;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXDatePicker;

public class YyeTjPanel extends JPanel
  implements IPage
{
  private static YyeTjPanel yyeTjPanel = new YyeTjPanel();

  private String intString = "1234567890-";
  private String starttime = null;
  private String endtime = null;
  private boolean isDefaultPage = false;
  private JLabel msg;
  private JXDatePicker datePicker;
  private JXDatePicker datePicker_1;
  private JLabel timeslice;
  private JLabel flowlog;
  private JLabel profits;
  private JLabel costs;
  private JTable table;
  private DefaultTableModel defaultTableModel;
  private Page page;
  private JLabel label_1;
  private JLabel label_3;
  private JComboBox comboBox;

  private YyeTjPanel()
  {
    GridBagLayout gridBagLayout_1 = new GridBagLayout();
    gridBagLayout_1.columnWidths = new int[] { 565 };
    gridBagLayout_1.rowHeights = new int[] { 86, 130, 270 };
    gridBagLayout_1.columnWeights = new double[] { 1.0D, 4.9E-324D };
    gridBagLayout_1.rowWeights = new double[] { 0.0D, 0.0D, 1.0D, 4.9E-324D };
    setLayout(gridBagLayout_1);

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "查询", 4, 2, null, null));
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 42, 61, 105 };
    gridBagLayout.rowHeights = new int[] { 19, 19 };
    gridBagLayout.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0001D };
    gridBagLayout.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0001D };
    panel.setLayout(gridBagLayout);
    GridBagConstraints gbc_panel = new GridBagConstraints();
    gbc_panel.anchor = 11;
    gbc_panel.fill = 2;
    gbc_panel.insets = new Insets(0, 0, 5, 0);
    gbc_panel.gridx = 0;
    gbc_panel.gridy = 0;
    add(panel, gbc_panel);

    this.msg = new JLabel("");
    this.msg.setForeground(Color.RED);
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.gridwidth = 3;
    gbc_label.insets = new Insets(0, 0, 5, 5);
    gbc_label.gridx = 1;
    gbc_label.gridy = 0;
    panel.add(this.msg, gbc_label);

    JLabel label_2 = new JLabel("开始时间");
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.insets = new Insets(0, 0, 5, 5);
    gbc_label_2.gridx = 1;
    gbc_label_2.gridy = 1;
    panel.add(label_2, gbc_label_2);

    this.datePicker = new JXDatePicker();
    this.datePicker.getEditor().setEnabled(false);
    this.datePicker.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker.getEditor().setFont(new Font("宋体", 1, 12));
    this.datePicker.setDate(DateHelper.currentDate());
    GridBagConstraints gbc_datePicker = new GridBagConstraints();
    gbc_datePicker.fill = 2;
    gbc_datePicker.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker.gridx = 2;
    gbc_datePicker.gridy = 1;
    panel.add(this.datePicker, gbc_datePicker);

    JLabel label = new JLabel("结束时间");
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = 13;
    gbc.insets = new Insets(0, 5, 5, 5);
    gbc.gridx = 3;
    gbc.gridy = 1;
    panel.add(label, gbc);

    this.datePicker_1 = new JXDatePicker();
    this.datePicker_1.getEditor().setEnabled(false);
    this.datePicker_1.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker_1.getEditor().setFont(new Font("宋体", 1, 12));
    this.datePicker_1.setDate(DateHelper.currentDate());
    GridBagConstraints gbc_datePicker_1 = new GridBagConstraints();
    gbc_datePicker_1.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker_1.gridx = 4;
    gbc_datePicker_1.gridy = 1;
    panel.add(this.datePicker_1, gbc_datePicker_1);

    JLabel label_4 = new JLabel("类型");
    GridBagConstraints gbc_label_4 = new GridBagConstraints();
    gbc_label_4.anchor = 13;
    gbc_label_4.insets = new Insets(0, 0, 0, 5);
    gbc_label_4.gridx = 1;
    gbc_label_4.gridy = 2;
    panel.add(label_4, gbc_label_4);

    this.comboBox = new JComboBox();
    this.comboBox = new JComboBox();
    this.comboBox.setModel(new DefaultComboBoxModel(
      SelectType.getOptionsWithAll("HWLX")));
    GridBagConstraints gbc_comboBox = new GridBagConstraints();
    gbc_comboBox.insets = new Insets(0, 0, 0, 5);
    gbc_comboBox.fill = 2;
    gbc_comboBox.gridx = 2;
    gbc_comboBox.gridy = 2;
    panel.add(this.comboBox, gbc_comboBox);

    JButton button = new JButton("查  询");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        YyeTjPanel.this.submmit();
      }
    });
    GridBagConstraints gbc1 = new GridBagConstraints();
    gbc1.anchor = 13;
    gbc1.insets = new Insets(0, 0, 0, 5);
    gbc1.gridx = 4;
    gbc1.gridy = 2;
    panel.add(button, gbc1);

    JPanel panel1 = new JPanel();
    panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "营业额统计", 4, 2, null, null));
    panel1.setLayout(null);
    GridBagConstraints gbc_panel1 = new GridBagConstraints();
    gbc_panel1.fill = 1;
    gbc_panel1.insets = new Insets(0, 0, 5, 0);
    gbc_panel1.gridx = 0;
    gbc_panel1.gridy = 1;
    add(panel1, gbc_panel1);

    JLabel label1 = new JLabel("时间段：");
    label1.setBounds(72, 16, 48, 15);
    panel1.add(label1);

    JLabel label2 = new JLabel("流水总计：");
    label2.setBounds(60, 41, 60, 17);
    panel1.add(label2);

    this.flowlog = new JLabel("0");
    this.flowlog.setBounds(125, 41, 70, 17);
    this.flowlog.setFont(new Font("宋体", 1, 12));
    this.flowlog.setBorder(UIManager.getBorder("MenuBar.border"));
    panel1.add(this.flowlog);

    JLabel label3 = new JLabel("成本总计：");
    label3.setBounds(288, 41, 60, 17);
    panel1.add(label3);

    this.costs = new JLabel("0");
    this.costs.setBounds(351, 42, 97, 17);
    this.costs.setFont(new Font("宋体", 1, 12));
    this.costs.setBorder(UIManager.getBorder("MenuBar.border"));
    panel1.add(this.costs);

    JLabel label4 = new JLabel("利润总计：");
    label4.setBounds(60, 94, 60, 17);
    panel1.add(label4);

    this.profits = new JLabel("0");
    this.profits.setBounds(125, 94, 70, 17);
    this.profits.setFont(new Font("宋体", 1, 12));
    this.profits.setBorder(UIManager.getBorder("MenuBar.border"));
    panel1.add(this.profits);

    this.timeslice = new JLabel("");
    this.timeslice.setFont(new Font("宋体", 1, 12));
    this.timeslice.setBounds(125, 16, 335, 15);
    panel1.add(this.timeslice);

    JLabel label5 = new JLabel("日常支出总计：");
    label5.setBounds(36, 68, 84, 15);
    panel1.add(label5);

    this.label_1 = new JLabel("0");
    this.label_1.setFont(new Font("宋体", 1, 12));
    this.label_1.setBorder(UIManager.getBorder("MenuBar.border"));
    this.label_1.setBounds(125, 68, 70, 15);
    panel1.add(this.label_1);

    JLabel label6 = new JLabel("每月固定支出总计：");
    label6.setBounds(240, 68, 108, 15);
    panel1.add(label6);

    this.label_3 = new JLabel("0");
    this.label_3.setFont(new Font("宋体", 1, 12));
    this.label_3.setBorder(UIManager.getBorder("MenuBar.border"));
    this.label_3.setBounds(351, 69, 97, 15);
    panel1.add(this.label_3);

    JLabel label7 = new JLabel("利润总计");
    label7.setBounds(349, 100, 48, 15);
    panel1.add(label7);

    JLabel label8 = new JLabel("=");
    label8.setFont(new Font("宋体", 1, 18));
    label8.setBounds(399, 100, 16, 15);
    panel1.add(label8);

    JLabel label9 = new JLabel("流水总计");
    label9.setBounds(412, 95, 48, 20);
    panel1.add(label9);

    JLabel label10 = new JLabel("-");
    label10.setFont(new Font("宋体", 1, 18));
    label10.setBounds(464, 100, 16, 15);
    panel1.add(label10);

    JLabel label11 = new JLabel("成本总计");
    label11.setBounds(480, 98, 54, 15);
    panel1.add(label11);

    JLabel label12 = new JLabel("-");
    label12.setFont(new Font("宋体", 1, 18));
    label12.setBounds(534, 98, 16, 15);
    panel1.add(label12);

    JLabel label13 = new JLabel("日常支出总计");
    label13.setBounds(548, 98, 74, 15);
    panel1.add(label13);

    JPanel panel2 = new JPanel();
    panel2.setBorder(new TitledBorder(null, "流水明细", 4, 2, null, null));
    GridBagConstraints gbc_panel2 = new GridBagConstraints();
    gbc_panel2.fill = 1;
    gbc_panel2.gridx = 0;
    gbc_panel2.gridy = 2;
    add(panel2, gbc_panel2);
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[4];
    gbl_panel.rowHeights = new int[] { 221 };
    gbl_panel.columnWeights = new double[] { 0.0D, 1.0D, 0.0D, 4.9E-324D };
    gbl_panel.rowWeights = new double[] { 1.0D, 0.0D, 4.9E-324D };
    panel2.setLayout(gbl_panel);

    JScrollPane scrollPane = new JScrollPane();
    GridBagConstraints gbc_scrollPane = new GridBagConstraints();
    gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
    gbc_scrollPane.fill = 1;
    gbc_scrollPane.gridx = 1;
    gbc_scrollPane.gridy = 0;
    panel2.add(scrollPane, gbc_scrollPane);

    this.table = new JTable();

    this.defaultTableModel = new DefaultTableModel(
      new Object[0][], 
      new String[] { 
      "货号", "售价", "数量", "利润", "成本", "类型", "名称", "时间" })
    {
      boolean[] columnEditables = new boolean[8];

      Class[] columnTypes = { 
        String.class, BigDecimal.class, Integer.class, BigDecimal.class, BigDecimal.class, String.class, String.class, String.class };

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }

      public boolean isCellEditable(int row, int column) {
        return this.columnEditables[column];
      }
    };
    this.table.setAutoCreateRowSorter(true);
    this.table.setModel(this.defaultTableModel);
    scrollPane.setViewportView(this.table);

    DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer() {
      public void setValue(Object value) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(value));

        if (bigDecimal.compareTo(new BigDecimal(0)) == -1)
          setForeground(Color.green);
        else {
          setForeground(Color.red);
        }

        setText(value == null ? "" : value.toString());
      }
    };
    this.table.getColumn("利润").setCellRenderer(fontColor);
    this.table.getColumn("售价").setCellRenderer(fontColor);
    this.table.getColumn("数量").setCellRenderer(fontColor);

    this.page = new Page(new PageAction()
    {
      public void pageFirst() {
        if ((YyeTjPanel.this.starttime != null) && (YyeTjPanel.this.endtime != null)) {
          FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();

          List list = flowLogMoudle.getStatistical(YyeTjPanel.this.starttime, YyeTjPanel.this.endtime, null, 0, 20);
          YyeTjPanel.this.pageData(list);
        }
      }

      public void pagePrev(int pagenum) {
        if ((YyeTjPanel.this.starttime != null) && (YyeTjPanel.this.endtime != null)) {
          FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
          String selvalue = YyeTjPanel.this.getType();

          List list = flowLogMoudle.getStatistical(YyeTjPanel.this.starttime, YyeTjPanel.this.endtime, selvalue, pagenum, 20);
          YyeTjPanel.this.pageData(list);
        }
      }

      public void pageNext(int pagenum) {
        if ((YyeTjPanel.this.starttime != null) && (YyeTjPanel.this.endtime != null)) {
          FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
          String selvalue = YyeTjPanel.this.getType();
          List list = flowLogMoudle.getStatistical(YyeTjPanel.this.starttime, YyeTjPanel.this.endtime, selvalue, pagenum, 20);
          YyeTjPanel.this.pageData(list);
        }
      }

      public void pageLast(int pagenum) {
        if ((YyeTjPanel.this.starttime != null) && (YyeTjPanel.this.endtime != null)) {
          FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
          String selvalue = YyeTjPanel.this.getType();
          List list = flowLogMoudle.getStatistical(YyeTjPanel.this.starttime, YyeTjPanel.this.endtime, selvalue, pagenum, 20);
          YyeTjPanel.this.pageData(list);
        }
      }

      public void itemStateChanged(int pagenum) {
        if ((YyeTjPanel.this.starttime != null) && (YyeTjPanel.this.endtime != null)) {
          FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
          String selvalue = YyeTjPanel.this.getType();
          List list = flowLogMoudle.getStatistical(YyeTjPanel.this.starttime, YyeTjPanel.this.endtime, selvalue, pagenum, 20);
          YyeTjPanel.this.pageData(list);
        }
      }

      public void export(MouseEvent e)
      {
        try {
          FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
          List list = flowLogMoudle.getStatistical(YyeTjPanel.this.starttime, YyeTjPanel.this.endtime, YyeTjPanel.this.getType());
          if ((list != null) && (list.size() > 0)) {
            JFileChooser jfc = new JFileChooser("d:/");
            jfc.setFileSelectionMode(2);
            File fileff = new File(YyeTjPanel.this.starttime + "--" + YyeTjPanel.this.endtime + "营业额明细.csv");
            jfc.setSelectedFile(fileff);
            int result = jfc.showSaveDialog(YyeTjPanel.this.table);
            if (result == 1) return;
            File savedFile = jfc.getSelectedFile();
            if (savedFile.exists()) {
              int overwriteSelect = JOptionPane.showConfirmDialog(YyeTjPanel.this.table, "<html><font size=3>文件" + savedFile.getName() + "已存在，是否覆盖?</font><html>", "是否覆盖?", 
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
            stringBuffer.append("货号");
            stringBuffer.append(",");
            stringBuffer.append("售价");
            stringBuffer.append(",");
            stringBuffer.append("数量");
            stringBuffer.append(",");
            stringBuffer.append("利润");
            stringBuffer.append(",");
            stringBuffer.append("成本");
            stringBuffer.append(",");
            stringBuffer.append("类型");
            stringBuffer.append(",");
            stringBuffer.append("名称");
            stringBuffer.append(",");
            stringBuffer.append("销售时间");
            stringBuffer.append("\r\n");
            output.write(String.valueOf(stringBuffer));
            for (int i = 0; i < list.size(); i++) {
              Flowlog flowlog = (Flowlog)list.get(i);
              StringBuilder sb = new StringBuilder(128);
              sb.append(flowlog.getCatno());
              sb.append(",");
              sb.append(flowlog.getSellprice());
              sb.append(",");
              sb.append(flowlog.getAmount());
              sb.append(",");
              sb.append(flowlog.getLrprice());
              sb.append(",");
              sb.append(flowlog.getCostprice());
              sb.append(",");
              sb.append(flowlog.getType());
              sb.append(",");
              sb.append(flowlog.getStockname());
              sb.append(",");
              sb.append(flowlog.getDate());
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
    GridBagConstraints gbc_page = new GridBagConstraints();
    gbc_page.insets = new Insets(0, 0, 0, 5);
    gbc_page.fill = 1;
    gbc_page.gridx = 1;
    gbc_page.gridy = 1;
    panel2.add(this.page, gbc_page);
  }

  private void submmit()
  {
    this.starttime = this.datePicker.getEditor().getText();
    this.endtime = this.datePicker_1.getEditor().getText();

    if ((this.starttime == null) || (this.starttime.trim().length() == 0)) {
      this.msg.setText("请输入开始时间");
      this.datePicker.getEditor().requestFocus();
      return;
    }
    if ((this.endtime == null) || (this.endtime.trim().length() == 0)) {
      this.msg.setText("请输入结束时间");
      this.datePicker_1.getEditor().requestFocus();
      return;
    }
    String nows = DateHelper.getNowDateTime();
    long now = Long.parseLong(nows.replaceAll("-", ""));
    long s = Long.parseLong(this.starttime.replaceAll("-", ""));
    long e = Long.parseLong(this.endtime.replaceAll("-", ""));

    if (e > now) {
      this.msg.setText("输入时间范围错误！只能统计今天以前的营业额");
      return;
    }
    if (s > e) {
      this.msg.setText("输入时间范围错误！开始时间应该小于结束时间");
      return;
    }

    String selvalue = getType();

    this.timeslice.setText(this.starttime + " 到 " + this.endtime);
    FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
    int toltal = flowLogMoudle.getStatisticalSize(this.starttime, this.endtime, selvalue);
    List list = flowLogMoudle.getStatistical(this.starttime, this.endtime, selvalue, 0, 20);
    this.page.setPageInfo(toltal);

    BigDecimal flowprice = flowLogMoudle.sumFlowPrice(this.starttime, this.endtime, selvalue);

    BigDecimal lrprice = flowLogMoudle.sumLrPrice(this.starttime, this.endtime, selvalue);
    if (lrprice == null) {
      lrprice = new BigDecimal(0);
    }

    BigDecimal costprice = flowLogMoudle.sumCostPrice(this.starttime, this.endtime, selvalue);
    this.flowlog.setText(String.valueOf(flowprice));

    this.costs.setText(String.valueOf(costprice));
    DailyExpensesMoudle expensesMoudle = MoudleContentFactry.getDailyExpensesMoudle();

    BigDecimal zczj = expensesMoudle.sumDailyExpensesPay(this.starttime, this.endtime);
    if (zczj != null)
      this.label_1.setText(String.valueOf(zczj));
    else {
      zczj = new BigDecimal(0);
    }

    this.profits.setText(String.valueOf(lrprice.subtract(zczj)));

    this.label_3.setText(String.valueOf(getTotal()));
    pageData(list);
  }

  private double getTotal() {
    double total = 0.0D;
    Config configmygdzczh = MoudleContentFactry.getConfigMoudle().getConfig("gdzc_ZH");
    if (configmygdzczh != null) {
      total = Double.parseDouble(configmygdzczh.getValue());
    }
    return total;
  }

  private void pageData(List list) {
    cleartable();
    if ((list != null) && (list.size() > 0))
      for (int i = 0; i < list.size(); i++) {
        Flowlog flowlog = (Flowlog)list.get(i);
        Object[] rowData = { flowlog.getCatno(), flowlog.getSellprice(), Double.valueOf(flowlog.getAmount()), flowlog.getLrprice(), flowlog.getCostprice(), flowlog.getType(), flowlog.getStockname(), flowlog.getDate() };
        this.defaultTableModel.insertRow(0, rowData);
      }
    else
      clear();
  }

  public void cleartable()
  {
    if (this.defaultTableModel.getRowCount() > 0) {
      int rows = this.defaultTableModel.getRowCount();
      for (int i = 0; i < rows; i++)
        this.defaultTableModel.removeRow(0);
    }
  }

  private void clear()
  {
    this.flowlog.setText("0");
    this.profits.setText("0");
    this.costs.setText("0");
  }

  public static YyeTjPanel getInstance() {
    if (yyeTjPanel != null) {
      yyeTjPanel.comboBox.setModel(new DefaultComboBoxModel(
        SelectType.getOptionsWithAll("HWLX")));
    }
    return yyeTjPanel;
  }

  public void disposePage()
  {
  }

  public String getPageId() {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>营业额统计";
  }

  public boolean isDefaultPage()
  {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool) {
    this.isDefaultPage = bool;
  }

  private String getType()
  {
    Options options = (Options)this.comboBox.getSelectedItem();
    if (options != null) {
      String key = options.getKey();
      String text = options.getText();
      if ("all".equals(key)) {
        return null;
      }
      return text;
    }

    return null;
  }
}