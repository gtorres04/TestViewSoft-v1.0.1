/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.manageBean;

import com.testviewsoft.dao.impl.PrivilegioDaoImpl;
import com.testviewsoft.dao.impl.PrivilegioRolDaoImpl;
import com.testviewsoft.dao.impl.RolDaoImpl;
import com.testviewsoft.dao.impl.RolRolDaoImpl;
import com.testviewsoft.entity.Privilegio;
import com.testviewsoft.entity.PrivilegioRol;
import com.testviewsoft.entity.Rol;
import com.testviewsoft.entity.RolRol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 * ManageBean RolBean.
 * En este ManageBean se controlan todos los eventos correspondientes
 * Al CRUD de la entidad "Rol" lanzados desde la vista:
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * @see Rol ENTITY: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
 * @author Gerlin Orlando Torres Saavedra.
 */
@ManagedBean
@SessionScoped
public class RolRolBean implements Serializable{
    private RolRol entidad;
    private List<RolRol> entidades;
    private Rol rol;
    private DualListModel<String> dualListModelRol;
    private DualListModel<String> dualListModelPrivilegio;
    private List<PrivilegioRol> listaPrivilegioRol;
    /**
     * Constructor RolRolBean() (Vacio).
     * Se crea una instancia del ManageBean y se crea un objeto de Tipo "RolRol" (variable entidad [Global]), 
     * el cual tiene como objetivo recibir y devolver los valores de la vista.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public RolRolBean() {
        inicializar();
    }

    public RolRol getRolRol() {
        return entidad;
    }

    public void setRolRol(RolRol entidad) {
        this.entidad = entidad;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public DualListModel<String> getDualListModelRol() {
        return dualListModelRol;
    }

    public void setDualListModelRol(DualListModel<String> dualListModelRol) {
        this.dualListModelRol = dualListModelRol;
    }

    public DualListModel<String> getDualListModelPrivilegio() {
        return dualListModelPrivilegio;
    }

    public void setDualListModelPrivilegio(DualListModel<String> dualListModelPrivilegio) {
        this.dualListModelPrivilegio = dualListModelPrivilegio;
    }
    
    /**
     * Metodo getRolRol().
     * Trae de la base de datos los RolRol que se encuentran almacenados.
     * @return Retorna una lista Vacia si no hay ningun RolRol registrado en la base de datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<RolRol> getRolRoles() {
        return entidades;
    }
    /**
     * Metodo insertar().
     * Toma la instancia de tipo "RolRol" (Global) y la envia para su registro,
     * Y la respuesta del proceso de registro la envia a la vista en un FaceMessage.
     * @see RolRol Entity[RolRol]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void insertar(){
        RolDaoImpl usuarioDaoImpl=new RolDaoImpl();
        //rol.setDocumentoIdentidadId(documentoIdentidad);
        rol.setRolRolList(entidades);
        String mensaje=usuarioDaoImpl.registrar(rol);
        inicializar();
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE RolRol",mensaje)); 
    }
    /**
     * Metodo actualizar().
     * Toma la instancia de tipo "RolRol" (variable entidad [Global]) seteada por el metodo prepararActualizacion(Integer id), 
     * y la envia para su modificacion, la respuesta del proceso de modificacion la envia a la vista 
     * en un FaceMessage.
     * @see RolRol Entity[RolRol]: Comprendase como entidad (entity) la clase que guarda la relacion de sus atributos con los campos de una tabla en la Base de Datos.
     * @see prepararActualizacion(Integer id)
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void actualizar(){
        RolDaoImpl daoImpl=new RolDaoImpl();
        //rol.setDocumentoIdentidadId(documentoIdentidad);
        rol.setRolRolList(entidades);
        String mensaje=daoImpl.actualizar(rol);
        inicializar();
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE USUARIO PAISES",mensaje));        
    }
    /**
     * Metodo prepararActualizacion(Integer id).
     * Recibe el Id (Llave Primaria) del usuario a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto de tipo "Rol" completo (variable rol [Global]), para posteriormente asignarlo a la variable "entidad" (Global), 
     * para que cuando se llame el metodo actualizar() el objeto tenga sus valores modificados.
     * @see RolRol Entity[RolRol]: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @see actualizar() modifica el objeto RolRol.
     * @param id primaryKey de la entidad de tipo "RolRol" a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL RolRol");
        RolDaoImpl daoImpl=new RolDaoImpl();
        rol=daoImpl.buscarPorId(id);
        //idDocumentoIdentificacion=""+rol.getDocumentoIdentidadId().getId();
        entidades=rol.getRolRolList();
        System.out.println("");
    }
    /**
     * Metodo eliminar().
     * Toma la instancia de tipo "RolRol" (variable entidad [Global]) seteada por el metodo prepararEliminacion(Integer id), 
     * y la envia para su Eliminacion, la respuesta del proceso de eliminacion la envia a la vista 
     * en un FaceMessage. Hay que recalcar que la eliminicion literalmente no se realiza, solo se le 
     * cambia el estado al registro, pero la informacion sigue en la base de datos para futuras inconsistencias.
     * @see RolRol Entity[RolRol]: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
     * @see prepararEliminacion(Integer id).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void eliminar(){
        Log("METODO ELIMINAR RolRol");
        RolDaoImpl daoImpl=new RolDaoImpl();
        String mensaje=daoImpl.inactivarRegistro(rol);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE USUARIOS",mensaje));
    }
    /**
     * Metodo prepararEliminacion(Integer id).
     * Recive el Id (llave primaria) del Rol-Pais a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto de tipo "RolRol" (variable entidad [Global]) completo, para posteriormente 
     * asignarlo a la variable "entidad" (Global), para que cuando se llame el metodo eliminar() el 
     * objeto tenga sus valores completos para su eliminacion.
     * @see eliminar() modifica el objeto de tipo "RolRol" (variable entidad [Global]).
     * @param id identificacion (PrimaryKey) del RolRol a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL USUARIO-PAIS");
        RolDaoImpl daoImpl=new RolDaoImpl();
        rol=daoImpl.buscarPorId(id);
    }
    /**
     * Metodo listaSelectItemPaisActivos().
     * Llama al metodo RolRolDaoImpl.listaSelectItemPaisActivos() de la clase RolRolDaoImpl 
     * para traer una lista de SelectItem con los Pais activos. Este metodo es llamado desde la 
     * vista para llenar un "selectOneMenu" (Combo o Lista Desplegable) con los Pais que estan activos para el registro
     * de un usuario.
     * @see RolRolDaoImpl listaSelectItemDocumentoIdentidadActivos().
     * @param id identificacion (PrimaryKey) del Rol a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<SelectItem> listaSelectItemPaisActivos(){
//        RolDaoImpl daoImpl=new RolDaoImpl();
        List<SelectItem> listaItems=null;//= daoImpl.listaSelectItemPaisActivos();
//        if (!entidades.isEmpty())
//        for (int i = 0; i < entidades.size(); i++) {
//            for (int j = 0; j < listaItems.size(); j++) {
//                SelectItem selectItem = listaItems.get(j);
//                String idPaisString=(String)listaItems.get(j).getValue();
//                int paisId=Integer.parseInt(idPaisString);
//                int paisIdSeleccionado=entidades.get(i).getPaisId().getId().intValue();
//                if(paisIdSeleccionado==paisId)
//                    listaItems.remove(j);
//            }
//        }
        return listaItems;
    }
    /**
     * Metodo Log(String msn).
     * Tiene como objetivo imprimir banderas durante la ejecucion del programa y lo imprime como si fuera 
     * un WARNING, para resaltarse.
     * @param msn de tipo String, Cadena a imprimir.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }
    /**
     * Metodo inicializar().
     * Este metodo inicializa o prepara las variables globales para su posterior uso.
     * @author Gerlin Orlando Torres Saavedra
     */
    private void inicializar(){
        Log("Se INICIALIZARON LOS VALORES");
        entidad=new RolRol();
        //entidad.setRolId(new Rol());
        entidades=new ArrayList<RolRol>();
        rol=new Rol();
        List<String> sourceRol=listadoEnCadenaDeLosRolesHijos();
        List<String> targetRol=new ArrayList<String>();
        this.dualListModelRol=new DualListModel<String>(sourceRol, targetRol);
        
        List<String> sourcePrivilegio=listadoEnCadenaDeLosPrivilegios();
        List<String> targetPrivilegio=new ArrayList<String>();
        this.dualListModelPrivilegio=new DualListModel<String>(sourcePrivilegio, targetPrivilegio);
        listaPrivilegioRol=new ArrayList<PrivilegioRol>();
    }
    public void probar(TransferEvent event){
        Log("ADICION->"+event.isAdd());
        Log("REMOVER->"+event.isRemove());
    }
    
    /**
     * Metodo cancelar().
     * Este metodo prepara el ManageBean al momento que se ha cancelado un intento de registro.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void cancelar(){
        inicializar();
    }
    /**
     * Metodo agregarPaisAlRol().
     * Este metodo permite agregar muchos Pais al usuario en proceso de registro o de modificacion.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void agregarPaisAlRol(){
        Log("Agregar Pais a la lista rolpaises");
        this.entidades.add(entidad);
        //Rol usuario=entidad.getRolId();
        entidad=new RolRol();
        //entidad.setRolId(usuario);
    }
    /**
     * Metodo eliminarPaisRelacionadosAlRol().
     * Este metodo elimina la relacion del pais seleccionado con el Rol en gestion.
     * @param id Identificacion del pais que se intenta eliminar de la relacion con el usuario en gestion.
     * @author Gerlin Orlando Torres Saavedra
     */
    public void eliminarPaisRelacionadosAlRol(Integer id){
//        if(!entidades.isEmpty())
//        for (int i = 0; i < entidades.size(); i++) {
//            RolRol rolPais = entidades.get(i);
//            if(rolPais.getPaisId().getId().equals(id)){
//                entidades.remove(i);
//                break;
//            }
//        }
    }
    private List<String> listadoEnCadenaDeLosPrivilegios(){
        PrivilegioDaoImpl privilegioDaoImpl=new PrivilegioDaoImpl();
        List<Privilegio> listaPrivilegio=privilegioDaoImpl.buscarActivos();
        listaCadenaPrivilegio=new ArrayList<String>();
        for (int i = 0; i < listaPrivilegio.size(); i++) {
            Privilegio privilegio = listaPrivilegio.get(i);
            listaCadenaPrivilegio.add("["+privilegio.getId()+"] "+privilegio.getNombre());            
        }
        return listaCadenaPrivilegio;
    }
    private List<String> listaCadenaRoles,listaCadenaPrivilegio;
    private List<String> listadoEnCadenaDeLosRolesHijos(){
        RolRolDaoImpl rolrolDaoImpl=new RolRolDaoImpl();
        List<RolRol> listaRolRol=rolrolDaoImpl.buscarActivos();
        listaCadenaRoles=new ArrayList<String>();
        for (int i = 0; i < listaRolRol.size(); i++) {
            RolRol rolRol = listaRolRol.get(i);
            listaCadenaRoles.add("["+rolRol.getRolHijoId().getId()+"] "+rolRol.getRolHijoId().getNombre());            
        }
        return listaCadenaRoles;
    }
    private int extraerId(String permiso){
        String id=permiso.trim().substring(1, 2);
        Integer ID=Integer.parseInt(id);
        return ID;
    }
    private String formatearIdsAConsultar(List<String> lista){
        String ids="";
        for (int i = 0; i < lista.size(); i++) {
            Integer id = extraerId(lista.get(i));
            ids=ids+" "+id;
        }
        ids=ids.trim();
        ids=ids.replaceAll(" ", ",");
        return ids;
    }
    public void transferenciaPrivilegios(TransferEvent event){
        List privilegiosTransferido=event.getItems();
        List<String> privilegiosFuentesCadena=this.dualListModelPrivilegio.getSource();
        List<String> privilegiosCargadosCadena=this.dualListModelPrivilegio.getTarget();
        PrivilegioDaoImpl privilegioDaoImpl=new PrivilegioDaoImpl();
        List<String> rolesCargadosCadena=this.dualListModelRol.getTarget();
        String IN=formatearIdsAConsultar(rolesCargadosCadena);
        List<Privilegio> listaPrivilegios=privilegioDaoImpl.buscarPrivilegioPorRoles(IN);
        
    }
    public void relacionarPrivilegios(TransferEvent event){
        List rolesTransferido=event.getItems();
        RolDaoImpl rolDaoImpl=new RolDaoImpl();
        PrivilegioDaoImpl privilegioDaoImpl=new PrivilegioDaoImpl();
        List<String> rolesFuentesCadena=this.dualListModelRol.getSource();
        List<String> rolesCargadosCadena=this.dualListModelRol.getTarget();
        List<String> privilegiosFuentesCadena=this.dualListModelPrivilegio.getSource();
        List<String> privilegiosCargadosCadena=this.dualListModelPrivilegio.getTarget();
        List<Rol> rolesCargados=new ArrayList<Rol>();
        for (int i = 0; i < rolesTransferido.size(); i++) {
            Integer id= extraerId((String)rolesTransferido.get(i));
            if(event.isAdd()){
                rolesCargadosCadena.add((String)rolesTransferido.get(i));
                rolesFuentesCadena.remove((String)rolesTransferido.get(i));
            }else{
                rolesFuentesCadena.add((String)rolesTransferido.get(i));
                rolesCargadosCadena.remove((String)rolesTransferido.get(i));
            }
        }
        if(event.isAdd()){
            List<Privilegio> privilegiosACargar=privilegioDaoImpl.buscarPrivilegioPorRolesTeniendoEnCuentaPrivilegiosFuentes(formatearIdsAConsultar(rolesCargadosCadena),formatearIdsAConsultar(privilegiosFuentesCadena));
            if(privilegiosACargar!=null){
                for (int j = 0; j < privilegiosACargar.size(); j++) {
                    Privilegio privilegio = privilegiosACargar.get(j);
                    for (int k = 0; k < privilegiosFuentesCadena.size(); k++) {
                        Integer idPrivilegioFuente = extraerId(privilegiosFuentesCadena.get(k));
                        if(idPrivilegioFuente.equals(privilegio.getId())){
                            privilegiosCargadosCadena.add(privilegiosFuentesCadena.get(k)+"*");
                            privilegiosFuentesCadena.remove(k);
                        }
                    }
                }
            }
        }else{
            boolean existe=false;
            String IN=formatearIdsAConsultar(rolesCargadosCadena);
            if(!IN.equals("")){
                List<Privilegio> privilegios=privilegioDaoImpl.buscarPrivilegioPorRoles(IN);
                List<String> privilegiosAEliminar=new ArrayList<String>();
                for (int l = 0; l < privilegiosCargadosCadena.size(); l++) {
                    Integer idPrivilegioCargado = extraerId(privilegiosCargadosCadena.get(l));
                    for (int m = 0; m < privilegios.size(); m++) {
                        Privilegio privilegio = privilegios.get(m);
                        if(idPrivilegioCargado.equals(privilegio.getId())&&privilegiosCargadosCadena.get(l).endsWith("*")){
                            existe=true;
                        }
                    }
                    if((!existe) && privilegiosCargadosCadena.get(l).endsWith("*")){
                        privilegiosFuentesCadena.add(privilegiosCargadosCadena.get(l).substring(0, privilegiosCargadosCadena.get(l).length()-1));
                        privilegiosAEliminar.add(privilegiosCargadosCadena.get(l));
                    }else{
                        existe=false;
                    }
                }
                for (int i = 0; i < privilegiosAEliminar.size(); i++) {
                    String string = privilegiosAEliminar.get(i);
                    privilegiosCargadosCadena.remove(string);
                }
            }else{
                if(!privilegiosCargadosCadena.isEmpty()){
                    List<String> privilegiosAEliminar=new ArrayList<String>();
                    for (int j = 0; j < privilegiosCargadosCadena.size(); j++) {
                        String privilegio = privilegiosCargadosCadena.get(j);
                        if(privilegio.endsWith("*")){
                            privilegiosFuentesCadena.add(privilegiosCargadosCadena.get(j).substring(0, privilegiosCargadosCadena.get(j).length()-1));
                            privilegiosAEliminar.add(privilegiosCargadosCadena.get(j));
                        }
                    }
                    for (int i = 0; i < privilegiosAEliminar.size(); i++) {
                        String privilegio = privilegiosAEliminar.get(i);
                        privilegiosCargadosCadena.remove(privilegio);
                    }
                }
            }
        }
    }    
}

