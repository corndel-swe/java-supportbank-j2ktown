package com.corndel.supportbank.exercises;

// import kong.unirest.Unirest;

import kong.unirest.Unirest;

/**
 * This class represents a Message to be sent to the Postman Echo API.
 * You don't need to modify it.
 */
class Message {
  public int id;
  public String content;

  public Message(int id, String content) {
    this.id = id;
    this.content = content;
  }
}

public class Postman {
  /**
   * Echoes a message with a given id and content to the Postman Echo API, and
   * returns the response as a string.
   *
   * @param id      The id of the message
   * @param content The content of the message
   *
   * @return The response body from the Postman Echo API
   */
  public static String echoMessage(int id, String content) {
    // TODO: Create a Message object with the given id and content
    Message message = new Message(id, content);
    String url = "https://postman-echo.com/post";

    // TODO: Post the Message object to the Postman Echo API
    // Hint: Use Unirest.post()
    var response = Unirest.post(url)
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .body(message) // convert to json and send to API
            .asString(); // get response as a string
    // TODO: Return the response body as a string of JSON
    String json = response.getBody(); // convert into a string of json
    return json;
  }

  /**
   * For debugging purposes, prints the response of the Postman Echo API to
   * `echoMessage(1, "hello")`.
   */
  public static void main(String[] args) {
    String response = echoMessage(1, "hello");
    System.out.println(response);
  }
}
