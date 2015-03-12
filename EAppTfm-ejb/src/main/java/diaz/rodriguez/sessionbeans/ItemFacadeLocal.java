/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.sessionbeans;

import diaz.rodriguez.entities.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mars
 */
@Local
public interface ItemFacadeLocal {

    void create(Item item);

    void edit(Item item);

    void remove(Item item);

    Item find(Object id);

    List<Item> findAll();
    
    List<Item> findByName(String name);
    
    List<Item> findByCategory(String category);
    
    List<Item> orderByPriceAsc();
    
    List<Item> orderByPriceDesc();
    
    List<Item> orderByNameAsc();
    
    List<Item> orderByNameDesc();
    
}
