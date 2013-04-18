package com.bluebee.util;

public class Message
{
  private static Message ourInstance = new Message();

  private String code = "";
  private String text = "";

  public static Message getInstance()
  {
    if (ourInstance == null) {
      ourInstance = new Message();
    }
    return ourInstance;
  }

  public String getCode()
  {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }
}