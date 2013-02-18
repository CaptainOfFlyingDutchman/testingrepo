<!-- 
This modal is used to show a button that initiates the registration action.
-->

<!-- Button to trigger modal if Javascript is available -->
    <button href="#ForgotModal" class="btn btn-block btn-success" role="button" data-toggle="modal" title="${message(code: 'security.forgot.label', default: 'Forgot Password')}">
<%--    	<i class="icon-signin"></i> --%>
    	<g:message code="security.forgot.label" default="Forgot Password"/>
    </button>

	<!-- NOTE: the renderDialog MUST be placed outside the NavBar (at least for Bootstrap 2.1.1): see bottom of main.gsp -->
<%--	<g:render template="/_common/modals/registerDialog" model="[item: item]"/>--%>
