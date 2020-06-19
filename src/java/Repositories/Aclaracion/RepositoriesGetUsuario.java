/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.Aclaracion;

import Model.aclaraciones_pagos;
import java.sql.CallableStatement;
import Conection.ConnectBD;
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


public class RepositoriesGetUsuario {
private ConnectBD dbSource = null;

    public RepositoriesGetUsuario() {
        this.dbSource = new ConnectBD();
    }
    
    
    public List<get_usuarios> GetUsuario(String txtGetUser) {
        List<get_usuarios> usuarios = new ArrayList<>();
        String sql = "{call sp_getusuario(?)}";
        
        System.out.println("GET USER:" + txtGetUser);

        try (Connection dbConnection = dbSource.conectar().getConnection();
                CallableStatement newService = dbConnection.prepareCall(sql);) {

            newService.setString(1, txtGetUser);
            newService.execute();
            System.out.println(newService);

            try (ResultSet servicesRs = (ResultSet) newService.getResultSet();) {
                System.out.println("RESPONSE:" + servicesRs);
                while (servicesRs.next()) {
                    get_usuarios usuario = new get_usuarios();
                    usuario.setId_usuario(servicesRs.getInt(1));
                    usuario.setNombre(servicesRs.getString(2));
                    usuario.setApellido(servicesRs.getString(3));
                    usuarios.add(usuario);
                    //id = servicesRs.getInt(1);

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Llegue aca");
        //System.out.println("ID USUARIO ADD "+id);
//        mav.addObject("usuarios",usuarios);
        return usuarios;


        //System.out.println("Antes de retornar");
    }
    
     public List<rol_usuarios> RolUsuario() {
           List<rol_usuarios> roles = new ArrayList<>();
        String sql3 = "SELECT id_rol, nombre_rol FROM public.rol";
        try (Connection dbConnection3 = dbSource.conectar().getConnection();
            
            CallableStatement newService3 = dbConnection3.prepareCall(sql3);){
            
            
            newService3.execute();
            System.out.println(newService3);
            
            try (ResultSet servicesRs = (ResultSet) newService3.getResultSet();){
                System.out.println("RESPONSE 3:"+servicesRs);
                while(servicesRs.next()){
                    rol_usuarios rol = new rol_usuarios();
                    rol.setId_rol(servicesRs.getInt(1));
                    rol.setNombre_rol(servicesRs.getString(2));
                    roles.add(rol);
              
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
      return roles;
       
     }
     public void AddAclaracion(String semana_pagoNue, String comentarioNue,String tipo_usuarioNue, int id){
         String sql ="INSERT INTO aclaraciones_usuarios(id_usuario, fecha_aclaracion, comentario, fk_id_rol) VALUES(in_id_usuario,in_fecha,in_comentario,in_id_rol);";

        sql = sql.replace("in_id_usuario,in_fecha,in_comentario,in_id_rol", id + ", '" + semana_pagoNue + "', '" + comentarioNue + "', "+tipo_usuarioNue );
        System.out.println(sql);
        try (Connection dbConnection = dbSource.conectar().getConnection();
                //Tipo CallableStatement, otra variante tambien es usar PrepareStatement
                CallableStatement newService = dbConnection.prepareCall(sql);) {

                newService.execute();

            } catch (Exception ex) {
                System.err.println("Excepcion sp_registrar_aclaracion_pagos() - Controller_home : " + ex.getMessage());
            }

     }
}
