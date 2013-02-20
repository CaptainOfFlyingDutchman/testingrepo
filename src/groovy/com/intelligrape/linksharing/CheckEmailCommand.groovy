package com.intelligrape.linksharing

import com.intelligrape.linksharing.*
import grails.validation.Validateable

/**
 * Created with IntelliJ IDEA.
 * User: manvendra
 * Date: 18/2/13
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */

// TODO :  Remove all of the unnecessary comments, no one want to know who you are, or which idea do you use or which file template
// TODO :  Remove all comments, that cleans the code.

@Validateable
class CheckEmailCommand {
    String username

    static constraints = {
        username email: true, blank: false /*,validator: { val, obj ->
            User.findByUsername(val) ? "true" : "false"*/
//        }
    }
}
