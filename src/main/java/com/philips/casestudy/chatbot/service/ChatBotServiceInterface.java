/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;
import com.philips.casestudy.chatbot.dto.MonitoringDeviceDTO;
import com.philips.casestudy.chatbot.dto.UserInfoDTO;

@Service
public interface ChatBotServiceInterface {

  int save(MonitoringDeviceDTO device);

  List<MonitoringDevice> findAll();

  int saveUsers(UserInfoDTO user);

  public List<MonitoringDevice> findByUserChoiceByBothTouchAndScreenSize(String touch, float screenSize) ;

  public List<MonitoringDevice> findByUserChoiceByTouchOnly(String touch);

  public List<MonitoringDevice> findByUserChoiceByScreenSizeOnly(float screenSize);

}
