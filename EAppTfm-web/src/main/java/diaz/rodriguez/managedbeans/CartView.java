/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.managedbeans;

import diaz.rodriguez.entities.Item;
import diaz.rodriguez.entities.Pedido;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mars
 */
@ViewScoped
@Named(value = "cartView")
public class CartView implements Serializable {

    private FacesContext fContext;
    private Pedido pedido;
    private Item it;
    private List<Item> listPedido;
    
    public CartView(){
        
        fContext = FacesContext.getCurrentInstance();
        pedido = (Pedido) fContext.getExternalContext().getSessionMap().get("pedido");
        
    }

    @PostConstruct
    public void init(){        
        listPedido = pedido.getCompra();
    }
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Item getIt() {
        return it;
    }

    public void setIt(Item it) {
        this.it = it;
    }

    public List<Item> getListPedido() {
        return listPedido;
    }

    public void setListPedido(List<Item> listPedido) {
        this.listPedido = listPedido;
    }
    
}
