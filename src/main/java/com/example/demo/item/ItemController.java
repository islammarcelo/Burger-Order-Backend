package com.example.demo.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ItemController {
    final ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/getItems")
    List<Item> getItems(){
        return itemService.getItems();
    }

    @PostMapping("/addItem")
    public void addNewItem(@RequestBody Item item){
        itemService.addNewItem(item);
    }

    @DeleteMapping(path = "/deleteItem/{itemId}")
    public void deleteItem(@PathVariable("itemId") int itemId){
        itemService.deleteItem(itemId);
    }

    @PutMapping(path = "updateItem/{itemId}")
    public void updateItem(@PathVariable("itemId") int itemId,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) double price,
                           @RequestParam(required = false) int code){
        itemService.updateItem(itemId,name,price,code);
    }
}
