package com.tekusource.sabongpro.email.notification.impl;

import java.io.File;

import com.tekusource.sabongpro.email.notification.NotificationMessage;

public class EmailNotificationMessage
    implements NotificationMessage
{
    private String from;

    private String[] to;

    private String[] cc;

    private String subject;

    private String body;

    private File[] attachments;

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

    public String getSubject()
    {
        return subject;
    }

    public void setSubject( String subject )
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody( String body )
    {
        this.body = body;
    }

    public String[] getCc()
    {
        return cc;
    }

    public void setCc( String[] cc )
    {
        this.cc = cc;
    }

    public File[] getAttachments()
    {
        return attachments;
    }

    public void setAttachments( File[] attachments )
    {
        this.attachments = attachments;
    }
}
