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
    
    public LoginManagedBean() {
      
        fContext = FacesContext.getCurrentInstance();
        usuario = new Usuario();
    }
    public Usuario getUsuario() {
        return usuario;
    }
    
    public String login() {
        String res = "OK";
        Usuario u = usuarioFacade.getUsuarioUsernamePassword(usuario.getUsername(), usuario.getPassword());
        if ( u == null ) {
            fContext.addMessage( null,
                new FacesMessage( FacesMessage.SEVERITY_INFO, "Error", "No existe un usuario con esa contraseña" ) );
            res = "ERROR";
        }
        else {
            fContext.getExternalContext().getSessionMap().put( "usuario", u);
        }
        return res;
    }
}
