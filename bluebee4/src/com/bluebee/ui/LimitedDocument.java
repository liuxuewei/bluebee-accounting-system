package com.bluebee.ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitedDocument extends PlainDocument
{
  private static final long serialVersionUID = 1L;
  private int maxLength = -1;
  private String allowCharAsString = null;

  public LimitedDocument()
  {
  }

  public LimitedDocument(int maxLength, String str)
  {
    this.maxLength = maxLength;
    this.allowCharAsString = str;
  }

  public void insertString(int offset, String str, AttributeSet attrSet)
    throws BadLocationException
  {
    if (str == null) {
      return;
    }

    if ((this.allowCharAsString != null) && (str.length() == 1) && 
      (this.allowCharAsString.indexOf(str) == -1)) {
      return;
    }

    char[] charVal = str.toCharArray();
    String strOldValue = getText(0, getLength());
    byte[] tmp = strOldValue.getBytes();

    if ((this.maxLength != -1) && (tmp.length + charVal.length > this.maxLength)) {
      return;
    }

    super.insertString(offset, str, attrSet);
  }
}