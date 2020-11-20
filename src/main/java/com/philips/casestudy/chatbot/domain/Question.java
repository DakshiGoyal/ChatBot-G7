/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.domain;

import java.util.Map;

public class Question {
  int questionId;
  String questionString;
  Map<String,String> options;


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

  public Question(int questionId, String question, Map<String, String> options) {
    super();
    this.questionId = questionId;
    this.questionString = question;
    this.options = options;
  }

  public Question() {
  }
  @Override
  public String toString() {
    return "Question [question_id=" + questionId + ", question=" + questionString + ", options="
        + options + "]";
  }



}
