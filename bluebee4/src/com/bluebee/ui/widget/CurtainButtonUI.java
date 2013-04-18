package com.bluebee.ui.widget;

import com.bluebee.util.GuiUtils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

public class CurtainButtonUI extends ComponentUI
  implements MouseListener, MouseMotionListener
{
  public static final int PREFERED_HEIGHT = 22;
  private static final Dimension PREFERED_SIZE = new Dimension(200, 
    22);
  private static final int LEADING_TEXT_PADDING = 12;
  private static final int TEXT_ICON_PADDING = 6;
  private static Color pressed_dark_color = new Color(254, 145, 78);
  private static Color pressed_light_color = new Color(254, 204, 135);
  private static Color hovered_dark_color = new Color(255, 211, 150);
  private static Color hovered_light_color = new Color(255, 244, 204);
  private Color light_color;
  private Color dark_color;
  private Color border_color;
  private boolean pressed;
  private boolean hovered;
  private CurtainButton button;

  public CurtainButtonUI()
  {
    this.light_color = (GuiUtils.getLookAndFeelColor("lightColor") == null ? UIManager.getColor("Panel.background") : GuiUtils.getLookAndFeelColor("lightColor"));
    this.light_color = (this.light_color == null ? Color.LIGHT_GRAY : this.light_color);
    this.dark_color = (GuiUtils.getLookAndFeelColor("darkColor") == null ? UIManager.getColor("Menu.selectionBackground") : 
      GuiUtils.getLookAndFeelColor("darkColor"));
    this.dark_color = (this.dark_color == null ? Color.LIGHT_GRAY : this.dark_color);

    this.border_color = (GuiUtils.getLookAndFeelColor("borderColor") == null ? UIManager.getColor("Panel.background") : 
      GuiUtils.getLookAndFeelColor("borderColor"));
    this.border_color = (this.border_color == null ? Color.DARK_GRAY : this.border_color);
  }

  public static ComponentUI createUI(JComponent c) {
    return new CurtainButtonUI();
  }

  public void installUI(JComponent c) {
    this.button = ((CurtainButton)c);
    this.button.addMouseListener(this);
    this.button.addMouseMotionListener(this);
  }

  public void uninstallUI(JComponent c) {
    this.button.removeMouseListener(this);
    this.button.removeMouseMotionListener(this);
  }

  public void mouseClicked(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
    setPressed(true);
  }

  public void mouseReleased(MouseEvent e) {
    setPressed(false);
    this.button.fireActionPerformed(new ActionEvent(this.button, 0, this.button.getText()));
  }

  public void mouseEntered(MouseEvent e) {
    setHovered(true);
  }

  public void mouseExited(MouseEvent e) {
    setHovered(false);
  }

  public void mouseDragged(MouseEvent e) {
  }

  public void mouseMoved(MouseEvent e) {
  }

  public boolean isPressed() {
    return this.pressed;
  }

  public void setPressed(boolean pressed) {
    this.pressed = pressed;
    this.button.repaint();
  }

  public boolean isHovered() {
    return this.hovered;
  }

  public void setHovered(boolean hovered) {
    this.hovered = hovered;
    this.button.repaint();
  }

  public Dimension getPreferredSize(JComponent c) {
    return PREFERED_SIZE;
  }

  public void paint(Graphics g, JComponent c) {
    paintComponentBackground(g);
    int w = this.button.getWidth();
    int h = this.button.getHeight();
    int wd = 0;
    if (this.button.getIcon() != null)
      wd += this.button.getIcon().getIconWidth();
    FontMetrics fm = g.getFontMetrics();
    if (this.button.getText() != null)
      wd += fm.stringWidth(this.button.getText()) + 6;
    int x = (int)(12.0F + (w - 24 - wd) * this.button.getAlignment());
    if (this.button.getIcon() != null) {
      int y = (h - this.button.getIcon().getIconHeight()) / 2;
      this.button.getIcon().paintIcon(this.button, g, x, y);
      x += this.button.getIcon().getIconWidth() + 6;
    }
    if (this.button.getText() != null) {
      int y = (h - fm.getHeight()) / 2 + fm.getAscent();
      g.setColor(this.button.getForeground());
      g.drawString(this.button.getText(), x, y);
    }
  }

  protected void paintComponentBackground(Graphics g) {
    int w = this.button.getWidth();
    int h = this.button.getHeight();
    Color font_color = this.hovered ? Color.black : this.pressed ? Color.GRAY : null;
    this.button.setForeground(font_color);
    this.button.setFont(new Font("ËÎÌו", 1, 12));
    Color top_color = this.hovered ? hovered_light_color : this.pressed ? pressed_dark_color : this.light_color;
    Color bottom_color = this.hovered ? hovered_dark_color : this.pressed ? pressed_light_color : this.dark_color;
    GradientPaint gp = new GradientPaint(1.0F, 1.0F, top_color, 1.0F, h - 2, bottom_color);
    Graphics2D g2d = (Graphics2D)g;
    g2d.setPaint(gp);
    g2d.fillRect(1, 1, w - 2, h - 2);
    g.setColor(this.border_color);
    g.drawLine(0, 0, w - 1, 0);
    g.drawLine(0, 0, 0, h - 1);
    g.setColor(this.border_color);
    g.drawLine(0, h - 1, w - 1, h - 1);
    g.drawLine(w - 1, 0, w - 1, h - 1);
  }
}