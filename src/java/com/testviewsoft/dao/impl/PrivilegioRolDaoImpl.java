/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.util.Utileria;
import com.testviewsoft.entity.Privilegio;
import com.testviewsoft.entity.PrivilegioRol;
import com.testviewsoft.entity.Rol;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
/**
 * Clase PrivilegioRolDaoImpl: Clase Modelo de la Entidad PrivilegioRol.
 * En este Clase Modelo se intermedia el ManageBean "PrivilegioRolBean" con toda la logica de negocio 
 * correspondiente a la entidad "PrivilegioRol". Esta clase hereda de la Clase Generica "DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros..
 * @see PrivilegioRol Entity[PrivilegioRol]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
 * @see DaoImpl<T> Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 * @author Gerlin Orlando Torres Saavedra.
 */
public class PrivilegioRolDaoImpl extends DaoImpl<PrivilegioRol> {
    EntityManagerFactory emf;
    /**
     * Constructor PrivilegioRolDaoImpl() (Vacio).
     * Se llama la instancia del EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia EntityManagerFactory en una variable global "emf".
     * @see EntityManagerFactory Clase manejadora de la persitencia.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public PrivilegioRolDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id (llave primaria) de la relacion Usuario-Privilegio a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "PrivilegioRol")) obteniendo el objeto de tipo "PrivilegioRol" completo.
     * @param id identificacion (primarykey) de la relacion Usuario-Privilegio a Modificar.
     * @return Una instancia de tipo "PrivilegioRol" del "id" especificado como parametro.
     * @see DaoImpl.buscarPorId(id, "PrivilegioRol")
     * @author Gerlin Orlando Torres Saavedra.
     */
    public PrivilegioRol buscarPorId(Integer id) {
        return super.buscarPorId(id, "PrivilegioRol");
    }
    /**
     * Metodo registrar(PrivilegioRol entidad). @Override
     * Registra la relacion Usuario-Privilegio en la base de datos.
     * @param entidad referencia de tipo "PrivilegioRol". Usuario-Privilegio que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     * @see PrivilegioRol Entity[PrivilegioRol]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String registrar(PrivilegioRol entidad) {
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
     * Metodo actualizar(PrivilegioRol entidad). @Override
     * Modifica la relacion Usuario-Privilegio en la base de datos.
     * @param entidad referencia de tipo "PrivilegioRol". Usuario-Privilegio que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     * @see PrivilegioRol Entity[PrivilegioRol]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String actualizar(PrivilegioRol entidad){
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
     * Metodo eliminar(PrivilegioRol entidad). @Override
     * Elimina la relacion Usuario-Privilegio en la base de datos.
     * @param entidad referencia de tipo "PrivilegioRol". PrivilegioRol que se intenta eliminar en la base de datos.
     * @see PrivilegioRol Entity[PrivilegioRol]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String eliminar(PrivilegioRol entidad) {
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
     * Consulta en la base de datos la relacion Usuario-Privilegio que estan activos, es decir, los que en la base de datos
     * se encuentren en un estado TRUE o 1.
     * @return List<PrivilegioRol> Retorna una lista de la relacion Usuario-Privilegio que se encuentran activos, es decir, que su estado es TRUE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<PrivilegioRol> buscarActivos() {
        List<PrivilegioRol> listaActivos=super.buscarActivos("PrivilegioRol");
        return listaActivos;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todas las relaciones Usuario-Privilegio, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<PrivilegioRol>, una lista de las relaciones Usuario-Privilegio que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<PrivilegioRol> buscarTodos() {
        List<PrivilegioRol> lista=super.buscarTodos("PrivilegioRol");
        return lista;
    }
    /**
     * Metodo inactivarRegistro(PrivilegioRol entidad). @Override
     * Le modifica el estado a la relacion usuario-pais (Inactivar), es decir, modificar el estado a FALSE.
     * @param entidad Parametro de Tipo "PrivilegioRol", cuyo relacion usuario-pais sera el referente para modificar el estado a FALSE.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la inactivacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String inactivarRegistro(PrivilegioRol entidad) {
        try {
            entidad.setEstado(Boolean.FALSE);
            entidad.setTiempoEstado(new Date());
            return super.inactivarRegistro(entidad); 
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo listaSelectItemPrivilegioActivos().
     * Crear una lista de SelectItems con los Privilegio activos. Este metodo tiene como objetivo llenar una lista desplegable en la vista.
     * @return List<SelectItem>, Retorna una lista de SelectItems con los datos necesarios de los Privilegio activos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<SelectItem> listaSelectItemPrivilegioActivos(){
        PrivilegioDaoImpl daoImpl=new PrivilegioDaoImpl();
        List<SelectItem> listaSelectItem=new ArrayList<SelectItem>();
        List<Privilegio> listaEntidades=daoImpl.buscarActivos();
        if(!listaEntidades.isEmpty())
        for (int i = 0; i < listaEntidades.size(); i++) {
            Privilegio entidad=listaEntidades.get(i);
            listaSelectItem.add(new SelectItem(""+entidad.getId(), entidad.getNombre()));
        }
        return listaSelectItem;
    }
    /**
     * Metodo buscarPrivilegiosPorRoles(List<Rol> listadoRoles).
     * Crear una lista de SelectItems con los Privilegio activos. Este metodo tiene como objetivo llenar una lista desplegable en la vista.
     * @return List<SelectItem>, Retorna una lista de SelectItems con los datos necesarios de los Privilegio activos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Privilegio> buscarPrivilegiosPorRoles(List<Rol> listadoRoles){
        String formatoIn="";
        for (int i = 0; i < listadoRoles.size(); i++) {
            Rol rol = listadoRoles.get(i);
            formatoIn=formatoIn+" "+rol.getId();
        }
        formatoIn=formatoIn.trim();
        formatoIn=formatoIn.replaceAll(" ", ",");
        EntityManager em;
        em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT DISTINCT pr.privilegioId FROM PrivilegioRol pr WHERE pr.estado=TRUE AND pr.rolId.id IN("+formatoIn+")");
            List<Privilegio> lista=q.getResultList();
            return lista;
        }catch (Exception e) {
            System.out.println("Error al Buscar los (as) Privilegios Activos Por Roles->"+e.getStackTrace());
            return null;
        }
    }
}
