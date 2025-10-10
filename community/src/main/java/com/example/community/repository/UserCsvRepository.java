package com.example.community.repository;
import com.example.community.dto.UserEntity;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class UserCsvRepository implements UserRepository {
    public final Map<Long, UserEntity> userStore = new LinkedHashMap<>();
    private AtomicLong sequence = new AtomicLong(0);
    private final String userDbPath = "src/main/resources/data/users.csv";

    private UserEntity createUserDto(String line){
        String[] parts = line.split(",");
        String deletedTime = null;
        if (parts.length == 8) {
            deletedTime = parts[7];
        }

        Long userId = Long.valueOf(parts[0]);
        String userEmail = parts[1];
        String userPassword = parts[2];
        String userNickname = parts[3];
        String userProfilePic = parts[4];
        Boolean userIsDeleted = Boolean.valueOf(parts[5]);
        String userCreatedAt = parts[6];
        String userDeletedAt = deletedTime;

        UserEntity userEntity = new UserEntity(
                userId, userEmail,userPassword,userNickname,userProfilePic,userIsDeleted,userCreatedAt, userDeletedAt
        );
        return userEntity;
    }

    private void init() throws IOException {
        File file = new File(userDbPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        bufferedReader.readLine(); // 칼럼행 건너뛰기
        while ((line = bufferedReader.readLine()) != null) {
            UserEntity userEntity = createUserDto(line);
            sequence.set(userEntity.getUserId());
            userStore.put(sequence.get(), userEntity);
        }
    }

    public UserCsvRepository() throws IOException {
        init();
    }

    @Override
    public ArrayList<UserEntity> findAll() {
        return new ArrayList<>(userStore.values());
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(userStore.get(id));
    }

    public Optional<UserEntity> findByNickname(String nickname) {
        return userStore.values().stream()
                .filter(item -> item.getUserNickname().equals(nickname))
                .findAny();
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userStore.values().stream()
                .filter(item -> item.getUserEmail().equals(email))
                .findAny();
    }

    @Override
    public void save(UserEntity userEntity) {
        long userNextId = sequence.incrementAndGet();
        userEntity.setUserId(userNextId);

        if (userEntity.getUserEmail() == null || userEntity.getUserEmail().isEmpty() ||
                userEntity.getUserPassword() == null || userEntity.getUserPassword().isEmpty() ||
                userEntity.getUserNickname() == null || userEntity.getUserNickname().isEmpty() ||
                userEntity.getUserProfileImgUrl() == null || userEntity.getUserProfileImgUrl().isEmpty()) {
            throw new IllegalArgumentException("Email or password or nickname or profile img url is null");
        }

        userStore.put(userEntity.getUserId(), userEntity);
    }

    public void editPassword(UserEntity userEntity, String newPassword) {
        userEntity.setUserPassword(newPassword);
    }

    public void editProfile(UserEntity userEntity, String newNickname, String newProfileImgUrl) {
        if (!(newProfileImgUrl == null || newProfileImgUrl.isEmpty())) {
            userEntity.setUserProfileImgUrl(newProfileImgUrl);
        }
        userEntity.setUserNickname(newNickname);
    }


}
