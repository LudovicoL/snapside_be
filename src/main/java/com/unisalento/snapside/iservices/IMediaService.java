package com.unisalento.snapside.iservices;

import com.unisalento.snapside.exceptions.MediaNotFoundException;
import com.unisalento.snapside.generated.domain.MediaEntity;
import com.unisalento.snapside.generated.domain.UserEntity;

import java.util.List;

public interface IMediaService {
    public List<MediaEntity> getAll() throws MediaNotFoundException;
    public List<MediaEntity> getAllByAd(int idAd) throws MediaNotFoundException;
    public MediaEntity getById(int id) throws MediaNotFoundException;
    public MediaEntity save(MediaEntity media);
    public void resetMedia(int idAd) throws MediaNotFoundException;
}
