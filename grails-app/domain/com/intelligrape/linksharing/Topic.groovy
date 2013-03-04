package com.intelligrape.linksharing

class Topic {
    Visibility visibility
    String name
    String summary
    Date dateCreated
    Date lastUpdated

    static belongsTo = [owner:User]

    static hasMany = [subscriptions:Subscription, resources:Resource]

    static mapping = {

    }
    
	static constraints = {
        name nullable: false, blank: false
        summary nullable: false, blank: false
    }

    @Override
    String toString() {
        return name
    }
}
