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
	<acme:form-textbox code="anonymous.shout.label.author" path="author" />
	<acme:form-textbox code="anonymous.shout.label.moment" path="moment" />
	<acme:form-textbox code="anonymous.shout.label.text" path="text" />
	<acme:form-textbox code="anonymous.shout.label.info" path="info" />


	<acme:form-submit code="anonymous.shout.button.create"
		action="/anonymous/shouts/create" />
	<acme:form-return code="anonymous.shout.button.return" />
</acme:form>
