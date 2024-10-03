package com.corndel.supportbank.exercises;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;

/**
 * This class represents a Pokemon. It uses Java's record syntax to
 * automatically generate a class with getters and utility methods.
 * See this url for more info:
 * https://www.baeldung.com/java-record-keyword
 */
@JsonIgnoreProperties(ignoreUnknown = true)
record Pokemon(Integer id, String name, Integer height, Integer weight) {
}

public class PokeAPI {
  /**
   * Get a Pokemon by its name.
   *
   * Makes a GET request to the PokeAPI, and uses Jackson to parse the JSON
   * response into a Pokemon object.
   *
   * @param name The name of the Pokemon to retrieve.
   * @return The Pokemon object.
   */
  public static Pokemon getPokemonByName(String name) throws Exception {
    // TODO: Create the url by appending the name to the base url
    String url = "https://pokeapi.co/api/v2/pokemon/" + name;
    // TODO: Make a GET request to the url
    // Hint: Use Unirest.get()
    var response = Unirest
            .get(url)
            .header("Accept","application/json")
            .asString();

    String json = response.getBody();
    //System.out.println(json);

    // TODO: Parse the response body into a Pokemon object
    // Hint: Use Jackson's ObjectMapper to map the response body to Pokemon.class
    ObjectMapper mapper = new ObjectMapper();
    Pokemon pokemon = mapper.readValue(json, Pokemon.class);
    System.out.println(pokemon);

    // TODO: Return the Pokemon
    return pokemon;
  }

  /**
   * For debugging purposes..
   */
  public static void main(String[] args) {
    try {
      Pokemon pokemon = getPokemonByName("pikachu");
      System.out.println(pokemon);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
