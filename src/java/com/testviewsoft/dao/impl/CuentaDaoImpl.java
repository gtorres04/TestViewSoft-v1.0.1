/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.util.Utileria;
import com.testviewsoft.entity.Cuenta;
import com.testviewsoft.entity.Dominio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
/**
 * Clase CuentaDaoImpl: Clase Modelo de la Entidad Cuenta.
 * En este Clase Modelo se intermedia el ManageBean "CuentaBean" con toda la logica de negocio 
 * correspondiente a la entidad "Cuenta". Esta clase hereda de la Clase Generica "DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros..
 * @see DaoImpl<T> Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 * @author Gerlin Orlando Torres Saavedra.
 */
public class CuentaDaoImpl extends DaoImpl<Cuenta> {
    private EntityManagerFactory emf;
    /**
     * Constructor CuentaDaoImpl() (Vacio).
     * Se llama la instancia del EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia EntityManagerFactory en una variable global "emf".
     * @see EntityManagerFactory entidad manejadora de la persitencia.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public CuentaDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id (llave primaria) del Cuenta a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "Cuenta")) obteniendo el objeto de tipo "Cuenta" completo.
     * @param id identificacion (primarykey) del Cuenta a Modificar.
     * @return Una instancia de tipo "Cuenta" del "id" especificado como parametro.
     * @see DaoImpl.buscarPorId(id, "Cuenta")
     * @author Gerlin Orlando Torres Saavedra.
     */
    public Cuenta buscarPorId(Integer id) {
        return super.buscarPorId(id, "Cuenta");
    }
    /**
     * Metodo registrar(Cuenta entidad). @Override
     * Registra el Cuenta en la base de datos y se le modifica el atributo tiempo a DocumentosIdentificacion.
     * @param entidad referencia de tipo "Cuenta". Cuenta que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     * @see Cuenta ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String registrar(Cuenta entidad) {
        try {
            Date tiempo=new Date();
            entidad.setTiempoEstado(tiempo);
            entidad.setEstado(Boolean.TRUE);
            return super.registrar(entidad);
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
    }
    /**
     * Metodo actualizar(Cuenta entidad). @Override
     * Modifica el Cuenta en la base de datos.
     * @param entidad referencia de tipo "Usaurios". Cuenta que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     * @see Cuenta ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String actualizar(Cuenta entidad){
        try {
            Date tiempo=new Date();
            entidad.setEstado(Boolean.TRUE);
            entidad.setTiempoEstado(tiempo);
            return super.actualizar(entidad); 
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }

    /**
     * Metodo eliminar(Cuenta entidad). @Override
     * Elimina el Cuenta en la base de datos.
     * @param entidad referencia de tipo "Cuenta". Cuenta que se intenta eliminar en la base de datos.
     * @see Cuenta ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String eliminar(Cuenta entidad) {
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
     * Consulta en la base de datos los Cuenta que estan activos, es decir, los que en la base de datos
     * se enceuntren en un estado TRUE o 1.
     * @return List<Cuenta>, una lista de los usuarios que se encuentran activos, es decir, que su estado es TRUE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Cuenta> buscarActivos() {
        List<Cuenta> listaActivos=super.buscarActivos("Cuenta");
        return listaActivos;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todos los Cuenta, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<Cuenta>, una lista de los usuarios que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Cuenta> buscarTodos() {
        List<Cuenta> lista=super.buscarTodos("Cuenta");
        return lista;
    }
    /**
     * Metodo inactivarRegistro(Cuenta entidad). @Override
     * Le modifica el estado al usuario (Inactivar), es decir, modificar el estado a FALSE.
     * @param entidad Parametro de Tipo "Cuenta", cuyo usuario sera el referente para modificar el estado a FALSE.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la inactivacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String inactivarRegistro(Cuenta entidad) {
        try {
            entidad.setEstado(Boolean.FALSE);
            entidad.setTiempoEstado(new Date());
            return super.inactivarRegistro(entidad); 
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo listaSelectItemDominioActivos().
     * Crear una lista de SelectItems con los Dominio activos. Este metodo tiene como objetivo llenar una lista desplegable en la vista.
     * @return List<SelectItem>, Retorna una lista de SelectItems con los datos necesarios de los Documentos de Identidad activos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<SelectItem> listaSelectItemDominioActivos(){
        DominioDaoImpl daoImpl=new DominioDaoImpl();
        List<SelectItem> listaSelectItem=new ArrayList<SelectItem>();
        List<Dominio> listaEntidades=daoImpl.buscarActivos();
        if(!listaEntidades.isEmpty())
        for (int i = 0; i < listaEntidades.size(); i++) {
            Dominio entidad=(Dominio)listaEntidades.get(i);
            listaSelectItem.add(new SelectItem(""+entidad.getId(), entidad.getNombre()));
        }
        return listaSelectItem;
    }
    public Cuenta verificarAutenticacion(Cuenta cuenta){
        EntityManager em;
        em=emf.createEntityManager();
        try{
            Query q = em.createQuery("SELECT entidad FROM Cuenta entidad WHERE entidad.estado=TRUE AND entidad.usuario=:usuario AND entidad.contrasena=:contrasena");
            q.setParameter("usuario", cuenta.getUsuario());
            q.setParameter("contrasena", cuenta.getContrasena());
            return (Cuenta)q.getSingleResult();
        }catch (Exception e) {
            System.out.println("Error al Autenticar El Usuario->"+e.getMessage());
            return null;
        }
    }
}
