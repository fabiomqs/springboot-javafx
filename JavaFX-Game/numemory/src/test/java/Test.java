import com.github.fabiomqs.numemory.NuMemoryApplication;

import java.util.Random;

public class Test {

    @org.junit.jupiter.api.Test
    void test() {
        Random random = new Random();
        for(int i = 0; i<=9;i++) {
            int x = random.nextInt(1280 / 80);
            int y = random.nextInt(560 / 80);
            System.out.println("( " + x + ", " + y + " )");
        }
    }
}
