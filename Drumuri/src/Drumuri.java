import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class DijkstraResult {
	List<Integer> d;
	List<Integer> p;

	DijkstraResult(List<Integer> _d, List<Integer> _p) {
		d = _d;
		p = _p;
	}
};

class Pair implements Comparable<Pair> {
	int node;
	long cost;

	Pair(int node, long cost) {
		this.node = node;
		this.cost = cost;
	}

	@Override
	public int compareTo(Pair other) {
		return Long.compare(this.cost, other.cost);
	}
}


public class Drumuri {

	// numarul maxim de noduri
	public static final int NMAX = 100005;

	// valoare mai mare decat orice distanta din graf
//	public static final double INF = Double.MAX_VALUE;
	public static final long INF = Long.MAX_VALUE;

//////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws FileNotFoundException {

//		String fileName = "C:\\Users\\crist\\Desktop\\An2_Sem2\\PA\\tema2\\Police-Programmers-Scandal\\Drumuri\\src\\drumuri.in";
		String fileName = "drumuri.in";

		MyScanner sc = new MyScanner(new FileReader(fileName));

		// n = numar de noduri, m = numar de muchii
		int n, m;
		// nodul sursa
		int source;

		// adj[node] = lista de adiacenta a nodului node
		// perechea (neigh, w) semnifica arc de la node la neigh de cost w
		ArrayList<Pair>[] adj = new ArrayList[NMAX]; // schimba aici nu "noduri" posibil


		n = sc.nextInt();
		m = sc.nextInt();

		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		// read input
		for (long i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			long w = sc.nextInt();
			adj[u].add(new Pair(v, w));
		}

		int x, y, z;
		x = sc.nextInt();
		y = sc.nextInt();
		z = sc.nextInt();
		sc.close();

		long[] distFromX = dijkstra(x, adj, n);
		long[] distFromY = dijkstra(y, adj, n);

		long a = distFromX[z];
		long b = distFromY[z];

		// writing to file
		String fileNameOut = "drumuri.out";
		try {
			PrintWriter fout = new PrintWriter(fileNameOut);

			fout.println(a + b);

			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static long[] dijkstra(int start, ArrayList<Pair>[] adj, int n) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		long[] dist = new long[n + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.add(new Pair(start, 0));

		while (!pq.isEmpty()) {
			Pair current = pq.poll();
			int u = current.node;
			long costU = current.cost;

			if (costU > dist[u]) continue;

			for (Pair neighbor : adj[u]) {
				int v = neighbor.node;
				long costV = neighbor.cost;

				if (dist[u] + costV < dist[v]) {
					dist[v] = dist[u] + costV;
					pq.add(new Pair(v, dist[v]));
				}
			}
		}

		return dist;
	}
}