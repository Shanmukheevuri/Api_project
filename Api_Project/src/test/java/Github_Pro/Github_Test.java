package Github_Pro;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import java.util.Base64;

import org.json.simple.JSONObject;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Github_Test {
	String baseurl="https://api.github.com";
	String content_sha;
    String update_content_sha;
    int autolink_id;

     @Test(priority = 1)
    public void Create_a_repository() {
    	 
        JSONObject jsn =new JSONObject();
        jsn.put("name", "Hello-World");
                
        given().baseUri(baseurl)//given().baseUri(baseurl)
        .header("Authorization","Bearer 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")
        .header("Content-Type","application/json")
        .body(jsn)
        .when()
        .post("/user/repos")
        .then()
        .statusCode(201)
        .log()
        .all();
    }
        @Test(priority = 2)
        public void Update_repos() {
        	JSONObject jsn =new JSONObject();
        	 jsn.put("name", "Hello-World");
        	 
        	given().baseUri(baseurl)
            .header("Authorization","Bearer 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")
            .header("Content-Type","application/json")
            .body(jsn)
            .when()
            .patch("/repos/Shanmukheevuri/Hello-World")
            .then()
            .statusCode(200)
            .log()
            .all();
    }
        @Test(priority = 3)
        public void Delete_repos() {
        	
        	 given().baseUri(baseurl)
            .header("Authorization","Bearer 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")
            .header("Content-Type","application/json")
            .when()
            .delete("/repos/Shanmukheevuri/Shannu")
            .then()
            .statusCode(204)
            .log()
            .all();
        }
        @Test(priority = 4)
        public void Get_repos() {
        	   given().baseUri(baseurl)
        	   .header("Authorization","Bearer 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")
               .header("Content-Type","application/json")
              .when()
              .get("/repos/Shanmukheevuri/AppiumFramework")
              .then()
              .statusCode(200)
              .log()
              .all();
        }
        @Test(priority = 5)
        public void Create_a_Fork() {
        	given().baseUri(baseurl)
       	    .header("Authorization","Bearer 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")
            .header("Content-Type","application/json")
            .when()
            .post("/repos/kanwarchalotra/AppiumFramework/forks")
            .then()
            .statusCode(202)
            .log()
            .all();
        }
        @Test(priority = 6)
 public void List_Forks () {
        	
	 given().baseUri(baseurl)
	 .header("Authorization","Bearer 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")
     .header("Content-Type","application/json")
     .when()
     .get("/repos/kanwarchalotra/AppiumFramework/forks")
     .then()
     .statusCode(200)
     .log()
     .all();
        }
        @Test(priority = 7)
 public void List_repos_for_a_User() {
	 given().baseUri(baseurl)
	 .header("Authorization","Bearer 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")
     .header("Content-Type","application/json")
     .when()
     .get("/users/repos")
     .then()
     .statusCode(200)
     .log()
     .all();
 }
        @Test(priority = 8)
 public void List_Repos_Language() {
	 given().baseUri(baseurl)
	 .header("Authorization","Bearer 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")
     .header("Content-Type","application/json")
     .when()
     .get("/repos/Shanmukheevuri/AppiumFramework/languages")
     .then()
     .statusCode(200)
     .log()
     .all();
 }
        @Test(priority = 9)
 public void List_public_Repos() {
	 given().baseUri(baseurl)
	 .header("Authorization","Bearer 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")
     .header("Content-Type","application/json")
     .when()
     .get("/repositories")
     .then()
     .statusCode(200)
     .log()
     .all();
 }
        @Test(priority = 10)
 public void Create_file_Content() {
        	 String content = "bXkgbmV3IGZpbGUgY29dGVudHM=";
             String base64Content = Base64.getEncoder().encodeToString(content.getBytes());


             JSONObject jsonObject = new JSONObject();
             jsonObject.put("message", "my commit message");
             JSONObject committer = new JSONObject();
             committer.put("name", "Monalisa Octocat");
             committer.put("email", "octocat@github.com");
             jsonObject.put("committer", committer);
             jsonObject.put("content", base64Content);    
             
             Response resp = given().baseUri(baseurl)
                     .header("Authorization", "Bearer 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")
                     .contentType("application/x-www-form-urlencoded")
                     .body(jsonObject.toJSONString()).when()
                     .put("/repos/Shanmukheevuri/AppiumFramework/contents/open12345.txt")
                     .then().log().all().extract().response();
             
             System.out.println("sha for creating a file ::" + resp.path("content.sha"));
             content_sha = resp.path("content.sha").toString();
         }    
//	 JSONObject jsn =new JSONObject();	 
//     jsn.put("message", "my commit message");
//     JSONObject committer =new JSONObject();
//     committer.put("name", "Monalisa Octocat");
//     committer.put("email", "octocat@github.com");
//     jsn.put("committer", committer);
//     jsn.put("content", "bXkgbmV3IGZpbGUgY29udGVudHM=");
//     
//	  
//	 Response rsp = given().baseUri(baseurl)
//	 .header("Authorization","Bearer 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")
//     .header("Content-Type","application/json")
//     .body(jsn)
//     .when()
//     .put("/repos/Shanmukheevuri/AppiumFramework/contents/open12345.txt")
//     .then()
//     .statusCode(201)
//     .log()
//     .all();
//	 .contentType("application/json")
//     .body(jsn.toJSONString()).when()
//     .put("/repos/Shanmukheevuri/AppiumFramework/contents/open12345.txt").then().log().all().extract().response();
//
//     System.out.println("sha for creating a file ::" + rsp.path("content.sha"));
//     content_sha = rsp.path("content.sha").toString();
        @Test(priority = 11)
        public void update_a_file_content() {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "a new commit message");
            JSONObject committer = new JSONObject();
            committer.put("name", "Monalisa Octocat");
            committer.put("email", "octocat@github.com");
            jsonObject.put("committer", committer);
            jsonObject.put("content", "bXkgdXBkYXRlZCBmaWxlIGNvbnRlbnRz");
            System.out.println("content_sha"+content_sha);
            jsonObject.put("sha", content_sha);

            Response resp = given().baseUri(baseurl)
                    .header("Authorization", "Bearer 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")
                    .contentType(ContentType.JSON).body(jsonObject.toJSONString()).when()
                    .put("/repos/Shanmukheevuri/AppiumFramework/contents/open12345.txt")
                    .then().log().all().extract().response();

   
            System.out.println("sha path for updating a file ::" + resp.path("content.sha"));
            update_content_sha = resp.path("content.sha").toString();
        }

 @Test(priority = 12)
 public void delete_a_file_content() {
     JSONObject jsonObject = new JSONObject();

     jsonObject.put("message", "a new commit message");

     JSONObject committer = new JSONObject();
     committer.put("name", "Monalisa Octocat");
     committer.put("email", "octocat@github.com");

     jsonObject.put("committer", committer);

     jsonObject.put("content", "bXkgdXBkYXRlZCBmaWxlIGNvbnRlbnRz");
     
     System.out.println("updated content shar"+update_content_sha);
     jsonObject.put("sha", update_content_sha);

     given().baseUri(baseurl).header("Authorization", "Bearer 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")
             .contentType(ContentType.JSON).body(jsonObject.toJSONString()).when()
             .delete("/repos/Shanmukheevuri/AppiumFramework/contents/open12345.txt").then().log().all();

 }
 @Test(priority = 13)
 public void List_Repos_Tags() {
	 given().baseUri(baseurl)
	 .header("Authorization","Bearer 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")
     .header("Content-Type","application/json")
     .when()
     .get("/repos/Shanmukheevuri/Shannu/tags")
     .then()
     .statusCode(200)
     .log()
     .all();
 }
 @Test(priority = 14)
 public void Create_an_Autolink_reference_for_repos() {
	 given().baseUri(baseurl)
	 .header("Authorization","Bearer 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")
     .header("Content-Type","application/json")
     .when()
     .get("/repos/Shanmukheevuri/Shannu/autolinks/1883298")
     .then()
     .statusCode(200)
     .log()
     .all();
 }
 @Test(priority = 15)
 public void Get_all_repos_topic() {
	      given().baseUri(baseurl)
		 .header("Authorization","Bearer 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")
	     .header("Content-Type","application/json")
	     .when()
	     .get("/repos/Shanmukheevuri/Shannu/topics")
	     .then()
	     .statusCode(200)
	     .log()
	     .all();
 }
 @Test(priority = 16)
 public void Get_an_autolink_reference_for_repos() {
	  given().baseUri(baseurl)
	 .header("Authorization","Bearer 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")
     .header("Content-Type","application/json")
     .when()
     .get("/repos/Shanmukheevuri/Shannu/autolinks/1883298")
     .then()
     .statusCode(200)
     .log()
     .all();
 }
 @Test(priority = 17)
 public void Delete_from_an_autolink_reference_for_repos() {
	 given().baseUri(baseurl)
	 .header("Authorization","Bearer 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")
     .header("Content-Type","application/json")
     .when()
     .delete("/repos/Shanmukheevuri/Shannu/autolinks/1883298")
     .then()
     .statusCode(204)
     .log()
     .all();
 	
 } @Test(priority = 18)
 public void Replace_all_repos_topic() {
	 JSONObject jsn =new JSONObject();
	 jsn.put("name", "Shannu");
     
     given().baseUri(baseurl)
     .header("Authorization","Bearer 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")
     .header("Content-Type","application/json")
    .body(jsn)
    .when()
    .get("/repos/Shanmukheevuri/Shannu/topics")
    .then()
    .statusCode(200)
    .log()
    .all();
 }
 
}
