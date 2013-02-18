import com.intelligrape.linksharing.User
import grails.validation.Validateable

/**
 * Created with IntelliJ IDEA.
 * User: manvendra
 * Date: 18/2/13
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */

@Validateable
class PasswordCheckCommand {
    String password
    String confirmPassword
    User user

    static constraints = {
        password blank: false

        confirmPassword blank: false, validator: { val, obj ->
            if (!val.equals(obj.password)) {
                return false
            }
        }
    }
}
