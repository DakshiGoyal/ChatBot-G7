/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.web;

import static org.junit.Assert.assertEquals;
import java.math.BigInteger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.philips.casestudy.chatbot.dto.UserInfoDTO;
import com.philips.casestudy.chatbot.service.ChatBotServiceInterface;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ChatBotServiceInterface service;

  @Test
  public void addUsers_When_Contact_Email_Doesnot_Exist_Test() throws Exception {

    final UserInfoDTO mockUser=new UserInfoDTO("aashna",new BigInteger("9080706050"),"aashna@yahoo.com","delhi");
    mockUser.setUserId(1);
    Mockito.when(
        service.saveUsers(Mockito.any(UserInfoDTO.class))).thenReturn(mockUser.getUserId());

    final String exampleUserJson = "{\"name\":\"aashna\",\"contactno\":\"9080706050\",\"email\":\"aashna@yahoo.com\",\"city\":\"delhi\"}";

    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/users")
        .accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
        .contentType(MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.CREATED.value(), response.getStatus());

  }



  @Test
  public void addUsers_When_Contact_Email_AlreadyExist_Test() throws Exception {

    final UserInfoDTO mockUser=new UserInfoDTO("aashna",new BigInteger("9080706050"),"aashna@yahoo.com","delhi");
    Mockito.when(
        service.saveUsers(Mockito.any(UserInfoDTO.class))).thenReturn(mockUser.getUserId());

    final String exampleUserJson = "{\"name\":\"aashna\",\"contactno\":\"9080706050\",\"email\":\"aashna@yahoo.com\",\"city\":\"delhi\"}";

    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/users")
        .accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
        .contentType(MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

  }

}
