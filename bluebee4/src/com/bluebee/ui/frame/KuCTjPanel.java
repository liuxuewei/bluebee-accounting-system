package com.bluebee.ui.frame;

import com.bluebee.action.PageAction;
import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.pojo.Stock;
import com.bluebee.ui.LimitedDocument;
import com.bluebee.ui.widget.KuCunDialog;
import com.bluebee.ui.widget.Options;
import com.bluebee.ui.widget.Page;
import com.bluebee.ui.widget.SelectType;
import com.bluebee.ui.widget.SuggestTextField;

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
import javax.swing.BorderFactory;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXDatePicker;

public class KuCTjPanel extends JPanel
  implements IPage
{
  private static KuCTjPanel kuCTjPanel = new KuCTjPanel();

  private boolean isDefaultPage = false;
  private String dubString = "1234567890.";
  private JTable table;
  private Page page;
  private Page pageAlarm;
  private JTextField textField_1;
  private JComboBox comboBox;
  private JLabel label_4;
  private JXDatePicker datePicker;
  private SuggestTextField suggestTextField;
  private JLabel label_7;
  private String catno = null;
  private String cost = null;
  private String type = null;
  private String date = null;
  private String dateTo = null;
  private double alarmNum = 20.0D;
  private JXDatePicker datePicker_1;
  private JLabel label_11;
  private JLabel label_12;
  private JTable table_1;
  private JTextField stockAlarmNum;
  private JComboBox stockAlarmTypes;

  private KuCTjPanel()
  {
    setLayout(new BorderLayout(0, 0));

    JTabbedPane tabbedPane = new JTabbedPane(1);
    Border loweredetched = 
      BorderFactory.createEtchedBorder(1);
    tabbedPane.setBorder(loweredetched);
    add(tabbedPane, "Center");

    JPanel panel = new JPanel();
    ImageIcon imageIcon1 = new ImageIcon(KuCTjPanel.class.getResource("/com/bluebee/resource/image/office-chart-pie.png"));
    tabbedPane.addTab("库存查询", imageIcon1, panel, null);
    panel.setLayout(new BorderLayout(0, 0));

    JPanel panel_2 = new JPanel();
    panel.add(panel_2, "North");
    GridBagLayout gbl_panel_2 = new GridBagLayout();
    gbl_panel_2.columnWidths = new int[] { 33, 0, 105, 32, 40, 81 };
    gbl_panel_2.rowHeights = new int[] { 29 };
    gbl_panel_2.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 4.9E-324D };
    gbl_panel_2.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel_2.setLayout(gbl_panel_2);

    this.label_12 = new JLabel("");
    this.label_12.setForeground(Color.RED);
    GridBagConstraints gbc_label_12 = new GridBagConstraints();
    gbc_label_12.anchor = 17;
    gbc_label_12.gridwidth = 4;
    gbc_label_12.insets = new Insets(0, 0, 5, 5);
    gbc_label_12.gridx = 2;
    gbc_label_12.gridy = 0;
    panel_2.add(this.label_12, gbc_label_12);

    JLabel label_1 = new JLabel("货号");
    GridBagConstraints gbc_label_1 = new GridBagConstraints();
    gbc_label_1.anchor = 13;
    gbc_label_1.insets = new Insets(0, 0, 5, 5);
    gbc_label_1.gridx = 1;
    gbc_label_1.gridy = 1;
    panel_2.add(label_1, gbc_label_1);

    this.suggestTextField = new SuggestTextField(new StockSuggestDataImpl(), null, null, null);
    GridBagConstraints gbc_suggestTextField = new GridBagConstraints();
    gbc_suggestTextField.fill = 2;
    gbc_suggestTextField.insets = new Insets(0, 0, 5, 5);
    gbc_suggestTextField.gridx = 2;
    gbc_suggestTextField.gridy = 1;
    panel_2.add(this.suggestTextField, gbc_suggestTextField);

    JLabel label_2 = new JLabel("成本价格");
    GridBagConstraints gbc_label_2 = new GridBagConstraints();
    gbc_label_2.anchor = 13;
    gbc_label_2.insets = new Insets(0, 0, 5, 5);
    gbc_label_2.gridx = 4;
    gbc_label_2.gridy = 1;
    panel_2.add(label_2, gbc_label_2);

    this.textField_1 = new JTextField();
    this.textField_1.setFont(new Font("宋体", 1, 12));
    this.textField_1.setColumns(10);
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.fill = 2;
    gbc_textField.insets = new Insets(0, 0, 5, 5);
    gbc_textField.gridx = 5;
    gbc_textField.gridy = 1;
    panel_2.add(this.textField_1, gbc_textField);

    JLabel label_3 = new JLabel("剩余数量总计");
    GridBagConstraints gbc_label_3 = new GridBagConstraints();
    gbc_label_3.anchor = 13;
    gbc_label_3.insets = new Insets(0, 0, 5, 5);
    gbc_label_3.gridx = 6;
    gbc_label_3.gridy = 1;
    panel_2.add(label_3, gbc_label_3);

    this.label_4 = new JLabel("");
    this.label_4.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_label_4 = new GridBagConstraints();
    gbc_label_4.anchor = 17;
    gbc_label_4.insets = new Insets(0, 0, 5, 0);
    gbc_label_4.gridx = 7;
    gbc_label_4.gridy = 1;
    panel_2.add(this.label_4, gbc_label_4);

    JLabel label_5 = new JLabel("类型");
    label_5.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_label_5 = new GridBagConstraints();
    gbc_label_5.anchor = 13;
    gbc_label_5.insets = new Insets(0, 0, 5, 5);
    gbc_label_5.gridx = 1;
    gbc_label_5.gridy = 2;
    panel_2.add(label_5, gbc_label_5);

    this.comboBox = new JComboBox();
    GridBagConstraints gbc_comboBox = new GridBagConstraints();
    gbc_comboBox.fill = 2;
    gbc_comboBox.insets = new Insets(0, 0, 5, 5);
    gbc_comboBox.gridx = 2;
    gbc_comboBox.gridy = 2;
    panel_2.add(this.comboBox, gbc_comboBox);

    JLabel label_6 = new JLabel("成本总计");
    GridBagConstraints gbc_label_6 = new GridBagConstraints();
    gbc_label_6.anchor = 13;
    gbc_label_6.insets = new Insets(0, 0, 5, 5);
    gbc_label_6.gridx = 6;
    gbc_label_6.gridy = 2;
    panel_2.add(label_6, gbc_label_6);

    this.label_7 = new JLabel("");
    this.label_7.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_label_7 = new GridBagConstraints();
    gbc_label_7.anchor = 17;
    gbc_label_7.insets = new Insets(0, 0, 5, 0);
    gbc_label_7.gridx = 7;
    gbc_label_7.gridy = 2;
    panel_2.add(this.label_7, gbc_label_7);

    JLabel label_8 = new JLabel("开始时间");
    label_8.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_label_8 = new GridBagConstraints();
    gbc_label_8.insets = new Insets(0, 0, 5, 5);
    gbc_label_8.gridx = 1;
    gbc_label_8.gridy = 3;
    panel_2.add(label_8, gbc_label_8);

    this.datePicker = new JXDatePicker();
    this.datePicker.getEditor().setFont(new Font("宋体", 1, 12));
    this.datePicker.getEditor().setColumns(10);
    this.datePicker.setFormats(new String[] { "yyyy-MM-dd" });
    GridBagConstraints gbc_datePicker = new GridBagConstraints();
    gbc_datePicker.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker.gridx = 2;
    gbc_datePicker.gridy = 3;
    panel_2.add(this.datePicker, gbc_datePicker);

    JLabel label_9 = new JLabel("结束时间");
    GridBagConstraints gbc_label_9 = new GridBagConstraints();
    gbc_label_9.insets = new Insets(0, 0, 5, 5);
    gbc_label_9.gridx = 4;
    gbc_label_9.gridy = 3;
    panel_2.add(label_9, gbc_label_9);

    this.datePicker_1 = new JXDatePicker();
    this.datePicker_1.getEditor().setFont(new Font("宋体", 1, 12));
    this.datePicker_1.getEditor().setColumns(10);
    this.datePicker_1.setFormats(new String[] { "yyyy-MM-dd" });
    GridBagConstraints gbc_datePicker_1 = new GridBagConstraints();
    gbc_datePicker_1.insets = new Insets(0, 0, 5, 5);
    gbc_datePicker_1.gridx = 5;
    gbc_datePicker_1.gridy = 3;
    panel_2.add(this.datePicker_1, gbc_datePicker_1);

    JLabel label_10 = new JLabel("剩余成本");
    label_10.setFont(new Font("宋体", 0, 12));
    GridBagConstraints gbc_label_10 = new GridBagConstraints();
    gbc_label_10.anchor = 13;
    gbc_label_10.insets = new Insets(0, 0, 5, 5);
    gbc_label_10.gridx = 6;
    gbc_label_10.gridy = 3;
    panel_2.add(label_10, gbc_label_10);

    this.label_11 = new JLabel("");
    this.label_11.setFont(new Font("宋体", 1, 12));
    GridBagConstraints gbc_label_11 = new GridBagConstraints();
    gbc_label_11.anchor = 17;
    gbc_label_11.insets = new Insets(0, 0, 5, 0);
    gbc_label_11.gridx = 7;
    gbc_label_11.gridy = 3;
    panel_2.add(this.label_11, gbc_label_11);

    JButton button = new JButton("查 询");
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.anchor = 13;
    gbc_button.insets = new Insets(0, 0, 0, 5);
    gbc_button.gridx = 5;
    gbc_button.gridy = 4;
    button.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        KuCTjPanel.this.catno = (KuCTjPanel.this.suggestTextField.getText().trim().equals("") ? null : KuCTjPanel.this.suggestTextField.getText().trim());
        KuCTjPanel.this.cost = (KuCTjPanel.this.textField_1.getText().trim().equals("") ? null : KuCTjPanel.this.textField_1.getText().trim());
        Options options = (Options)KuCTjPanel.this.comboBox.getSelectedItem();
        if (options != null) {
          String key = options.getKey();
          String text = options.getText();
          if ("all".equals(key))
            KuCTjPanel.this.type = null;
          else
            KuCTjPanel.this.type = text;
        }
        else {
          KuCTjPanel.this.type = null;
        }

        KuCTjPanel.this.date = (KuCTjPanel.this.datePicker.getEditor().getText().trim().equals("") ? null : KuCTjPanel.this.datePicker.getEditor().getText());
        KuCTjPanel.this.dateTo = (KuCTjPanel.this.datePicker_1.getEditor().getText().trim().equals("") ? null : KuCTjPanel.this.datePicker_1.getEditor().getText());
        int total = stockMoudle.getStockByParmSize(KuCTjPanel.this.catno, KuCTjPanel.this.cost, KuCTjPanel.this.type, KuCTjPanel.this.date, KuCTjPanel.this.dateTo);
        List list = stockMoudle.getStockByParm(KuCTjPanel.this.catno, KuCTjPanel.this.cost, KuCTjPanel.this.type, KuCTjPanel.this.date, KuCTjPanel.this.dateTo, 0, 20);
        KuCTjPanel.this.page.setPageInfo(total);
        KuCTjPanel.this.pageData(list);
        KuCTjPanel.this.sum();
      }
    });
    panel_2.add(button, gbc_button);

    JPanel panel_3 = new JPanel();
    panel.add(panel_3, "Center");
    GridBagLayout gbl_panel_3 = new GridBagLayout();
    gbl_panel_3.columnWidths = new int[4];
    gbl_panel_3.rowHeights = new int[] { 122 };
    gbl_panel_3.columnWeights = new double[] { 0.0D, 1.0D, 1.0D, 4.9E-324D };
    gbl_panel_3.rowWeights = new double[] { 1.0D, 0.0D, 4.9E-324D };
    panel_3.setLayout(gbl_panel_3);

    JScrollPane scrollPane = new JScrollPane();
    GridBagConstraints gbc_scrollPane = new GridBagConstraints();
    gbc_scrollPane.fill = 1;
    gbc_scrollPane.gridwidth = 3;
    gbc_scrollPane.insets = new Insets(10, 10, 10, 10);
    gbc_scrollPane.gridx = 0;
    gbc_scrollPane.gridy = 0;
    panel_3.add(scrollPane, gbc_scrollPane);

    this.table = new JTable();
    this.table.setAutoCreateRowSorter(true);

    this.table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {
        KuCTjPanel.this.clickedTable(arg0.getClickCount());
      }
    });
    this.table.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "id", "货号", "剩余数量", "进货数量", "类型", "售价", "成本", "名称", "规格", "颜色", "入库时间", "操作" })
    {
      Class[] columnTypes = { 
        String.class, String.class, Long.class, Long.class, Object.class, BigDecimal.class, BigDecimal.class, String.class, String.class, String.class, String.class, String.class };

      boolean[] columnEditables = new boolean[12];

      public Class getColumnClass(int columnIndex)
      {
        return this.columnTypes[columnIndex];
      }

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    this.table.getColumnModel().getColumn(0).setResizable(false);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(0);
    this.table.getColumnModel().getColumn(0).setMinWidth(0);
    this.table.getColumnModel().getColumn(0).setMaxWidth(0);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(101);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(85);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(85);
    this.table.getColumnModel().getColumn(4).setPreferredWidth(85);
    this.table.getColumnModel().getColumn(9).setResizable(false);
    this.table.getColumnModel().getColumn(10).setMinWidth(75);
    this.table.getColumnModel().getColumn(10).setMaxWidth(75);
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(0);
    TableColumn tc = this.table.getColumn("操作");
    tc.setPreferredWidth(60);
    tc.setCellRenderer(renderer);
    this.table.getTableHeader().setReorderingAllowed(false);
    scrollPane.setViewportView(this.table);

    this.page = new Page(new PageAction() {
      public void pageFirst() {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        List list = null;
        if ((KuCTjPanel.this.catno != null) || (KuCTjPanel.this.cost != null) || (KuCTjPanel.this.type != null) || (KuCTjPanel.this.date != null) || (KuCTjPanel.this.dateTo != null))
        {
          list = stockMoudle.getStockByParm(KuCTjPanel.this.catno, KuCTjPanel.this.cost, KuCTjPanel.this.type, KuCTjPanel.this.date, KuCTjPanel.this.dateTo, 0, 20);
        }
        else list = stockMoudle.getStock(0, 20);

        KuCTjPanel.this.pageData(list);
      }

      public void pagePrev(int pagenum) {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        List list = null;
        if ((KuCTjPanel.this.catno != null) || (KuCTjPanel.this.cost != null) || (KuCTjPanel.this.type != null) || (KuCTjPanel.this.date != null) || (KuCTjPanel.this.dateTo != null))
          list = stockMoudle.getStockByParm(KuCTjPanel.this.catno, KuCTjPanel.this.cost, KuCTjPanel.this.type, KuCTjPanel.this.date, KuCTjPanel.this.dateTo, pagenum, 20);
        else {
          list = stockMoudle.getStock(pagenum, 20);
        }
        KuCTjPanel.this.pageData(list);
      }

      public void pageNext(int pagenum) {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();

        List list = null;
        if ((KuCTjPanel.this.catno != null) || (KuCTjPanel.this.cost != null) || (KuCTjPanel.this.type != null) || (KuCTjPanel.this.date != null) || (KuCTjPanel.this.dateTo != null))
          list = stockMoudle.getStockByParm(KuCTjPanel.this.catno, KuCTjPanel.this.cost, KuCTjPanel.this.type, KuCTjPanel.this.date, KuCTjPanel.this.dateTo, pagenum, 20);
        else {
          list = stockMoudle.getStock(pagenum, 20);
        }
        KuCTjPanel.this.pageData(list);
      }

      public void pageLast(int pagenum) {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        List list = null;
        if ((KuCTjPanel.this.catno != null) || (KuCTjPanel.this.cost != null) || (KuCTjPanel.this.type != null) || (KuCTjPanel.this.date != null))
          list = stockMoudle.getStockByParm(KuCTjPanel.this.catno, KuCTjPanel.this.cost, KuCTjPanel.this.type, KuCTjPanel.this.date, KuCTjPanel.this.dateTo, pagenum, 20);
        else {
          list = stockMoudle.getStock(pagenum, 20);
        }
        KuCTjPanel.this.pageData(list);
      }

      public void itemStateChanged(int pagenum) {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        List list = null;
        if ((KuCTjPanel.this.catno != null) || (KuCTjPanel.this.cost != null) || (KuCTjPanel.this.type != null) || (KuCTjPanel.this.date != null))
          list = stockMoudle.getStockByParm(KuCTjPanel.this.catno, KuCTjPanel.this.cost, KuCTjPanel.this.type, KuCTjPanel.this.date, KuCTjPanel.this.dateTo, pagenum, 20);
        else {
          list = stockMoudle.getStock(pagenum, 20);
        }

        KuCTjPanel.this.pageData(list);
      }

      public void export(MouseEvent e)
      {
        try {
          StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
          List list = stockMoudle.getStock();
          if ((list != null) && (list.size() > 0)) {
            JFileChooser jfc = new JFileChooser("d:/");
            jfc.setFileSelectionMode(2);
            File fileff = new File("库存清单.csv");
            jfc.setSelectedFile(fileff);
            int result = jfc.showSaveDialog(KuCTjPanel.this.table);
            if (result == 1) return;
            File savedFile = jfc.getSelectedFile();
            if (savedFile.exists()) {
              int overwriteSelect = JOptionPane.showConfirmDialog(KuCTjPanel.this.table, "<html><font size=3>文件" + savedFile.getName() + "已存在，是否覆盖?</font><html>", "是否覆盖?", 
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
            stringBuffer.append("剩余数量");
            stringBuffer.append(",");
            stringBuffer.append("进货数量");
            stringBuffer.append(",");
            stringBuffer.append("成本");
            stringBuffer.append(",");
            stringBuffer.append("类型");
            stringBuffer.append(",");
            stringBuffer.append("名称");
            stringBuffer.append(",");
            stringBuffer.append("规格");
            stringBuffer.append(",");
            stringBuffer.append("颜色");
            stringBuffer.append(",");
            stringBuffer.append("进货时间");
            stringBuffer.append("\r\n");
            output.write(String.valueOf(stringBuffer));
            for (int i = 0; i < list.size(); i++) {
              Stock stock = (Stock)list.get(i);
              StringBuilder sb = new StringBuilder(128);
              sb.append(stock.getCatno());
              sb.append(",");
              sb.append(stock.getSellprice());
              sb.append(",");
              sb.append(stock.getSyamount());
              sb.append(",");
              sb.append(stock.getAmount());
              sb.append(",");
              sb.append(stock.getCostprice());
              sb.append(",");
              sb.append(stock.getType());
              sb.append(",");
              sb.append(stock.getStockname());
              sb.append(",");
              sb.append(stock.getSpecif());
              sb.append(",");
              sb.append(stock.getColor());
              sb.append(",");
              sb.append(stock.getDate());
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
    gbc_page.insets = new Insets(0, 0, 0, 5);
    gbc_page.gridx = 1;
    gbc_page.gridy = 1;
    panel_3.add(this.page, gbc_page);
    GridBagLayout gbl_page = new GridBagLayout();
    gbl_page.columnWidths = new int[1];
    gbl_page.rowHeights = new int[1];
    gbl_page.columnWeights = new double[] { 4.9E-324D };
    gbl_page.rowWeights = new double[] { 4.9E-324D };
    this.page.setLayout(gbl_page);

    JPanel panel_1 = new JPanel();
    ImageIcon alertimageIcon = new ImageIcon(KuCTjPanel.class.getResource("/com/bluebee/resource/image/alert.png"));
    tabbedPane.addTab("库存报警", alertimageIcon, panel_1, null);
    panel_1.setLayout(new BorderLayout(0, 0));

    JPanel panel_4 = new JPanel();
    panel_1.add(panel_4, "North");
    GridBagLayout gbl_panel_4 = new GridBagLayout();
    gbl_panel_4.columnWidths = new int[] { 60, 0, 124, 50, 0, 112 };
    gbl_panel_4.rowHeights = new int[] { 28, 0, 19 };
    gbl_panel_4.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_4.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    panel_4.setLayout(gbl_panel_4);

    final JLabel label_14 = new JLabel("");
    label_14.setForeground(Color.RED);
    GridBagConstraints gbc_label_14 = new GridBagConstraints();
    gbc_label_14.anchor = 17;
    gbc_label_14.gridwidth = 5;
    gbc_label_14.insets = new Insets(0, 0, 5, 5);
    gbc_label_14.gridx = 1;
    gbc_label_14.gridy = 0;
    panel_4.add(label_14, gbc_label_14);

    JLabel label = new JLabel("报警类型");
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.insets = new Insets(0, 0, 5, 5);
    gbc_label.anchor = 13;
    gbc_label.gridx = 1;
    gbc_label.gridy = 1;
    panel_4.add(label, gbc_label);

    this.stockAlarmTypes = new JComboBox();
    this.stockAlarmTypes.setModel(new DefaultComboBoxModel(SelectType.getStockAlarmTypes()));
    GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
    gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
    gbc_comboBox_1.fill = 2;
    gbc_comboBox_1.gridx = 2;
    gbc_comboBox_1.gridy = 1;
    panel_4.add(this.stockAlarmTypes, gbc_comboBox_1);

    JLabel label_13 = new JLabel("警戒线");
    GridBagConstraints gbc_label_13 = new GridBagConstraints();
    gbc_label_13.insets = new Insets(0, 0, 5, 5);
    gbc_label_13.anchor = 13;
    gbc_label_13.gridx = 4;
    gbc_label_13.gridy = 1;
    panel_4.add(label_13, gbc_label_13);

    this.stockAlarmNum = new JTextField();
    this.stockAlarmNum.setDocument(new LimitedDocument(20, this.dubString));
    GridBagConstraints gbc_textField1 = new GridBagConstraints();
    gbc_textField1.insets = new Insets(0, 0, 5, 5);
    gbc_textField1.fill = 2;
    gbc_textField1.gridx = 5;
    gbc_textField1.gridy = 1;
    panel_4.add(this.stockAlarmNum, gbc_textField1);
    this.stockAlarmNum.setColumns(10);

    JButton button_1 = new JButton("  查询 ");
    button_1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        String num = KuCTjPanel.this.stockAlarmNum.getText();
        Options options = (Options)KuCTjPanel.this.stockAlarmTypes.getSelectedItem();
        String key = options.getKey();
        String text = options.getText();
        label_14.setText("");
        if ("".equals(num.trim())) {
          label_14.setText("请输入" + text + "数量");
          return;
        }

        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
        try {
          double sl = Double.parseDouble(num);
          KuCTjPanel.this.alarmNum = sl;
          int size = stockMoudle.getStockAlarmSize(key, sl);
          KuCTjPanel.this.pageAlarm.setPageInfo(size);
          List pageList = stockMoudle.getStockAlarm(key, sl, 0, 20);
          KuCTjPanel.this.pageAlarmData(pageList);
        } catch (NumberFormatException ex) {
          label_14.setText("输入" + text + "数量不是数字");
          return;
        }
      }
    });
    GridBagConstraints gbc_button_1 = new GridBagConstraints();
    gbc_button_1.insets = new Insets(0, 0, 5, 0);
    gbc_button_1.gridx = 7;
    gbc_button_1.gridy = 1;
    panel_4.add(button_1, gbc_button_1);

    JPanel panel_5 = new JPanel();
    panel_1.add(panel_5, "Center");
    panel_5.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane_1 = new JScrollPane();
    panel_5.add(scrollPane_1, "Center");

    this.table_1 = new JTable();
    this.table_1.setModel(new DefaultTableModel(
      null, 
      new String[] { 
      "货号", "剩余数量", "类型", "成本", "名称", "颜色", "规格", "入库时间" })
    {
      boolean[] columnEditables = new boolean[8];

      public boolean isCellEditable(int row, int column)
      {
        return this.columnEditables[column];
      }
    });
    scrollPane_1.setViewportView(this.table_1);

    this.pageAlarm = new Page(new PageAction() {
      public void pageFirst() {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();

        Options options = (Options)KuCTjPanel.this.stockAlarmTypes.getSelectedItem();
        String key = options.getKey();

        List pageList = stockMoudle.getStockAlarm(key, KuCTjPanel.this.alarmNum, 0, 20);
        KuCTjPanel.this.pageAlarmData(pageList);
      }

      public void pagePrev(int pagenum)
      {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();

        Options options = (Options)KuCTjPanel.this.stockAlarmTypes.getSelectedItem();
        String key = options.getKey();

        List pageList = stockMoudle.getStockAlarm(key, KuCTjPanel.this.alarmNum, pagenum, 20);
        KuCTjPanel.this.pageAlarmData(pageList);
      }

      public void pageNext(int pagenum) {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();

        Options options = (Options)KuCTjPanel.this.stockAlarmTypes.getSelectedItem();
        String key = options.getKey();

        List pageList = stockMoudle.getStockAlarm(key, KuCTjPanel.this.alarmNum, pagenum, 20);
        KuCTjPanel.this.pageAlarmData(pageList);
      }

      public void pageLast(int pagenum) {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();

        Options options = (Options)KuCTjPanel.this.stockAlarmTypes.getSelectedItem();
        String key = options.getKey();

        List pageList = stockMoudle.getStockAlarm(key, KuCTjPanel.this.alarmNum, pagenum, 20);
        KuCTjPanel.this.pageAlarmData(pageList);
      }

      public void itemStateChanged(int pagenum) {
        StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();

        Options options = (Options)KuCTjPanel.this.stockAlarmTypes.getSelectedItem();
        String key = options.getKey();

        List pageList = stockMoudle.getStockAlarm(key, KuCTjPanel.this.alarmNum, pagenum, 20);
        KuCTjPanel.this.pageAlarmData(pageList);
      }

      public void export(MouseEvent e)
      {
        try {
          StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
          Options options = (Options)KuCTjPanel.this.stockAlarmTypes.getSelectedItem();
          String key = options.getKey();
          List list = stockMoudle.getStockAlarm(key, KuCTjPanel.this.alarmNum, 0, 2000000000);
          if ((list != null) && (list.size() > 0)) {
            JFileChooser jfc = new JFileChooser("d:/");
            jfc.setFileSelectionMode(2);
            File fileff = new File("库存报警清单.csv");
            jfc.setSelectedFile(fileff);
            int result = jfc.showSaveDialog(KuCTjPanel.this.table);
            if (result == 1) return;
            File savedFile = jfc.getSelectedFile();
            if (savedFile.exists()) {
              int overwriteSelect = JOptionPane.showConfirmDialog(KuCTjPanel.this.table, "<html><font size=3>文件" + savedFile.getName() + "已存在，是否覆盖?</font><html>", "是否覆盖?", 
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
            stringBuffer.append("剩余数量");
            stringBuffer.append(",");
            stringBuffer.append("类型");
            stringBuffer.append(",");
            stringBuffer.append("成本");
            stringBuffer.append(",");
            stringBuffer.append("名称");
            stringBuffer.append(",");
            stringBuffer.append("颜色");
            stringBuffer.append(",");
            stringBuffer.append("规格");

            stringBuffer.append(",");
            stringBuffer.append("进货时间");
            stringBuffer.append("\r\n");
            output.write(String.valueOf(stringBuffer));
            for (int i = 0; i < list.size(); i++) {
              Stock stock = (Stock)list.get(i);
              StringBuilder sb = new StringBuilder(128);
              sb.append(stock.getCatno());
              sb.append(",");
              sb.append(stock.getSyamount());
              sb.append(",");
              sb.append(stock.getType());
              sb.append(",");
              sb.append(stock.getCostprice());
              sb.append(",");
              sb.append(stock.getStockname());
              sb.append(",");
              sb.append(stock.getColor());
              sb.append(",");
              sb.append(stock.getSpecif());
              sb.append(",");
              sb.append(stock.getDate());
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
    panel_1.add(this.pageAlarm, "South");
  }

  public static KuCTjPanel getInstance()
  {
    if (kuCTjPanel != null) {
      kuCTjPanel.initdata();
    }
    return kuCTjPanel;
  }

  private void initdata()
  {
    this.catno = null;
    this.cost = null;
    this.type = null;
    this.date = null;
    StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
    int totalrow = stockMoudle.getStockSize();
    List list = stockMoudle.getStock(0, 20);
    pageData(list);
    this.page.setPageInfo(totalrow);
    sum();
    this.comboBox.setModel(new DefaultComboBoxModel(
      SelectType.getOptionsWithAll("HWLX")));
  }

  private void sum() {
    StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();

    int lcsl = stockMoudle.sumStockSyAmount(this.catno, this.cost, this.type, this.date, this.dateTo);
    this.label_12.setText("");
    if (lcsl > 0)
    {
      BigDecimal totalCost = stockMoudle.sumStockCostAmount(this.catno, this.cost, this.type, this.date, this.dateTo);

      BigDecimal totalsyCost = stockMoudle.sumSyStockCostAmount(this.catno, this.cost, this.type, this.date, this.dateTo);
      this.label_4.setText(String.valueOf(lcsl));
      this.label_7.setText(String.valueOf(totalCost));
      this.label_11.setText(String.valueOf(totalsyCost));
    } else {
      this.label_12.setText("当前没有库存了,请赶快进货吧!");
      clear();
    }
  }

  private void pageData(List list) {
    if ((list != null) && (list.size() > 0)) {
      clear();
      DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
      for (int i = 0; i < list.size(); i++) {
        Stock stock = (Stock)list.get(i);
        Object[] rowData = { stock.getId(), stock.getCatno(), Double.valueOf(stock.getSyamount()), Double.valueOf(stock.getAmount()), 
          stock.getType(), stock.getSellprice(), stock.getCostprice(), stock.getStockname(), stock.getSpecif(), stock.getColor(), stock.getDate(), "删除" };
        tableModel.insertRow(i, rowData);
      }
    }
  }

  private void pageAlarmData(List list)
  {
    if ((list != null) && (list.size() > 0)) {
      clearAlarm();

      DefaultTableModel tableModel = (DefaultTableModel)this.table_1.getModel();
      for (int i = 0; i < list.size(); i++) {
        Stock stock = (Stock)list.get(i);
        Object[] rowData = { stock.getCatno(), Double.valueOf(stock.getSyamount()), 
          stock.getType(), stock.getCostprice(), stock.getStockname(), stock.getColor(), stock.getSpecif(), stock.getDate() };
        tableModel.insertRow(i, rowData);
      }
    }
  }

  public void clear()
  {
    DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
    if (tableModel.getRowCount() > 0) {
      int rows = tableModel.getRowCount();
      for (int i = 0; i < rows; i++)
        tableModel.removeRow(0);
    }
  }

  public void clearAlarm()
  {
    DefaultTableModel tableModel = (DefaultTableModel)this.table_1.getModel();
    if (tableModel.getRowCount() > 0) {
      int rows = tableModel.getRowCount();
      for (int i = 0; i < rows; i++)
        tableModel.removeRow(0);
    }
  }

  private void clickedTable(int clickCount)
  {
    int nCol = this.table.getSelectedColumn();
    int nRow = this.table.getSelectedRow();
    Object objSel = this.table.getValueAt(nRow, nCol);
    if (clickCount == 2) {
      StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
      String id = String.valueOf(this.table.getValueAt(nRow, 0));

      Stock stockold = stockMoudle.getStockByID(id);

      KuCunDialog stockDialog = new KuCunDialog(this, "修改库存货物信息", stockold, this.table, new KuCunDialog.CallBack() {
        public void updateView() {
          KuCTjPanel.this.sum();
        }
      });
      stockDialog.setVisible(true);
    }
    else if ((objSel != null) && ((objSel instanceof String)) && 
      ("删除".equals(String.valueOf(objSel))))
    {
      int response = JOptionPane.showConfirmDialog(null, 
        "确定删除此条数据?", "删除库存数据", 0);
      switch (response) {
      case 0:
        DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
        String id = String.valueOf(tableModel.getValueAt(nRow, 0));
        StockMoudle optionMoudle = MoudleContentFactry.getStockMoudle();
        boolean isd = optionMoudle.delete(id);

        if (isd) {
          tableModel.removeRow(nRow);
          StockMoudle stockMoudle = MoudleContentFactry.getStockMoudle();
          int lcsl = stockMoudle.sumStockSyAmount(this.catno, this.cost, this.type, this.date, this.dateTo);
          BigDecimal totalCost = stockMoudle.sumStockCostAmount(this.catno, this.cost, this.type, this.date, this.dateTo);
          BigDecimal totalsyCost = stockMoudle.sumSyStockCostAmount(this.catno, this.cost, this.type, this.date, this.dateTo);
          this.label_12.setText("");
          if (lcsl > 0) {
            this.label_4.setText(String.valueOf(stockMoudle.sumStockSyAmount(this.catno, this.cost, this.type, this.date, this.dateTo)));
            this.label_7.setText(String.valueOf(totalCost));

            this.label_11.setText(String.valueOf(totalsyCost));
          } else {
            this.label_12.setText("当前没有库存了,请赶快进货吧!");
            clear();
          }
          sum();
        }
        break;
      case -1:
      case 1:
      }
    }
  }

  public void disposePage()
  {
  }

  public String getPageId()
  {
    return getClass().getName();
  }

  public String getPageName() {
    return ">>>库存统计";
  }

  public boolean isDefaultPage() {
    return this.isDefaultPage;
  }

  public void setDefaultPage(boolean bool)
  {
    this.isDefaultPage = bool;
  }
}