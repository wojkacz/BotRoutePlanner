import java.util.*;

public class Bot {
    private int[] startLocation;
    private int[] desiredLocation;
    private String desiredProductName;

    public Bot(int[] startLocation, int[] desiredLocation, String desiredProductName) {
        this.startLocation = startLocation;
        this.desiredLocation = desiredLocation;
        this.desiredProductName = desiredProductName;
    }

    // Method responsible for returning list of positions where desired product is.
    // Each element in list is array of (x position, y position, n - layer)
    private List<int[]> findProduct(Module[][] grid) {
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++) {
                String[] contents = grid[i][j].getItems();

                for (int k = 0; k < contents.length; k++) {
                    if (contents[k] != null && contents[k].equals(desiredProductName)) {
                        result.add(new int[]{i, j, k});

                        // We only need one product of a single location, the one on lowest layer
                        break;
                    }
                }
            }
        }

        return result;
    }

    // Method responsible for getting time of travel between two modules
    // It is weight of the edge
    protected double getTravelTime(Module[][] grid, Node one, Node two){
        Module from = grid[one.getLocation()[0]][one.getLocation()[1]];
        Module to = grid[two.getLocation()[0]][two.getLocation()[1]];

        if(from.getDriveTime() < to.getDriveTime())
             return to.getDriveTime();
        else return from.getDriveTime();
    }

    // Method responsible for calling alghoritm method and then adding results
    // It look for path from start to products and then from product to desired location.
    // When all of the results are found, this method choose best result and returns it.
    public Object[] findBestPath(Module[][] grid){
        List<int[]> desiredProductLocations = findProduct(grid);
        List<Object[]> results = new ArrayList<>();

        Node[][] nodesToProducts = findPaths(grid, startLocation);
        for(int[] location : desiredProductLocations){
            Node[][] nodesFromProduct = findPaths(grid, location);

            Object[] to = getAnswer(nodesToProducts, location);
            Object[] from = getAnswer(nodesFromProduct, desiredLocation);

            Double time = (double) to[0] + (double) from[0];
            time += grid[location[0]][location[1]].getItemTime(location[2]);
            int pathLength = (int) to[1] + (int) from[1];

            List<int[]> pathTo = (ArrayList<int[]>) to[2];
            List<int[]> pathFrom = (ArrayList<int[]>) from[2];
            pathFrom.remove(0);

            List<int[]> path = new ArrayList<>(pathTo);
            path.addAll(pathFrom);

            results.add(new Object[]{time, pathLength, path});
        }
        results.sort(Comparator.comparingDouble(o -> (double) o[0]));
        return results.get(0);
    }

    // Method responsible for retrieving path, its length and reaching time
    // Returned object is array of Objects: time (Double), length of path (int) and path (List of coordinates)
    private Object[] getAnswer(Node[][] nodes, int[] location){
        int pathLength = -1;
        List<int[]> path = new ArrayList<>();

        Node node = nodes[location[0]][location[1]];
        Double time = node.getTime();

        while(node != null){
            pathLength++;
            path.add(0, node.getLocation());
            node = node.getParent();
        }
        return new Object[] {time, pathLength, path};
    }

    // Dijkstry alghoritm
    private Node[][] findPaths(Module[][] grid, int[] findStartLocation){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Node[][] gridNode = new Node[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length; j++) {
                gridNode[i][j] = new Node(new int[]{i, j});
                if(i == findStartLocation[0] && j == findStartLocation[1])
                    gridNode[i][j].setTime(0.0);
                if(grid[i][j].getClass() != TypeO.class)
                    queue.add(gridNode[i][j]);
            }

        while(!queue.isEmpty()){
            Node currentNode = queue.poll();

            int curX = currentNode.getLocation()[0];
            int curY = currentNode.getLocation()[1];

            List<Node> childNodes = new ArrayList<>();

            if(curX - 1 >= 0)                   childNodes.add(gridNode[curX - 1][curY]);
            if(curX + 1 < gridNode.length)      childNodes.add(gridNode[curX + 1][curY]);
            if(curY - 1 >= 0)                   childNodes.add(gridNode[curX][curY - 1]);
            if(curY + 1 < gridNode[0].length)   childNodes.add(gridNode[curX][curY + 1]);
            childNodes.removeIf(n -> !n.isGood(grid));

            for(Node childNode : childNodes){
                if(getTravelTime(grid, currentNode, childNode) + currentNode.getTime() < childNode.getTime()){
                    queue.remove(childNode);
                    childNode.setTime(getTravelTime(grid, currentNode, childNode) + currentNode.getTime());
                    childNode.setParent(currentNode);
                    queue.add(childNode);
                }
            }
        }
        return gridNode;
    }

    public int[] getStartLocation() {
        return startLocation;
    }

    public int[] getDesiredLocation() {
        return desiredLocation;
    }

    public String getDesiredProductName() {
        return desiredProductName;
    }
}
