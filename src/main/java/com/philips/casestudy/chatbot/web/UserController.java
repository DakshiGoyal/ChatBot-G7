/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy.chatbot.dto.UserInfoDTO;
import com.philips.casestudy.chatbot.service.ChatBotServiceInterface;

@RestController
public class UserController {

  @Autowired
  ChatBotServiceInterface service;

  @PostMapping("/api/users")
  public ResponseEntity<String> addUsers(@RequestBody UserInfoDTO toBeSaved){
    final int id=service.saveUsers(toBeSaved);
    if(id!=0) {
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    else
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}





