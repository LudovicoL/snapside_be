package com.unisalento.snapside.iservices;

import com.unisalento.snapside.exceptions.NotifNotFoundException;
import com.unisalento.snapside.generated.domain.NotifEntity;

import java.util.List;

public interface INotifService {
    public List<NotifEntity> getAll() throws NotifNotFoundException;
    public NotifEntity getById(int id) throws NotifNotFoundException;
    public List<NotifEntity> getAllbyUser(int userid) throws NotifNotFoundException;
    public NotifEntity save(NotifEntity notif);
    public void clearPush(int idnotif);
    public void clearNotif(int idnotif);
    public void clearAllByUser(int userid);

}
