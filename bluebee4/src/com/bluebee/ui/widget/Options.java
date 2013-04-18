package com.bluebee.ui.widget;

public class Options
{
  private String key;
  private String text;

  public Options(String key, String text)
  {
    this.key = key;
    this.text = text;
  }
  public String getKey() {
    return this.key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getText()
  {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String toString() {
    return this.text;
  }
}