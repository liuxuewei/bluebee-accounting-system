package com.bluebee.ui.frame;

import com.bluebee.action.PageAction;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.SuppliersMoudle;
import com.bluebee.pojo.Suppliers;
import com.bluebee.ui.widget.Page;
import com.bluebee.ui.widget.SuppliersDialog;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SuppliersPanel extends JPanel
  implements IPage
{
  private static SuppliersPanel suppliersPanel = new SuppliersPanel();

  private boolean isDefaultPage = false;
  private JTable table;
  private JTextField textField;
  private Page page;
  private JLabel label_1;

  public static SuppliersPanel getInstance()
  {
    if (suppliersPanel != null) {
      suppliersPanel.initdata();
    }
    return suppliersPanel;
  }

  private SuppliersPanel() {
    setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    add(panel, "North");
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[] { 0, 0, 14, 0, 15, 304 };
    gbl_panel.rowHeights = new int[2];
    gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel.rowWeights = new double[] { 0.0D, 4.9E-324D };
    panel.setLayout(gbl_panel);

    JButton button = new JButton("增加");
    button.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        SuppliersDialog suppliersDialog = new SuppliersDialog(SuppliersPanel.suppliersPanel, "增加供应商资料", null, SuppliersPanel.this.table);
        suppliersDialog.setVisible(true);
      }
    });
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.insets = new Insets(0, 0, 0, 5);
    gbc_button.gridx = 1;
    gbc_button.gridy = 0;
    panel.add(button, gbc_button);

    JButton button_1 = new JButton("修改");
    button_1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int nRow = SuppliersPanel.this.table.getSelectedRow();
        if ((nRow > -1) && (SuppliersPanel.this.table.getRowCount() > 0)) {
          String id = String.valueOf(SuppliersPanel.this.table.getValueAt(nRow, 0));
          SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();
          Suppliers suppliers = suppliersMoudle.getSuppliersByNo(id);
          SuppliersDialog suppliersDialog = new SuppliersDialog(SuppliersPanel.suppliersPanel, "修改供应商资料", suppliers, SuppliersPanel.this.table);
          suppliersDialog.setVisible(true);
        }
      }
    });
    GridBagConstraints gbc_button_1 = new GridBagConstraints();
    gbc_button_1.insets = new Insets(0, 0, 0, 5);
    gbc_button_1.gridx = 2;
    gbc_button_1.gridy = 0;
    panel.add(button_1, gbc_button_1);

    JButton button_2 = new JButton("删除");
    button_2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        int nRow = SuppliersPanel.this.table.getSelectedRow();
        int rowCount = SuppliersPanel.this.table.getRowCount();

        if ((nRow > -1) && (rowCount > 0)) {
          int response = JOptionPane.showConfirmDialog(null, "确定删除此条数据?", 
            "删除供应商数据", 0);
          switch (response) {
          case 0:
            String id = String.valueOf(SuppliersPanel.this.table.getValueAt(nRow, 0));
            DefaultTableModel tableModel = (DefaultTableModel)SuppliersPanel.this.table.getModel();
            tableModel.removeRow(nRow);
            int newrowCount = SuppliersPanel.this.table.getRowCount();
            SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();
            suppliersMoudle.deleteSuppliers(id);
            SuppliersPanel.this.table.setSelectionMode(0);
            if (newrowCount == 1)
              SuppliersPanel.this.table.getSelectionModel().setSelectionInterval(0, 0);
            else
              SuppliersPanel.this.table.getSelectionModel().setSelectionInterval(nRow - 1, nRow - 1);
            break;
          case -1:
          case 1:
          }
        }
      }
    });
    GridBagConstraints gbc_button_2 = new GridBagConstraints();
    gbc_button_2.insets = new Insets(0, 0, 0, 5);
    gbc_button_2.gridx = 3;
    gbc_button_2.gridy = 0;
    panel.add(button_2, gbc_button_2);

    this.label_1 = new JLabel("");
    this.label_1.setBackground(Color.RED);
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.anchor = 17;
    gbc_label_1.gridwidth = 2;
    gbc_label_1.insets = new Insets(0, 0, 0, 5);
    gbc_label_1.gridx = 4;
    gbc_label_1.gridy = 0;
    panel.add(this.label_1, gbc_label_1);

    this.page = new Page(new PageAction() {
      public void pageFirst() {
        SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();

        List list = suppliersMoudle.getSuppliers(0, 20);
        SuppliersPanel.this.pageData(list);
      }

      public void pagePrev(int pagenum)
      {
        SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();

        List list = suppliersMoudle.getSuppliers(pagenum, 20);
        SuppliersPanel.this.pageData(list);
      }

      public void pageNext(int pagenum) {
        SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();

        List list = suppliersMoudle.getSuppliers(pagenum, 20);
        SuppliersPanel.this.pageData(list);
      }

      public void pageLast(int pagenum) {
        SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();

        List list = suppliersMoudle.getSuppliers(pagenum, 20);
        SuppliersPanel.this.pageData(list);
      }

      public void itemStateChanged(int pagenum) {
        String v = SuppliersPanel.this.textField.getText();
        SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();
        if (!"".equals(v)) {
          List list = suppliersMoudle.getSuppliers(v, v, v, 0, 20);
          SuppliersPanel.this.pageData(list);
        } else {
          List list = null;
          int totalrow = suppliersMoudle.getSuppliersSize();
          list = suppliersMoudle.getSuppliers(pagenum, 20);
          SuppliersPanel.this.pageData(list);
        }
      }

      public void export(MouseEvent e)
      {
        try {
          SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();
          List list = suppliersMoudle.getSuppliers();
          if ((list != null) && (list.size() > 0)) {
            JFileChooser jfc = new JFileChooser("d:/");
            jfc.setFileSelectionMode(2);
            File fileff = new File("供应商资料.csv");
            jfc.setSelectedFile(fileff);
            int result = jfc.showSaveDialog(SuppliersPanel.suppliersPanel);
            if (result == 1) return;
            File savedFile = jfc.getSelectedFile();
            if (savedFile.exists()) {
              int overwriteSelect = JOptionPane.showConfirmDialog(SuppliersPanel.suppliersPanel, "<html><font size=3>文件" + savedFile.getName() + "已存在，是否覆盖?</font><html>", "是否覆盖?", 
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
            stringBuffer.append("供应商名称");
            stringBuffer.append(",");
            stringBuffer.append("地址");
            stringBuffer.append(",");
            stringBuffer.append("联系人");
            stringBuffer.append(",");
            stringBuffer.append("联系电话");
            stringBuffer.append(",");
            stringBuffer.append("QQ");
            stringBuffer.append(",");
            stringBuffer.append("传真");
            stringBuffer.append(",");
            stringBuffer.append("邮箱");
            stringBuffer.append(",");
            stringBuffer.append("邮政编码");
            stringBuffer.append(",");
            stringBuffer.append("备注");
            stringBuffer.append("\r\n");
            output.write(String.valueOf(stringBuffer));
            for (int i = 0; i < list.size(); i++) {
              Suppliers suppliers = (Suppliers)list.get(i);
              StringBuilder sb = new StringBuilder(128);
              sb.append(suppliers.getSuppliersName());
              sb.append(",");
              sb.append(suppliers.getAddress());
              sb.append(",");
              sb.append(suppliers.getContact());
              sb.append(",");
              sb.append(suppliers.getPhone());
              sb.append(",");
              sb.append(suppliers.getQq());
              sb.append(",");
              sb.append(suppliers.getFax());
              sb.append(",");
              sb.append(suppliers.getEmail());
              sb.append(",");
              sb.append(suppliers.getZipcode());
              sb.append(",");
              sb.append(suppliers.getRemarks());

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

    JPanel panel_1 = new JPanel();
    add(panel_1, "Center");
    panel_1.setLayout(new BorderLayout(0, 0));

    JPanel panel_2 = new JPanel();
    panel_2.setBorder(new TitledBorder(null, "", 4, 2, null, null));
    panel_1.add(panel_2, "North");
    GridBagLayout gbl_panel_2 = new GridBagLayout();
    gbl_panel_2.columnWidths = new int[] { 0, 0, 233 };
    gbl_panel_2.rowHeights = new int[2];
    gbl_panel_2.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_2.rowWeights = new double[] { 0.0D, 4.9E-324D };
    panel_2.setLayout(gbl_panel_2);

    JLabel label = new JLabel("名称/电话/联系人");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 0, 5);
    gbc_label.anchor = 13;
    gbc_label.gridx = 1;
    gbc_label.gridy = 0;
    panel_2.add(label, gbc_label);

    this.textField = new JTextField();
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
      public void mouseClicked(MouseEvent e) {
        SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();
        String v = SuppliersPanel.this.textField.getText();

        List list = suppliersMoudle.getSuppliers(v, v, v, 0, 20);
        int totalrow = suppliersMoudle.getSuppliersSize(v, v, v);
        SuppliersPanel.this.pageData(list);
        SuppliersPanel.this.page.setPageInfo(totalrow);
      }
    });
    GridBagConstraints gbc_button_3 = new GridBagConstraints();
    gbc_button_3.insets = new Insets(0, 0, 0, 5);
    gbc_button_3.gridx = 3;
    gbc_button_3.gridy = 0;
    panel_2.add(button_3, gbc_button_3);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBorder(new TitledBorder(null, "供应商列表", 4, 2, null, null));
    panel_1.add(scrollPane, "Center");

    this.table = new JTable();
    this.table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int clicked = e.getClickCount();
        int nRow = SuppliersPanel.this.table.getSelectedRow();
        String id = String.valueOf(SuppliersPanel.this.table.getValueAt(nRow, 0));
        if (clicked == 2) {
          SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();
          Suppliers suppliers = suppliersMoudle.getSuppliersByNo(id);
          SuppliersDialog suppliersDialog = new SuppliersDialog(SuppliersPanel.suppliersPanel, "修改供应商资料", suppliers, SuppliersPanel.this.table);
          suppliersDialog.setVisible(true);
        }
      }
    });
    this.table.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "no", "供应商名称", "地址", "联系人", "联系电话", "QQ", "传真", "邮箱", "邮政编码", "备注" })
    {
      boolean[] columnEditables = new boolean[10];

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table.getColumnModel().getColumn(0).setResizable(false);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(0).setMinWidth(0);
    this.table.getColumnModel().getColumn(0).setMaxWidth(0);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(329);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(224);
    this.table.getColumnModel().getColumn(2).setMinWidth(50);
    this.table.setSelectionMode(0);
    this.table.getSelectionModel().setSelectionInterval(0, 0);
    scrollPane.setViewportView(this.table);
  }

  private void initdata()
  {
    SuppliersMoudle suppliersMoudle = MoudleContentFactry.getSuppliersMoudle();
    int totalrow = suppliersMoudle.getSuppliersSize();
    List list = suppliersMoudle.getSuppliers(0, 20);
    pageData(list);
    this.page.setPageInfo(totalrow);
  }

  private void pageData(List list) {
    if ((list != null) && (list.size() > 0)) {
      clear();
      DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
      for (int i = 0; i < list.size(); i++) {
        Suppliers suppliers = (Suppliers)list.get(i);
        Object[] rowData = { 
          suppliers.getSuppliersno(), 
          suppliers.getSuppliersName(), 
          suppliers.getAddress(), 
          suppliers.getContact(), 
          suppliers.getPhone(), 
          suppliers.getQq(), 
          suppliers.getFax(), 
          suppliers.getEmail(), 
          suppliers.getZipcode(), 
          suppliers.getRemarks() };

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
    return ">>>供应商资料";
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