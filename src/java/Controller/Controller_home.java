/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.aclaraciones_pagos;
import Conection.ConnectBD;
import Model.add_documento;
import Model.get_documento;
import Model.get_usuarios;
import Model.resolutivo_aclaracion;
import Repositories.Aclaracion.RepositoriesGetIndex;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author atzin
 */
public class Controller_home {

    public int id_usuario;
    public int id;
    public int id_aclaracion;
    public int id_documento;
    public int id_aclara;
    public int id_aclaraEli;
    public int id_documentoEli;
    public int id_aclaracionRes;
     private CacheManager cacheManager;        
    
     @Scheduled(cron = "0 0/30 * * * ?")              // execure after every 30 min
    public void clearCacheSchedule(){
        for(String name:cacheManager.getCacheNames()){
            cacheManager.getCache(name).clear();            // clear cache by name
        }
    }
     
     RepositoriesGetIndex repository = new RepositoriesGetIndex();
    //Ruta para enviar los archivos
    private static String UPLOADED_FOLDER = "uploads/files";
    //Aquí se van a subir los archivos C:\Users\atzin\GlassFish_Server\glassfish\domains\domain1\config
    private ConnectBD dbSource = null;

    ModelAndView mav = new ModelAndView();

    public Controller_home() {
        this.dbSource = new ConnectBD();
    }
        
    //Collect ID_USER and Create Index Table
 //Collect ID_USER and Create Index Table
    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {
//        Create the list And the sql sentence
        List<get_usuarios> usuarios = new ArrayList<>();
        //String sql = "{call sp_getusuario(?)}";
        
        String sql = "{call sp_getusuario(?)}";
        
        String txtBuscar = request.getParameter("txtBuscar");
        System.out.println(txtBuscar);

        try (Connection dbConnection = dbSource.conectar().getConnection();
                CallableStatement newService = dbConnection.prepareCall(sql);) {

            newService.setString(1, txtBuscar);
            newService.execute();
            System.out.println(newService);

            try (ResultSet servicesRs = (ResultSet) newService.getResultSet();) {
                System.out.println("RESPONSE:" + servicesRs);
                while (servicesRs.next()) {
                    get_usuarios usuario = new get_usuarios();
                    usuario.setId_usuario(servicesRs.getInt(1));
                    usuario.setNum_telefono(servicesRs.getString(4));
                    usuario.setCorreo(servicesRs.getString(5));
                    usuarios.add(usuario);
//                    Add value to user id
                    id_usuario = servicesRs.getInt(1);

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("ID USUARIO " + id_usuario);
        mav.addObject("usuarios", usuarios);

        if (id_usuario >= 0) {
//            Use value to rol id

            
//            Add value for home index 
            List<aclaraciones_pagos> pagos = new ArrayList<>();
             String sql2 = "{call sp_indexaclaracionespagos(?) }";
            try (Connection dbConnection2 = dbSource.conectar().getConnection();
                    //Tipo CallableStatement, otra variante tambien es usar PrepareStatement
                    CallableStatement newService2 = dbConnection2.prepareCall(sql2);) {
                newService2.setInt(1, id_usuario);
                System.out.println("ID USUARIO EN INDEX " + id_usuario);
//            dbConnection.setAutoCommit(false);
                newService2.execute();

                try (ResultSet servicesRs = (ResultSet) newService2.getResultSet();) {
                    while (servicesRs.next()) {

                        //System.out.println(servicesRs.getString(1));
                        aclaraciones_pagos pago = new aclaraciones_pagos();
                        pago.setFecha_solicitud(servicesRs.getString(1));
                        pago.setFecha_resolutiva(servicesRs.getString(2));
                        pago.setNombre(servicesRs.getString(3));
                        pago.setTipo_usuario(servicesRs.getString(4));
                        pago.setSemana_pago(servicesRs.getString(5));
                        pago.setId_aclaracion(servicesRs.getInt(6));
                        pago.setId_resolutivo_aclaracion(servicesRs.getInt(7));
                        pago.setId_usuario(servicesRs.getInt(8));
                        pago.setUsuario(servicesRs.getString(9));
                        pago.setApellido(servicesRs.getString(10));
                        pago.setId_rol(servicesRs.getInt(11));
                        pago.setResolutivo(servicesRs.getString(12));
                        pago.setComentario(servicesRs.getString(13));
                        pago.setId_ciudad(servicesRs.getInt(14));
                        pago.setCiudad(servicesRs.getString(15));
                        id_aclaracion = servicesRs.getInt(6);
                        pagos.add(pago);
                        
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("ID ACLA " + id_aclaracion);

            mav.addObject("pagos", pagos);
       System.out.println("pagos "+pagos);
       
        }
        mav.setViewName("home");
        return mav;
       
    }
        //Get Documents
     @RequestMapping("/get_docu")
     @ResponseBody
    public String get_docu(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {
         
        List<get_documento> docus = new ArrayList();
        String getId_aclaracion = request.getParameter("getId_aclaracion");
        id_aclara = Integer.parseInt(request.getParameter("getId_aclaracion"));
        docus = repository.getDocumentos(id_aclara);
        System.out.println("ID DOCUMENTO Aparte " + id_documento);

        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(docus);
        return data;
    }
    
    //Delete Documents
     @RequestMapping("/delete_docu")
    public ModelAndView delete_docu(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {
        
        List<add_documento> eliminar_documentos = new ArrayList<>();
        String sql6 = "{call sp_eliminar_documentos_aclaracion (?,?)}";
         id_aclaraEli = Integer.parseInt(request.getParameter("getId_aclaracion"));
         id_documentoEli = Integer.parseInt(request.getParameter("getId_documento"));
         eliminar_documentos = repository.DeleteDocumentos(id_aclaraEli, id_documentoEli);
        System.out.println("GETID_ACLARACION_ELIMINAR "+id_aclara);
 
        mav.addObject("eliminar_documentos", eliminar_documentos);
        System.out.println("ID DOCUMENTO Aparte " + id_documento);

        
        mav.setViewName("home");
        return mav;
    }
    
    //Upload Resolution
     @RequestMapping("/upload_resolutivo")
    public ModelAndView upload_resolutivo(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {
        List<resolutivo_aclaracion> aclaraciones = new ArrayList<>();
            String resolutivoMod = request.getParameter("resolutivoMod");
            id_aclaracionRes = Integer.parseInt(request.getParameter("getId_aclaracion"));
            System.out.println("RESOLUTIVO " + resolutivoMod);
            if (resolutivoMod != null){
                String sql ="{call sp_registrar_resolutivo_aclaracion(?,?) }";
                aclaraciones = repository.uploadResolutivo(id_aclaracionRes, resolutivoMod);
                System.out.println("id_aclaracion" + resolutivoMod);
                mav.addObject("aclaraciones", aclaraciones);
                 mav.setViewName("home");
            }
        mav.setViewName("home");
        
        return mav;
    
    }
}

