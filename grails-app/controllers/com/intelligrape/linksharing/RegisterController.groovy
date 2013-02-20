package com.intelligrape.linksharing

import org.bouncycastle.bcpg.ElGamalSecretBCPGKey
import org.codehaus.groovy.grails.web.mapping.LinkGenerator

//package linksharing
/**
 * com.intelligrape.linksharing.RegisterController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class RegisterController {

    def springSecurityService
    def sendAsynchronousMailService

    LinkGenerator grailsLinkGenerator

    static defaultAction = "resetPassword"

    /*def index = {
        [user: params.user]
    }*/

    def register(RegisterCommand command) {
        User user
        if (command.validate()) {
            user = command.registerUser()
            user.save(flush: true, failOnError: true)

            springSecurityService.reauthenticate(user.username, user.password)
        } else {
            [command: command]
        }
    }

    def forgotPassword(CheckEmailCommand command) {
        if (command.validate()) {

            User user = User.findByUsername(command.username)

            if (user) {
                String uuid = UUID.randomUUID().toString()

                ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(token: uuid, user: user)
                forgotPasswordToken.save(flush: true, failOnError: true)

                String subjectText = "Your password"

                String url = grailsLinkGenerator.link(controller: 'register', action: 'resetPassword', absolute: true,
                        base: "http://linksharing-manvendra.qa3.intelligrape.net",
                        params: [forgotPasswordToken: forgotPasswordToken.token])

                String template = '/register/forgotPasswordBody'

                Map templateModel = [user: user, url: url]

                sendAsynchronousMailService.sendAsynchronousMail(user.username, subjectText, template, templateModel)
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
            springSecurityService.reauthenticate(user.username, user.password)

        } else {
            [command: command]
        }
    }
}
