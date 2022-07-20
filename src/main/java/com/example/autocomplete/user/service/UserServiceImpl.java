package com.example.autocomplete.user.service;

import com.example.autocomplete.global.domain.BaseResponse;
import com.example.autocomplete.global.exception.BadRequestException;
import com.example.autocomplete.global.util.NameDivider;
import com.example.autocomplete.global.util.RedisUtilStrObj;
import com.example.autocomplete.user.domain.UserList;
import com.example.autocomplete.user.domain.UserSignUp;
import com.example.autocomplete.user.entity.UserEntity;
import com.example.autocomplete.user.mapper.UserMapper;
import com.example.autocomplete.user.repository.UserEntityJPARepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserEntityJPARepository userEntityJPARepository;

    private static final NameDivider nameDivider = new NameDivider();

    private final RedisUtilStrObj redisUtilStrObj;

    @Autowired
    public UserServiceImpl(UserEntityJPARepository userEntityJPARepository, RedisUtilStrObj redisUtilString) {
        this.userEntityJPARepository = userEntityJPARepository;
        this.redisUtilStrObj = redisUtilString;
    }

    void cacheDividedName(UserSignUp userSignUp) throws JsonProcessingException {
        // 분리된 이름 연속되게 변경
        // ㄱㅣㄹㄷㅗㅇ => ㄱ, 기, 길, 길ㄷ, 길도, 길동
        ArrayList<ArrayList<String>> dividedName = nameDivider.divideName(userSignUp.getFirstName() + userSignUp.getLastName());
        for (int i = 0; i < dividedName.size() - 1; i++) {
            ArrayList<String> nowList = dividedName.get(i);
            for (int j = i + 1; j < dividedName.size(); j++) {
                ArrayList<String> nextList = dividedName.get(j);
                String lastChar = nowList.get(nowList.size() - 1);
                for (String str : nextList) {
                    nowList.add(lastChar + str);
                }
            }
        }

        // 레디스에 저장
        for (ArrayList<String> nowList : dividedName) {
            for (String nowName : nowList) {
                redisUtilStrObj.saveDataAsSet(nowName, userSignUp);
            }
        }
    }

    @Override
    public void signUp(UserSignUp userSignUp) {
        userSignUp.setPassword(BCrypt.hashpw(userSignUp.getPassword(), BCrypt.gensalt()));
        try {
            cacheDividedName(userSignUp);
        } catch (JsonProcessingException e) {
            throw new BadRequestException("사용할 수 없는 이름입니다.");
        }

        UserEntity user = UserMapper.INSTANCE.toUserEntity(userSignUp);
        UserEntity saveResult =  userEntityJPARepository.save(user);
    }

    @Override
    public BaseResponse autoCompleteUserName(String name) {
        UserList userList = new UserList();
        try {
            ArrayList<UserSignUp> searchList = redisUtilStrObj.getDataAsSet(name, UserSignUp.class);
            userList.setUserList(searchList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new BaseResponse(userList, HttpStatus.OK);
    }

    @Override
    public Long countUsers() {
        return userEntityJPARepository.count();
    }
}
