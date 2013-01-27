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
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * Clase PrivilegioDaoImpl: Clase Modelo de la Entidad Privilegio.
 * En este Clase Modelo se intermedia el ManageBean "PrivilegioBean" con toda la logica de negocio 
 * correspondiente a la entidad "Privilegio". Esta clase hereda de la Clase Generica "@see DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros.
 * @author Gerlin Orlando Torres Saavedra
 * @see DaoImpl Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 */
public class PrivilegioDaoImpl extends DaoImpl<Privilegio> {
    EntityManagerFactory emf;
    /**
     * Constructor PrivilegioDaoImpl (Vacio).
     * Se llama la instancia del @see EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia @see EntityManagerFactory en una variable global "emf".
     */
    public PrivilegioDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id del Privilegio a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "Privilegio")) obteniendo el objeto PAIS completo.
     * @param id identificados del Privilegio a Modificar.
     * @return Una instancia de tipo "Privilegio" del "id" especificado como parametro.
     */
    public Privilegio buscarPorId(Integer id) {
        return super.buscarPorId(id, "Privilegio");
    }
    /**
     * Metodo registrar(Privilegio entidad). @Override
     * Registra el Privilegio en la base de datos.
     * @param entidad referencia de tipo "Privilegio". Privilegio que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     */
    @Override
    public String registrar(Privilegio entidad) {
        entidad.setTiempoEstado(new Date());
        entidad.setEstado(Boolean.TRUE);
        return super.registrar(entidad);
    }
    /**
     * Metodo actualizar(Privilegio entidad). @Override
     * Modifica el Privilegio en la base de datos.
     * @param entidad referencia de tipo "Privilegio". Privilegio que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     */
    @Override
    public String actualizar(Privilegio entidad){
        try {
            if(buscarRolPorPrivilegioSiPrivilegioRolEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.actualizar(entidad); 
            }else{
                return "Privilegio a modificar:"+entidad.toString()+". No es permitido Modificar este Privilegio, ya que existen usuarios relacionado con este privilegio.";
            }
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }

    /**
     * Metodo eliminar(Privilegio entidad). @Override
     * Elimina el Privilegio en la base de datos.
     * @param entidad referencia de tipo "Privilegio". Privilegio que se intenta eliminar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     */
    @Override
    public String eliminar(Privilegio entidad) {
        try {
            if(buscarRolPorPrivilegioSiPrivilegioRolEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.eliminar(entidad); 
            }else{
                return "Privilegio a eliminar:"+entidad.toString()+". No es permitido Eliminar este Privilegio, ya que existen usuarios relacionado con este privilegio.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo buscarActivos().
     * Consulta en la base de datos los Privilegio que estan activos, es decir, los que en la base de datos
     * se enceuntren en un estado TRUE o 1.
     * @return List<Privilegio>, una lista de los privilegioes que se encuentran activos, es decir,
     * que su estado es TRUE.
     */
    public List<Privilegio> buscarActivos() {
        List<Privilegio> listaPrivilegioActivas=super.buscarActivos("Privilegio");
        return listaPrivilegioActivas;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todos los Privilegio, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<Privilegio>, una lista de los privilegioes que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     */
    public List<Privilegio> buscarTodos() {
        List<Privilegio> listaPrivilegioActivas=super.buscarTodos("Privilegio");
        return listaPrivilegioActivas;
    }
    /**
     * Metodo buscarRolPorPrivilegioSiPrivilegioRolEstaActivo(Privilegio entidad).
     * Consulta en la base de datos todos los Rol, que esten relacionado con 
     * un privilegio en especifico (Privilegio especificado como parametro)  y que su relacion este activa, es decir,
     * que su estado sea activo. 
     * @param entidad Parametro de Tipo "Privilegio", cuyo privilegio sera el referente para buscar a los usuarios.
     * @return List<PrivilegioRol>, una lista de los Rol que se encuentran activos que estan relacionados con el Privilegio especificado como parametro.
     * @exception Exception
     * @see Exception
     */
    public List<PrivilegioRol> buscarRolPorPrivilegioSiPrivilegioRolEstaActivo(Privilegio entidad)throws Exception{
        EntityManager em;
        em=emf.createEntityManager();
        Query q = em.createQuery("SELECT privilegioRol FROM PrivilegioRol privilegioRol WHERE privilegioRol.privilegioId=:privilegio AND privilegioRol.estado=TRUE");
        q.setParameter("privilegio", entidad);
        return q.getResultList();
        
    }
    /**
     * Metodo buscarPrivilegioPorRoles(List<Rol> entidad).
     * Consulta en la base de datos todos los privilegios, que esten relacionado con 
     * una lista de roles en especifico (Listado de Roles especificado como parametro)  y que su relacion este activa, es decir,
     * que su estado sea activo. 
     * @param entidades Parametro de Tipo "List<Rol>", cuyo listado sera el referente para buscar a los privilegios.
     * @return List<Privilegio>, una lista de los Privilegios que se encuentran activos que estan relacionados con el listado de Roles especificado como parametro.
     */
    public List<Privilegio> buscarPrivilegioPorRolesTeniendoEnCuentaPrivilegiosFuentes(String cadenaRolesCargados, String cadenaPrivilegiosFuentes){
        EntityManager em;
        em=emf.createEntityManager();
        Query q = em.createQuery("SELECT DISTINCT privilegio FROM Privilegio privilegio, PrivilegioRol privilegioRol WHERE privilegio.id=privilegioRol.privilegioId.id AND privilegioRol.rolId.id IN("+cadenaRolesCargados+") AND privilegio.id IN("+cadenaPrivilegiosFuentes+") AND privilegio.estado=TRUE");
        return q.getResultList();
        
    }
    public List<Privilegio> buscarPrivilegioPorRoles(String cadenaRolesCargados){
        EntityManager em;
        em=emf.createEntityManager();
        Query q = em.createQuery("SELECT DISTINCT privilegio FROM Privilegio privilegio, PrivilegioRol privilegioRol WHERE privilegio.id=privilegioRol.privilegioId.id AND privilegioRol.rolId.id IN("+cadenaRolesCargados+") AND privilegio.estado=TRUE");
        return q.getResultList();
        
    }
    /**
     * Metodo inactivarRegistro(Privilegio entidad). @Override
     * Le modifica el estado al privilegio (Inactivar) siempre y cuando este privilegio no se enceuntre relacionado con un usuario activo (Un usuario donde su estado es TRUE)
     * @param entidad Parametro de Tipo "Privilegio", cuyo privilegio sera el referente para modificar el estado a FALSE.
     * @return List<PrivilegioRol>, una lista de los Rol que se encuentran activos que estan relacionados con el Privilegio especificado como parametro.
     */
    @Override
    public String inactivarRegistro(Privilegio entidad) {
        try {
            if(buscarRolPorPrivilegioSiPrivilegioRolEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.FALSE);
                entidad.setTiempoEstado(new Date());
                return super.inactivarRegistro(entidad); 
            }else{
                return "Privilegio a eliminar:"+entidad.toString()+". No es permitido Eliminar este Privilegio, ya que existen usuarios relacionado con este privilegio.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
}
