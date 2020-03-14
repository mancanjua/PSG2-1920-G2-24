<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">

    <h2>Veterinarian Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${vet.firstName} ${vet.lastName}"/></b></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>Specialties</th>
            <td><c:forEach var="specialty" items="${vet.specialties}">
                   <b><c:out value="${specialty}"/></b><br>
            </c:forEach>
            <c:if test="${vet.nrOfSpecialties == 0}">none</c:if></td>
            
            <td><c:forEach var="specialty" items="${vet.specialties}">
                        <spring:url value="/vets/{vetId}/removeSpecialty/{specialtyId}" var="removeSpecialtyUrl">
                            <spring:param name="vetId" value="${vet.id}"/>
                            <spring:param name="specialtyId" value="${specialty.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(removeSpecialtyUrl)}">Delete</a><br>
            </c:forEach></td>
       </tr>
               

    </table>

    <spring:url value="{vetId}/edit" var="editUrl">
        <spring:param name="vetId" value="${vet.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Vet</a>
    <spring:url value="{vetId}/delete" var="deleteUrl">
        <spring:param name="vetId" value="${vet.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete Vet</a>
 

  
    
</petclinic:layout>