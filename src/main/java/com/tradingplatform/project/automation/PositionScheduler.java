package com.tradingplatform.project.automation;

import org.springframework.stereotype.*;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class PositionScheduler {

    private final
    PositionMonitorService
        positionMonitorService;

    public PositionScheduler(
        PositionMonitorService
        positionMonitorService
    ){

        this.positionMonitorService =
            positionMonitorService;
    }

    @Scheduled(
        fixedRate = 60000
    )
    public void monitor(){

        positionMonitorService
            .monitorPositions();
    }
}
