package core.rybina.listener.entity;

import lombok.Getter;

import java.util.EventObject;

public class EntityEvent extends EventObject {

    @Getter
    private final AccessType accessType;

    /**
     * Constructs a prototypical Event.
     *
     * @param entity  the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public EntityEvent(Object entity, AccessType accessType) {
        super(entity);
        this.accessType = accessType;
    }
}
