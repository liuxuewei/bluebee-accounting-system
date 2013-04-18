package com.bluebee.ui.widget;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lines extends JPanel
{
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

    float[] dash1 = { 2.0F, 0.0F, 2.0F };
    float[] dash2 = { 1.0F, 1.0F, 1.0F };
    float[] dash3 = { 4.0F, 0.0F, 2.0F };
    float[] dash4 = { 4.0F, 4.0F, 1.0F };

    g2d.drawLine(20, 40, 250, 40);

    BasicStroke bs1 = new BasicStroke(1.0F, 0, 
      1, 1.0F, dash1, 2.0F);

    BasicStroke bs2 = new BasicStroke(1.0F, 0, 
      1, 1.0F, dash2, 2.0F);

    BasicStroke bs3 = new BasicStroke(1.0F, 0, 
      1, 1.0F, dash3, 2.0F);

    BasicStroke bs4 = new BasicStroke(1.0F, 0, 
      1, 1.0F, dash4, 2.0F);

    g2d.setStroke(bs1);
    g2d.drawLine(20, 80, 250, 80);

    g2d.setStroke(bs2);
    g2d.drawLine(20, 120, 250, 120);

    g2d.setStroke(bs3);
    g2d.drawLine(20, 160, 250, 160);

    g2d.setStroke(bs4);
    g2d.drawLine(20, 200, 250, 200);
  }

  public static void main(String[] args)
  {
    Lines lines = new Lines();
    JFrame frame = new JFrame("Lines");
    frame.setDefaultCloseOperation(3);
    frame.add(lines);
    frame.setSize(280, 270);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}