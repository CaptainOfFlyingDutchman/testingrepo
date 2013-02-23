package com.intelligrape.linksharing

import org.bouncycastle.bcpg.ElGamalSecretBCPGKey
import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class RegisterController {

    def registerService

    static defaultAction = "resetPassword"

    def register(RegisterCommand command) {
        User user
        if (command.validate()) {
            user = command.registerUser()
            user.save(flush: true, failOnError: true)
            registerService.reauthenticate(user.username, user.password)
        } else {
            [command: command]
        }
    }

    def forgotPassword(CheckEmailCommand command) {
        if (command.validate()) {
            User user = User.findByUsername(command.username)
            if (user) {
                ForgotPasswordToken forgotPasswordToken = registerService.generateForgotPasswordToken(user)
                registerService.sendForgotPasswordMail(user, forgotPasswordToken)
            } else {
                flash.message = "The specified user doesn't exist in the system."
            }
        } else {
            [command: command]
        }
    }

    def resetPassword() {
        try {
            ForgotPasswordToken forgotPasswordToken = ForgotPasswordToken.findByToken(params.forgotPasswordToken)
            if (!forgotPasswordToken) {
                render view: "linkExpired"
            } else {
                User tokenUser = forgotPasswordToken.user
                [tokenUser: tokenUser]
            }
        } catch (MissingMethodException ex) {
            String message = "Sorry the page you're looking for doesn't exist."
            [exceptionMessage: message]
        }
    }

    def changePasswordAndLoginWithNewPassword(PasswordCheckCommand command) {
        if (command.validate()) {
            User user = command.user
            user.password = command.password
            user.save(flush: true, failOnError: true)
            ForgotPasswordToken.findByUser(user).delete(flush: true)
            registerService.reauthenticate(user.username, user.password)
        } else {
            [command: command]
        }
    }
}
