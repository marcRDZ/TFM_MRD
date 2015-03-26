/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.managedbeans;

import diaz.rodriguez.entities.Item;
import diaz.rodriguez.entities.Pedido;
import diaz.rodriguez.entities.Usuario;
import diaz.rodriguez.sessionbeans.PedidoFacadeLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author mars
 */
@RequestScoped
@Named(value = "cartManager")
public class CartManager {
    @EJB
    private PedidoFacadeLocal pedidoFacade;
    private FacesContext fContext;
    private Usuario logUser;
    private Pedido p;
    private Item selectIt;

    /**
     * Creates a new instance of CartManager
     */
    public CartManager() {
            
        fContext = FacesContext.getCurrentInstance();
        logUser = (Usuario) fContext.getExternalContext().getSessionMap().get("usuario");
        p = (Pedido) fContext.getExternalContext().getSessionMap().get("pedido");

    }

    public void setSelectIt(Item selectIt) {
        this.selectIt = selectIt;
    }

    public void addToCart() {
        
        FacesMessage message = null;
        
        if(logUser == null){
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Para realizar pedidos primero debe identificarse");      
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        else{
            if(p == null){
                p = new Pedido();
                p.setUsuario(logUser);
                p.setFechaPedido(new Date());
            }
            try {
                if(p.getCompra().add(selectIt)){
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Artículo añadido");      
                    p.setSuma(p.getSuma().add(selectIt.getPrecio()));                    
                    fContext.getExternalContext().getSessionMap().put("pedido", p);   
                }
                else{
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se ha podido añadir el artículo");
                }
            } catch (Exception e) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", e.toString());

            }      
            
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
        
    }
    
    public void removeFromCart(){
        
        FacesMessage message = null;
        try {
            if(p.getCompra().remove(selectIt)){
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Artículo retirado de la lista");
                p.setSuma(p.getSuma().subtract(selectIt.getPrecio()));                
                fContext.getExternalContext().getSessionMap().put("pedido", p);   
            }
            else{
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se ha podido quitar el artículo");
            }
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", e.toString());

        }      

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void checkOut(ActionEvent event){
        
        FacesMessage message = null;
        Pedido compra = pedidoFacade.create(p);
        if ( compra == null ) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se ha podido enviar el pedido.");
        }
        else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Enhorabuena", "Su pedido ha sido procesado");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
