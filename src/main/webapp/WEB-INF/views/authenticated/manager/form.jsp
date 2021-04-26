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
    <acme:form-moment code="authenticated.manager.task.label.start" path="start" />
    <acme:form-moment code="authenticated.manager.task.label.end" path="end" />
    <acme:form-textbox code="authenticated.manager.task.label.title" path="title" />
    <acme:form-textarea code="authenticated.manager.task.label.text" path="text" />
    <acme:form-url code="authenticated.task.manager.label.info" path="link" />
    <acme:form-checkbox code="authenticated.manager.task.label.visibility" path="visibility" /> 

	<acme:form-submit test="${command == 'create'}" code="authenticated.manager.task.form.button.create" action="/authenticated/manager/task/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.manager.task.form.button.update" action="/authenticated/manager/task/update"/>
    <acme:form-return code="authenticated.manager.task.button.return" />
</acme:form>