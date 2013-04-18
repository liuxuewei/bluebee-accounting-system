package com.bluebee;

import com.bluebee.moudle.MoudleContentFactry;
import com.bluebee.pojo.Config;
import com.bluebee.pojo.User;

public class SysEnv
{
  private static SysEnv sysEnv = new SysEnv();

  private String skin = "aTunes Dark";
  private String policy = "2";
  private String version;
  private String loginUserid;

  private SysEnv()
  {
    Config configSkin = MoudleContentFactry.getConfigMoudle().getConfig("skin");
    if (configSkin != null) {
      this.skin = configSkin.getValue();
    }

    Config configpolicy = MoudleContentFactry.getConfigMoudle().getConfig("policy");

    if (configpolicy != null) {
      this.policy = configpolicy.getValue();
    }

    Config versionconfig = MoudleContentFactry.getConfigMoudle().getConfig("VERSION");

    if ((versionconfig == null) || ("".equals(versionconfig.getValue())))
      this.version = "1.1";
    else
      this.version = versionconfig.getValue();
  }

  public static SysEnv getInstance()
  {
    return sysEnv;
  }

  public String getSkin() {
    return this.skin;
  }

  public User getAdminUser() {
    return MoudleContentFactry.getUserMoudle().getUserByid("10000");
  }

  public String getLoginUser() {
    return this.loginUserid;
  }

  public void setLoginUser(String loginUserid) {
    this.loginUserid = loginUserid;
  }

  public String getVersion() {
    return this.version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setSkin(String skin) {
    this.skin = skin;
  }

  public String getPolicy() {
    return this.policy;
  }

  public void setPolicy(String policy) {
    this.policy = policy;
  }
}