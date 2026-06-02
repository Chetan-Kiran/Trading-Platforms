package com.tradingplatform.project.automation;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.tradingplatform.project.entity.AutomationRule;

@RestController
@RequestMapping("/automation")
public class AutomationController {

    private final
    AutomationService service;

    public AutomationController(
        AutomationService service
    ){
        this.service=service;
    }

    @PostMapping("/rule")
    public AutomationRule save(

        @RequestBody
        AutomationRule rule
    ){

        return service.save(
            rule
        );
    }

    @GetMapping("/rules")
    public List<AutomationRule>
    rules(){

        return service.getRules();
    }

    @PostMapping("/run")
    public String run(){

        return service.run();
    }
}