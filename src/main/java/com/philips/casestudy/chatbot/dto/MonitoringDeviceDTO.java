/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dto;

import org.modelmapper.ModelMapper;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;

public class MonitoringDeviceDTO {
  String deviceName;
  String touch;
  float screenSize;
  int id;
  public MonitoringDeviceDTO()
  {

  }

  public MonitoringDeviceDTO(MonitoringDevice device)
  {
    this(device.getDeviceName(),device.getTouch(),device.getScreenSize());
  }

  public MonitoringDeviceDTO(String name,String touch,float screenSize)
  {
    this.deviceName=name;
    this.touch=touch;
    this.screenSize=screenSize;
  }

  public int getDeviceId() {
    return id;
  }

  public void setDeviceId(int id) {
    this.id = id;
  }

  public String getDeviceName() {
    return deviceName;
  }
  public void setDeviceName(String name) {
    this.deviceName = name;
  }
  public String getTouch() {
    return touch;
  }
  public void setTouch(String touch) {
    this.touch = touch;
  }
  public float getScreenSize() {
    return screenSize;
  }
  public void setScreenSize(float screenSize) {
    this.screenSize = screenSize;
  }

  public MonitoringDevice changeDTOToEntity(MonitoringDeviceDTO device)
  {
    final ModelMapper model=new ModelMapper();
    return model.map(device,MonitoringDevice.class);
  }

}
