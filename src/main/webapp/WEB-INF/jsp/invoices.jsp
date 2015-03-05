<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="commons" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<commons:header title="Invoices - myCRM"/>
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
		                        <label>Invoices</label>
		                        <div class="row">
			                        <form:form modelAttribute="invoice" action="${pageContext.request.contextPath}/invoices" method="POST">
											<div class="col-lg-12">
												<div class="col-lg-2">
													<div class="form-group ${dateFromError }">
														<label class="control-label">Date From</label>
								                        <div class="input-group date" id="invoiceDateFrom">
								                            <form:input path="dateFrom" name="dateFrom" type="text" class="form-control"/>
								                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar" style="color:white"></i></span>
								                        </div>
								                        <form:errors path="dateFrom" cssClass="control-label"/>
							                        </div>
						                        </div>
					                            <div class="col-lg-2">
						                            <div class="form-group ${dateToError }">
							                    		<label class="control-label">Date To</label>
								                        <div class="input-group date" id="invoiceDateTo">
								                            <form:input path="dateTo" name="dateTo" type="text" class="form-control"/>
								                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar" style="color:white"></i></span>
								                        </div>
								                        <form:errors path="dateTo" cssClass="control-label"/>
							                        </div>
						                        </div>
										   </div>
										   <div class="col-lg-12">
									   		   <div class="col-lg-1" >
											   		<form:input type="text" path="companyInfo.companyId" class="form-control" readonly="true"/>
											   </div>
											   <div class="col-lg-4">
							                        <div class="input-group custom-search-form">
							                           <form:input type="text" path="companyInfo.name" class="form-control" placeholder="Company..." readonly="true"/>
							                           <span class="input-group-btn">
								                           <!-- <button class="btn btn-default" type="submit">
								                               <i class="fa fa-search"></i>
								                           </button>-->
								                            <a href="${pageContext.request.contextPath}/selectCompany/invoices" class="btn btn-default"><i class="fa fa-search"></i></a>
							                       	   </span>
							                    	</div>
						                        </div>
						                        <div class="col-lg-1" >
											   		<form:input type="text" path="customer.customerId" class="form-control" readonly="true"/>
											   </div>
												<div class="col-lg-4">
												    <div class="input-group custom-search-form">
							                           <form:input type="text" path="customer.customerName" class="form-control" placeholder="Customer..." readonly="true"/>
							                           <span class="input-group-btn">
								                           <!-- <button class="btn btn-default" type="submit">
								                               <i class="fa fa-search"></i>
								                           </button>-->
								                           <a href="${pageContext.request.contextPath}/selectCustomer/invoices" class="btn btn-default"><i class="fa fa-search"></i></a>
							                       	   </span>
							                    	</div>
						                        </div>
										   </div>
										   <div class="col-lg-12"  style="margin-left:12px">
										   	  <br>
				                    		  <input type="submit" class="btn btn-primary" name="search" value="Seacrh"/>
				                    		  <a href="${pageContext.request.contextPath}/invoice" class="btn btn-primary">New Invoice</a>
				                    		  <input type="submit" class="btn btn-primary" name="clear" value="Clear Filters"/>
			                    	       </div>
			                    	   </form:form>
		                    	</div>
		                    </div>
		                </div>
		                <!-- /.panel-heading -->
		                <div class="panel-body ">
		                    <div class="table-responsive">
		                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
		                            <thead>
		                            <tr>
		                                <th>Αρ.Τιμ.</th>
		                                <th>Ημερομηνία</th>
		                                <th>Εταιρεία</th>
		                                <th>Πελάτης</th>
		                                <th>Ποσό</th>
		                                <th>Φ.Π.Α</th>
		                                <th>Σύνολο</th>
		                            </tr>
		                            </thead>
		                                <tbody>
		                                	<c:forEach var="invoice" items="${invoices }">
		                                	    <tr class="gradeA">
		                                            <td><a href="${pageContext.request.contextPath}/invoicePdf/${invoice.invoiceId}">${invoice.invoiceId}</a></td>
		                                            <td>${invoice.invoiceDateString}</td>
		                                            <td>${invoice.companyInfo.name }</td>
		                                            <td>${invoice.customer.customerName }</td>
		                                            <td>${invoice.amount}</td>
		                                            <td>${invoice.fpaAmount}</td>
		                                            <td>${invoice.gross}</td>
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
    <script>
	$('#invoiceDateFrom').datepicker({
		format: "dd/mm/yyyy",
		autoclose: true 
	});
    </script>
    <script>
	 $('#invoiceDateTo').datepicker({
		format: "dd/mm/yyyy",
		autoclose: true 
	 });
    </script>
  
</body>

</html>