<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="commons" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<commons:header title="Companies - myCRM"/>
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
		                        <label>Ivoice</label>
		                    </div>
		                </div>
		                <!-- /.panel-heading -->
		                <div class="panel-body ">
		                	<div class="row">
		                        <form:form modelAttribute="invoice" action="${pageContext.request.contextPath}/companiesInfo" method="POST">
		                        	<div class="col-lg-8">
		                        		<a href="${pageContext.request.contextPath}/selectCompany" class="btn btn-primary">Select Company</a>
	                        		</div>
		                        	<form:errors path="*" element="div" cssClass="alert alert-danger"/>
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
			                    	
			                    	<div class="col-lg-12">
			                    		<br>
		                        		<a href="${pageContext.request.contextPath}/selectCustomer" class="btn btn-primary">Select Customer</a>
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

									<div class="col-lg-12">
										<br>
										<b>Taxes</b>
									</div>	

		                    	    <div class="col-lg-3">
		                    	    	<form:input type="number" path="fpa" class="form-control" placeholder="FPA" pattern="##0.00"/>
		                    	    </div>

		                    	    <div class="col-lg-3">
		                    	    	<form:input type="number" path="withHolding" class="form-control" placeholder="Withholding" pattern="##0.00"/>
		                    	    </div>
			                    	
			                    	<div class="col-lg-12">
			                    		<br>
				                    	<div class="table-responsive">
					                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
					                            <thead>
					                            <tr>
					                                <th style="width:10%">Line No</th>
					                                <th style="width:70%">Description</th>
					                                <th style="width:20%">Net Amount</th>
					                            </tr>
					                            </thead>
					                                <tbody>
					                                	<c:forEach var="invoiceLine" items="${invoice.invoiceLines }">
					                                	    <tr class="gradeA">
					                                            <td>${invoiceLine.lineId}</td>
					                                            <td><textarea style="width:100%">${invoiceLine.description}</textarea></td>
					                                            <td><input type="number" pattern="###,##0,00" value="${invoiceLine.net}" /></td>
					                                        </tr>
					                                    </c:forEach>    
				                                    </tbody>
					                        </table>
					                    </div>
					                </div>    
			                    	<!-- <div class="col-lg-3">
			                    		<a href="${pageContext.request.contextPath}/companyInfo" class="btn btn-primary">New Company</a>
			                    	</div> -->
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

</body>

</html>