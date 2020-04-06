<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
	<h2>New cause</h2>
	<form:form modelAttribute="cause" class="form-horizontal" id="create-cause-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Name" name="name"/>
			<petclinic:inputField label="Target" name="target"/>
			<petclinic:inputField label="Organization" name="organization"/>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 com-sm-10">
				<button class="btn btn-default" type="submit">Create cause</button>
			</div>
			
		</div>
	
	</form:form>
</petclinic:layout>