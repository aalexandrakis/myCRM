<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="commons" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<commons:header title="Company Info - myCRM"/>
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
		                    <label>Company Info</label>
		                </div>
		                <!-- /.panel-heading -->
		                <div class="panel-body ">
		                	<div class="form-group">
		                        <div class="row">
		                        	<form:form modelAttribute="companyInfo" action="${pageContext.request.contextPath}/companyInfo" method="POST">
		                        		<div class="col-lg-10">
		                        			<form:errors element="div" cssClass="alert alert-danger"/>
		                        			<c:if test="${successMessage != null }">
		                        				<div class="alert alert-success">
		                        					${successMessage }
		                        				</div>		
		                        			</c:if>
		                        			<c:if test="${companyId != null}">
			                        			<div class="form-group">
			                        			   <label>Company Id:</label>
			                        			   <form:input type="text" path="companyId" style="border:none" readonly="true"/>
				                        	    </div>   
					                    	</c:if>
		                        			<div class="form-group ${nameError}">
		                        			   <form:input type="text" path="name" class="form-control" placeholder="Name"/>
		                        			   <form:errors path="name" element="label" cssClass="control-label"/>
			                        	    </div>   
					                    	<div class="form-group ${busDescError }">
					                           <form:input type="text" path="busDesc" class="form-control" placeholder="Profession"/>
					                           <form:errors path="busDesc" element="label" cssClass="control-label"/>
					                    	</div>   
					                    	<div class="form-group ${addressError }">
					                           <form:input type="text" path="address" class="form-control" placeholder="Address"/>
					                           <form:errors path="address" element="label" cssClass="control-label"/>
					                    	</div>   
					                    	<div class="form-group ${afmError }">
					                           <form:input type="text" path="afm" class="form-control" placeholder="AFM"/>
					                           <form:errors path="afm" element="label" cssClass="control-label"/>
					                    	</div>   
					                    	<div class="form-group ${doyError }">
					                           <form:input type="text" path="doy" class="form-control" placeholder="DOY"/>
					                           <form:errors path="doy" element="label" cssClass="control-label"/>
					                    	</div>   
					                    	<div class="form-group ${mobilePhoneError }">
					                           <form:input type="text" path="mobilePhone" class="form-control" placeholder="Mobile Phone"/>
					                           <form:errors path="mobilePhone" element="label" cssClass="control-label"/>
					                    	</div>   
					                    	<div class="form-group ${workPhoneError }">
					                           <form:input type="text" path="workPhone" class="form-control" placeholder="Work Phone"/>
					                           <form:errors path="workPhone" element="label" cssClass="control-label"/>
					                    	</div>
					                    	<div class="form-group ${emailError }">
					                           <form:input type="email" path="email" class="form-control" placeholder="Email"/>
					                           <form:errors path="email" element="label" cssClass="control-label"/>
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