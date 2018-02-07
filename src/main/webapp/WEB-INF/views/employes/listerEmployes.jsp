<jsp:include page="../header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<h1>Liste des employés</h1>
<a href='creer' classs="btn btn-dark">Ajouter un employé</a>
<table class="table">
  <tr>
    <th>Date/heure création</th>
    <th>Matricule</th>
    <th>Grade</th>
  </tr>
  <c:forEach items="${listRe}" var="re">
  <tr>
    <td>${re.dateCreation.format(dateTimeFormatter)}</td>
    <td>${re.matricule}</td>
    <td>${re.grade.code}</td>
  </tr>
  </c:forEach>
</table>

<jsp:include page="../footer.jsp" />