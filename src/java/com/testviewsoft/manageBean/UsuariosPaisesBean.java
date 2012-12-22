/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.manageBean;

import com.testviewsoft.dao.impl.UsuariosDaoImpl;
import com.testviewsoft.dao.impl.UsuariosPaisesDaoImpl;
import com.testviewsoft.entity.DocumentosIdentidad;
import com.testviewsoft.entity.Usuarios;
import com.testviewsoft.entity.UsuariosPaises;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * ManageBean UsuariosBean.
 * En este ManageBean se controlan todos los eventos correspondientes
 * Al CRUD de la entidad "Usuarios" lanzados desde la vista:
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * @see Usuarios ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
 * @author Gerlin Orlando Torres Saavedra.
 */
@ManagedBean
@SessionScoped
public class UsuariosPaisesBean implements Serializable{
    private UsuariosPaises entidad;
    private List<UsuariosPaises> entidades;
    private List<UsuariosPaises> usuariosPaisesEliminados;
    private String idDocumentoIdentificacion;
    private String idPais;
    private String idUsuario;
    private Usuarios usuarios;
    /**
     * Constructor UsuariosPaisesBean() (Vacio).
     * Se crea una instancia del ManageBean y se crea un objeto de Tipo "UsuariosPaises" (variable entidad [Global]), 
     * el cual tiene como objetivo recibir y devolver los valores de la vista.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public UsuariosPaisesBean() {
        Log("se crea un objeto usuariosbean");
        entidad=new UsuariosPaises();
        getUsuariosPaises();
    }

    public UsuariosPaises getUsuarioPais() {
        return entidad;
    }

    public void setUsuarioPais(UsuariosPaises entidad) {
        this.entidad = entidad;
    }

    public String getIdDocumentoIdentificacion() {
        return idDocumentoIdentificacion;
    }

    public void setIdDocumentoIdentificacion(String idDocumentoIdentificacion) {
        this.idDocumentoIdentificacion = idDocumentoIdentificacion;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    
    /**
     * Metodo getUsuariosPaises().
     * Trae de la base de datos los UsuariosPaises que se encuentran almacenados.
     * @return Retorna una lista Vacia si no hay ningun UsuariosPaises registrado en la base de datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<UsuariosPaises> getUsuariosPaises() {
        UsuariosPaisesDaoImpl daoImpl=new UsuariosPaisesDaoImpl();
        entidades=daoImpl.buscarActivos();
        return entidades;
    }
    /**
     * Metodo insertar().
     * Toma la instancia de tipo "UsuariosPaises" (Global) y la envia para su registro,
     * Y la respuesta del proceso de registro la envia a la vista en un FaceMessage.
     * @see UsuariosPaises Entity[UsuariosPaises]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void insertar(){
        Log("METODO INSERTAR Usuarios");
        UsuariosPaisesDaoImpl daoImpl=new UsuariosPaisesDaoImpl();
        String mensaje=daoImpl.registrar(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE UsuariosPaises",mensaje)); 
    }
    /**
     * Metodo actualizar().
     * Toma la instancia de tipo "UsuariosPaises" (variable entidad [Global]) seteada por el metodo prepararActualizacion(Integer id), 
     * y la envia para su modificacion, la respuesta del proceso de modificacion la envia a la vista 
     * en un FaceMessage.
     * @see UsuariosPaises Entity[UsuariosPaises]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @see prepararActualizacion(Integer id)
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void actualizar(){
        Log("METODO ACTUALIZAR UsuariosPaises");
        UsuariosPaisesDaoImpl daoImpl=new UsuariosPaisesDaoImpl();
        String mensaje=daoImpl.actualizar(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE USUARIO PAISES",mensaje));        
    }
    /**
     * Metodo prepararActualizacion(Integer id).
     * Recibe el Id (Llave Primaria) de la relacion usuario-paises a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto de tipo "UsuariosPaises" completo (variable entidad [Global]), para posteriormente asignarlo a la variable "entidad" (Global), 
     * para que cuando se llame el metodo actualizar() el objeto tenga sus valores modificados.
     * @see UsuariosPaises Entity[UsuariosPaises]: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @see actualizar() modifica el objeto UsuariosPaises.
     * @param id primaryKey de la entidad de tipo "UsuariosPaises" a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL UsuariosPaises");
        UsuariosPaisesDaoImpl daoImpl=new UsuariosPaisesDaoImpl();
        entidad=daoImpl.buscarPorId(id);
    }
    /**
     * Metodo eliminar().
     * Toma la instancia de tipo "UsuariosPaises" (variable entidad [Global]) seteada por el metodo prepararEliminacion(Integer id), 
     * y la envia para su Eliminacion, la respuesta del proceso de eliminacion la envia a la vista 
     * en un FaceMessage. Hay que recalcar que la eliminicion literalmente no se realiza, solo se le 
     * cambia el estado al registro, pero la informacion sigue en la base de datos para futuras inconsistencias.
     * @see UsuariosPaises Entity[UsuariosPaises]: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @see prepararEliminacion(Integer id).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void eliminar(){
        Log("METODO ELIMINAR UsuariosPaises");
        UsuariosPaisesDaoImpl daoImpl=new UsuariosPaisesDaoImpl();
        String mensaje=daoImpl.inactivarRegistro(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE USUARIOS-PAISES",mensaje));

    }
    /**
     * Metodo prepararEliminacion(Integer id).
     * Recive el Id (llave primaria) del Usuario-Pais a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto de tipo "UsuariosPaises" (variable entidad [Global]) completo, para posteriormente 
     * asignarlo a la variable "entidad" (Global), para que cuando se llame el metodo eliminar() el 
     * objeto tenga sus valores completos para su eliminacion.
     * @see eliminar() modifica el objeto de tipo "UsuariosPaises" (variable entidad [Global]).
     * @param id identificacion (PrimaryKey) del UsuariosPaises a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL USUARIO-PAIS");
        UsuariosPaisesDaoImpl daoImpl=new UsuariosPaisesDaoImpl();
        entidad=daoImpl.buscarPorId(id);
    }
    /**
     * Metodo listaSelectItemPaisesActivos().
     * Llama al metodo UsuariosPaisesDaoImpl.listaSelectItemPaisesActivos() de la clase UsuariosPaisesDaoImpl 
     * para traer una lista de SelectItem con los Paises activos. Este metodo es llamado desde la 
     * vista para llenar un "selectOneMenu" (Combo o Lista Desplegable) con los Paises que estan activos para el registro
     * de un usuario.
     * @see UsuariosPaisesDaoImpl listaSelectItemDocumentosIdentidadActivos().
     * @param id identificacion (PrimaryKey) del Usuario a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<SelectItem> listaSelectItemPaisesActivos(){
        UsuariosDaoImpl daoImpl=new UsuariosDaoImpl();
        return daoImpl.listaSelectItemPaisesActivos();
    }
    /**
     * Metodo Log(String msn).
     * Tiene como objetivo imprimir banderas durante la ejecucion del programa y lo imprime como si fuera 
     * un WARNING, para resaltarse.
     * @param msn de tipo String, Cadena a imprimir.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }
    /**
     * Metodo inicializar().
     * Este metodo inicializa o prepara las variables globales para su posterior uso.
     * @author Gerlin Orlando Torres Saavedra
     */
    private void inicializar(){
        Log("Se INICIALIZARON LOS VALORES");
        idDocumentoIdentificacion=null;
        usuariosPaisesEliminados=new ArrayList<UsuariosPaises>();
        entidad=new UsuariosPaises();
        entidad.setUsuariosId(new Usuarios());
        entidades=new ArrayList<UsuariosPaises>();
        usuarios=new Usuarios();
    }
    /**
     * Metodo cancelar().
     * Este metodo prepara el ManageBean al momento que se ha cancelado un intento de registro.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void cancelar(){
        inicializar();
    }
    /**
     * Metodo agregarPaisAlUsuario().
     * Este metodo permite agregar muchos Paises al usuario en proceso de registro o de modificacion.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void agregarPaisAlUsuario(){
        Log("Agregar Pais a la lista usuariospaises");
        this.entidades.add(entidad);
        Usuarios usuario=entidad.getUsuariosId();
        entidad=new UsuariosPaises();
        entidad.setUsuariosId(usuario);
    }
}

