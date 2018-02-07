<jsp:include page="./header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Connexion</h1>
<!-- Spring Security s'attend aux paramètres "username" et "password" -->
<form method="post">
	<input name="username">
	<input name="password">
	<input type="submit" value="Se connecter">
	<sec:csrfInput/>
</form>
<!-- en cas d'erreur un paramètre "error" est créé par Spring Security -->
<c:if test="${param.error !=null}"> Erreur d'authentification </c:if>

<jsp:include page="./footer.jsp" />