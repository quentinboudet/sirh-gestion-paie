<jsp:include page="../header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<div class="container">
	<h1>Bulletin de salaire</h1>
	<a href='lister' classs="btn btn-dark">Retour</a>
	<div class="col-6">
		<h4>Entreprise</h4>
		<p>${bulletin.remunerationEmploye.entreprise.denomination}</p>
		<p>SIRET : ${bulletin.remunerationEmploye.entreprise.siret}</p>
	</div>
	<div class="col-6">
		<h4>Période</h4>
		<p>Du ${bulletin.periode.dateDebut.format(dateFormatter)} au
			${bulletin.periode.dateFin.format(dateFormatter)}</p>
	</div>
	<div>
		<p>Matricule : ${bulletin.remunerationEmploye.matricule}</p>
	</div>
	<h4>Salaire</h4>
	<table class="table">
		<tr>
			<th>Rubriques</th>
			<th>Base</th>
			<th>Taux Salarial</th>
			<th>Montant Salarial</th>
			<th>Taux patronal</th>
			<th>Cot. patronales</th>
		</tr>
		<tr>
			<td>Salaire de base</td>
			<td>${bulletin.remunerationEmploye.grade.nbHeuresBase}</td>
			<td>${bulletin.remunerationEmploye.grade.tauxBase}</td>
			<td>${calculBulletin.salaireDeBase}</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>Prime Except.</td>
			<td></td>
			<td></td>
			<td>${bulletin.primeExceptionnelle}</td>
			<td></td>
			<td></td>
		</tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		<tr>
		</tr>
		<tr>
			<td>Salaire Brut</td>
			<td></td>
			<td></td>
			<td>${calculBulletin.salaireBrut}</td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<h4>Cotisations</h4>
	<table class="table">
		<tr>
			<th>Rubriques</th>
			<th>Base</th>
			<th>Taux Salarial</th>
			<th>Montant Salarial</th>
			<th>Taux patronal</th>
			<th>Cot. patronales</th>
		</tr>
		<c:forEach items="${bulletin.remunerationEmploye.profilRemuneration.cotisationsImposables}" var="cI">
			<tr>
				<td>${cI.libelle}</td>
				<td>${calculBulletin.salaireBrut}</td>
				<td>${cI.tauxSalarial}</td>
				<td>xxxx</td>
				<td>${cI.tauxPatronal}</td>
				<td>xxxx</td>
			</tr>
		</c:forEach>
		<tr>
			<td>Total Retenue</td>
			<td></td>
			<td></td>
			<td>xx</td>
			<td></td>
			<td>${calculBulletin.totalCotisationsPatronales}</td>
		</tr>
	</table>
	<h4>NET Imposable : ${calculBulletin.netImposable}</h4>
	<table class="table">
		<tr>
			<th>Rubriques</th>
			<th>Base</th>
			<th>Taux Salarial</th>
			<th>Montant Salarial</th>
			<th>Taux patronal</th>
			<th>Cot. patronales</th>
		</tr>
		<c:forEach items="${bulletin.remunerationEmploye.profilRemuneration.cotisationsNonImposables}" var="cNI">
			<tr>
				<td>${cNI.libelle}</td>
				<td>${calculBulletin.salaireBrut}</td>
				<td>${cNI.tauxSalarial}</td>
				<td>xxxx</td>
				<td>${cNI.tauxPatronal}</td>
				<td></td>
			</tr>
		</c:forEach>
	</table>
	<h4>NET A PAYER : ${calculBulletin.netAPayer}</h4>
</div>

<jsp:include page="../footer.jsp" />