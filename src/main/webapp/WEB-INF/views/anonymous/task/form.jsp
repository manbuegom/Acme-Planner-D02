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
	<acme:form-moment code="anonymous.tasks.label.start" path="start" />
	<acme:form-moment code="anonymous.tasks.label.end" path="end" />
	<acme:form-textbox code="anonymous.tasks.label.title" path="title" />
	<acme:form-textarea code="anonymous.tasks.label.text" path="text" />
	<acme:form-url code="anonymous.task.label.info" path="link" />
    <acme:form-checkbox code="anonymous.task.label.visibility" path="visibility" /> 


	<acme:form-submit code="anonymous.tasks.button.create"
		action="/anonymous/tasks/create" />
	<acme:form-return code="anonymous.tasks.button.return" />
</acme:form>
