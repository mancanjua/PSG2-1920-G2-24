<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

    <h2>Cause Information</h2>

    <table class="table table-striped">
		<tr>
			<th>Name</th>
			<td><b><c:out value="${cause.name}" /></b></td>
		</tr>
		<tr>
			<th>Target</th>
			<td><c:out value="${cause.target}" /></td>
		</tr>
		<tr>
			<th>Present Bugdet</th>
			<td><c:out value="${cause.getPresentBudget()}" /></td>
		</tr>
		<tr>
			<th>Organization</th>
			<td><c:out value="${cause.organization}" /></td>
		</tr>
	</table>
    
    <h2>List of donations</h2>
    
    <table id="donationsTable" class="table table-striped">
		<thead>
			<tr>
				<th>Date</th>
				<th>Amount</th>
				<th>Client</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cause.donations}" var="donation">
				<tr>
					<td><petclinic:localDate date="${donation.date}" pattern="dd/MM/yyyy" /></td>
					<td><c:out value="${donation.amount}"/></td>
					<td><c:out value="${donation.owner}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:choose>
		<c:when test="${cause.target>cause.getPresentBudget()}">
				<spring:url value="/causes/{causeId}/donations/new" var="newDonationUrl">
					<spring:param name="causeId" value="${cause.id}" />
				</spring:url> 
				<a class="btn btn-default" href='${fn:escapeXml(newDonationUrl)}'>New Donation</a>
		</c:when>
		<c:otherwise>
			<p>This cause is closed.</p>
		</c:otherwise>
	</c:choose>
	
</petclinic:layout>