package com.example.tmstraining.controller;

import com.example.tmstraining.entities.Item;
import com.example.tmstraining.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    @Test
    public void findAllItems() {
        Item testItem1 = new Item(1, "testItem", 232);
        Item testItem2 = new Item(1, "testItem", 232);

        Mockito.when(itemService.findAllItems())
                .thenReturn(List.of(testItem1, testItem2));

        List<Item> items = itemController.findAllItems();
        Assertions.assertEquals(List.of(testItem1, testItem2), items);
    }

    @Test
    public void findItemById() {
        Item testItem = new Item(1, "testItem", 232);

        Mockito.when(itemService.findItemById(1)).thenReturn(testItem);
        Item item = itemController.findItemById(1);
        Assertions.assertEquals(testItem, item);
    }

    @Test
    public void addItem() {
        Item testItem = new Item(1, "testItem", 232);
        Mockito.when(itemService
                        .createItem(testItem))
                .thenReturn(testItem);
        Item item = itemController.addItem(testItem);

        Assertions.assertEquals(testItem, item);
    }

    @Test
    public void updateItem() {
        Item testItem = new Item(1, "testItem", 232);

        Mockito.when(itemService.updateItem(testItem.getId(), testItem)).thenReturn(testItem);
        Item item = itemController.updateItem(testItem.getId(), testItem);

        Assertions.assertEquals(testItem, item);
    }

    @Test
    public void deleteItem() {
        Mockito.when(itemService.deleteItem(1)).thenReturn("Item deleted Successfully");
        String message = itemController.deleteItem(1);
        Assertions.assertEquals("Item deleted Successfully", message);
    }
}
