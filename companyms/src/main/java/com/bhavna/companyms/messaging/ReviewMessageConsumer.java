package com.bhavna.companyms.messaging;

import com.bhavna.companyms.dto.ReviewMessage;
import com.bhavna.companyms.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewMessageConsumer
{
    private final CompanyService companyservice;
    public ReviewMessageConsumer(CompanyService companyservice)
    {
        this.companyservice=companyservice;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewmessage)
    {
        companyservice.updateCompanyRating(reviewmessage);
    }

}
