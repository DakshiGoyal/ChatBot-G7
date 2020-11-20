/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.service;

import static org.junit.Assert.assertEquals;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import com.philips.casestudy.chatbot.dal.MonitoringDeviceInterface;
import com.philips.casestudy.chatbot.dal.UserInfoInterface;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;
import com.philips.casestudy.chatbot.domain.UserInfo;
import com.philips.casestudy.chatbot.dto.MonitoringDeviceDTO;
import com.philips.casestudy.chatbot.dto.UserInfoDTO;

public class ChatBotServiceTest {

  @Test
  public void save_Monitoring_Device_When_Same_Parameters_Not_Present(){

    final ChatbotService service=new ChatbotService();

    final MonitoringDeviceInterface mockDao=Mockito.mock(MonitoringDeviceInterface.class);

    service.setMonitoringDAO(mockDao);

    final MonitoringDeviceDTO mockDeviceDto=new MonitoringDeviceDTO("GoldwayMan40E","touch",10);

    final List<MonitoringDevice> devices=new ArrayList<>();

    Mockito.when(mockDao.findByParameters(mockDeviceDto.getDeviceName(), mockDeviceDto.getTouch(), mockDeviceDto.getScreenSize())).thenReturn(devices);
    Mockito.when(mockDao.save(mockDeviceDto.getDeviceName(), mockDeviceDto.getTouch(), mockDeviceDto.getScreenSize())).thenReturn(1);

    final int id=service.save(mockDeviceDto);

    assertEquals(1,id);

  }

  @Test
  public void save_Monitoring_Device_When_Same_Parameters_Present(){
    final ChatbotService service=new ChatbotService();

    final MonitoringDeviceInterface mockDao=Mockito.mock(MonitoringDeviceInterface.class);

    service.setMonitoringDAO(mockDao);

    final MonitoringDeviceDTO mockDeviceDto=new MonitoringDeviceDTO("GoldwayMan40E","touch",10);

    final MonitoringDevice mockDevice=new MonitoringDevice("GoldwayMan40E","touch",10);

    final List<MonitoringDevice> devices=new ArrayList<>();
    devices.add(mockDevice);

    Mockito.when(mockDao.findByParameters(mockDeviceDto.getDeviceName(), mockDeviceDto.getTouch(), mockDeviceDto.getScreenSize())).thenReturn(devices);

    final int id=service.save(mockDeviceDto);

    assertEquals(mockDeviceDto.getDeviceId(),id);

  }

  @Test
  public void saveUsers_When_Existing_Contact_And_Existing_Email(){

    final ChatbotService service=new ChatbotService();

    final UserInfoInterface mockDao=Mockito.mock(UserInfoInterface.class);

    service.setUserInfoDAO(mockDao);

    final UserInfoDTO mockUserDto=new UserInfoDTO("Abc",new BigInteger("1122334455"),"abc@gmail.com","abc");

    final UserInfo mockUser=new UserInfo("Abc",new BigInteger("1122334455"),"abc@gmail.com","abc");
    mockUser.setUserId(1);

    final List<UserInfo> users=new ArrayList<>();
    users.add(mockUser);

    Mockito.when(mockDao.findByContactno(new BigInteger("1122334455"))).thenReturn(users);
    Mockito.when(mockDao.findByEmail(Mockito.anyString())).thenReturn(users);
    final int id=service.saveUsers(mockUserDto);
    assertEquals(mockUserDto.getUserId(),id);
  }


  @Test
  public void saveUsers_When_Non_Existing_Contact_Or_Non_Existing_Email(){

    final ChatbotService service=new ChatbotService();

    final UserInfoInterface mockDao=Mockito.mock(UserInfoInterface.class);

    service.setUserInfoDAO(mockDao);

    final UserInfoDTO mockUserDto=new UserInfoDTO("Abc",new BigInteger("1122334455"),"abc@gmail.com","abc");

    final List<UserInfo> users=new ArrayList<>();

    Mockito.when(mockDao.findByContactno(BigInteger.valueOf(Mockito.anyLong()))).thenReturn(users);
    Mockito.when(mockDao.findByEmail(Mockito.anyString())).thenReturn(users);
    Mockito.when(mockDao.save(Mockito.anyString(),BigInteger.valueOf(Mockito.anyLong()), Mockito.anyString(), Mockito.anyString())).thenReturn(1);
    final int id=service.saveUsers(mockUserDto);
    assertEquals(mockUserDto.getUserId(),id);

  }




}






