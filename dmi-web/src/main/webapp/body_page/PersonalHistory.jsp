

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<body>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="css_declare.jsp"/>
        <title>PersonalHistory</title>
    </head>
    <body>
     
       
        <div class="row">

		<!-- NEW COL START -->
		<article class="col-sm-6 col-md-6 col-lg-6 sortable-grid ui-sortable">
			
			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget jarviswidget-sortable" id="wid-id-1" data-widget-editbutton="false" data-widget-custombutton="false" role="widget">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header role="heading"><div class="jarviswidget-ctrls" role="menu">   <a href="javascript:void(0);" class="button-icon jarviswidget-toggle-btn" rel="tooltip" title="" data-placement="bottom" data-original-title="Collapse"><i class="fa fa-minus "></i></a> <a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn" rel="tooltip" title="" data-placement="bottom" data-original-title="Fullscreen"><i class="fa fa-expand "></i></a> <a href="javascript:void(0);" class="button-icon jarviswidget-delete-btn" rel="tooltip" title="" data-placement="bottom" data-original-title="Delete"><i class="fa fa-times"></i></a></div><div class="widget-toolbar" role="menu"><a data-toggle="dropdown" class="dropdown-toggle color-box selector" href="javascript:void(0);" aria-expanded="false"></a><ul class="dropdown-menu arrow-box-up-right color-select pull-right"><li><span class="bg-color-green" data-widget-setstyle="jarviswidget-color-green" rel="tooltip" data-placement="left" data-original-title="Green Grass"></span></li><li><span class="bg-color-greenDark" data-widget-setstyle="jarviswidget-color-greenDark" rel="tooltip" data-placement="top" data-original-title="Dark Green"></span></li><li><span class="bg-color-greenLight" data-widget-setstyle="jarviswidget-color-greenLight" rel="tooltip" data-placement="top" data-original-title="Light Green"></span></li><li><span class="bg-color-purple" data-widget-setstyle="jarviswidget-color-purple" rel="tooltip" data-placement="top" data-original-title="Purple"></span></li><li><span class="bg-color-magenta" data-widget-setstyle="jarviswidget-color-magenta" rel="tooltip" data-placement="top" data-original-title="Magenta"></span></li><li><span class="bg-color-pink" data-widget-setstyle="jarviswidget-color-pink" rel="tooltip" data-placement="right" data-original-title="Pink"></span></li><li><span class="bg-color-pinkDark" data-widget-setstyle="jarviswidget-color-pinkDark" rel="tooltip" data-placement="left" data-original-title="Fade Pink"></span></li><li><span class="bg-color-blueLight" data-widget-setstyle="jarviswidget-color-blueLight" rel="tooltip" data-placement="top" data-original-title="Light Blue"></span></li><li><span class="bg-color-teal" data-widget-setstyle="jarviswidget-color-teal" rel="tooltip" data-placement="top" data-original-title="Teal"></span></li><li><span class="bg-color-blue" data-widget-setstyle="jarviswidget-color-blue" rel="tooltip" data-placement="top" data-original-title="Ocean Blue"></span></li><li><span class="bg-color-blueDark" data-widget-setstyle="jarviswidget-color-blueDark" rel="tooltip" data-placement="top" data-original-title="Night Sky"></span></li><li><span class="bg-color-darken" data-widget-setstyle="jarviswidget-color-darken" rel="tooltip" data-placement="right" data-original-title="Night"></span></li><li><span class="bg-color-yellow" data-widget-setstyle="jarviswidget-color-yellow" rel="tooltip" data-placement="left" data-original-title="Day Light"></span></li><li><span class="bg-color-orange" data-widget-setstyle="jarviswidget-color-orange" rel="tooltip" data-placement="bottom" data-original-title="Orange"></span></li><li><span class="bg-color-orangeDark" data-widget-setstyle="jarviswidget-color-orangeDark" rel="tooltip" data-placement="bottom" data-original-title="Dark Orange"></span></li><li><span class="bg-color-red" data-widget-setstyle="jarviswidget-color-red" rel="tooltip" data-placement="bottom" data-original-title="Red Rose"></span></li><li><span class="bg-color-redLight" data-widget-setstyle="jarviswidget-color-redLight" rel="tooltip" data-placement="bottom" data-original-title="Light Red"></span></li><li><span class="bg-color-white" data-widget-setstyle="jarviswidget-color-white" rel="tooltip" data-placement="right" data-original-title="Purity"></span></li><li><a href="javascript:void(0);" class="jarviswidget-remove-colors" data-widget-setstyle="" rel="tooltip" data-placement="bottom" data-original-title="Reset widget color to default">Remove</a></li></ul></div>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>Personal History </h2>				
					
				<span class="jarviswidget-loader"><i class="fa fa-refresh fa-spin"></i></span></header>

				<!-- widget div-->
				<div role="content">
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<form action="" id="checkout-form" class="smart-form" novalidate="novalidate">

							<fieldset>
								<div class="row">
									<section class="col col-6">
										<label class="input"> <i class="icon-prepend fa fa-user"></i>
											<input type="text" name="fname" placeholder="Patient Name">
										</label>
									</section>
									<section class="col col-6">
										<label class="input"> <i class="icon-prepend fa fa-envelope-o"></i>
											<input type="number" name="lname" placeholder="MR.NO.">
										</label>
									</section>
								</div>

								<div class="row">
									<section class="col col-6">
                                                                            <p>Date</p>
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="date" name="d" placeholder="Date">
										</label>
									</section>
									<section class="col col-6">
                                                                            <p>Time</p>
										<label class="input state-success"> <i class="icon-append fa fa-calendar"></i>
											<input type="time" name="temps" placeholder="Time" >
										</label>
									</section>
								</div>
							</fieldset>

							<fieldset>
								<div class="row">
									<section class="col col-5">
										<label class="input">
											<input type="number" name="age" placeholder="Age">
										</label>
									</section>

									<section class="col col-4">
										<label class="input">
											<input type="number" name="numroom" placeholder="Room No.">
										</label>
									</section>

									<section class="col col-3">
										<label class="input">
											<input type="text" name="job" placeholder="Ocuupation">
										</label>
									</section>
								</div>

								

								<section>
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold"> Sex</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="radio-inline" checked="">
											<i></i>Male</label>
										<label class="radio">
											<input type="radio" name="radio-inline">
											<i></i>Female</label>
										
									</div>
								</section>
                                                            <section>
									<label class="input"><i class="icon-append fa fa-briefcase"></i>
										<input type="text" name="consultant" placeholder="Consultant">
									</label>
								</section>
                                                            <section>
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold"> Marital History</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="M" checked="">
											<i></i>M</label>
										<label class="radio">
											<input type="radio" name="S">
											<i></i>S</label>
                                                                            <label class="radio">
											<input type="radio" name="W">
											<i></i>W</label>
                                                                            <label class="radio">
											<input type="radio" name="D">
											<i></i>D</label>
										
									</div>
								</section>
                                                             <section>
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold"> Children</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="yes" checked="" >
											<i></i>Yes</label>
										<label class="radio">
											<input type="radio" name="no">
											<i></i>No</label>
                                                                          
										
									</div>
								</section>
                                                            <section>
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold"> Special Habits</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="smoking" checked="">
											<i></i>Smoking</label>
										<label class="radio">
											<input type="radio" name="coffee">
											<i></i>Coffee</label>
                                                                            <label class="radio">
											<input type="radio" name="alcohol">
											<i></i>Alcohol</label>
                                                                            <label class="radio">
											<input type="radio" name="diabetes">
											<i></i>Diabetes</label>
                                                                            <label class="radio">
											<input type="radio" name="hypertension">
											<i></i>Hypertension</label>
                                                                            <label class="radio">
											<input type="radio" name="bi">
											<i></i>BI.Transfusion</label>
										
									</div>
								</section>
                                                            <section>
									<label class="input">
										<input type="text" name="other" placeholder="Other">
									</label>
								</section>
                                                   
                                                            <section>
                                                                <div class="row">
									<section class="col col-6">
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold">Allergy</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="yes" checked="">
											<i></i>Yes</label>
										<label class="radio">
											<input type="radio" name="no">
											<i></i>No</label>
                                                                        
										
									</div>
								</section>
                                                            <section class="col col-6">
                                                                            
										<label class="input state-success"> <i class="icon-append fa fa-comment"></i>
											<input type="text" name="comment" placeholder="Comment" >
										</label>
									</section>
                                                                    
                                                                    
                                                                    
							</fieldset>

							<fieldset>
								<section>
                                                                <div class="row">
									<section class="col col-6">
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold">Adverse drug reaction</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="yes" checked="">
											<i></i>Yes</label>
										<label class="radio">
											<input type="radio" name="no">
											<i></i>No</label>
                                                                        
										
									</div>
								</section>
                                                            <section class="col col-6">
                                                                            
										<label class="input state-success"> <i class="icon-append fa fa-comment"></i>
											<input type="text" name="comment" placeholder="Comment" >
										</label>
									</section>
                                                                    
							</fieldset>
                                                    <fieldset>
                                                        <section>
									<label class="label">Complaint</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="5" name="message" id="message"></textarea>
									</label>
								</section>
                                                        
                                                    </fieldset>
                                                    
                                                    <fieldset>
                                                        <section>
									<label class="label">Personal History</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="12" name="message" id="ph"></textarea>
									</label>
								</section>
                                                        
                                                    </fieldset>
                                                    <fieldset>
                                                        <section>
									
									<label class="label">Past History ( Hospital admission & surgery )</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="1" name="pasth" id="pasth"></textarea>
									</label>
								</section>
                                                        
                                                    </fieldset>
                                                    <fieldset>
                                                        <section>
									
									<label class="label">Family History</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="1" name="family" id="family"></textarea>
									</label>
								</section>
                                                        
                                                    </fieldset>
                                                    <fieldset>
                                                        <section>
									
									<label class="label">Psycho social History</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="1" name="psycho" id="psycho_h"></textarea>
									</label>
								</section>
                                                        
                                                    </fieldset>
                                                    <fieldset>
                                                        <section>
									
									<label class="label">Neurological State</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="1" name="neuro" id="neuro_s"></textarea>
									</label>
								</section>
                                                        
                                                    </fieldset>
                                                    <fieldset>
                                                    <section>
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold"> Consciousness</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="cons" checked="">
											<i></i>Consc.</label>
										<label class="radio">
											<input type="radio" name="stupper">
											<i></i>Stupper</label>
                                                                            <label class="radio">
											<input type="radio" name="drowzy">
											<i></i>Drowzy</label>
                                                                            <label class="radio">
											<input type="radio" name="disoriented">
											<i></i>Disoriented</label>
										
									</div>
								</section>          
                                                    </fieldset>
                                                    <fieldset>
                                                    <section>
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold"> G.CS.</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="eye" checked="">
											<i></i>/4Eye open</label>
										<label class="radio">
											<input type="radio" name="verbal">
											<i></i>/5 Verbal resp.</label>
                                                                            <label class="radio">
											<input type="radio" name="motor">
											<i></i>/6 Motor Power</label>
                                                                            <label class="radio">
											<input type="radio" name="total">
											<i></i>/15 Total</label>
										
									</div>
								</section>          
                                                    </fieldset>
                                                    <fieldset>
                                                    <section>
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold">Lat. Signs</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="pupils" checked="">
											<i></i>Pupils</label>
										<label class="radio">
											<input type="radio" name="rt">
											<i></i>RT.</label>
                                                                            <label class="radio">
											<input type="radio" name="ul">
											<i></i>U.L.</label>
                                                                            <label class="radio">
											<input type="radio" name="rt2">
											<i></i>Rt.</label>
                                                                             <label class="radio">
											<input type="radio" name="ll">
											<i></i>L.L.</label>
                                                                            <label class="radio">
											<input type="radio" name="lt">
											<i></i>LT.</label>
                                                                
                                                                            <label class="radio">
											<input type="radio" name="lt2">
											<i></i>Lt.</label>
										
									</div>
								</section>          
                                                    </fieldset>
							<footer>
								<button type="submit" class="btn btn-primary">
									Validate Form
								</button>
							</footer>
						</form>

					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
                                </div></article>
			<!-- end widget -->
					<article class="col-sm-6 col-md-6 col-lg-6 sortable-grid ui-sortable">

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget jarviswidget-sortable" id="wid-id-3" data-widget-editbutton="false" data-widget-custombutton="false" role="widget">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header role="heading"><div class="jarviswidget-ctrls" role="menu">   <a href="javascript:void(0);" class="button-icon jarviswidget-toggle-btn" rel="tooltip" title="" data-placement="bottom" data-original-title="Collapse"><i class="fa fa-minus "></i></a> <a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn" rel="tooltip" title="" data-placement="bottom" data-original-title="Fullscreen"><i class="fa fa-expand "></i></a> <a href="javascript:void(0);" class="button-icon jarviswidget-delete-btn" rel="tooltip" title="" data-placement="bottom" data-original-title="Delete"><i class="fa fa-times"></i></a></div><div class="widget-toolbar" role="menu"><a data-toggle="dropdown" class="dropdown-toggle color-box selector" href="javascript:void(0);"></a><ul class="dropdown-menu arrow-box-up-right color-select pull-right"><li><span class="bg-color-green" data-widget-setstyle="jarviswidget-color-green" rel="tooltip" data-placement="left" data-original-title="Green Grass"></span></li><li><span class="bg-color-greenDark" data-widget-setstyle="jarviswidget-color-greenDark" rel="tooltip" data-placement="top" data-original-title="Dark Green"></span></li><li><span class="bg-color-greenLight" data-widget-setstyle="jarviswidget-color-greenLight" rel="tooltip" data-placement="top" data-original-title="Light Green"></span></li><li><span class="bg-color-purple" data-widget-setstyle="jarviswidget-color-purple" rel="tooltip" data-placement="top" data-original-title="Purple"></span></li><li><span class="bg-color-magenta" data-widget-setstyle="jarviswidget-color-magenta" rel="tooltip" data-placement="top" data-original-title="Magenta"></span></li><li><span class="bg-color-pink" data-widget-setstyle="jarviswidget-color-pink" rel="tooltip" data-placement="right" data-original-title="Pink"></span></li><li><span class="bg-color-pinkDark" data-widget-setstyle="jarviswidget-color-pinkDark" rel="tooltip" data-placement="left" data-original-title="Fade Pink"></span></li><li><span class="bg-color-blueLight" data-widget-setstyle="jarviswidget-color-blueLight" rel="tooltip" data-placement="top" data-original-title="Light Blue"></span></li><li><span class="bg-color-teal" data-widget-setstyle="jarviswidget-color-teal" rel="tooltip" data-placement="top" data-original-title="Teal"></span></li><li><span class="bg-color-blue" data-widget-setstyle="jarviswidget-color-blue" rel="tooltip" data-placement="top" data-original-title="Ocean Blue"></span></li><li><span class="bg-color-blueDark" data-widget-setstyle="jarviswidget-color-blueDark" rel="tooltip" data-placement="top" data-original-title="Night Sky"></span></li><li><span class="bg-color-darken" data-widget-setstyle="jarviswidget-color-darken" rel="tooltip" data-placement="right" data-original-title="Night"></span></li><li><span class="bg-color-yellow" data-widget-setstyle="jarviswidget-color-yellow" rel="tooltip" data-placement="left" data-original-title="Day Light"></span></li><li><span class="bg-color-orange" data-widget-setstyle="jarviswidget-color-orange" rel="tooltip" data-placement="bottom" data-original-title="Orange"></span></li><li><span class="bg-color-orangeDark" data-widget-setstyle="jarviswidget-color-orangeDark" rel="tooltip" data-placement="bottom" data-original-title="Dark Orange"></span></li><li><span class="bg-color-red" data-widget-setstyle="jarviswidget-color-red" rel="tooltip" data-placement="bottom" data-original-title="Red Rose"></span></li><li><span class="bg-color-redLight" data-widget-setstyle="jarviswidget-color-redLight" rel="tooltip" data-placement="bottom" data-original-title="Light Red"></span></li><li><span class="bg-color-white" data-widget-setstyle="jarviswidget-color-white" rel="tooltip" data-placement="right" data-original-title="Purity"></span></li><li><a href="javascript:void(0);" class="jarviswidget-remove-colors" data-widget-setstyle="" rel="tooltip" data-placement="bottom" data-original-title="Reset widget color to default">Remove</a></li></ul></div>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>Personal Examination </h2>				
					
				<span class="jarviswidget-loader"><i class="fa fa-refresh fa-spin"></i></span></header>

				<!-- widget div-->
				<div role="content">
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<form action="" id="order-form" class="smart-form" novalidate="novalidate">
							<header>
								General Examination
							</header>

							<fieldset>
                                                         <p>A)Vital Signs</p>
                                                         </br>
								<div class="row">
									<section class="col col-6">
                                                                          
										<label class="input"> <i class="icon-append fa fa-question-circle"></i>
											<input type="number" name="temperature" placeholder="Temperature">
										</label>
									</section>
									<section class="col col-6">
										<label class="input"> <i class="icon-append fa fa-question-circle"></i>
											<input type="number" name="blood" placeholder="Blood pressure">
										</label>
									</section>
								</div>

								<div class="row">
									<section class="col col-6">
										<label class="input"> <i class="icon-append fa fa-question-circle"></i>
											<input type="number" name="pulse" placeholder="Pulse">
										</label>
									</section>
									<section class="col col-6">
										<label class="input"> <i class="icon-append fa fa-question-circle"></i>
											<input type="number" name="respiratory" placeholder="Respiratory" data-mask="(999) 999-9999">
										</label>
									</section>
								</div>
                                                         <div class="row">
									<section class="col col-6">
										<label class="input"> <i class="icon-append fa fa-question-circle"></i>
											<input type="number" name="lymph" placeholder=" Lymph nodes">
										</label>
									</section>
									<section class="col col-6">
										<label class="input"> <i class="icon-append fa fa-question-circle"></i>
											<input type="number" name="weight" placeholder="Weight" data-mask="(999) 999-9999">
										</label>
									</section>
								</div>
                                                         
							</fieldset>
<fieldset>
								<section>
                                                                <div class="row">
									<section class="col col-6">
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold">Pain</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="yes" checked="">
											<i></i>Yes</label>
										<label class="radio">
											<input type="radio" name="no">
											<i></i>No</label>
                                                                        
										
									</div>
								</section>
                                                            <section class="col col-6">
                                                                            
										<label class="input state-success"> <i class="icon-append fa fa-comment"></i>
											<input type="text" name="spec" placeholder="Specify" >
										</label>
									</section>
                                                                    
							</fieldset>
                                                    <fieldset>
                                                        <section>
									<label class="input">
										<input type="text" name="other" placeholder="Other">
									</label>
								</section>
                                                    </fieldset>
                                                    <fieldset>
                                                         <p>B)Systemic Examination</p>
                                                         </br>
								 <section>
									<label class="label">Heart</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="4" name="heart" id="coeur"></textarea>
									</label>
								</section>
                                                         <section>
									<label class="label">Chest</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="4" name="chest" id="chest"></textarea>
									</label>
								</section>
                                                         
                                                         <section>
									<label class="label">Abdomen</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="4" name="abdomen" id="ab"></textarea>
									</label>
								</section>
                                                         <section>
									<label class="label">Neurological</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="4" name="neurological" id="neuro"></textarea>
									</label>
								</section>
                                                         
                                                    </fieldset>
                                                    <fieldset>
									<section>
									<label class="textarea"> <i class="icon-append fa fa-comment"></i> 										
										<textarea rows="20" name="local" placeholder="Local Examination"></textarea> 
									</label>
								</section>
							</fieldset>
                                                    <fieldset>
									<section>
									<label class="textarea"> <i class="icon-append fa fa-comment"></i> 										
										<textarea rows="5" name="pro" placeholder="Provisional diagnosis"></textarea> 
									</label>
								</section>
							</fieldset>
                                                    <fieldset>
                                                        <section>
									<label class="label">Plan of care</label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<textarea rows="2" name="plan" id="plan"></textarea>
									</label>
								</section>
                                                    </fieldset>
                                                    <fieldset>
								<div class="row">
									<section class="col col-6">
										<label class="input"> <i class="icon-prepend fa fa-user"></i>
											<input type="text" name="nomMedecin" placeholder="*Checked & reviwed by Dr.">
										</label>
									</section>
									
								</div>

								<div class="row">
									<section class="col col-6">
                                                                            <p>Date</p>
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="date" name="d" placeholder="Date">
										</label>
									</section>
									<section class="col col-6">
                                                                            <p>Time</p>
										<label class="input state-success"> <i class="icon-append fa fa-calendar"></i>
											<input type="time" name="temps" placeholder="Time" >
										</label>
									</section>
								</div>
							</fieldset>
                                                    <fieldset>
                                                        <section>
									<div class="inline-group">
                                                                            <label style="font-size:12px;font-weight:bold"> *Add in the care plan management if any of the following is present</label>
                                                                            <p></p>
										<label class="radio">
											<input type="radio" name="1" checked="">
											<i></i>1- Lost Weight unintentionally</label>
										<label class="radio">
											<input type="radio" name="2">
											<i></i>2- Looks poorly nourished</label>
                                                                            <label class="radio">
											<input type="radio" name="3">
											<i></i>3- Inability to eat ≥ 50 days </label>
                                                                            <label class="radio">
											<input type="radio" name="4">
											<i></i>4- Pregnant / Lactating with medical complications</label>
                                                                            
									</div>
								</section>
                                                    </fieldset>
							<footer>
								<button type="submit" class="btn btn-primary">
									Validate Form
								</button>
							</footer>
						</form>

					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
                        </div></article></div>
			<!-- end widget -->				

			
	</div>
        <jsp:include page="js_declare.jsp"/>
        <script src="../body_page_js/PersonalHistory.js?version=<%=session.getAttribute("versionJS")%>"></script>
        <script src="../body_page_js/PersonalHistory_function.js?version=<%=session.getAttribute("versionJS")%>"></script>
    </body>
</html>
