/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.manageBean;

import com.testviewsoft.dao.impl.DocumentoIdentidadDaoImpl;
import com.testviewsoft.dao.impl.PrivilegioDaoImpl;
import com.testviewsoft.dao.impl.RolDaoImpl;
import com.testviewsoft.dao.impl.PrivilegioRolDaoImpl;
import com.testviewsoft.entity.DocumentoIdentidad;
import com.testviewsoft.entity.Privilegio;
import com.testviewsoft.entity.Rol;
import com.testviewsoft.entity.PrivilegioRol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * ManageBean PrivilegioRolBean.
 * En este ManageBean se controlan todos los eventos correspondientes
 * Al CRUD de la entidad "Rol" lanzados desde la vista:
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * @see Rol ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
 * @author Gerlin Orlando Torres Saavedra.
 */
@ManagedBean
@SessionScoped
public class PrivilegioRolBean implements Serializable{
    private PrivilegioRol entidad;
    private List<PrivilegioRol> entidades;
    private Rol rol;
    /**
     * Constructor PrivilegioRolBean() (Vacio).
     * Se crea una instancia del ManageBean y se crea un objeto de Tipo "PrivilegioRol" (variable entidad [Global]), 
     * el cual tiene como objetivo recibir y devolver los valores de la vista.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public PrivilegioRolBean() {
        inicializar();
    }

    public PrivilegioRol getPrivilegioRol() {
        return entidad;
    }

    public void setPrivilegioRol(PrivilegioRol entidad) {
        this.entidad = entidad;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    /**
     * Metodo getPrivilegioRol().
     * Trae de la base de datos los PrivilegioRol que se encuentran almacenados.
     * @return Retorna una lista Vacia si no hay ningun PrivilegioRol registrado en la base de datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<PrivilegioRol> getRolsPrivilegioes() {
        return entidades;
    }
    /**
     * Metodo insertar().
     * Toma la instancia de tipo "PrivilegioRol" (Global) y la envia para su registro,
     * Y la respuesta del proceso de registro la envia a la vista en un FaceMessage.
     * @see PrivilegioRol Entity[PrivilegioRol]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void insertar(){
        DocumentoIdentidadDaoImpl documentoIdentidadDaoImpl=new DocumentoIdentidadDaoImpl();
        RolDaoImpl usuarioDaoImpl=new RolDaoImpl();
        rol.setPrivilegioRolList(entidades);
        String mensaje=usuarioDaoImpl.registrar(rol);
        inicializar();
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE PrivilegioRol",mensaje)); 
    }
    /**
     * Metodo actualizar().
     * Toma la instancia de tipo "PrivilegioRol" (variable entidad [Global]) seteada por el metodo prepararActualizacion(Integer id), 
     * y la envia para su modificacion, la respuesta del proceso de modificacion la envia a la vista 
     * en un FaceMessage.
     * @see PrivilegioRol Entity[PrivilegioRol]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @see prepararActualizacion(Integer id)
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void actualizar(){
        DocumentoIdentidadDaoImpl documentoIdentidadDaoImpl=new DocumentoIdentidadDaoImpl();
        RolDaoImpl daoImpl=new RolDaoImpl();
        rol.setPrivilegioRolList(entidades);
        String mensaje=daoImpl.actualizar(rol);
        inicializar();
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE USUARIO PAISES",mensaje));        
    }
    /**
     * Metodo prepararActualizacion(Integer id).
     * Recibe el Id (Llave Primaria) del usuario a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto de tipo "Rol" completo (variable rol [Global]), para posteriormente asignarlo a la variable "entidad" (Global), 
     * para que cuando se llame el metodo actualizar() el objeto tenga sus valores modificados.
     * @see PrivilegioRol Entity[PrivilegioRol]: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @see actualizar() modifica el objeto PrivilegioRol.
     * @param id primaryKey de la entidad de tipo "PrivilegioRol" a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL PrivilegioRol");
        RolDaoImpl daoImpl=new RolDaoImpl();
        rol=daoImpl.buscarPorId(id);
        entidades=rol.getPrivilegioRolList();
        System.out.println("");
    }
    /**
     * Metodo eliminar().
     * Toma la instancia de tipo "PrivilegioRol" (variable entidad [Global]) seteada por el metodo prepararEliminacion(Integer id), 
     * y la envia para su Eliminacion, la respuesta del proceso de eliminacion la envia a la vista 
     * en un FaceMessage. Hay que recalcar que la eliminicion literalmente no se realiza, solo se le 
     * cambia el estado al registro, pero la informacion sigue en la base de datos para futuras inconsistencias.
     * @see PrivilegioRol Entity[PrivilegioRol]: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @see prepararEliminacion(Integer id).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void eliminar(){
        Log("METODO ELIMINAR PrivilegioRol");
        RolDaoImpl daoImpl=new RolDaoImpl();
        String mensaje=daoImpl.inactivarRegistro(rol);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE USUARIOS",mensaje));
    }
    /**
     * Metodo prepararEliminacion(Integer id).
     * Recive el Id (llave primaria) del Rol-Privilegio a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto de tipo "PrivilegioRol" (variable entidad [Global]) completo, para posteriormente 
     * asignarlo a la variable "entidad" (Global), para que cuando se llame el metodo eliminar() el 
     * objeto tenga sus valores completos para su eliminacion.
     * @see eliminar() modifica el objeto de tipo "PrivilegioRol" (variable entidad [Global]).
     * @param id identificacion (PrimaryKey) del PrivilegioRol a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL USUARIO-PAIS");
        RolDaoImpl daoImpl=new RolDaoImpl();
        rol=daoImpl.buscarPorId(id);
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
        entidad=new PrivilegioRol();
        entidad.setRolId(new Rol());
        entidades=new ArrayList<PrivilegioRol>();
        rol=new Rol();
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
     * Metodo agregarPrivilegioAlRol().
     * Este metodo permite agregar muchos Privilegio al usuario en proceso de registro o de modificacion.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void agregarPrivilegioAlRol(){
        Log("Agregar Privilegio a la lista rolpaises");
        this.entidades.add(entidad);
        Rol usuario=entidad.getRolId();
        entidad=new PrivilegioRol();
        entidad.setRolId(usuario);
    }
    /**
     * Metodo eliminarPrivilegioRelacionadosAlRol().
     * Este metodo elimina la relacion del pais seleccionado con el Rol en gestion.
     * @param id Identificacion del pais que se intenta eliminar de la relacion con el usuario en gestion.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void eliminarPrivilegioRelacionadosAlRol(Integer id){
        if(!entidades.isEmpty())
        for (int i = 0; i < entidades.size(); i++) {
            PrivilegioRol privilegioRol = entidades.get(i);
            if(privilegioRol.getPrivilegioId().getId().equals(id)){
                entidades.remove(i);
                break;
            }
        }
    }
}

