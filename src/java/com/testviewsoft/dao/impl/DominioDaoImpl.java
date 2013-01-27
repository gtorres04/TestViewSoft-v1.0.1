/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.util.Utileria;
import com.testviewsoft.entity.Cuenta;
import com.testviewsoft.entity.Dominio;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * Clase DominioDaoImpl: Clase Modelo de la Entidad Dominio.
 * En este Clase Modelo se intermedia el ManageBean "DominioBean" con toda la logica de negocio 
 * correspondiente a la entidad "Dominio". Esta clase hereda de la Clase Generica "@see DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros.
 * @author Gerlin Orlando Torres Saavedra
 * @see DaoImpl Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 */
public class DominioDaoImpl extends DaoImpl<Dominio> {
    EntityManagerFactory emf;
    /**
     * Constructor DominioDaoImpl (Vacio).
     * Se llama la instancia del @see EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia @see EntityManagerFactory en una variable global "emf".
     */
    public DominioDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id del Dominio a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "Dominio")) obteniendo el objeto PAIS completo.
     * @param id identificados del Dominio a Modificar.
     * @return Una instancia de tipo "Dominio" del "id" especificado como parametro.
     */
    public Dominio buscarPorId(Integer id) {
        return super.buscarPorId(id, "Dominio");
    }
    /**
     * Metodo registrar(Dominio entidad). @Override
     * Registra el Dominio en la base de datos.
     * @param entidad referencia de tipo "Dominio". Dominio que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     */
    @Override
    public String registrar(Dominio entidad) {
        entidad.setTiempoEstado(new Date());
        entidad.setEstado(Boolean.TRUE);
        return super.registrar(entidad);
    }
    /**
     * Metodo actualizar(Dominio entidad). @Override
     * Modifica el Dominio en la base de datos.
     * @param entidad referencia de tipo "Dominio". Dominio que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     */
    @Override
    public String actualizar(Dominio entidad){
        try {
            if(buscarCuentaPorDominioSiCuentaEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.actualizar(entidad); 
            }else{
                return "Dominio a modificar:"+entidad.toString()+". No es permitido Modificar este Dominio, ya que existen cuentas relacionado con este dominio.";
            }
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }

    /**
     * Metodo eliminar(Dominio entidad). @Override
     * Elimina el Dominio en la base de datos.
     * @param entidad referencia de tipo "Dominio". Dominio que se intenta eliminar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     */
    @Override
    public String eliminar(Dominio entidad) {
        try {
            if(buscarCuentaPorDominioSiCuentaEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.eliminar(entidad); 
            }else{
                return "Dominio a eliminar:"+entidad.toString()+". No es permitido Eliminar este Dominio, ya que existen cuentas relacionado con este dominio.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo buscarActivos().
     * Consulta en la base de datos los Dominio que estan activos, es decir, los que en la base de datos
     * se enceuntren en un estado TRUE o 1.
     * @return List<Dominio>, una lista de los dominioes que se encuentran activos, es decir,
     * que su estado es TRUE.
     */
    public List<Dominio> buscarActivos() {
        List<Dominio> listaDominioActivas=super.buscarActivos("Dominio");
        return listaDominioActivas;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todos los Dominio, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<Dominio>, una lista de los dominioes que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     */
    public List<Dominio> buscarTodos() {
        List<Dominio> listaDominioActivas=super.buscarTodos("Dominio");
        return listaDominioActivas;
    }
    /**
     * Metodo buscarCuentaPorDominioSiCuentaEstaActivo(Dominio entidad).
     * Consulta en la base de datos todas las Cuentas, que esten relacionado con 
     * un dominio en especifico (Dominio especificado como parametro)  y que su relacion este activa, es decir,
     * que su estado sea activo. 
     * @param entidad Parametro de Tipo "Dominio", cuyo dominio sera el referente para buscar a los cuentas.
     * @return List<Cuenta>, una lista de los Cuentas que se encuentran activos que estan relacionados con el Dominio especificado como parametro.
     * @exception Exception
     * @see Exception
     */
    public List<Cuenta> buscarCuentaPorDominioSiCuentaEstaActivo(Dominio entidad)throws Exception{
        EntityManager em;
        em=emf.createEntityManager();
        Query q = em.createQuery("SELECT cuenta FROM Cuenta cuenta WHERE cuenta.dominioId=:dominio AND cuenta.estado=TRUE");
        q.setParameter("dominio", entidad);
        return q.getResultList();
        
    }
    /**
     * Metodo inactivarRegistro(Dominio entidad). @Override
     * Le modifica el estado al dominio (Inactivar) siempre y cuando este dominio no se enceuntre relacionado con un usuario activo (Un usuario donde su estado es TRUE)
     * @param entidad Parametro de Tipo "Dominio", cuyo dominio sera el referente para modificar el estado a FALSE.
     * @return List<Dominio>, una lista de los Cuentas que se encuentran activos que estan relacionados con el Dominio especificado como parametro.
     */
    @Override
    public String inactivarRegistro(Dominio entidad) {
        try {
            if(buscarCuentaPorDominioSiCuentaEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.FALSE);
                entidad.setTiempoEstado(new Date());
                return super.inactivarRegistro(entidad); 
            }else{
                return "Dominio a eliminar:"+entidad.toString()+". No es permitido Eliminar este Dominio, ya que existen cuentas relacionado con este dominio.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
}

