/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import com.philips.casestudy.chatbot.domain.Question;

public class QuestionDAOTest {

  @Test
  public void getRequiredQuestion_When_Question_Present() throws IOException
  {

    final QuestionDAOInterface dao=new QuestionDAO();

    final Question que=new Question();
    que.setQuestionId(1);
    que.setQuestionString("Select anyone of the following options for screen type:");
    final Map<String, String> options = Stream.of(new String[][] {
      { "1", "touch" },
      { "2", "nontouch" },
      { "3", "does"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    que.setOptions(options);
    final List<Question> questionsList=new ArrayList<>();
    questionsList.add(que);


    final Question receviedQue=dao.getRequiredQuestion(questionsList,que.getQuestionId());
    assertNotNull(receviedQue);


  }


  @Test
  public void getRequiredQuestion_When_Question_Not_Present() throws IOException
  {
    final QuestionDAOInterface dao=new QuestionDAO();
    final Question que=new Question();
    que.setQuestionId(2);
    que.setQuestionString("Select anyone of the following options for screen type:");
    final Map<String, String> options = Stream.of(new String[][] {
      { "1", "touch" },
      { "2", "nontouch" },
      { "3", "does"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    que.setOptions(options);
    final List<Question> questionsList=new ArrayList<>();
    questionsList.add(que);


    final Question receviedQue=dao.getRequiredQuestion(questionsList,1);
    assertNull(receviedQue);



  }


  @Test
  public void readJSONForQuestions_When_Path_Is_Correct_Read_Success() throws IOException {

    final QuestionDAOInterface dummyQuestionDAO=new QuestionDAO();
    final String path="src/main/resources/question.txt";
    final byte[] file=dummyQuestionDAO.readJSONForQuestions(path);
    assertTrue(file.length>0);

  }





}
