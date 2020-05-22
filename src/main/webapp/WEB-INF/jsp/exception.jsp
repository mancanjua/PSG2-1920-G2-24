<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/pets.png" var="petsImage"/>
    <img src="${petsImage}"/>

    <h2>Something happened...if you don't know how to solve this problem, you can contact us on twitter, our e-mail or call us at phone number: xxx-xxx-xxx</h2>

    <p>${exception.message}</p>

</petclinic:layout>
