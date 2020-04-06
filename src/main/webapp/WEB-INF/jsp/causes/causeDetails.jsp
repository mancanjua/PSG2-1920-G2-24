<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="cause">

  <b>Cause</b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Target</th>
                <th>Present Budget</th>
                <th>Organization</th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${cause.name}"/></td>
                <td><c:out value="${cause.description}" /></td>
                <td><c:out value="${cause.target}"/></td>
                <td><c:out value="${cause.getPresentBudget()}"/></td>
                <td><c:out value="${cause.organization}"/></td>
            </tr>
        </table>

	
</petclinic:layout>