/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.aclaraciones_pagos;
import java.sql.CallableStatement;
import Model.ConnectBD;
import Model.aclaraciones_usuarios;
import Model.add_aclaracion;
import Model.add_documento;
import Model.get_documento;
import Model.get_usuarios;
import Model.resolutivo_aclaracion;
import Model.rol_usuarios;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.json.stream.JsonGenerationException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.CacheControl;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
     
     
    //Ruta para enviar los archivos
    private static String UPLOADED_FOLDER = "uploads/files";
    //Aquí se van a subir los archivos C:\Users\atzin\GlassFish_Server\glassfish\domains\domain1\config
    private ConnectBD dbSource = null;

    ModelAndView mav = new ModelAndView();

    public Controller_home() {
        this.dbSource = new ConnectBD();
    }

//    @RequestMapping("/home.htm")
//    public ModelAndView home_start(){
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("home");
//        return mav;
//    }
    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {
//        Create the list And the sql sentence
        List<get_usuarios> usuarios = new ArrayList<>();
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

        if (id_usuario >= 1) {
//            Use value to rol id

            List<rol_usuarios> roles = new ArrayList<>();
            String sql3 = "{call sp_getrolesusuario(?) }";
            try (Connection dbConnection3 = dbSource.conectar().getConnection();
                    CallableStatement newService3 = dbConnection3.prepareCall(sql);) {

                newService3.setInt(1, id_usuario);
                newService3.execute();
                System.out.println(newService3);

                try (ResultSet servicesRs = (ResultSet) newService3.getResultSet();) {
                    System.out.println("RESPONSE 3:" + servicesRs);
                    while (servicesRs.next()) {
                        rol_usuarios rol = new rol_usuarios();
                        rol.setId_rol(servicesRs.getInt(1));
                        rol.setNombre_rol(servicesRs.getString(2));
                        roles.add(rol);
                        id_usuario = servicesRs.getInt(1);

                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("ID USUARIO 3 " + id_usuario);
            mav.addObject("roles", roles);
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
            //System.out.println("SEMANA PAGO "+pagos.get(5).getSemana_pago());
//        System.out.println("ID 5ta ACLARACION "+pagos.get(5).getId_aclaracion());
//            System.out.println("ID ACLARACION " + id_aclaracion);
            mav.addObject("pagos", pagos);
       
        }
        mav.setViewName("home");
        return mav;
       
    }

     @RequestMapping("/get_docu")
     @ResponseBody
    public String get_docu(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {
         List<get_documento> docus = new ArrayList<>();
        String sql6 = "{call sp_get_documentos_aclaracion(?)}";
        String getId_aclaracion = request.getParameter("getId_aclaracion");
        id_aclara = Integer.parseInt(request.getParameter("getId_aclaracion"));
        System.out.println("GETID_ACLARACION "+id_aclara);
        try (Connection dbConnection6 = dbSource.conectar().getConnection();
                CallableStatement newService6 = dbConnection6.prepareCall(sql6);) {

            newService6.setInt(1, id_aclara);
            newService6.execute();
            System.out.println(newService6);

            try (ResultSet servicesRs = (ResultSet) newService6.getResultSet();) {
                System.out.println("RESPONSE:" + servicesRs);
                while (servicesRs.next()) {
                    get_documento docu = new get_documento();
                    
                    docu.setId_documento(servicesRs.getInt(1));
                    docu.setId_aclaracion(servicesRs.getInt(2));
                    docu.setId_tipo_documento(servicesRs.getInt(3));
                    docu.setTipo_documento(servicesRs.getString(4));
                    docu.setArchivo(servicesRs.getString(5));
                     docu.setEstado(servicesRs.getString(6));
                    docu.setComentario(servicesRs.getString(7));
                    id_documento = servicesRs.getInt(1);
//                    //GET NOMBRE_ARCHIVO
//                      //create object of Path 
//                    Path path = Paths.get(servicesRs.getString(5)); 
//
//                     //call getFileName() and get FileName path object 
//                    Path fileName = path.getFileName(); 
//
//                     //print FileName 
//                    System.out.println("FileName: " + fileName.toString());   
//                    
//                    docu.setNombre_archivo(fileName.toString());

                    //GET NOMBRE_ARCHIVO
                    String str = servicesRs.getString(5); 
                    String[] arrOfStr = str.split("/", 0); 

//                  for (String a : arrOfStr) 
//                      System.out.println(a); 
                    System.out.println("FILENAME:"+arrOfStr[arrOfStr.length - 1 ]);
                    docu.setNombre_archivo(arrOfStr[arrOfStr.length - 1 ]);
//                    
                    
                    docus.add(docu);
//                    Add value to ID_DOCUMENTO
                     
                    
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       
        //mav.addObject("docus", docus);
        //System.out.println("ID 1 documento "+docus.get(1).getId_documento());
        System.out.println("ID DOCUMENTO Aparte " + id_documento);

        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(docus);
        return data;
    }
    
    
     @RequestMapping("/delete_docu")
    public ModelAndView delete_docu(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {
        
        List<add_documento> eliminar_documentos = new ArrayList<>();
        String sql6 = "{call sp_eliminar_documentos_aclaracion (?,?)}";
         //String getId_aclaracion = request.getParameter("getId_aclaracion");
         id_aclaraEli = Integer.parseInt(request.getParameter("getId_aclaracion"));
          //String getId_documento = request.getParameter("getId_documento");
          id_documentoEli = Integer.parseInt(request.getParameter("getId_documento"));
        System.out.println("GETID_ACLARACION_ELIMINAR "+id_aclara);
        try (Connection dbConnection6 = dbSource.conectar().getConnection();
                CallableStatement newService = dbConnection6.prepareCall(sql6);) {

            newService.setInt(1, id_aclaraEli);
            newService.setInt(2, id_documentoEli);
            newService.execute();
            System.out.println(newService);

            try (ResultSet servicesRs = (ResultSet) newService.getResultSet();) {
                System.out.println("RESPONSE:" + servicesRs);
                while (servicesRs.next()) {
                    add_documento eliminar_documento = new add_documento();
                    
                     eliminar_documento.setId_aclaracion(servicesRs.getInt(1));
                     eliminar_documento.setUrl(servicesRs.getString(2));
                     eliminar_documento.setId_documento(servicesRs.getInt(3));
                    
                    
                    
                    eliminar_documentos.add(eliminar_documento);

                    
                    
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        mav.addObject("eliminar_documentos", eliminar_documentos);
        //System.out.println("ID 1 documento "+docus.get(1).getId_documento());
        System.out.println("ID DOCUMENTO Aparte " + id_documento);

        
        mav.setViewName("home");
        return mav;
    }

     @RequestMapping("/upload_resolutivo")
    public ModelAndView upload_resolutivo(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {
        List<resolutivo_aclaracion> aclaraciones = new ArrayList<>();
            String resolutivoMod = request.getParameter("resolutivoMod");
            System.out.println(resolutivoMod);
            System.out.println("Entre en add Resolutivo");
            id_aclaracionRes = Integer.parseInt(request.getParameter("getId_aclaracion"));
            System.out.println("RESOLUTIVO " + resolutivoMod);
            if (resolutivoMod != null){
                String sql ="{call sp_registrar_resolutivo_aclaracion(?,?) }";
                try (Connection dbConnection = dbSource.conectar().getConnection();
                    CallableStatement newService = dbConnection.prepareCall(sql);) {

                    newService.setInt(1, id_aclaracionRes);
                    newService.setString(2, resolutivoMod);
                    System.out.println(newService);
                    newService.execute();
                    System.out.println(newService);

                    try (ResultSet servicesRs = (ResultSet) newService.getResultSet();) {
                        System.out.println("RESPONSE:" + servicesRs);
                        while (servicesRs.next()) {
                            resolutivo_aclaracion aclaracion = new resolutivo_aclaracion();
                            aclaracion.setId_resolutivo(servicesRs.getInt(1));
                            aclaracion.setResolutivo(servicesRs.getString(2));
                            aclaraciones.add(aclaracion);

                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("id_aclaracion" + id_aclaracion);
                mav.addObject("aclaraciones", aclaraciones);
                 mav.setViewName("home");
            }
        mav.setViewName("home");
        
        return mav;
    
    }
}

