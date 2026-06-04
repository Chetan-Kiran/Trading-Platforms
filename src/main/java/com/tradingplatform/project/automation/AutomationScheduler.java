package com.tradingplatform.project.automation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutomationScheduler {

    private final
    AutomationService automationService;

    public AutomationScheduler(
        AutomationService automationService
    ){
        this.automationService =
            automationService;
    }

    @Scheduled(fixedRate = 60000)
    public void runAutomation(){

        System.out.println(
            "AUTO SCHEDULER RUNNING..."
        );

        automationService.run();
    }
}