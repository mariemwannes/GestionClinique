$(function () {
    $("#_liste_patients").unbind("click");
    $("#_liste_patients").bind("click", function (e)
    {
        window.parent.$("#enregistrer1").hide();
        window.parent.$("#_pharmacie").hide();
        window.parent.$("._info_pat_rea1").hide();
        window.parent.$("#inf_client").hide();
        $("#logo").show();
        $(".breadcrumb").html("");
        $("#content").empty();
        $("#content").append('<iframe id="iframe_cloturee" src="../britishgaz/body_page/liste_patients.jsp" scrolling="no" width="100%" height="3000" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');
    });

    $("#_li_pharmacie").unbind("click");
    $("#_li_pharmacie").bind("click", function (e)
    {
        window.parent.$("#enregistrer1").show();
        window.parent.$("#_pharmacie").show();
        window.parent.$("._info_pat_rea1").hide();
        window.parent.$("#inf_client").hide();
        $("#logo").show();
        $(".breadcrumb").html($(this).attr("title"));
        $("#content").empty();
        $("#content").append('<iframe id="iframe_cloturee" src="../britishgaz/body_page/pharmacie.jsp" scrolling="no" width="100%" height="3000" frameborder="0" style="display:none" onload="this.style.display = \'block\';"/>');
    });

    $("#_liste_patients").trigger("click");
});