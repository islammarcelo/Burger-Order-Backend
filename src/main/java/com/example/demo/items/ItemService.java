package com.example.demo.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemService {
    final ItemRepository itemRepository;
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public void addNewItem(Item item) {
        Optional<Item> itemByCode = getItem(item.getCode());
        if (itemByCode.isPresent()){
            throw new IllegalStateException("This item exists");
        }
        itemRepository.save(item);
    }

    private Optional<Item> getItem(int code) {
        Optional<Item> itemByCode = itemRepository.findItemByCode(code);
        return itemByCode;
    }

    public void deleteItem(int itemId) {
        boolean exists = itemRepository.existsById(itemId);
        if (!exists){
            throw new IllegalStateException("This item not exists");
        }
        itemRepository.deleteById(itemId);
    }

    @Transactional
    public void updateItem(int itemId, String name,double price, int code) {
        Item item = itemRepository.findById(itemId).orElseThrow(()-> new IllegalStateException(
                "Item with this id " + itemId + " dose not exists"));

        if(name != null && name.length() > 0 && !Objects.equals(item.getName(),name)){
            item.setName(name);
        }

        if (price != 0 && !Objects.equals(item.getPrice(),price)){
            item.setPrice(price);
        }

        if (code != 0 && !Objects.equals(item.getCode(),code)){

            Optional<Item> itemByCode = getItem(code);
            if (itemByCode.isPresent()){
                throw new IllegalStateException("This code taken");
            }
            item.setCode(code);
            System.out.println(item.getCode());
        }
    }
}
