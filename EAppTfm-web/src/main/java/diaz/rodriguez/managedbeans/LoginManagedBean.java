/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.managedbeans;

import diaz.rodriguez.entities.Usuario;
import diaz.rodriguez.sessionbeans.UsuarioFacadeLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mars
 */
@RequestScoped
@Named(value = "loginManagedBean")
public class LoginManagedBean {
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private FacesContext fContext;
    private Usuario usuario;
    private boolean loggedIn;

    public LoginManagedBean() {
      
        fContext = FacesContext.getCurrentInstance();
        usuario = new Usuario();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }   
    
    public String login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        String res = "index";
        Usuario u = usuarioFacade.getUsuarioUsernamePassword(usuario.getUsername(), usuario.getPassword());

        if(u == null) {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No existe un usuario con esas credenciales");
            res = "register";
        } else {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", u.getNombre());
            fContext.getExternalContext().getSessionMap().put("usuario", u);

        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        return res;
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    } 
}
