import com.intelligrape.linksharing.User

//package linksharing

/**
 * RegisterController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class RegisterController {

//	static scaffold = true

    static defaultAction = "index"

    def index = {
        [user: params.user]
    }

    def register(RegisterCommand command) {
        User user

        if (command.validate()) {
            println "validation succeed"
            user = command.registerUser()
            user.save(flush: true, failOnError: true)

//            [user:user]
        } else {
            println "validation failed"
//            flash.message = message(code: "register.confirm.doesn't.not.match.with.password",
//                    default: "Confirm password doesn't match with password")

//            redirect(controller: "register", action: "index", params: [user: User])
//            render "error"
        }
    }
}
