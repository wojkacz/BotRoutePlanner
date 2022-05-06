import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BotTest {

    static Bot bot;
    static Module[][] grid;

    @BeforeAll
    public static void setup() {
        grid = Loader.loadGrid("grid-1.txt");
        bot = Loader.loadJob("job-1.txt");
    }

    @Test
    public void getTravelTimeTest(){
        Node one = new Node(new int[]{0,1});
        Node two = new Node(new int[]{1,1});

        Node three = new Node(new int[]{0,0});
        Node four = new Node(new int[]{1,0});

        assertEquals(1.0, bot.getTravelTime(grid, one, two));
        assertEquals(0.5, bot.getTravelTime(grid, three, four));
    }

    @Test
    public void findBestPathTest(){
        Object[] result = bot.findBestPath(grid);
        assertEquals(10.5, (double) result[0]);
        assertEquals(8, (int) result[1]);
    }
}
