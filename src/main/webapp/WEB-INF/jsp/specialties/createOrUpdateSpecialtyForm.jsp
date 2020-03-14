<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="specialty">
    <jsp:body>
        <h2><c:if test="${specialty['new']}">New </c:if>Specialty</h2>
   

        <form:form modelAttribute="specialty" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Name" name="name"/>
            </div>

            <div class="form-group">
            <input type="hidden" name="id" value="${specialty.id}"/>
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
	                    <c:when test="${specialty['new']}">
	                        <button class="btn btn-default" type="submit">Add Specialty</button>
	                    </c:when>
	                    <c:otherwise>
	                        <button class="btn btn-default" type="submit">Update Specialty</button>
	                    </c:otherwise>
	                </c:choose>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>