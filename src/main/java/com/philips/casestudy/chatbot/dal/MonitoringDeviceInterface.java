/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dal;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;

@Repository
@Transactional
public interface MonitoringDeviceInterface extends CrudRepository<MonitoringDevice, Integer>{

  @Modifying
  @Query(value="insert into MonitoringDevice(name,touch,screen_size)values(:name,:touch,:screenSize)", nativeQuery = true)
  int save(@Param("name") String name,@Param("touch") String touch,@Param("screenSize") float screenSize);

  @Override
  @Query("select device.name from MonitoringDevice as device")
  List<MonitoringDevice> findAll();

  @Query("select device.name from MonitoringDevice as device where device.touch LIKE ?1")
  public List<MonitoringDevice> findByUserChoiceOnlyTouch(String touch);

  @Query("select device.name from MonitoringDevice as device where device.screenSize=?1")
  public List<MonitoringDevice> findByUserChoiceOnlyScreenSize(float screenSize);

  @Query("select device.name from MonitoringDevice as device where device.screenSize=?2 AND device.touch LIKE ?1")
  public List<MonitoringDevice> findByUserChoiceByBothTouchAndScreenSize(String touch,float screenSize);

  @Query("select device.name from MonitoringDevice as device where device.screenSize=?3 AND device.touch LIKE ?2 AND device.name LIKE ?1")
  public List<MonitoringDevice> findByParameters(String name,String touch,float screenSize);



}
