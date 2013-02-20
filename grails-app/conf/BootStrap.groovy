import com.intelligrape.linksharing.*

class BootStrap {

    def springSecurityService

    def init = { servletContext ->

        def userRole = Role.findByAuthority("ROLE_USER")?: new Role(authority: "ROLE_USER").save(failOnError: true)
        def adminRole = Role.findByAuthority("ROLE_ADMIN")?: new Role(authority: "ROLE_ADMIN").save(failOnError: true)

        def adminUser = User.findByUsername("manvendra+1@intelligrape.com")?:new User(username: "manvendra+1@intelligrape.com",
                                        password: "a",
                                        enabled: true,
                                        firstName: "Manvendra", lastName: "Singh", confirmPassword: "a").save(failOnError: true)

        // TODO : Use method Role.findOrCreateBy in code above.

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create(adminUser, adminRole)
        }
    }


    def destroy = {
    }
}
