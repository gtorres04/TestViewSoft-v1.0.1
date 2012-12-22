/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.manageBean;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.impl.DocumentosIdentidadDaoImpl;
import com.testviewsoft.entity.DocumentosIdentidad;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * ManageBean DocumentosIdentidadBean.
 * En este ManageBean se controlan todos los eventos correspondientes
 * Al CRUD de la entidad DocumentosIdentidad lansados desde la vista:
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * @author Gerlin Orlando Torres Saavedra.
 */
@ManagedBean
@RequestScoped
public class DocumentosIdentidadBean {
    private DocumentosIdentidad entidad;
    private List<DocumentosIdentidad> entidades;
    /**
     * Constructor DocumentosIdentidadBeanBean (Vacio).
     * Se crea una instancia del ManageBean y se crea un objeto de Tipo DocumentosIdentidadBean, 
     * el cual tiene como objetivo, recibir y devolver los valores de la vista. 
     * Ademas inicializa la lista de los DocumentosIdentidadBean que se han registrado hasta el momento.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public DocumentosIdentidadBean() {
        Log("se crea un objeto DocumentosIdentidadBeanbean");
        entidad=new DocumentosIdentidad();
        getDocumentosIdentidad();
    }

    public DocumentosIdentidad getDocumentosIdentidad() {
        return entidad;
    }

    public void setDocumentosIdentidad(DocumentosIdentidad entidad) {
        this.entidad = entidad;
    }
    /**
     * Metodo getDocumentosIdentidad().
     * Trae de la base de datos los documentos de Identidad que se encuentran almacenados
     * @return Una lista Vacia si no hay ningun DocumentosIdentidad registrado en la base de datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<DocumentosIdentidad> getDocumentosIdentidades() {
        DocumentosIdentidadDaoImpl documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        entidades=documentosIdentidadDao.buscarActivos();
        return entidades;
    }
    /**
     * Metodo insertar().
     * Toma la instancia "entidad" (Global) y la envia para su registro,
     * Y la respuesta del proceso de registro la envia a la vista en un FaceMessage.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void insertar(){
        Log("METODO INSERTAR DOCUMENTOS IDENTIDAD");
        DaoImpl documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        String mensaje=documentosIdentidadDao.registrar(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE DOCUMENTOS IDENTIDAD",mensaje));        
    }
    /**
     * Metodo actualizar().
     * Toma la instancia "entidad" (Global), seteada por el metodo @see prepararActualizacion(Integer id), 
     * y la envia para su modificacion, la respuesta del proceso de modificacion la envia a la vista 
     * en un FaceMessage.
     * @see prepararActualizacion(Integer id).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void actualizar(){
        Log("METODO ACTUALIZAR DOCUMENTOS IDENTIDAD");
        DaoImpl documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        String mensaje=documentosIdentidadDao.actualizar(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE Documentos Identidad",mensaje));        
    }
    /**
     * Metodo prepararActualizacion(Integer id).
     * Recive el Id del DocumentosIdentidad a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto DocumentosIdentidad completo, para posteriormente asignarlo a la variable "entidad" (Global), 
     * para que cuando se llame el metodo @see actualizar() el objeto tenga sus valores modificados.
     * @see actualizar() modifica el objeto DocumentosIdentidad.
     * @param id identificados del DocumentosIdentidad a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL DocumentosIdentidad");
        DocumentosIdentidadDaoImpl daoImpl=new DocumentosIdentidadDaoImpl();
        entidad=daoImpl.buscarPorId(id);
    }
    /**
     * Metodo eliminar().
     * Toma la instancia "entidad" (Global), seteada por el metodo @see prepararEliminacion(Integer id), 
     * y la envia para su Eliminacion, la respuesta del proceso de eliminacion la envia a la vista 
     * en un FaceMessage. Hay que recalcar que la eliminicion literalmente no se realiza, solo se le 
     * cambia el estado al registro, pero la informacion sigue en la base de datos para futuras inconsistencias.
     * @see prepararEliminacion(Integer id).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void eliminar(){
        Log("METODO ELIMINAR DocumentosIdentidad");
        DocumentosIdentidadDaoImpl daoImpl=new DocumentosIdentidadDaoImpl();
        String mensaje=daoImpl.inactivarRegistro(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE DOCUMENTO DE IDENTIDAD",mensaje));
    }
    /**
     * Metodo prepararEliminacion(Integer id).
     * Recive el Id del DocumentosIdentidad a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto DocumentosIdentidad completo, para posteriormente asignarlo a la variable "entidad" (Global), 
     * para que cuando se llame el metodo @see eliminar() el objeto tenga sus valores 
     * completos para su eliminacion.
     * @see eliminar() modifica el objeto DocumentosIdentidad.
     * @param id identificados del DocumentosIdentidad a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL DOCUMENTO DE IDENTIDAD");
        DocumentosIdentidadDaoImpl daoImpl=new DocumentosIdentidadDaoImpl();
        entidad=daoImpl.buscarPorId(id);
    }
    /**
     * Metodo Log(String msn).
     * Tiene como objetivo imprimir banderas durante la ejecucion del programa y lo imprime como si fuera 
     * un WARNING, para resaltarse.
     * @param msn de tipo String, Cadena a imprimir.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }    
}