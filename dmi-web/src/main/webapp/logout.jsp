<%
    String User = (String) session.getAttribute("user");
    if (User == null || User.equalsIgnoreCase("null")) 
    {
        out.println("<script>window.location.href='./index.jsp';</script>");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title> DMI </title>
        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

        <!-- #CSS Links -->
        <!-- Basic Styles -->
        <link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/font-awesome.min.css">

        <!-- SmartAdmin Styles : Caution! DO NOT change the order -->
        <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production-plugins.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-skins.min.css">

        <!-- SmartAdmin RTL Support -->
        <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css"> 

        <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
        <link rel="stylesheet" type="text/css" media="screen" href="css/demo.min.css">

        <!-- page related CSS -->
        <link rel="stylesheet" type="text/css" media="screen" href="css/lockscreen.min.css">

        <!-- #FAVICONS -->
        <link rel="shortcut icon" href="img/favicon/favicon.ico" type="image/x-icon">
        <link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">

        <!-- #GOOGLE FONT -->
        <link rel="stylesheet" href="css/googleapis.css">
    </head>

	
	<body>

		<div style=" margin-left: 0px; ">

			<!-- MAIN CONTENT -->

			<div class="lockscreen animated flipInY">
				<div class="logo">
					<h1 class="semi-bold"><img src="img/logo-o.png" alt="" /> Dossier Médical Informatisé </h1>
				</div>
				<div>
					<img src="img/avatars/doctor.jpg" alt="" width="120" height="120" />
					<div>
						<h1><i class="fa fa-user fa-3x text-muted air air-top-right hidden-mobile"></i>
                                                    <%
                                                        String User2 = (String) session.getAttribute("user");
                                                    %>
                                                    <%= User2 %>
                                                    <small><i class="fa fa-lock text-muted"></i> Utilisateur Connecté</small></h1>

						<br>
                                                <a onClick="retour()"><button class="btn btn-primary" style="margin-left: 15%; ">
                                                    Retour
                                                </button></a>
                                                <a href="logout_function.jsp"><button id="submit" class="btn btn-danger" style="margin-left: 15%; ">
                                                    Déconnexion
                                                </button></a>
					</div>

				</div>
				<p class="font-xs margin-top-5">
					Copyright Computer Systems 2015.
				</p>
			</div>

		</div>

		<!-- BOOTSTRAP JS -->
                <script src="body_page_js/acceuil_function.js?version=<%=session.getAttribute("versionJS")%>"></script>
                <script src="js/jquery/jquery.min.js"></script>
                <script src="js/jquery/jquery-ui.min.js"></script>
	</body>
        <script>
            function retour()
            {
                window.history.back();
            }
        </script>
</html>
