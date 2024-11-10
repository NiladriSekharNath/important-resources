package com.adidas;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TestDateFormat {

    public static final String LEGACY = "legacy";
    public static void main(String args[]) {

        //LocalDateTime localDateTime = LocalDateTime.parse("2024-08-28 10:02:02[.010]");

        //Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

        //Instant instant = Instant.parse("2024-08-28T10:02:02.010Z");

        Timestamp timestamp = Timestamp.valueOf("2024-08-28 10:02:02.010");

        log.info("Current Time : {}", timestamp.toInstant());

        java.sql.Date date = java.sql.Date.valueOf(LocalDate.of(2024, Month.AUGUST, 28));

        log.info("date :{}", date.toLocalDate());

        log.info("date : {}", DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC).format(timestamp.toInstant()));


        Map<String, Object> value = new HashMap<>();

        //value.put("legacy", "false");


        boolean legacyDisabled =
            value.containsKey(LEGACY) &&
                !Boolean.parseBoolean(value.get(LEGACY).toString()) ;

        log.info("legacyDisabled : {}", legacyDisabled);

        if(legacyDisabled){
            log.info("performing new logic");
        }

        log.info("Putting new logic or old logic based on flag, logic: {}", legacyDisabled? "new logic" : "old logic");

        String url = "https://4e60ee06803c42d6acd7add192289185.serving.cloud.databricks.com/809065969263152/serving-endpoints/ds-digital-ppa-real_time_check_inference/invocations";

        final String regex = "https:\\/\\/(\\p{Alnum}*)(\\..*)";

        Pattern pattern = Pattern.compile(regex);

        Matcher m = pattern.matcher(url);

        if(m.find() && m.groupCount() > 1){
            String firstMatchingGroup = m.group(1);
            log.info("Endpoint Id: {} ", firstMatchingGroup);
        }

        Map<String, Object> realTime = new HashMap<>();
        realTime.put("eky", "value");
        for(Map.Entry<String, Object> eachParams : realTime.entrySet()){
            log.info("Logging each realTime Value");
            log.info("Key : {}, Value : {}", eachParams.getKey(), eachParams.getValue());
        }

    }



}
