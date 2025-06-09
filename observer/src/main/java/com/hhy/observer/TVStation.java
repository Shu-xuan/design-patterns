package com.hhy.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述: 相当于总线
 * </p>
 *
 * @Author huhongyuan
 */
public class TVStation {
    private final List<EventListener> listeners = new ArrayList<>();
    private final Map<Class<? extends Event>, List<EventListener>> listenerMap = new HashMap<>();

    public void subscribe(EventListener eventListener, Class<? extends Event> eventClass) {
        listenerMap.computeIfAbsent(eventClass, k -> new ArrayList<>()).add(eventListener);
    }

    public void publish(Event event) {
        List<EventListener> eventListeners = listenerMap.get(event.getClass());
        if (eventListeners == null) {
            return;
        }
        eventListeners.forEach(eventListener-> eventListener.onEvent(event));
    }

}
