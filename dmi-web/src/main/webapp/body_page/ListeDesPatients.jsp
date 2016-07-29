<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="css_declare.jsp"/>
    </head>
    <body>
        <h1>Liste des patients</h1>
        <div id="dt_basic_wrapper" class="dataTables_wrapper form-inline no-footer"><div class="dt-toolbar"><div class="col-xs-12 col-sm-6"><div id="dt_basic_filter" class="dataTables_filter"><label><span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span> <input type="search" class="form-control" placeholder="" aria-controls="dt_basic"></label></div></div><div class="col-sm-6 col-xs-12 hidden-xs"><div class="dataTables_length" id="dt_basic_length"><label><select name="dt_basic_length" aria-controls="dt_basic" class="form-control"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select></label></div></div></div><table id="dt_basic" class="table table-striped table-bordered table-hover dataTable no-footer" width="100%" role="grid" aria-describedby="dt_basic_info" style="width: 100%;">
                <thead>			                
                    <tr role="row"><th data-hide="phone" class="sorting_asc" tabindex="0" aria-controls="dt_basic" rowspan="1" colspan="1" aria-sort="ascending" aria-label="ID: activate to sort column ascending" style="width: 30px;">ID</th><th data-class="expand" class="sorting" tabindex="0" aria-controls="dt_basic" rowspan="1" colspan="1" aria-label=" Name: activate to sort column ascending" style="width: 83px;"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i> Name</th><th data-hide="phone" class="sorting" tabindex="0" aria-controls="dt_basic" rowspan="1" colspan="1" aria-label=" Phone: activate to sort column ascending" style="width: 127px;"><i ></i> Age</th><th class="sorting" tabindex="0" aria-controls="dt_basic" rowspan="1" colspan="1" aria-label="Company: activate to sort column ascending" style="width: 323px;">Sex</th><th data-hide="phone,tablet" class="sorting" tabindex="0" aria-controls="dt_basic" rowspan="1" colspan="1" aria-label=" Zip: activate to sort column ascending" style="width: 86px;"><i class="fa fa-fw fa-map-marker txt-color-blue hidden-md hidden-sm hidden-xs"></i> Zip</th><th data-hide="phone,tablet" class="sorting" tabindex="0" aria-controls="dt_basic" rowspan="1" colspan="1" aria-label="City: activate to sort column ascending" style="width: 180px;">Occupation</th><th data-hide="phone,tablet" class="sorting" tabindex="0" aria-controls="dt_basic" rowspan="1" colspan="1" aria-label=" Date: activate to sort column ascending" style="width: 75px;"><i class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i> Date</th></tr>
                </thead>
                <tbody>
                    <tr role="row" class="odd">
                        <td class="sorting_1">1</td>
                        <td><span class="responsiveExpander"></span>Jennifer</td>
                        <td>1-342-463-8341</td>
                        <td>Et Rutrum Non Associates</td>
                        <td>35728</td>
                        <td>Fogo</td>
                        <td>03/04/14</td>
                    </tr><tr role="row" class="even">
                        <td class="sorting_1">2</td>
                        <td><span class="responsiveExpander"></span>Clark</td>
                        <td>1-516-859-1120</td>
                        <td>Nam Ac Inc.</td>
                        <td>7162</td>
                        <td>Machelen</td>
                        <td>03/23/13</td>
                    </tr><tr role="row" class="odd">
                        <td class="sorting_1">3</td>
                        <td><span class="responsiveExpander"></span>Brendan</td>
                        <td>1-724-406-2487</td>
                        <td>Enim Commodo Limited</td>
                        <td>98611</td>
                        <td>Norman</td>
                        <td>02/13/14</td>
                    </tr><tr role="row" class="even">
                        <td class="sorting_1">4</td>
                        <td><span class="responsiveExpander"></span>Warren</td>
                        <td>1-412-485-9725</td>
                        <td>Odio Etiam Institute</td>
                        <td>10312</td>
                        <td>Sautin</td>
                        <td>01/01/13</td>
                    </tr><tr role="row" class="odd">
                        <td class="sorting_1">5</td>
                        <td><span class="responsiveExpander"></span>Rajah</td>
                        <td>1-849-642-8777</td>
                        <td>Neque Ltd</td>
                        <td>29131</td>
                        <td>Glovertown</td>
                        <td>02/16/13</td>
                    </tr><tr role="row" class="even">
                        <td class="sorting_1">6</td>
                        <td><span class="responsiveExpander"></span>Demetrius</td>
                        <td>1-470-329-9627</td>
                        <td>Euismod In Ltd</td>
                        <td>1883</td>
                        <td>Kapolei</td>
                        <td>03/15/13</td>
                    </tr><tr role="row" class="odd">
                        <td class="sorting_1">7</td>
                        <td><span class="responsiveExpander"></span>Keefe</td>
                        <td>1-188-191-2346</td>
                        <td>Molestie Industries</td>
                        <td>2211BM</td>
                        <td>Modena</td>
                        <td>07/08/13</td>
                    </tr><tr role="row" class="even">
                        <td class="sorting_1">8</td>
                        <td><span class="responsiveExpander"></span>Leila</td>
                        <td>1-663-655-8904</td>
                        <td>Est LLC</td>
                        <td>75286</td>
                        <td>Hondelange</td>
                        <td>12/09/12</td>
                    </tr><tr role="row" class="odd">
                        <td class="sorting_1">9</td>
                        <td><span class="responsiveExpander"></span>Fritz</td>
                        <td>1-598-234-7837</td>
                        <td>Et Ultrices Posuere Institute</td>
                        <td>2324</td>
                        <td>Monte San Pietrangeli</td>
                        <td>12/29/12</td>
                    </tr><tr role="row" class="even">
                        <td class="sorting_1">10</td>
                        <td><span class="responsiveExpander"></span>Cassady</td>
                        <td>1-212-965-8381</td>
                        <td>Vitae Erat Vel Company</td>
                        <td>5898</td>
                        <td>Huntly</td>
                        <td>10/07/13</td>
                    </tr></tbody>
            </table><div class="dt-toolbar-footer"><div class="col-sm-6 col-xs-12 hidden-xs"><div class="dataTables_info" id="dt_basic_info" role="status" aria-live="polite">Showing <span class="txt-color-darken">1</span> to <span class="txt-color-darken">10</span> of <span class="text-primary">100</span> entries</div></div><div class="col-xs-12 col-sm-6"><div class="dataTables_paginate paging_simple_numbers" id="dt_basic_paginate"><ul class="pagination pagination-sm"><li class="paginate_button previous disabled" aria-controls="dt_basic" tabindex="0" id="dt_basic_previous"><a href="#">Previous</a></li><li class="paginate_button active" aria-controls="dt_basic" tabindex="0"><a href="#">1</a></li><li class="paginate_button " aria-controls="dt_basic" tabindex="0"><a href="#">2</a></li><li class="paginate_button " aria-controls="dt_basic" tabindex="0"><a href="#">3</a></li><li class="paginate_button " aria-controls="dt_basic" tabindex="0"><a href="#">4</a></li><li class="paginate_button " aria-controls="dt_basic" tabindex="0"><a href="#">5</a></li><li class="paginate_button disabled" aria-controls="dt_basic" tabindex="0" id="dt_basic_ellipsis"><a href="#">…</a></li><li class="paginate_button " aria-controls="dt_basic" tabindex="0"><a href="#">10</a></li><li class="paginate_button next" aria-controls="dt_basic" tabindex="0" id="dt_basic_next"><a href="#">Next</a></li></ul></div></div></div></div>
                                <jsp:include page="js_declare.jsp"/>
        <script src="../body_page_js/ListeDesPatients.js?version=<%=session.getAttribute("versionJS")%>"></script>
        <script src="../body_page_js/ListeDesPatients_function.js?version=<%=session.getAttribute("versionJS")%>"></script>
    </body>
</html>
