package com.example.community.users;
import com.example.community.users.dto.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

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
        String[] parts = line.split(",", -1);
        Long userId = Long.valueOf(parts[0]);
        String userEmail = parts[1];
        String userPassword = parts[2];
        String userNickname = parts[3];
        String userProfilePic = parts[4];
        Boolean userIsDeleted = Boolean.valueOf(parts[5]);
        String userCreatedAt = parts[6];
        String userDeletedAt = null;

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
    public ArrayList<UserEntity> findAll() {return new ArrayList<>(userStore.values());}

    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(userStore.get(id));
    }

    @Override
    public Optional<UserEntity> findByNickname(String nickname) {
        return userStore.values().stream()
                .filter(item -> item.getUserNickname().equals(nickname))
                .findAny();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userStore.values().stream()
                .filter(item -> item.getUserEmail().equals(email))
                .findAny();
    }

    public Optional<UserEntity> findNotDeletedById(Long id) {
        UserEntity userEntity = userStore.get(id);
        if (userEntity != null && !userEntity.getUserIsDeleted()){
            return Optional.of(userEntity);
        }
        return Optional.empty();
    }

    @Override
    public void editPassword(UserEntity userEntity, String newPassword) {
        userEntity.setUserPassword(newPassword);
        userStore.put(userEntity.getUserId(), userEntity);
    }

    @Override
    public void editProfile(UserEntity userEntity, String newNickname, String newProfileImgUrl) {
        if (!(newProfileImgUrl == null || newProfileImgUrl.isEmpty())) {
            userEntity.setUserProfileImgUrl(newProfileImgUrl);
        }
        userEntity.setUserNickname(newNickname);
        userStore.put(userEntity.getUserId(), userEntity);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        long userNextId = sequence.incrementAndGet();
        userEntity.setUserId(userNextId);
        userStore.put(userEntity.getUserId(), userEntity);
        return userEntity;
    }

    @Override
    public boolean existsById(Long id) {
        return userStore.containsKey(id);
    }

    @Override
    public void delete(Long id) {
        UserEntity userEntity = userStore.get(id);
        userEntity.setUserIsDeleted(true);
        userStore.put(userEntity.getUserId(), userEntity);
    }

    @Override
    public void softDelete(UserEntity userEntity) {
        userEntity.setUserIsDeleted(true);
        userStore.put(userEntity.getUserId(), userEntity);
    }
}
