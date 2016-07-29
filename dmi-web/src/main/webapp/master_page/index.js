$(function() {
    var session_activite = {
        //Logout Settings
        inactiveTimeout: 900000,     //(ms) The time until we display a warning message
        warningTimeout: 10000,      //(ms) The time until we log them out
        minWarning: 5000,           //(ms) If they come back to page (on mobile), The minumum amount, before we just log them out
        warningStart: null,         //Date time the warning was started
        warningTimer: null,         //Timer running every second to countdown to logout
        logout: function () {       //Logout function once warningTimeout has expired
            window.parent.$("#mdlLoggedOut").modal("show");
            setTimeout(function() {
                window.parent.location = "../logout_function.jsp";
             }, 2000);
        },

        //Keepalive Settings
        keepaliveTimer: null,
        keepaliveUrl: "",
        keepaliveInterval: 5000,     //(ms) the interval to call said url
        keepAlive: function () {
            $.ajax({ url: session_activite.keepaliveUrl });
        }
    };

    window.parent.$(document).on("idle.idleTimer", function (event, elem, obj) {
        //Get time when user was last active
        var diff = (+new Date()) - obj.lastActive - obj.timeout,
            warning = (+new Date()) - diff;

        //On mobile js is paused, so see if this was triggered while we were sleeping
        if (diff >= session_activite.warningTimeout || warning <= session_activite.minWarning) {
            window.parent.$("#mdlLoggedOut").modal("show");
        } else {
            //Show dialog, and note the time
            window.parent.$('#sessionSecondsRemaining').html(Math.round((session_activite.warningTimeout - diff) / 1000));
            window.parent.$("#myModal").modal("show");
            session_activite.warningStart = (+new Date()) - diff;

            //Update counter downer every second
            session_activite.warningTimer = setInterval(function () {
                var remaining = Math.round((session_activite.warningTimeout / 1000) - (((+new Date()) - session_activite.warningStart) / 1000));
                if (remaining >= 0) {
                    window.parent.$('#sessionSecondsRemaining').html(remaining);
                } else {
                    session_activite.logout();
                }
            }, 1000);
        }
    });

    // create a timer to keep server session alive, independent of idle timer
    session_activite.keepaliveTimer = setInterval(function () {
        session_activite.keepAlive();
    }, session_activite.keepaliveInterval);

    //User clicked ok to extend session
    window.parent.$("#extendSession").click(function () {
        clearTimeout(session_activite.warningTimer);
    });
    //User clicked logout
    window.parent.$("#logoutSession").click(function () {
        session_activite.logout();
    });

    //Set up the timer, if inactive for 10 seconds log them out
    window.parent.$(document).idleTimer(session_activite.inactiveTimeout);
            
    if(('ontouchstart' in window) === true)
    {
        localStorage.setItem("event_string",'tap'); 
    }
    else
        localStorage.setItem("event_string",'dblclick');
    
    var url_acceuil = "Acceuil";
    var urlConsult = "ConsultFeuilleRea";
    var urlUpdate = "UpdateFeuilleRea";
    var height = screen.height;

    var version = localStorage.getItem("version");
    if(version === "N")
        $('#_demande_endo').parent().hide();
    
    $(".ajax-dropdown2").css('height',parseInt(height)-200);
    $(".ajax-notifications2").css('height',parseInt(height)-400);
    
    var dock = localStorage.getItem("dock_Observation");
    if(dock === null)
        localStorage.setItem("dock_Observation",false);
    
    if(dock === "false")
      $("#epingler").removeClass('active');
    else if(dock === "true")
      $("#epingler").addClass('active');
        
    $(window).resize(function() 
    {
        checkWidth();
    });
    
    $("#_close_window").unbind("click");
    $("#_close_window").bind("click", function() {
        window.close();
    });
    
    $("#_refresh").unbind("click");
    $("#_refresh").bind("click", function() {
        $("nav").find("li[class='active']").find("a").trigger("click");
    });
    
    $("#logo-group .click_info").unbind("click");
    $("#logo-group .click_info").bind("click", function() {        
        if($("[name='_info_pat_rea'][class='_num_dossier_patient click_info']").text().split(":")[1] !== undefined)
        {
            //Initialisation
            $("#avatar_info_patient").attr("src", '');
            $("#dossier_info_patient").text("");
            $("#chambre_info_patient").text("");
            $("#prenom_info_patient").text("");
            $("#nom_info_patient").text("");
            $("#sex_info_patient").html("");
            $("#nationalite_info_patient").html("");
            $("#motif_info_patient").text("");
            $("#medecin_info_patient").text("");
            $("#dateArr_info_patient").text("");
            $("#age_info_patient").text("");
                
                
            //Remplissage
            var numdoss = $("[name='_info_pat_rea'][class='_num_dossier_patient click_info']").text().split(":")[1].replace(" ","");
            
            var patient = JSON.parse(localStorage.getItem("Patient"));
            
            if(patient.photo.length > 0)
                $("#avatar_info_patient").attr("src", patient.photoSRC);
            else if (patient.photo.length === 0)
            {
                if(patient.sex === true) //M
                    $("#avatar_info_patient").attr("src", '../img/avatars/male.png');
                else if(patient.sex === false) //F
                    $("#avatar_info_patient").attr("src", '../img/avatars/female.png');
            }  
            
            $("#dossier_info_patient").text(numdoss);
            $("#chambre_info_patient").text(patient.numCha.numCha);
            $("#prenom_info_patient").text(patient.nomCli);
            $("#nom_info_patient").text(patient.prenom);
            
            if(patient.sex === true) //M
                $("#sex_info_patient").html("<img src='../img/avatars/imageM.jpg' width='24px' />");
            else if(patient.sex === false) //F
                $("#sex_info_patient").html("<img src='../img/avatars/imageF.jpg' width='24px' />");
            
            if(patient.nation.codNat === "LBY") 
                $("#nationalite_info_patient").html("<img class='flag flag-ly' src='../img/avatars/blank.gif'>");
            else if(patient.nation.codNat === "TUN") 
                $("#nationalite_info_patient").html("<img class='flag flag-tn' src='../img/avatars/blank.gif'>");

            if(patient.motif !== null && patient.motif !== undefined)    
                $("#motif_info_patient").text(patient.motif.des);
            
            $("#medecin_info_patient").text(patient.medTrait.nomMed);
            
            if(patient.dateArr instanceof Object)
            {
                $("#dateArr_info_patient").text(dateFormatter(patient.dateArr));
            }
            
            if(patient.datNai instanceof Object)
            {
                $("#age_info_patient").text(getDiffDayDate("", patient.datNai, true)+" ans");
            }
            
            if($(".ajax-dropdown").css("display") === "none")
            {    
                $(".ajax-dropdown").show();
                $(".ajax-dropdown > div").eq(0).find('label').eq(0).find('input').trigger('click');
            }
            else if($(".ajax-dropdown").css("display") === "block")
            {    
                $(".ajax-dropdown").hide();
                $(".ajax-dropdown > div").eq(0).find('label').removeClass('active');
            }
            
            //Remplissage entete
            var codeHTML_entete = "";
            if(patient.hasLabos === true)
                codeHTML_entete += '<div id="labo_patient_entete" style="text-align: center;padding-left: 10px;margin-top: 5px;"><img src="../img/avatars/labo.png" width="36px" style="margin:0px auto;display:block;"></div>';

            if(patient.hasRadios === true)
                codeHTML_entete += '<div id="radio_patient_entete" style="text-align: center;padding-left: 10px;margin-top: 5px;"><img src="../img/avatars/radio.png" width="36px" style="margin:0px auto;display:block;"></div>';

            var codeHTML_Anesthesie = "<div id='Anesthesie_patient_entete' style='padding-left: 20px;'>";
            if(patient.hasPreAnesthesie === true)
                codeHTML_Anesthesie += "<div class='row'><span style='height: 20px;' class='label label-primary myLabelMenuStyle'>Pré&nbsp;&nbsp;</span></div>";
            else
                codeHTML_Anesthesie += "<div class='row'><span style='height: 20px;visibility: hidden;' class='label label-primary myLabelMenuStyle'>Pré&nbsp;&nbsp;</span></div>";

            if(patient.hasPostOpe === true)
                codeHTML_Anesthesie += "<div class='row' style='margin-top: 5px;'><span style='height: 20px;' class='label label-primary myLabelMenuStyle'>Post</span></div>";
            else
                codeHTML_Anesthesie += "<div class='row' style='margin-top: 5px;'><span style='height: 20px;visibility: hidden;' class='label label-primary myLabelMenuStyle'>Post</span></div>";

            codeHTML_entete += codeHTML_Anesthesie+"</div>";

            window.parent.$("#entete_patient").html(codeHTML_entete);
            
            bind_entete_patient();
        } 
    }); 
    
    $("#observation_liste").unbind("click");
    $("#observation_liste").bind("click", function() {
        var patient = JSON.parse(localStorage.getItem("Patient"));
        if(patient !== null)
        {
            if($(".ajax-dropdown2").css("display") === "none")
            {    
                $(".ajax-dropdown2").show();
                $(".ajax-dropdown2 > div").eq(0).find('label').eq(0).find('input').trigger('click');
            }
            else if($(".ajax-dropdown2").css("display") === "block")
            {    
                $(".ajax-dropdown2").hide();
                $(".ajax-dropdown2 > div").eq(0).find('label').removeClass('active');
                $("iframe").css('width','100%');
            }   
        }
    });
    
    $("#epingler").unbind("click");
    $("#epingler").bind("click", function() {

        if( $("#epingler").hasClass("active") === true )
        {
            localStorage.setItem("dock_Observation",false);
        }
        else if( $("#epingler").hasClass("active") === false )
        {
            localStorage.setItem("dock_Observation",true);
        }
        
        setTimeout(function() {
           $("#content > iframe").focus();
        }, 100);
    });
    
    bind_entete_patient();
});

function bind_entete_patient()
{
    window.parent.$("#labo_patient_entete").unbind();
    window.parent.$("#labo_patient_entete").bind("click", function() {
        localStorage.setItem("Consultation_Labo","oui");
        window.parent.$("#_demande_labo").trigger("click"); 
    });

    window.parent.$("#radio_patient_entete").unbind();
    window.parent.$("#radio_patient_entete").bind("click", function() {
        localStorage.setItem("Consultation_Radio","oui");
        window.parent.$("#_demande_radio").trigger("click"); 
    });

    window.parent.$("#Anesthesie_patient_entete").unbind();
    window.parent.$("#Anesthesie_patient_entete").bind("click", function() {
        localStorage.setItem("click_anesthesie","oui");
        window.parent.$("#_liste_anesthesie").trigger("click"); 
    });
    
    window.parent.$(".ajax-dropdown2").hide();
}

function checkWidth()
{
    windowsize = $(window).width();

    if (windowsize < 640) 
    {
        $("._age_patient").hide();
        $("._num_dossier_patient").hide(); 
        $("#_medecin_infirmier").hide(); 
        $("#_nom_medecin").hide(); 
        $("#entete_patient").hide(); 
        $("#hide-menu").parent().css("width","40%");
    }
    else if (windowsize > 640 && windowsize < 1024) 
    {
        $("._age_patient").show();
        $("._num_dossier_patient").show(); 
        $("#_medecin_infirmier").show(); 
        $("#_nom_medecin").show();
        $("#hide-menu").parent().css("width","20%");
        
        if(windowsize <= 900)
        {
            $("#logo-group").css('width','220px');
            $("#consigne_Click").parent().hide();
            $("#entete_patient").css("width","16%");
            $(".project-context").css("width","35%");
        }
//        else if(windowsize > 900)
//        {
//            $("#consigne_Click").parent().css('margin-left','10%');
//            $("#calendrier").parent().css('width','50%');
//            $("#logo-group").css('width','300px');
//            $("#consigne_Click").parent().show();    
//            
//            if($("#calendrier").css('display') === "block")
//            {
//                $("#logo-group").css('width','330px');
//                $("#consigne_Click").parent().hide();
//            }
//            else
//            {
//                $("#consigne_Click").parent().show();
//            }
//        }
    }  
    else if (windowsize >= 1024) 
    {
        $("#logo-group").css('width','25');
        $("#calendrier").parent().css('width','50%');
        $("#calendrier").parent().css('margin-left','15%');
        $("#calendrier").css('margin-left','21%');
        $("#entete_patient").css("width","13%");
        
        $("._age_patient").show();
        $("._num_dossier_patient").show(); 
        $("#_medecin_infirmier").show(); 
        $("#_nom_medecin").show(); 
        $("#hide-menu").parent().css("width","15%");
        
        $("#consigne_Click").parent().show();
    }  
}

function getPatientByNumDoss(url_acceuil, numdoss) {
    var listRep;
    $.ajax({
        url: "../" + url_acceuil + "?type=consult&function=getPatientByNumDoss&numDoss=" + numdoss,
        type: 'POST',
        async: false,
        error: function(jqXHR, textStatus, errorThrown) {
        },
        complete: function(jqXHR, textStatus) {
        },
        success: function(data, textStatus, jqXHR) {
            listRep = eval('(' + data + ')');
        }
    });
    return listRep;
}

function getHeureDepart(url) 
{
    var listRep;
    $.ajax({
        url: "../" + url + "?function=findHeureDebut",
        type: 'POST',
        async: false,
        error: function (jqXHR, textStatus, errorThrown) {
        },
        complete: function (jqXHR, textStatus) {

        },
        success: function (data, textStatus, jqXHR) {

            listRep = eval('(' + data + ')');
        }
    });

    return listRep;
}

function getClientByChambre(url_acceuil, numcha) {
    var listRep;
    $.ajax({
        url: "../" + url_acceuil + "?type=consult&function=getClientByChambre&numcha=" + numcha,
        type: 'POST',
        async: false,
        error: function(jqXHR, textStatus, errorThrown) {
        },
        complete: function(jqXHR, textStatus) {
        },
        success: function(data, textStatus, jqXHR) {
            listRep = eval('(' + data + ')');
        }
    });
    return listRep;
}

function addSession(url, numdoss, codemed, datefeuil, user, depot,groupe) {
    var listRep;
    $.ajax({
        url: "../" + url + "?type=update&function=addSession&numdoss=" + numdoss + "&codemed=" + codemed 
                + "&datefeuil=" + datefeuil + "&user=" + user + "&groupe=" + groupe ,
        type: 'POST',
        async: false,
        error: function(jqXHR, textStatus, errorThrown) {
        },
        complete: function(jqXHR, textStatus) {
        },
        success: function(data, textStatus, jqXHR) {
            listRep = eval('(' + data + ')');
        }
    });
    return listRep;
}

function getSession(url) {
    var listRep;
    $.ajax({
        url: "../" + url + "?type=consult&function=getSession",
        type: 'POST',
        async: false,
        error: function(jqXHR, textStatus, errorThrown) {
        },
        complete: function(jqXHR, textStatus) {
        },
        success: function(data, textStatus, jqXHR) {
            listRep = eval('(' + data + ')');
        }
    });
    return listRep;
}

function showNotification(title, msg, type, delais) {
    $(".SmallBox.animated").remove();
    var color;
    var icon;
    var sound;
    if(type === "error") 
    {
        color = "#a90329";
        icon = "fa fa-times-circle fa-2x bounce animated"
        if (title === "")
            title = "Attention";
        sound = "voice_alert";
    } 
    else 
    {
        color = "#296191";
        icon = "fa fa-thumbs-up fa-2x bounce animated";
        if (title === "")
            title = "Félicitation";
        sound = "bigbox";
    }
    
    if(delais === 0)
    {
        window.parent.$.sound_path = "../master_page/sound/";
        window.parent.$.sound_on = true;
        window.parent.$.smallBox({
            title: title,
            content: msg,
            color: color,
            iconSmall: icon,
            sound_file: sound
        });
    }
    else if(delais !== 0)
    {
        window.parent.$.sound_path = "../master_page/sound/";
        window.parent.$.sound_on = true;
        window.parent.$.smallBox({
            title: title,
            content: msg,
            color: color,
            iconSmall: icon,
            timeout: delais,
            sound_file: sound
        });
    }
}

function showNotificationArret() {
    $(".SmallBox.animated").remove();
    var color;
    var icon;
    var sound;
    
        color = "#a90329";
        icon = "fa fa-times-circle fa-2x bounce animated"
        
            title = "Attention";
        sound = "voice_alert";
    
    window.parent.$.sound_path = "../master_page/sound/";
    $.sound_on = true;
    window.parent.$.smallBox({
        title: title,
        content: "Traitement arrêté",
        color: color,
        iconSmall: icon,
        timeout: 4000,
        sound_file: sound
    });
}

function showAlertNotification(content) {

    window.parent.$.SmartMessageBox({
        title: "ATTENTION !",
        content: content,
        buttons: '[OK]'
    });
}

function showNotificationMessage(title) 
{
  
    window.parent.$.sound_on = false;
    window.parent.$.SmartMessageBox({
        title: "<i class='fa fa-refresh fa-spin' style='color:green'></i>"+title
    });
    window.parent.$(".botTempo").css("display", "none");
    window.parent.$.sound_on = false;

}

function showNumdossNotification() {
    window.parent.$.sound_on = false;
    window.parent.$.SmartMessageBox({
        title: "<i class='fa fa-refresh fa-spin' style='color:green'></i> VEUILLEZ CHOISIR UN PATIENT ",
        buttons: "[]"
    });
    window.parent.$(".botTempo").css("display", "none");
    window.parent.$.sound_on = false;

}

function showDossierAudite() {
    window.parent.$.sound_on = false;
    window.parent.$.SmartMessageBox({
        title: "<i class='fa fa-refresh fa-spin' style='color:green'></i> DOSSIER DEJA AUDITE ",
        buttons: "[]"
    });
    window.parent.$(".botTempo").css("display", "none");
    window.parent.$.sound_on = false;

}

function showLoadingNotification() {
    window.parent.$.sound_on = false;
    window.parent.$.SmartMessageBox({
        title: "<i class='fa fa-refresh fa-spin' style='color:green'></i> VEUILLEZ PATIENTER QUELQUES SECONDES...",
        buttons: "[OK]"
    });
    window.parent.$(".botTempo").css("display", "none");
    window.parent.$.sound_on = false;
    window.parent.$("#MsgBoxBack").show();
}

function showNotificationDialog() {
    window.parent.$.sound_on = false;
    window.parent.$.SmartMessageBox({
        title: "<i class='fa fa-refresh fa-spin' style='color:green'></i> LA LISTE DES DEMANDES DEJA CHOISIES SERA EFFECEE !",
        buttons: "[Annuler],[Confirmer]"
    });
    window.parent.$(".botTempo").css("display", "block");
    window.parent.$.sound_on = false;

}

function hideLoadingNotification() 
{
    window.parent.$(".botTempo").trigger("click");
    window.parent.$(".MessageBoxContainer").remove();
    window.parent.$("#MsgBoxBack").hide();
}

function dateNext()
{
    var length = parent.document.getElementById("selectDatesFeuille").length;
    var selectedIndex = document.getElementById("selectDatesFeuille").selectedIndex;
    if (selectedIndex > 0)
    {
        var valPrevious = document.getElementById("selectDatesFeuille").options[parseInt(selectedIndex) - 1].value;
        parent.$('#selectDatesFeuille').select2("val", valPrevious);
        parent.$('#selectDatesFeuille').change();
    }
}

function datePrevious()
{
    var selectedIndex = document.getElementById("selectDatesFeuille").selectedIndex;
    var length = document.getElementById("selectDatesFeuille").length;
    if (selectedIndex < length - 1)
    {
        var valNext = document.getElementById("selectDatesFeuille").options[parseInt(selectedIndex) + 1].value;
        parent.$('#selectDatesFeuille').select2("val", valNext);
        parent.$('#selectDatesFeuille').change();
    }
}

function createBackgrid(pageableGrid, idElement, gridColumns, collection, pagination, FocusableRow) {
    var columns = gridColumns;

    if (pagination === true) {
        pageableGrid = new Backgrid.Grid({
            emptyText: "Pas de resultat !",
            columns: columns,
            row: FocusableRow,
            collection: collection,
            footer: Backgrid.Extension.Paginator.extend({
                template: _.template('<tr><td colspan="<@= colspan @>"><ul class="pagination"><@ _.each(handles, function (handle) { @><li <@ if (handle.className) { @>class="<@= handle.className @>"<@ } @>><a href="#" <@ if (handle.title) {@> title="<@= handle.title @>"<@ } @>><@= handle.label @></a></li><@ }); @></ul></td></tr>')
            }),
            className: 'table table-bordered table-striped table-editable no-margin table-hover',
        });
    } else {
        pageableGrid = new Backgrid.Grid({
            emptyText: "",
            columns: columns,
            row: FocusableRow,
            collection: collection,
            className: 'table table-bordered table-striped table-editable no-margin table-hover',
        });
    }

    if(idElement === "_grid_examen_demande_pharm") 
    {
        var clientSideFilter = new Backgrid.Extension.ClientSideFilter({
            collection: collection,
            placeholder: "Recherche",
            id: "rechercher",
            fields: ['desart', 'codart'],
            wait: 150
        });

        $("#_grid_examen_demande_pharm").before(clientSideFilter.render().el);

        document.getElementById("search").focus();
    } 
    else if(idElement === "xxxxxxxx") 
    {
        var clientSideFilter = new Backgrid.Extension.ClientSideFilter({
            collection: collection,
            placeholder: "Recherche",
            id: "rechercher",
            fields: ['desart', 'codart'],
            wait: 150
        });

        $("#xxxxx").before(clientSideFilter.render().el);

        document.getElementById("search").focus();
    }
    else if(idElement === "_grid_medecin") 
    {
        var clientSideFilter = new Backgrid.Extension.ClientSideFilter({
            collection: collection,
            placeholder: "Recherche",
            id: "rechercher",
            fields: ['nomMed', 'codMed','libSpec'],
            wait: 150
        });

        $("#_grid_medecin").before(clientSideFilter.render().el);

        document.getElementById("search").focus();
    }

    $("#" + idElement).html(pageableGrid.render().$el);
    return pageableGrid;
}

function removeTmpGrid(grid)
{
    var models = grid.models;
    while (models.length > 0)
    {
        models[0].collection.remove(models[0]);
    }
}

function getUrlObjectParams() {
    var QueryString = function() {
        var query_string = {};
        var query = window.parent.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            // If first entry with this name
            if (typeof query_string[pair[0]] === "undefined") {
                query_string[pair[0]] = pair[1];
                // If second entry with this name
            } else if (typeof query_string[pair[0]] === "string") {
                var arr = [query_string[pair[0]], pair[1]];
                query_string[pair[0]] = arr;
                // If third or later entry with this name
            } else {
                query_string[pair[0]].push(pair[1]);
            }
        }

        return query_string;
    }();

    return QueryString;
}

function applyListenerToIndicator(index) {
    $("#_indice_demande_bloc").click(function(e) {
        index.$("#_demande_bloc").trigger("click");
    });
    $("#_indice_demande_labo").click(function(e) {
        index.$("#_demande_labo").trigger("click");
    });
    $("#_indice_demande_radio").click(function(e) {
        index.$("#_demande_radio").trigger("click");
    });
}

function getCodeFeuilleByType(url, suffixeCodeFeuille) {
    var listRep;
    $.ajax({
        url: "../" + url + "?type=consult&function=getCodeFeuilleByType&suffixeCodeFeuille=" + suffixeCodeFeuille,
        type: 'POST',
        async: false,
        error: function(jqXHR, textStatus, errorThrown) {

        },
        complete: function(jqXHR, textStatus) {

        },
        success: function(data, textStatus, jqXHR) {

            listRep = eval('(' + data + ')');
        }


    });
    return listRep;
}

function createPlanificationTacheInfirmiere(url_acceuil, planification) {
    var listRep;
    $.ajax({
        url: "../" + url_acceuil + "?type=update&function=createPlanificationTacheInfirmiere",
        type: 'POST',
        async: false,
        data: {planification: JSON.stringify(planification)},
        error: function(jqXHR, textStatus, errorThrown) {
        },
        complete: function(jqXHR, textStatus) {

        },
        success: function(data, textStatus, jqXHR) {

            listRep = eval('(' + data + ')');
        }
    });
    return listRep;
}

function createPlanificationTacheInfirmiereRadioLabo(url_acceuil, planification) {
    var listRep;
    $.ajax({
        url: "../" + url_acceuil + "?type=update&function=createPlanificationTacheInfirmiereRadioLabo",
        type: 'POST',
        async: false,
        data: {planification: JSON.stringify(planification)},
        error: function(jqXHR, textStatus, errorThrown) {
        },
        complete: function(jqXHR, textStatus) {

        },
        success: function(data, textStatus, jqXHR) {

            listRep = eval('(' + data + ')');
        }
    });
    return listRep;
}

function getMedecinByCode(url_acceuil, codMed) {
    var listRep;
    $.ajax({
        url: "../" + url_acceuil + "?type=consult&function=getMedecinByCode&codMed=" + codMed,
        type: 'POST',
        async: false,
        error: function(jqXHR, textStatus, errorThrown) {
        },
        complete: function(jqXHR, textStatus) {

        },
        success: function(data, textStatus, jqXHR) {

            listRep = eval('(' + data + ')');
        }
    });
    return listRep;
}

function showDateFR() {
    window.parent.$("[name='_doss_date_cloturee']").hide();
    window.parent.$("#calendrier").show();
    window.parent.$("[name='_info_pat_rea_cloturee']").hide();
    window.parent.$("[name='_info_pat_rea']").show();
}

function hideAll() {
    window.parent.$("[name='_doss_date_cloturee']").hide();
    window.parent.$("#calendrier").css("visibility","hidden");
    window.parent.$("[name='_info_pat_rea_cloturee']").hide();
    window.parent.$("[name='_info_pat_rea']").show();
    window.parent.$("#hour_planif").hide();
    window.parent.$("#leftHour").hide();
    window.parent.$("#rightHour").hide();
}

function updateImagePatient(numdoss,image)
{
    var reponse = "";
    $.ajax({
            url: "../Acceuil?type=update&function=updateImagePatient&numDoss=" + numdoss,
            type: 'POST',
            data: {image: JSON.stringify(image)},
            async: false,
            error: function()
            {
            },
            success: function(data)
            {
                reponse = data;
            }
        });
    return reponse;    
}

function findByCode(param)
{
    var reponse = "";
    $.ajax({
            url: "../Acceuil?type=consult&function=findByCode&param=" + param,
            type: 'POST',
            async: false,
            dataType: "json",
            error: function()
            {
            },
            success: function(data)
            {
                reponse = data;
            }
        });
    return reponse;    
}

function getParamByModule(code)
{
    $.ajax({
            url: "../Acceuil?type=consult&function=getParamByModule&code=" + code,
            type: 'POST',
            async: false,
            dataType: "json",
            error: function()
            {
            },
            success: function(data)
            {
                $.each(data, function(i, item)
                {
                    localStorage.setItem(data[i].code,data[i].valeur);
                });
            }
        });    
}

function js_dd_mm_yyyy__hh_mm_ss() {
  now = new Date();
  year = "" + now.getFullYear();
  month = "" + (now.getMonth() + 1); if (month.length === 1) { month = "0" + month; }
  day = "" + now.getDate(); if (day.length === 1) { day = "0" + day; }
  hour = "" + now.getHours(); if (hour.length === 1) { hour = "0" + hour; }
  minute = "" + now.getMinutes(); if (minute.length === 1) { minute = "0" + minute; }
  second = "" + now.getSeconds(); if (second.length === 1) { second = "0" + second; }
  return day + "-" + month + "-" + year + " " + hour + ":" + minute + ":" + second;
}

function hourNext()
{
    var length = parent.document.getElementById("selectHourPlanif").length;
    var selectedIndex = document.getElementById("selectHourPlanif").selectedIndex;
    if (selectedIndex > 0)
    {
        var valPrevious = document.getElementById("selectHourPlanif").options[parseInt(selectedIndex) - 1].value;
        parent.$('#selectHourPlanif').select2("val", valPrevious);
        parent.$('#selectHourPlanif').change();
        window.parent.$("#rightHour").show();
    }
    else if (selectedIndex >= 0){
        window.parent.$("#leftHour").hide();  
        
    }
}

function hourPrevious()
{
    var selectedIndex = document.getElementById("selectHourPlanif").selectedIndex;
    var length = document.getElementById("selectHourPlanif").length;
    if (selectedIndex < length - 1)
    {
        var valNext = document.getElementById("selectHourPlanif").options[parseInt(selectedIndex) + 1].value;
        parent.$('#selectHourPlanif').select2("val", valNext);
        parent.$('#selectHourPlanif').change();
        window.parent.$("#leftHour").show();  
    }
    else if (selectedIndex <= length - 1){
        window.parent.$("#rightHour").hide();
    }
}

function applyDatepicker(idElement) {

    $('#' + idElement).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 3,
        prevText: '<i class="fa fa-chevron-left"></i>',
        nextText: '<i class="fa fa-chevron-right"></i>',
        closeText: 'Fermer',
        currentText: 'Aujourd\'hui',
        monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
        monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Déc.'],
        dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
        dayNamesShort: ['Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.'],
        dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
        weekHeader: 'Sem.',
        dateFormat: 'dd/mm/yy',
        beforeShow: function() {
            setTimeout(function() {
                $('.ui-datepicker').css('z-index', 99999999999999);
            }, 0);
        }
    });

}

function fillSelectgetMedecin(div,groupe,codemed)
{
    $.ajax({
        url: "../Pharmacie?type=consult&function=getMedecin",
        contentType: "text/html; charset=utf-8",
        type: 'POST',
        dataType: "json",
        success: function (data)
        {
            var select_html = "<select id='selectgetMedecin'>";
            
            select_html += "<option value='choice' disabled='disabled' selected='selected'> Choisir Médecin </option>";
            
            $.each(data, function (i, item) {
                select_html += "<option value='" + data[i].codMed + "'>" + data[i].nomMed + "</option>";
            });

            select_html += "</select>";
            
            $('#'+div).html(select_html).trigger('create');

            $('#selectgetMedecin').select2({
                allowClear: true,
                dropdownAutoWidth: true,
                width: "100%"
            });
        },
        error: function () {
        }
    });
}

function DateSysteme()
{
    var reponse;
    $.ajax({
        url: "../Rea_Consult?function=getDate",
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

function AddZero(num) 
{
    return (num >= 0 && num < 10) ? "0" + num : num + "";
}