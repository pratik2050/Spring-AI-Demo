package com.pratifolio.SpringAI_Demo.Tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;

@Component
public class TimeTools {

    public static final Logger logger = LoggerFactory.getLogger(TimeTools.class);

    @Tool(
            name = "getCurrentLocalTime",
            description = "This tool should be called when Local Time Information is needed"
    )
    String getCurrentLocalTime() {
        logger.info("Returning Current Local Time");
        return LocalTime.now().toString();
    }

    @Tool(
            name = "getCurrentTime",
            description = "This tool should be called when a specific time for a specific zone or region is needed"
    )
    String getCurrentTime(@ToolParam(
            description = "Value representing the time zone") String timeZone
    ) {
        logger.info("Getting time based on time zone");
        return LocalTime.now(ZoneId.of(timeZone)).toString();
    }

}
