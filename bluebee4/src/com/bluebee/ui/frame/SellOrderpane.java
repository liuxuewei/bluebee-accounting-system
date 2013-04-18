package com.bluebee.ui.frame;

import com.bluebee.action.PageAction;
import com.bluebee.moudle.FlowLogMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Flowlog;
import com.bluebee.ui.widget.Page;

import java.awt.BorderLayout;
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
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;

public class SellOrderpane extends JPanel
  implements IPage
{
  private static SellOrderpane sellOrderpane = new SellOrderpane();

  private boolean isDefaultPage = false;
  private JTable table;
  private JXDatePicker datePicker;
  private JXDatePicker datePicker_1;
  private DefaultTableModel defaultTableModel;
  private JLabel msg;
  private String starttime = null;
  private String endtime = null;
  private Page page;

  private SellOrderpane()
  {
    setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "查询", 4, 2, null, null));
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 51 };
    gridBagLayout.rowHeights = new int[5];
    gridBagLayout.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0001D };
    gridBagLayout.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0001D };
    panel.setLayout(gridBagLayout);
    add(panel, "North");

    this.msg = new JLabel("");
    this.msg.setForeground(Color.RED);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = 2;
    gbc.insets = new Insets(0, 0, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(this.msg, gbc);

    JLabel label = new JLabel("开始时间");
    GridBagConstraints gbc1 = new GridBagConstraints();
    gbc1.anchor = 13;
    gbc1.insets = new Insets(0, 100, 5, 5);
    gbc1.gridx = 0;
    gbc1.gridy = 2;
    panel.add(label, gbc1);

    this.datePicker = new JXDatePicker();
    this.datePicker.getEditor().setEnabled(false);

    this.datePicker.getEditor().setFont(new Font("宋体", 1, 12));
    this.datePicker.getEditor().setColumns(10);
    this.datePicker.setFormats(new String[] { "yyyy-MM-dd" });
    GridBagConstraints gbc_datePicker = new GridBagConstraints();
    gbc_datePicker.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker.gridx = 1;
    gbc_datePicker.gridy = 2;
    panel.add(this.datePicker, gbc_datePicker);

    JLabel label1 = new JLabel("结束时间");
    GridBagConstraints gbc11 = new GridBagConstraints();
    gbc11.anchor = 13;
    gbc11.insets = new Insets(0, 5, 5, 5);
    gbc11.gridx = 2;
    gbc11.gridy = 2;
    panel.add(label1, gbc11);

    this.datePicker_1 = new JXDatePicker();
    this.datePicker_1.getEditor().setEnabled(false);
    this.datePicker_1.setFocusCycleRoot(true);
    this.datePicker_1.getEditor().setFont(new Font("宋体", 1, 12));
    this.datePicker_1.setFormats(new String[] { "yyyy-MM-dd" });
    GridBagConstraints gbc_datePicker_1 = new GridBagConstraints();
    gbc_datePicker_1.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker_1.gridx = 3;
    gbc_datePicker_1.gridy = 2;
    panel.add(this.datePicker_1, gbc_datePicker_1);

    JButton button = new JButton("查  询");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SellOrderpane.this.submmit();
      }
    });
    GridBagConstraints gbc2 = new GridBagConstraints();
    gbc2.insets = new Insets(2, 0, 5, 0);
    gbc2.gridx = 4;
    gbc2.gridy = 2;
    panel.add(button, gbc2);

    JPanel panel1 = new JPanel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    gridBagLayout1.columnWidths = new int[2];
    gridBagLayout1.rowHeights = new int[] { 131 };
    gridBagLayout1.columnWeights = new double[] { 1.0D, 0.0001D };
    gridBagLayout1.rowWeights = new double[] { 1.0D, 0.0D, 0.0001D };
    panel1.setLayout(gridBagLayout1);
    panel1.setBorder(new TitledBorder(null, "查询列表", 4, 2, null, null));
    add(panel1, "Center");

    JScrollPane scrollPane = new JScrollPane();
    GridBagConstraints gbc3 = new GridBagConstraints();
    gbc3.insets = new Insets(10, 10, 10, 10);
    gbc3.fill = 1;
    gbc3.gridx = 0;
    gbc3.gridy = 0;
    panel1.add(scrollPane, gbc3);

    this.table = new JTable();
    this.defaultTableModel = new DefaultTableModel(null, 
      new String[] { 
      "货号", "销售总数量", "销售总利润", "类型", "名称" })
    {
      boolean[] columnEditables = new boolean[5];

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    };
    this.table.setModel(this.defaultTableModel);
    scrollPane.setViewportView(this.table);

    this.page = new Page(new PageAction() {
      public void pageFirst() {
        if ((SellOrderpane.this.starttime != null) && (SellOrderpane.this.endtime != null)) {
          List list = MoudleContentFactry.getFlowLogMoudle().getFlowLogOrderBy(SellOrderpane.this.starttime, SellOrderpane.this.endtime, 0, 23);

          SellOrderpane.this.pageData(list);
        }
      }

      public void pagePrev(int pagenum)
      {
        if ((SellOrderpane.this.starttime != null) && (SellOrderpane.this.endtime != null)) {
          List list = MoudleContentFactry.getFlowLogMoudle().getFlowLogOrderBy(SellOrderpane.this.starttime, SellOrderpane.this.endtime, pagenum, 23);

          SellOrderpane.this.pageData(list);
        }
      }

      public void pageNext(int pagenum) {
        if ((SellOrderpane.this.starttime != null) && (SellOrderpane.this.endtime != null)) {
          List list = MoudleContentFactry.getFlowLogMoudle().getFlowLogOrderBy(SellOrderpane.this.starttime, SellOrderpane.this.endtime, pagenum, 23);

          SellOrderpane.this.pageData(list);
        }
      }

      public void pageLast(int pagenum) {
        if ((SellOrderpane.this.starttime != null) && (SellOrderpane.this.endtime != null)) {
          List list = MoudleContentFactry.getFlowLogMoudle().getFlowLogOrderBy(SellOrderpane.this.starttime, SellOrderpane.this.endtime, pagenum, 23);

          SellOrderpane.this.pageData(list);
        }
      }

      public void itemStateChanged(int pagenum) {
        if ((SellOrderpane.this.starttime != null) && (SellOrderpane.this.endtime != null)) {
          List list = MoudleContentFactry.getFlowLogMoudle().getFlowLogOrderBy(SellOrderpane.this.starttime, SellOrderpane.this.endtime, pagenum, 23);

          SellOrderpane.this.pageData(list);
        }
      }

      public void export(MouseEvent e)
      {
        try {
          FlowLogMoudle flowLogMoudle = MoudleContentFactry.getFlowLogMoudle();
          List list = flowLogMoudle.getFlowLogOrderBy(SellOrderpane.this.starttime, SellOrderpane.this.endtime);
          if ((list != null) && (list.size() > 0)) {
            JFileChooser jfc = new JFileChooser("d:/");
            jfc.setFileSelectionMode(2);
            File fileff = new File("销售排行.csv");
            jfc.setSelectedFile(fileff);
            int result = jfc.showSaveDialog(SellOrderpane.this.table);
            if (result == 1) return;
            File savedFile = jfc.getSelectedFile();
            if (savedFile.exists()) {
              int overwriteSelect = JOptionPane.showConfirmDialog(SellOrderpane.this.table, "<html><font size=3>文件" + savedFile.getName() + "已存在，是否覆盖?</font><html>", "是否覆盖?", 
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
            stringBuffer.append("序号");
            stringBuffer.append(",");
            stringBuffer.append("货号");
            stringBuffer.append(",");
            stringBuffer.append("销售总数量");
            stringBuffer.append(",");
            stringBuffer.append("销售总利润");
            stringBuffer.append(",");
            stringBuffer.append("类型");
            stringBuffer.append(",");
            stringBuffer.append("名称");

            stringBuffer.append("\r\n");
            output.write(String.valueOf(stringBuffer));
            for (int i = 0; i < list.size(); i++) {
              Flowlog flowLog = (Flowlog)list.get(i);
              StringBuilder sb = new StringBuilder(128);
              sb.append(i + 1);
              sb.append(",");
              sb.append(flowLog.getCatno());
              sb.append(",");
              sb.append(flowLog.getAmount());
              sb.append(",");
              sb.append(flowLog.getLrprice());
              sb.append(",");
              sb.append(flowLog.getStockname());
              sb.append(",");
              sb.append(flowLog.getType());

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
    gbc_page.fill = 1;
    gbc_page.gridx = 0;
    gbc_page.gridy = 1;
    panel1.add(this.page, gbc_page);
  }

  public static SellOrderpane getInstance()
  {
    return sellOrderpane;
  }

  private void submmit() {
    this.starttime = this.datePicker.getEditor().getText();
    this.endtime = this.datePicker_1.getEditor().getText();
    this.msg.setText("");
    if ((this.starttime == null) || (this.starttime.trim().length() == 0)) {
      this.msg.setText("请输入开始时间");
      return;
    }
    if ((this.endtime == null) || (this.endtime.trim().length() == 0)) {
      this.msg.setText("请输入结束时间");
      return;
    }
    long s = Long.parseLong(this.starttime.replaceAll("-", ""));
    long e = Long.parseLong(this.endtime.replaceAll("-", ""));

    if (s > e) {
      this.msg.setText("输入时间范围错错误！开始时间应该小于结束时间");
    }
    int toltal = MoudleContentFactry.getFlowLogMoudle().getFlowLogOrderSize(this.starttime, this.endtime);
    List list = MoudleContentFactry.getFlowLogMoudle().getFlowLogOrderBy(this.starttime, this.endtime, 0, 23);
    this.page.setPageInfo(toltal);
    pageData(list);
  }

  private void pageData(List list)
  {
    if ((list != null) && (list.size() > 0)) {
      clear();

      for (int i = 0; i < list.size(); i++) {
        Flowlog flowLog = (Flowlog)list.get(i);

        Object[] rowData = { flowLog.getCatno(), Double.valueOf(flowLog.getAmount()), flowLog.getLrprice(), flowLog.getType(), flowLog.getStockname() };
        this.defaultTableModel.insertRow(i, rowData);
      }
    } else {
      this.msg.setText("没有销售排行，可能你没有出售货物！");
      clear();
    }
  }

  public void clear()
  {
    if (this.defaultTableModel.getRowCount() > 0) {
      int rows = this.defaultTableModel.getRowCount();
      for (int i = 0; i < rows; i++)
        this.defaultTableModel.removeRow(0);
    }
  }

  public void disposePage()
  {
  }

  public String getPageId() {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>销售排行";
  }

  public boolean isDefaultPage()
  {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool) {
    this.isDefaultPage = bool;
  }
}