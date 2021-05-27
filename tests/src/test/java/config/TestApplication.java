package config;

import ru.kampaii.telegram.bots.ExampleBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class TestApplication {

    @Bean
    public ExampleBot bot() {
        return new ExampleBot(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }
}
