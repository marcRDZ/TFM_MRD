/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaz.rodriguez.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
@NamedQueries({ @NamedQuery(name = "Pedido.findAll", query ="select o from Pedido o"),
                @NamedQuery(name = "Pedido.findByUser", query ="select o from Pedido o where o.usuario=:u"),
                @NamedQuery(name = "Pedido.findByDate", query ="select o from Pedido o where o.fechaPedido=:f")})
@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPedido;
    @Column(nullable = false)
    private BigDecimal suma;
    @ManyToMany
    private List<Item> compra;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public Pedido() {
        compra = new ArrayList<>();
        suma = BigDecimal.ZERO;
    }
    
    public Long getId() {
        return id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public BigDecimal getSuma() {
        return suma;
    }

    public void setSuma(BigDecimal suma) {
        this.suma = suma;
    }

    public List<Item> getCompra() {
        return compra;
    }

    public void setCompra(List<Item> compra) {
        this.compra = compra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diaz.rodriguez.Pedido[ id=" + id + " ]";
    }
    
}
