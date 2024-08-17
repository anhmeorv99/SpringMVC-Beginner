<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<table class="table table-striped">
		<caption><spring:message code="todo.caption" /></caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Created At</th>
				<th>Updated At</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${reports}" var="report">
				<tr>
					<td>${report.id}</td>
					<td>${report.name}</td>
					<td>${report.description}</td>
					<td>${report.createdAt}</td>
					<td>${report.updatedAt}</td>
					<td><a type="button" class="btn btn-primary"
						href="/update-report?id=${report.id}">Edit</a> <a type="button"
						class="btn btn-warning" href="/delete-report?id=${report.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a type="button" class="btn btn-success" href="/report">Add</a>
	</div>
</div>
<%@ include file="common/footer.jspf"%>