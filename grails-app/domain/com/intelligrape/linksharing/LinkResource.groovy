package com.intelligrape.linksharing

class LinkResource extends Resource {
    String url

    static mapping = {
    }
    
	static constraints = {
        url url: true
    }
	
	@Override
	public String toString() {
		return "${url}";
	}
}
