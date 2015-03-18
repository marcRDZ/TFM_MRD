/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.sessionbeans;

import diaz.rodriguez.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UsuarioFacadeLocal {

    Usuario create(Usuario usuario);

    Usuario edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();
    
    Usuario getUsuarioUsernamePassword(String username, String password);

}
