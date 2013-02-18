<div class="modal hide" id="ForgotModal">

	<g:form controller="register" action="forgotPassword" class="form-horizontal" method="post" name="register_form">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">x</button>
		<h3><g:message code="security.forgot.title" default="Have you forgot your password?"/></h3>
	</div>
	<div class="modal-body">

		<div class="control-group">
			<label class="control-label" for="username">${message(code: 'security.email.label', default: 'Email')}</label>
			<div class="controls">
				<input type="text" class="span3" name="username" id="username" placeholder="${message(code: 'security.email.label', default: 'Email')}">
			</div>
		</div>

	</div>
	<div class="modal-footer">
		<button type="submit" class="btn btn-primary"><g:message code="security.forgot.label" default="Get Password"/></button>
	</div>
	</g:form>

</div>
