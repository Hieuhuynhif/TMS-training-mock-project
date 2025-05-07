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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CartDetailsRepository cartDetailsRepository;
    private final OrderDetailsRepository orderDetailRepository;

    public ItemService(ItemRepository itemRepository, CartDetailsRepository cartDetailsRepository,
                       OrderDetailsRepository orderDetailRepository) {
        this.itemRepository = itemRepository;
        this.cartDetailsRepository = cartDetailsRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Item findItemById(int id) {
        return itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public Item createItem(Item item) {
        Item createdItem = itemRepository.findByName(item.getName());
        if (createdItem != null) {
            throw new BadRequestException("Item existed with name: " + item.getName());
        }

        return itemRepository.save(item);
    }

    public Item updateItem(int itemId, Item item) {
        itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        return itemRepository.save(item);
    }

    public String deleteItem(int id) {
        itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);

        List<CartDetails> listCartDetails = cartDetailsRepository.findByItemId(id);
        List<OrderDetails> listOrderDetails = orderDetailRepository.findByItemId(id);

        if (!listOrderDetails.isEmpty()) {
            throw new ItemCascadeDeleteException("order");
        }
        if (!listCartDetails.isEmpty()) {
            throw new ItemCascadeDeleteException("cart");
        }

        itemRepository.deleteById(id);
        return "Item deleted Successfully";
    }
}
