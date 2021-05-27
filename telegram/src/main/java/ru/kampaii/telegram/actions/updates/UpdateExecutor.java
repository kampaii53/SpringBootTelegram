package ru.kampaii.telegram.actions.updates;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateExecutor {

    boolean applies(Update update);

    void execute(Update update);
}
