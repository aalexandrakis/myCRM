<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="commons" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<commons:header title="Customer - myCRM"/>
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
		                    <label>Customer</label>
		                </div>
		                <!-- /.panel-heading -->
		                <div class="panel-body ">
		                	<div class="form-group">
		                        <div class="row">
		                        	<form:form modelAttribute="customer" action="${pageContext.request.contextPath}/customer" method="POST">
		                        		<div class="col-lg-10">
		                        			<form:errors element="div" cssClass="alert alert-danger"/>
		                        			<c:if test="${successMessage != null }">
		                        				<div class="alert alert-success">
		                        					${successMessage }
		                        				</div>		
		                        			</c:if>
		                        			<c:if test="${customerId != null}">
			                        			<div class="form-group">
			                        			   <label>Customer Id:</label>
			                        			   <form:input type="text" path="customerId" style="border:none" readonly="true"/>
				                        	    </div>   
					                    	</c:if>
		                        			<div class="form-group ${customerNameError}">
		                        			   <form:input type="text" path="customerName" class="form-control" placeholder="Name"/>
		                        			   <form:errors path="customerName" element="label" cssClass="control-label"/>
			                        	    </div>   
					                    	<div class="form-group ${customerBusDescError }">
					                           <form:input type="text" path="customerBusDesc" class="form-control" placeholder="Profession"/>
					                           <form:errors path="customerBusDesc" element="label" cssClass="control-label"/>
					                    	</div>
				                    	    <div class="form-group ${customerPhoneError }">
					                           <form:input type="text" path="customerPhone" class="form-control" placeholder="Phone"/>
					                           <form:errors path="customerPhone" element="label" cssClass="control-label"/>
					                    	</div>
				                    	    <div class="form-group ${customerAfmError }">
					                           <form:input type="text" path="customerAfm" class="form-control" placeholder="VAT"/>
					                           <form:errors path="customerAfm" element="label" cssClass="control-label"/>
					                    	</div>
				                    	    <div class="form-group ${customerDoyError }">
					                           <form:input type="text" path="customerDoy" class="form-control" placeholder="DOY"/>
					                           <form:errors path="customerDoy" element="label" cssClass="control-label"/>
					                    	</div>
				                    	    <div class="form-group ${customerAddressError }">
					                           <form:input type="text" path="customerAddress" class="form-control" placeholder="Address"/>
					                           <form:errors path="customerAddress" element="label" cssClass="control-label"/>
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