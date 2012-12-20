/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.util.Utileria;
import com.testviewsoft.entity.Paises;
import com.testviewsoft.entity.UsuariosPaises;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * Clase PaisesDaoImpl: Clase Modelo de la Entidad Paises.
 * En este Clase Modelo se intermedia el ManageBean "PaisesBean" con toda la logica de negocio 
 * correspondiente a la entidad "Paises". Esta clase hereda de la Clase Generica "@see DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros.
 * @author Gerlin Orlando Torres Saavedra
 * @see DaoImpl Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 */
public class PaisesDaoImpl extends DaoImpl<Paises> {
    EntityManagerFactory emf;
    /**
     * Constructor PaisesDaoImpl (Vacio).
     * Se llama la instancia del @see EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia @see EntityManagerFactory en una variable global "emf".
     */
    public PaisesDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id del Pais a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "Paises")) obteniendo el objeto PAIS completo.
     * @param id identificados del Pais a Modificar.
     * @return Una instancia de tipo "Paises" del "id" especificado como parametro.
     */
    public Paises buscarPorId(Integer id) {
        return super.buscarPorId(id, "Paises");
    }
    /**
     * Metodo registrar(Paises entidad). @Override
     * Registra el Pais en la base de datos.
     * @param entidad referencia de tipo "Paises". Pais que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     */
    @Override
    public String registrar(Paises entidad) {
        entidad.setTiempoEstado(new Date());
        entidad.setEstado(Boolean.TRUE);
        return super.registrar(entidad);
    }
    /**
     * Metodo actualizar(Paises entidad). @Override
     * Modifica el Pais en la base de datos.
     * @param entidad referencia de tipo "Paises". Pais que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     */
    @Override
    public String actualizar(Paises entidad){
        try {
            if(buscarUsuariosPorPaisSiUsuarioPaisEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.actualizar(entidad); 
            }else{
                return "Pais a modificar:"+entidad.toString()+". No es permitido Modificar este Pais, ya que existen usuarios relacionado con este pais.";
            }
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }

    /**
     * Metodo eliminar(Paises entidad). @Override
     * Elimina el Pais en la base de datos.
     * @param entidad referencia de tipo "Paises". Pais que se intenta eliminar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     */
    @Override
    public String eliminar(Paises entidad) {
        try {
            if(buscarUsuariosPorPaisSiUsuarioPaisEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.eliminar(entidad); 
            }else{
                return "Pais a eliminar:"+entidad.toString()+". No es permitido Eliminar este Pais, ya que existen usuarios relacionado con este pais.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo buscarTodosEstadoTRUE().
     * Consulta en la base de datos los Paises que estan activos, es decir, los que en la base de datos
     * se enceuntren en un estado TRUE o 1.
     * @return List<Paises>, una lista de los paises que se encuentran activos, es decir,
     * que su estado es TRUE.
     */
    public List<Paises> buscarActivos() {
        List<Paises> listaPaisesActivas=super.buscarActivos("Paises");
        return listaPaisesActivas;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todos los Paises, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<Paises>, una lista de los paises que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     */
    public List<Paises> buscarTodos() {
        List<Paises> listaPaisesActivas=super.buscarTodos("Paises");
        return listaPaisesActivas;
    }
    /**
     * Metodo buscarUsuariosPorPaisSiUsuarioPaisEstaActivo(Paises entidad).
     * Consulta en la base de datos todos los Usuarios, que esten relacionado con 
     * un pais en especifico (Pais especificado como parametro)  y que su relacion este activa, es decir,
     * que su estado sea activo. 
     * @param entidad Parametro de Tipo "Paises", cuyo pais sera el referente para buscar a los usuarios.
     * @return List<UsuariosPaises>, una lista de los Usuarios que se encuentran activos que estan relacionados con el Pais especificado como parametro.
     * @exception Exception
     * @see Exception
     */
    public List<UsuariosPaises> buscarUsuariosPorPaisSiUsuarioPaisEstaActivo(Paises entidad)throws Exception{
        EntityManager em;
        em=emf.createEntityManager();
        Query q = em.createQuery("SELECT usuarioPais FROM UsuariosPaises usuarioPais WHERE usuarioPais.paisesId=:pais AND usuarioPais.estado=TRUE");
        q.setParameter("pais", entidad);
        return q.getResultList();
        
    }
    /**
     * Metodo inactivarRegistro(Paises entidad). @Override
     * Le modifica el estado al pais (Inactivar) siempre y cuando este pais no se enceuntre relacionado con un usuario activo (Un usuario donde su estado es TRUE)
     * @param entidad Parametro de Tipo "Paises", cuyo pais sera el referente para modificar el estado a FALSE.
     * @return List<UsuariosPaises>, una lista de los Usuarios que se encuentran activos que estan relacionados con el Pais especificado como parametro.
     */
    @Override
    public String inactivarRegistro(Paises entidad) {
        try {
            if(buscarUsuariosPorPaisSiUsuarioPaisEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.FALSE);
                entidad.setTiempoEstado(new Date());
                return super.inactivarRegistro(entidad); 
            }else{
                return "Pais a eliminar:"+entidad.toString()+". No es permitido Eliminar este Pais, ya que existen usuarios relacionado con este pais.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
}
