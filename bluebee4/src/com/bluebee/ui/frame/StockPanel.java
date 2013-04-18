package com.bluebee.ui.frame;

import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.pojo.Stock;
import com.bluebee.ui.widget.StockDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class StockPanel extends JPanel
  implements IPage
{
  private static StockPanel sellHelperPanel = new StockPanel();

  private boolean isDefaultPage = false;
  private JPanel panel1;
  private JPanel panel;
  private JLabel label;
  private JLabel label_1;
  private JLabel label_2;
  private JLabel label_3;
  private JLabel label_6;
  private JLabel label_8;
  private JPanel panel_1;
  private JLabel label_4;
  private JScrollPane scrollPane;
  private JTable table;
  private JButton button;
  private JButton button_1;

  private StockPanel()
  {
    setLayout(new BorderLayout());

    initComponents();
    initTadayFow();
  }

  private void initComponents()
  {
    this.panel1 = new JPanel();
    this.panel1.setPreferredSize(new Dimension(10, 60));

    setLayout(new BorderLayout());

    this.panel1.setBorder(new CompoundBorder(new TitledBorder(
      "录入进货数据"), new EmptyBorder(5, 
      5, 5, 5)));
    this.panel1.setLayout(null);

    add(this.panel1, "North");

    this.button = new JButton("增加");
    this.button.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        StockDialog stockDialog = new StockDialog(StockPanel.sellHelperPanel, "增加货物", null, StockPanel.this.table, new StockDialog.CallBack() {
          public void updateView() {
            StockPanel.this.deltail();
          }
        });
        stockDialog.setVisible(true);
      }
    });
    this.button.setBounds(22, 27, 69, 23);
    this.panel1.add(this.button);

    this.button_1 = new JButton("修改");
    this.button_1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        int nRow = StockPanel.this.table.getSelectedRow();
        if ((nRow > -1) && (StockPanel.this.table.getRowCount() > 0)) {
          String id = String.valueOf(StockPanel.this.table.getValueAt(nRow, 0));
          StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
          Stock stockold = stockMoudle.getStockByID(id);
          StockDialog stockDialog = new StockDialog(StockPanel.sellHelperPanel, "修改货物信息", stockold, StockPanel.this.table, new StockDialog.CallBack() {
            public void updateView() {
              StockPanel.this.deltail();
            }
          });
          stockDialog.setVisible(true);
        } else if (StockPanel.this.table.getRowCount() > 0) {
          StockPanel.this.label_4.setText("请选中需要修改的行，或者鼠标双击修改行");
        }
      }
    });
    this.button_1.setBounds(113, 27, 69, 23);
    this.panel1.add(this.button_1);

    this.label_4 = new JLabel("");
    this.label_4.setForeground(Color.RED);
    this.label_4.setBounds(207, 31, 318, 15);
    this.panel1.add(this.label_4);

    this.panel_1 = new JPanel();
    this.panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "进货列表", 4, 2, null));
    add(this.panel_1, "Center");
    this.panel_1.setLayout(new BorderLayout(0, 0));

    this.scrollPane = new JScrollPane();
    this.panel_1.add(this.scrollPane, "Center");

    this.table = new JTable();

    this.table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int clicked = e.getClickCount();
        int nRow = StockPanel.this.table.getSelectedRow();
        String id = String.valueOf(StockPanel.this.table.getValueAt(nRow, 0));
        int nCol = StockPanel.this.table.getSelectedColumn();
        Object objSel = StockPanel.this.table.getValueAt(nRow, nCol);
        if (clicked == 2) {
          if ((objSel != null) && ((objSel instanceof String))) {
            if ("删除".equals(String.valueOf(objSel)))
              StockPanel.this.clickedTable();
          }
          else {
            StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
            Stock stockold = stockMoudle.getStockByID(id);
            StockDialog stockDialog = new StockDialog(StockPanel.sellHelperPanel, "修改货物信息", stockold, StockPanel.this.table, new StockDialog.CallBack() {
              public void updateView() {
                StockPanel.this.deltail();
              }
            });
            stockDialog.setVisible(true);
          }
        }
        else
          StockPanel.this.clickedTable();
      }
    });
    this.table.setModel(new DefaultTableModel(
      null, new String[] { 
      "id", "货号", "数量", "成本", 
      "总计", "售价", "类型", "名称", "规格", "颜色", "时间", "操作" })
    {
      boolean[] columnEditable = new boolean[12];

      Class[] columnTypes = { 
        String.class, String.class, Double.class, BigDecimal.class, BigDecimal.class, BigDecimal.class, String.class, String.class, 
        String.class, String.class, String.class, String.class };

      public boolean isCellEditable(int rowIndex, int columnIndex)
      {
        return this.columnEditable[columnIndex];
      }

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }
    });
    this.table.getColumnModel().getColumn(0).setResizable(false);
    this.table.setSelectionMode(0);

    this.table.getSelectionModel().setSelectionInterval(0, 0);
    TableColumn tcflow = this.table.getColumn("id");
    tcflow.setResizable(false);
    tcflow.setPreferredWidth(0);
    tcflow.setWidth(0);
    tcflow.setMinWidth(0);
    tcflow.setMaxWidth(0);
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(0);
    this.table.getTableHeader().setReorderingAllowed(false);
    TableColumn tc = this.table.getColumn("操作");
    tc.setPreferredWidth(60);
    tc.setCellRenderer(renderer);
    this.scrollPane.setViewportView(this.table);

    this.panel = new JPanel();
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 50, 0, 0, 0, 0, 0, 43, 17 };
    gridBagLayout.rowHeights = new int[2];
    gridBagLayout.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 
      0.0D, 0.0D, 0.0D, 0.0D, 0.0001D };
    gridBagLayout.rowWeights = new double[] { 0.0D, 0.0001D };
    this.panel.setLayout(gridBagLayout);
    add(this.panel, "South");

    this.label = new JLabel("数量总计：");
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 0, 0, 5);
    gbc.gridx = 1;
    gbc.gridy = 0;
    this.panel.add(this.label, gbc);

    this.label_1 = new JLabel("0");
    GridBagConstraints gbc_1 = new GridBagConstraints();
    gbc_1.insets = new Insets(0, 0, 0, 5);
    gbc_1.gridx = 2;
    gbc_1.gridy = 0;
    this.panel.add(this.label_1, gbc_1);

    this.label_2 = new JLabel("货款总计：");
    GridBagConstraints gbc_2 = new GridBagConstraints();
    gbc_2.insets = new Insets(0, 0, 0, 5);
    gbc_2.gridx = 4;
    gbc_2.gridy = 0;
    this.panel.add(this.label_2, gbc_2);

    this.label_3 = new JLabel("0");
    GridBagConstraints gbc_3 = new GridBagConstraints();
    gbc_3.insets = new Insets(0, 0, 0, 5);
    gbc_3.gridx = 5;
    gbc_3.gridy = 0;
    this.panel.add(this.label_3, gbc_3);

    this.label_6 = new JLabel("");
    this.label_6.setForeground(Color.RED);
    GridBagConstraints gbc_label_6 = new GridBagConstraints();
    gbc_label_6.insets = new Insets(0, 0, 0, 5);
    gbc_label_6.gridx = 7;
    gbc_label_6.gridy = 0;
    this.panel.add(this.label_6, gbc_label_6);

    this.label_8 = new JLabel("查询以往进货数据，可在库存统计中查看");
    GridBagConstraints gbc_label_8 = new GridBagConstraints();
    gbc_label_8.gridx = 8;
    gbc_label_8.gridy = 0;
    this.panel.add(this.label_8, gbc_label_8);
  }

  public static StockPanel getInstance()
  {
    return sellHelperPanel;
  }

  public String getPageId() {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>采购进货";
  }

  public void disposePage() {
  }

  public void clickedTable() {
    int nCol = this.table.getSelectedColumn();
    int nRow = this.table.getSelectedRow();
    Object objSel = this.table.getValueAt(nRow, nCol);
    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table.getModel();
    if ((objSel != null) && ((objSel instanceof String)) && 
      ("删除".equals(String.valueOf(objSel)))) {
      int response = JOptionPane.showConfirmDialog(null, "确定删除此条数据?", 
        "删除库存数据", 0);
      switch (response) {
      case 0:
        String id = String.valueOf(defaultTableModel.getValueAt(
          nRow, 0));

        StockMoudle stockMoudle = 
          MoudleContentFactry.getStockMoudle();

        stockMoudle.delete(id);
        defaultTableModel.removeRow(nRow);

        deltail();
      case -1:
      case 1:
      }
    }
  }

  private void initTadayFow()
  {
    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table.getModel();
    if (defaultTableModel.getRowCount() == 0) {
      StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
      List list = stockMoudle.getStockByTaday();
      if ((list != null) && (list.size() > 0)) {
        for (int i = 0; i < list.size(); i++) {
          Stock formdata = (Stock)list.get(i);
          Object[] rowData = { formdata.getId(), formdata.getCatno(), 
            Double.valueOf(formdata.getAmount()), formdata.getCostprice(), 
            formdata.getTotal(), formdata.getSellprice(), 
            formdata.getType(), formdata.getStockname(), formdata.getSpecif(), formdata.getColor(), formdata.getDate(), "删除" };

          defaultTableModel.insertRow(0, rowData);
        }
      }
      this.table.setSelectionMode(0);

      this.table.getSelectionModel().setSelectionInterval(0, 0);
      deltail();
    }
  }

  private void deltail()
  {
    BigDecimal lrtotalcost = new BigDecimal(0);
    double num = 0.0D;
    DefaultTableModel defaultTableModel = (DefaultTableModel)this.table.getModel();
    int rows = defaultTableModel.getRowCount();
    for (int i = 0; i < rows; i++)
    {
      double amounts = ((Double)defaultTableModel.getValueAt(i, 2)).doubleValue();
      num += amounts;

      BigDecimal lr = (BigDecimal)defaultTableModel.getValueAt(i, 4);
      lrtotalcost = lrtotalcost.add(lr);
    }
    this.label_1.setText(String.valueOf(num));
    this.label_3.setText(lrtotalcost.toString());
  }

  public boolean isDefaultPage() {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool) {
    this.isDefaultPage = bool;
  }
}