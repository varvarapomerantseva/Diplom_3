package DataAPI;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserClient extends RestClient {


    public static String accessToken;
    public final static String BASEURI = "https://stellarburgers.nomoreparties.site";
    public static User user;

    public ValidatableResponse create(String name, String email, String password) {
        user = new User(email, password, name);
        return given()
                .spec((getBaseSpec()))
                .body(user)
                .when()
                .post("/api/auth/register")
                .then();
    }

    public ValidatableResponse login(String email, String password) {
        user = new User(email, password);
        return given()
                .spec((getBaseSpec()))
                .body(user)
                .when()
                .post("/api/auth/login")
                .then();
    }

    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete("api/auth/user")
                .then();
    }
}
