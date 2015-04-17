<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="commons" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<commons:header title="Login - myCRM-PROD-gitlab"/>

<body>
	<div id="wrapper">
		<jsp:include page="navigation.jsp"/>
		<div id="page-wrapper">
			<div class="row" id="login-box">
				<div class="col-md-4 col-md-offset-4">
	                <div class="login-panel panel panel-default">
	                    <div class="panel-heading">
	                        <h3 class="panel-title">Log in</h3>
	                    </div>
	                    <div class="panel-body">
	                    	<!-- <c:url value='j_spring_security_check' /> -->
	                        <form:form role="form" modelAttribute="user" action="${pageContext.request.contextPath}/login" method="post">
	                        	<form:errors path="*" element="div" cssClass="alert alert-danger"/>
	                        	<c:if test="${param.logout != null}">
									<div class="alert alert-success">
										You have been logged out successfully
									</div>
								</c:if>
	                        	<fieldset>
	                                <div class="form-group">
	                                    <form:input class="form-control" path="j_username" placeholder="User Name" name="j_username" type="text" autofocus=""/>
	                                </div>
	                                <div class="form-group">
                                	    <form:input class="form-control" path="j_password" placeholder="Password" name="j_password" type="password" value=""/>
	                                </div>
	                                <!--  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />-->
	                                <!-- Change this to a button or input when using this as a form -->
	                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Είσοδος"/>	                                
	                            </fieldset>
	                        </form:form>
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