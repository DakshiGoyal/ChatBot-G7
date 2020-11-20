/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.domain;

import java.util.List;

public class Answer {
  List <String> userAnswer;

  public List<String> getUserAnswer() {
    return userAnswer;
  }

  public void setUserAnswer(List<String> userAnswer) {
    this.userAnswer = userAnswer;
  }

  public Answer(List<String> userAnswer) {
    super();
    this.userAnswer = userAnswer;
  }

  public Answer() {
    super();
  }







}
