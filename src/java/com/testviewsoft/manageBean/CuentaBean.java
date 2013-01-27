/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.manageBean;

import com.testviewsoft.dao.impl.CuentaDaoImpl;
import com.testviewsoft.entity.Cuenta;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * ManageBean CuentaBean.
 * En este ManageBean se controlan todos los eventos correspondientes
 * Al CRUD de la entidad "Cuenta" lanzados desde la vista:
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * @see Cuenta ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
 * @author Gerlin Orlando Torres Saavedra.
 */
@ManagedBean
@RequestScoped
public class CuentaBean {
    private Cuenta entidad;
    private List<Cuenta> entidades;
    /**
     * Constructor CuentaBean() (Vacio).
     * Se crea una instancia del ManageBean y se crea un objeto de Tipo Cuenta (variable entidad [Global]), 
     * el cual tiene como objetivo recibir y devolver los valores de la vista. 
     * Ademas inicializa la lista de los usuarios que se han registrado hasta el momento en la base de datos, 
     * para que la vista los capture y los muestre en la tabla (grid).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public CuentaBean() {
        Log("se crea un objeto usuariosbean");
        entidad=new Cuenta();
        getCuenta();
    }

    public Cuenta getCuenta() {
        return entidad;
    }

    public void setCuenta(Cuenta entidad) {
        this.entidad = entidad;
    }
    /**
     * Metodo getCuenta().
     * Trae de la base de datos los usuarios que se encuentran almacenados.
     * @return Retorna una lista Vacia si no hay ningun usuario registrado en la base de datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Cuenta> getCuentas() {
        CuentaDaoImpl daoImpl=new CuentaDaoImpl();
        entidades=daoImpl.buscarActivos();
        return entidades;
    }
    /**
     * Metodo insertar().
     * Toma la instancia de tipo "Cuenta" (Global) y la envia para su registro,
     * Y la respuesta del proceso de registro la envia a la vista en un FaceMessage.
     * @see Cuenta ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void insertar(){
        Log("METODO INSERTAR Cuenta");
        CuentaDaoImpl daoImpl=new CuentaDaoImpl();
        String mensaje=daoImpl.registrar(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE USUARIO",mensaje)); 
    }
    /**
     * Metodo actualizar().
     * Toma la instancia de tipo "Cuenta" (variable entidad [Global]) seteada por el metodo prepararActualizacion(Integer id), 
     * y la envia para su modificacion, la respuesta del proceso de modificacion la envia a la vista 
     * en un FaceMessage.
     * @see Cuenta ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @see prepararActualizacion(Integer id)
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void actualizar(){
        Log("METODO ACTUALIZAR USUARIO");
        CuentaDaoImpl daoImpl=new CuentaDaoImpl();
        String mensaje=daoImpl.actualizar(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE USUARIO",mensaje));        
    }
    /**
     * Metodo prepararActualizacion(Integer id).
     * Recibe el Id (Llave Primaria) del Cuenta a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto de tipo "Cuenta" completo (variable entidad [Global]), para posteriormente asignarlo a la variable "entidad" (Global), 
     * para que cuando se llame el metodo actualizar() el objeto tenga sus valores modificados.
     * @see Cuenta Entity[Cuenta]: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @see actualizar() modifica el objeto Cuenta.
     * @param id primaryKey de la entidad de tipo "Cuenta" a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL usuarios");
        CuentaDaoImpl daoImpl=new CuentaDaoImpl();
        entidad=daoImpl.buscarPorId(id);
    }
    /**
     * Metodo eliminar().
     * Toma la instancia de tipo "Cuenta" (variable entidad [Global]) seteada por el metodo prepararEliminacion(Integer id), 
     * y la envia para su Eliminacion, la respuesta del proceso de eliminacion la envia a la vista 
     * en un FaceMessage. Hay que recalcar que la eliminicion literalmente no se realiza, solo se le 
     * cambia el estado al registro, pero la informacion sigue en la base de datos para futuras inconsistencias.
     * @see Cuenta Entity[Cuenta]: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @see prepararEliminacion(Integer id).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void eliminar(){
        Log("METODO ELIMINAR USUARIO");
        CuentaDaoImpl daoImpl=new CuentaDaoImpl();
        String mensaje=daoImpl.inactivarRegistro(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE USUARIOS",mensaje));

    }
    /**
     * Metodo prepararEliminacion(Integer id).
     * Recive el Id (llave primaria) del Cuenta a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto de tipo "Cuenta" (variable entidad [Global]) completo, para posteriormente 
     * asignarlo a la variable "entidad" (Global), para que cuando se llame el metodo eliminar() el 
     * objeto tenga sus valores completos para su eliminacion.
     * @see eliminar() modifica el objeto de tipo "Cuenta" (variable entidad [Global]).
     * @param id identificacion (PrimaryKey) del Cuenta a Modificar.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL USUARIO");
        CuentaDaoImpl daoImpl=new CuentaDaoImpl();
        entidad=daoImpl.buscarPorId(id);
    }
    /**
     * Metodo listaSelectItemDominioActivos().
     * Llama al metodo CuentaDaoImpl.listaSelectItemDominioActivos() de la clase CuentaDaoImpl 
     * para traer una lista de SelectItem con los documentos de Identidad activos. Este metodo es llamado desde la 
     * vista para llenar un "selectOneMenu" (Combo) con los documentos de Identidad que estan activos para el registro
     * de un usuario.
     * @see CuentaDaoImpl listaSelectItemDominioActivos().
     * @param id identificacion (PrimaryKey) del Cuenta a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<SelectItem> listaSelectItemDominioActivos(){
        CuentaDaoImpl daoImpl=new CuentaDaoImpl();
        return daoImpl.listaSelectItemDominioActivos();
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

