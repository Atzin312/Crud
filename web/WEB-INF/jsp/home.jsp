<%-- 
    Document   : home
    Author     : atzin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Aclaracion de pagos</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap 4 -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <!-- Fontawesome  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="css/home.css"/>
        <style>
            .pac-container {
                z-index: 100000;
            }
        </style>
    </head>


    <body>


        <!-- Header section -->
        <div class="row mx-auto col-md-12 p-4">

            <!-- Home and plus buttons -->
            <div class="row col-md-2 ml-2">

                <!-- Home Button -->
                <div
                    class="
                    d-flex
                    justify-content-center
                    align-items-center
                    rounded-circle
                    bg-white"
                    style="
                    width: 40px;
                    height: 40px; color: black;">

                    <button type="button" class="btn">
                        <span style="font-size: 150%">
                            <i class="fa fa-home"></i>
                        </span>
                    </button>

                </div>

                <!-- Arrow left button -->
                <div class="
                     d-flex
                     justify-content-center
                     align-items-center
                     ml-3"
                     style="
                     width: 40px;
                     height: 40px;">

                    <button type="button" class="btn">
                        <span style="font-size: 150%">
                            <i class="fa fa-arrow-left"></i>
                        </span>
                    </button>

                </div>

            </div>

            <!-- Header title -->
            <div class="col-md-8">

                <h1 class="text-center">Aclaración de Pagos</h1>

            </div>


            <div class="row col-sm-12 col-lg-12 col-md-12">
                <div class="row col-md-3">
                    <h5>Nombre de usuario</h5>
                </div>
                <div class="row col-sm-3 col-lg-3 col-md-3">
                    <form class="form-inline">
                    <input name="txtBuscar" id="txtBuscar" type="search"  class="form-control">
                    <!-- Arrow left button -->
                    <div class="
                        d-flex
                        justify-content-center
                        align-items-center
                        ml-4"
                        style="
                        width: 40px;
                        height: 40px;">

                        <button type="submit" id="btnBuscar" class="btn">
                        <span style="font-size: 150%">
                            <i class="fa fa-search"></i>
                        </span>
                    </button>

                    </div>
                    </form>
                </div>
                
                <div class="col-md-5">

                </div>

                <!-- Plus button -->
                <div class="
                     d-flex
                     justify-content-center
                     align-items-center
                     ml-4"
                     style="
                     width: 40px;
                     height: 40px;">

                    <div
                        class="
                        d-flex
                        justify-content-center
                        align-items-center
                        rounded-circle
                        bg-white"
                        style="
                        width: 40px;
                        height: 40px; color: black;">

                        <button type="button" class="btn">
                            <span style="font-size: 150%">
                                <i class="fa fa-plus-circle"></i>
                            </span>
                        </button>

                    </div>

                </div>
            </div>
            <div class="col-md-12 col-sm-12 col-lg-12">
                <div class="card">
                    <div class="card-header bg-info text-white">

                    </div>
                    <div class="card-body col-md-12 col-sm-12 col-lg-12">
                        <table border="1" class="table">

                            <thead>
                                <tr>
                                    <th>Fecha de solicitud</th>
                                    <th>Fecha resolutiva</th>
                                    <th>Nombre</th>
                                    <th>Tipo de usuarios</th>
                                    <th>Semana de pago</th>
                                    <th>Ver</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${pagos}" var="pago" varStatus="status">
                                    <tr>
                                        <td>${pago.fecha_solicitud}</td>
                                        <td>${pago.fecha_resolutiva}</td>
                                        <td>${pago.nombre}</td>
                                        <td>${pago.tipo_usuario}</td>
                                        <td>${pago.semana_pago}</td>
                                        <td class="fa fa-search"><td>
                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>

                    </div>

                </div>
            </div>
            
        <!-- Modal Create -->
        <div id="ModalCreate" class="modal fade" role="dialog" tabindex="-1" >

            <div class="modal-dialog modal-dialog-centered">

                <!-- Modal content-->
                <div class="modal-content ">

                    <div class="modal-header">
                        <h4 class="modal-title">Nueva aclaracion de pago</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="d-flex align-items-center justify-content-center modal-body">
                        <form action="<c:url value="add_aclaracion.htm" />">
                            <div class=" d-flex  align-items-center form-group" style="width: 80%">
                                <div style="width: 20">
                                    <label>Numero/telefono</label>
                                </div>
                                <div style="width: 40%">
                                    <input type="text" name="nombre" class="form-control" >
                                </div>
                                <div style="width: 10%">
                                    <input class="fa fa-search" >
                                </div>

                            </div>
                            <div class=" d-flex  align-items-center form-group" style="width: 80%">
                                <div style="width: 30%">
                                    <label>Nombre</label>
                                    <label name="nom" id="nom"></label>
                                </div>
                                <div style="whidth: 10"
                                <div style="width: 30%">
                                    <label>Apellidos</label>
                                    <label name="ape" id="ape"></label>
                                </div>
                            </div>
                            <div class=" d-flex form-group" style="width: 50%">
                                <div style="width: 50%">
                                    <label>Tipo de usuario:</label> 
                                </div>
                                <div class="pl-1" style="width: 50%">
                                    <select class="form-control" name="unidad" >
                                        <option value="0">Seleccionar</option>
                                       
                                    </select>
                                </div>

                            </div>
                              <div class=" d-flex  align-items-center form-group" style="width: 50">
                                <div style="width: 20">
                                    <label>Semana de pago</label>
                                </div>
                                <div style="width: 40%">
                                    <input type="text" name="semana" class="form-control" >
                                </div>
                                <div style="width: 10%">
                                    <input class="fa fa-search" >
                                </div>

                            </div>  
                             <div class=" d-flex  align-items-center form-group" style="width: 80%">
                                <div style="width: 40%">
                                    <label>Comentarios:</label>
                                </div>
                                <div style="width: 60%">
                                    <input type="text" class="form-control" name="nota">
                                </div>
                            </div>   
                            <div class="d-flex align-items-center justify-content-around" style="width: 100%">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Guardar</button>
                            </div>
                           

                        </form>
                    </div>

                    <!--
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                    -->

                </div>

            </div>

        </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        
        <script>
            $("#btnBuscar").submit(function (){
                     let parametros = {
                         "txtBuscar":$("#txtBuscar").val()
                     };

                     $.ajax({
                         url: "home.htm",
                         type: "GET",
                         data: parametros,
                         success: function(){
                             console.log("SUCCESS");
                         },
                         error: function(erorr){
                             console.log(erorr);
                         }
                         
                     });
            });
            
            $(document).ready(function(){
                    
            });
        </script>
    </body>
</html>
