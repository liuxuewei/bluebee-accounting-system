package com.bluebee.ui.widget;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JComponent;

public class CurtainButton extends JComponent
{
  private static final long serialVersionUID = 8224371439953976749L;
  private ArrayList<ActionListener> listeners = new ArrayList();
  private String text;
  private Icon icon;
  private float alignment = 0.0F;

  public CurtainButton()
  {
    this(null, null);
  }

  public CurtainButton(String text, Icon icon) {
    this.text = text;
    this.icon = icon;
    setFont(new Font("ËÎÌו", 0, 12));
    setUI(new CurtainButtonUI());
  }

  public void addActionListener(ActionListener listener) {
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

  public void setText(String text) {
    this.text = text;
    repaint();
  }

  public String getText() {
    return this.text;
  }

  public Icon getIcon() {
    return this.icon;
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
    repaint();
  }

  public float getAlignment() {
    return this.alignment;
  }

  public void setAlignment(float alignment) {
    this.alignment = alignment;
    repaint();
  }

  public void updateUI() {
    setUI(new CurtainButtonUI());
  }
}