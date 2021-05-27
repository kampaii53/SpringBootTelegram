import config.TestApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kampaii.telegram.bots.ExampleBot;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
public class Test {

    @Autowired
    private ExampleBot exampleBot;

    @org.junit.Test
    public void test(){
        System.out.println(1);
    }
}
