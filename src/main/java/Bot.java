import java.util.ArrayList;
import java.util.List;

public class Bot {
    private int[] startLocation;
    private int[] desiredLocation;
    private String desiredProductName;

    public Bot(int[] startLocation, int[] desiredLocation, String desiredProductName) {
        this.startLocation = startLocation;
        this.desiredLocation = desiredLocation;
        this.desiredProductName = desiredProductName;
    }

    private List<int[]> findProduct(Module[][] grid) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++) {
                String[] contents = grid[i][j].getItems();
                for (int k = 0; k < contents.length; k++) {
                    if (contents[k] != null && contents[k].equals(desiredProductName)) {
                        result.add(new int[]{i, j, k});
                        break;
                    }
                }
            }
        }
        return result;
    }

    private double getTravelTime(Module from, Module to){
        if(from.getDriveTime() < to.getDriveTime())
             return to.getDriveTime();
        else return from.getDriveTime();
    }

    public void findBestPath(Module[][] grid){
        List<int[]> desiredProductLocations = findProduct(grid);
        for(int[] t : desiredProductLocations)
            System.out.printf("[%s, %s] at layer %s, time -> %s\n", t[0], t[1], t[2], findPath(grid, t));
    }

    public Double findPath(Module[][] grid, int[] productLocation){
        Double time = 0.0;

        // Algorytm

        return time;
    }
}
