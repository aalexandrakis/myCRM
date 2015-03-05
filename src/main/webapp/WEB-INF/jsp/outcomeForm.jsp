<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="commons" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<commons:header title="Outcome Invoice - myCRM"/>
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
		                        <h2>
		                        	<b>Outcome Invoice</b>
		                        	<c:if test="${not empty outcome.outcomeId}">
		                        		<b>Number : </b>
		                        		<b>${outcome.outcomeId}</b>
		                        	</c:if>
	                        	</h2>
		                    </div>
		                </div>
		                <!-- /.panel-heading -->
		                <div class="panel-body ">
		                	<div class="row">
		                        <form:form modelAttribute="outcome" action="${pageContext.request.contextPath}/outcome" enctype="multipart/form-data" method="POST">
		                        	<form:input type="hidden" path="outcomeId"/>
		                        	<form:errors element="div" cssClass="alert alert-danger"/>
		                        	<div class="form-group ${companyError}">
		                        	    <div class="col-lg-12">
		                        	    	<c:if test="${empty outcome.outcomeId}">
			                        			<a href="${pageContext.request.contextPath}/selectCompany/outcome" class="btn btn-primary">Select Company</a>
			                        		</c:if>	
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
									<div class="form-group ${supplierError}">				                    	
				                    	<div class="col-lg-12">
				                    		<br>
				                    		<c:if test="${empty outcome.outcomeId}">
			                        			<a href="${pageContext.request.contextPath}/selectSupplier/outcome" class="btn btn-primary">Select Supplier</a>
		                        			</c:if>
			                        		<form:errors path="supplierId" cssClass="control-label"/>
		                        		</div>

		                        		<div class="col-lg-3">
				                           <form:input type="text" path="supplierId" class="form-control" placeholder="Supplier Id" readonly="true"/>
				                    	</div>
			                        	
			                        	<div class="col-lg-8">
				                           <form:input type="text" path="supplier.supplierName" class="form-control" placeholder="Name" readonly="true"/>
				                    	</div>

				                    	<div class="col-lg-3">
				                           <form:input type="text" path="supplier.supplierAfm" class="form-control" placeholder="VAT" max="9" readonly="true"/>
				                    	</div>
				                    	
				                    	<div class="col-lg-8">
				                           <form:input type="text" path="supplier.supplierBusDesc" class="form-control" placeholder="Profession" readonly="true"/>
				                    	</div>
				                    	<div class="col-lg-8">
				                           <form:input type="text" path="supplier.supplierAddress" class="form-control" placeholder="Address" readonly="true"/>
				                    	</div>
	     					            <div class="col-lg-3">
					                       <form:input type="text" path="supplier.supplierDoy" class="form-control" placeholder="DOY" readonly="true"/>
					                   	</div>
				                    	
			                    		<div class="col-lg-3">
				                           <form:input type="text" path="supplier.supplierPhone" class="form-control" placeholder="Phone" readonly="true"/>
				                    	</div>
									</div>
									<div class="col-lg-12">								
										<div class="col-lg-3">
											<div class="form-group ${outcomeDateError }" >
												<label class="control-label">Outcome Date</label>
						                        <div class="input-group date" id="outcomeDate">
						                            <form:input path="outcomeDate" name="outcomeDate" type="text" class="form-control"/>
					                            	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar" style="color:white"></i></span>
						                        </div>
			                            		<form:errors path="outcomeDate" cssClass="control-label"/>
					                        </div>
										</div>
										
										<div class="col-lg-3">
			                    	    	<div class="form-group ${outcomeNumberError}">
				                    	    	<label class="control-label">Number</label>
				                    	    	<form:input type="text" path="outcomeNumber" class="form-control" placeholder="Number"/>
				                    	    	<form:errors path="outcomeNumber" cssClass="control-label"/>
			                    	    	</div>
			                    	    </div>
										
										<div class="col-lg-3">
			                    	    	<div class="form-group ${selectFileError}">
				                    	    	<label class="control-label">Outcome Invoice File ${outcome.fileName }</label>
				                    	    	<input type="file" name="file" />
			                    	    	</div>
		                    	    	</div>
		                    	    	
		                    	    	<c:if test="${not empty outcome.fileName}">
			                    	    	<div class="col-lg-3">
			                    	    		<object data="${pageContext.request.contextPath}/outcomePdf/${outcome.outcomeId}" type="${outcome.fileType}" width="200" height="200">
												</object>				
			                    	    	</div>
		                    	    	</c:if>
									</div>
									<div class="col-lg-12">
										<br>
										<h2><b>Outcome Invoice Details</b></h2>
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
					                                <th style="width:15%">Fpa</th>
					                                <th style="width:15%">Net Amount</th>
				                                	<th style="width:5%">Remove</th>
					                            </tr>
					                            </thead>
				                                <tbody>
				                                	<c:forEach var="outcomeLine" items="${outcome.outcomeLines}" varStatus="status">
				                                	    <tr class="gradeA">
				                                	    	<td>
				                                	    		<input type="hidden" name="outcomeLines[${status.index}].id" value="${outcomeLine.id}"/>
				                                            	<input type="number" name="outcomeLines[${status.index}].lineId" value="${outcomeLine.lineId}" readonly/>
			                                	    		</td>
				                                            <td>
                              	             	 	    			<textarea name="outcomeLines[${status.index}].description" style="width:100%">${outcomeLine.description}</textarea>
				                                            </td>
				                                            <td>
                              	             	 	    			<input type="text" name="outcomeLines[${status.index}].fpa" value="${outcomeLine.fpa}"></input>
				                                            </td>
				                                            <td>
	                           	             	 	    			<input name="outcomeLines[${status.index}].net" type="text" value="${outcomeLine.net}"/>
			                                            	</td>
                           									<td>
                           										<input type="submit" name="removeLine" class="btn btn-danger" value="${outcomeLine.lineId}"/>
                       										</td>
				                                        </tr>
				                                    </c:forEach>
							                 	    <tr class="gradeA">
			                                            <td></td>
			                                            <td style="text-align:right"><b>Summary</b></td>
			                                            <td><form:input type="text" path="amount" readonly="true" pattern="###,##0.00" step="0.01"/></td>
			                                        </tr>
							                 	    <tr class="gradeA">
			                                            <td></td>
			                                            <td style="text-align:right"><b>FPA</b></td>
			                                            <td><form:input type="text" path="fpaAmount" readonly="true" pattern="###,##0.00" step="0.01"/></td>
			                                        </tr>    
							                 	    <tr class="gradeA">
			                                            <td></td>
			                                            <td style="text-align:right"><b>Gross</b></td>
			                                            <td><form:input type="text" path="gross" readonly="true" pattern="###,##0.00" step="0.01"/></td>
			                                        </tr>    
			                                    </tbody>
					                        </table>
					                    </div>
					                </div>   
					                <div class="col-lg-3">
			                    		<input type="submit" name="calculate" class="btn btn-primary" value="Calculate"/>
			                    		<input type="submit" name="save" class="btn btn-primary" value="Save"/>
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
  <c:if test="${empty outcome.outcomeId}">
	 <script>
		$('#outcomeDate').datepicker({
			format: "dd/mm/yyyy",
			autoclose: true 
		});
	  </script>
  </c:if>
</body>


</html>