package com.example.community;

import com.example.community.dto.SignUpRequest;
import com.example.community.repository.UserCsvRepository;
import com.example.community.service.SignUpService;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
//        UserCsvRepository repo = new UserCsvRepository();
//        Entity entity = new Entity();
//        entity.setUserEmail("test4@gmail.com");
//        entity.setUserNickname("test4");
//        entity.setUserPassword("Test4!");
//        entity.setUserProfileImgUrl("image.com/test4");
//        entity.setUserCreatedAt("202510071023");
//
//        repo.save(entity);
//
//        Entity entity2 = new Entity();
//        entity2.setUserEmail("test5@gmail.com");
//        entity2.setUserNickname("test5");
//        entity2.setUserPassword("Test5!");
//        entity2.setUserProfileImgUrl("image.com/test5");
//        entity2.setUserCreatedAt("202510071023");
//
//        repo.save(entity2);
//        System.out.println(repo.findAll());
//
//        System.out.println(repo.findById(1L));

        UserCsvRepository userCsvRepository = new UserCsvRepository();
        SignUpService signUpService = new SignUpService(userCsvRepository);
        signUpService.signup(new SignUpRequest(
                "test4@gmail.com",
                "Testpassword4!",
                "test4",
                "https://www.kakaocorp.com/page/"
        ));
        signUpService.signup(new SignUpRequest(
                "test5@gmail.com",
                "Testpassword4!",
                "test5"
        ));
        System.out.println(userCsvRepository.findAll());

    }
}
