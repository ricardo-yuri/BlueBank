package com.br.panacademy.devcompilers.bluebank.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.br.panacademy.devcompilers.bluebank.dto.NotificationDTO;
import org.springframework.stereotype.Service;


@Service
public class AwsSNSService {

    final static String ARN_AWS_CLIENT = "arn:aws:sns:us-east-2:965934840569:DevCompilers";

    public String newSubscription(String emailClient) {

        AmazonSNSClient snsClient = createSNSClient();

        final SubscribeRequest subscribeRequest = new SubscribeRequest(ARN_AWS_CLIENT, "email", emailClient);
        snsClient.subscribe(subscribeRequest);
        return "Realizado o subscription com sucesso!";
    }

    public String sendNotification(NotificationDTO notificationDTO) {

        AmazonSNSClient snsClient = createSNSClient();

        snsClient.publish(ARN_AWS_CLIENT, notificationDTO.getMessage(), notificationDTO.getSubject());
        return "Realizado o envio da notificação com sucesso!";
    }

    private AmazonSNSClient createSNSClient() {
        final String SECRET_KEY = "F4ErCxih08PjUQwZFug7FjLfmusstDDFWhP2asbT";
        final String ACCESS_KEY = "AKIA6BZRT7L4YR72JVVZ";

        return (AmazonSNSClient) AmazonSNSClientBuilder.standard()
                .withRegion("us-east-2")
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
                .build();
    }
}
