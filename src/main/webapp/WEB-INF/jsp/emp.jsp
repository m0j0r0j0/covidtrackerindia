<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<c:forEach var="list" items="${empList}">
		<tr>
			<td>${list.id}</td>
			<td>${list.name}</td>
		</tr>	
	</c:forEach>
</html>