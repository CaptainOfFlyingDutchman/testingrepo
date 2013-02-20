import com.intelligrape.linksharing.*

class BootStrap {

    def springSecurityService
    def linkSharingService

    def init = { servletContext ->

        def userRole = Role.findOrSaveByAuthority("ROLE_USER")
        def adminRole = Role.findOrSaveByAuthority("ROLE_ADMIN")

        def adminUser = linkSharingService.createUser("manvendra+1@intelligrape.com", "Manvendra", "Singh", "a", true)

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create(adminUser, adminRole)
        }
    }


    def destroy = {
    }
}
