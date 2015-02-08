<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="commons" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<commons:header title="ΔΕΔΔΗΕ Web App"/>
<body>
	<div id="wrapper">
		<!--<jsp:include page="navigation.jsp"/>-->
        <div id="page-wrapper">
			<div class="row">
                <div class="col-lg-6">
                	<div class="alert alert-danger">
                    	<h1 class="page-header">Απαγορεύεται η πρόσβαση</h1>
                   	</div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-8">
      	    		<img src="${pageContext.request.contextPath}/images/stop.jpg"/>
            	</div>
            </div>
			
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->

  <commons:footer/>

</body>

</html>