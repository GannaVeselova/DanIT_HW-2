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

public class CreatePetTest {
    private static final String Base_Url = "https://petstore.swagger.io/v2/pet/";
@Test
public void varifyPetCanBeCreate(){
    int petDtoId = 654321;
    String statusValue = "available";
    String categorySetName = "Mashka";
    String name = "Pashka";
    CategotyDto categotyDto = new CategotyDto();
    categotyDto.setId(456);
    categotyDto.setName(categorySetName );
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

    Response response = given().header("Content-Type","application/json").body(petDto).post(Base_Url);
    System.out.println(response.asPrettyString());
    Response previouslyCreatedPetDtoResponse = given().get(Base_Url+petDtoId);
    PetDto previouslyCreatedPetDto = previouslyCreatedPetDtoResponse.as(PetDto.class);

    Assert.assertEquals(petDtoId,previouslyCreatedPetDto.getId());
    Assert.assertEquals(statusValue,previouslyCreatedPetDto.getStatus());
    Assert.assertEquals(name,previouslyCreatedPetDto.getName());
    Assert.assertEquals(categorySetName,previouslyCreatedPetDto.getCategory().getName());
}
}
