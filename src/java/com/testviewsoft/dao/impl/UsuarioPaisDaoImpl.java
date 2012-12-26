/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.util.Utileria;
import com.testviewsoft.entity.Pais;
import com.testviewsoft.entity.UsuarioPais;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
/**
 * Clase UsuarioPaisDaoImpl: Clase Modelo de la Entidad UsuarioPais.
 * En este Clase Modelo se intermedia el ManageBean "UsuarioPaisBean" con toda la logica de negocio 
 * correspondiente a la entidad "UsuarioPais". Esta clase hereda de la Clase Generica "DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros..
 * @see UsuarioPais Entity[UsuarioPais]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
 * @see DaoImpl<T> Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 * @author Gerlin Orlando Torres Saavedra.
 */
public class UsuarioPaisDaoImpl extends DaoImpl<UsuarioPais> {
    EntityManagerFactory emf;
    /**
     * Constructor UsuarioPaisDaoImpl() (Vacio).
     * Se llama la instancia del EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia EntityManagerFactory en una variable global "emf".
     * @see EntityManagerFactory Clase manejadora de la persitencia.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public UsuarioPaisDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id (llave primaria) de la relacion Usuario-Pais a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "UsuarioPais")) obteniendo el objeto de tipo "UsuarioPais" completo.
     * @param id identificacion (primarykey) de la relacion Usuario-Pais a Modificar.
     * @return Una instancia de tipo "UsuarioPais" del "id" especificado como parametro.
     * @see DaoImpl.buscarPorId(id, "UsuarioPais")
     * @author Gerlin Orlando Torres Saavedra.
     */
    public UsuarioPais buscarPorId(Integer id) {
        return super.buscarPorId(id, "UsuarioPais");
    }
    /**
     * Metodo registrar(UsuarioPais entidad). @Override
     * Registra la relacion Usuario-Pais en la base de datos.
     * @param entidad referencia de tipo "UsuarioPais". Usuario-Pais que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     * @see UsuarioPais Entity[UsuarioPais]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String registrar(UsuarioPais entidad) {
        try {
            entidad.setTiempoEstado(new Date());
            entidad.setEstado(Boolean.TRUE);
            return super.registrar(entidad);
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Registrar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Registrar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
    }
    /**
     * Metodo actualizar(UsuarioPais entidad). @Override
     * Modifica la relacion Usuario-Pais en la base de datos.
     * @param entidad referencia de tipo "UsuarioPais". Usuario-Pais que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     * @see UsuarioPais Entity[UsuarioPais]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String actualizar(UsuarioPais entidad){
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
     * Metodo eliminar(UsuarioPais entidad). @Override
     * Elimina la relacion Usuario-Pais en la base de datos.
     * @param entidad referencia de tipo "UsuarioPais". UsuarioPais que se intenta eliminar en la base de datos.
     * @see UsuarioPais Entity[UsuarioPais]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String eliminar(UsuarioPais entidad) {
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
     * @return List<UsuarioPais> Retorna una lista de la relacion Usuario-Pais que se encuentran activos, es decir, que su estado es TRUE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<UsuarioPais> buscarActivos() {
        List<UsuarioPais> listaActivos=super.buscarActivos("UsuarioPais");
        return listaActivos;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todas las relaciones Usuario-Pais, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<UsuarioPais>, una lista de las relaciones Usuario-Pais que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<UsuarioPais> buscarTodos() {
        List<UsuarioPais> lista=super.buscarTodos("UsuarioPais");
        return lista;
    }
    /**
     * Metodo inactivarRegistro(UsuarioPais entidad). @Override
     * Le modifica el estado a la relacion usuario-pais (Inactivar), es decir, modificar el estado a FALSE.
     * @param entidad Parametro de Tipo "UsuarioPais", cuyo relacion usuario-pais sera el referente para modificar el estado a FALSE.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la inactivacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String inactivarRegistro(UsuarioPais entidad) {
        try {
            entidad.setEstado(Boolean.FALSE);
            entidad.setTiempoEstado(new Date());
            return super.inactivarRegistro(entidad); 
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo listaSelectItemPaisActivos().
     * Crear una lista de SelectItems con los Pais activos. Este metodo tiene como objetivo llenar una lista desplegable en la vista.
     * @return List<SelectItem>, Retorna una lista de SelectItems con los datos necesarios de los Pais activos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<SelectItem> listaSelectItemPaisActivos(){
        PaisDaoImpl daoImpl=new PaisDaoImpl();
        List<SelectItem> listaSelectItem=new ArrayList<SelectItem>();
        List<Pais> listaEntidades=daoImpl.buscarActivos();
        if(!listaEntidades.isEmpty())
        for (int i = 0; i < listaEntidades.size(); i++) {
            Pais entidad=listaEntidades.get(i);
            listaSelectItem.add(new SelectItem(""+entidad.getId(), entidad.getNombre()));
        }
        return listaSelectItem;
    }
}
