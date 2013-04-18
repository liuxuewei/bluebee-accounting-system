package com.bluebee.ui.frame;

import com.bluebee.action.PageAction;
import com.bluebee.moudle.CustomMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Custom;
import com.bluebee.ui.widget.CustomDialog;
import com.bluebee.ui.widget.Page;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CustomPanel extends JPanel
  implements IPage
{
  private static CustomPanel customPanel = new CustomPanel();

  private boolean isDefaultPage = false;
  private JTextField textField;
  private JTable table;
  private Page cpage;

  public CustomPanel()
  {
    setLayout(new BorderLayout(0, 0));
    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    add(panel, "North");
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[4];
    gbl_panel.rowHeights = new int[2];
    gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel.rowWeights = new double[] { 0.0D, 4.9E-324D };
    panel.setLayout(gbl_panel);

    JButton button = new JButton("增加");
    button.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent arg0) {
        CustomDialog customDialog = new CustomDialog(CustomPanel.customPanel, "增加客户资料", null, CustomPanel.this.table);
        customDialog.setVisible(true);
      }
    });
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.insets = new Insets(0, 0, 0, 5);
    gbc_button.gridx = 0;
    gbc_button.gridy = 0;
    panel.add(button, gbc_button);

    JButton button_1 = new JButton("修改");
    button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {
        int nRow = CustomPanel.this.table.getSelectedRow();
        if ((nRow > -1) && (CustomPanel.this.table.getRowCount() > 0)) {
          String id = String.valueOf(CustomPanel.this.table.getValueAt(nRow, 0));
          CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
          Custom custom = customMoudle.getCustomById(id);
          CustomDialog customDialog = new CustomDialog(CustomPanel.customPanel, "修改客户资料", custom, CustomPanel.this.table);
          customDialog.setVisible(true);
        }
      }
    });
    GridBagConstraints gbc_button_1 = new GridBagConstraints();
    gbc_button_1.insets = new Insets(0, 0, 0, 5);
    gbc_button_1.gridx = 1;
    gbc_button_1.gridy = 0;
    panel.add(button_1, gbc_button_1);

    JButton button_2 = new JButton("删除");
    button_2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        int nRow = CustomPanel.this.table.getSelectedRow();
        int rowCount = CustomPanel.this.table.getRowCount();

        if ((nRow > -1) && (rowCount > 0)) {
          int response = JOptionPane.showConfirmDialog(null,"确定删除此条数据?", 
        		  "删除库存数据", 0);
          switch (response) {
          case 0:
            String id = String.valueOf(CustomPanel.this.table.getValueAt(nRow, 0));
            DefaultTableModel tableModel = (DefaultTableModel)CustomPanel.this.table.getModel();
            tableModel.removeRow(nRow);
            int newrowCount = CustomPanel.this.table.getRowCount();
            CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
            customMoudle.delete(id);
            CustomPanel.this.table.setSelectionMode(0);
            if (newrowCount == 1)
              CustomPanel.this.table.getSelectionModel().setSelectionInterval(0, 0);
            else
              CustomPanel.this.table.getSelectionModel().setSelectionInterval(nRow - 1, nRow - 1);
            break;
          case -1:
          case 1:
          }
        }
      }
    });
    GridBagConstraints gbc_button_2 = new GridBagConstraints();
    gbc_button_2.gridx = 2;
    gbc_button_2.gridy = 0;
    panel.add(button_2, gbc_button_2);

    JPanel panel_1 = new JPanel();
    add(panel_1, "Center");
    panel_1.setLayout(new BorderLayout(0, 0));

    JPanel panel_2 = new JPanel();
    panel_2.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    panel_1.add(panel_2, "North");
    GridBagLayout gbl_panel_2 = new GridBagLayout();
    gbl_panel_2.columnWidths = new int[] { 0, 0, 188 };
    gbl_panel_2.rowHeights = new int[2];
    gbl_panel_2.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_2.rowWeights = new double[] { 0.0D, 4.9E-324D };
    panel_2.setLayout(gbl_panel_2);

    JLabel label = new JLabel("客户号/姓名/电话");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 0, 5);
    gbc_label.anchor = 13;
    gbc_label.gridx = 1;
    gbc_label.gridy = 0;
    panel_2.add(label, gbc_label);

    this.textField = new JTextField();
    this.textField.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10)
          CustomPanel.this.submmit();
      }
    });
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.insets = new Insets(0, 0, 0, 5);
    gbc_textField.fill = 2;
    gbc_textField.gridx = 2;
    gbc_textField.gridy = 0;
    panel_2.add(this.textField, gbc_textField);
    this.textField.setColumns(10);

    JButton button_3 = new JButton("查询");
    button_3.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {
        CustomPanel.this.submmit();
      }
    });
    GridBagConstraints gbc_button_3 = new GridBagConstraints();
    gbc_button_3.gridx = 3;
    gbc_button_3.gridy = 0;
    panel_2.add(button_3, gbc_button_3);

    JPanel panel_3 = new JPanel();
    panel_3.setBorder(new TitledBorder(null, "客户资料列表", 4, 2, null, null));
    panel_1.add(panel_3, "Center");
    panel_3.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    panel_3.add(scrollPane, "Center");

    this.table = new JTable();
    this.table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int clicked = e.getClickCount();
        int nRow = CustomPanel.this.table.getSelectedRow();
        String id = String.valueOf(CustomPanel.this.table.getValueAt(nRow, 0));
        if (clicked == 2) {
          CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
          Custom custom = customMoudle.getCustomById(id);
          CustomDialog customDialog = new CustomDialog(CustomPanel.customPanel, "修改客户资料", custom, CustomPanel.this.table);
          customDialog.setVisible(true);
        }
      }
    });
    this.table.setModel(new DefaultTableModel(
      null, 
      new String[] { 
    		  "客户号", "姓名", "级别", "积分", "消费金额", "消费次数", "性别", "生日", "电话", "联系地址", "备注" })
    {
      Class[] columnTypes = { 
        String.class, String.class, String.class, Object.class, Object.class, Integer.class, String.class, String.class, String.class, Object.class, Object.class };

      boolean[] columnEditables = new boolean[11];

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table.setSelectionMode(0);
    this.table.getSelectionModel().setSelectionInterval(0, 0);
    this.table.getTableHeader().setReorderingAllowed(false);

    scrollPane.setViewportView(this.table);

    this.cpage = new Page(new PageAction() {
      public void pageFirst() {
        List list = null;
        CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
        String cu = CustomPanel.this.textField.getText();
        if (cu.trim().length() > 0) {
          list = customMoudle.getCustom(cu, cu, cu);
        }
        else {
          list = customMoudle.getCustoms(0, 20);
        }
        CustomPanel.this.pageData(list);
      }

      public void pagePrev(int pagenum) {
        List list = null;
        CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
        String cu = CustomPanel.this.textField.getText();
        if (cu.trim().length() > 0) {
          list = customMoudle.getCustom(cu, cu, cu);
        }
        else {
          list = customMoudle.getCustoms(pagenum, 20);
        }
        CustomPanel.this.pageData(list);
      }

      public void pageNext(int pagenum) {
        List list = null;
        CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
        String cu = CustomPanel.this.textField.getText();
        if (cu.trim().length() > 0) {
          list = customMoudle.getCustom(cu, cu, cu);
        }
        else {
          list = customMoudle.getCustoms(pagenum, 20);
        }
        CustomPanel.this.pageData(list);
      }

      public void pageLast(int pagenum) {
        List list = null;
        CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
        String cu = CustomPanel.this.textField.getText();
        if (cu.trim().length() > 0) {
          list = customMoudle.getCustom(cu, cu, cu);
        }
        else {
          list = customMoudle.getCustoms(pagenum, 20);
        }
        CustomPanel.this.pageData(list);
      }

      public void itemStateChanged(int pagenum) {
        List list = null;
        CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
        String cu = CustomPanel.this.textField.getText();
        if (cu.trim().length() > 0)
          list = customMoudle.getCustom(cu, cu, cu);
        else {
          list = customMoudle.getCustoms(pagenum, 20);
        }
        CustomPanel.this.pageData(list);
      }

      public void export(MouseEvent e)
      {
        try {
          CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
          List list = customMoudle.getCustoms();
          if ((list != null) && (list.size() > 0)) {
            JFileChooser jfc = new JFileChooser("d:/");
            jfc.setFileSelectionMode(2);
            File fileff = new File("客户资料.csv");
            jfc.setSelectedFile(fileff);
            int result = jfc.showSaveDialog(CustomPanel.customPanel);
            if (result == 1) return;
            File savedFile = jfc.getSelectedFile();
            if (savedFile.exists()) {
              int overwriteSelect = JOptionPane.showConfirmDialog(CustomPanel.customPanel,  "<html><font size=3>文件" + savedFile.getName() + "已存在，是否覆盖?</font><html>", "是否覆盖?", 
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
            stringBuffer.append("姓名");
            stringBuffer.append(",");
            stringBuffer.append("级别");
            stringBuffer.append(",");
            stringBuffer.append("积分");
            stringBuffer.append(",");
            stringBuffer.append("缴费金额");
            stringBuffer.append(",");
            stringBuffer.append("消费次数");
            stringBuffer.append(",");
            stringBuffer.append("性别");
            stringBuffer.append(",");
            stringBuffer.append("生日");
            stringBuffer.append(",");
            stringBuffer.append("电话");
            stringBuffer.append(",");
            stringBuffer.append("联系地址");
            stringBuffer.append(",");
            stringBuffer.append("备注");
            stringBuffer.append("\r\n");
            output.write(String.valueOf(stringBuffer));
            for (int i = 0; i < list.size(); i++) {
              Custom custom = (Custom)list.get(i);
              StringBuilder sb = new StringBuilder(128);
              sb.append(custom.getId());
              sb.append(",");
              sb.append(custom.getName());
              sb.append(",");
              sb.append(custom.getType());
              sb.append(",");
              sb.append(custom.getType());
              sb.append(",");
              sb.append(custom.getAmount());
              sb.append(",");
              sb.append(custom.getFrequency());
              sb.append(",");
              sb.append(custom.getSex());
              sb.append(",");
              sb.append(custom.getBirthday());
              sb.append(",");
              sb.append(custom.getTelephone());
              sb.append(",");
              sb.append(custom.getAddress());
              sb.append(",");
              sb.append(custom.getNote());
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
    add(this.cpage, "South");
  }

  public static CustomPanel getInstance()
  {
    if (customPanel != null) {
      customPanel.initdata();
    }
    return customPanel;
  }

  private void initdata() {
    CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
    int totalrow = customMoudle.getCustomsSize();
    List list = customMoudle.getCustoms(0, 20);
    pageData(list);
    this.cpage.setPageInfo(totalrow);
  }

  private void submmit() {
    String cu = this.textField.getText();
    CustomMoudle customMoudle = MoudleContentFactry.getCustomMoudle();
    if (cu.trim().length() > 0) {
      List list = customMoudle.getCustom(cu, cu, cu);
      pageData(list);
      this.cpage.setPageInfo(list.size());
    } else {
      int rt = customMoudle.getCustomsSize();
      List list = customMoudle.getCustoms(0, 20);
      pageData(list);
      this.cpage.setPageInfo(rt);
    }
  }

  private void pageData(List list) {
    if ((list != null) && (list.size() > 0)) {
      clear();
      DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
      for (int i = 0; i < list.size(); i++) {
        Custom custom = (Custom)list.get(i);

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

        tableModel.insertRow(0, rowData);
      }
      this.table.getSelectionModel().setSelectionInterval(0, 0);
    }
  }

  public void clear() {
    DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
    if (tableModel.getRowCount() > 0) {
      int rows = tableModel.getRowCount();
      for (int i = 0; i < rows; i++)
        tableModel.removeRow(0);
    }
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>客户资料";
  }

  public void disposePage() {
  }

  public boolean isDefaultPage() {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool) {
    this.isDefaultPage = bool;
  }
}