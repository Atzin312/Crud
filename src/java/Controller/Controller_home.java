/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectBD;
import Model.aclaraciones_pagos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author atzin
 */
@Controller
public class Controller_home {

    private ConnectBD dbSource = null;

    ModelAndView mav = new ModelAndView();

    public Controller_home() {
        this.dbSource = new ConnectBD();
    }
    
    
    @RequestMapping("home.htm")
    public ModelAndView home() throws SQLException {
        List<aclaraciones_pagos> pagos = new ArrayList<>();
        String sql = "{call sp_indexaclaracionespagos(?)}";

        try (Connection dbConnection = dbSource.conectar().getConnection();
                //Tipo CallableStatement, otra variante tambien es usar PrepareStatement
                CallableStatement newService = dbConnection.prepareCall(sql);) {
            newService.setInt(1, 1);
//            dbConnection.setAutoCommit(false);
            newService.execute();

            try (ResultSet servicesRs = (ResultSet) newService.getResultSet();) {
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
        System.out.println(pagos.get(0).getFecha_solicitud());
        mav.setViewName("home");
        return mav;

//            newService.setString(1, "chuky@gmail.com");
//           aclaraciones_pagos pagos;
//           System.out.println(newService.execute()); 
//        } catch (Exception ex) {
//            System.err.println("Excepcion2: " + ex.getMessage());
//            //ex.printStackTrace();
//        }
//        mav.setViewName("home");
//        return mav;
    }
}
