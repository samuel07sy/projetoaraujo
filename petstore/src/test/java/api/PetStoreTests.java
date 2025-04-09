package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pet.Store;
import pet.Pet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;


public class PetStoreTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void testPostPedido() {
        Store order = new Store();
        order.setId(8425);
        order.setPetId(8425);
        order.setQuantity(1);
        order.setStatus("placed");
        order.setComplete(true);

        given()
            .contentType(ContentType.JSON)
            .body(order)
        .when()
            .post("/store/order")
        .then()
            .statusCode(200)
            .body("id", equalTo(8425))
            .body("status", equalTo("placed"));
    }

    @Test
    public void testGetPetById() {
        Pet pet = new Pet();
        pet.setId(825);
        pet.setName("Caramelo");
        pet.setStatus("pending");
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add("http://example.com/photo.jpg");
        pet.setPhotoUrls(photoUrls);


        given()
            .contentType(ContentType.JSON)
            .body(pet)
        .when()
            .post("/pet")
        .then()
            .statusCode(200);

        given()
            .pathParam("petId", 825)
        .when()
            .get("/pet/{petId}")
        .then()
            .statusCode(200)
            .body("name", equalTo("Caramelo"))
            .body("status", equalTo("pending"));

        given()
            .pathParams("petId", 825, "status", "available")
        .when()
            .get("/pet/{petId}/pet/{status}")
        .then()
            .statusCode(404);
    }

    @Test
    public void testPutPet() {
        Pet pet = new Pet();
        pet.setId(8255);
        pet.setName("Caramelo update");
        pet.setStatus("available");
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add("http://example.com/photo.jpg");
        pet.setPhotoUrls(photoUrls);

        given()
            .contentType(ContentType.JSON)
            .body(pet)
        .when()
            .post("/pet")
        .then()
            .statusCode(200);

        pet.setStatus("sold");
        given()
            .contentType(ContentType.JSON)
            .body(pet)
        .when()
            .put("/pet")
        .then()
            .statusCode(200)
            .body("status", equalTo("sold"));
    }

    @Test
    public void testFindPetsByStatus() {
        String[] statuses = {"pending"};
        
        for (String status : statuses) {
            given()
                .queryParam("status", status)
            .when()
                .get("/pet/findByStatus")
            .then()
                .statusCode(200)
                .body("status", everyItem(equalTo(status)));
        }
    }
}
