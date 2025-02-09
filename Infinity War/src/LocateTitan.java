package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }

    	// WRITE YOUR CODE HERE
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        int g = StdIn.readInt();
        int adjMatrix[][] = new int[g][g];
        int cost[][] = new int[g][g];
        double[] funcValueArr = new double[g];
        int currentSource = 0;

        int count = 0;
        while (count < g){
            funcValueArr[StdIn.readInt()] = StdIn.readDouble();
            count++;
        }
        for (int i = 0; i<g; i++){
            for (int j = 0; j<g; j++){
                int edgeVal = StdIn.readInt();
                adjMatrix[i][j] = edgeVal;
                adjMatrix[j][i] = edgeVal;
            }
        }
        for (int i = 0; i<g; i++){
            for (int j = 0; j<g; j++){
                double totCost = adjMatrix[i][j]/(funcValueArr[i]*funcValueArr[j]);
                int totalCost = (int) totCost;
                cost[i][j] = totalCost;
                cost[j][i] = totalCost;     
            }
        }

        int[] minCost = new int[g];
        boolean[] dSet = new boolean[g];
        
        for (int i = 1; i<g; i++){
            if (i==0){
                minCost[i] =0;
            }
            else{
                minCost[i] = Integer.MAX_VALUE;
            }
            } //set all values to infinity except first one
      
        for (int i = 0; i<g-1; i++){
            int tempMin = Integer.MAX_VALUE; //determine vertex w minimum cost from 0
            int temp = 0;
             for (int j = 0; j<g; j++){
                 if ((minCost[j]<=tempMin)&&(dSet[j]==false)){
                    temp = j;
                    tempMin = minCost[j];
        }
    }
            currentSource = temp;
            dSet[currentSource] = true;
            for (int w = 0; w<g; w++){
                if ((dSet[w]==false)&&(minCost[currentSource]!=Integer.MAX_VALUE) && (minCost[w] > (minCost[currentSource] + cost[currentSource][w])) && (cost[currentSource][w]!=0)){
                    minCost[w] = minCost[currentSource] + cost[currentSource][w];
                }
            }
        
}
StdOut.print(minCost[g-1]);

    }


    }

