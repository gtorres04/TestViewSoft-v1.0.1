/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GerlinOrlandoTorresSaavedra
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "referencia_identificacion")
    private String referenciaIdentificacion;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 1)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 255)
    @Column(name = "mail")
    private String mail;
    @Column(name = "nacionalidad")
    private Integer nacionalidad;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "tiempo_estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiempoEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosId")
    private List<UsuariosPaises> usuariosPaisesList;
    @JoinColumn(name = "tipo_identificacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DocumentosIdentidad tipoIdentificacion;

    public Usuarios() {
    }

    public Usuarios(Integer id) {
        this.id = id;
    }

    public Usuarios(Integer id, String referenciaIdentificacion) {
        this.id = id;
        this.referenciaIdentificacion = referenciaIdentificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReferenciaIdentificacion() {
        return referenciaIdentificacion;
    }

    public void setReferenciaIdentificacion(String referenciaIdentificacion) {
        this.referenciaIdentificacion = referenciaIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Integer nacionalidad) {
        this.nacionalidad = nacionalidad;
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

    @XmlTransient
    public List<UsuariosPaises> getUsuariosPaisesList() {
        return usuariosPaisesList;
    }

    public void setUsuariosPaisesList(List<UsuariosPaises> usuariosPaisesList) {
        this.usuariosPaisesList = usuariosPaisesList;
    }

    public DocumentosIdentidad getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(DocumentosIdentidad tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.testviewsoft.entity.Usuarios[ id=" + id + " ]";
    }
    
}
