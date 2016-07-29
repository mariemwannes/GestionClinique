<%@page import="java.util.Date"%>
<%
    long ts = (new Date()).getTime();
    session.setAttribute("versionJS",ts);
    
    String User = (String) session.getAttribute("user");
    if (User != null) 
    {
        out.println("<script>window.location.href='./logout.jsp';</script>");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Clinisys -- DMI</title>
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
    </head>
	<body>
		<div style=" margin-left: 0px; ">
			<div class="lockscreen animated flipInY">
				<div class="logo">
					<h1 class="semi-bold"><img src="img/logo-o.png" alt="" /> SE CONNECTER </h1>
				</div>
				<div>
					<img src="img/avatars/doctor.jpg" alt="" width="120" height="120" />
					<div>
						<h1>
                                                    <i class="fa fa-user fa-3x text-muted air air-top-right hidden-mobile"></i>
                                                </h1>
                                                <div class="input-group">
                                                    <input id="user" class="form-control" type="text" placeholder="Nom d'utilisateur">
                                                    <div class="input-group-btn">
                                                        <button class="btn btn-primary" disabled>
                                                            <i class="fa fa-user"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="input-group">
                                                    <input id="password" class="form-control" type="password" placeholder="Mot de passe">
                                                    <div class="input-group-btn">
                                                        <button class="btn btn-primary" disabled>
                                                            <i class="fa fa-key"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                                <br>
                                                <% String var = request.getParameter("var");
                                                    if (var != null) 
                                                {%>
                                                <div id="captcha">
                                                </div>
                                                <br>
                                                <%  
                                                }%>
                                                <button id="submit" class="btn btn-primary" style="margin-left: 55%; ">
                                                    Connexion
                                                </button>
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
                <script src="js/notification/SmartNotification.min.js"></script>
                <script src="index.js?version=<%=session.getAttribute("versionJS")%>"></script>
                <script src="js/captcha.js"></script>
                <script src="js/md5.js"></script>
                <%
                    if (var != null) 
                {%>
                    <script type="text/javascript">sjcap();</script>
                <%  
                }%>
	</body>
</html>