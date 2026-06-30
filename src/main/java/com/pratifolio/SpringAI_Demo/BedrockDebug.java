package com.pratifolio.SpringAI_Demo;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.bedrock.converse.BedrockProxyChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BedrockDebug {
    @Autowired
    private BedrockProxyChatModel model;

    @PostConstruct
    public void init() {
        System.out.println(model);
    }
}
