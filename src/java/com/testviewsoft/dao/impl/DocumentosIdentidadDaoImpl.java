/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.util.Utileria;
import com.testviewsoft.entity.DocumentosIdentidad;
import com.testviewsoft.entity.Paises;
import com.testviewsoft.entity.Usuarios;
import com.testviewsoft.entity.UsuariosPaises;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * Clase DocumentosIdentidadDaoImpl: Clase Modelo de la Entidad DocumentosIdentidad.
 * En este Clase Modelo se intermedia el ManageBean "DocumentosIdentidadBean" con toda la logica de negocio 
 * correspondiente a la entidad "DocumentosIdentidad". Esta clase hereda de la Clase Generica "@see DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros.
 * @author Gerlin Orlando Torres Saavedra
 * @see DaoImpl Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 */
public class DocumentosIdentidadDaoImpl extends DaoImpl<DocumentosIdentidad> {
    EntityManagerFactory emf;
    /**
     * Constructor DocumentosIdentidadDaoImpl (Vacio).
     * Se llama la instancia del @see EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia @see EntityManagerFactory en una variable global "emf".
     */
    public DocumentosIdentidadDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id del DocumentosIdentidad a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "DocumentosIdentidad")) obteniendo el objeto DocumentosIdentidad completo.
     * @param id identificados del DocumentosIdentidad a Modificar.
     * @return Una instancia de tipo "DocumentosIdentidad" del "id" especificado como parametro.
     */
    public DocumentosIdentidad buscarPorId(Integer id) {
        return super.buscarPorId(id, "DocumentosIdentidad");
    }
    /**
     * Metodo registrar(DocumentosIdentidad entidad). @Override
     * Registra el DocumentosIdentidad en la base de datos.
     * @param entidad referencia de tipo "DocumentosIdentidad". DocumentosIdentidad que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     */
    @Override
    public String registrar(DocumentosIdentidad entidad) {
        entidad.setTiempoEstado(new Date());
        entidad.setEstado(Boolean.TRUE);
        return super.registrar(entidad);
    }
    /**
     * Metodo actualizar(DocumentosIdentidad entidad). @Override
     * Modifica el DocumentosIdentidad en la base de datos.
     * @param entidad referencia de tipo "DocumentosIdentidad". DocumentosIdentidad que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     */
    @Override
    public String actualizar(DocumentosIdentidad entidad){
        try {
            if(buscarUsuariosPorDocumentosIdentidadSiUsuarioEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.actualizar(entidad); 
            }else{
                return "DocumentosIdentidad a modificar:"+entidad.toString()+". No es permitido Modificar este DocumentosIdentidad, ya que existen usuarios relacionado con este DocumentosIdentidad.";
            }
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }

    /**
     * Metodo eliminar(DocumentosIdentidad entidad). @Override
     * Elimina el DocumentosIdentidad en la base de datos.
     * @param entidad referencia de tipo "DocumentosIdentidad". DocumentosIdentidad que se intenta eliminar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     */
    @Override
    public String eliminar(DocumentosIdentidad entidad) {
        try {
            if(buscarUsuariosPorDocumentosIdentidadSiUsuarioEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.eliminar(entidad); 
            }else{
                return "DocumentosIdentidad a eliminar:"+entidad.toString()+". No es permitido Eliminar este DocumentosIdentidad, ya que existen usuarios relacionado con este pais.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo buscarActivos().
     * Consulta en la base de datos los DocumentosIdentidad que estan activos, es decir, los que en la base de datos
     * se encuentren en un estado TRUE o 1.
     * @return List<DocumentosIdentidad>, una lista de los DocumentosIdentidad que se encuentran activos, es decir,
     * que su estado es TRUE.
     */
    public List<DocumentosIdentidad> buscarActivos() {
        List<DocumentosIdentidad> listaDocumentosIdentidadActivas=super.buscarActivos("DocumentosIdentidad");
        return listaDocumentosIdentidadActivas;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todos los DocumentosIdentidad, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<DocumentosIdentidad>, una lista de los DocumentosIdentidad que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     */
    public List<DocumentosIdentidad> buscarTodos() {
        List<DocumentosIdentidad> listaDocumentosIdentidadActivas=super.buscarTodos("DocumentosIdentidad");
        return listaDocumentosIdentidadActivas;
    }
    /**
     * Metodo buscarUsuariosPorDocumentosIdentidadSiUsuarioEstaActivo(DocumentosIdentidad entidad).
     * Consulta en la base de datos todos los Usuarios, que esten relacionado con 
     * un DocumentosIdentidad en especifico (DocumentosIdentidad especificado como parametro)  y que su relacion este activa, es decir,
     * que su estado sea activo. 
     * @param entidad Parametro de Tipo "DocumentosIdentidad", cuyo DocumentosIdentidad sera el referente para buscar a los usuarios.
     * @return List<Usuarios>, una lista de los Usuarios que se encuentran activos que estan relacionados con el DocumentosIdentidad especificado como parametro.
     * @exception Exception
     * @see Exception
     */
    public List<Usuarios> buscarUsuariosPorDocumentosIdentidadSiUsuarioEstaActivo(DocumentosIdentidad entidad)throws Exception{
        EntityManager em;
        em=emf.createEntityManager();
        Query q = em.createQuery("SELECT usuario FROM Usuarios usuario WHERE usuario.tipoIdentificacion=:DocumentosIdentidad AND usuario.estado=TRUE");
        q.setParameter("DocumentosIdentidad", entidad);
        return q.getResultList();
        
    }
    /**
     * Metodo inactivarRegistro(DocumentosIdentidad entidad). @Override
     * Le modifica el estado al DocumentosIdentidad (Inactivar) siempre y cuando este DocumentosIdentidad no se encuentre relacionado con un usuario activo (Un usuario donde su estado es TRUE)
     * @param entidad Parametro de Tipo "DocumentosIdentidad", cuyo DocumentosIdentidad sera el referente para modificar el estado a FALSE.
     * @return List<Usuarios>, una lista de los Usuarios que se encuentran activos que estan relacionados con el DocumentosIdentidad especificado como parametro.
     */
    @Override
    public String inactivarRegistro(DocumentosIdentidad entidad) {
        try {
            if(buscarUsuariosPorDocumentosIdentidadSiUsuarioEstaActivo(entidad).isEmpty()){
                entidad.setEstado(Boolean.FALSE);
                entidad.setTiempoEstado(new Date());
                return super.inactivarRegistro(entidad); 
            }else{
                return "DocumentosIdentidad a eliminar:"+entidad.toString()+". No es permitido Eliminar este DocumentosIdentidad, ya que existen usuarios relacionado con este DocumentosIdentidad.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
}

