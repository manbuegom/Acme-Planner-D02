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

<acme:list>
<!--  no sabemos aún qué queremos mostrar o no pero cuando en el command metes la accion que se esté realizando muestra o no la columna -->
<!--	<jstl:if test="${command == 'show'}"> -->
<!--		</jstl:if> -->
	<acme:list-column  code="anonymous.task.label.title" path="title" width="80%"/>
		
</acme:list>
<acme:form>
<acme:form-return code="anonymous.task.label.details" action= "/anonymous/task/show"/>
</acme:form>


