import com.intelligrape.linksharing.User
import grails.validation.Validateable
import org.springframework.web.method.annotation.InitBinderDataBinderFactory
//import sun.plugin2.os.windows.FLASHWINFO

import javax.security.auth.callback.ConfirmationCallback

/**
 * Created with IntelliJ IDEA.
 * User: manvendra
 * Date: 17/2/13
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */

//# My messages
//register.confirm.doesn't.not.match.with.password = Confirm password doesn't match with password
//email.already.exist.in.the.database = Email is already present in the database

@Validateable
class RegisterCommand {
    String username
    String firstName
    String lastName
    String password
    String confirmPassword


    // TODO :  remove unnecessary blank lines in code below, you are not getting paid for LOC

    static constraints = {
        username blank: false, unique: true, email: true, validator: { val, obj ->
            if (User.findByUsername(val)) {
                return false
            }
        }

        password blank: false
        firstName blank: false
        lastName nullable: true

        confirmPassword validator: { val, obj ->
            if (!val.equals(obj.password))
                return false
        }
    }

    User registerUser() {
        User user = new User(username: username, firstName: firstName, lastName: lastName, password: password, enabled: true)

        return user
    }
}
