/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.util.Utileria;
import com.testviewsoft.entity.PrivilegioRol;
import com.testviewsoft.entity.Rol;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
/**
 * Clase RolDaoImpl: Clase Modelo de la Entidad Rol.
 * En este Clase Modelo se intermedia el ManageBean "RolBean" con toda la logica de negocio 
 * correspondiente a la entidad "Rol". Esta clase hereda de la Clase Generica "DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros..
 * @see DaoImpl<T> Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 * @author Gerlin Orlando Torres Saavedra.
 */
public class RolDaoImpl extends DaoImpl<Rol> {
    private EntityManagerFactory emf;
    /**
     * Constructor RolDaoImpl() (Vacio).
     * Se llama la instancia del EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia EntityManagerFactory en una variable global "emf".
     * @see EntityManagerFactory entidad manejadora de la persitencia.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public RolDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id (llave primaria) del Rol a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "Rol")) obteniendo el objeto de tipo "Rol" completo.
     * @param id identificacion (primarykey) del Rol a Modificar.
     * @return Una instancia de tipo "Rol" del "id" especificado como parametro.
     * @see DaoImpl.buscarPorId(id, "Rol")
     * @author Gerlin Orlando Torres Saavedra.
     */
    public Rol buscarPorId(Integer id) {
        return super.buscarPorId(id, "Rol");
    }
    /**
     * Metodo registrar(Rol entidad). @Override
     * Registra el Rol en la base de datos y se le modifica el atributo tiempo a DocumentosIdentificacion.
     * @param entidad referencia de tipo "Rol". Rol que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     * @see Rol ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String registrar(Rol entidad) {
        try {
            Date tiempo=new Date();
            List<PrivilegioRol> usuariosPrivilegioList=entidad.getPrivilegioRolList();
            if(!usuariosPrivilegioList.isEmpty())
            for (int i = 0; i < usuariosPrivilegioList.size(); i++) {
                PrivilegioRol privilegioRol = usuariosPrivilegioList.get(i);
                privilegioRol.setRolId(entidad);
                privilegioRol.setEstado(Boolean.TRUE);
                privilegioRol.setTiempoEstado(tiempo);
            }
            entidad.setTiempoEstado(tiempo);
            entidad.setEstado(Boolean.TRUE);
            return super.registrar(entidad);
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
    }
    /**
     * Metodo actualizar(Rol entidad). @Override
     * Modifica el Rol en la base de datos.
     * @param entidad referencia de tipo "Usaurios". Rol que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     * @see Rol ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String actualizar(Rol entidad){
        try {
            Date tiempo=new Date();
            List<PrivilegioRol> usuariosPrivilegioList=entidad.getPrivilegioRolList();
            if(!usuariosPrivilegioList.isEmpty())
            for (int i = 0; i < usuariosPrivilegioList.size(); i++) {
                PrivilegioRol privilegioRol = usuariosPrivilegioList.get(i);
                privilegioRol.setRolId(entidad);
                privilegioRol.setEstado(Boolean.TRUE);
                privilegioRol.setTiempoEstado(tiempo);
            }
            entidad.setEstado(Boolean.TRUE);
            entidad.setTiempoEstado(tiempo);
            return super.actualizar(entidad); 
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }

    /**
     * Metodo eliminar(Rol entidad). @Override
     * Elimina el Rol en la base de datos.
     * @param entidad referencia de tipo "Rol". Rol que se intenta eliminar en la base de datos.
     * @see Rol ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String eliminar(Rol entidad) {
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
     * Consulta en la base de datos los Rol que estan activos, es decir, los que en la base de datos
     * se enceuntren en un estado TRUE o 1.
     * @return List<Rol>, una lista de los usuarios que se encuentran activos, es decir, que su estado es TRUE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Rol> buscarActivos() {
        List<Rol> listaActivos=super.buscarActivos("Rol");
        return listaActivos;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todos los Rol, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<Rol>, una lista de los usuarios que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Rol> buscarTodos() {
        List<Rol> lista=super.buscarTodos("Rol");
        return lista;
    }
    /**
     * Metodo inactivarRegistro(Rol entidad). @Override
     * Le modifica el estado al usuario (Inactivar), es decir, modificar el estado a FALSE.
     * @param entidad Parametro de Tipo "Rol", cuyo usuario sera el referente para modificar el estado a FALSE.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la inactivacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String inactivarRegistro(Rol entidad) {
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
//    public List<SelectItem> listaSelectItemPrivilegioActivos(){
//        PrivilegioDaoImpl daoImpl=new PrivilegioDaoImpl();
//        List<SelectItem> listaSelectItem=new ArrayList<SelectItem>();
//        List<Privilegio> listaEntidades=daoImpl.buscarActivos();
//        if(!listaEntidades.isEmpty())
//        for (int i = 0; i < listaEntidades.size(); i++) {
//            Privilegio entidad=listaEntidades.get(i);
//            listaSelectItem.add(new SelectItem(""+entidad.getId(), entidad.getNombre()));
//        }
//        return listaSelectItem;
//    } 
}
