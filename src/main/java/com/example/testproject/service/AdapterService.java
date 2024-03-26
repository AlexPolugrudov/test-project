package com.example.testproject.service;

import com.example.testproject.enums.Lang;
import com.example.testproject.model.messageA.MsA;
import com.example.testproject.model.messageB.MsB;
import com.example.testproject.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdapterService {

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
        return lng.equals(Lang.RU);
    }

    private MsB createMsB(String message, double temperature) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-'T'HH:mm:ssX");
        var createdAt = LocalDateTime.parse(LocalDateTime.now().format(formatter));

        return MsB.builder()
                .txt(message)
                .createdDt(createdAt)
                .currenTemp(temperature)
                .build();
    }

    private void sendMessageToServiceB(MsB msB) {

        var bodyValues = createSendingBody(msB);

        webClientBuilder.build()
                .post()
                .uri(Utility.SERVICE_B_URL)
                .body(BodyInserters.fromFormData(bodyValues))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private MultiValueMap<String, String> createSendingBody(MsB msB) {
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

        bodyValues.add("txt", msB.getTxt());
        bodyValues.add("createdDt", msB.getCreatedDt().toString());
        bodyValues.add("currentTemp", String.valueOf(msB.getCurrenTemp()));

        return bodyValues;
    }
}
