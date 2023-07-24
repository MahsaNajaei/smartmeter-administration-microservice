package com.energymeter.administrationservice.exception;

import com.energymeter.administrationservice.exception.messages.GeneralErrorMessages;

public class EntityIdNotFoundException extends RuntimeException {

    public EntityIdNotFoundException(String entityName, long id) {
        super(String.format(GeneralErrorMessages.ENTITY_ID_NOT_EXISTS, entityName, id));
    }
}
