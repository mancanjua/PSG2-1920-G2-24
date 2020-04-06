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

    
</petclinic:layout>


