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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
/**
 * Clase UsuariosDaoImpl: Clase Modelo de la Entidad Usuarios.
 * En este Clase Modelo se intermedia el ManageBean "UsuariosBean" con toda la logica de negocio 
 * correspondiente a la entidad "Usuarios". Esta clase hereda de la Clase Generica "DaoImp<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros..
 * @see DaoImpl<T> Clase Generica donde se realizan los procesos que son factor comun entre las entidades.
 * @author Gerlin Orlando Torres Saavedra.
 */
public class UsuariosDaoImpl extends DaoImpl<Usuarios> {
    private EntityManagerFactory emf;
    /**
     * Constructor UsuariosDaoImpl() (Vacio).
     * Se llama la instancia del EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia EntityManagerFactory en una variable global "emf".
     * @see EntityManagerFactory entidad manejadora de la persitencia.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public UsuariosDaoImpl() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id (llave primaria) del Usuario a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "Usuarios")) obteniendo el objeto de tipo "Usuarios" completo.
     * @param id identificacion (primarykey) del Usuario a Modificar.
     * @return Una instancia de tipo "Usuarios" del "id" especificado como parametro.
     * @see DaoImpl.buscarPorId(id, "Usuarios")
     * @author Gerlin Orlando Torres Saavedra.
     */
    public Usuarios buscarPorId(Integer id) {
        return super.buscarPorId(id, "Usuarios");
    }
    /**
     * Metodo registrar(Usuarios entidad). @Override
     * Registra el Usuario en la base de datos y se le modifica el atributo tiempo a DocumentosIdentificacion.
     * @param entidad referencia de tipo "Usuarios". Usuario que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     * @see Usuarios ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String registrar(Usuarios entidad) {
        try {
            Date tiempo=new Date();
            List<UsuariosPaises> usuariosPaisesList=entidad.getUsuariosPaisesList();
            if(!usuariosPaisesList.isEmpty())
            for (int i = 0; i < usuariosPaisesList.size(); i++) {
                UsuariosPaises usuarioPais = usuariosPaisesList.get(i);
                usuarioPais.setUsuariosId(entidad);
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
     * Metodo actualizar(Usuarios entidad). @Override
     * Modifica el Usuario en la base de datos.
     * @param entidad referencia de tipo "Usaurios". Usuario que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     * @see Usuarios ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String actualizar(Usuarios entidad){
        try {
            Date tiempo=new Date();
            List<UsuariosPaises> usuariosPaisesList=entidad.getUsuariosPaisesList();
            if(!usuariosPaisesList.isEmpty())
            for (int i = 0; i < usuariosPaisesList.size(); i++) {
                UsuariosPaises usuarioPais = usuariosPaisesList.get(i);
                usuarioPais.setUsuariosId(entidad);
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
     * Metodo eliminar(Usuarios entidad). @Override
     * Elimina el Usuario en la base de datos.
     * @param entidad referencia de tipo "Usuarios". Usuario que se intenta eliminar en la base de datos.
     * @see Usuarios ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String eliminar(Usuarios entidad) {
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
     * Consulta en la base de datos los Usuarios que estan activos, es decir, los que en la base de datos
     * se enceuntren en un estado TRUE o 1.
     * @return List<Usuarios>, una lista de los usuarios que se encuentran activos, es decir, que su estado es TRUE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Usuarios> buscarActivos() {
        List<Usuarios> listaActivos=super.buscarActivos("Usuarios");
        return listaActivos;
    }
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todos los Usuarios, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<Usuarios>, una lista de los usuarios que se encuentran activos e inactivos, es decir, que su estado es TRUE o FALSE.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<Usuarios> buscarTodos() {
        List<Usuarios> lista=super.buscarTodos("Usuarios");
        return lista;
    }
    /**
     * Metodo inactivarRegistro(Usuarios entidad). @Override
     * Le modifica el estado al usuario (Inactivar), es decir, modificar el estado a FALSE.
     * @param entidad Parametro de Tipo "Usuarios", cuyo usuario sera el referente para modificar el estado a FALSE.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la inactivacion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    @Override
    public String inactivarRegistro(Usuarios entidad) {
        try {
            entidad.setEstado(Boolean.FALSE);
            entidad.setTiempoEstado(new Date());
            return super.inactivarRegistro(entidad); 
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
        
    }
    /**
     * Metodo listaSelectItemDocumentosIdentidadActivos().
     * Crear una lista de SelectItems con los DocumentosIdentidad activos. Este metodo tiene como objetivo llenar una lista desplegable en la vista.
     * @return List<SelectItem>, Retorna una lista de SelectItems con los datos necesarios de los Documentos de Identidad activos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<SelectItem> listaSelectItemDocumentosIdentidadActivos(){
        DocumentosIdentidadDaoImpl daoImpl=new DocumentosIdentidadDaoImpl();
        List<SelectItem> listaSelectItem=new ArrayList<SelectItem>();
        List<DocumentosIdentidad> listaEntidades=daoImpl.buscarActivos();
        if(!listaEntidades.isEmpty())
        for (int i = 0; i < listaEntidades.size(); i++) {
            DocumentosIdentidad entidad=(DocumentosIdentidad)listaEntidades.get(i);
            listaSelectItem.add(new SelectItem(""+entidad.getId(), entidad.getNombre()));
        }
        return listaSelectItem;
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
