/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.aclaraciones_pagos;
import java.sql.CallableStatement;
import Model.ConnectBD;
import Model.get_usuarios;
import Model.rol_usuarios;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.json.stream.JsonGenerationException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author atzin
 */
public class Controller_home {
    public int id_usuario;
    public int id;
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
    public ModelAndView home(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException 
    {
//        Create the list And the sql sentence
        List<get_usuarios> usuarios = new ArrayList<>();
        String sql ="{call sp_getusuario(?)}";
        String txtBuscar = request.getParameter("txtBuscar");
        System.out.println(txtBuscar);
        
        try (Connection dbConnection = dbSource.conectar().getConnection();
            
            CallableStatement newService = dbConnection.prepareCall(sql);){
            
            newService.setString(1, txtBuscar);
            newService.execute();
            System.out.println(newService);
            
            try (ResultSet servicesRs = (ResultSet) newService.getResultSet();){
                System.out.println("RESPONSE:"+servicesRs);
                while(servicesRs.next()){
                    get_usuarios usuario = new get_usuarios();
                    usuario.setId_usuario(servicesRs.getInt(1));
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
        
        System.out.println("ID USUARIO "+id_usuario);
        mav.addObject("usuarios",usuarios);
        
        
        
        if (id_usuario >=1){
//            Use value to rol id


        List<rol_usuarios> roles = new ArrayList<>();
        String sql3 =  "{call sp_getrolesusuario(?) }";
        try (Connection dbConnection3 = dbSource.conectar().getConnection();
            
            CallableStatement newService3 = dbConnection3.prepareCall(sql);){
            
            newService3.setInt(1, id_usuario);
            newService3.execute();
            System.out.println(newService3);
            
            try (ResultSet servicesRs = (ResultSet) newService3.getResultSet();){
                System.out.println("RESPONSE 3:"+servicesRs);
                while(servicesRs.next()){
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
        
        System.out.println("ID USUARIO 3 "+id_usuario);
        mav.addObject("roles",roles);    
//            Add value for home index 
        List<aclaraciones_pagos> pagos = new ArrayList<>();
        String sql2 = "{call sp_indexaclaracionespagos(?) }";
         
        try (Connection dbConnection2 = dbSource.conectar().getConnection();
                //Tipo CallableStatement, otra variante tambien es usar PrepareStatement
                CallableStatement newService2 = dbConnection2.prepareCall(sql2);) {
            newService2.setInt(1, id_usuario);
            System.out.println("ID USUARIO EN INDEX "+id_usuario);
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

                    pagos.add(pago);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        mav.addObject("pagos", pagos);
//        System.out.println(pagos.get(0).getFecha_solicitud());
        
        
        }
        mav.setViewName("home");
        
        return mav;
    }
    
    
   
}
//    @RequestMapping("/home.htm")
//    public ModelAndView index_usuario() 
//    {
//        
//        System.out.println("ID_USUARIO"+id_usuario);
//        List<aclaraciones_pagos> pagos = new ArrayList<>();
//        String sql = "{call sp_indexaclaracionespagos(?)}";
//         
//        try (Connection dbConnection = dbSource.conectar().getConnection();
//                //Tipo CallableStatement, otra variante tambien es usar PrepareStatement
//                CallableStatement newService = dbConnection.prepareCall(sql);) {
//            newService.setInt(1, id_usuario);
////            dbConnection.setAutoCommit(false);
//            newService.execute();
//
//            try (ResultSet servicesRs = (ResultSet) newService.getResultSet();) {
//                while (servicesRs.next()) {
//
//                    //System.out.println(servicesRs.getString(1));
//                    aclaraciones_pagos pago = new aclaraciones_pagos();
//                    pago.setFecha_solicitud(servicesRs.getString(1));
//                    pago.setFecha_resolutiva(servicesRs.getString(2));
//                    pago.setNombre(servicesRs.getString(3));
//                    pago.setTipo_usuario(servicesRs.getString(4));
//                    pago.setSemana_pago(servicesRs.getString(5));
//                    pago.setId_aclaracion(servicesRs.getInt(6));
//                    pago.setId_resolutivo_aclaracion(servicesRs.getInt(7));
//                    pago.setId_usuario(servicesRs.getInt(8));
//                    pago.setUsuario(servicesRs.getString(9));
//                    pago.setApellido(servicesRs.getString(10));
//                    pago.setId_rol(servicesRs.getInt(11));
//                    pago.setResolutivo(servicesRs.getString(12));
//                    pago.setComentario(servicesRs.getString(13));
//                    pago.setId_ciudad(servicesRs.getInt(14));
//                    pago.setCiudad(servicesRs.getString(15));
//
//                    pagos.add(pago);
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        mav.addObject("pagos", pagos);
//        System.out.println(pagos.get(0).getFecha_solicitud());
//        mav.setViewName("home");
//        return mav;
//    }
//    
    
    
    

