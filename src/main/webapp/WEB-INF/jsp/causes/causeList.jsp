<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<petclinic:layout pageName="causes">
    <h2>Causes</h2>

    <table id="causesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Causa</th>
            
        </tr>
        </thead>
 <tbody>
        <c:forEach items="${causes.causeList}" var="cause">
            <tr>
                <td>
                	<spring:url value="/causes/{causeId}" var="causeUrl">
                        <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(causeUrl)}"><c:out value="${cause.name}"/></a>
                </td>
                
                       
            </tr>
        </c:forEach>
        </tbody>
    </table>
	<br/>
    <br/>
      
    
    
    
</petclinic:layout>