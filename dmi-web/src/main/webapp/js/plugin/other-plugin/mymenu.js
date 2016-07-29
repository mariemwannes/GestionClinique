$(function() {
  /*  var arraySeesion = getSession("Acceuil");
    if (arraySeesion.length !== 0) 
    {
        userCreat = arraySeesion[3];
        groupe = arraySeesion[4];
    }
    */
    var version = localStorage.getItem("version");
    
    $(window).on('beforeunload', function(){
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
        {
            var Conf = confirm("Toutes vos modifications vont être supprimées ! Voulez vous enregistrer avant de quitter ?");
            return "Toutes vos modifications vont être supprimées ! Pour enregistrer veuillez cliquer sur 'Rester sur cette page'.";
        }
    });
    
    $(window).on('unload', function(){
        localStorage.setItem("modifAnesthesie","false");
    });
    
    function valider_feuille_anesthesie(element)
    {        
        bootbox.dialog({
          message: "Toutes vos modifications vont être supprimées ! <br>Voulez vous enregistrer avant de quitter ?",
          title: "Confirmation",
          buttons: {
            success: {
              label: "Enregistrer",
              className: "btn-success",
              callback: function() {
                localStorage.setItem("redirection",element.id);    
                window.parent.$('#btnReservation').trigger('click');
                bootbox.hideAll();
              }
            },
            danger: {
              label: "Ne pas enregistrer",
              className: "btn-danger",
              callback: function() {
                localStorage.setItem("modifAnesthesie",false);
                showNotification("", "Feuille non enregistrée !", "error", 3000);
                window.parent.$("#"+element.id).trigger("click");
                bootbox.hideAll();
              }
            },
            main: {
              label: "Annuler",
              className: "btn-info",
              callback: function() {
                bootbox.hideAll();
              }
            }
          }
        });
    }
    
    function getDateFeuille()
    {
        var reponse;
        $.ajax({
            url: "../Rea_Consult?function=getDateFeuille",
            type: 'POST',
            async: false,
            dataType: "json",
            error: function(jqXHR, textStatus, errorThrown)
            {
            },
            success: function(data, textStatus, jqXHR)
            {
                reponse = data;
            }
        });

        return reponse;
    }

    window.name = "MainWindow";
    windowsize = $(window).width();
    var params = [
        'height='+parseInt(screen.height-105),
        'width='+parseInt(screen.width-50),
        'top=105',
        'left=50',
        'scrollbars=yes',
        'fullscreen=yes' // only works in IE, but here for completeness
    ].join(','); 
    
    var top_ribbon = $("#ribbon").offset().top;
    var left_ribbon = $("#ribbon").offset().left;
    
    $("#_PersonalHistory").unbind("click");
    $("#_PersonalHistory").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            $("nav li").removeClass("active");
            $(this).parents("li").addClass("active");
            $(".breadcrumb").html($(this).attr("title"));
            parent.$('#ribbon').css("display", "none");

            $("#content").empty();
            $("#content").append('<iframe id="iframe" src="../body_page/PersonalHistory.jsp" width="100%" height="1500" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

            window.parent.$("#hour_planif").hide();
            window.parent.$("#leftHour").hide();
            window.parent.$("#rightHour").hide();

            window.parent.$(".footerParent").hide();
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_pancarte").unbind("click");
    $("#_pancarte").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            $("nav li").removeClass("active");
            $(this).parents("li").addClass("active");
            $(".breadcrumb").html($(this).attr("title"));
            parent.$('#ribbon').css("display", "none");

            $("#content").empty();
            $("#content").append('<iframe id="iframe" src="../body_page/pancarte.jsp" width="100%" height="1500" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');
            
            window.parent.$("#calendrier").css("visibility","hidden");
            window.parent.$("#hour_planif").hide();
            window.parent.$("#leftHour").hide();
            window.parent.$("#rightHour").hide();

            window.parent.$(".footerParent").hide();
        }
    });
    
    $("#planification_rea").unbind("click");
    $("#planification_rea").bind("click", function(e) 
    {
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(groupe !== "Infirmier" && version === "N")
            {
                var patient = JSON.parse(localStorage.getItem("Patient"));
                if(patient !== null)
                {
                    $(".breadcrumb").html("Planification");
                    parent.$('#ribbon').css("display", "none");
                    $("#content").empty();
                    $("#content").append('<iframe name="planification_frame" id="iframe" src="../body_page/planification_rea.jsp" width="100%" height="1500" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');
                }

                window.parent.$(".footerParent").hide(); 
            }
            else
            {
                showNotification("", "Vous n'êtes pas autorisé à saisir des réalisations depuis la version Desktop ! Veuillez utiliser la tablette.", "error", 4000);
                return false;
            }
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#saisie_med").unbind("click");
    $("#saisie_med").bind("click", function(e) 
    {
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            var patient = JSON.parse(localStorage.getItem("Patient"));
            if(patient !== null)
            {
                $(".breadcrumb").html("Saisie");
                parent.$('#ribbon').css("display", "none");
                $("#content").empty();
                $("#content").append('<iframe name="planification_frame" id="iframe" src="../body_page/saisie_test.jsp" width="100%" height="1500" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');
            }

            window.parent.$(".footerParent").hide();
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    $("#saisie_med1").unbind("click");
    $("#saisie_med1").bind("click", function(e) 
    {
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            var patient = JSON.parse(localStorage.getItem("Patient"));
            if(patient !== null)
            {
                $(".breadcrumb").html("Saisie");
                parent.$('#ribbon').css("display", "none");
                $("#content").empty();
                $("#content").append('<iframe name="planification_frame" id="iframe" src="../body_page/saisie_test.jsp" width="100%" height="1500" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');
            }

            window.parent.$(".footerParent").hide();
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    //  Observation Infirmier
    $("#_ObservationInfirmier").unbind("click");
    $("#_ObservationInfirmier").bind("click", function(e)
    {   
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/ObservationInfirmier.jsp" width="100%" height="3500" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/ObservationInfirmier.jsp', 'pop', params);
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            }  
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#consigneMedInf").unbind("click");
    $("#consigneMedInf").bind("click", function(e)
    {
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/ConsigneMedInf.jsp" width="100%" height="3500" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/ConsigneMedInf.jsp', 'pop', params);
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            }  
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_acceuil").unbind("click");
    $("#_acceuil").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            $("nav li").removeClass("active");
            $(this).parents("li").addClass("active");
            $(".breadcrumb").html($(this).attr("title"));
            $("#content").empty();
            $("#content").append('<iframe id="iframe_cloturee" src="../body_page/acceuil.jsp" width="100%" height="1000" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

            window.parent.$(".footerParent").hide();
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_photo").unbind("click");
    $("#_photo").bind("click", function(e) 
    {
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            $("nav li").removeClass("active");
            $(this).parents("li").addClass("active");
            $(".breadcrumb").html($(this).attr("title"));
            parent.$('#ribbon').css("display", "none");        
            $("#content").empty();
            $("#content").append('<iframe id="iframe" src="../body_page/galeriePhoto.jsp" width="100%" height="1500" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>'); 

            window.parent.$(".footerParent").hide();
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_interface_acceuil").unbind("click");
    $("#_interface_acceuil").bind("click", function(e) {

        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            $("nav li").removeClass("active");
            $(this).parents("li").addClass("active");
            $(".breadcrumb").html($(this).attr("title"));
            $("#content").empty();
            $("#content").append('<iframe id="iframe_cloturee" src="../body_page/interface_acceuil.jsp" width="100%" height="3500" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

            window.parent.$(".footerParent").hide();
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_demande_labo").unbind("click");
    $("#_demande_labo").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/demande_labo.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/demande_labo.jsp', 'pop', params);
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            } 
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_demande_radio").unbind("click");
    $("#_demande_radio").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/demande_radio.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/demande_radio.jsp', 'pop', params); 
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            }
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });

    $("#_demande_endo").unbind("click");
    $("#_demande_endo").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/demande_endoscopie.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/demande_endoscopie.jsp', 'pop', params); 
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            }
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });

    $("#_demande_bloc").unbind("click");
    $("#_demande_bloc").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/demande_bloc.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/demande_bloc.jsp', 'pop', params);
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            } 
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });

    $("#_prescription").unbind("click");
    $("#_prescription").bind("click", function(e) {

        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/prescription.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/prescription.jsp', 'pop', params);
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            } 
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });

    $("#_evenement").unbind("click");
    $("#_evenement").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/evenement.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/evenement.jsp', 'pop', params);
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            } 
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });

    $("#_pharmacie").unbind("click");
    $("#_pharmacie").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/pharmacie.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/pharmacie.jsp', 'pop', params);
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            } 
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_Histo_feuil_rea").unbind("click");
    $("#_Histo_feuil_rea").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/rea_cloturee.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/rea_cloturee.jsp', 'pop', params);
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            }
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_Histo_dossier_malade").unbind("click");
    $("#_Histo_dossier_malade").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/dossier_malade_cloturee.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/dossier_malade_cloturee.jsp', 'pop', params);
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            }
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_Histo_liste").unbind("click");
    $("#_Histo_liste").bind("click", function(e) 
    {   
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            //Effacement patient
            var patient = JSON.parse(localStorage.getItem("Patient"));
            if(patient !== null)
            {
                $("._nom_patient").text("");
                $("._avatar_patient").attr("src","../img/avatars/male.png");
                $("#_nom_medecin").text("");
                $("#_medecin_infirmier").text("");
                $("._num_cha_patient").html("");
                $("._num_dossier_patient").html("");
                $("._age_patient").text("");
                $("#entete_patient").html("");
                $("#consigne").html("").hide();
                $("#allergie").html("").hide();
                $("#antecedent").html("").hide();
                $("#bmr").html("").hide();
                $("#badge_observation").hide();
                $("#badge_observation").text(0);
                $("#badge_observation").css('background','#0091d9');
                $("#bmr_Click").hide();

                localStorage.removeItem("Patient");
                localStorage.removeItem("Medecin");
                localStorage.removeItem("natureetage");
                localStorage.removeItem("Liste_observations");
                var dateFeuil = getDateFeuille();

                addSession("Patients",null,null,dateFeuil,userCreat,groupe);
            }
                
            $("nav li").removeClass("active");
            $(this).parents("li").addClass("active");
            $(".breadcrumb").html($(this).attr("title"));
            $("#content").empty();
            $("#content").append('<iframe id="iframe_cloturee" src="../body_page/liste_patients_historique.jsp" width="100%" height="3000" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

            window.parent.$(".footerParent").hide();
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_liste_patients").unbind("click");
    $("#_liste_patients").bind("click", function(e) 
    {   
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            var modifAnesthesie = localStorage.getItem("modifAnesthesie");
            if(modifAnesthesie === "true")
                valider_feuille_anesthesie(this);
            else
            {
                //Effacement patient
                var patient = JSON.parse(localStorage.getItem("Patient"));
                if(patient !== null)
                {
                    $("._nom_patient").text("");
                    $("._avatar_patient").attr("src","../img/avatars/male.png");
                    $("#_nom_medecin").text("");
                    $("#_medecin_infirmier").text("");
                    $("._num_cha_patient").html("");
                    $("._num_dossier_patient").html("");
                    $("._age_patient").text("");
                    $("#entete_patient").html("");
                    $("#consigne").html("").hide();
                    $("#allergie").html("").hide();
                    $("#antecedent").html("").hide();
                    $("#bmr").html("").hide();
                    $("#badge_observation").hide();
                    $("#badge_observation").text(0);
                    $("#badge_observation").css('background','#0091d9');
                    $("#bmr_Click").hide();
                    
                    localStorage.removeItem("Patient");
                    localStorage.removeItem("Medecin");
                    localStorage.removeItem("natureetage");
                    localStorage.removeItem("Liste_observations");
                    var dateFeuil = getDateFeuille();
                    
                    addSession("Patients",null,null,dateFeuil,userCreat,groupe);
                }
                
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/ListeDesPatients.jsp" width="100%" height="3000" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
                window.parent.$("#curseur_pancarte").hide();
            }
        }
    });
    
    $("#_param").unbind("click");
    $("#_param").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/parametrage.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                var w = window.open('../body_page/parametrage.jsp', 'pop', params);
                setTimeout(function() {
                    w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                    w.moveTo(50,170);
                }, 10);
            } 
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_trace").unbind("click");
    $("#_trace").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            $("nav li").removeClass("active");
            $(this).parents("li").addClass("active");
            $(".breadcrumb").html($(this).attr("title"));
            $("#content").empty();
            $("#content").append('<iframe id="iframe_cloturee" src="../body_page/Tracabilite.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

            window.parent.$(".footerParent").hide();
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_extra").unbind("click");
    $("#_extra").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            $("nav li").removeClass("active");
            $(this).parents("li").addClass("active");
            $(".breadcrumb").html($(this).attr("title"));
            $("#content").empty();
            $("#content").append('<iframe id="iframe_cloturee" src="../body_page/Prestations_Extras.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

            window.parent.$(".footerParent").hide();
            window.parent.$("#curseur_pancarte").hide();
        }
    });
    
    $("#_liste_anesthesie").unbind("click");
    $("#_liste_anesthesie").bind("click", function(e) 
    {   
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            if(windowsize < 750)
            {
                $("nav li").removeClass("active");
                $(this).parents("li").addClass("active");
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/liste_feuille_preAnesthesie.jsp" width="100%" height="3000" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                window.parent.$(".footerParent").hide();
            }
            else
            {
                if(localStorage.getItem("click_anesthesie") === "oui")
                {
                    localStorage.setItem("click_anesthesie","non");
                    var w = window.open('../body_page/liste_feuille_preAnesthesie.jsp', 'pop', params);
                    setTimeout(function() {
                        w.resizeTo(parseInt(screen.width-1.5*left_ribbon),parseInt(screen.height-3.25*top_ribbon));
                        w.moveTo(50,170);
                    }, 10);
                }
                else
                {
                    $("nav li").removeClass("active");
                    $(this).parents("li").addClass("active");
                    $(".breadcrumb").html($(this).attr("title"));
                    $("#content").empty();
                    $("#content").append('<iframe id="iframe_cloturee" src="../body_page/liste_feuille_preAnesthesie.jsp" width="100%" height="3000" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');

                    window.parent.$(".footerParent").hide();
                }
            }   
            
            window.parent.$("#curseur_pancarte").hide();
        }
    });

    //GetCodeParamètre Etat Neurologique
    localStorage.setItem("CSG",findByCode("codeCSG"));
    localStorage.setItem("PD",findByCode("codePupilleDroite"));
    localStorage.setItem("PG",findByCode("codePupilleGauche"));
    localStorage.setItem("RC",findByCode("codeReflexeCorneen"));
    localStorage.setItem("RT",findByCode("codeReflexeToux"));
    
    localStorage.setItem("NomCli",findByCode("NomCli"));
    
    var access = localStorage.getItem("access");
    var chambre = localStorage.getItem("chambre");
    if(access !== null) 
    {
        if(access.indexOf('chambre') > -1)
        {
            var client = getClientByChambre("Acceuil",chambre);
            if(client !== null)
            {
                var numDoss = client.numDoss;
                var codeMed = client.medTrait.codMed;
                var user = userCreat;
                var dateFeuil = getDateFeuille();
                addSession("Patients",numDoss,codeMed,dateFeuil,user,groupe);
                localStorage.setItem('Patient',JSON.stringify(client));
                
                var depart = parseInt(getHeureDepart("ConsultFeuilleRea"));
                localStorage.setItem("HeureDepart",depart);
                
                if (client.numCha.etage.nature.toUpperCase() === "REA") 
                {
                    localStorage.setItem("natureetage", "rea");
                }
                else
                {
                    localStorage.setItem("natureetage", "sur");
                }
            }         

            $("#_liste_patients").hide();
            $("#_Histo_feuil_rea").hide();
            
            $("#_PersonalHistory").click();
        }
        else
        {
            $("#_liste_patients").show();
            $("#consigneMedInf").show();
            $("#_ObservationInfirmier").show();
            $("#_Histo_feuil_rea").show();
            
            $("#_liste_patients").trigger("click");
        }  
    }
    else
    {
        $("#_liste_patients").show();
        $("#consigneMedInf").show();
        $("#_ObservationInfirmier").show();
        $("#_Histo_feuil_rea").show();

        $("#_liste_patients").trigger("click");
    }   
});