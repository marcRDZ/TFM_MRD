/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.sessionbeans;

import diaz.rodriguez.entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "diaz.rodriguez_EAppTfm-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario getUsuarioUsernamePassword(String username, String password){
        Usuario res = null;
        Query query = em.createNamedQuery( "Usuario.findByUsernamePassword", Usuario.class);
        query.setParameter( "u", username );
        query.setParameter( "p", password );
        //res = (Usuario) query.getSingleResult();
        try{

            res = (Usuario) query.getSingleResult();

        }
        catch (Exception ex){

            res=null;

        }
        return res;
    }
    
}
