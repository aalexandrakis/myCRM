<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
       <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" style="margin-bottom: 0px;" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/index">myCRM</a>
            </div>
            <br>
            <!-- /.navbar-header -->
            <ul class="nav navbar-top-links navbar-right">
            	<li>
	            	<sec:authorize access="isAuthenticated()">
						<a href="<c:url value="j_spring_security_logout" />">Log out</a>
					</sec:authorize>
					<sec:authorize access="isAnonymous()">
						<a href="${pageContext.request.contextPath}/login">Log in</a>
					</sec:authorize>
                <!-- <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-out fa-fw"></i>Έξοδος</a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-in fa-fw"></i>Είσοδος</a>
                        </li>
                    </ul>-->
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                    	<li>
                    		 <h2>Menu</h2>
                    	</li>
                        <li>
                            <a class="${companyInfoActive }" href="${pageContext.request.contextPath}/companiesInfo">Companies</a>
                        </li>
                        <li>
                            <a class="${customerActive }" href="${pageContext.request.contextPath}/customers">Customers</a>
                        </li>
                        <li>
                            <a class="${supplierActive }" href="${pageContext.request.contextPath}/suppliers">Suppliers</a>
                        </li>
                        <li>
                            <a class="${invoiceActive }" href="${pageContext.request.contextPath}/invoices">Invoices</a>
                        </li>
                        <li>
                            <a class="${outcomeActive }" href="${pageContext.request.contextPath}/outcomes">Outcomes</a>
                        </li>
                        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>