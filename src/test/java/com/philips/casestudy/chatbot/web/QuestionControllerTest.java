/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.web;

import static org.junit.Assert.assertEquals;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
import com.philips.casestudy.chatbot.domain.Question;
import com.philips.casestudy.chatbot.service.QuestionServiceInterface;

@RunWith(SpringRunner.class)
@WebMvcTest(value = QuestionController.class)
public class QuestionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private QuestionServiceInterface service;

  @Test
  public void getQuestionByIndex_Present_Test() throws Exception
  {
    final Map<String, String> options = Stream.of(new String[][] {
      { "1", "good" },
      { "2", "fine" },
      { "3", "excellent"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    final Question que=new Question(1,"How are you",options);
    Mockito.doReturn(que).when(service).returnQuestion(1);
    final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
        "/api/questions/1").accept(
            MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    final MockHttpServletResponse response = result.getResponse();
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void getQuestionByIndex_NotPresent_Test() throws Exception
  {
    final Map<String, String> options = Stream.of(new String[][] {
      { "1", "good" },
      { "2", "fine" },
      { "3", "excellent"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    final Question que=new Question(1,"How are you",options);
    Mockito.doReturn(que).when(service).returnQuestion(2);
    final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
        "/api/questions/0").accept(
            MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    final MockHttpServletResponse response = result.getResponse();
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  @Test
  public void option_Selected_Present() throws Exception
  {
    Mockito.when(service.getOptions(Mockito.anyInt(),Mockito.anyString())).thenReturn(true);
    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/api/questions/{index}/{answer}",1,"1")
        .contentType(MediaType.APPLICATION_JSON);
    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());


  }

  @Test
  public void option_Selected_Not_Present() throws Exception
  {
    Mockito.when(service.getOptions(Mockito.anyInt(),Mockito.anyString())).thenReturn(false);
    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/api/questions/{index}/{answer}",1,"1")
        .contentType(MediaType.APPLICATION_JSON);
    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.OK.value(), response.getStatus());

  }

}
