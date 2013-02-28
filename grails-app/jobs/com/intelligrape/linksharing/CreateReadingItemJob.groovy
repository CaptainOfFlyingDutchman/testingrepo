package com.intelligrape.linksharing

import org.quartz.JobExecutionContext

class CreateReadingItemJob {
    def name = "CreateReadingItemJob"
    def linkDocumentResourceService

    static triggers = {
    }

    def execute(JobExecutionContext context) {
        Resource resource = context.mergedJobDataMap.get('resource')
        linkDocumentResourceService.createReadingItemForCreatorAndAllSubscriberFromJob(resource)
    }
}
