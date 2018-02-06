<jsp:include page="../header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Ajouter Employe</h1>
<p>Préfixe Matricule : ${prefixMatricule}</p>
<form:form method='post' modelAttribute="employe" cssClass="d-flex container">
	<div class="form-group">
		<form:label path="matricule">Matricule</form:label>
		<form:input path="matricule" cssClass="form-control" type="text" />
	</div>
	<div class="form-group">
		<form:label path="entreprise.id">Entreprise</form:label>
		<form:select path="entreprise.id" cssClass="form-control" >
			<form:options items="${entreprises}" itemValue="id" itemLabel="denomination"></form:options>
		</form:select>
	</div>
	<div class="form-group">
		<form:label path="profilRemuneration.id">Profil</form:label>
		<form:select path="profilRemuneration.id" cssClass="form-control" >
			<form:options items="${profils}" itemValue="id" itemLabel="code"></form:options>
		</form:select>
	</div>
	<div class="form-group">
		<form:label path="grade.id" cssClass="col-2">Grade</form:label>
		<form:select path="grade.id" cssClass="form-control col-10">

			<form:options items="${grades}" itemValue="id" itemLabel="code"></form:options>
		</form:select>
		
	</div>
	<form:button>Ajouter</form:button>
</form:form>
<jsp:include page="../footer.jsp" />