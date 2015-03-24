/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.managedbeans;

import diaz.rodriguez.entities.Usuario;
import diaz.rodriguez.sessionbeans.UsuarioFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author mars
 */
@Named(value = "registerManagedBean")
@RequestScoped
public class RegisterManagedBean {
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private FacesContext context;
    private Usuario usuario;
    private String repassword;
    
    public RegisterManagedBean() {        
        usuario = new Usuario();
        context = FacesContext.getCurrentInstance();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public String getRepassword() {
        return repassword;
    }
    
    public void setRepassword( String repassword ) {
        this.repassword = repassword;
    }
    
    public String register(ActionEvent event) {
        
        String res = validate();
        FacesMessage message = null;

        if ( !res.equals( "ERROR" ) ) {
            Usuario u = usuarioFacade.create(usuario);
            if ( u == null ) {
                context.addMessage( null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No se ha podido crear el usuario." ) );
                res = "ERROR";
            }
            else {
                context.addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "El usuario ha sido creado."));
            }
        }
        return res;
    }
    
    private String validate() {
        String res = "OK";
        if ( !usuario.getPassword().equals( repassword ) ) {
            context.addMessage( null, new
            FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Las contraseñas no son iguales." ));
            res = "ERROR";
        }
        return res;
    }
}
