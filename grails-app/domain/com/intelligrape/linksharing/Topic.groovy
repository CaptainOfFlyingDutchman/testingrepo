package com.intelligrape.linksharing

class Topic {

    Visibility visibility
    String name

    static belongsTo = [owner:User]

    static hasMany = [subscriptions:Subscription, resources:Resource]

    static mapping = {

    }
    
	static constraints = {
    }

    @Override
    String toString() {
        return name
    }
}
