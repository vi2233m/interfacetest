package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.equalTo;

class DepartmentTest {

    Department department;
    @BeforeEach
    void setUp(){
        if (department == null){
            department = new Department();
        }
    }

    @Test
    void list() {
        department.list("").then().statusCode(200).body("department.name[0]", equalTo("定向班第一期"));
//        department.list("33").then().statusCode(200).body("department.name[0]", equalTo("定向班第一期"));
    }

    @Test
    void create() {
        department.create("testvi22233","1").then().body("errcode", equalTo(0));
    }

    @Test
    void create1() {
        department.create("testvi22233","3").then().body("errcode", equalTo(60008));
        department.create("testvi22233","3").then().body("errcode", equalTo(60008));
    }

    @Test
    void update(){
        String departmenId = department.create("testvi222330","33").path("id");
        department.update(departmenId, "testvi22233_1").then().body("errcode",equalTo(0));
    }
}