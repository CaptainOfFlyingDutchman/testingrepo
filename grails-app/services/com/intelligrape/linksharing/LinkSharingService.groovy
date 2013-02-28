package com.intelligrape.linksharing

import org.springframework.web.multipart.MultipartFile

class LinkSharingService {
    static transactional = true

    User createUser(String username, String firstName, String lastName, String password, boolean enabled) {
        User.findByUsername(username) ?: new User(username: username, firstName: firstName,
                lastName: lastName, password: password, enabled: enabled).save(flush: true, failOnError: true)
    }

    Topic createTopic(Visibility visibility, String name, User owner) {
        return new Topic(visibility: visibility, name: name, owner: owner).save(flush: true, failOnError: true)
    }

    Subscription createSubscription(Seriousness seriousness, User subscriber, Topic topic) {
        return new Subscription(seriousness: seriousness, subscriber: subscriber, topic: topic).save(flush: true, failOnError: true)
    }

    LinkResource createLinkResource(User creator, String title, String summary, String url, Topic topic) {
        return new LinkResource(creator: creator, title: title, summary: summary,
                url: url, topic: topic).save(flush: true, failOnError: true)
    }

    DocumentResource createDocumentResource(User creator, String title, String summary, Topic topic, MultipartFile file) {
        try {
            DocumentResource documentResource = new DocumentResource(creator: creator, title: title, summary: summary,
                    fileName: file.originalFilename, contentType: file.contentType, topic: topic).save(flush: true, failOnError: true)
            String uploadPath = Utility.uploadPath + "/" + documentResource.id
            file.transferTo(new File("${uploadPath}"))
            return documentResource
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload the document.")
        }
    }

    ReadingItem createReadingItem(Resource resource, User user) {
        return new ReadingItem(resource: resource, user: user).save(flush: true, failOnError: true)
    }
}
