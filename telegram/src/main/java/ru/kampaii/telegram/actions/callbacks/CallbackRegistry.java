package ru.kampaii.telegram.actions.callbacks;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.exceptions.BotException;

import java.util.*;

public class CallbackRegistry {

    private Map<Long, Map<Integer, List<CallbackEntry>>> registry = new HashMap<>();

    private Map<Class<?>, CallbackExecutor<Update>> executors = new HashMap<>();

    public CallbackRegistry(Collection<CallbackExecutor<Update>> callbackExecutors) {
        callbackExecutors.forEach(executor -> executors.put(executor.getClass(), executor));
    }

    public void registerCallback(Long chatId, Integer messageId, CallbackEntry callbackEntry) {
        registry.computeIfAbsent(chatId, k -> new HashMap<>())
                .computeIfAbsent(messageId, l -> new ArrayList<>())
                .add(callbackEntry);
    }

    public void executeCallback(Long chatId, Integer messageId, Update update){
        Optional.ofNullable(registry.get(chatId))
                .map(map -> map.get(messageId))
                .ifPresent(
                        callbackEntries -> callbackEntries.forEach(
                                callbackEntry -> Optional.ofNullable(executors.get(callbackEntry.getExecutorClass()))
                                        .orElseThrow(() ->
                                                new BotException(
                                                        String.format("executor %s not found",
                                                                callbackEntry.getExecutorClass().getName())
                                                ))
                                        .execute(update, callbackEntry.getProperties())
                        )
                );
    }
}
