package com.bluebee.ui.widget;

import com.bluebee.util.GuiUtils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class LinkLabel extends JLabel
{
  private static final long serialVersionUID = 5536368501963764796L;
  private Color defaultColor;
  private Color selectedColor;
  private Color indicatedColor;
  private boolean isDrawLine = true;

  private boolean handCursor = true;

  private Cursor defaultCursor = getCursor();
  private boolean hovered;
  private boolean pressed;
  private Vector<ActionListener> listeners = new Vector();

  public LinkLabel()
  {
    this.defaultColor = getForeground();
    addMouseListener(new MouseAdapter()
    {
      public void mouseEntered(MouseEvent event) {
        LinkLabel.this.HandleMouseEntered(event);
      }

      public void mouseExited(MouseEvent event) {
        LinkLabel.this.HandleMouseExited(event);
      }

      public void mouseReleased(MouseEvent event) {
        LinkLabel.this.HandleMouseReleased(event);
      }

      public void mouseClicked(MouseEvent event) {
        LinkLabel.this.HandleMouseClicked(event);
      }
    });
  }

  public Color getDefaultColor()
  {
    return this.defaultColor;
  }

  public Color getSelectedColor() {
    return this.selectedColor;
  }

  public Color getIndicatedColor() {
    return this.indicatedColor;
  }

  public void setDefaultColor(Color c) {
    this.defaultColor = c;
  }

  public void setDrawLine(boolean b) {
    this.isDrawLine = b;
  }

  public void setIndicatedColor(Color c) {
    this.indicatedColor = c;
  }

  public void setSelectedColor(Color c) {
    this.selectedColor = c;
  }

  public void addActionListener(ActionListener listener)
  {
    if (!this.listeners.contains(listener))
      this.listeners.add(listener);
  }

  public void removeActionListener(ActionListener listener) {
    if (this.listeners.contains(listener))
      this.listeners.remove(listener);
  }

  protected void fireActionPerformed(ActionEvent e) {
    for (ActionListener listener : this.listeners)
      listener.actionPerformed(e);
  }

  public void HandleMouseClicked(MouseEvent event)
  {
    fireActionPerformed(new ActionEvent(this, hashCode(), 
      getText()));

    this.pressed = true;
  }

  public void HandleMouseEntered(MouseEvent event) {
    setHovered(true);
    setCursor(new Cursor(12));
    Color c = GuiUtils.getLookAndFeelColor("lightColor") == null ? UIManager.getColor("Menu.foreground") : GuiUtils.getLookAndFeelColor("lightColor");
    c = c == null ? Color.DARK_GRAY : c;
    setForeground(c);
    if (this.isDrawLine)
      repaint();
  }

  public void HandleMouseExited(MouseEvent event)
  {
    setHovered(false);
    setForeground(this.defaultColor);
    if (this.isDrawLine)
      repaint();
  }

  public void HandleMouseReleased(MouseEvent event)
  {
  }

  public void paint(Graphics g)
  {
    super.paint(g);
    int w = getWidth();
    int h = getHeight();
    int wd = 0;

    FontMetrics fm = g.getFontMetrics();
    if (getText() != null)
      wd += fm.stringWidth(getText());
    int x = w - wd;
    if (isHovered()) {
      int y = (h - fm.getHeight()) / 2 + fm.getAscent() + 2;
      g.setColor(this.defaultColor);
      g.drawLine(x, y, x + w, y);
    }
  }

  public boolean isHovered() {
    return this.hovered;
  }

  public void setHovered(boolean hovered) {
    this.hovered = hovered;
  }
}