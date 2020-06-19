/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conection.ConnectBD;
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
import Repositories.Aclaracion.RepositoriesGetUsuario;
import Repositories.Aclaracion.RepositoriesGetIndex;



/**
 *
 * @author atzin
 */
@Controller
public class Controller_add_aclaracion {

    public int id;
    public int id_aclaracion;
    RepositoriesGetUsuario repository = new RepositoriesGetUsuario();
     RepositoriesGetIndex repositorys = new RepositoriesGetIndex();
    //Querys
      Controller_query procedimiento = new Controller_query();
    
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
       
        String txtGetUser = request.getParameter("txtGetUser");
        System.out.println("GET USER:" + txtGetUser);
        usuarios = repository.GetUsuario(txtGetUser);
       
        System.out.println("SACAR ID ACLARACION PARA EL GET USUARIO " +  usuarios.get(0).getId_usuario());
        id =usuarios.get(0).getId_usuario();
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(usuarios);
        return data;
        
        
    }
    
    
    //Get Rol
        @RequestMapping(value = "/get_roles")
    public @ResponseBody
    String get_roles(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException {

                 List<rol_usuarios> roles = new ArrayList<>();
        
        roles = repository.RolUsuario();
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(roles);
        return data;
        
    }
    
    
    //Add Adlarations
    @RequestMapping(value = "/add_aclaracion.htm")
    public ModelAndView add_aclaracion(HttpServletRequest request/*2*/) throws SQLException, MalformedURLException, IOException, ParseException {
           
        String semana_pagoNue = request.getParameter("semana_pagoNue") + " 00:00:00";
      
        String comentarioNue = request.getParameter("comentarioNue");
         String tipo_usuarioNue = request.getParameter("tipo_usuarioNue");
        repository.AddAclaracion(semana_pagoNue, comentarioNue, tipo_usuarioNue, id);
        
        mav.setViewName("home");

        return mav;
} 
    }
