import config.WeworkConfig;
import org.junit.jupiter.api.Test;

public class WeworkConfigTest {

    @Test
    void load() {
        WeworkConfig.load("");
    }

    @Test
    void getInstance(){
        WeworkConfig.getInstance();
        System.out.println("dad");

    }
}
