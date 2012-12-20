/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.bean;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.impl.PaisesDaoImpl;;
import com.testviewsoft.entity.Paises;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * ManageBean PaisesBean.
 * En este ManageBean se controlan todos los eventos correspondientes
 * Al CRUD de la entidad Paises lansados desde la vista:
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * @author Gerlin Orlando Torres Saavedra
 */
@ManagedBean
@RequestScoped
public class PaisesBean {
    private Paises pais;
    private List<Paises> paises;
    /**
     * Constructor PaisesBean (Vacio).
     * Se crea una instancia del ManageBean y se crea un objeto de Tipo Paises, 
     * el cual tiene como objetivo recivir y devolver los valores de la vista. 
     * Ademas inicializa la lista de los paise que se han registrado hasta el momento
     */
    public PaisesBean() {
        Log("se crea un objeto paisbean");
        pais=new Paises();
        getPaises();
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }
    /**
     * Metodo getPaises().
     * Trae de la base de datos los paises que se encuentran almacenados
     * @return Una lista Vacia si no hay ningun pais registrado en la base de datos
     */
    public List<Paises> getPaises() {
        PaisesDaoImpl paisesDao=new PaisesDaoImpl();
        paises=paisesDao.buscarActivos();
        return paises;
    }
    /**
     * Metodo insertar().
     * Toma la instancia "pais" (Global) y la envia para su registro,
     * Y la respuesta del proceso de registro la envia a la vista en un FaceMessage.
     */
    public void insertar(){
        Log("METODO INSERTAR PAIS");
        DaoImpl paisesDao=new PaisesDaoImpl();
        String mensaje=paisesDao.registrar(pais);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE PAIS",mensaje));        
    }
    /**
     * Metodo actualizar().
     * Toma la instancia "pais" (Global), seteada por el metodo @see prepararActualizacion(Integer id), 
     * y la envia para su modificacion, la respuesta del proceso de modificacion la envia a la vista 
     * en un FaceMessage.
     * @see prepararActualizacion(Integer id)
     */
    public void actualizar(){
        Log("METODO ACTUALIZAR PAIS");
        DaoImpl paisesDao=new PaisesDaoImpl();
        String mensaje=paisesDao.actualizar(pais);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE PAIS",mensaje));        
    }
    /**
     * Metodo prepararActualizacion(Integer id).
     * Recive el Id del Pais a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto PAIS completo, para posteriormente asignarlo a la variable "pais" (Global), 
     * para que cuando se llame el metodo @see actualizar() el objeto tenga sus valores modificados.
     * @see actualizar() modifica el objeto Pais.
     * @param id identificados del Pais a Modificar.
     */
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL PAIS");
        PaisesDaoImpl paisesDao=new PaisesDaoImpl();
        pais=paisesDao.buscarPorId(id);
    }
    /**
     * Metodo eliminar().
     * Toma la instancia "pais" (Global), seteada por el metodo @see prepararEliminacion(Integer id), 
     * y la envia para su Eliminacion, la respuesta del proceso de eliminacion la envia a la vista 
     * en un FaceMessage. Hay que recalcar que la eliminicion literalmente no se realiza, solo se le 
     * cambia el estado al registro, pero la informacion sigue en la base de datos para futuras inconsistencias.
     * @see prepararEliminacion(Integer id)
     */
    public void eliminar(){
        Log("METODO ELIMINAR PAIS");
        PaisesDaoImpl paisesDao=new PaisesDaoImpl();
        String mensaje=paisesDao.inactivarRegistro(pais);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE PAIS",mensaje));

    }
    /**
     * Metodo prepararEliminacion(Integer id).
     * Recive el Id del Pais a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto PAIS completo, para posteriormente asignarlo a la variable "pais" (Global), 
     * para que cuando se llame el metodo @see eliminar() el objeto tenga sus valores 
     * completos para su eliminacion.
     * @see eliminar() modifica el objeto Pais.
     * @param id identificados del Pais a Modificar.
     */
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL PAIS");
        PaisesDaoImpl paisesDao=new PaisesDaoImpl();
        pais=paisesDao.buscarPorId(id);
    }
    /**
     * Metodo Log(String msn).
     * Tiene como objetivo imprimir banderas durante la ejecucion del programa y lo imprime como si fuera 
     * un WARNING, para resaltarse.
     * @param msn de tipo String, Cadena a imprimir.
     */
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }    
}
