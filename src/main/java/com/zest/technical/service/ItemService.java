package com.zest.technical.service;

import com.zest.technical.model.Item;
import com.zest.technical.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getItemsByProductId(Long productId) {
        return itemRepository.findByProductId(productId);
    }
}

