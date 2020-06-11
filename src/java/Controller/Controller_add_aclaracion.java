/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectBD;
import Model.aclaraciones_usuarios;
import Model.get_usuarios;
import Model.rol_usuarios;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author atzin
 */
@Controller
public class Controller_add_aclaracion {

    public int id;
    public int id_aclaracion;
    
    private ConnectBD dbSource = null;
    ModelAndView mav = new ModelAndView();
    

    public Controller_add_aclaracion() {
        this.dbSource = new ConnectBD();
    }
    //Get User
    @RequestMapping(value = "/get_usuario")
    public @ResponseBody
    String get_usuario(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {

        List<get_usuarios> usuarios = new ArrayList<>();
        String sql = "{call sp_getusuario(?)}";
        String txtGetUser = request.getParameter("txtGetUser");
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
                    id = servicesRs.getInt(1);

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Llegue aca");
        System.out.println("ID USUARIO ADD "+id);
//        mav.addObject("usuarios",usuarios);
        


        System.out.println("Antes de retornar");
        
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(usuarios);
        return data;
        
    }
    
    
    //Get Rol
        @RequestMapping(value = "/get_roles")
    public @ResponseBody
    String get_roles(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {

                 List<rol_usuarios> roles = new ArrayList<>();
        String sql3 =  "SELECT id_rol, nombre_rol FROM public.rol";
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
        
        System.out.println(roles.get(2).getNombre_rol());

        System.out.println("Antes de retornar");
        
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(roles);
        return data;
        
    }
    
    
    //Add Adlarations
    @RequestMapping(value = "/add_aclaracion.htm")
    public ModelAndView add_aclaracion(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException, ParseException {
           
        
        System.out.println("Entre en add Aclaracion");
        String sql ="INSERT INTO aclaraciones_usuarios(id_usuario, fecha_aclaracion, comentario, fk_id_rol) VALUES(in_id_usuario,in_fecha,in_comentario,in_id_rol);";
        
        String semana_pagoNue = request.getParameter("semana_pagoNue") + " 00:00:00";
       
         System.out.println("Semana Pago Convertido:" + semana_pagoNue);
         
        String comentarioNue = request.getParameter("comentarioNue");
         String tipo_usuarioNue = request.getParameter("tipo_usuarioNue");
        
        sql = sql.replace("in_id_usuario,in_fecha,in_comentario,in_id_rol", id + ", '" + semana_pagoNue + "', '" + comentarioNue + "', "+tipo_usuarioNue );
        System.out.println(sql);
        try (Connection dbConnection = dbSource.conectar().getConnection();
                //Tipo CallableStatement, otra variante tambien es usar PrepareStatement
                CallableStatement newService = dbConnection.prepareCall(sql);) {

                newService.execute();

            } catch (Exception ex) {
                System.err.println("Excepcion sp_registrar_aclaracion_pagos() - Controller_home : " + ex.getMessage());
            }
        
        mav.setViewName("home");

        return mav;
} 
    }
