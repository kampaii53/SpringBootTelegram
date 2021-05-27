package ru.kampaii.telegram.bots;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;
import ru.kampaii.telegram.actions.callbacks.CallbackRegistry;
import ru.kampaii.telegram.actions.updates.UpdateExecutor;
import ru.kampaii.telegram.bots.Bot;

import java.util.List;

public class ExampleBot  extends Bot{
    public ExampleBot(List<CallbackExecutor<Update>> callbackExecutors, List<UpdateExecutor> updateExecutors) {
        super(
                new DefaultBotOptions(),
                System.getenv("BOT_TOKEN"),
                "KampaiiTestBot",
                callbackExecutors,
                updateExecutors
        );
    }
}
