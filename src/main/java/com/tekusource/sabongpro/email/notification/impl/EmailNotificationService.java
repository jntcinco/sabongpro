package com.tekusource.sabongpro.email.notification.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.tekusource.sabongpro.email.notification.MessageTemplate;
import com.tekusource.sabongpro.email.notification.NotificationException;
import com.tekusource.sabongpro.email.notification.NotificationMessage;

public class EmailNotificationService
{
    private static final Logger logger = LoggerFactory.getLogger( EmailNotificationService.class );

    private boolean async;

    @Resource
    private JavaMailSender mailSender;

    @Autowired
    @Qualifier( "messageTemplate" )
    private MessageTemplate messageTemplate;
    
    public void sendEmailNotification( List<String> recipients, String message, String subject, String from )
    {
        String[] to = (String[]) recipients.toArray( new String[0] );
        Map<String, Object> emailVals = new HashMap<String, Object>();
        emailVals.put( "testDate", new Date() );
        emailVals.put( "bodyContent", message );
        emailVals.put( "subjectMessage", subject );

        EmailNotificationMessage emailMessage =
            (EmailNotificationMessage) messageTemplate.createMessageFromTemplate( emailVals );
        emailMessage.setTo( to );
        emailMessage.setFrom( from );
        try
        {
        	sendNotificationMessage( emailMessage );
        }
        catch ( NotificationException e )
        {
        	logger.error( "An exception occured while sending notification.", e );
        }
    }

//    @Override
    private void sendNotificationMessage( NotificationMessage message ) throws NotificationException
    {
        try
        {
            if ( message instanceof EmailNotificationMessage )
            {
                MimeMessage mimeMessage = convertToMimeMessage( (EmailNotificationMessage) message );
                logger.info( "converted to mime message..." );
                mailSender.send( mimeMessage );
                logger.info( "message sent..." );
            }
            else
            {
                throw new IllegalArgumentException( "not expected message - EmailNotificationMessage" );
            }
        }
        catch ( Exception e )
        {
        	logger.error( "error occurred while sending email", e );
        }
    }

    private MimeMessage convertToMimeMessage( EmailNotificationMessage message )
        throws AddressException, MessagingException
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeHelper = new MimeMessageHelper( mimeMessage, true );
        mimeHelper.setFrom( message.getFrom() );
        mimeHelper.setTo( message.getTo() );
        if ( message.getCc() != null )
        {
            mimeHelper.setCc( message.getCc() );
        }
        mimeHelper.setSubject( message.getSubject() );
        mimeHelper.setText( message.getBody(), true );
        if ( message.getAttachments() != null )
        {
            for ( File attachment : message.getAttachments() )
            {
                FileSystemResource file = new FileSystemResource( attachment );
                mimeHelper.addAttachment( attachment.getName(), file );
            }
        }
        return mimeMessage;
    }

    public boolean isAsync()
    {
        return async;
    }

    public void setAsync( boolean async )
    {
        this.async = async;
    }

    public void setMailSender( JavaMailSender mailSender )
    {
        this.mailSender = mailSender;
    }
}
