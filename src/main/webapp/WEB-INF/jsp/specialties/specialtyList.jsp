<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="specialties">
    <h2>Specialties</h2>

    <table id="specialtiesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Specialty</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${specialties}" var="specialty">
            <tr>
                <td>
                	<c:out value="${specialty.name}"/>
                </td>
                
                <td>
                	<a href="<spring:url value="/specialties/${specialty.id}/delete" htmlEscape="true" />">Delete</a> /
                	<a href="<spring:url value="/specialties/${specialty.id}/edit" htmlEscape="true" />">Edit</a>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
	<br/>
    <a class="btn btn-default" href='<spring:url value="/specialties/new" htmlEscape="true"/>'>Add Specialty</a>
    <br/>
    <br/>
    
    
    
    
</petclinic:layout>