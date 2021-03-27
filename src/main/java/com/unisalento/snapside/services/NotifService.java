package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.NotifNotFoundException;
import com.unisalento.snapside.generated.domain.NotifEntity;
import com.unisalento.snapside.iservices.INotifService;
import com.unisalento.snapside.repositories.NotifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifService implements INotifService {

    @Autowired
    NotifRepository notifRepository;

    @Override
    public List<NotifEntity> getAll() throws NotifNotFoundException {
        // TODO Auto-generated method stub
        return notifRepository.findAll();
    }

    @Override
    public NotifEntity getById(int id) throws NotifNotFoundException {
        // TODO Auto-generated method stub
        return notifRepository.getOne(id);
    }

    @Override
    public NotifEntity save(NotifEntity notif) {
        return notifRepository.save(notif);
    }

    @Override
    public List<NotifEntity> getAllbyUser(int userid) {
        return notifRepository.getAllbyUser(userid);
    }

    @Override
    public void clearPush(int idnotif) {
        notifRepository.clearPush(idnotif);
    }

    @Override
    public void clearNotif(int idnotif) {
        notifRepository.clearNotif(idnotif);
    }

    @Override
    public void clearAllByUser(int userid) {
        notifRepository.clearAllByUser(userid);
    }
}
