public class Node implements Comparable<Node> {
    private Double time;
    private int[] location;
    private Node parent;

    public Node(int[] location) {
        this.location = location;
        this.parent = null;
        time = Double.MAX_VALUE;
    }

    public boolean isGood(Module[][] grid){
        return (grid[location[0]][location[1]].getClass() != TypeO.class);
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public int[] getLocation() {
        return location;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Node node) {
        if (this.time < node.time)
            return -1 ;
        else if (this.time > node.time)
            return 1;
        return 0;
    }
}
