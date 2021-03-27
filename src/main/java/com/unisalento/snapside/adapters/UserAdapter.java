package com.unisalento.snapside.adapters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.models.UserDTO;

public class UserAdapter {
    public static UserDTO UserEntityToUserDTO(UserEntity userEntity) {

       //DATE FORMATTER STRING
       // DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-DD");
       // String strDate = dateFormatter.format(userDTO.getDob());

        UserDTO user=new UserDTO();
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurname());
        user.setDob(userEntity.getDob());
        user.setEmail(userEntity.getEmail());
        user.setUsername(userEntity.getUsername());
        user.setAddress(userEntity.getAddress());
        user.setPassword(userEntity.getPassword());
        user.setEnabled(userEntity.getEnabled());
        user.setUserType(userEntity.getUserType());
        user.setIdUser(userEntity.getIdUser());
        user.setPhone(userEntity.getPhone());
        user.setUserImg(userEntity.getUserImg());
        user.setLastAccess(userEntity.getLastAccess());
        user.setToken(userEntity.getToken());
        return user;
    }

    public static UserEntity UserDTOToUserEntity(UserDTO userDTO) {


        UserEntity user=new UserEntity();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setDob(userDTO.getDob());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setAddress(userDTO.getAddress());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.getEnabled());
        user.setUserType(userDTO.getUserType());
//        user.setIdUser(userDTO.getIdUser());
        user.setPhone(userDTO.getPhone());
        user.setUserImg(userDTO.getUserImg());
        user.setLastAccess(userDTO.getLastAccess());
        user.setToken(userDTO.getToken());
        return user;
    }


}