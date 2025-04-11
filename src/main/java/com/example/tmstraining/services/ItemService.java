package com.example.tmstraining.services;

import com.example.tmstraining.entities.Item;
import com.example.tmstraining.exceptions.ItemNotFoundException;
import com.example.tmstraining.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Item findItemById(int id) {
        return itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    public String deleteItem(int id) {
        itemRepository.deleteById(id);
        return "Item deleted Successfully";
    }
}
