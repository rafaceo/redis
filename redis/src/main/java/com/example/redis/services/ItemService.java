package com.example.redis.services;

import com.example.redis.model.Item;
import com.example.redis.repositry.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CacheService cacheService;

    public Item getItemFromBdOrRedis(Long id){
        final String key = "item:" + id;
        Item itemCache = (Item) cacheService.get(key);
        if(itemCache != null){
            return itemCache;
        }
        Optional<Item> itemOptional = itemRepository.findById(id);
        itemOptional.ifPresent(item -> cacheService.addCache(key,item));

        return itemOptional.orElse(null);
    }

    public Item update(Item item){
        itemRepository.save(item);
         cacheService.addCache("item:" + item.getId(), item);
        return item;
    }

    public void deleteItemFromBdOrRedis(Long id){
        itemRepository.deleteById(id);
        cacheService.delete("item:"+id);
    }
}
