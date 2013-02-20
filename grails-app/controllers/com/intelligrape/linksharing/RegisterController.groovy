package com.intelligrape.linksharing

import com.intelligrape.linksharing.*

//package linksharing
// TODO :  remove all unnecessary comments, no one gets paid for LOC these days.
/**
 * com.intelligrape.linksharing.RegisterController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class RegisterController {

//	static scaffold = true


    def springSecurityService
    def sendAsynchronousEmailService

    static defaultAction = "index"



    def index = {
        [user: params.user]
    }

    def register(RegisterCommand command) {
        User user
        if (command.validate()) {
            user = command.registerUser()
            user.save(flush: true, failOnError: true)

            springSecurityService.reauthenticate(user.username, user.password)
//            redirect(url: "/LinkSharing/j_spring_security_check", params: [j_username:user.username, j_password:user.password])
//            redirect(controller: "login", action: "auth", params: [j_username:user.username, j_password:user.password])
        } else {
//            flash.message = "Errors are present in the registration box."

            [command: command]
        }
    }

    def forgotPassword(CheckEmailCommand command) {
        if (command.validate()) {
            User user = User.findByUsername(command.username)
            String uuid = UUID.randomUUID().toString()

            ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(token: uuid, user: user)
            forgotPasswordToken.save(flush: true, failOnError: true)

            println "====================================="
            println forgotPasswordToken.token
            println "====================================="


            sendAsynchronousEmailService.sendAsyncEmail(user, forgotPasswordToken)
        } else {
            [command: command]
        }
    }

    def resetPassword() {
        if (!ForgotPasswordToken.findByToken(params.forgotPasswordToken)) {
            render view: "linkExpired"
        } else {
            User tokenUser = ForgotPasswordToken.findByToken(params.forgotPasswordToken).user
            [tokenUser: tokenUser]
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
