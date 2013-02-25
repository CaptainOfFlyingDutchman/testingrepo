package com.intelligrape.linksharing

class ApplicationFilters {

    def springSecurityService

    def filters = {
        blockUserController(controller: 'user', action: '*') {
            before = {
                // TODO-MANVENDRA - Remove unnecessary commented code and blank lines.
                // TODO-MANVENDRA - Remove everything thats unused.
                /*if (!springSecurityService.currentUser) {
                    redirect(url: "/")
                    return false
                }*/
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
