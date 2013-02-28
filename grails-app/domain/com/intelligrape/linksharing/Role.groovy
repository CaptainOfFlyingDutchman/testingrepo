package com.intelligrape.linksharing

class Role {
	String authority

    static final String ROLE_ADMIN = "ROLE_ADMIN"
    static final String ROLE_USER = "ROLE_USER"

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}

    static Role getAdminRole() {
        return Role.findByAuthority(ROLE_ADMIN)
    }

    static Role getUserRole() {
        return Role.findByAuthority(ROLE_USER)
    }
}
