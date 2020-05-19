/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectBD;
import Model.get_usuarios;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author atzin
 */
public class Controller_search {
     private ConnectBD dbSource = null;

    ModelAndView mav = new ModelAndView();

    public Controller_search() {
        this.dbSource = new ConnectBD();
    }
    @RequestMapping("home.htm")
    public ModelAndView home() {
        List<get_usuarios> usuarios = new ArrayList<>();
        String sql ="{call sp_getusuario(?)}";
        try (Connection dbConnection = dbSource.conectar().getConnection();
                CallableStatement newService = dbConnection.prepareCall(sql);){
            newService.execute();
            
            try (ResultSet servicesRs = (ResultSet) newService.getResultSet();){
                while(servicesRs.next()){
                    get_usuarios usuario = new get_usuarios();
                    usuario.setId_usuario(servicesRs.getInt(1));
                    usuario.setNombre(servicesRs.getString(2));
                    usuario.setApellido(servicesRs.getString(3));
                    usuarios.add(usuario);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        mav.addObject("usuarios",usuarios);
       
        mav.setViewName("home");
        return mav;
    }
}
