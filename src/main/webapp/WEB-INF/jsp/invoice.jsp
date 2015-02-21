<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="commons" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<commons:header title="Invoice - myCRM"/>
<body>
	<div id="wrapper">
		<jsp:include page="navigation.jsp"/>
        <div id="page-wrapper">
            <!-- /.row -->
            <br>
            <div class="row">
                <div class="col-lg-12">
                <div class="panel panel-default">
		                <div class="panel-heading">
		                    <div class="form-group">
		                        <h2><b>Ivoice</b></h2>
		                    </div>
		                </div>
		                <!-- /.panel-heading -->
		                <div class="panel-body ">
		                	<div class="row">
		                        <form:form modelAttribute="invoice" action="${pageContext.request.contextPath}/invoice" method="POST">
		                        	<form:errors element="div" cssClass="alert alert-danger"/>
		                        	<div class="form-group ${companyError}">
		                        	    <div class="col-lg-12">
			                        		<a href="${pageContext.request.contextPath}/selectCompany" class="btn btn-primary">Select Company</a>
			                        		<form:errors path="companyId" cssClass="control-label"/>
		                        		</div>
		                        		
		                        		<div class="col-lg-3">
				                           <form:input type="text" path="companyId" class="form-control" placeholder="Company Id" readonly="true"/>
				                    	</div>
				                    	
			                        	<div class="col-lg-8">
				                           <form:input type="text" path="companyInfo.name" class="form-control" placeholder="Name" readonly="true"/>
				                    	</div>
				                    	
				                    	<div class="col-lg-3">
				                           <form:input type="text" path="companyInfo.afm" class="form-control" placeholder="VAT" max="9" readonly="true"/>
				                    	</div>
				                    	
				                    	<div class="col-lg-8">
				                           <form:input type="text" path="companyInfo.busDesc" class="form-control" placeholder="Profession" readonly="true"/>
				                    	</div>
				                    	<div class="col-lg-8">
				                           <form:input type="text" path="companyInfo.address" class="form-control" placeholder="Address" readonly="true"/>
				                    	</div>
	     					            <div class="col-lg-3">
					                       <form:input type="text" path="companyInfo.doy" class="form-control" placeholder="DOY" readonly="true"/>
					                   	</div>
				                    	
			                    		<div class="col-lg-3">
				                           <form:input type="text" path="companyInfo.workPhone" class="form-control" placeholder="Work phone" readonly="true"/>
				                    	</div>
				                    	
				                    	<div class="col-lg-3">
				                           <form:input type="text" path="companyInfo.mobilePhone" class="form-control" placeholder="Mobile phone" readonly="true"/>
				                    	</div>
				                    	
				                    	<div class="col-lg-3">
				                           <form:input type="text" path="companyInfo.email" class="form-control" placeholder="Email" readonly="true"/>
				                    	</div>
									</div>
									<div class="form-group ${customerError}">				                    	
				                    	<div class="col-lg-12">
				                    		<br>
			                        		<a href="${pageContext.request.contextPath}/selectCustomer" class="btn btn-primary">Select Customer</a>
			                        		<form:errors path="customerId" cssClass="control-label"/>
		                        		</div>

		                        		<div class="col-lg-3">
				                           <form:input type="text" path="customerId" class="form-control" placeholder="Customer Id" readonly="true"/>
				                    	</div>
			                        	
			                        	<div class="col-lg-8">
				                           <form:input type="text" path="customer.customerName" class="form-control" placeholder="Name" readonly="true"/>
				                    	</div>

				                    	<div class="col-lg-3">
				                           <form:input type="text" path="customer.customerAfm" class="form-control" placeholder="VAT" max="9" readonly="true"/>
				                    	</div>
				                    	
				                    	<div class="col-lg-8">
				                           <form:input type="text" path="customer.customerBusDesc" class="form-control" placeholder="Profession" readonly="true"/>
				                    	</div>
				                    	<div class="col-lg-8">
				                           <form:input type="text" path="customer.customerAddress" class="form-control" placeholder="Address" readonly="true"/>
				                    	</div>
	     					            <div class="col-lg-3">
					                       <form:input type="text" path="customer.customerDoy" class="form-control" placeholder="DOY" readonly="true"/>
					                   	</div>
				                    	
			                    		<div class="col-lg-3">
				                           <form:input type="text" path="customer.customerPhone" class="form-control" placeholder="Phone" readonly="true"/>
				                    	</div>
									</div>
									<div class="form-group ${invoiceDateError }" style="margin-left:-12px">
										<div class="col-lg-12">
											<div class="col-lg-3">
												<label class="control-label">Invoice Date</label>
						                        <div class="input-group date" id="invoiceDate">
						                            <form:input path="invoiceDate" name="invoiceDate" type="text" class="form-control"/>
						                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar" style="color:white"></i></span>
						                        </div>
			                            		<form:errors path="invoiceDate" cssClass="control-label"/>
					                        </div>
										</div>
									</div>
									<div class="col-lg-12">
										<br>
										<h2><b>Taxes</b></h2>
									</div>	

		                    	    <div class="col-lg-3">
		                    	    	<div class="form-group ${fpaError}">
			                    	    	<label class="control-label">FPA</label>
			                    	    	<form:input type="number" path="fpa" class="form-control" placeholder="FPA" min="0" step="0.01"/>
			                    	    	<form:errors path="fpa" cssClass="control-label"/>
		                    	    	</div>
		                    	    </div>

		                    	    <div class="col-lg-3">
		                    	    	<div class="form-group ${withHoldingError}">
			                    	        <label class="control-label">Withholding</label>
			                    	    	<form:input type="number" path="withHolding" class="form-control" placeholder="Withholding"  min="0" step="0.01"/>
		   		                 	    	<form:errors path="withHolding" cssClass="control-label"/>
		                    	    	</div>
		                    	    </div>
									<div class="col-lg-12">
										<br>
										<h2><b>Invoice Details</b></h2>
										<input type="submit" name="addNewLine" class="btn btn-default" value="Add New Line"/>
									</div>	
				                    	
			                    	<div class="col-lg-12">
			                    		<br>
				                    	<div class="table-responsive">
					                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
					                            <thead>
					                            <tr>
					                                <th style="width:10%">Line No</th>
					                                <th style="width:70%">Description</th>
					                                <th style="width:15%">Net Amount</th>
					                                <th style="width:5%">Remove</th>
					                            </tr>
					                            </thead>
				                                <tbody>
				                                	<c:forEach var="invoiceLine" items="${invoice.invoiceLines }" varStatus="status">
				                                	    <tr class="gradeA">
				                                	    	<td>
				                                            	<input type="number" name="invoiceLines[${status.index}].lineId" value="${invoiceLine.lineId}"/>
			                                	    		</td>
				                                            <td>
                              	             	 	    		<textarea name="invoiceLines[${status.index}].description" style="width:100%">${invoiceLine.description}</textarea>
				                                            </td>
				                                            <td>
	                           	             	 	    		<input name="invoiceLines[${status.index}].net" type="number" pattern="###,##0,00" value="${invoiceLine.net}" />
			                                            	</td>
                           									<td><input type="submit" name="removeLine" class="btn btn-danger" value="${invoiceLine.lineId}"/></td>
				                                        </tr>
				                                    </c:forEach>
				                                    <c:if test="${invoice.withHoldingString != null}">
				                                    	<tr class="gradeA">
				                                	    	<td>
			                                	    		</td>
				                                            <td>
                              	             	 	    		<form:input path="withHoldingString" class="form-control" readonly="true"></form:input>
				                                            </td>
				                                            <td>
			                                            	</td>
                           									<td>
                           									</td>
				                                        </tr>
				                                    </c:if>
							                 	    <tr class="gradeA">
			                                            <td></td>
			                                            <td style="text-align:right"><b>Summary</b></td>
			                                            <td><form:input path="amount" type="number" readonly="true"/></td>
			                                        </tr>
							                 	    <tr class="gradeA">
			                                            <td></td>
			                                            <td style="text-align:right"><b>FPA</b></td>
			                                            <td><form:input type="number" path="fpaAmount" readonly="true"/></td>
			                                        </tr>    
							                 	    <tr class="gradeA">
			                                            <td></td>
			                                            <td style="text-align:right"><b>Gross</b></td>
			                                            <td><form:input type="number" path="gross" readonly="true"/></td>
			                                        </tr>    
			                                    </tbody>
					                        </table>
					                    </div>
					                </div>   
					                <div class="col-lg-12">
					                	<label class="control-label">Ολογράφως</label>
					                	<form:input type="text" path="words" class="form-control" readonly="true"/>
					                	<br>
					                </div> 
			                    	<div class="col-lg-3">
			                    		<input type="submit" name="calculate" class="btn btn-primary" value="Calculate"/>
			                    		<input type="submit" name="saveAndPrint" class="btn btn-primary" value="Save & Print"/>
			                    	</div>
		                    	</form:form>
	                    	</div>
		                </div>
		                <!-- /.panel-body -->
		            </div>
                </div>
            </div>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->

  <commons:footer/>
  <script>
	$('#invoiceDate').datepicker({
		format: "dd/mm/yyyy",
		autoclose: true 
	});
  </script>
</body>


</html>