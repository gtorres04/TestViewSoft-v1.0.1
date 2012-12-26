/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.DaoImpl;
import com.testviewsoft.dao.util.Utileria;
import com.testviewsoft.entity.DocumentoIdentidad;
import com.testviewsoft.entity.Pais;
import com.testviewsoft.entity.Usuario;
import com.testviewsoft.entity.UsuarioPais;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
/**
 * Clase UsuarioDaoImpl: Clase Modelo de la Entidad Usuario.
 * En este Clase Modelo se intermedia el ManageBean "UsuarioBean" con toda la logica de negocio 
 * correspondiente a la entidad "Usuario". Esta clase hereda de la Clase Generica "DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros..
 * @see DaoImpl<T> Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 * @author Gerlin Orlando Torres Saavedra.
 */
public class UsuarioDaoImpl extends DaoImpl<Usuario> {
    private EntityManagerFactory emf;
    /**
     * Constructor UsuarioDaoImpl() (Vacio).
     * Se llama la instancia del EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia EntityManagerFactory en una variable global "emf".
     * @see EntityManagerFactory entidad manejadora de la persitencia.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public UsuarioDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id (llave primaria) del Usuario a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "Usuario")) obteniendo el objeto de tipo "Usuario" completo.
     * @param id identificacion (primarykey) del Usuario a Modificar.
     * @return Una instancia de tipo "Usuario" del "id" especificado como parametro.
     * @see DaoImpl.buscarPorId(id, "Usuario")
     * @author Gerlin Orlando Torres Saavedra.
     */
    public Usuario buscarPorId(Integer id) {
        return super.buscarPorId(id, "Usuario");
    }
    /**
     * Metodo registrar(Usuario entidad). @Override
     * Registra el Usuario en la base de datos y se le modifica el atributo tiempo a DocumentosIdentificacion.
     * @param entidad referencia de tipo "Usuario". Usuario que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     * @see Usuario ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String registrar(Usuario entidad) {
        try {
            Date tiempo=new Date();
            List<UsuarioPais> usuariosPaisList=entidad.getUsuarioPaisList();
            if(!usuariosPaisList.isEmpty())
            for (int i = 0; i < usuariosPaisList.size(); i++) {
                UsuarioPais usuarioPais = usuariosPaisList.get(i);
                usuarioPais.setUsuarioId(entidad);
                usuarioPais.setEstado(Boolean.TRUE);
                usuarioPais.setTiempoEstado(tiempo);
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
     * Metodo actualizar(Usuario entidad). @Override
     * Modifica el Usuario en la base de datos.
     * @param entidad referencia de tipo "Usaurios". Usuario que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     * @see Usuario ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String actualizar(Usuario entidad){
        try {
            Date tiempo=new Date();
            List<UsuarioPais> usuariosPaisList=entidad.getUsuarioPaisList();
            if(!usuariosPaisList.isEmpty())
            for (int i = 0; i < usuariosPaisList.size(); i++) {
                UsuarioPais usuarioPais = usuariosPaisList.get(i);
                usuarioPais.setUsuarioId(entidad);
                usuarioPais.setEstado(Boolean.TRUE);
                usuarioPais.setTiempoEstado(tiempo);
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
     * Metodo eliminar(Usuario entidad). @Override
     * Elimina el Usuario en la base de datos.
     * @param entidad referencia de tipo "Usuario". Usuario que se intenta eliminar en la base de datos.
     * @see Usuario ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String eliminar(Usuario entidad) {
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
     * Consulta en la base de datos los Usuario que estan activos, es decir, los que en la base de datos
     * se enceuntren en un estado TRUE o 1.
     * @return List<Usuario>, una lista de los usuarios que se encuentran activos, es decir, que su estado es TRUE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Usuario> buscarActivos() {
        List<Usuario> listaActivos=super.buscarActivos("Usuario");
        return listaActivos;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todos los Usuario, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<Usuario>, una lista de los usuarios que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Usuario> buscarTodos() {
        List<Usuario> lista=super.buscarTodos("Usuario");
        return lista;
    }
    /**
     * Metodo inactivarRegistro(Usuario entidad). @Override
     * Le modifica el estado al usuario (Inactivar), es decir, modificar el estado a FALSE.
     * @param entidad Parametro de Tipo "Usuario", cuyo usuario sera el referente para modificar el estado a FALSE.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la inactivacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String inactivarRegistro(Usuario entidad) {
        try {
            entidad.setEstado(Boolean.FALSE);
            entidad.setTiempoEstado(new Date());
            return super.inactivarRegistro(entidad); 
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo listaSelectItemDocumentoIdentidadActivos().
     * Crear una lista de SelectItems con los DocumentoIdentidad activos. Este metodo tiene como objetivo llenar una lista desplegable en la vista.
     * @return List<SelectItem>, Retorna una lista de SelectItems con los datos necesarios de los Documentos de Identidad activos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<SelectItem> listaSelectItemDocumentoIdentidadActivos(){
        DocumentoIdentidadDaoImpl daoImpl=new DocumentoIdentidadDaoImpl();
        List<SelectItem> listaSelectItem=new ArrayList<SelectItem>();
        List<DocumentoIdentidad> listaEntidades=daoImpl.buscarActivos();
        if(!listaEntidades.isEmpty())
        for (int i = 0; i < listaEntidades.size(); i++) {
            DocumentoIdentidad entidad=(DocumentoIdentidad)listaEntidades.get(i);
            listaSelectItem.add(new SelectItem(""+entidad.getId(), entidad.getNombre()));
        }
        return listaSelectItem;
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
