import com.intelligrape.linksharing.User

//package linksharing

/**
 * RegisterController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class RegisterController {

//	static scaffold = true

    def springSecurityService

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
}
