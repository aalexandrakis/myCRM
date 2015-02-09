<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="commons" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<commons:header title="Supplier - myCRM"/>
<body>
	<div id="wrapper">
		<jsp:include page="navigation.jsp"/>
        <div id="page-wrapper">
            <!-- /.row -->
            <br>
            <div class="row">
                <div class="col-lg-6">
                <div class="panel panel-default">
		                <div class="panel-heading">
		                    <label>Supplier</label>
		                </div>
		                <!-- /.panel-heading -->
		                <div class="panel-body ">
		                	<div class="form-group">
		                        <div class="row">
		                        	<form:form modelAttribute="supplier" action="${pageContext.request.contextPath}/supplier" method="POST">
		                        		<div class="col-lg-10">
		                        			<form:errors element="div" cssClass="alert alert-danger"/>
		                        			<c:if test="${successMessage != null }">
		                        				<div class="alert alert-success">
		                        					${successMessage }
		                        				</div>		
		                        			</c:if>
		                        			<c:if test="${supplierId != null}">
			                        			<div class="form-group">
			                        			   <label>Supplier Id:</label>
			                        			   <form:input type="text" path="supplierId" style="border:none" readonly="true"/>
				                        	    </div>   
					                    	</c:if>
		                        			<div class="form-group ${supplierNameError}">
		                        			   <form:input type="text" path="supplierName" class="form-control" placeholder="Name"/>
		                        			   <form:errors path="supplierName" element="label" cssClass="control-label"/>
			                        	    </div>   
					                    	<div class="form-group ${supplierBusDescError }">
					                           <form:input type="text" path="supplierBusDesc" class="form-control" placeholder="Profession"/>
					                           <form:errors path="supplierBusDesc" element="label" cssClass="control-label"/>
					                    	</div>
				                    	    <div class="form-group ${supplierPhoneError }">
					                           <form:input type="text" path="supplierPhone" class="form-control" placeholder="Phone"/>
					                           <form:errors path="supplierPhone" element="label" cssClass="control-label"/>
					                    	</div>
				                    	    <div class="form-group ${supplierAfmError }">
					                           <form:input type="text" path="supplierAfm" class="form-control" placeholder="VAT"/>
					                           <form:errors path="supplierAfm" element="label" cssClass="control-label"/>
					                    	</div>
				                    	    <div class="form-group ${supplierDoyError }">
					                           <form:input type="text" path="supplierDoy" class="form-control" placeholder="DOY"/>
					                           <form:errors path="supplierDoy" element="label" cssClass="control-label"/>
					                    	</div>
				                    	    <div class="form-group ${supplierAddressError }">
					                           <form:input type="text" path="supplierAddress" class="form-control" placeholder="Address"/>
					                           <form:errors path="supplierAddress" element="label" cssClass="control-label"/>
					                    	</div>
				                    	    <button class="btn btn-primary" type="submit">
					                               Save
		                           		    </button>
	                           		    </div>
			                    	</form:form>
		                    	</div>
		                    </div>
	                    </div>   
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