package com.unisalento.snapside.adapters;

import com.unisalento.snapside.generated.domain.NotifEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.models.NotifDTO;

public class NotifAdapter {
    public static NotifEntity NotifDTOToNotifEntity(NotifDTO notifDTO, UserEntity user) {
        NotifEntity notifEntity = new NotifEntity();
        notifEntity.setIdNotif(notifDTO.getIdNotif());
        notifEntity.setTitle(notifDTO.getTitle());
        notifEntity.setSubject(notifDTO.getSubject());
        notifEntity.setCleared(notifDTO.getCleared());
        notifEntity.setStatus(notifDTO.getStatus());
        notifEntity.setUserByUserIdUser(user);
        notifEntity.setDate(notifDTO.getDate());
        notifEntity.setBody(notifDTO.getBody());
        notifEntity.setAdditive(notifDTO.getAdditive());
        notifEntity.setSenderIdUser(notifDTO.getSenderIdUser());
        return notifEntity;
    }
    public static NotifDTO NotifEntityToNotifDTO(NotifEntity notifEntity) {
        NotifDTO notifDTO = new NotifDTO();
        notifDTO.setIdNotif(notifEntity.getIdNotif());
        notifDTO.setTitle(notifEntity.getTitle());
        notifDTO.setSubject(notifEntity.getSubject());
        notifDTO.setCleared(notifEntity.getCleared());
        notifDTO.setStatus(notifEntity.getStatus());
        notifDTO.setUserByUserIdUser(notifEntity.getUserByUserIdUser().getIdUser());
        notifDTO.setDate(notifEntity.getDate());
        notifDTO.setSenderIdUser(notifEntity.getSenderIdUser());

        if(notifEntity.getAdByAdIdAd()!=null){
        notifDTO.setAdByAdIdAd(notifEntity.getAdByAdIdAd().getIdAd());}

        notifDTO.setBody(notifEntity.getBody());
        notifDTO.setAdditive(notifEntity.getAdditive());
        return notifDTO;
    }

}
