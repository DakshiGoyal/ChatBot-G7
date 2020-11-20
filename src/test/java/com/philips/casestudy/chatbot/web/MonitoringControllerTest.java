/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.web;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
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
import com.philips.casestudy.chatbot.domain.MonitoringDevice;
import com.philips.casestudy.chatbot.dto.MonitoringDeviceDTO;
import com.philips.casestudy.chatbot.service.ChatBotServiceInterface;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MonitoringDeviceController.class)
public class MonitoringControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ChatBotServiceInterface service;

  @Test
  public void addDevices_When_Same_Parameters_Doesnot_Exist_Test() throws Exception {

    final MonitoringDeviceDTO mockDevice=new MonitoringDeviceDTO("GoldwayMan40E","touch",10);
    mockDevice.setDeviceId(1);
    Mockito.when(
        service.save(Mockito.any(MonitoringDeviceDTO.class))).thenReturn(mockDevice.getDeviceId());

    final String exampleDeviceJson = "{\"name\":\"GoldmanWay40E\",\"touch\":\"touch\",\"screenSize\":10}";

    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/devices")
        .accept(MediaType.APPLICATION_JSON).content(exampleDeviceJson)
        .contentType(MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.CREATED.value(), response.getStatus());

  }



  @Test
  public void addUsers_When_Parameters_Exist_Test() throws Exception {

    final MonitoringDeviceDTO mockDevice=new MonitoringDeviceDTO("GoldwayMan40E","touch",10);
    // mockDevice.setId(1);
    Mockito.when(
        service.save(Mockito.any(MonitoringDeviceDTO.class))).thenReturn(mockDevice.getDeviceId());

    final String exampleDeviceJson = "{\"name\":\"GoldmanWay40E\",\"touch\":\"touch\",\"screenSize\":10}";

    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/devices")
        .accept(MediaType.APPLICATION_JSON).content(exampleDeviceJson)
        .contentType(MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());


  }


  @Test
  public void getDevices_When_Touch_Null_ScreenSize_Null() throws Exception
  {
    final List<MonitoringDevice> devices=new ArrayList<>();
    devices.add(new MonitoringDevice("GoldmanWay40E","touch",10));
    Mockito.doReturn(devices).when(service).findAll();
    final String answerJson = "{\"userAnswer\":[null,null]}";

    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/getDevices")
        .accept(MediaType.APPLICATION_JSON).content(answerJson)
        .contentType(MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.OK.value(), response.getStatus());


  }


  @Test
  public void getDevices_When_Touch_Null_ScreenSize_10() throws Exception
  {
    final List<MonitoringDevice> devices=new ArrayList<>();
    devices.add(new MonitoringDevice("GoldmanWay40E","touch",10));
    Mockito.doReturn(devices).when(service).findByUserChoiceByScreenSizeOnly(10);
    final String answerJson ="{\"userAnswer\":[null,\"10\"]}" ;

    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/getDevices")
        .accept(MediaType.APPLICATION_JSON).content(answerJson)
        .contentType(MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.OK.value(), response.getStatus());


  }


  @Test
  public void getDevices_When_Touch_touch_ScreenSize_Null() throws Exception
  {
    final List<MonitoringDevice> devices=new ArrayList<>();
    devices.add(new MonitoringDevice("GoldmanWay40E","touch",10));
    Mockito.doReturn(devices).when(service).findByUserChoiceByTouchOnly("touch");
    final String answerJson ="{\"userAnswer\":[\"touch\",null]}" ;

    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/getDevices")
        .accept(MediaType.APPLICATION_JSON).content(answerJson)
        .contentType(MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.OK.value(), response.getStatus());


  }


  @Test
  public void getDevices_When_Touch_touch_ScreenSize_10() throws Exception
  {
    final List<MonitoringDevice> devices=new ArrayList<>();
    devices.add(new MonitoringDevice("GoldmanWay40E","touch",10));
    Mockito.doReturn(devices).when(service).findByUserChoiceByBothTouchAndScreenSize("touch",10);
    final String answerJson ="{\"userAnswer\":[\"touch\",\"10\"]}" ;

    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/getDevices")
        .accept(MediaType.APPLICATION_JSON).content(answerJson)
        .contentType(MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.OK.value(), response.getStatus());


  }

  @Test
  public void getDevices_When_Touch_nontouch_ScreenSize_12_No_device_Present() throws Exception
  {
    final List<MonitoringDevice> devices=new ArrayList<>();
    Mockito.doReturn(devices).when(service).findByUserChoiceByBothTouchAndScreenSize("nontouch",12);
    final String answerJson ="{\"userAnswer\":[\"nontouch\",\"12\"]}" ;

    final RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/getDevices")
        .accept(MediaType.APPLICATION_JSON).content(answerJson)
        .contentType(MediaType.APPLICATION_JSON);

    final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    final MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());


  }

}
