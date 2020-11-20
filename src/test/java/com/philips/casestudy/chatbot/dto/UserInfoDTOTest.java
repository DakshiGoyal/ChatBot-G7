/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.math.BigInteger;
import org.junit.Test;
import com.philips.casestudy.chatbot.domain.UserInfo;

public class UserInfoDTOTest {

  @Test
  public void when_Convert_UserDto_To_UserEntity_thenCorrect() {

    final UserInfoDTO userDto = new UserInfoDTO();

    userDto.setUserName("aashna");
    userDto.setEmail("aashna@yahoo.com");
    userDto.setcontactno(new BigInteger("9080706050") );
    userDto.setCity("delhi");
    final UserInfo savedUser=userDto.changeDTOToEntity(userDto);

    assertTrue(savedUser.getUserName().equals(userDto.getUserName()));
    assertTrue(savedUser.getEmail().equals(userDto.getEmail()));
    assertEquals(savedUser.getcontactno(),userDto.getcontactno());
    assertTrue(savedUser.getCity().equals(userDto.getCity()));



  }

}
