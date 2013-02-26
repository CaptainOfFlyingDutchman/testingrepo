package com.intelligrape.linksharing

import org.springframework.web.multipart.MultipartFile

class ResourceController {
    def userService
    def linkSharingService

    def createLinkResource() {
        Topic topicToAddResourceTo = Topic.get(params.id)
        [topicToAddResourceTo: topicToAddResourceTo, creator: userService.currentUser]
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
            linkResource.save(flush: true, failOnError: true)
            redirect action: "linkResourceDetails", params: [id: params.id]
        }
    }

    def viewAssociatedLinkResources() {
        Topic topicForAssociatedLinkResources = Topic.get(params.id)
        List<LinkResource> resources = LinkResource.findAllByTopic(topicForAssociatedLinkResources)
        [resources: resources, topicForAssociatedLinkResources: topicForAssociatedLinkResources]
    }

    def createDocumentResource() {
        Topic topicToAddResourceTo = Topic.get(params.id)
        [topicToAddResourceTo: topicToAddResourceTo, creator: userService.currentUser]
    }

    def saveDocumentResource(SaveDocumentResourceCommand command) {
        if (command.validate()) {
            MultipartFile file = request.getFile('fileName')
            DocumentResource documentResource = linkSharingService.createDocumentResource(command.creator, command.title,
                    command.summary, command.topic, file)
            redirect action: "documentResourceDetails", params: [id: documentResource.id]
        }
    }

    def documentResourceDetails() {
        [documentResource: DocumentResource.get(params.id), creator: userService.currentUser]
    }

    def downloadDocumentResource() {
        DocumentResource documentResourceToDownload = DocumentResource.get(params.id)
        String fileName = documentResourceToDownload.fileName.replace(' ', '-')
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName)
        response.contentType = documentResourceToDownload.contentType
        File fileToDownload = new File(Utility.uploadPath + "/" + documentResourceToDownload.id)
        response.contentLength = fileToDownload.size()
        response.outputStream << fileToDownload.bytes
    }

    def viewAssociatedDocumentResources() {
        Topic topicForAssociatedDocumentResources = Topic.get(params.id)
        List<DocumentResource> resources = DocumentResource.findAllByTopic(topicForAssociatedDocumentResources)
        [resources: resources, topicForAssociatedDocumentResources: topicForAssociatedDocumentResources]
    }
}
