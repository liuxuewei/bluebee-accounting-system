package com.bluebee.ui.frame;

import com.bluebee.ui.widget.LinkLabel;
import com.bluebee.util.GuiUtils;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ListPane extends JPanel
{
  private static final long serialVersionUID = 6807890033041814417L;
  private static final int HORZ_PAD = 12;
  private static final int VERT_PAD = 6;

  public ListPane()
  {
    initComponents();
    Border b = BorderFactory.createEmptyBorder(6, 12, 6, 12);
    setBorder(b);
  }

  private void initComponents() {
    BoxLayout boxLayout = new BoxLayout(this, 1);

    setLayout(boxLayout);
  }

  public void addItem(String text, ImageIcon icon) {
    addItem(text, icon, null, true);
  }

  public void addItem(String text, ImageIcon icon, final Action action, boolean isVisible) {
    LinkLabel lblItem = new LinkLabel();
    lblItem.setFont(new Font("ËÎÌו", 0, 12));
    lblItem.setCursor(new Cursor(12));
    lblItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (action != null)
          action.actionPerformed(event);
        else
          System.out.println(((LinkLabel)event.getSource()).getText());
      }
    });
    if (icon != null) {
      lblItem.setIcon(icon);
      lblItem.setForeground(GuiUtils.getLookAndFeelColor("foregroundColor"));
    } else {
      lblItem.setForeground(GuiUtils.getLookAndFeelColor("foregroundColor"));
    }
    lblItem.setText(text);
    lblItem.setVisible(isVisible);
    add(lblItem);
    JLabel jLabel = new JLabel("      ");
    jLabel.setSize(185, 10);
    jLabel.setFont(new Font("ËÎÌו", 0, 6));
    jLabel.setPreferredSize(new Dimension(185, 10));
    jLabel.setVisible(isVisible);
    add(jLabel);
  }

  public void addButtonItem(String text, ImageIcon icon) {
    JButton btnItem = new JButton();

    btnItem.setBorder(BorderFactory.createCompoundBorder());
    btnItem.setBackground(GuiUtils.getLookAndFeelColor("backgroundFillColor"));
    if (icon != null) {
      btnItem.setIcon(icon);
    }
    btnItem.setForeground(GuiUtils.getLookAndFeelColor("foregroundColor"));
    btnItem.setText(text);
    add(btnItem);
  }

  public void addListItem() {
    String[] data = { "one", "two", "three", "four" };
    JList lstItem = new JList(data);
    add(lstItem);
  }
}