/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.sessionbeans;

import diaz.rodriguez.entities.Pedido;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PedidoFacadeLocal {

    Pedido create(Pedido pedido);

    Pedido edit(Pedido pedido);

    void remove(Pedido pedido);

    Pedido find(Object id);

    List<Pedido> findAll();
    
    List<Pedido> findByUser(String user);
    
    List<Pedido> findByDate(Date date);
       
}
