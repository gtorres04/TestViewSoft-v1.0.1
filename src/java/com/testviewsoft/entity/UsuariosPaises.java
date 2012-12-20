/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GerlinOrlandoTorresSaavedra
 */
@Entity
@Table(name = "usuarios_paises")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosPaises.findAll", query = "SELECT u FROM UsuariosPaises u")})
public class UsuariosPaises implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "gentilicio")
    private String gentilicio;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "tiempo_estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiempoEstado;
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios usuariosId;
    @JoinColumn(name = "paises_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paises paisesId;

    public UsuariosPaises() {
    }

    public UsuariosPaises(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGentilicio() {
        return gentilicio;
    }

    public void setGentilicio(String gentilicio) {
        this.gentilicio = gentilicio;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getTiempoEstado() {
        return tiempoEstado;
    }

    public void setTiempoEstado(Date tiempoEstado) {
        this.tiempoEstado = tiempoEstado;
    }

    public Usuarios getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(Usuarios usuariosId) {
        this.usuariosId = usuariosId;
    }

    public Paises getPaisesId() {
        return paisesId;
    }

    public void setPaisesId(Paises paisesId) {
        this.paisesId = paisesId;
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
        if (!(object instanceof UsuariosPaises)) {
            return false;
        }
        UsuariosPaises other = (UsuariosPaises) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.testviewsoft.entity.UsuariosPaises[ id=" + id + " ]";
    }
    
}
