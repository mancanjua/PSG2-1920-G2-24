<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="causes">
	<h2>Causes</h2>

	<table id="causesTable" class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Present Budget</th>
				<th>Target</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
			<tbody>
			<c:forEach items="${causes}" var="cause">
				<tr>
					<td><c:out value="${cause.name}" /></td>
					<td><c:out value="${cause.getPresentBudget()}" /></td>
					<td><c:out value="${cause.target}" /></td>
					
					<c:choose> <c:when test="${cause.target>cause.getPresentBudget()}">
						<td><spring:url value="/causes/{causeId}/donations/new" var="newDonationUrl">
								<spring:param name="causeId" value="${cause.id}" />
							</spring:url> <a href="${fn:escapeXml(newDonationUrl)}" class="">New Donation</a></td>
					</c:when>
					<c:otherwise><td />
					</c:otherwise>
					</c:choose>
				
					
				</tr>

			</c:forEach>
		</tbody>
	</table>
	</br>
	<a class="btn btn-default" href='<spring:url value="/causes/new" htmlEscape="true"/>'>Create New Cause</a>
	
	
	
</petclinic:layout>