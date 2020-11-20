/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="monitoringdevice")
public class MonitoringDevice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  @Column(name="name")
  String name;
  @Column(name="touch")
  String touch;
  @Column(name = "screen_size")
  float screenSize;
  public MonitoringDevice() {
    super();
  }
  public int getDeviceId() {
    return id;
  }

  public void setDeviceId(int id) {
    this.id = id;
  }
  public MonitoringDevice(String name, String touch, float screenSize) {
    super();
    this.name = name;
    this.touch = touch;
    this.screenSize = screenSize;
  }
  public String getDeviceName() {
    return name;
  }
  public void setDeviceName(String name) {
    this.name = name;
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




}
