/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.managedbeans;

import diaz.rodriguez.entities.Item;
import diaz.rodriguez.entities.Pedido;
import diaz.rodriguez.entities.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mars
 */
@ViewScoped
@Named(value = "userView")
public class UserView implements Serializable{

    
    private FacesContext fContext;
    private Item it;
    private List<Pedido> listCompras;
    private Usuario user;
    
    public UserView() {
        
        fContext = FacesContext.getCurrentInstance();
        user = (Usuario) fContext.getExternalContext().getSessionMap().get("usuario");
        listCompras = user.getPedidos();
    }  

    public Item getIt() {
        return it;
    }

    public void setIt(Item it) {
        this.it = it;
    }

    public List<Pedido> getListCompras() {
        return listCompras;
    }

    public void setListCompras(List<Pedido> listCompras) {
        this.listCompras = listCompras;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
}
