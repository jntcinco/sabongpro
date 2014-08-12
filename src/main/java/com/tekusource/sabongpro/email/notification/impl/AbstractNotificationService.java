package com.tekusource.sabongpro.email.notification.impl;

import javax.annotation.Resource;

import org.springframework.core.task.TaskExecutor;

import com.tekusource.sabongpro.email.notification.NotificationException;
import com.tekusource.sabongpro.email.notification.NotificationMessage;
import com.tekusource.sabongpro.email.notification.NotificationService;

public abstract class AbstractNotificationService
    implements NotificationService
{
    @Resource
    protected TaskExecutor taskExecutor;

    public void sendNotification( final NotificationMessage message )
        throws NotificationException
    {
        if ( isAsync() )
        {
            taskExecutor.execute( new Runnable()
            {
                public void run()
                {
                    sendNotificationMessage( message );
                }
            } );
        }
        else
        {
            sendNotificationMessage( message );
        }
    }

    protected abstract void sendNotificationMessage( NotificationMessage message );
}
