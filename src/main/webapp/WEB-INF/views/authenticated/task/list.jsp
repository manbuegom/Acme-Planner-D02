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

<acme:list readonly="true">
<!--  no sabemos aún qué queremos mostrar o no pero cuando en el command metes la accion que se esté realizando muestra o no la columna -->
<!--    <jstl:if test="${command == 'show'}"> -->
<!--        </jstl:if> -->
    <acme:list-column  code="authenticated.task.label.title" path="title" width="20%"/>
    <acme:list-column code="authenticated.task.label.start" path="start" width="20%"/>
    <acme:list-column code="authenticated.task.label.end" path="end" width="20%"/>
    <acme:list-column code="authenticated.task.label.text" path="text" width="20%"/>
    <acme:list-column code="authenticated.task.label.link" path="link" width="20%"/>
    
    
        

</acme:list>