package com.example.testproject.service;

import com.example.testproject.enums.Lang;
import com.example.testproject.model.messageA.MsA;
import com.example.testproject.model.messageB.MsB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdapterService {

    @Value("${service-b.url}")
    private String serviceBUrl;

    private final WeatherService weatherService;
    private final WebClient.Builder webClientBuilder;

    public String processMessage(MsA messageA) {
        var msgSatisfiesTheConditions = checkingMessage(messageA);

        if (msgSatisfiesTheConditions) {

            double temperature = weatherService.getAirTemperature(messageA);

            MsB msB = createMsB(messageA.getMsg(), temperature);

            sendMessageToServiceB(msB);

            return "Message sent successfully!";
        } else {
            return "Message is not satisfies the conditions";
        }

    }

    private boolean checkingMessage(MsA msg) {
        var msgLngIsRu = isMsgInRuLanguage(msg.getLng());
        var msgNotEmpty = isMsgIsNotEmpty(msg.getMsg());

        if (!msgNotEmpty) {
            log.error("Message from Service A is empty");
        }

        return msgLngIsRu && msgNotEmpty;
    }

    private boolean isMsgIsNotEmpty(String msg) {
        return !msg.isEmpty();
    }

    private boolean isMsgInRuLanguage(Lang lng) {
        return Lang.RU.equals(lng);
    }

    private MsB createMsB(String message, double temperature) {
        return MsB.builder()
                .txt(message)
                .createdDt(LocalDateTime.now())
                .currentTemp(temperature)
                .build();
    }

    private void sendMessageToServiceB(MsB msB) {

        var bodyValues = createSendingBody(msB);

        webClientBuilder.build()
                .post()
                .uri(serviceBUrl)
                .body(BodyInserters.fromValue(bodyValues))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private Map<String, String> createSendingBody(MsB msB) {
        Map<String, String> bodyValues = new HashMap<>();

        bodyValues.put("txt", msB.getTxt());
        bodyValues.put("createdDt", msB.getCreatedDt().toString());
        bodyValues.put("currentTemp", String.valueOf(msB.getCurrentTemp()));

        return bodyValues;
    }
}
