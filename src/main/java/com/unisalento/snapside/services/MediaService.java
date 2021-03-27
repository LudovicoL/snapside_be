package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.MediaNotFoundException;
import com.unisalento.snapside.generated.domain.MediaEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.iservices.IAdService;
import com.unisalento.snapside.iservices.IMediaService;
import com.unisalento.snapside.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService implements IMediaService {

    @Autowired
    MediaRepository mediaRepository;

    @Override
    public List<MediaEntity> getAll() throws MediaNotFoundException {
        // TODO Auto-generated method stub
        return mediaRepository.findAll();
    }



    @Override
    public MediaEntity getById(int id) throws MediaNotFoundException {
        // TODO Auto-generated method stub
        return mediaRepository.getOne(id);
    }

    @Override
    public MediaEntity save(MediaEntity media) {
        return mediaRepository.save(media);
    }

    @Override
    public List<MediaEntity> getAllByAd(int idAd) throws MediaNotFoundException {
        // TODO Auto-generated method stub
        return mediaRepository.getAllByAd(idAd);
    }

    @Override
    public void resetMedia(int idAd) throws MediaNotFoundException {
        // TODO Auto-generated method stub
        mediaRepository.resetMedia(idAd);
    }
}
