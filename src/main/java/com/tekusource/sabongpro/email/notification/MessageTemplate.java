package com.tekusource.sabongpro.email.notification;

import java.io.Serializable;
import java.util.Map;

public interface MessageTemplate extends Serializable
{
    String getFrom();

    String[] getTo();

    String getSubjectTemplate();

    String getBodyTemplate();

    NotificationMessage createMessageFromTemplate( Map<String, Object> modelMap );
}
