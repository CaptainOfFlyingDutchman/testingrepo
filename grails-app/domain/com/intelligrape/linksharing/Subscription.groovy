package com.intelligrape.linksharing

class Subscription {

    Seriousness seriousness

    static belongsTo = [subscriber:User, topic:Topic]

    static mapping = {
    }
    
	static constraints = {
    }

}
