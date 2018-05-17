package com.setcce;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.isA;

import org.jooby.test.JoobyRule;
import org.junit.ClassRule;
import org.junit.Test;

public class AppTest {

  @ClassRule
  public static JoobyRule app = new JoobyRule(new App());

  @Test
  public void integrationTest() {
    get("/user/1").then().assertThat().body(isA(String.class)).statusCode(200).contentType("text/html;charset=UTF-8");
  }

}
