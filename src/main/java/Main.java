import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static int[] botStartLocation;
    static int[] botDesiredLocation;
    static String botDesiredProductName;

    public static Module[][] loadGrid(String fileName){
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found!");
            return null;
        }

        // Loading Grid Size
        int xSize = scanner.nextInt();
        int ySize = scanner.nextInt();
        int nSize = scanner.nextInt();

        Module[][] grid = new Module[xSize][ySize];

        // Loading Grid
        for(int i = 0; i < ySize; i++){
            String row = scanner.next();
            for(int j = 0; j < xSize; j++){
                char type = row.charAt(j);
                switch(type){
                    case 'H':
                        grid[j][i] = new TypeH(nSize);
                        break;
                    case 'B':
                        grid[j][i] = new TypeB(nSize);
                        break;
                    case 'S':
                        grid[j][i] = new TypeS(nSize);
                        break;
                    case 'O':
                        grid[j][i] = new TypeO(nSize);
                        break;
                }
            }
        }

        // Loading items
        while(scanner.hasNext()){
            String productName = scanner.next();
            int xLocation = scanner.nextInt();
            int yLocation = scanner.nextInt();
            int nLayer = scanner.nextInt();

            grid[xLocation][yLocation].getItems()[nLayer] = productName;
        }

        scanner.close();
        return grid;
    }

    public static boolean loadJob(String fileName){
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found!");
            return false;
        }

        botStartLocation = new int[2];
        botDesiredLocation = new int[2];

        botStartLocation[0] = scanner.nextInt();
        botStartLocation[1] = scanner.nextInt();

        botDesiredLocation[0] = scanner.nextInt();
        botDesiredLocation[1] = scanner.nextInt();

        botDesiredProductName = scanner.next();

        scanner.close();
        return true;
    }

    public static void main(String[] args) {
        if(args.length != 2)
            System.out.println("Incorrect amount of arguments! [Expected 2]");

        Module[][] grid = loadGrid(args[0]);

        if(grid == null) return;
        if(!loadJob(args[1])) return;

        System.out.println("Wczytywanie dziala!");
    }


}
