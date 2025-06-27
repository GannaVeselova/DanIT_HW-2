package AQAHW_2.Dto;


import AQAHW_2.Dto.Dto.CheckIdDoesNotExistDto;
import AQAHW_2.Dto.Dto.PetDto;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetPetTests {
    private static final String BASE_URl = "https://petstore.swagger.io/v2/pet/";

    @Test

    public void verifyPetCanBeObtained() {
        int petId = 654321;

        Response response = given().get(BASE_URl + petId);
        System.out.println(response.asPrettyString());
        System.out.println(response.statusCode());

        PetDto petDto = response.as(PetDto.class);
        System.out.println(petDto.getId());
        System.out.println(petDto.getName());
        System.out.println(petDto.getStatus());
        Assert.assertEquals(petId, petDto.getId());

    }

    @Test
    public void checkPetIdThatDoesNotExist() {
        int idCheck = 85236;
        Response response = given().get(BASE_URl + idCheck);
        System.out.println(response.asPrettyString());
        CheckIdDoesNotExistDto checkIdDto = response.as(CheckIdDoesNotExistDto.class);
        System.out.println(checkIdDto.toString());
        Assert.assertEquals("error", checkIdDto.getType());
        Assert.assertEquals("Pet not found", checkIdDto.getMessage());

    }
}
