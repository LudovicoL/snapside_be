package com.unisalento.snapside.adapters;

import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.MediaEntity;
import com.unisalento.snapside.generated.domain.ItemEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.models.MediaDTO;
import com.unisalento.snapside.models.MediaDTO;
import com.unisalento.snapside.services.UserService;

public class MediaAdapter {
    public static MediaEntity MediaDTOToMediaEntity(MediaDTO mediaDTO, AdEntity ad) {
        MediaEntity media = new MediaEntity();
        media.setIdMedia(mediaDTO.getIdMedia());
        media.setDefaultImg(mediaDTO.getDefaultImg());
        media.setMediaName(mediaDTO.getMediaName());
        media.setContent(mediaDTO.getContent());
        media.setAdByAdIdAd(ad);
        return media;
    }
    public static MediaDTO MediaEntityToMediaDTO(MediaEntity media) {
//        UserService userService;
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setIdMedia(media.getIdMedia());
        mediaDTO.setDefaultImg(media.getDefaultImg());
        mediaDTO.setContent(media.getContent());
        mediaDTO.setAdByAdIdAd(media.getAdByAdIdAd().getIdAd());
        mediaDTO.setMediaName(media.getMediaName());


        return mediaDTO;
    }

}
