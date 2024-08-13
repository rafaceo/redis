package com.example.redis.controller;


import com.example.redis.model.Item;
import com.example.redis.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("{id}")
    public Item getItem(@PathVariable(name = "id") Long id){
        return itemService.getItemFromBdOrRedis(id);
    }

    @PutMapping("update")
    public Item updateItem(@RequestBody Item item){
        return itemService.update(item);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id){
        itemService.deleteItemFromBdOrRedis(id);
    }
}
