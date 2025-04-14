package com.example.tmstraining.controller;

import com.example.tmstraining.entities.Item;
import com.example.tmstraining.service.ItemService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("")
    public Item updateItem(@RequestBody Item item) {
        return itemService.updateItem(item);
    }

    @DeleteMapping("{itemId}")
    public String deleteItem(@PathVariable int itemId) {
        return itemService.deleteItem(itemId);
    }
}
