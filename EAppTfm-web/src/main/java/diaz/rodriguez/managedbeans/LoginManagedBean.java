/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.managedbeans;

import diaz.rodriguez.entities.Usuario;
import diaz.rodriguez.sessionbeans.UsuarioFacadeLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mars
 */
@Named(value = "loginManagedBean")
@RequestScoped
@ManagedBean
public class LoginManagedBean {
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private FacesContext fContext;
    private Usuario usuario;
    private boolean loggedIn = false;

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
    
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        Usuario u = usuarioFacade.getUsuarioUsernamePassword(usuario.getUsername(), usuario.getPassword());

        if(u == null) {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        } else {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", u.getNombre());
            fContext.getExternalContext().getSessionMap().put( "usuario", u);

        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }       
   
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
}
