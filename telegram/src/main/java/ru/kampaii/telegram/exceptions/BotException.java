package ru.kampaii.telegram.exceptions;

public class BotException extends RuntimeException{
    public BotException(String s) {
        super(s);
    }
}
