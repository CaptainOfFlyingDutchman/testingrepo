package com.intelligrape.linksharing

class ApplicationFilters {

    def springSecurityService

    def filters = {
        blockUserController(controller: 'user', action: '*') {
            before = {
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
