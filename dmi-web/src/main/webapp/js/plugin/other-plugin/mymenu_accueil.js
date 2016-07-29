$(function() {
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
    
    $("#_param").unbind("click");
    $("#_param").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            $("#planification_rea_opt").hide();
            if(windowsize < 750)
            {
                $("nav li").removeClass("active")
                $(this).parents("li").addClass("active").trigger('classChange');;
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/parametrage.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');
            }
            else
                window.open('../body_page/parametrage.jsp', 'pop', params);
        }
    });
    
    $("#_prescription").unbind("click");
    $("#_prescription").bind("click", function(e) {
        
        var modifAnesthesie = localStorage.getItem("modifAnesthesie");
        if(modifAnesthesie === "true")
            valider_feuille_anesthesie(this);
        else
        {
            $("#planification_rea_opt").hide();
            if(windowsize < 750)
            {
                $("nav li").removeClass("active")
                $(this).parents("li").addClass("active").trigger('classChange');;
                $(".breadcrumb").html($(this).attr("title"));
                $("#content").empty();
                $("#content").append('<iframe id="iframe_cloturee" src="../body_page/prescription.jsp" width="100%" height="1300" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');
            }
            else
                window.open('../body_page/prescription.jsp', 'pop', params);
        }
    });
    
    var arraySeesion = getSession("Acceuil");
    if (arraySeesion.length !== 0) 
    {
        userCreat = arraySeesion[3];
        groupe = arraySeesion[4];
    }
    
    var patient = JSON.parse(localStorage.getItem("Patient"));
    var access = localStorage.getItem("access");
    var chambre = localStorage.getItem("chambre");
    if(access !== null) 
    {
        if(access.indexOf('chambre') > -1 && patient !== null)
        {
            var patient = JSON.parse(localStorage.getItem("Patient"));
            if(patient !== null)
            {
                var numDoss = patient.numDoss;
                var codeMed = patient.medTrait.codMed;
                var user = userCreat;
                var dateFeuil = getDateFeuille();
                addSession("Patients",numDoss,codeMed,dateFeuil,user,groupe);
            }   
        }
    }
});

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
            if(element.id === "_accueil")
                window.parent.$("#"+element.id).trigger("click");
            else if(element.id !== "_accueil")
                window.parent.$("#"+element.id).trigger("tap");
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