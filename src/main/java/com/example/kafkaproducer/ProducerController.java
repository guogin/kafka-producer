package com.example.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    private final KafkaTemplate<String, String> template;

    private static Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    public ProducerController(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @PostMapping(value = "/api/send")
    public String sendMessage(@RequestBody String message) {
        ListenableFuture<SendResult<String, String>> future =
                template.send("topic1", message);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });

        return message;
    }
}
