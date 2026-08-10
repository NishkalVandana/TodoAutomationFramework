package ApiTesting;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import utils.ConfigReader;
public class ApiLogin{
    @DataProvider(name = "taskData")
    public Object[][] getTaskData() {
        return new Object[][] {
                {"Drink Water", "low"},
                {"Read Book", "medium"},
                {"Finish Report", "high"}
        };
    }
    String token;
    String baseUrl=ConfigReader.getProperty("backendurl");
    List<Integer> createdTaskIds = new ArrayList<>();
    @Test
    public void loginTest(){
        Response response=given().contentType("application/json").
                body("{\"email\": \"user1@gmail.com\",\"password\": \"user1@1234\"}").
                when().post(baseUrl+"/api/login").then()
                .statusCode(200).extract().response();
        token=response.jsonPath().getString("access_token");
        System.out.println("Token:"+token);
    }
   @Test(dependsOnMethods = "loginTest",dataProvider = "taskData",priority = 1)
   public void postTasks(String task,String priority) {
       //post a task
       Response response=given().header("Authorization", "Bearer " + token)
               .contentType("application/json")
               .body("{\"task\":\"" + task + "\",\"priority\":\"" + priority + "\"}")
               .when().post(baseUrl + "/api/tasks")
               .then().log().ifValidationFails().statusCode(201).extract().response();
       int taskid=response.jsonPath().getInt("id");
       createdTaskIds.add(taskid);
   }
   @Test(dependsOnMethods = "loginTest",priority = 2)
   public void getTasks() {
       //printing Tasks Through Get
       System.out.println("All tasks before update and delete:");
       Response response1 = given().header("Authorization", "Bearer " + token)
               .when()
               .get(baseUrl + "/api/tasks")
               .then().log().ifValidationFails().statusCode(200)
               .extract().response();
       System.out.println(response1.asPrettyString());
   }
   @Test(dependsOnMethods = {"loginTest", "postTasks"},priority = 3)
   public void updateAndDelete() {
       //updating the task through put
       int taskid1=createdTaskIds.get(0);
       given().contentType("application/json")
               .header("Authorization", "Bearer " + token)
               .body("{\"task\":\"Walk 1 km\",\"priority\":\"low\"}")
               .when().put(baseUrl + "/api/tasks/"+taskid1)
               .then().statusCode(200);
       System.out.println("Task is updated");
       //delete the task
       int taskid2=createdTaskIds.get(1);
       given()
               .header("Authorization", "Bearer " + token)
               .when().delete(baseUrl + "/api/tasks/"+taskid2)
               .then().log().ifValidationFails().statusCode(200).log().body();
        System.out.println("Tasks after update and delte:");
        given().header("Authorization","Bearer "+token)
                .when().get(baseUrl+"/api/tasks")
                .then().statusCode(200).log().body();
    }
}
