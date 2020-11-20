/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.domain;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo")
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  int userId;
  @Column(name="name")
  String userName;
  @Column(name = "contact_no")
  BigInteger contactno;
  @Column(name="email")
  String email;
  @Column(name="city")
  String city;

  public UserInfo() {
    super();
  }
  public UserInfo(String name, BigInteger contactno, String email, String city) {
    super();
    this.userName = name;
    this.contactno = contactno;
    this.email = email;
    this.city = city;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int id) {
    this.userId = id;
  }
  public String getUserName() {
    return userName;
  }
  public void setUserName(String name) {
    this.userName = name;
  }
  public BigInteger getcontactno() {
    return contactno;
  }
  public void setcontactno(BigInteger contactno) {
    this.contactno = contactno;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }




}

