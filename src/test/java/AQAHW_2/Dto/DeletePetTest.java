package AQAHW_2.Dto;

import AQAHW_2.Dto.Dto.*;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DeletePetTest {
    private static final String Base_Url = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void verifyPetCanBeODelete() {
        int petDtoId = 987654;
        String statusValue = "available";
        String categorySetName = "Mashka";
        String name = "Pashka";
        CategotyDto categotyDto = new CategotyDto();
        categotyDto.setId(456);
        categotyDto.setName(categorySetName);
        List<String> photoUrl = new ArrayList<>(List.of("First", "Second", "Third"));
        TadsDto creatTadDto = new TadsDto();
        creatTadDto.setName("someTag");
        creatTadDto.setId(45);
        List<TadsDto> tadsDtoList = new ArrayList<>(List.of(creatTadDto));
        PetDto petDto = new PetDto();
        petDto.setId(petDtoId);
        petDto.setName(name);
        petDto.setCategory(categotyDto);
        petDto.setName(name);
        petDto.setPhotoUrls(photoUrl);
        petDto.setTags(tadsDtoList);
        petDto.setStatus(statusValue);

        Response response = given().header("Content-Type", "application/json").body(petDto).post(Base_Url);
        System.out.println(response.asPrettyString());
        Response previouslyCreatedPetDtoResponse = given().get(Base_Url + petDtoId);
        PetDto previouslyCreatedPetDto = previouslyCreatedPetDtoResponse.as(PetDto.class);
        Assert.assertEquals(petDtoId, previouslyCreatedPetDto.getId());

        Response deleteRetResponse = given().delete(Base_Url + petDtoId);
        DeleteDto deletePetDto = deleteRetResponse.as(DeleteDto.class);
        System.out.println(deleteRetResponse.asPrettyString());
        Assert.assertEquals("unknown", deletePetDto.getType());
        Assert.assertEquals(String.valueOf(petDtoId), deletePetDto.getMessage());
        System.out.println(deleteRetResponse.getStatusCode());


    }

    @Test
    public void noWayToDelete() {
        int petDtoId = 987654;
        Response response = given().get(Base_Url + petDtoId);
        Assert.assertEquals(404, response.getStatusCode());


    }

}
