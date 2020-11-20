/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dal;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.philips.casestudy.chatbot.domain.Question;


public interface QuestionDAOInterface {

  Question getQuestionByIndex(int index) throws IOException;

  Question getRequiredQuestion(List<Question> questionList,int index);

  public Map<String,String> getOptions(int index,String answer) throws IOException;

  public byte[] readJSONForQuestions(String path) throws IOException;

}
