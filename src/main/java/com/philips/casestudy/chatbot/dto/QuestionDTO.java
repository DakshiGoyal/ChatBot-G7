/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dto;

import java.util.Map;
import org.modelmapper.ModelMapper;
import com.philips.casestudy.chatbot.domain.Question;

public class QuestionDTO {
  int questionId;
  String questionString;
  Map<String,String> options;

  public QuestionDTO(Question que)
  {
    this(que.getQuestionId(),que.getQuestionString(),que.getOptions());
  }

  public QuestionDTO(int questionId,String questionString,Map<String, String> options)
  {
    this.questionId=questionId;
    this.questionString=questionString;
    this.options=options;
  }
  public int getQuestionId() {
    return questionId;
  }
  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }
  public String getQuestionString() {
    return questionString;
  }
  public void setQuestionString(String questionString) {
    this.questionString = questionString;
  }
  public Map<String, String> getOptions() {
    return options;
  }
  public void setOptions(Map<String, String> options) {
    this.options = options;
  }

  public Question changeDTOToEntity(QuestionDTO que)
  {
    final ModelMapper model=new ModelMapper();
    return model.map(que,Question.class);

  }
}
