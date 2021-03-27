package com.unisalento.snapside.services;


import java.sql.Timestamp;
import java.util.List;

import com.unisalento.snapside.generated.domain.BenefitEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.exceptions.UserNotFoundException;

import com.unisalento.snapside.repositories.UserRepository;



@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> getAll() throws UserNotFoundException {
        return userRepository.findAll();
    }

    @Override
    public UserEntity save(UserEntity user) {
       return userRepository.save(user);
    }

    @Override
    public void removeUserById(int id) throws UserNotFoundException {

    }

    @Override
    public UserEntity getById(int id) throws UserNotFoundException {
        // TODO Auto-generated method stub
        return userRepository.getOne(id);
        //return userRepository.getUserEntityById(id); funziona in alternativa
    }

    @Override
    public UserEntity getByUsername(String username) throws UserNotFoundException {
        // TODO Auto-generated method stub
        return userRepository.getByUsername(username);
        //return userRepository.getUserEntityById(id); funziona in alternativa
    }

    @Override
    public int count() throws UserNotFoundException {
        return 0;
    }

    @Override
    public  List<String> getAllBenefitsAtAd(int id_ad) {
        // TODO Auto-generated method stub
        return userRepository.getAllBenefitsAtAd(id_ad);
    }
/*    @Override
    public UserEntity userbyid(int id) throws UserNotFoundException {
        return null;
    }*/

    @Override
    public UserEntity getUserFromComment(int idComment) {
        // TODO Auto-generated method stub
        return userRepository.getUserFromComment(idComment);
    }

    @Override
    public void updateLastAccess(int idUser,Timestamp lastAccess) {
        // TODO Auto-generated method stub
        userRepository.updateLastAccess(idUser, lastAccess);
    }

    @Override
    public void updateFirebaseToken(int idUser,String token) {
        // TODO Auto-generated method stub
        userRepository.updateFirebaseToken(idUser, token);
    }
}
