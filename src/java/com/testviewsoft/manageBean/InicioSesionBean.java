/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.manageBean;

import com.testviewsoft.dao.impl.CuentaDaoImpl;
import com.testviewsoft.dao.impl.DocumentoIdentidadDaoImpl;
import com.testviewsoft.dao.impl.DominioDaoImpl;
import com.testviewsoft.dao.impl.PrivilegioDaoImpl;
import com.testviewsoft.dao.impl.RolDaoImpl;
import com.testviewsoft.dao.impl.UsuarioDaoImpl;
import com.testviewsoft.entity.Cuenta;
import com.testviewsoft.entity.DocumentoIdentidad;
import com.testviewsoft.entity.Dominio;
import com.testviewsoft.entity.Privilegio;
import com.testviewsoft.entity.PrivilegioRol;
import com.testviewsoft.entity.Rol;
import com.testviewsoft.entity.RolRol;
import com.testviewsoft.entity.Usuario;
import com.testviewsoft.entity.UsuarioPais;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.FlowEvent;

/**
 * ManageBean InicioSesionBean.
 * En este ManageBean se controlan la autenticacion de los usuarios, incleyendo el registro.
 * @see Usuario Entity[Usuario]: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
 * @see Cuenta Entity[Cuenta]: Comprendase como entidad (entity) la relacion de los atributos de esta clase con los campos de una tabla en una Base de Datos.
 * @author Gerlin Orlando Torres Saavedra.
 */
@ManagedBean
@SessionScoped
public class InicioSesionBean implements Serializable{
    private Cuenta entidad; 
    private Usuario usuario;
    private UsuarioPais usuarioPais;
    private String idDocumentoIdentificacion;
    private String idPais;
    private String idDominio;
    private DocumentoIdentidad documentoIdentidad;
    private Dominio dominio;
    private List<UsuarioPais> listaUsuarioPais;
    private boolean skip;
    private static Logger logger = Logger.getLogger(InicioSesionBean.class.getName());  
   /**
     * Constructor InicioSesionBean() (Vacio).
     * 
     * @author Gerlin Orlando Torres Saavedra.
     */
    public InicioSesionBean() {
        inicializar();
        InicializarCuentaAdministrador();
    }
    /**
     * Metodo InicializarCuentaAdministrador() (Vacio).
     * Este metodo crea las credenciales por defecto para que el usuario administrador inicie sesion.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void InicializarCuentaAdministrador(){
        DominioDaoImpl dominioDaoImpl=new DominioDaoImpl();
        DocumentoIdentidadDaoImpl documentoIdentificacionDaoImpl=new DocumentoIdentidadDaoImpl();
        UsuarioDaoImpl usuarioDaoImpl=new UsuarioDaoImpl();
        List<Dominio> listaDominio=dominioDaoImpl.buscarActivos();
        CuentaDaoImpl daoImpl=new CuentaDaoImpl();
        if(listaDominio.isEmpty()){
            Date tiempo=new Date();
            //DOMINIO
            Dominio dominio=new Dominio();
            dominio.setNombre("dominio.com");
            dominio.setTiempoEstado(tiempo);
            dominio.setEstado(Boolean.TRUE);
            dominioDaoImpl.registrar(dominio);
            //DOCUMENTO
            DocumentoIdentidad documentoIdentidad=new DocumentoIdentidad();
            documentoIdentidad.setNombre("Cédula de Ciudadania");
            documentoIdentidad.setTiempoEstado(tiempo);
            documentoIdentidad.setEstado(Boolean.TRUE);
            documentoIdentificacionDaoImpl.registrar(documentoIdentidad);
            //USUARIO
            Usuario usuarioAdmin=new Usuario();
            usuarioAdmin.setApellido("Administrador");
            usuarioAdmin.setDocumentoIdentidadId(documentoIdentidad);
            usuarioAdmin.setEstado(Boolean.TRUE);
            usuarioAdmin.setFechaNacimiento(tiempo);
            usuarioAdmin.setMail("administrador@micorreopersonal.com");
            usuarioAdmin.setNombre("Administrador");
            usuarioAdmin.setReferenciaIdentificacion("0000000000");
            usuarioAdmin.setSexo("M");
            usuarioAdmin.setTiempoEstado(tiempo);
            usuarioAdmin.setUsuarioPaisList(new ArrayList<UsuarioPais>());
            usuarioDaoImpl.registrar(usuarioAdmin);
            //PRIVILEGIO
            Privilegio privilegio=new Privilegio();
            privilegio.setNombre("iniciarSesion");
            privilegio.setEstado(Boolean.TRUE);
            privilegio.setTiempoEstado(tiempo);
            PrivilegioDaoImpl privilegioDaoImpl=new PrivilegioDaoImpl();
            privilegioDaoImpl.registrar(privilegio);
            //ROL ADMINISTRADOR
            Rol rolAdministrador=new Rol();
            rolAdministrador.setNombre("ADMINISTRADOR");
            rolAdministrador.setPrivilegioRolList(new ArrayList<PrivilegioRol>());
            RolRol rolHijoAdministrador=new RolRol();
            rolHijoAdministrador.setRolHijoId(rolAdministrador);
            rolHijoAdministrador.setEstado(Boolean.TRUE);
            rolHijoAdministrador.setTiempoEstado(tiempo);
            PrivilegioRol privilegioRol=new PrivilegioRol();
            privilegioRol.setPrivilegioId(privilegio);
            privilegioRol.setRolId(rolAdministrador);
            privilegioRol.setEstado(Boolean.TRUE);
            privilegioRol.setTiempoEstado(tiempo);
            List<PrivilegioRol> listaPR=new ArrayList<PrivilegioRol>();
            listaPR.add(privilegioRol);
            rolAdministrador.setPrivilegioRolList(listaPR);
            List<RolRol> lista=new ArrayList<RolRol>();
            lista.add(rolHijoAdministrador);
            rolAdministrador.setRolRolList(lista);
            RolDaoImpl rolDaoImpl=new RolDaoImpl();
            rolDaoImpl.registrar(rolAdministrador);
            //ROL INVITADO
            Rol rolInvitado=new Rol();
            rolInvitado.setNombre("INVITADO");
            rolInvitado.setPrivilegioRolList(new ArrayList<PrivilegioRol>());
            RolRol rolHijoInvitado=new RolRol();
            rolHijoInvitado.setRolHijoId(rolInvitado);
            rolHijoInvitado.setEstado(Boolean.TRUE);
            rolHijoInvitado.setTiempoEstado(tiempo);
            privilegioRol.setPrivilegioId(privilegio);
            privilegioRol.setRolId(rolInvitado);
            privilegioRol.setEstado(Boolean.TRUE);
            privilegioRol.setTiempoEstado(tiempo);
            listaPR=new ArrayList<PrivilegioRol>();
            listaPR.add(privilegioRol);
            rolInvitado.setPrivilegioRolList(listaPR);
            lista=new ArrayList<RolRol>();
            lista.add(rolHijoInvitado);
            rolInvitado.setRolRolList(lista);
            rolDaoImpl.registrar(rolInvitado);
            //CUENTA
            Cuenta cuentaAdmin=new Cuenta();
            cuentaAdmin.setDominioId(dominio);
            cuentaAdmin.setCorreo("admin");
            cuentaAdmin.setContrasena("admin");
            cuentaAdmin.setUsuario("admin");
            cuentaAdmin.setTiempoEstado(tiempo);
            cuentaAdmin.setEstado(Boolean.TRUE);
            cuentaAdmin.setUsuarioId(usuarioAdmin);
            cuentaAdmin.setRolId(rolAdministrador);
            daoImpl.registrar(cuentaAdmin);
            
        }
    }
    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
    public Cuenta getCuenta() {
        return entidad;
    }

    public void setCuenta(Cuenta entidad) {
        this.entidad = entidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getIdDocumentoIdentificacion() {
        return idDocumentoIdentificacion;
    }

    public void setIdDocumentoIdentificacion(String idDocumentoIdentificacion) {
        DocumentoIdentidadDaoImpl daoImpl=new DocumentoIdentidadDaoImpl();
        documentoIdentidad=daoImpl.buscarPorId(Integer.parseInt(idDocumentoIdentificacion));
        usuario.setDocumentoIdentidadId(documentoIdentidad);
        this.idDocumentoIdentificacion = idDocumentoIdentificacion;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(String idDominio) {
        DominioDaoImpl daoImpl=new DominioDaoImpl();
        dominio=daoImpl.buscarPorId(Integer.parseInt(idDominio));
        entidad.setDominioId(dominio);
        this.idDominio = idDominio;
    }
    
    public List<UsuarioPais> getListaUsuarioPais() {
        return listaUsuarioPais;
    }
    
    public void inicializar(){
        usuario=new Usuario();
        Dominio dominio=new Dominio();
        entidad=new Cuenta();
        entidad.setDominioId(dominio);
        usuarioPais=new UsuarioPais();
        listaUsuarioPais=new ArrayList<UsuarioPais>();
        usuario.setUsuarioPaisList(listaUsuarioPais);
    }
    public String iniciarSesion(){
        CuentaDaoImpl cuentaDaoImpl=new CuentaDaoImpl();
        entidad=cuentaDaoImpl.verificarAutenticacion(entidad);
        if(entidad!=null){
            return "vistaUsuarios";
        }else{
            inicializar();
            return "";
        }
    }
    public void registrar(){
        UsuarioDaoImpl usuarioDaoImpl=new UsuarioDaoImpl();
        usuarioDaoImpl.registrar(usuario);
        CuentaDaoImpl cuentaDaoImpl=new CuentaDaoImpl();
        RolDaoImpl rolDaoImpl=new RolDaoImpl();
        Rol rolInvitado=rolDaoImpl.buscarPorId(2);
        entidad.setUsuarioId(usuario);
        entidad.setRolId(rolInvitado);
        String mensaje=cuentaDaoImpl.registrar(entidad);
        inicializar();
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE CUENTA: ",mensaje));        
    
    }
    public String recorrerflujoDeCreacionDeCuenta(FlowEvent event) {
        String actual,siguiente;
        actual=event.getOldStep();
        siguiente=event.getNewStep();
        logger.info("Current wizard step:" + actual);  
        logger.info("Next step:" + siguiente);  
        
        if(skip) {  
            skip = false;   //reset in case user goes back  
            return "confirm";  
        }  
        else {  
            return siguiente;  
        }  
    }
    public List<SelectItem> listaSelectItemPaisActivos(){
        UsuarioDaoImpl daoImpl=new UsuarioDaoImpl();
        List<SelectItem> listaItems= daoImpl.listaSelectItemPaisActivos();
        if (!listaUsuarioPais.isEmpty())
        for (int i = 0; i < listaUsuarioPais.size(); i++) {
            for (int j = 0; j < listaItems.size(); j++) {
                SelectItem selectItem = listaItems.get(j);
                String idPaisString=(String)listaItems.get(j).getValue();
                int paisId=Integer.parseInt(idPaisString);
                int paisIdSeleccionado=listaUsuarioPais.get(i).getPaisId().getId().intValue();
                if(paisIdSeleccionado==paisId)
                    listaItems.remove(j);
            }
        }
        return listaItems;
    }
    public void agregarPaisAlUsuario(){
        this.listaUsuarioPais.add(usuarioPais);
        Usuario usuario=usuarioPais.getUsuarioId();
        usuarioPais=new UsuarioPais();
        usuarioPais.setUsuarioId(usuario);
    }
    public void eliminarPaisRelacionadosAlUsuario(Integer id){
        if(!listaUsuarioPais.isEmpty())
        for (int i = 0; i < listaUsuarioPais.size(); i++) {
            UsuarioPais usuariosPais = listaUsuarioPais.get(i);
            if(usuariosPais.getPaisId().getId().equals(id)){
                listaUsuarioPais.remove(i);
                break;
            }
        }
    }
    public void cancelar(){
        inicializar();
    }
}
