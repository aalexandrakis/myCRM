<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="commons" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<commons:header title="Payment Invoice - myCRM"/>
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
		                        	<b>Payment Invoice</b>
		                        	<c:if test="${not empty payment.paymentId}">
		                        		<b>Number : </b>
		                        		<b>${payment.paymentId}</b>
		                        	</c:if>
	                        	</h2>
		                    </div>
		                </div>
		                <!-- /.panel-heading -->
		                <div class="panel-body ">
		                	<div class="row">
		                        <form:form modelAttribute="payment" action="${pageContext.request.contextPath}/payment" enctype="multipart/form-data" method="POST">
		                        	<form:input type="hidden" path="paymentId"/>
		                        	<form:errors element="div" cssClass="alert alert-danger"/>
		                        	<div class="form-group ${companyError}">
		                        	    <div class="col-lg-12">
		                        	    	<c:if test="${empty payment.paymentId}">
			                        			<a href="${pageContext.request.contextPath}/selectCompany/payment" class="btn btn-primary">Select Company</a>
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
				                    		<c:if test="${empty payment.paymentId}">
			                        			<a href="${pageContext.request.contextPath}/selectSupplier/payment" class="btn btn-primary">Select Supplier</a>
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
											<div class="form-group ${paymentDateError }" >
												<label class="control-label">Payment Date</label>
						                        <div class="input-group date" id="paymentDate">
						                            <form:input path="paymentDate" name="paymentDate" type="text" class="form-control"/>
					                            	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar" style="color:white"></i></span>
						                        </div>
			                            		<form:errors path="paymentDate" cssClass="control-label"/>
					                        </div>
										</div>
										
										<div class="col-lg-3">
			                    	    	<div class="form-group ${amountError}">
				                    	    	<label class="control-label">Amount</label>
				                    	    	<form:input type="text" path="amount" class="form-control" placeholder="Amount"/>
				                    	    	<form:errors path="amount" cssClass="control-label"/>
			                    	    	</div>
			                    	    </div>
										
										<div class="col-lg-3">
			                    	    	<div class="form-group ${selectFileError}">
				                    	    	<label class="control-label">Payment receipt ${payment.paymentFileName }</label>
				                    	    	<input type="file" name="file" />
			                    	    	</div>
		                    	    	</div>
		                    	    	
		                    	    	<c:if test="${not empty payment.paymentFileName}">
			                    	    	<div class="col-lg-12">
			                    	    		<object data="${pageContext.request.contextPath}/paymentPdf/${payment.paymentId}" type="${payment.paymentFileType}" style="width:100%;">
												</object>				
			                    	    	</div>
		                    	    	</c:if>
									</div>
					                <div class="col-lg-3">
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
  <c:if test="${empty payment.paymentId}">
	 <script>
		$('#paymentDate').datepicker({
			format: "dd/mm/yyyy",
			autoclose: true 
		});
	  </script>
  </c:if>
</body>


</html>