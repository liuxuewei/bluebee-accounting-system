package com.bluebee.pojo;

public class User
{
  private String id;
  private String usernmae;
  private String password;
  private String type;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsernmae() {
    return this.usernmae;
  }

  public void setUsernmae(String usernmae) {
    this.usernmae = usernmae;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }
  public String toString() {
    return this.usernmae;
  }
}