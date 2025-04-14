package com.example.tmstraining.service;

import com.example.tmstraining.entities.CartDetails;
import com.example.tmstraining.entities.Item;
import com.example.tmstraining.entities.OrderDetails;
import com.example.tmstraining.exception.BadRequestException;
import com.example.tmstraining.exception.ItemCascadeDeleteException;
import com.example.tmstraining.exception.ItemNotFoundException;
import com.example.tmstraining.repository.CartDetailsRepository;
import com.example.tmstraining.repository.ItemRepository;
import com.example.tmstraining.repository.OrderDetailsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private OrderDetailsRepository orderDetailRepository;
    @Mock
    private CartDetailsRepository cartDetailsRepository;

    @Test
    public void findAllItems() {
        Item item1 = new Item(1, "item1", 11111);
        Item item2 = new Item(2, "item2", 22222);
        Mockito.when(itemRepository.findAll()).thenReturn(List.of(item1, item2));
        List<Item> items = itemService.findAllItems();

        Assertions.assertEquals(List.of(item1, item2), items);
    }

    @Test
    public void findItemById() {
        Item item1 = new Item(1, "item", 11111);

        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item1));
        Mockito.when(itemRepository.findById(2)).thenReturn(Optional.empty());

        Assertions.assertEquals(item1, itemService.findItemById(1));
        Assertions.assertThrows(ItemNotFoundException.class, () -> itemService.findItemById(2));
    }

    @Test
    public void createItem() {
        Item item1 = new Item(1, "item1", 11111);
        Item item2 = new Item(2, "item2", 22222);
        Mockito.when(itemRepository.findByName(item1.getName())).thenReturn(null);
        Mockito.when(itemRepository.save(item1)).thenReturn(item1);

        Mockito.when(itemRepository.findByName(item2.getName())).thenReturn(item2);

        Assertions.assertEquals(item1, itemService.createItem(item1));
        Assertions.assertThrows(BadRequestException.class, () -> itemService.createItem(item2));
    }

    @Test
    public void updateItem() {
        Item item1 = new Item(1, "item1", 11111);
        Item item2 = new Item(2, "item2", 22222);
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item1));
        Mockito.when(itemRepository.save(item1)).thenReturn(item1);
        Mockito.when(itemRepository.findById(2)).thenReturn(Optional.empty());

        Assertions.assertEquals(item1, itemService.updateItem(item1));
        Assertions.assertThrows(ItemNotFoundException.class, () -> itemService.updateItem(item2));
    }

    @Test
    public void deleteItem() {
        Item item1 = new Item(1, "item1", 11111);
        Item item2 = new Item(2, "item2", 22222);
        Item item3 = new Item(3, "item3", 33333);

        Mockito.when(itemRepository.findById(item1.getId())).thenReturn(Optional.of(item1));
        Mockito.when(cartDetailsRepository.findByItemId(item1.getId())).thenReturn(List.of());
        Mockito.when(orderDetailRepository.findByItemId(item1.getId())).thenReturn(List.of());
        Mockito.doNothing().when(itemRepository).deleteById(item1.getId());
        Assertions.assertEquals("Item deleted Successfully", itemService.deleteItem(item1.getId()));

        Mockito.when(itemRepository.findById(item2.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(ItemNotFoundException.class, () -> itemService.deleteItem(item2.getId()));

        Mockito.when(itemRepository.findById(item3.getId())).thenReturn(Optional.of(item3));
        Mockito.when(cartDetailsRepository.findByItemId(item3.getId())).thenReturn(List.of(new CartDetails()));
        Mockito.when(orderDetailRepository.findByItemId(item3.getId())).thenReturn(List.of(new OrderDetails()));
        Assertions.assertThrows(ItemCascadeDeleteException.class, () -> itemService.deleteItem(item3.getId()));
    }
}
