
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clinisys -- DMI</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <jsp:include page="../body_page/css_declare.jsp"/>
        

        <style>
            @media all and (max-device-width: 768px) 
            {
                #width_image
                {
                    width: 90% !important;

                }
            }

            @media screen and (min-width: 1200px) 
            {
                #width_image
                {
                    width: 50% !important;
                }
            }

            @media print 
            {
                .no_print
                {
                    display:none;
                }
                .print
                {
                    display:block;
                }

            }
            .minified nav ul>li>a {
                padding: 15px 11px;
                display: block;
                cursor: pointer;
            }
            .minified nav>ul>li {
                border-bottom: 1px solid #1A1817;
                border-top: 1px solid #525151;
                cursor: pointer;
            }
            body{
                cursor: pointer;
            }
        </style>

    </head>
    <body class="minified fixed-header fixed-navigation no_print" oncontextmenu="return false;">

        <!-- #HEADER -->
        <header id="header">
            <div id="logo-group" class="activity-dropdown">
                <span name="_info_pat_rea" style="width: 400px;"> 
                    <img class="_avatar_patient" src="../img/avatars/male.png" alt="me" class="online" style="height: 40px; width: 40px; margin-top: 3px; margin-left: 3px;vertical-align: top"
                         onclick="javascript:uploadImagePatient()"/> 
                    <span class="_nom_patient click_info" style="color:#000000;font-weight: 700;"></span> 
                    <span class="_age_patient click_info"></span>
                </span>
                <span name="_info_pat_rea" class="_num_cha_patient click_info" style="margin-left: 46px; margin-top: -24px;width: 400px;"></span>
                <span name="_info_pat_rea" class="_num_dossier_patient click_info" style="margin-left: 160px; margin-top: -39px;width: 400px;"></span>
                <span name="_info_pat_rea_cloturee"> 
                    <img class="_avatar_patient" src="../img/avatars/male.png" alt="me" class="online" style=" height: 40px; width: 40px; margin-top: 3px; margin-left: 3px;vertical-align: top "/> 
                    <span class="_nom_patient_cloturee click_info" style="color:#000000;font-weight: 700;"></span> 
                    <span class="_age_patient_cloturee click_info"></span>
                </span>
                <span name="_info_pat_rea_cloturee" class="_num_cha_patient_cloturee click_info" style="margin-left: 46px; margin-top: -24px;width: 100%"></span>

                <span class="click_info" style=" margin-left: 46px;margin-top: -23px;width: 300px;">
                    <b><span class="click_info" id="_medecin_infirmier"></span>  </b><span class="click_info" id="_nom_medecin"></span>
                </span>

                <div class="ajax-dropdown">
                    <div class="btn-group btn-group-justified" data-toggle="buttons">
                        <label class="btn btn-default">
                            <input type="radio" name="activity" id="../body_page/liste_patients_dropdown.jsp">
                            Liste des patients </label>
                        <label class="btn btn-default">
                            <input type="radio" name="activity" id="../body_page/patient_en_cours.jsp">
                            Patient en cours </label>
                    </div>

                    <div class="ajax-notifications custom-scroll">
                        <div class="alert alert-transparent">
                            <div class="row">
                                <div class="col-sm-3 profile-pic">
                                    <img id="avatar_info_patient">
                                    <div class="padding-10">
                                        <h4 class="font-md"><strong id="dossier_info_patient" style="color: black;"></strong>
                                            <br>
                                            <small>Dossier</small></h4>
                                        <br>
                                        <h4 class="font-md"><strong id="chambre_info_patient" style="color: black;"></strong>
                                            <br>
                                            <small>Chambre</small></h4>
                                    </div>
                                </div>
                                <div class="col-sm-6" style="margin-left: 5%;">
                                    <h1>
                                        <span id="prenom_info_patient" style="font-size: 18px;color: black;"></span>
                                        <span id="nom_info_patient" style="font-size: 22px;color: black;"></span>
                                        <br>
                                        <span id="sex_info_patient"></span>
                                        <span id="nationalite_info_patient"></span>
                                        <span id="motif_info_patient" style="font-size: 18px;"></span>
                                    </h1>

                                    <ul class="list-unstyled">
                                        <li>
                                            <p class="text-muted">
                                                <i class="fa fa-clock-o"></i>&nbsp;&nbsp;<span id="age_info_patient"></span>
                                            </p>
                                        </li>
                                        <li>
                                            <p class="text-muted">
                                                <i class="fa fa-user-md"></i>&nbsp;&nbsp;<span id="medecin_info_patient"></span>
                                            </p>
                                        </li>
                                        <li>
                                            <p class="text-muted">
                                                <i class="fa fa-calendar"></i>&nbsp;&nbsp; depuis le <span id="dateArr_info_patient"></span>
                                            </p>
                                        </li>
                                    </ul>
                                </div>       
                            </div>  
                        </div>                        
                    </div>

                    <span> Dernière actualisation: <div style=" display: initial; " id="time_liste"></div>
                        <button id="refresh_liste_patient" style="display: -webkit-box;" type="button" data-loading-text="<i class='fa fa-refresh fa-spin'></i> Chargement..." class="btn btn-xs btn-default pull-right">
                            <i class="fa fa-refresh"></i>
                        </button> </span>
                </div>
            </div>

            <div class="project-context hidden-xs" style="display: inline-flex;">
                <span id="calendrier" style=" font-size: 15px;margin-top: 3px;width: 100%;">
                    <ul class="demo-btns">
                        <li>
                            <a onclick="datePrevious()" id="leftDate" class="btn btn-primary" style="background-color: #A65858;border-color: #A65858;display: none"><i class="fa fa-chevron-left"></i></a>
                        </li>
                        <li style="margin-left: -3px; ">
                            <span><div id="selectDatesFeuillediv" style="display: inline;"></div></span>
                        </li>
                        <li>
                            <a onclick="dateNext()" id="rightDate" class="btn btn-primary" style="background-color: #A65858;border-color: #A65858;display: none"><i class="fa fa-chevron-right"></i></a>
                        </li>
                    </ul>
                </span>

                <span id="hour_planif" style=" font-size: 15px;margin-left: 15%; margin-top: 3px;display: none;">
                    <ul class="demo-btns" style="  width: 108%;">
                        <li>
                            <a onclick="hourNext()" id="leftHour" class="btn btn-primary" style="background-color: #A65858;border-color: #A65858;display: none"><i class="fa fa-chevron-left"></i></a>
                        </li>
                        <li style=" margin-left: 0px; ">
                            <span><div id="selectHourPlanifdiv" style="display: inline;"></div></span>
                        </li>
                        <li style=" margin-left: 1px; ">
                            <a onclick="hourPrevious()" id="rightHour" class="btn btn-primary" style="background-color: #A65858;border-color: #A65858;display: none"><i class="fa fa-chevron-right"></i></a>
                        </li>
                        <li style=" margin-left: 30%; ">
                        </li>
                    </ul>
                </span>
                
                <span id="curseur_pancarte" style="display: none;">
                    <a id="panLeft" class="panner btn btn-primary" data-scroll-modifier="-2" style="position: absolute;margin-left: -40%;border-color: #A65858;background-color: #A65858;"><i class="fa fa-chevron-left"></i></a>
                    <a id="panRight" class="panner btn btn-primary" data-scroll-modifier="2" style="position: absolute;margin-left: -30%;border-color: #A65858;background-color: #A65858;"><i class="fa fa-chevron-right"></i></a>
                </span>
                
                <span style="margin-top: 1%;width: 110%;margin-right: -11%;">
                    <!-- Consigne -->
                    <a href="javascript:void(0);" id="consigne_Click">
                        <div id="consigne" class="badge" style="background-color: #F96348; display: none;"></div> 
                    </a>

                    <!-- Allergie -->
                    <a href="javascript:void(0);" id="allergie_Click">
                        <div id="allergie" class="badge bg-color-blue txt-color-white" style="display: none;"></div> 
                    </a>

                    <!-- Antecedent -->
                    <a href="javascript:void(0);" id="antecedent_Click">
                        <div id="antecedent" class="badge bg-color-blue txt-color-white" style="display: none;"></div> 
                    </a>

                    <!-- BMR -->
                    <a href="javascript:void(0);" id="bmr_Click">
                        <div id="bmr" class="badge bg-color-blue txt-color-white" style="display: none;"></div> 
                    </a>
                </span>
            </div>
            
            <div class="pull-right" class="activity-dropdown">
                <div id="hide-menu" class="btn-header pull-right">
                    <span> <a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu"><i class="fa fa-reorder"></i></a> </span>
                </div>

                <ul id="mobile-profile-img" class="header-dropdown-list hidden-xs padding-5">
                    <li class="">
                        <a href="#" class="dropdown-toggle no-margin userdropdown" data-toggle="dropdown"> 
                            <img  src="../img/avatars/sunny.png" alt="John Doe" class="online" />  
                        </a>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a href="javascript:void(0);" class="padding-10 padding-top-0 padding-bottom-0"><i class="fa fa-cog"></i> Setting</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#ajax/profile.html" class="padding-10 padding-top-0 padding-bottom-0"> <i class="fa fa-user"></i> <u>P</u>rofile</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="javascript:void(0);" class="padding-10 padding-top-0 padding-bottom-0" data-action="toggleShortcut"><i class="fa fa-arrow-down"></i> <u>S</u>hortcut</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="javascript:void(0);" class="padding-10 padding-top-0 padding-bottom-0" data-action="launchFullscreen"><i class="fa fa-arrows-alt"></i> Full <u>S</u>creen</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="login.html" class="padding-10 padding-top-5 padding-bottom-5" data-action="userLogout"><i class="fa fa-sign-out fa-lg"></i> <strong><u>L</u>ogout</strong></a>
                            </li>
                        </ul>
                    </li>
                </ul>

                <div class="ajax-dropdown2">
                    <div style="  display: -webkit-box; ">
                        <div data-toggle="buttons">
                            <label class="btn btn-default">
                                <input type="radio" name="activity2" id="../body_page/liste_observations_dropdown.jsp?type=ALL">
                                Tous </label>
                            <label class="btn btn-default">
                                <input type="radio" name="activity2" id="../body_page/liste_observations_dropdown.jsp?type=AF">
                                Non exécutées </label>
                        </div>
                        <div data-toggle="buttons" style=" margin-left: 45px; ">
                            <button id="epingler" class="btn btn-default" style=" float: right;display: block; ">
                                <i class="fa fa-paperclip"></i>
                            </button>
                        </div>
                    </div>
                    <div class="ajax-notifications2 custom-scroll">
                        <div class="alert alert-transparent">
                            <div class="row">   
                            </div>  
                        </div>
                    </div>
                    <span> 
                        <div class="chat-footer">
                            <div class="textarea-div">
                                <div class="typearea">
                                    <textarea placeholder="Ecrire une observation ..." id="textarea-expand" class="custom-scroll"></textarea>
                                </div>
                            </div>
                            <span class="textarea-controls">
                                <button id="ajouter_observation_liste" class="btn btn-sm btn-primary pull-right">
                                    Valider
                                </button> 
                                <span class="pull-right smart-form" style="margin-top: 3px; margin-right: 10px;"> 
                                    <label class="checkbox pull-right">
                                        <input type="checkbox" name="subscription" id="checkbox_observation">
                                        <i></i> <strong> Important </strong>
                                    </label> 
                                </span>  
                            </span>
                        </div>
                    </span>
                </div>

                <!-- fullscreen button -->
                <div id="fullscreen" class="btn-header transparent pull-right">
                    <span> <a href="javascript:void(0);" data-action="launchFullscreen" title="Full Screen"><i class="fa fa-arrows-alt"></i></a> </span>
                </div>

                <!-- logout -->
                <div id="logout" class="btn-header transparent pull-right">
                    <span> <a href="../logout.jsp" data-action="logout" title="Déconnexion"><i class="fa fa-power-off"></i></a> </span>
                </div>

                <!-- planification -->
                <div id="planification_rea" class="btn-header transparent pull-right">
                    <span> <a href="#" data-action="liste" title="Planification"><i class="fa fa-pencil-square-o fa-lg"></i></a> </span>
                </div>

                <!-- observation -->
                <div id="observation_liste" class="btn-header transparent pull-right">
                    <span> <a href="#" data-action="liste" title="Liste d'observation"><i class="fa fa-user-md"></i><b id="badge_observation" class="badge  bounceIn animated">0</b></a> </span>
                </div> 
            </div>
            
            <div id="entete_patient" class="pull-right" style=" display: -webkit-inline-box;"></div>
        </header>

        <aside id="left-panel">
            <nav>
                <ul>
        
                    <li class="active"  style=" height: 40px;     cursor: pointer;">
                        <a href="#"  title="Liste des patients" id="_liste_patients">
                            <i class="fa fa-lg fa-fw fa-list"></i> 
                            <span class="menu-item-parent">Liste des patients</span>
                        </a>
                    </li>
                    <li class=""  style=" height: 40px;    cursor: pointer; ">
                        <a href="#"  title="Dossier Malade" id="_interface_acceuil">
                            <i class="fa fa-lg fa-fw fa-home"></i> 
                            <span class="menu-item-parent">Acceuil</span>
                        </a>
                    </li>
                    <li class=""  style=" height: 40px;     cursor: pointer;">
                        <a href="#"  title="Personal History" id="_PersonalHistory">
                            <i class="fa fa-lg fa-fw fa-file"></i> 
                            <span class="menu-item-parent">Personal History/Examination</span>
                        </a>
                    </li>
                    <li class=""  style=" height: 40px;     cursor: pointer;">
                        <a href="#"  title="Pancarte" id="_pancarte">
                            <i class="fa fa-line-chart"></i> 
                            <span class="menu-item-parent">Pancarte</span>
                        </a>
                    </li>
                    <li class="" onClick="" style=" height: 40px;     cursor: pointer;">
                        <a href="#" onClick="" title="Dossier Malade" id="_acceuil">
                            <i class="fa fa-lg fa-fw fa-folder-open"></i> 
                            <span class="menu-item-parent">Dossier Malade</span>
                        </a>
                    </li>

                    <li class="" onClick="" style=" height: 40px;    cursor: pointer; ">
                        <a href="#" onClick="" title="Labo" id="_demande_labo">
                            <i class="fa fa-lg fa-fw fa-flask"></i> 
                            <span class="menu-item-parent">Labo</span>
                        </a>
                    </li>
                    <li class="" onClick="" style=" height: 40px;    cursor: pointer; ">
                        <a href="#" onClick="" title="Radio" id="_demande_radio">
                            <i class="fa fa-lg fa-fw fa-video-camera"></i> 
                            <span class="menu-item-parent">Radio</span>
                        </a>
                    </li>
                    <li class="" onClick="" style=" height: 40px;     cursor: pointer;">
                        <a href="#" onClick="" title="Endo" id="_demande_endo">
                            <i class="fa fa-lg fa-fw fa-lastfm fa-rotate-90"></i> 
                            <span class="menu-item-parent">Endo</span>
                        </a>
                    </li>
                    <li class="" onClick="" style=" height: 40px;    cursor: pointer; ">
                        <a href="#" onClick="" title="Bloc" id="_demande_bloc">
                            <i class="fa fa-lg fa-fw fa-hospital-o"></i> 
                            <span class="menu-item-parent">Bloc</span>
                        </a>
                    </li>
                    <li class="" onClick="" style=" height: 40px;     cursor: pointer;">
                        <a href="#" onClick="" title="Prescription" id="_prescription">
                            <i class="fa fa-lg fa-fw fa-medkit"></i> 
                            <span class="menu-item-parent">Prescription</span>
                        </a>
                    </li>
                    <!-------------------------   Consigne    ---------------------------------->
                    <li class="" onClick="" style=" height: 40px;    cursor: pointer; ">
                        <a href="#" onClick="" title="Consigne" id="consigneMedInf">
                            <i class="fa fa-lg fa-fw fa-pencil"></i> 
                            <span class="menu-item-parent">Consigne</span>
                        </a>
                    </li>
                    <!--------------------- Observation Infirmier ----------------------------->
                    <li class="" style=" height: 40px;    cursor: pointer; ">
                        <a href="#" title="Observation Infirmier" id="_ObservationInfirmier">
                            <i class="fa fa-lg fa-fw fa-eye"></i> 
                            <span class="menu-item-parent">Observation Infirmier</span>
                        </a>
                    </li>

                    <!--------------------- Pharmacie ----------------------------->
                    <li class="" style=" height: 40px;    cursor: pointer; ">
                        <a href="#" title="Pharmacie" id="_pharmacie">
                            <span class="label label-success myLabelMenuStyle">PH</span>
                            <span class="menu-item-parent">Pharmacie</span>
                        </a>
                    </li
                    <!--------------------- Anesthésie ----------------------------->
                    <li class="" style=" height: 40px;     cursor: pointer;">
                        <a href="#" title="Liste Pré-anesthésie & Postopératoire" id="_liste_anesthesie">
                            <img src="../img/anesthesia.png" style="margin-top: -10px;" />
                            <span class="menu-item-parent">Liste Pré & Post</span>
                        </a>
                    </li>
                    <!--------------------- Historique ----------------------------->
                    <li class="" style=" height: 40px;     cursor: pointer;">
                        <a href="#" title="Hostorique FR" id="_Histo_liste">
                            <span class="label label-primary myLabelMenuStyle">HST</span>
                            <span class="menu-item-parent">Historique</span>
                        </a>
                    </li>
                    <!--------------------- galerie photo    ------------------------->
                    <li class="" style=" height: 40px;     cursor: pointer;">
                        <a href="#" title="Galerie Photo" id="_photo">
                            <i class="fa fa-lg fa-fw fa fa-camera"></i> 
                            <span class="menu-item-parent">Galerie Photo</span>
                        </a>
                    </li>
                    <!----------------- Prestations et Extras ------------------------>
                    <li class="" style=" height: 40px;     cursor: pointer;">
                        <a href="#" title="Prestations et Extras" id="_extra">
                            <i class="fa fa-lg fa-usd"></i> 
                            <span class="menu-item-parent">Prestations et Extras</span>
                        </a>
                    </li>
                    <!--------------------- Traçabilité ----------------------------->
                    <li class="" style=" height: 40px;     cursor: pointer;">
                        <a href="#" title="Traçabilité" id="_trace">
                            <i class="fa fa-lg fa-terminal"></i> 
                            <span class="menu-item-parent">Traçabilité</span>
                        </a>
                    </li>
                    <!--------------------- Paramètrage ----------------------------->
                    <li class="" style=" height: 40px;     cursor: pointer;">
                        <a href="#" title="Paramètrage" id="_param">
                            <i class="fa fa-lg fa-fw fa-cog"></i> 
                            <span class="menu-item-parent">Paramètrage</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <span class="minifyme" data-action="minifyMenu"> 
                <i class="fa fa-arrow-circle-left hit"></i> 
            </span>

        </aside>
        <!-- END NAVIGATION -->

        <!-- #MAIN PANEL -->
        <div id="main" role="main">

            <!-- RIBBON -->
            <div id="ribbon">
                <div style="display: inline;">
                    <span class="ribbon-button-alignment"> 
                        <span id="_refresh" class="btn btn-ribbon"rel="tooltip" data-placement="bottom" data-original-title="Actualiser" ><i class="fa fa-refresh"></i></span> 
                    </span>
                    <strong>
                        <ol class="breadcrumb"></ol>
                    </strong>
                </div>
            </div>
            <div id="content"></div>
        </div>

        <div id="shortcut">
            <ul>
                <li>
                    <a href="#ajax/inbox.html" class="jarvismetro-tile big-cubes bg-color-blue"> <span class="iconbox"> <i class="fa fa-envelope fa-4x"></i> <span>Mail <span class="label pull-right bg-color-darken">14</span></span> </span> </a>
                </li>
                <li>
                    <a href="#ajax/calendar.html" class="jarvismetro-tile big-cubes bg-color-orangeDark"> <span class="iconbox"> <i class="fa fa-calendar fa-4x"></i> <span>Calendar</span> </span> </a>
                </li>
                <li>
                    <a href="#ajax/gmap-xml.html" class="jarvismetro-tile big-cubes bg-color-purple"> <span class="iconbox"> <i class="fa fa-map-marker fa-4x"></i> <span>Maps</span> </span> </a>
                </li>
                <li>
                    <a href="#ajax/invoice.html" class="jarvismetro-tile big-cubes bg-color-blueDark"> <span class="iconbox"> <i class="fa fa-book fa-4x"></i> <span>Invoice <span class="label pull-right bg-color-darken">99</span></span> </span> </a>
                </li>
                <li>
                    <a href="#ajax/gallery.html" class="jarvismetro-tile big-cubes bg-color-greenLight"> <span class="iconbox"> <i class="fa fa-picture-o fa-4x"></i> <span>Gallery </span> </span> </a>
                </li>
                <li>
                    <a href="#ajax/profile.html" class="jarvismetro-tile big-cubes selected bg-color-pinkDark"> <span class="iconbox"> <i class="fa fa-user fa-4x"></i> <span>My Profile </span> </span> </a>
                </li>
            </ul>
        </div>

        <div class="print" style="display: none;">Veuillez utiliser le bouton d'impression !</div>

     
        <jsp:include page="../body_page/js_declare.jsp"/>

        <script src="../js/plugin/other-plugin/mymenu.js"></script>
        <script src="../js/plugin/other-plugin/summernote.min.js"></script>

        <script>

                                var video;
                                var dataURL;
                                var localStream;

                                function setup() {
                                    navigator.myGetMedia = (navigator.getUserMedia ||
                                            navigator.webkitGetUserMedia ||
                                            navigator.mozGetUserMedia ||
                                            navigator.msGetUserMedia);
                                    navigator.myGetMedia({video: true}, connect, error);
                                }

                                function connect(stream) {
                                    video = document.getElementById("video");
                                    video.src = window.URL ? window.URL.createObjectURL(stream) : stream;
                                    video.play();
                                    localStream = stream;
                                }

                                function error(e) {
                                    console.log(e);
                                }

                                function captureImage() {
                                    var canvas = document.createElement('canvas');
                                    canvas.id = 'hiddenCanvas';
                                    //add canvas to the body element
                                    document.body.appendChild(canvas);
                                    //add canvas to #canvasHolder
                                    document.getElementById('canvasHolder').appendChild(canvas);
                                    var ctx = canvas.getContext('2d');
                                    canvas.width = video.videoWidth / 4;
                                    canvas.height = video.videoHeight / 4;
                                    ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
                                    //save canvas image as data url
                                    dataURL = canvas.toDataURL();
                                    //set preview image src to dataURL
                                    document.getElementById('preview').src = dataURL;
                                    // place the image value in the text box
                                    document.getElementById('imageToForm').value = dataURL;
                                    $("#canvasHolder").hide();
                                }

                                // Camera 
                                function uploadImagePatient()
                                {
                                    $("#modal_image_patient").modal('show');
                                    setup();
                                }

                                $('#_valider_image_patient').bind("click", function () {
                                    captureImage();
                                    var image = $("#imageToForm").val();
                                    var numdoss = $("[class~='_num_dossier_patient']").text().split(":")[1].replace(" ", "");
                                    var res = updateImagePatient(numdoss, image);
                                    if (res.indexOf("true") > -1)
                                    {
                                        showNotification("Succés", "Changement d'image effectué avec succés !", "success", 2000);
                                        setTimeout(function () {
                                            location.reload();
                                        }, 2500);
                                    } else
                                        showNotification("Echec !!", "Changement d'image non effectué !", "error", 0);

                                    localStream.stop();
                                    video.src = "";
                                    document.getElementById('preview').src = "";
                                });

                                $('#modal_image_patient').on('hidden.bs.modal', function () {
                                    localStream.stop();
                                    video.src = "";
                                    document.getElementById('preview').src = "";
                                });
        </script>
    </body>
</html>