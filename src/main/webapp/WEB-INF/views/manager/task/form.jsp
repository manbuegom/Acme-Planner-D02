<%--
- form.jsp
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
    <acme:form-moment code="manager.task.label.start" path="start" />
    <acme:form-moment code="manager.task.label.end" path="end" />
    <acme:form-textbox code="manager.task.label.title" path="title" />
    <acme:form-textarea code="manager.task.label.text" path="text" />
    <acme:form-url code="manager.task.label.info" path="link" />
    <acme:form-checkbox code="manager.task.label.visibility" path="visibility" /> 

	<acme:form-submit test="${command == 'create'}" 
	code="manager.task.button.create" 
	action="/manager/task/create"/>
<%--	
	<acme:form-submit test="${command == 'update'}" code="manager.task.button.update" action="/manager/task/update"/>
--%>	
    <acme:form-return code="manager.task.button.return" />
</acme:form>