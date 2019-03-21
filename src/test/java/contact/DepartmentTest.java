package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.assertj.core.api.Assertions.*;

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
        department.list("").then().statusCode(200).log().all().body("department.name[0]", equalTo("零壹testing"));
        department.list("2").then().statusCode(200).body("errmsg", equalTo("ok"));
        String name = department.list("2").then().extract().body().jsonPath().get("department.name[0]");
        List<String> names = department.list("").then().extract().body().jsonPath().get("department.name");

        assertThat(name).isEqualTo("测试部");
        assertThat(names).contains("测试部", "testvi22233").hasSize(5).doesNotContain("test");
        assertThat(name).as("my department is %s", name).isEqualTo("测试");
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