import com.intelligrape.linksharing.*

class BootStrap {

    def springSecurityService

    def init = { servletContext ->

        def userRole = Role.findByAuthority("ROLE_USER")?: new Role(authority: "ROLE_USER").save(failOnError: true)
        def adminRole = Role.findByAuthority("ROLE_ADMIN")?: new Role(authority: "ROLE_ADMIN").save(failOnError: true)

        // TODO : Use method Role.findOrCreateBy in code above.


        def adminUser = User.findByUsername("admin@ig.com")?:new User(username: "admin@ig.com",
                                        password: "admin",
                                        enabled: true,
                                        firstName: "Admin", confirmPassword: "admin").save(failOnError: true)

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create(adminUser, adminRole)
        }
    }


    def destroy = {
    }
}
