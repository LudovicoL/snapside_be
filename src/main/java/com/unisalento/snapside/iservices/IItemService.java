package com.unisalento.snapside.iservices;

import com.unisalento.snapside.exceptions.ItemNotFoundException;
import com.unisalento.snapside.generated.domain.ItemEntity;
import java.util.List;

public interface IItemService {
    public List<ItemEntity> getAll() throws ItemNotFoundException;
    public ItemEntity getById(int id) throws ItemNotFoundException;
    public ItemEntity getItemFromAd(int idAd) throws ItemNotFoundException;
    public ItemEntity saveAndFlush(ItemEntity item);

}

