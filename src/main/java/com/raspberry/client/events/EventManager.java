package com.raspberry.client.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Event manager for the Raspberry Client.
 * Handles event registration, deregistration, and execution with priority support.
 */
public class EventManager {

    private static final Map<Class<? extends Event>, List<MethodData>> REGISTRY = new ConcurrentHashMap<>();

    /**
     * Registers all methods annotated with @EventTarget in the given object.
     * @param object the object to register
     */
    public static void register(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventTarget.class)) {
                if (method.getParameterCount() == 1 && Event.class.isAssignableFrom(method.getParameterTypes()[0])) {
                    Class<? extends Event> eventClass = (Class<? extends Event>) method.getParameterTypes()[0];

                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }

                    EventTarget annotation = method.getAnnotation(EventTarget.class);
                    MethodData data = new MethodData(object, method, annotation.priority());

                    REGISTRY.computeIfAbsent(eventClass, k -> new CopyOnWriteArrayList<>()).add(data);

                    // Sort by priority (higher priority first)
                    REGISTRY.get(eventClass).sort((a, b) -> Integer.compare(b.priority, a.priority));
                }
            }
        }
    }

    /**
     * Unregisters all methods in the given object.
     * @param object the object to unregister
     */
    public static void unregister(Object object) {
        for (List<MethodData> dataList : REGISTRY.values()) {
            dataList.removeIf(data -> data.source == object);
        }
    }

    /**
     * Calls an event to all registered listeners.
     * @param event the event to call
     * @return the event after execution
     */
    public static Event call(Event event) {
        List<MethodData> dataList = REGISTRY.get(event.getClass());
        if (dataList != null) {
            for (MethodData data : dataList) {
                try {
                    data.method.invoke(data.source, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return event;
    }

    /**
     * Data class to store method information with priority.
     */
    private static class MethodData {
        private final Object source;
        private final Method method;
        private final int priority;

        public MethodData(Object source, Method method, int priority) {
            this.source = source;
            this.method = method;
            this.priority = priority;
        }
    }
}
