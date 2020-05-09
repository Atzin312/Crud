<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
                <input id="nomUsuario0" type="text" class="form-control">
            </div>
            <!-- Arrow left button -->
            <div class="
                     d-flex
                     justify-content-center
                     align-items-center
                     ml-4"
                     style="
                     width: 40px;
                     height: 40px;">

                    <button type="button" class="btn">
                        <span style="font-size: 150%">
                            <i class="fa fa-search"></i>
                        </span>
                    </button>

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
        <div class=" mx-auto col-md-12 col-sm-12 col-lg-12">
            <div class="card">
                <div class="card-header bg-info text-white">
                    
                </div>
                <div class="card-body col-md-12 col-sm-12 col-lg-12">
                    <table border="1">
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
                           
                            <th></th>
                                
                        </tbody>
                    </table>

                </div>
            
            </div>
        </div>
         <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>


    </body>
</html>
