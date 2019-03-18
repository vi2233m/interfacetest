package contact;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import config.Wework;
import io.restassured.response.Response;

import javax.naming.Name;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Department extends Contact{


//    public Response list(String id){
//        return given().log().all()
//                .param("access_token", Wework.getToken())
//                .param("id", id)
//                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
//                .then().log().all().statusCode(200).extract().response();
//    }
//
//    public Response create(String name, int parentid){
//
//        String body=JsonPath.parse(this.getClass()
//                .getResourceAsStream("/data/create.json"))
//                .set("$.name", name)
//                .set("parentid", parentid).jsonString();
//        return
//        given().log().all().queryParam("access_token",Wework.getToken())
//                .body(body)
//        .when()
//                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
//        .then().log().all().statusCode(200).extract().response();
//    }
//
//    public Response update(int id, String name){
//        String body=JsonPath.parse(this.getClass()
//                .getResourceAsStream("/data/update.json"))
//                .set("$.id", id)
//                .set("$.name", name).jsonString();
//
//        return
//                given().log().all().queryParam("access_token",Wework.getToken())
//                        .body(body)
//                        .when()
//                        .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
//                        .then().log().all().statusCode(200).extract().response();
//    }


    public Response list(String id){
        Response response= requestSpecification
                .param("id", id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list").then().extract().response();
        reset();
        return response;
    }

    public Response create(String name, String parentid){
        reset();
        String body=JsonPath.parse(this.getClass()
                .getResourceAsStream("/data/create.json"))
                .set("$.name", name)
                .set("parentid", parentid).jsonString();
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();

    }
    public Response create(HashMap<String, Object> map){
        reset();

        DocumentContext documentContext=JsonPath.parse(this.getClass()
                .getResourceAsStream("/data/create.json"));
        map.entrySet().forEach(entry->{
            documentContext.set(entry.getKey(), entry.getValue());
        });
        return requestSpecification
                .body(documentContext.jsonString())
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().extract().response();

    }

    public Response delete(String id){
        reset();
        return requestSpecification
                .queryParam("id", id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().extract().response();
    }

    public Response update(String name, String id){
        reset();
        String body=JsonPath.parse(this.getClass()
                .getResourceAsStream("/data/update.json"))
                .set("$.name", name)
                .set("id", id).jsonString();
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().extract().response();

    }

    public Response update(HashMap<String, Object> map){
        return templateFromHar(
                "demo.har.json",
                "https://work.weixin.qq.com/wework_admin/party?action=addparty" ,
                map
        );
    }

    public Response deleteAll(){
        reset();
        List<Integer> idList=list("").then().log().all().extract().path("department.id");
        System.out.println(idList);
        idList.forEach(id->delete(id.toString()));
        return null;
    }

    public Response updateAll(HashMap<String, Object> map){
        return api("api.json", map);
    }


}
