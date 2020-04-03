<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="donations">
	<h2>New donation</h2>
	<form:form modelAttribute="donation" class="form-horizontal" id="add-donation-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Amount" name="amount"/>
			<petclinic:selectField label="Owner " name="owner" names="${owners}" size="4"/>
			<petclinic:hidden name="date"/>
			<petclinic:hidden name="cause"/>
				
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 com-sm-10">
				<button class="btn btn-default" type="submit">Add Donation</button>
			</div>
		</div>
	</form:form>
</petclinic:layout>