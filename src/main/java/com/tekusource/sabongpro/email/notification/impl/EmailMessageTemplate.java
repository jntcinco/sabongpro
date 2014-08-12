package com.tekusource.sabongpro.email.notification.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.tekusource.sabongpro.email.notification.MessageTemplate;
import com.tekusource.sabongpro.email.notification.NotificationMessage;

public class EmailMessageTemplate
    implements MessageTemplate
{
    private String from;

    private String[] to;

    private String[] cc;

    private String subjectTemplate;

    private String bodyTemplate;

    @Resource
    private VelocityEngine velocityEngine;

    public String getFrom()
    {
        return from;
    }

    public void setFrom( String from )
    {
        this.from = from;
    }

    public String[] getTo()
    {
        return to;
    }

    public void setTo( String[] to )
    {
        this.to = to;
    }

    public String getSubjectTemplate()
    {
        return subjectTemplate;
    }

    public void setSubjectTemplate( String subjectTemplate )
    {
        this.subjectTemplate = subjectTemplate;
    }

    public String getBodyTemplate()
    {
        return bodyTemplate;
    }

    public void setBodyTemplate( String bodyTemplate )
    {
        this.bodyTemplate = bodyTemplate;
    }

    public String[] getCc()
    {
        return cc;
    }

    public void setCc( String[] cc )
    {
        this.cc = cc;
    }

    public NotificationMessage createMessageFromTemplate( Map<String, Object> modelMap )
    {
        EmailNotificationMessage message = new EmailNotificationMessage();
        message.setFrom( getFrom() );
        message.setTo( getTo() );
        message.setCc( getCc() );
        message.setSubject( VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, getSubjectTemplate(), modelMap ) );
        message.setBody( VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, getBodyTemplate(), modelMap ) );
        return message;
    }
}
