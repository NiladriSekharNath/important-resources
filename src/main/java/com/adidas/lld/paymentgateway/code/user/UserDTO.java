package com.adidas.lld.paymentgateway.code.user;

public class UserDTO {
  private String userId;
  private String username;
  private String mail;

  public String getUserId(){ return userId; }
  public void setUserId(String userId){ this.userId = userId; }

  public String getUserName() { return this.username; }

  public void setUserName(String username) { this.username = username; }

  public String getMail() { return mail; }

  public void setMail(String mail) { this.mail = mail; }
}
