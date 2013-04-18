package com.bluebee.ui.widget;

import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SearchTextField extends JTextField
{
  public SearchTextField()
  {
    ImageIcon image = new ImageIcon(SearchTextField.class.getResource("/com/bluebee/resource/image/system-search-3.png"));
    int w = image.getIconWidth();
    int h = image.getIconHeight();
    Insets m = getMargin();
    setMargin(new Insets(m.top, m.left + w, m.bottom, m.right));

    JLabel label = new JLabel(image);
    label.setCursor(Cursor.getPredefinedCursor(0));
    label.setBorder(null);
    label.setBounds(m.left, m.top, w, h);

    add(label);
  }
}