<%--
- list.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-integer code="administrator.dashboard.label.numberOfPublicTasks" path="numberOfPublicTasks" />
	<acme:form-integer code="administrator.dashboard.label.numberOfPrivateTasks" path="numberOfPrivateTasks" />
	<acme:form-integer code="administrator.dashboard.label.numberOfFinishedTasks" path="numberOfFinishedTasks" />
</acme:form>

<%-- <acme:list readonly="true">
	<acme:list-column
		code="administrator.dashboard.label.numberOfFinishedTasks"
		path="numberOfFinishedTasks" width="15%" />
	<acme:list-column
		code="administrator.dashboard.label.numberOfPrivateTasks"
		path="numberOfPrivateTasks" width="15%" />
	<acme:list-column
		code="administrator.dashboard.label.numberOfPublicTasks"
		path="numberOfPublicTasks" width="70%" />

</acme:list> --%>



