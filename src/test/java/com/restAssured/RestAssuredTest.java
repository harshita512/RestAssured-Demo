package com.restAssured;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basePath;
import com.test.pojo.Datum;
import com.test.pojo.Support;
import com.test.pojo.Users;
import com.test.pojo.User;
import com.test.pojo.PutPojo;
import com.test.pojo.PatchPojo;

public class RestAssuredTest {
	
	
	
	@BeforeTest
	public void setup() {
	
		baseURI="https://reqres.in/";
		basePath ="api";
	}
	@Test
	public void getRequest() {
		Response response = given().queryParam("page", "2").when().get("/users/");
		System.out.println("response: " + response.asString());
		response.asPrettyString();
       }
	@Test
	public void PostRequest() {
		Response response = given().body("{\n"
				+ "    \"name\": \"morpheus\",\n"
				+ "    \"job\": \"leader\"\n"
				+ "}").when().post("/users");
		
		response.body().prettyPrint();
	}
	@Test
	public void deleteRequest() {
		given().when().delete("/users/2").then().statusCode(204);
		
	}
	@Test
	public void PutRequest() {
		Response response= given().body("{\n"
				+ "    \"name\": \"morpheus\",\n"
				+ "    \"job\": \"zion resident\"\n"
				+ "}").when().put("/users/2").then().statusCode(200).extract().response();
		System.out.println("status code is..."+response.statusCode());
		response.body().prettyPrint();
		
		
	}
	@Test
	public void Patchrequest() {
		Response response =given().body("{{\n"
				+ "    \"name\": \"morpheus\",\n"
				+ "    \"job\": \"zion resident\"\n"
				+ "}").when().patch("/users/2").then().statusCode(200).extract().response();
		System.out.println("Status code is ..." +response.statusCode());
		response.body().print();
		
	}
	@Test
	public void getFromPOJO() {
		Response response = given().queryParam("page", "2").when().get("/users/");
		System.out.println("response: \n" + response.asString());
		Users users = response.getBody().as(Users.class);
		System.out.println("Per Page is :"+users.getPerPage());
		System.out.println("Total::\n" + users.getTotal());
		Support support = users.getSupport();
		System.out.println("Support Text::\n" + support.getText());
		System.out.println("Support URL::\n" + support.getUrl());
		List<Datum> data = users.getData();
		System.out.println("Data Size::" + data.size());
		for (int i = 1; i < data.size(); i++) {
			System.out.println("index ::" + i);
			System.out.println("Id ::" + data.get(i).getId());
			if ((data.get(i).getId()) == 5) {
				System.out.println(data.get(i).getEmail());
				System.out.println(data.get(i).getFirstName());
				System.out.println(data.get(i).getLastName());
				break;
			}
		}
	}
		@Test
		
			public void postfromPojo() {
			
				User user = new User();
				String[] str = { "Manchu", "Tesfey", "Ritul" };
				for (int i = 0; i < str.length; i++) {
					System.out.println("Index " + i);
					user.setName(str[i]);
					user.setJob("QA");
					Response res = given().header("Content-Type", "application/json").body(user).when().post("/users").then()
							.statusCode(201).extract().response();
					User user1 = res.getBody().as(User.class);
					System.out.println("Id is ::" + user1.getId());
					res.body().prettyPrint();
				}
			}
		@Test
		public void putFromPojo() {
			
			PutPojo user = new PutPojo();
			String[] str = {"Name1"};
				
			for (int i = 0; i < str.length; i++) {
				System.out.println("Index " + i);
				
				
			
			
			
				user.setName(str[i]);
				
				user.setJob("QA");
				
				}
				
				Response res = given().header("Content-Type", "application/json").body(user).when().put("/users/2").then()
						.statusCode(200).extract().response();
				PutPojo user1 = res.getBody().as(PutPojo.class);
				
				
				res.body().prettyPrint();
			}
		@Test
		public void patchPojo() {
			User user = new User();
			String[] str = { "name1", "name2", "name3" };
			for (int i = 0; i < str.length; i++) {
				System.out.println("Index " + i);
				user.setName(str[i]);
				user.setJob("QA");
				Response res = given().header("Content-Type", "application/json").body(user).when().patch("/users/2").then()
						.statusCode(200).extract().response();
				User user1 = res.getBody().as(User.class);
				System.out.println("Id is ::" + user1.getId());
				res.body().prettyPrint();
			
		}
			
			
		
		}
		@Test
		public void deletePojo() {
			
		}
		
		
		
		
		
		
		
		
		
}
		

				
				
				
				
				
				
			
			
			
		
		
			
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


