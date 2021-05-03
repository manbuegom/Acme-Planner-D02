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


	<jstl:if test="${command == 'update'}">

		<acme:form-textarea code="administrator.spam.label.words"
			path="palabras" readonly="true" />

		<acme:form-textbox code="administrator.spam.label.words"
			path="newword" />

		<acme:form-submit code="administrator.spam.label.update"
			action="/administrator/spam/update" />

		<acme:form-submit code="administrator.spam.label.remove"
			action="/administrator/spam/spam_remove_word" />

	</jstl:if>

	<jstl:if test="${command == 'spam_remove_word'}">
		<acme:form-textarea code="administrator.spam.label.words"
			path="palabras" readonly="true" />

		<acme:form-textbox code="administrator.spam.label.words"
			path="newword" />

		<acme:form-submit code="administrator.spam.label.update"
			action="/administrator/spam/update" />

		<acme:form-submit code="administrator.spam.label.remove"
			action="/administrator/spam/spam_remove_word" />

	</jstl:if>

	<jstl:if test="${command == 'update_threshold'}">

		<acme:form-double code="administrator.spam.label.threshold"
			path="threshold" />

		<acme:form-submit code="administrator.spam.label.update.threshold"
			action="/administrator/spam/update_threshold" />


	</jstl:if>



	<acme:form-return code="administrator.spam.button.return" />

</acme:form>