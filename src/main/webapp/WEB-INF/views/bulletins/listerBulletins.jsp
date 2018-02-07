<jsp:include page="../header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<h1>Liste des Bulletins de salaire</h1>
<a href='creer' classs="btn btn-dark">Creer un Bulletin</a>
<table class="table">
  <tr>
    <th>Date/heure création</th>
    <th>Période</th>
    <th>Matricule</th>
    <th>Salaire brut</th>
    <th>Net Imposable</th>
    <th>Net à payer</th>
    <th>Action</th>
  </tr>
  <c:forEach items="${listBulletin}" var="bulletin">
  <tr>
    <td>${bulletin.dateCreation.format(dateTimeFormatter)}</td>
    <td>${bulletin.periode.dateDebut.format(dateFormatter)} - ${bulletin.periode.dateFin.format(dateFormatter)}</td>
    <td>${bulletin.remunerationEmploye.matricule}</td>
    <td>${listCalculBulletin[bulletin.id-1].salaireBrut}</td>
    <td>${listCalculBulletin[bulletin.id-1].netImposable}</td>
    <td>${listCalculBulletin[bulletin.id-1].netAPayer}</td>
    <td><a href="visualiser?id=${bulletin.id}">Visualiser</a></td>
  </tr>
  </c:forEach>
</table>

<jsp:include page="../footer.jsp" />