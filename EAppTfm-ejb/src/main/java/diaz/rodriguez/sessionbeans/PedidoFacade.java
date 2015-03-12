/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.sessionbeans;

import diaz.rodriguez.entities.Pedido;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> implements PedidoFacadeLocal {
    @PersistenceContext(unitName = "diaz.rodriguez_EAppTfm-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }

    @Override
    public List<Pedido> findByUser(String user) {        
        List<Pedido> res = null;
        Query query = em.createNamedQuery( "Pedido.findByUser", Pedido.class);
        query.setParameter( "u", user);
        res = query.getResultList();

        return res;
    }

    @Override
    public List<Pedido> findByDate(Date date) {
        List<Pedido> res = null;
        Query query = em.createNamedQuery( "Pedido.findByDate", Pedido.class);
        query.setParameter( "f", date);
        res = query.getResultList();

        return res;
    }
    
}
