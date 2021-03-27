package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.ItemNotFoundException;
import com.unisalento.snapside.repositories.ItemRepository;

import org.junit.jupiter.api.Test;
import com.unisalento.snapside.generated.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepositoryMock;
    @InjectMocks
    private ItemService itemService;

    private ItemEntity item = new ItemEntity();
    private ItemEntity item1 = new ItemEntity();
    private ItemEntity item2 = new ItemEntity();


    @BeforeEach
    public void setUp() {
        item.setIdItem(1);
        item.setItemName("Item1");
        item.setDescriptionItem("Description1");
        item.setCategoryByCategoryIdCategory(new CategoryEntity());

        item1.setIdItem(2);
        item1.setItemName("Item2");
        item1.setDescriptionItem("Description2");
        item1.setCategoryByCategoryIdCategory(new CategoryEntity());

        item2.setIdItem(3);
        item2.setItemName("Item2");
        item2.setDescriptionItem("Description3");
        item2.setCategoryByCategoryIdCategory(new CategoryEntity());
    }

    @Test
    void getAll() throws ItemNotFoundException {
        when(itemRepositoryMock.findAll()).thenReturn(Arrays.asList(item1, item2));
        List<ItemEntity> item0 = itemService.getAll();

        assertEquals(item1.getIdItem(), item0.get(0).getIdItem());
        assertEquals(item1.getItemName(), item0.get(0).getItemName());
        assertEquals(item1.getDescriptionItem(), item0.get(0).getDescriptionItem());
        assertEquals(item2.getIdItem(), item0.get(1).getIdItem());
        assertEquals(item2.getItemName(), item0.get(1).getItemName());
        assertEquals(item2.getDescriptionItem(), item0.get(1).getDescriptionItem());

    }

    @Test
    void getById() throws ItemNotFoundException {
        when(itemRepositoryMock.getOne(1)).thenReturn(item);
        ItemEntity item0 = itemService.getById(item.getIdItem());
        assertEquals(item.getIdItem(), item0.getIdItem());
        assertEquals(item.getItemName(), item0.getItemName());
        assertEquals(item.getDescriptionItem(), item0.getDescriptionItem());
    }

    @Test
    void getItemFromAd() throws ItemNotFoundException {
        AdEntity ad = new AdEntity();
        ad.setIdAd(1);

        when(itemRepositoryMock.getItemFromAd(1)).thenReturn(item);
        ItemEntity item0 = itemService.getItemFromAd(ad.getIdAd());
        assertEquals(item.getIdItem(), item0.getIdItem());
        assertEquals(item.getItemName(), item0.getItemName());
        assertEquals(item.getDescriptionItem(), item0.getDescriptionItem());
    }

    @Test
    void saveAndFlush() {
        when(itemRepositoryMock.saveAndFlush(Mockito.any(ItemEntity.class))).thenReturn(item1);
        ItemEntity item0 = itemService.saveAndFlush(item1);
        assertEquals(item1.getIdItem(), item0.getIdItem());
        assertEquals(item1.getItemName(), item0.getItemName());
        assertEquals(item1.getDescriptionItem(), item0.getDescriptionItem());
    }
}