package com.intelligrape.linksharing

import org.springframework.web.multipart.MultipartFile

class ResourceController {
    def userService
    def linkSharingService
    def linkDocumentResourceService

    def createLinkResource() {
        Topic topicToAddResourceTo = Topic.get(params.id)
        [topicToAddResourceTo: topicToAddResourceTo, creator: userService.currentUser]
    }

    def saveLinkResource(SaveLinkResourceCommand command) {
        if (command.validate()) {
            LinkResource linkResource = linkSharingService.createLinkResource(command.creator, command.title, command.summary,
                    command.url, command.topic)
            linkDocumentResourceService.createReadingItemForCreatorAndAllSubscribers(linkResource)
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
        List<ReadingItem> readingItems = ReadingItem.findAllByUser(userService.currentUser)
        [resources: resources, topicForAssociatedLinkResources: topicForAssociatedLinkResources,
                readingItems:readingItems]
    }

    def deleteLinkResource() {
        LinkResource linkResourceToDelete  = LinkResource.get(params.id)
        if (linkDocumentResourceService.deleteLinkResource(linkResourceToDelete)) {
            redirect action: "viewAssociatedLinkResources", id: linkResourceToDelete.topic.id
        } else {
            flash.message = "You're not authorized to delete this link resource."
            redirect action: "viewAssociatedLinkResources", id: linkResourceToDelete.topic.id
        }
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
            linkDocumentResourceService.createReadingItemForCreatorAndAllSubscribers(documentResource)
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
        List<ReadingItem> readingItems = ReadingItem.findAllByUser(userService.currentUser)
        [resources: resources, topicForAssociatedDocumentResources: topicForAssociatedDocumentResources,
                    readingItems: readingItems]
    }

    def deleteDocumentResource() {
        DocumentResource documentResourceToDelete  = DocumentResource.get(params.id)
        if (linkDocumentResourceService.deleteDocumentResource(documentResourceToDelete)) {
            redirect action: "viewAssociatedDocumentResources", id: documentResourceToDelete.topic.id
        } else {
            flash.message = "You're not authorized to delete this document resource."
            redirect action: "viewAssociatedDocumentResources", id: documentResourceToDelete.topic.id
        }
    }
}
