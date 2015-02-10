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
		                        <label>Select Company</label>
		                    </div>
		                </div>
		                <!-- /.panel-heading -->
		                <div class="panel-body ">
		                	 <div class="row">
			                        <form:form modelAttribute="companyInfo" action="${pageContext.request.contextPath}/selectCompany" method="POST">
			                        	<form:errors path="*" element="div" cssClass="alert alert-danger"/>
		                        	    <div class="col-lg-3">
					                        <div class="input-group custom-search-form">
					                           <form:input type="text" path="name" class="form-control" placeholder="Name..."/>
					                           <span class="input-group-btn">
						                           <button class="btn btn-default" type="submit">
						                               <i class="fa fa-search"></i>
						                           </button>
					                       	   </span>
					                    	</div>
				                    	</div>
				                     	<div class="col-lg-3">
					                        <div class="input-group custom-search-form">
					                           <form:input type="text" path="afm" class="form-control" placeholder="VAT..." max="9"/>
					                           <span class="input-group-btn">
						                           <button class="btn btn-default" type="submit">
						                               <i class="fa fa-search"></i>
						                           </button>
					                       	   </span>
					                    	</div>
				                    	</div>
				                    </form:form>
                    		 </div>
		                    <div class="table-responsive">
		                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
		                            <thead>
		                            <tr>
		                                <th>Code</th>
		                                <th>Name</th>
		                                <th>Profession</th>
		                                <th>Phone</th>
		                                <th>VAT</th>
		                                <th>DOY</th>
		                                <th>Address</th>
		                            </tr>
		                            </thead>
		                                <tbody>
		                                	<c:forEach var="companyInfo" items="${companiesInfo }">
		                                	    <tr class="gradeA">
		                                            <td><a href="${pageContext.request.contextPath}/invoice/company/${companyInfo.companyId}">${companyInfo.companyId}</a></td>
		                                            <td><a href="${pageContext.request.contextPath}/invoice/company/${companyInfo.companyId}">${companyInfo.name}</a></td>
		                                            <td>${companyInfo.busDesc}</td>
		                                            <td>${companyInfo.workPhone}</td>
		                                            <td>${companyInfo.afm}</td>
		                                            <td>${companyInfo.doy}</td>
		                                            <td>${companyInfo.address}</td>
		                                        </tr>
		                                    </c:forEach>    
	                                    </tbody>
		                        </table>
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