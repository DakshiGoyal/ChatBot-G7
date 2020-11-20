/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;

public class MonitoringDeviceDTOTest {

  @Test
  public void when_Convert_MonitoringDto_To_MonitoringEntity_thenCorrect() {

    final MonitoringDeviceDTO monitoringDto = new MonitoringDeviceDTO();

    monitoringDto.setDeviceName("GoldwayMan40E");
    monitoringDto.setTouch("touch");
    monitoringDto.setScreenSize(10);
    final MonitoringDevice saveddevice=monitoringDto.changeDTOToEntity(monitoringDto);

    assertTrue(saveddevice.getDeviceName().equals(monitoringDto.getDeviceName()));
    assertTrue(saveddevice.getTouch().equals(monitoringDto.getTouch()));
    assertEquals(saveddevice.getScreenSize(),monitoringDto.getScreenSize(),0.00);



  }

}
