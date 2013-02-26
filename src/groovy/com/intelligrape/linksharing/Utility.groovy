package com.intelligrape.linksharing

import org.codehaus.groovy.grails.commons.ApplicationHolder

class Utility {
    public static def getBean(String beanName) {
        return ApplicationHolder.application.mainContext.getBean(beanName)
    }

    static String getUploadPath() {
        return getBean('grailsApplication').config.grails.config.upload.docs.location
    }
}
