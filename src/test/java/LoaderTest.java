import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoaderTest {

    @Test
    public void loadGridTest(){
        Module[][] grid1 = Loader.loadGrid("grid-1.txt");
        Module[][] grid2 = Loader.loadGrid("grid-2.txt");

        assertNotNull(grid1);
        assertNotNull(grid2);
        assertSame(grid1[2][2].getClass(), TypeO.class);
        assertSame(grid2[2][13].getClass(), TypeH.class);
        assertEquals("185", grid2[86][34].getItems()[5]);
        assertEquals("P2", grid1[1][1].getItems()[2]);
    }

    @Test
    public void loadJobTest(){
        Bot bot1 = Loader.loadJob("job-1.txt");
        Bot bot2 = Loader.loadJob("job-2.txt");

        assertNotNull(bot1);
        assertNotNull(bot2);
        assertEquals(52, bot2.getStartLocation()[0]);
        assertEquals(0, bot1.getDesiredLocation()[1]);
        assertEquals("P1", bot1.getDesiredProductName());
    }

}
