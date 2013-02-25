package com.intelligrape.linksharing

/**
 * ForgotPasswordToken
 * A domain class describes the data object and it's mapping to the database
 */
// TODO-MANVENDRA - Remove unnecessary comments
class ForgotPasswordToken {

    String token
    User user

	static constraints = {
    }
	
}
