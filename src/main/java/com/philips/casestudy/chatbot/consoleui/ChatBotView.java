/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */

package com.philips.casestudy.chatbot.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.casestudy.chatbot.domain.Question;
import com.philips.casestudy.chatbot.dto.AnswerDTO;
import com.philips.casestudy.chatbot.dto.UserInfoDTO;

public class ChatBotView  {

  static List<String> userAnswers=new ArrayList<>();
  static Scanner sc = new Scanner(System.in);
  static boolean checkIfNameCorrect=false;
  static boolean checkIfContactCorrect=false;
  static boolean checkIfEmailCorrect=false;
  static boolean checkIfCityCorrect=false;

  static UserInfoDTO user=new UserInfoDTO();



  public static Logger returnLogger() {
    return Logger.getLogger(ChatBotView.class.getName());

  }

  public static void printError(String error) {
    final Logger logger=returnLogger();
    logger.log(Level.WARNING,error);
  }

  public static void printInfo(String info) {
    final Logger logger=returnLogger();
    logger.log(Level.INFO,info);
  }

  public static void printSevereWarning(String severeWarning) {
    final Logger logger=returnLogger();
    logger.log(Level.SEVERE,severeWarning);
  }

  public static String fromKB(String question) {
    printInfo(question);
    return sc.nextLine();
  }

  public static void getUserDetails() {

    printInfo("Please Provide following information");
    gettingName(user);
    gettingcontact(user);
    gettingEmail(user);
    gettingCity(user);

  }

  public static int askQuestion(int index) {
    int responseCode=400;
    try {
      final URL urlForGetRequest = new URL("http://localhost:8080/api/questions/" + index);
      String readLine = null;
      final HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
      conection.setRequestMethod("GET");
      responseCode = conection.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {

        final BufferedReader in =
            new BufferedReader(new InputStreamReader(conection.getInputStream()));
        final StringBuilder response = new StringBuilder();
        while ((readLine = in.readLine()) != null) {
          response.append(readLine);
        }
        in.close();


        final ObjectMapper objectMapper =new ObjectMapper();
        final Question question=objectMapper.readValue(response.toString(), Question.class);
        final String answer=fromKB(question.getQuestionString()+"\n"+question.getOptions());
        final boolean IfAnswer=validate(question.getQuestionId(),answer);

        if(!IfAnswer) {
          askQuestion(index);
        }

      }

    } catch (final Exception e) {
      printSevereWarning(e.toString());
    }
    return responseCode;

  }

  public static boolean validate(int questionId,String answer) throws IOException
  {

    try {
      final URL urlForGetRequest = new URL("http://localhost:8080/api/questions/" + questionId+"/"+answer);
      final HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
      conection.setRequestMethod("GET");
      final int responseCode = conection.getResponseCode();


      if (responseCode == HttpURLConnection.HTTP_OK) {
        userAnswers.add(answer);
        return true;
      }
      else {
        return false;
      }
    } catch (final Exception e) {
      printSevereWarning(e.toString());
      return false;
    }


  }

  public static void startChatBot() {
    try {
      userAnswers.clear();

      getUserDetails();
      final String json=new ObjectMapper().writeValueAsString(user);

      final URL obj = new URL("http://localhost:8080/api/users");
      final HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
      postConnection.setRequestMethod("POST");
      postConnection.setRequestProperty("Content-Type","application/json");
      postConnection.setDoOutput(true); final OutputStream os =postConnection.getOutputStream();
      os.write(json.getBytes());
      os.flush();
      os.close();
      final int responseCode = postConnection.getResponseCode();
      if(responseCode == HttpURLConnection.HTTP_CREATED) { //success
        printInfo("Thank You for registering with us! ");
      }
      else {
        printInfo("You have already registered! Please Continue");
      }


    }catch (final Exception e) { printSevereWarning(e.toString()); }

  }
  public static void terminateChatBot()
  {
    userAnswers.clear();
    askQuestion(0);
    switch(userAnswers.get(0))
    {
      case "1":
        printInfo("Thank you");
        break;
      case "2":
        startQuestion();
        mapUserInputToParametersOfDevicesTouch();
        mapUserInputToParametersOfDevicesScreenSize();
        final AnswerDTO answerSet=new AnswerDTO(userAnswers);
        getDevices(answerSet);
        break;

      default:
        break;
    }

  }

  public static void startQuestion() {
    userAnswers.clear();

    int index = 0;
    int responseCode = 0;
    do {
      index++;
      responseCode = askQuestion(index);
    } while (responseCode != 400);

  }

  public static void mapUserInputToParametersOfDevicesTouch()
  {

    switch(userAnswers.get(0))
    {
      case "1":
        userAnswers.set(0,"touch");
        break;
      case "2":
        userAnswers.set(0,"nontouch");
        break;
      case "3":
        userAnswers.set(0,null);
        break;
      default:
        break;

    }
  }

  public static void mapUserInputToParametersOfDevicesScreenSize()
  {
    switch(userAnswers.get(1))
    {
      case "1":
        userAnswers.set(1,null);
        break;
      case "9":
        userAnswers.set(1,"9");
        break;
      case "10":
        userAnswers.set(1,"10");
        break;
      case "12":
        userAnswers.set(1,"12");
        break;
      case "14":
        userAnswers.set(1,"14");
        break;
      case "15":
        userAnswers.set(1,"15");
        break;
      default:
        break;
    }
  }



  public static void getDevices(AnswerDTO answerSet) {

    try {
      final String json=new ObjectMapper().writeValueAsString(answerSet);
      final URL obj = new URL("http://localhost:8080/api/getDevices");
      final HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
      postConnection.setRequestMethod("POST");
      postConnection.setRequestProperty("Content-Type","application/json");
      postConnection.setDoOutput(true);
      final OutputStream os =postConnection.getOutputStream();
      os.write(json.getBytes());
      os.flush();
      os.close();
      final int responseCode = postConnection.getResponseCode();
      if(responseCode == HttpURLConnection.HTTP_OK) { //success
        final BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
        String inputLine;
        final StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        printInfo(response.toString());
        in .close();
      }
      else {
        printInfo("This device is not present try again");

      }

      terminateChatBot();

    }catch (final Exception e) {printSevereWarning(e.toString()); }

  }



  public static void main(String []args)  {

    startChatBot();
    startQuestion();
    mapUserInputToParametersOfDevicesTouch();
    mapUserInputToParametersOfDevicesScreenSize();
    final AnswerDTO answerSet=new AnswerDTO(userAnswers);
    getDevices(answerSet);

  }

  public static boolean checkingNameField(String name)
  {
    return name.matches("^[A-Z][a-z]*$");
  }
  public static boolean checkingContactField(String contact)
  {
    return contact.matches("^[0-9]{10}$");
  }
  public static boolean checkingEmailField(String email)
  {
    return email.matches("^[A-Za-z0-9]*@(gmail|yahoo).com$");
  }
  public static boolean checkingCityField(String city)
  {
    return city.matches("^[A-Za-z]*$");
  }

  public static void gettingName(UserInfoDTO user)
  {
    while(!checkIfNameCorrect)
    {
      final String name = fromKB("May I Know your name ");
      if(checkingNameField(name))
      {
        checkIfNameCorrect=true;
        user.setUserName(name);
        break;
      }
      else
      {
        printError("This field takes only alphabets and start with captial letter only ");
      }

    }

  }
  public static void gettingcontact(UserInfoDTO user)
  {
    while(!checkIfContactCorrect)
    { final String contactNo = fromKB("your contact number ");
    if(checkingContactField(contactNo))
    {
      checkIfContactCorrect=true;
      user.setcontactno(new BigInteger(contactNo));
    } else {
      printError("This need only digits till length 10");
    }
    }

  }
  public static void gettingEmail(UserInfoDTO user)
  {

    while(!checkIfEmailCorrect)
    {
      final String email = fromKB("your email id ");
      if(checkingEmailField(email))
      {
        checkIfEmailCorrect=true;
        user.setEmail(email);
      } else {
        printError("It must end with @gmail.com or @yahoo.com");
      }
    }
  }
  public static void gettingCity(UserInfoDTO user)
  {
    while(!checkIfCityCorrect)
    {
      final String city = fromKB("Your city ");
      if(checkingCityField(city))
      {
        checkIfCityCorrect=true;
        user.setCity(city);
      } else {
        printError("Only alphabets");
      }
    }
  }

}