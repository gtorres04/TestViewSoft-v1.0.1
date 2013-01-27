/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.manageBean;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.impl.PrivilegioDaoImpl;
import com.testviewsoft.entity.Privilegio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * ManageBean PrivilegioBean.
 * En este ManageBean se controlan todos los eventos correspondientes
 * Al CRUD de la entidad Privilegio lanzados desde la vista:
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * @author Gerlin Orlando Torres Saavedra.
 */
@ManagedBean
@RequestScoped
public class PrivilegioBean {
    private Privilegio entidad;
    private List<Privilegio> entidades;
    /**
     * Constructor PrivilegioBean (Vacio).
     * Se crea una instancia del ManageBean y se crea un objeto de Tipo Privilegio (variable entidad [Global]), 
     * el cual tiene como objetivo recibir y devolver los valores de la vista. 
     * Ademas inicializa la lista de los privilegios que se han registrado hasta el momento en la base de datos, 
     * para que la vista los capture y los muestre en la tabla (grid).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public PrivilegioBean() {
        Log("se crea un objeto privilegiobean");
        entidad=new Privilegio();
        getPrivilegio();
    }

    public Privilegio getPrivilegio() {
        return entidad;
    }

    public void setPrivilegio(Privilegio entidad) {
        this.entidad = entidad;
    }
    /**
     * Metodo getPrivilegio().
     * Trae de la base de datos los privilegios que se encuentran almacenados.
     * @return Retorna una lista Vacia si no hay ningun privilegio registrado en la base de datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Privilegio> getPrivilegios() {
        PrivilegioDaoImpl daoImpl=new PrivilegioDaoImpl();
        entidades=daoImpl.buscarActivos();
        return entidades;
    }
    /**
     * Metodo insertar().
     * Toma la instancia "entidad" (Global) y la envia para su registro,
     * Y la respuesta del proceso de registro la envia a la vista en un FaceMessage.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void insertar(){
        Log("METODO INSERTAR PRIVILEGIO");
        DaoImpl daoImpl=new PrivilegioDaoImpl();
        String mensaje=daoImpl.registrar(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE PRIVILEGIO",mensaje));        
    }
    /**
     * Metodo actualizar().
     * Toma la instancia de tipo privilegio (variable entidad [Global]) seteada por el metodo prepararActualizacion(Integer id), 
     * y la envia para su modificacion, la respuesta del proceso de modificacion la envia a la vista 
     * en un FaceMessage.
     * @see prepararActualizacion(Integer id)
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void actualizar(){
        Log("METODO ACTUALIZAR PRIVILEGIO");
        DaoImpl daoImpl=new PrivilegioDaoImpl();
        String mensaje=daoImpl.actualizar(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE PRIVILEGIO",mensaje));        
    }
    /**
     * Metodo prepararActualizacion(Integer id).
     * Recive el Id (Llave Primaria) del Privilegio a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto de tipo "Privilegio" completo (variable entidad [Global]), para posteriormente asignarlo a la variable "entidad" (Global), 
     * para que cuando se llame el metodo actualizar() el objeto tenga sus valores modificados.
     * @see actualizar() modifica el objeto Privilegio.
     * @param id primaryKey de la entidad de tipo Privilegio a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL PRIVILEGIO");
        PrivilegioDaoImpl daoImpl=new PrivilegioDaoImpl();
        entidad=daoImpl.buscarPorId(id);
    }
    /**
     * Metodo eliminar().
     * Toma la instancia "entidad" (Global) seteada por el metodo prepararEliminacion(Integer id), 
     * y la envia para su Eliminacion, la respuesta del proceso de eliminacion la envia a la vista 
     * en un FaceMessage. Hay que recalcar que la eliminicion literalmente no se realiza, solo se le 
     * cambia el estado al registro, pero la informacion sigue en la base de datos para futuras inconsistencias.
     * @see prepararEliminacion(Integer id).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void eliminar(){
        Log("METODO ELIMINAR PRIVILEGIO");
        PrivilegioDaoImpl daoImpl=new PrivilegioDaoImpl();
        String mensaje=daoImpl.inactivarRegistro(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE PRIVILEGIO",mensaje));

    }
    /**
     * Metodo prepararEliminacion(Integer id).
     * Recive el Id (llave primaria) del Privilegio a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto de tipo "Privilegio" (variable entidad [Global]) completo, para posteriormente 
     * asignarlo a la variable "entidad" (Global), para que cuando se llame el metodo @see eliminar() el 
     * objeto tenga sus valores completos para su eliminacion.
     * @see eliminar() modifica el objeto Privilegio (variable entidad [Global]).
     * @param id identificados del Privilegio a Modificar.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL PRIVILEGIO");
        PrivilegioDaoImpl daoImpl=new PrivilegioDaoImpl();
        entidad=daoImpl.buscarPorId(id);
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
}
