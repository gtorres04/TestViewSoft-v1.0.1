/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.Dao;
import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.util.Utileria;
import com.testviewsoft.entity.DocumentosIdentidad;
import com.testviewsoft.entity.Paises;
import com.testviewsoft.entity.Usuarios;
import com.testviewsoft.entity.UsuariosPaises;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
/**
 * Clase UsuariosPaisesDaoImpl: Clase Modelo de la Entidad UsuariosPaises.
 * En este Clase Modelo se intermedia el ManageBean "UsuariosPaisesBean" con toda la logica de negocio 
 * correspondiente a la entidad "UsuariosPaises". Esta clase hereda de la Clase Generica "DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros..
 * @see UsuariosPaises Entity[UsuariosPaises]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
 * @see DaoImpl<T> Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 * @author Gerlin Orlando Torres Saavedra.
 */
public class UsuariosPaisesDaoImpl extends DaoImpl<UsuariosPaises> {
    EntityManagerFactory emf;
    /**
     * Constructor UsuariosPaisesDaoImpl() (Vacio).
     * Se llama la instancia del EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia EntityManagerFactory en una variable global "emf".
     * @see EntityManagerFactory Clase manejadora de la persitencia.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public UsuariosPaisesDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id (llave primaria) de la relacion Usuario-Pais a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "UsuariosPaises")) obteniendo el objeto de tipo "UsuariosPaises" completo.
     * @param id identificacion (primarykey) de la relacion Usuario-Pais a Modificar.
     * @return Una instancia de tipo "UsuariosPaises" del "id" especificado como parametro.
     * @see DaoImpl.buscarPorId(id, "UsuariosPaises")
     * @author Gerlin Orlando Torres Saavedra.
     */
    public UsuariosPaises buscarPorId(Integer id) {
        return super.buscarPorId(id, "UsuariosPaises");
    }
    /**
     * Metodo registrar(UsuariosPaises entidad). @Override
     * Registra la relacion Usuario-Pais en la base de datos.
     * @param entidad referencia de tipo "UsuariosPaises". Usuario-Pais que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     * @see UsuariosPaises Entity[UsuariosPaises]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String registrar(UsuariosPaises entidad) {
        entidad.setTiempoEstado(new Date());
        entidad.setEstado(Boolean.TRUE);
        return super.registrar(entidad);
    }
    /**
     * Metodo actualizar(UsuariosPaises entidad). @Override
     * Modifica la relacion Usuario-Pais en la base de datos.
     * @param entidad referencia de tipo "UsuariosPaises". Usuario-Pais que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     * @see UsuariosPaises Entity[UsuariosPaises]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String actualizar(UsuariosPaises entidad){
        try {
            entidad.setEstado(Boolean.TRUE);
            entidad.setTiempoEstado(new Date());
            return super.actualizar(entidad); 
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }

    /**
     * Metodo eliminar(UsuariosPaises entidad). @Override
     * Elimina la relacion Usuario-Pais en la base de datos.
     * @param entidad referencia de tipo "UsuariosPaises". UsuariosPaises que se intenta eliminar en la base de datos.
     * @see UsuariosPaises Entity[UsuariosPaises]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String eliminar(UsuariosPaises entidad) {
        try {
            entidad.setEstado(Boolean.TRUE);
            entidad.setTiempoEstado(new Date());
            return super.eliminar(entidad); 
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo buscarActivos().
     * Consulta en la base de datos la relacion Usuario-Pais que estan activos, es decir, los que en la base de datos
     * se encuentren en un estado TRUE o 1.
     * @return List<UsuariosPaises> Retorna una lista de la relacion Usuario-Pais que se encuentran activos, es decir, que su estado es TRUE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<UsuariosPaises> buscarActivos() {
        List<UsuariosPaises> listaActivos=super.buscarActivos("UsuariosPaises");
        return listaActivos;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todas las relaciones Usuario-Pais, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<UsuariosPaises>, una lista de las relaciones Usuario-Pais que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<UsuariosPaises> buscarTodos() {
        List<UsuariosPaises> lista=super.buscarTodos("UsuariosPaises");
        return lista;
    }
    /**
     * Metodo inactivarRegistro(UsuariosPaises entidad). @Override
     * Le modifica el estado a la relacion usuario-pais (Inactivar), es decir, modificar el estado a FALSE.
     * @param entidad Parametro de Tipo "UsuariosPaises", cuyo relacion usuario-pais sera el referente para modificar el estado a FALSE.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la inactivacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String inactivarRegistro(UsuariosPaises entidad) {
        try {
            entidad.setEstado(Boolean.FALSE);
            entidad.setTiempoEstado(new Date());
            return super.inactivarRegistro(entidad); 
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo listaSelectItemPaisesActivos().
     * Crear una lista de SelectItems con los Paises activos. Este metodo tiene como objetivo llenar una lista desplegable en la vista.
     * @return List<SelectItem>, Retorna una lista de SelectItems con los datos necesarios de los Paises activos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<SelectItem> listaSelectItemPaisesActivos(){
        PaisesDaoImpl daoImpl=new PaisesDaoImpl();
        List<SelectItem> listaSelectItem=new ArrayList<SelectItem>();
        List<Paises> listaEntidades=daoImpl.buscarActivos();
        if(!listaEntidades.isEmpty())
        for (int i = 0; i < listaEntidades.size(); i++) {
            Paises entidad=listaEntidades.get(i);
            listaSelectItem.add(new SelectItem(""+entidad.getId(), entidad.getNombre()));
        }
        return listaSelectItem;
    }
}
