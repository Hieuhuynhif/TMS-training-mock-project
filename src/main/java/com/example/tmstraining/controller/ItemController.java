package com.example.tmstraining.controller;

import com.example.tmstraining.entities.Item;
import com.example.tmstraining.service.ItemService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("")
    public List<Item> findAllItems() {
        return itemService.findAllItems();
    }

    @GetMapping("{itemId}")
    public Item findItemById(@PathVariable int itemId) {
        return itemService.findItemById(itemId);
    }

    @PostMapping("")
    public Item addItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @PutMapping("{itemId}")
    public Item updateItem(@PathVariable int itemId, @RequestBody Item item) {
        return itemService.updateItem(itemId, item);
    }

    @DeleteMapping("{itemId}")
    public String deleteItem(@PathVariable int itemId) {
        return itemService.deleteItem(itemId);
    }
}
