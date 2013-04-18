package com.bluebee.ui.frame;

import com.bluebee.action.PageAction;
import com.bluebee.moudle.FlowLogMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Flowlog;
import com.bluebee.ui.widget.Page;
import com.bluebee.util.DateHelper;

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
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;

public class GkJLPanel extends JPanel
  implements IPage
{
  private static GkJLPanel gkJLPanel = new GkJLPanel();
  private JTextField textField;
  private JTextField textField_1;
  private JTable table;
  private JXDatePicker datePicker;
  private JXDatePicker datePicker_1;
  private JLabel msg;
  private Page page;
  private boolean isDefaultPage = false;

  public static GkJLPanel getInstance()
  {
    if (gkJLPanel == null) {
      gkJLPanel = new GkJLPanel();
    }
    return gkJLPanel;
  }

  private GkJLPanel() {
    setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "查询", 4, 2, null, null));
    add(panel, "North");
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[] { 71, 0, 79, 43 };
    gbl_panel.rowHeights = new int[4];
    gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 
      4.9E-324D };
    gbl_panel.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel.setLayout(gbl_panel);

    JLabel label = new JLabel("开始时间");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 5, 5);
    gbc_label.gridx = 1;
    gbc_label.gridy = 0;
    panel.add(label, gbc_label);

    this.datePicker = new JXDatePicker();
    this.datePicker.getEditor().setEditable(false);
    this.datePicker.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker.getEditor().setColumns(10);
    this.datePicker.getEditor().setFont(new Font("宋体", 0, 12));
    this.datePicker.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_datePicker = new GridBagConstraints();
    gbc_datePicker.fill = 2;
    gbc_datePicker.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker.gridx = 2;
    gbc_datePicker.gridy = 0;
    panel.add(this.datePicker, gbc_datePicker);

    JLabel label_2 = new JLabel("结束时间");
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.insets = new Insets(0, 0, 5, 5);
    gbc_label_2.gridx = 4;
    gbc_label_2.gridy = 0;
    panel.add(label_2, gbc_label_2);

    this.datePicker_1 = new JXDatePicker();
    this.datePicker_1.getEditor().setEditable(false);
    this.datePicker_1.setFormats(new String[] { "yyyy-MM-dd" });
    this.datePicker_1.getEditor().setColumns(10);
    this.datePicker_1.getEditor().setFont(new Font("宋体", 0, 12));
    this.datePicker_1.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_datePicker_1 = new GridBagConstraints();
    gbc_datePicker_1.anchor = 17;
    gbc_datePicker_1.insets = new Insets(0, 0, 5, 0);
    gbc_datePicker_1.gridx = 5;
    gbc_datePicker_1.gridy = 0;
    panel.add(this.datePicker_1, gbc_datePicker_1);

    JLabel label_1 = new JLabel("客户号码");
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.anchor = 13;
    gbc_label_1.fill = 3;
    gbc_label_1.insets = new Insets(0, 0, 5, 5);
    gbc_label_1.gridx = 1;
    gbc_label_1.gridy = 1;
    panel.add(label_1, gbc_label_1);

    this.textField = new JTextField();
    this.textField.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.fill = 2;
    gbc_textField.insets = new Insets(0, 0, 5, 5);
    gbc_textField.gridx = 2;
    gbc_textField.gridy = 1;
    panel.add(this.textField, gbc_textField);
    this.textField.setColumns(10);

    JLabel label_3 = new JLabel("客户名称");
    GridBagConstraints gbc_label_3 = new GridBagConstraints();
    gbc_label_3.anchor = 13;
    gbc_label_3.insets = new Insets(0, 0, 5, 5);
    gbc_label_3.gridx = 4;
    gbc_label_3.gridy = 1;
    panel.add(label_3, gbc_label_3);

    this.textField_1 = new JTextField();
    this.textField_1.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
    gbc_textField_1.insets = new Insets(0, 0, 5, 0);
    gbc_textField_1.fill = 2;
    gbc_textField_1.gridx = 5;
    gbc_textField_1.gridy = 1;
    panel.add(this.textField_1, gbc_textField_1);
    this.textField_1.setColumns(10);

    JButton button = new JButton(" 查 询 ");
    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        String starttime = GkJLPanel.this.datePicker.getEditor().getText();
        String endtime = GkJLPanel.this.datePicker_1.getEditor().getText();
        GkJLPanel.this.msg.setText("");
        if ((starttime == null) || (starttime.trim().length() == 0)) {
          GkJLPanel.this.msg.setText("请输入开始时间");
          GkJLPanel.this.datePicker.getEditor().requestFocus();
          return;
        }
        if ((endtime == null) || (endtime.trim().length() == 0)) {
          GkJLPanel.this.msg.setText("请输入结束时间");
          GkJLPanel.this.datePicker_1.getEditor().requestFocus();
          return;
        }
        String nows = DateHelper.getNowDateTime();
        long now = Long.parseLong(nows.replaceAll("-", ""));
        long s = Long.parseLong(starttime.replaceAll("-", ""));
        long end = Long.parseLong(endtime.replaceAll("-", ""));
        if (end > now) {
          GkJLPanel.this.msg.setText("输入时间范围错误！只能查看今天以前的购买记录");
          return;
        }
        if (s > end) {
          GkJLPanel.this.msg.setText("输入时间范围错误！开始时间应该小于结束时间");
          return;
        }
        String cno = GkJLPanel.this.textField.getText();
        String cnName = GkJLPanel.this.textField_1.getText();
        FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
        int size = flowLogMoudle.getFlowLogKeHLogSize(starttime, endtime, cno, cnName);
        List list = flowLogMoudle.getFlowLogKeHLog(starttime, endtime, cno, cnName, 0, 20);
        GkJLPanel.this.page.setPageInfo(size);
        GkJLPanel.this.pageData(list);
      }
    });
    this.msg = new JLabel("");
    this.msg.setForeground(Color.RED);
    GridBagConstraints gbc_label_4 = new GridBagConstraints();
    gbc_label_4.anchor = 17;
    gbc_label_4.gridwidth = 4;
    gbc_label_4.insets = new Insets(0, 0, 0, 5);
    gbc_label_4.gridx = 1;
    gbc_label_4.gridy = 2;
    panel.add(this.msg, gbc_label_4);
    button.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.gridx = 5;
    gbc_button.gridy = 2;
    panel.add(button, gbc_button);

    JPanel panel_1 = new JPanel();
    panel_1.setBorder(new TitledBorder(null, 
      "客户购买明细", 4, 
      2, null, null));
    panel_1.setFont(new Font("宋体", 0, 12));
    add(panel_1, "Center");
    panel_1.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    panel_1.add(scrollPane, "Center");

    this.table = new JTable();
    this.table.setModel(new DefaultTableModel(
      new Object[0][], 
      new String[] { 
      "id", "客户号", "客户名称", "购买时间", "货物名称", "货号", "类型", "购买数量", "购买价格" })
    {
      boolean[] columnEditables = new boolean[9];

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(0).setMinWidth(0);
    this.table.getColumnModel().getColumn(0).setMaxWidth(0);
    scrollPane.setViewportView(this.table);

    this.page = new Page(new PageAction() {
      public void pageFirst() {
        String starttime = GkJLPanel.this.datePicker.getEditor().getText();
        String endtime = GkJLPanel.this.datePicker_1.getEditor().getText();
        String cno = GkJLPanel.this.textField.getText();
        String cnName = GkJLPanel.this.textField_1.getText();
        FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
        List list = flowLogMoudle.getFlowLogKeHLog(starttime, endtime, cno, cnName, 0, 20);
        GkJLPanel.this.pageData(list);
      }

      public void pagePrev(int pagenum) {
        String starttime = GkJLPanel.this.datePicker.getEditor().getText();
        String endtime = GkJLPanel.this.datePicker_1.getEditor().getText();
        String cno = GkJLPanel.this.textField.getText();
        String cnName = GkJLPanel.this.textField_1.getText();
        FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
        List list = flowLogMoudle.getFlowLogKeHLog(starttime, endtime, cno, cnName, pagenum, 20);
        GkJLPanel.this.pageData(list);
      }

      public void pageNext(int pagenum) {
        String starttime = GkJLPanel.this.datePicker.getEditor().getText();
        String endtime = GkJLPanel.this.datePicker_1.getEditor().getText();
        String cno = GkJLPanel.this.textField.getText();
        String cnName = GkJLPanel.this.textField_1.getText();
        FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
        List list = flowLogMoudle.getFlowLogKeHLog(starttime, endtime, cno, cnName, pagenum, 20);
        GkJLPanel.this.pageData(list);
      }

      public void pageLast(int pagenum) {
        String starttime = GkJLPanel.this.datePicker.getEditor().getText();
        String endtime = GkJLPanel.this.datePicker_1.getEditor().getText();
        String cno = GkJLPanel.this.textField.getText();
        String cnName = GkJLPanel.this.textField_1.getText();
        FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
        List list = flowLogMoudle.getFlowLogKeHLog(starttime, endtime, cno, cnName, pagenum, 20);
        GkJLPanel.this.pageData(list);
      }

      public void itemStateChanged(int pagenum) {
        String starttime = GkJLPanel.this.datePicker.getEditor().getText();
        String endtime = GkJLPanel.this.datePicker_1.getEditor().getText();
        String cno = GkJLPanel.this.textField.getText();
        String cnName = GkJLPanel.this.textField_1.getText();
        FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
        List list = flowLogMoudle.getFlowLogKeHLog(starttime, endtime, cno, cnName, pagenum, 20);
        GkJLPanel.this.pageData(list);
      }

      public void export(MouseEvent e) {
        try {
          FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
          String starttime = GkJLPanel.this.datePicker.getEditor().getText();
          String endtime = GkJLPanel.this.datePicker_1.getEditor().getText();
          String cno = GkJLPanel.this.textField.getText();
          String cnName = GkJLPanel.this.textField_1.getText();
          List list = flowLogMoudle.getFlowLogKeHLog(starttime, endtime, cno, cnName);
          if ((list != null) && (list.size() > 0)) {
            JFileChooser jfc = new JFileChooser("d:/");
            jfc.setFileSelectionMode(2);
            File fileff = new File(starttime + "--" + endtime + "客户购买明细.csv");
            jfc.setSelectedFile(fileff);
            int result = jfc.showSaveDialog(GkJLPanel.this.table);
            if (result == 1) return;
            File savedFile = jfc.getSelectedFile();
            if (savedFile.exists()) {
              int overwriteSelect = JOptionPane.showConfirmDialog(GkJLPanel.this.table, "<html><font size=3>文件" + savedFile.getName() + "已存在，是否覆盖?</font><html>", "是否覆盖?", 
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
            stringBuffer.append("客户号");
            stringBuffer.append(",");
            stringBuffer.append("客户名称");
            stringBuffer.append(",");
            stringBuffer.append("货物名称");
            stringBuffer.append(",");
            stringBuffer.append("购买时间");
            stringBuffer.append(",");
            stringBuffer.append("购买价格");
            stringBuffer.append("\r\n");
            output.write(String.valueOf(stringBuffer));
            for (int i = 0; i < list.size(); i++) {
              Flowlog flowlog = (Flowlog)list.get(i);
              StringBuilder sb = new StringBuilder(128);
              sb.append(flowlog.getCustomNo());
              sb.append(",");
              sb.append(flowlog.getCustomName());
              sb.append(",");
              sb.append(flowlog.getStockname());
              sb.append(",");
              sb.append(flowlog.getDate());
              sb.append(",");
              sb.append(flowlog.getSellprice());
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
    add(this.page, "South");
  }

  private void pageData(List list) {
    cleartable();
    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table.getModel();
    if ((list != null) && (list.size() > 0))
      for (int i = 0; i < list.size(); i++) {
        Flowlog flowlog = (Flowlog)list.get(i);
        Object[] rowData = { 
          flowlog.getFlowno(), 
          flowlog.getCustomNo(), 
          flowlog.getCustomName(), 
          flowlog.getDate(), 
          flowlog.getStockname(), 
          flowlog.getCatno(), 
          flowlog.getType(), 
          Double.valueOf(flowlog.getAmount()), 
          flowlog.getSellprice() };
        defaultTableModel.insertRow(0, rowData);
      }
  }

  public void cleartable()
  {
    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table.getModel();
    if (defaultTableModel.getRowCount() > 0) {
      int rows = defaultTableModel.getRowCount();
      for (int i = 0; i < rows; i++)
        defaultTableModel.removeRow(0);
    }
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>客户购买统计";
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