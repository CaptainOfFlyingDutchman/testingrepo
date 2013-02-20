package com.intelligrape.linksharing

/**
 * Topic
 * A domain class describes the data object and it's mapping to the database
 */
class Topic {

    Visibility visibility
    String name

    static belongsTo = [owner:User]

    static hasMany = [subscriptions:Subscription]

    static mapping = {

    }
    
	static constraints = {
    }

    @Override
    String toString() {
        return name
    }
}
