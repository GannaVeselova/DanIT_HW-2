package AQAHW_2.Dto;

import AQAHW_2.Dto.Dto.CategotyDto;
import AQAHW_2.Dto.Dto.PetDto;
import AQAHW_2.Dto.Dto.TadsDto;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PutPetTest {
    private static final String BASE_URl = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void varifyPetCanBeCModified() {
        int petDtoId = 654321;
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

        Response response = given().header("Content-Type", "application/json").body(petDto).post(BASE_URl);
        System.out.println(response.asPrettyString());

        Response previouslyCreatedPetDtoResponse = given().get(BASE_URl + petDtoId);
        PetDto previouslyCreatedPetDto = previouslyCreatedPetDtoResponse.as(PetDto.class);
        System.out.println(previouslyCreatedPetDto.getStatus());
        Assert.assertEquals(petDtoId, previouslyCreatedPetDto.getId());

        String changeName = "Pirat";
        String changeStatusValue = "sold";
        String changeCategoryName = "cat";
        categotyDto.setName(changeCategoryName);
        petDto.setName(changeName);
        petDto.setStatus(changeStatusValue);
        petDto.setCategory(categotyDto);

        Response objectChanges = given().header("Content-Type", "application/json").body(petDto).put(BASE_URl);
        System.out.println(objectChanges.asPrettyString());

        Response changeObjectResponse = given().get(BASE_URl + petDtoId);
        PetDto previouslyChangeObject = changeObjectResponse.as(PetDto.class);
        System.out.println(previouslyChangeObject.getStatus());
        System.out.println(previouslyChangeObject.getName());
        System.out.println(previouslyChangeObject.getCategory().getName());

        Assert.assertEquals(petDtoId, previouslyChangeObject.getId());
        Assert.assertEquals(changeName, previouslyChangeObject.getName());
        Assert.assertEquals(changeStatusValue, previouslyChangeObject.getStatus());
        Assert.assertEquals(changeCategoryName, previouslyChangeObject.getCategory().getName());

    }
}
