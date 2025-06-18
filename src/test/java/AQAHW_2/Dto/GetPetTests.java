package AQAHW_2.Dto;


import AQAHW_2.Dto.Dto.CheckIdDto;
import AQAHW_2.Dto.Dto.PetDto;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetPetTests {
    private static final String Base_Url = "https://petstore.swagger.io/v2/pet/";

    @Test

    public void verifyPetCanBeObtained() {
        int petId = 654321;

        Response response = given().get(Base_Url + petId);
        System.out.println(response.asPrettyString());
        System.out.println(response.statusCode());

        PetDto petDto = response.as(PetDto.class);
        System.out.println(petDto.getId());
        System.out.println(petDto.getName());
        System.out.println(petDto.getStatus());
        Assert.assertEquals(petId, petDto.getId());

    }

    @Test
    public void checkPetId() {
        int idCheck = 85236;
        Response response = given().get(Base_Url + idCheck);
        System.out.println(response.asPrettyString());
        CheckIdDto checkIdDto = response.as(CheckIdDto.class);
        System.out.println(checkIdDto.toString());
        Assert.assertEquals("error", checkIdDto.getType());
        Assert.assertEquals("Pet not found", checkIdDto.getMessage());

    }
}
