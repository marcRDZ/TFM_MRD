/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.managedbeans;

import diaz.rodriguez.entities.Item;
import diaz.rodriguez.sessionbeans.ItemFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author mars
 */
@Named(value = "storeView")
@ViewScoped
public class StoreView implements Serializable{
    @EJB
    private ItemFacadeLocal itemFacade;
    private FacesContext fContext;
    private String azOrder, nOrder;   
    private String searchTerm;
    private Item selectedItem;
    private List<Item> resultList;
    FacesMessage message = null;
   
    public StoreView() {
        
        fContext = FacesContext.getCurrentInstance();
        azOrder = "asc";
        nOrder = "asc";
    }
    
    @PostConstruct
    public void init(){
        resultList = findAll();
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    
    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<Item> getResultList() {
        return resultList;
    }

    public void setResultList(List<Item> resultList) {
        this.resultList = resultList;
    }

    public List<Item> findAll(){
        return itemFacade.findAll();
    }
    
    public List<Item> findByName(ActionEvent event){
        resultList = itemFacade.findByName(searchTerm+"%");
        if(resultList == null){
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No hay resultados");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }

        return resultList;
    }
    
    public List<Item> findByCategory(String category){
        resultList = itemFacade.findByCategory(category);      
        return resultList;
    }
    
    public List<Item> azSort(ActionEvent event){
        if( azOrder.equals("asc")){
            azOrder = "desc";
            resultList = itemFacade.orderByNameAsc();
        }
        else{
            azOrder = "asc";
            resultList = itemFacade.orderByNameDesc();
        }
        return resultList;
    }
    
    public List<Item> nSort(ActionEvent event){
        if( nOrder.equals("asc")){
            nOrder = "desc";
            resultList = itemFacade.orderByPriceAsc();
        }
        else{
            nOrder = "asc";
            resultList = itemFacade.orderByPriceDesc();
        }    
        return resultList;
    }
    
}
