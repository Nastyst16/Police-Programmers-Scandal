##### Copyright Nastase Cristian-Gabriel 325CA

# PA - Homework 2 - Police-Programmers-Scandal

    In every problem i used the given MyScanner code to read the input.

### Problem 1: Counting

    Complexity: O(n^3)
    Step 1: Reading the input from line 15 to 41
    Step 2: Utilizing dynamic programming: The dp array is used to keep track
            of the number of ways to reach each node in the graphs from node 1.
            Initially dp[1] = 1 because we start from node 1. For each node "x"
            the program checks reachable nodes "i" from graph1 and "j" in graph2.
            If i == j updates dp[i]. Because of this only common paths are
            counted. The final result is stored in dp[n].
    Step 3: Printing the output.

### Problem 2: Trains

    For this problem unfortunately I used chatGPT, because with my implementation
    i was able to get only 21 points, so i gave chatGPT my code and he gave me
    almost the same solution but implemented with dinamyic programming.

    Complexity: O(n^2)
    Step 1: Reading the input from line 23 to 48.
    Step 2: In the reading, I counted for every node the number of paths to it.
    Step 3: Topological Sort the graph using the "gradIntrare" which is what
            I explained at step 2.
    Step 4: Giving an index for each city.
    Step 5: The dp array tracks the max number of distinct cities that can be visited
            starting from "startCity". The dp is updated based on the topological
            order. If it is a valid path dp[v] is updated. The maxDistance we are
            looking for is finally stored in dp[cityIndex.get(endCity)] (the dp
            corresponding to the endCity).
    Step 6: Printing the output.

### Problem 3: Mandatory Roads

    Complexity: O(2 * m * logn)
    I inspired from radu nichita public solution of diskjtra algorithm.
    I built the adj list based on that function. So:
    Step 1: Reading the input from line 37 -> 63
    Step 2: Applying the dijkstra algorithm for node x
    Step 3: Applying the dijkstra algorithm for node y
    Step 4: a = distFromX[z], b = distFromY[z];
    Step 5: Printing the output: a + b;
    Very simple :)