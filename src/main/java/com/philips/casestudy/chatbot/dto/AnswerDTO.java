/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dto;

import java.util.List;
import org.modelmapper.ModelMapper;
import com.philips.casestudy.chatbot.domain.Answer;


public class AnswerDTO {
  List <String> userAnswer;

  public List<String> getUserAnswer() {
    return userAnswer;
  }

  public void setUserAnswer(List<String> userAnswer) {
    this.userAnswer = userAnswer;
  }

  public AnswerDTO(Answer ans)
  {
    this(ans.getUserAnswer());
  }

  public AnswerDTO(List<String> answ)
  {
    this.userAnswer=answ;
  }

  public Answer changeDTOToEntity(AnswerDTO ans)
  {
    final ModelMapper model=new ModelMapper();
    return model.map(ans,Answer.class);

  }

  public AnswerDTO() {
    super();
  }
}
