<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="home">
   <div align="center"><h1 style="font: oblique bold 250% cursive;"><fmt:message key="welcome"/></h1>
    </div>  
      
    <div class="row">
        <div class="col-md-12" style="width: 580px;height: 390px;margin-left: -290px;left: 50%; position: absolute;">
            <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}"/>
        </div>
    </div>
</petclinic:layout>
