package ru.kampaii.telegram.bots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;
import ru.kampaii.telegram.actions.callbacks.CallbackRegistry;
import ru.kampaii.telegram.actions.updates.UpdateExecutor;

import java.util.List;

public abstract class Bot extends TelegramLongPollingCommandBot {

    private final String token;

    private final String name;

    private final List<UpdateExecutor> updateExecutors;

    private final CallbackRegistry callbackRegistry;

    protected Bot(
            DefaultBotOptions botOptions,
            String token,
            String name,
            List<CallbackExecutor<Update>> callbackExecutors,
            List<UpdateExecutor> updateExecutors
    ) {
        super(botOptions);
        this.token = token;
        this.name = name;
        this.updateExecutors = updateExecutors;
        this.callbackRegistry = new CallbackRegistry(callbackExecutors);
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        updateExecutors.stream()
                .filter(executor -> executor.applies(update))
                .forEach(executor -> executor.execute(update));
    }

    public Message sendMessage(Long chatId, String message) throws TelegramApiException {
        return execute(new SendMessage(chatId.toString(),message));
    }
}
