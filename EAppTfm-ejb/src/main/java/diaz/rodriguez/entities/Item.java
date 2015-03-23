/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@NamedQueries({ @NamedQuery(name = "Item.findAll", query ="select o from Item o"),
                @NamedQuery(name = "Item.findByName", query ="select o from Item o where o.nombre like :n"),
                @NamedQuery(name = "Item.findByCategory", query ="select o from Item o where o.categoria=:c"),
                @NamedQuery(name = "Item.orderByPriceAsc", query ="select o from Item o order by o.precio asc"),
                @NamedQuery(name = "Item.orderByPriceDesc", query ="select o from Item o order by o.precio desc"),
                @NamedQuery(name = "Item.orderByNameAsc", query ="select o from Item o order by o.nombre asc"),
                @NamedQuery(name = "Item.orderByNameDesc", query ="select o from Item o order by o.nombre desc")})
@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String categoria;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private BigDecimal precio;
    @ManyToMany(mappedBy = "pedido", cascade=CascadeType.ALL)
    private List<Pedido> ventas;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public List<Pedido> getVentas() {
        return ventas;
    }

    public void setVentas(List<Pedido> ventas) {
        this.ventas = ventas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diaz.rodriguez.Item[ id=" + id + " ]";
    }
    
}
