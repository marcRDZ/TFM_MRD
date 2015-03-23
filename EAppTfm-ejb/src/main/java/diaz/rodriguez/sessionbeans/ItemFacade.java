/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.sessionbeans;

import diaz.rodriguez.entities.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mars
 */
@Stateless
public class ItemFacade extends AbstractFacade<Item> implements ItemFacadeLocal {
    @PersistenceContext(unitName = "EappTfm")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemFacade() {
        super(Item.class);
    }

    @Override
    public List<Item> findAll() {
        return em.createNamedQuery("Item.findAll", Item.class).getResultList();
    }
    
    @Override
    public List<Item> findByName(String name) {
        List<Item> res = null;
        Query query = em.createNamedQuery( "Item.findByName", Item.class);
        query.setParameter("n", name);
        res = query.getResultList();

        return res;    
    }

    @Override
    public List<Item> findByCategory(String category) {
        List<Item> res = null;
        Query query = em.createNamedQuery( "Item.findByCategory", Item.class);
        query.setParameter( "c", category);
        res = query.getResultList();

        return res;      
    }

    @Override
    public List<Item> orderByPriceAsc() {
        List<Item> res = null;
        Query query = em.createNamedQuery( "Item.orderByPriceAsc", Item.class);
        res = query.getResultList();

        return res;  
    }

    @Override
    public List<Item> orderByPriceDesc() {
        List<Item> res = null;
        Query query = em.createNamedQuery( "Item.orderByPriceDesc", Item.class);
        res = query.getResultList();

        return res;  
    }

    @Override
    public List<Item> orderByNameAsc() {
        List<Item> res = null;
        Query query = em.createNamedQuery( "Item.orderByNameAsc", Item.class);
        res = query.getResultList();

        return res;  
    }

    @Override
    public List<Item> orderByNameDesc() {
        List<Item> res = null;
        Query query = em.createNamedQuery( "Item.orderByNameDesc", Item.class);
        res = query.getResultList();

        return res;  
    }
    
}
