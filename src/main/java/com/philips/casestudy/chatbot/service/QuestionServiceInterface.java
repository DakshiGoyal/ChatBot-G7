/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.service;

import java.io.IOException;
import java.util.Map;
import com.philips.casestudy.chatbot.domain.Question;

public interface QuestionServiceInterface {

  Question returnQuestion(int index) throws IOException;

  public boolean getOptions(int index,String answer) throws IOException;

  public boolean validate(Map<String,String> options,String answer);


}
