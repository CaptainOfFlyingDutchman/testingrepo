package com.intelligrape.linksharing

/**
 * UserController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class UserController {

    static defaultAction = "createTopic"

    def createTopic() {
        List visiblityConstants = Visibility.getEnumConstants()

        [visiblityConstants:visiblityConstants]
    }
}
