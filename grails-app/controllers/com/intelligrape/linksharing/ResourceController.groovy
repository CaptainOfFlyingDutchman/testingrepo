package com.intelligrape.linksharing

class ResourceController {
    def userService

    def createLinkResource() {
        Topic topicToAddResourceTo = Topic.get(params.id)
        [topicToAddResourceTo: topicToAddResourceTo, creator: userService.currentUser]
    }

    def createDocumentResource() {
        Topic topicToAddResourceTo = Topic.get(params.id)
        [topicToAddResourceTo:topicToAddResourceTo, creator: userService.currentUser]
    }

    def saveLinkResource(SaveLinkResourceCommand command) {
        if (command.validate()) {
            LinkResource linkResource = new LinkResource(creator: command.creator, title: command.title, summary: command.summary,
                    url: command.url, topic: command.topic).save(flush: true, failOnError: true)
            redirect action: "linkResourceDetails", params: [id: linkResource.id]
        }
    }

    def linkResourceDetails() {
        [linkResource: LinkResource.get(params.id), creator: userService.currentUser]
    }

    def editLinkResourceDetails(EditLinkResourceCommand command) {
        if (command.validate())
            [linkResource: command.linkResource, creator: command.creator]
    }

    def saveNewChangesToLinkResource(SaveNewChangesToLinkResourceCommand command) {
        if (command.validate()) {
            LinkResource linkResource = LinkResource.get(command.id)
            linkResource.properties = command.properties
            linkResource.save(flush: true,failOnError: true)
            redirect action: "linkResourceDetails", params: [id: params.id]
        }
    }

    def viewAssociatedLinkResources() {
        Topic topicForAssociatedLinkResources = Topic.get(params.id)
        Set<LinkResource> resources = topicForAssociatedLinkResources.resources
        [resources:resources, topicForAssociatedLinkResources:topicForAssociatedLinkResources]
    }

    def viewAssociatedDocumentResources() {
//        Topic topicForAssociatedDocumentResources = Topic.get(params.id)
    }
}
