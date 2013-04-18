package com.bluebee.ui.widget;

import com.bluebee.moudle.CustomtTypeMoudle;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.CustomType;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CustomTypeDialog extends JDialog
{
  private JTable table;
  private JComboBox jcomboBox;

  public CustomTypeDialog(Component owner, String tilte, JComboBox comboBox)
  {
    setResizable(false);
    setTitle(tilte);
    setSize(new Dimension(387, 310));
    setLocationRelativeTo(owner);

    setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    setDefaultCloseOperation(2);
    this.jcomboBox = comboBox;
    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "客户级别设置", 4, 2, null, null));
    getContentPane().add(panel, "Center");
    panel.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    panel.add(scrollPane, "Center");

    this.table = new JTable();
    this.table.setModel(new DefaultTableModel(
      new Object[][] { 
      new Object[3] }, 
      new String[] { 
      "级别", "积分", "享受折扣" })
    {
      Class[] columnTypes = { 
        String.class, Double.class, Double.class };

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }
    });
    this.table.getColumnModel().getColumn(0).setPreferredWidth(108);
    this.table.getColumnModel().getColumn(0).setMaxWidth(108);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(88);
    this.table.setSelectionMode(0);
    this.table.getSelectionModel().setSelectionInterval(0, 0);
    scrollPane.setViewportView(this.table);
    JPanel pane2 = new JPanel();
    panel.add(pane2, "South");
    GridBagLayout gbl_pane2 = new GridBagLayout();
    gbl_pane2.columnWidths = new int[] { 28, 54, 66 };
    gbl_pane2.rowHeights = new int[] { 23 };
    gbl_pane2.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_pane2.rowWeights = new double[] { 0.0D, 4.9E-324D };
    pane2.setLayout(gbl_pane2);

    JButton button_2 = new JButton("删除");
    button_2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {
        DefaultTableModel tableModel = (DefaultTableModel)CustomTypeDialog.this.table.getModel();
        int r = CustomTypeDialog.this.table.getSelectedRow();
        if (r > -1) {
          String name = String.valueOf(tableModel.getValueAt(r, 0));
          if ((name != null) && (!"".equals(name))) {
            CustomtTypeMoudle typeMoudle = MoudleContentFactry.getCustomtTypeMoudle();
            typeMoudle.delete(name);
            tableModel.removeRow(r);
            CustomTypeDialog.this.jcomboBox.setModel(new DefaultComboBoxModel(SelectType.getCustomType()));
          }
        }
        CustomTypeDialog.this.table.setSelectionMode(0);
        CustomTypeDialog.this.table.getSelectionModel().setSelectionInterval(0, 0);
      }
    });
    JButton button_1 = new JButton("保存");
    button_1.requestFocus();
    button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {
        DefaultTableModel tableModel = (DefaultTableModel)CustomTypeDialog.this.table.getModel();
        Object[] rowdata = (Object[])null;
        tableModel.addRow(rowdata);
        int r = tableModel.getRowCount();
        if (r > -1) {
          CustomTypeDialog.this.table.requestFocusInWindow();
          CustomTypeDialog.this.table.editCellAt(r - 1, 0, null);

          CustomTypeDialog.this.table.setColumnSelectionInterval(0, 0);
          CustomTypeDialog.this.table.setRowSelectionInterval(r - 1, r - 1);

          CustomtTypeMoudle typeMoudle = MoudleContentFactry.getCustomtTypeMoudle();
          CustomType[] customTypes = CustomTypeDialog.this.getCustomType();
          if (customTypes != null) {
            typeMoudle.addCustomTypes(customTypes);
            CustomTypeDialog.this.jcomboBox.setModel(new DefaultComboBoxModel(SelectType.getCustomType()));
          }
        }
      }
    });
    JButton button = new JButton("增加");
    button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {
        DefaultTableModel tableModel = (DefaultTableModel)CustomTypeDialog.this.table.getModel();
        int r = tableModel.getRowCount();
        if (r > -1) {
          boolean ist = false;
          for (int i = 0; i < r; i++) {
            Object ob1 = tableModel.getValueAt(i, 0);
            Object ob2 = tableModel.getValueAt(i, 1);
            Object ob3 = tableModel.getValueAt(i, 2);
            if (((ob1 == null) && (ob2 == null) && (ob3 == null)) || 
              ("".equals(String.valueOf(ob1).trim()))) {
              CustomTypeDialog.this.table.requestFocusInWindow();
              CustomTypeDialog.this.table.editCellAt(i, 0, null);
              CustomTypeDialog.this.table.setColumnSelectionInterval(0, 0);
              CustomTypeDialog.this.table.setRowSelectionInterval(i, i);
              ist = true;
              break;
            }
          }
          if (!ist) {
            Object[] rowdata = (Object[])null;
            tableModel.addRow(rowdata);
          }
        } else {
          Object[] rowdata = (Object[])null;
          tableModel.addRow(rowdata);
        }
      }
    });
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.insets = new Insets(0, 0, 0, 5);
    gbc_button.anchor = 18;
    gbc_button.gridx = 2;
    gbc_button.gridy = 0;
    pane2.add(button, gbc_button);
    GridBagConstraints gbc_button_1 = new GridBagConstraints();
    gbc_button_1.insets = new Insets(0, 0, 0, 5);
    gbc_button_1.gridx = 3;
    gbc_button_1.gridy = 0;
    pane2.add(button_1, gbc_button_1);
    GridBagConstraints gbc_button_2 = new GridBagConstraints();
    gbc_button_2.gridx = 4;
    gbc_button_2.gridy = 0;
    pane2.add(button_2, gbc_button_2);
    init();
  }

  private void init() {
    CustomtTypeMoudle typeMoudle = MoudleContentFactry.getCustomtTypeMoudle();
    List list = typeMoudle.getCustomtTypes();
    DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
    for (int i = 0; i < list.size(); i++) {
      CustomType dd = (CustomType)list.get(i);
      Object[] objects = { 
        dd.getTypename(), 
        Double.valueOf(dd.getIntegration()), 
        Double.valueOf(dd.getDiscount()) };

      tableModel.insertRow(0, objects);
    }
  }

  private CustomType[] getCustomType() {
    DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
    int r = tableModel.getRowCount();
    if (r > 0) {
      CustomType[] customTypes = new CustomType[r];
      for (int i = 0; i < r; i++) {
        String name = String.valueOf(tableModel.getValueAt(i, 0));
        if ((name != null) && (!"".equals(name.trim())) && (!"null".equals(name))) {
          double integration = 0.0D;
          if (tableModel.getValueAt(i, 1) != null) {
            integration = ((Double)tableModel.getValueAt(i, 1)).doubleValue();
          }
          double discount = 10.0D;
          if (tableModel.getValueAt(i, 2) != null) {
            discount = ((Double)tableModel.getValueAt(i, 2)).doubleValue();
          }

          CustomType customType = new CustomType();
          customType.setTypename(name);
          customType.setIntegration(integration);
          customType.setDiscount(discount);
          customTypes[i] = customType;
        }
      }
      return customTypes;
    }
    return null;
  }
}