package com.raspberry.client.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for marking methods that should receive events.
 * Methods must have exactly one parameter of type Event or a subclass.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventTarget {
    /**
     * Priority of event execution. Higher values are executed first.
     * Default priority is 0.
     * @return the priority value
     */
    int priority() default 0;
}
