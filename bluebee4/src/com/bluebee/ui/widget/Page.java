package com.bluebee.ui.widget;

import com.bluebee.action.PageAction;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Page extends JPanel
{
  private JLabel currentPage;
  private JLabel totalPage;
  private PageInfo pageInfo;
  private DefaultComboBoxModel defaultComboBoxModel;
  private PageAction pageAction;

  public Page(PageAction pageAction)
  {
    this.pageAction = pageAction;
    this.pageInfo = new PageInfo();
    initLayout();
  }

  private void initLayout() {
    JPanel panel_1 = new JPanel();
    GridBagConstraints gbc_panel_1 = new GridBagConstraints();
    gbc_panel_1.insets = new Insets(0, 0, 0, 5);
    gbc_panel_1.fill = 1;
    gbc_panel_1.gridx = 1;
    gbc_panel_1.gridy = 1;
    add(panel_1, gbc_panel_1);
    GridBagLayout gbl_panel_1 = new GridBagLayout();
    gbl_panel_1.columnWidths = new int[] { 12, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 62 };
    gbl_panel_1.rowHeights = new int[] { 15 };
    gbl_panel_1.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 
      0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 
      0.0D, 0.0D, 0.0D, 4.9E-324D };
    gbl_panel_1.rowWeights = new double[] { 0.0D, 4.9E-324D };
    panel_1.setLayout(gbl_panel_1);

    JLabel label = new JLabel("");
    label.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent arg0) {
        Page.this.pageAction.pageFirst();
        Page.this.pageInfo.setCurrentPage(1);
        Page.this.currentPage.setText("1");
      }
    });
    label.setCursor(Cursor.getPredefinedCursor(12));
    label.setToolTipText("第一页");
    label.setIcon(new ImageIcon(
      Page.class
      .getResource("/com/bluebee/resource/image/page_first.gif")));
    GridBagConstraints gbc_label = new GridBagConstraints();
    gbc_label.fill = 3;
    gbc_label.insets = new Insets(0, 0, 0, 5);
    gbc_label.gridx = 0;
    gbc_label.gridy = 0;
    panel_1.add(label, gbc_label);

    JLabel label1 = new JLabel("");
    label1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {
        int c = Page.this.pageInfo.getCurrentPage();
        if (c > 1) {
          int prev = c - 1;
          Page.this.pageInfo.setCurrentPage(prev);
          Page.this.currentPage.setText(String.valueOf(prev));
          int offset = (c - 2) * Page.this.pageInfo.getRowPerPage();
          Page.this.pageAction.pagePrev(offset);
        }
      }
    });
    label1.setCursor(Cursor.getPredefinedCursor(12));
    label1.setToolTipText("上一页");
    label1.setIcon(new ImageIcon(
      Page.class.getResource("/com/bluebee/resource/image/page_prev.gif")));
    GridBagConstraints gbc_label1 = new GridBagConstraints();
    gbc_label1.fill = 3;
    gbc_label1.insets = new Insets(0, 0, 0, 5);
    gbc_label1.gridx = 1;
    gbc_label1.gridy = 0;
    panel_1.add(label1, gbc_label1);

    JLabel label2 = new JLabel("");
    label2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {
        int c = Page.this.pageInfo.getCurrentPage();
        int next = c + 1;
        if (next <= Page.this.pageInfo.getTotalPage()) {
          Page.this.pageInfo.setCurrentPage(next);
          Page.this.currentPage.setText(String.valueOf(next));
          int offset = c * Page.this.pageInfo.getRowPerPage();
          Page.this.pageAction.pageNext(offset);
        }
      }
    });
    label2.setCursor(Cursor.getPredefinedCursor(12));
    label2.setToolTipText("下一页");
    label2.setIcon(new ImageIcon(
      Page.class
      .getResource("/com/bluebee/resource/image/page_next.gif")));
    GridBagConstraints gbc_label2 = new GridBagConstraints();
    gbc_label2.fill = 3;
    gbc_label2.insets = new Insets(0, 0, 0, 5);
    gbc_label2.gridx = 2;
    gbc_label2.gridy = 0;
    panel_1.add(label2, gbc_label2);

    JLabel label3 = new JLabel("");
    label3.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0) {
        int totalpage = Page.this.pageInfo.getTotalPage();
        Page.this.pageInfo.setCurrentPage(totalpage);
        int offset = (totalpage - 1) * Page.this.pageInfo.getRowPerPage();
        Page.this.pageAction.pageLast(offset);

        Page.this.currentPage.setText(String.valueOf(Page.this.pageInfo.getTotalPage()));
      }
    });
    label3.setCursor(Cursor.getPredefinedCursor(12));
    label3.setToolTipText("最后一页");
    label3.setIcon(new ImageIcon(
      Page.class
      .getResource("/com/bluebee/resource/image/page_last.gif")));
    GridBagConstraints gbc_label3 = new GridBagConstraints();
    gbc_label3.fill = 3;
    gbc_label3.insets = new Insets(0, 0, 0, 5);
    gbc_label3.gridx = 3;
    gbc_label3.gridy = 0;
    panel_1.add(label3, gbc_label3);

    JLabel label4 = new JLabel("当前第");
    GridBagConstraints gbc_label4 = new GridBagConstraints();
    gbc_label4.fill = 3;
    gbc_label4.insets = new Insets(0, 0, 0, 5);
    gbc_label4.gridx = 6;
    gbc_label4.gridy = 0;
    panel_1.add(label4, gbc_label4);

    this.currentPage = new JLabel("0");
    GridBagConstraints gbc_label5 = new GridBagConstraints();
    gbc_label5.fill = 3;
    gbc_label5.insets = new Insets(0, 0, 0, 5);
    gbc_label5.gridx = 7;
    gbc_label5.gridy = 0;
    panel_1.add(this.currentPage, gbc_label5);

    JLabel label5 = new JLabel("页");
    GridBagConstraints gbc_label6 = new GridBagConstraints();
    gbc_label6.fill = 3;
    gbc_label6.insets = new Insets(0, 0, 0, 5);
    gbc_label6.gridx = 8;
    gbc_label6.gridy = 0;
    panel_1.add(label5, gbc_label6);

    JLabel label7 = new JLabel("共");
    GridBagConstraints gbc_label7 = new GridBagConstraints();
    gbc_label7.fill = 3;
    gbc_label7.anchor = 13;
    gbc_label7.insets = new Insets(0, 0, 0, 5);
    gbc_label7.gridx = 10;
    gbc_label7.gridy = 0;
    panel_1.add(label7, gbc_label7);

    this.totalPage = new JLabel("0");
    GridBagConstraints gbc_label8 = new GridBagConstraints();
    gbc_label8.fill = 3;
    gbc_label8.insets = new Insets(0, 0, 0, 5);
    gbc_label8.gridx = 11;
    gbc_label8.gridy = 0;
    panel_1.add(this.totalPage, gbc_label8);

    JLabel label8 = new JLabel("页");
    GridBagConstraints gbc_label9 = new GridBagConstraints();
    gbc_label9.fill = 3;
    gbc_label9.insets = new Insets(0, 0, 0, 5);
    gbc_label9.gridx = 12;
    gbc_label9.gridy = 0;
    panel_1.add(label8, gbc_label9);

    JLabel label9 = new JLabel("第");
    GridBagConstraints gbc_label10 = new GridBagConstraints();
    gbc_label10.fill = 3;
    gbc_label10.insets = new Insets(0, 0, 0, 5);
    gbc_label10.anchor = 13;
    gbc_label10.gridx = 14;
    gbc_label10.gridy = 0;
    panel_1.add(label9, gbc_label10);

    JComboBox comboBoxPage = new JComboBox();
    comboBoxPage.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent arg0) {
        int pager = Integer.parseInt(String.valueOf(arg0.getItem()));
        Page.this.pageInfo.setCurrentPage(pager);
        Page.this.currentPage.setText(String.valueOf(pager));
        int offset = (pager - 1) * Page.this.pageInfo.getRowPerPage();
        Page.this.pageAction.itemStateChanged(offset);
      }
    });
    this.defaultComboBoxModel = new DefaultComboBoxModel();
    comboBoxPage.setModel(this.defaultComboBoxModel);

    GridBagConstraints gbc_comboBox = new GridBagConstraints();
    gbc_comboBox.gridwidth = 2;
    gbc_comboBox.insets = new Insets(0, 0, 0, 5);
    gbc_comboBox.fill = 1;
    gbc_comboBox.gridx = 15;
    gbc_comboBox.gridy = 0;
    panel_1.add(comboBoxPage, gbc_comboBox);

    JLabel label10 = new JLabel("页");
    GridBagConstraints gbc_label11 = new GridBagConstraints();
    gbc_label11.fill = 3;
    gbc_label11.insets = new Insets(0, 0, 0, 5);
    gbc_label11.gridx = 17;
    gbc_label11.gridy = 0;
    panel_1.add(label10, gbc_label11);

    JLabel label11 = new JLabel("");
    label11.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e) {
        Page.this.pageAction.export(e);
      }
    });
    label11.setToolTipText("导出");
    label11.setIcon(new ImageIcon(Page.class.getResource("/com/bluebee/resource/image/save.gif")));
    GridBagConstraints gbc_label12 = new GridBagConstraints();
    gbc_label12.anchor = 17;
    gbc_label12.gridx = 19;
    gbc_label12.gridy = 0;
    panel_1.add(label11, gbc_label12);
  }

  public void setPageInfo(int totalrows)
  {
    int rowPerPage = this.pageInfo.getRowPerPage();

    int totalpagenum = totalrows / rowPerPage;

    if (totalrows % rowPerPage > 0) {
      totalpagenum++;
    }
    this.pageInfo.setCurrentPage(1);
    this.pageInfo.setTotalPage(totalpagenum);
    setPageList(totalpagenum);
    this.currentPage.setText("1");
    this.totalPage.setText(String.valueOf(totalpagenum));
  }

  private void setPageList(int pagesize) {
    int n = pagesize;
    if (this.defaultComboBoxModel.getSize() > 0) {
      this.defaultComboBoxModel.removeAllElements();
    }
    if (pagesize > 0) {
      for (int i = 0; i < n; i++) {
        this.defaultComboBoxModel.insertElementAt(Integer.valueOf(n - i), 0);
      }
      this.defaultComboBoxModel.setSelectedItem(Integer.valueOf(1));
    }
  }
  public static class PageInfo { private int currentPage = 1;

    private int totalPage = 0;

    private int rowPerPage = 20;

    private int totalRowCount = 0;
    private Object[][] resultDate;
    private int restDate;

    public int getCurrentPage() { return this.currentPage; }

    public void setCurrentPage(int currentPage)
    {
      this.currentPage = currentPage;
    }

    public int getTotalPage()
    {
      return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
      this.totalPage = totalPage;
    }

    public int getRowPerPage() {
      return this.rowPerPage;
    }

    public void setRowPerPage(int rowPerPage) {
      this.rowPerPage = rowPerPage;
    }

    public int getTotalRowCount() {
      return this.totalRowCount;
    }

    public void setTotalRowCount(int totalRowCount) {
      this.totalRowCount = totalRowCount;
    }

    public Object[][] getResultDate() {
      return this.resultDate;
    }

    public void setResultDate(Object[][] resultDate) {
      this.resultDate = resultDate;
    }

    public int getRestDate() {
      return this.restDate;
    }

    public void setRestDate(int restDate) {
      this.restDate = restDate;
    }
  }
}