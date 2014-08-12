package com.tekusource.sabongpro.email.notification;

public interface NotificationService
{
    void sendNotification( NotificationMessage message ) throws NotificationException;

    boolean isAsync();
}
