package com.unisalento.snapside.iservices;

import java.sql.Timestamp;
import java.util.List;

import com.unisalento.snapside.exceptions.BenefitNotFoundException;
import com.unisalento.snapside.exceptions.CommentNotFoundException;
import com.unisalento.snapside.generated.domain.BenefitEntity;
import com.unisalento.snapside.generated.domain.CommentEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.exceptions.UserNotFoundException;


public interface IUserService {
    public List<UserEntity> getAll() throws UserNotFoundException;
    public UserEntity save(UserEntity user);
    public void removeUserById (int id) throws UserNotFoundException;
    public UserEntity getById(int id) throws UserNotFoundException;
    public UserEntity getByUsername(String username) throws UserNotFoundException;
    public  List<String> getAllBenefitsAtAd(int idAd) throws BenefitNotFoundException;
    public int count() throws UserNotFoundException;
    public void updateLastAccess(int idUser, Timestamp lastAccess) throws UserNotFoundException;
    public UserEntity getUserFromComment(int idComment) throws UserNotFoundException;
    public void updateFirebaseToken(int idUser, String token) throws UserNotFoundException;
    //UserEntity userbyid(int id) throws UserNotFoundException;
}
