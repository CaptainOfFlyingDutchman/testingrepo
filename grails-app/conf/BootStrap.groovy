import com.intelligrape.linksharing.*

class BootStrap {

    def springSecurityService
    def linkSharingService

    def init = { servletContext ->

        def userRole = Role.findOrSaveByAuthority("ROLE_USER")
        def adminRole = Role.findOrSaveByAuthority("ROLE_ADMIN")

        def adminUser = linkSharingService.createUser("manvendra+1@intelligrape.com", "Manvendra", "Singh", "a", true)
        def normalUser = linkSharingService.createUser("manvendra+2@intelligrape.com", "Rahul", "Kumar", "a", true)

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create(adminUser, adminRole)
        }

        if (!normalUser.authorities.contains(userRole)) {
            UserRole.create(normalUser, userRole)
        }
    }


    def destroy = {
    }
}
