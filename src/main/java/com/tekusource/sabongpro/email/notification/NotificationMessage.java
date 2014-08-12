package com.tekusource.sabongpro.email.notification;

import java.io.Serializable;

public interface NotificationMessage extends Serializable
{
    String getFrom();

    String[] getTo();

    String getSubject();

    String getBody();
}
