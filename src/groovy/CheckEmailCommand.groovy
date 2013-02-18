import com.intelligrape.linksharing.*
import grails.validation.Validateable

/**
 * Created with IntelliJ IDEA.
 * User: manvendra
 * Date: 18/2/13
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */

@Validateable
class CheckEmailCommand {
    String username

    static constraints = {
        username email: true, blank: false /*,validator: { val, obj ->
            User.findByUsername(val) ? "true" : "false"*/
//        }
    }
}
