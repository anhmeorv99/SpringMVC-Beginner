<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<form:form method="post" commandName="report">
		<form:hidden path="id" />
		
		<fieldset class="form-group">
			<form:label path="name">Name</form:label>
			<form:input path="name" type="text" class="form-control"
				required="required" />
			<form:errors path="name" cssClass="text-warning" />
		</fieldset>
		
		<fieldset class="form-group">
			<form:label path="description">Description</form:label>
			<form:input path="description" type="text" class="form-control"
				required="required" />
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>
		
		<button type="submit" class="btn btn-success">Submit</button>
		
	</form:form>
</div>

<%@ include file="common/footer.jspf"%>
