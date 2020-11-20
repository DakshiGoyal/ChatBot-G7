/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;
import com.philips.casestudy.chatbot.dto.AnswerDTO;
import com.philips.casestudy.chatbot.dto.MonitoringDeviceDTO;
import com.philips.casestudy.chatbot.service.ChatBotServiceInterface;

@RestController
public class MonitoringDeviceController {

  @Autowired
  ChatBotServiceInterface service;


  @PostMapping("/api/devices")
  public ResponseEntity<String> addDevices (@RequestBody MonitoringDeviceDTO toBeSaved){
    final int deviceId=service.save(toBeSaved);

    if(deviceId!=0) {

      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    else
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }



  @PostMapping("/api/getDevices")
  public ResponseEntity<String> getDevices(@RequestBody AnswerDTO answerSet) throws IOException{
    final List<MonitoringDevice> devices;
    if(answerSet.getUserAnswer().get(0)==null && answerSet.getUserAnswer().get(1)==null )
    {
      devices=service.findAll();
    }

    else if(answerSet.getUserAnswer().get(0)==null && answerSet.getUserAnswer().get(1)!=null )
    {
      devices=service.findByUserChoiceByScreenSizeOnly(Float.parseFloat(answerSet.getUserAnswer().get(1)));
    }

    else if(answerSet.getUserAnswer().get(0)!=null && answerSet.getUserAnswer().get(1)==null )
    {
      devices=service.findByUserChoiceByTouchOnly(answerSet.getUserAnswer().get(0));
    }

    else
    {
      devices=service.findByUserChoiceByBothTouchAndScreenSize(answerSet.getUserAnswer().get(0),Float.parseFloat(answerSet.getUserAnswer().get(1)));
    }
    if(devices.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else
    {
      final String jsonData=listToJsonConversion(devices);

      return new ResponseEntity<>(jsonData,HttpStatus.OK);
    }
  }

  public String listToJsonConversion(List<MonitoringDevice> devices) throws IOException
  {
    final ByteArrayOutputStream out=new ByteArrayOutputStream();
    final ObjectMapper mapper=new ObjectMapper();
    mapper.writeValue(out, devices);
    final byte[] jsonData=out.toByteArray();
    return (new String(jsonData));

  }



}
