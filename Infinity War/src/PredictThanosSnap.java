package avengers;

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes. 
 * Then, write into the output file a boolean (true or false) indicating if 
 * the graph is still connected.
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 * Read from PredictThanosSnapInputFile with the format:
 *    1. seed (long): a seed for the random number generator
 *    2. p (int): number of people (vertices in the graph)
 *    2. p lines, each with p edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note: the last p lines of the PredictThanosSnapInputFile is an ajacency matrix for
 * an undirected graph. 
 * 
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the matrix, 0-1, 1-0, 0-2, 2-0).
 * 
 * 0 1 1 0
 * 1 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * Delete random vertices from the graph. You can use the following pseudocode.
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) { 
 *     if (StdRandom.uniform() <= 0.5) { 
 *          delete vertex;
 *     }
 * }
 * Answer the following question: is the graph (after deleting random vertices) connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 * 
 * Note 1: a connected graph is a graph where there is a path between EVERY vertex on the graph.
 * 
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, isConnected is true if the graph is connected,
 *   false otherwise):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(isConnected);
 * 
 * @author Yashas Ravi
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/PredictThanosSnap predictthanossnap.in predictthanossnap.out
*/

public class PredictThanosSnap {
	 
    public static void main (String[] args) {
 
        if ( args.length < 2 ) {
            StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>");
            return;
        }
        
    	// WRITE YOUR CODE HERE
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        long seed = StdIn.readLong();
        int people = StdIn.readInt();
        int[][] peopleArr = new int[people][people];
        boolean[] deleteVert = new boolean[people];
        StdRandom.setSeed(seed);


        for (int i = 0; i<people; i++){
            for (int j = 0; j<people; j++){
                int edgeVal = StdIn.readInt();
                peopleArr[i][j] = edgeVal;
                peopleArr[j][i] = edgeVal;
            }
        }
        for (int i = 0; i<people; i++){ //delete verticies
            if (StdRandom.uniform()<=0.5){
                for (int j = 0; j<people; j++){
                    peopleArr[j][i] = 0;
                    peopleArr[i][j] = 0;
                    deleteVert[i] = true;
                }
            }
        }
        
        //check if graph is still connected
        for (int i = 0; i<people; i++){
            if (deleteVert[i]==false){
                deleteVert = dfs(i, peopleArr, deleteVert);
                break;
            }
        }
        for (int i = 0; i<people; i++){
            if (deleteVert[i] == false){
                StdOut.println(false);
                return;
            }
        }
        StdOut.println(true);
    }
      
    public static boolean[] dfs(int people, int[][] peopleArr, boolean[] deleted){
        deleted[people] = true;
        for (int i = 0; i< peopleArr.length; i++){
            //is there an edge
            if (peopleArr[people][i] == 1){
                if (deleted[i]==false){
                    dfs(i, peopleArr, deleted);
                }
            }
        }
        return deleted;
    }
    

    
    }

