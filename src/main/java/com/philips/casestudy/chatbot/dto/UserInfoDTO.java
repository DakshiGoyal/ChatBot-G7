/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dto;

import java.math.BigInteger;
import org.modelmapper.ModelMapper;
import com.philips.casestudy.chatbot.domain.UserInfo;

public class UserInfoDTO {
  String userName;
  BigInteger contactno;
  String email;
  String city;
  int userId;

  public UserInfoDTO()
  {

  }

  public UserInfoDTO(UserInfo user)
  {
    this(user.getUserName(),user.getcontactno(),user.getEmail(),user.getCity());
  }

  public UserInfoDTO(String name,BigInteger contactno,String email, String city)
  {
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

  public UserInfo changeDTOToEntity(UserInfoDTO user)
  {
    final ModelMapper model=new ModelMapper();
    return model.map(user,UserInfo.class);

  }
}
