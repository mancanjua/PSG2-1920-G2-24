<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
 
 
<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#startDate").datepicker({dateFormat: 'yy/mm/dd'});
                $("#endDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${hotel['new']}">New </c:if>Book</h2>
 
        <b>Pet</b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Birth Date</th>
                <th>Type</th>
                <th>Owner</th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${hotel.pet.name}"/></td>
                <td><petclinic:localDate date="${hotel.pet.birthDate}" pattern="yyyy/MM/dd"/></td>
                <td><c:out value="${hotel.pet.type.name}"/></td>
                <td><c:out value="${hotel.pet.owner.firstName} ${hotel.pet.owner.lastName}"/></td>
            </tr>
        </table>
 
        <form:form modelAttribute="hotel" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Description" name="description"/>
                <petclinic:inputField label="Start Book Date" name="startDate"/>
                <petclinic:inputField label="End Book Date" name="endDate"/>
            </div>
 
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${hotel.pet.id}"/>
                    <button class="btn btn-default" type="submit">Add Book</button>
                </div>
            </div>
        </form:form>
 
        <br/>
        <b>Previous Books</b>
        <table class="table table-striped">
            <tr>
                <th>Start Book Date</th>
                <th>End Book Date</th>
                <th>Description</th>
            </tr>
            <c:forEach var="hotel" items="${hotel.pet.hotels}">
                <c:if test="${!hotel['new']}">
                    <tr>
                        <td><petclinic:localDate date="${hotel.startDate}" pattern="yyyy/MM/dd"/></td>
                        <td><petclinic:localDate date="${hotel.endDate}" pattern="yyyy/MM/dd"/></td>
                        <td><c:out value="${hotel.description}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </jsp:body>
 
</petclinic:layout>