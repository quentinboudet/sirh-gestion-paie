<jsp:include page="../header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Creer bulletin de salaire</h1>
<form:form method='post' modelAttribute="bulletin" cssClass="d-flex container">
	<div class="form-group">
		<form:label path="periode.id">Période</form:label>
		<form:select path="periode.id" cssClass="form-control" >
			<c:forEach items="${listPeriode}" var="periode">
				<form:option  value="${periode.id}" ><c:out value="${periode.dateDebut} - ${periode.dateFin}"/></form:option>
			</c:forEach>
		</form:select>
	</div>
	<div class="form-group">
		<form:label path="remunerationEmploye.id">Matricule</form:label>
		<form:select path="remunerationEmploye.id" cssClass="form-control" >
			<form:options items="${listRe}" itemValue="id" itemLabel="matricule"></form:options>
		</form:select>
	</div>
	<div class="form-group">
		<form:label path="primeExceptionnelle">Prime Exceptionnelle</form:label>
		<form:input path="primeExceptionnelle" cssClass="form-control" type="text"/>
	</div>
	<form:button>Creer</form:button>
</form:form>
<jsp:include page="../footer.jsp" />