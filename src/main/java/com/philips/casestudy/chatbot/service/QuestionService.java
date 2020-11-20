/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.casestudy.chatbot.dal.QuestionDAOInterface;
import com.philips.casestudy.chatbot.domain.Question;

@Service
public class QuestionService implements QuestionServiceInterface {

  QuestionDAOInterface quesDao;



  public QuestionDAOInterface getQuesDao() {
    return quesDao;
  }

  @Autowired
  public void setQuesDao(QuestionDAOInterface quesDao) {
    this.quesDao = quesDao;
  }


  @Override
  public Question returnQuestion(int index) throws IOException
  {
    final Question question=quesDao.getQuestionByIndex(index);
    if(question!=null) {
      return question;
    }
    else {
      return null;
    }
  }


  @Override
  public boolean getOptions(int index,String answer) throws IOException
  {
    final Map<String,String>options=quesDao.getOptions(index, answer);
    return validate(options,answer);
  }

  @Override
  public boolean validate(Map<String,String> options,String answer)
  {
    final List<String> checkOption=options.keySet().stream().filter(item->item.equals(answer)).collect(Collectors.toList());
    return checkOption.isEmpty();

  }

}
