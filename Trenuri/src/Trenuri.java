import java.io.*;
import java.util.*;

public class Trenuri {

	static List<String> topoOrder = new ArrayList<>();

	public static void main(String[] args) throws IOException {

//		String fileName = "C:\\Users\\crist\\Desktop\\An2_Sem2\\PA\\tema2\\Police-Programmers-Scandal\\Trenuri\\src\\trenuri.in";
		String fileName = "trenuri.in";

		MyScanner sc = new MyScanner(new FileReader(fileName));

		String startCity = sc.next();
		String endCity = sc.next();

		int M = sc.nextInt();

		Map<String, List<String>> graph = new HashMap<>();
		Map<String, Integer> gradIntrare = new HashMap<>();
		Set<String> toateOrasele = new HashSet<>();

		for (int i = 0; i < M; i++) {
			String from = sc.next();
			String to = sc.next();

			graph.putIfAbsent(from, new ArrayList<>());
			graph.get(from).add(to);

			gradIntrare.put(to, gradIntrare.getOrDefault(to, 0) + 1);
			gradIntrare.putIfAbsent(from, 0);

			toateOrasele.add(from);
			toateOrasele.add(to);
		}

		// topological sort
		topologicalSort(graph, gradIntrare, toateOrasele);

		// map city names to indices
		Map<String, Integer> cityIndex = new HashMap<>();
		int index = 0;
		for (String city : toateOrasele) {
			cityIndex.put(city, index++);
		}

		// xreate distance array
		int[] dp = new int[toateOrasele.size()];
		Arrays.fill(dp, -1);

		// set the starting point
		dp[cityIndex.get(startCity)] = 1;

		// get the index of startCity from topoOrder
		int startCityIndex = topoOrder.indexOf(startCity);

		// DP calculation based on topological order
		for (int i = startCityIndex; i < topoOrder.size(); i++) {
			String city = topoOrder.get(i);
			int u = cityIndex.get(city);
			if (dp[u] != -1 && graph.containsKey(city)) {
				for (String neighbor : graph.get(city)) {
					int v = cityIndex.get(neighbor);
					dp[v] = Math.max(dp[v], dp[u] + 1);
				}
			}
		}

		// maxDistance is the maximum distance from startCity to endCity
		int maxDistance = dp[cityIndex.get(endCity)];

		String outFile = "trenuri.out";
		PrintWriter out = new PrintWriter(new FileWriter(outFile));

		try {
			out.println(maxDistance);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}


	}

	// topological sort
	static void topologicalSort(Map<String, List<String>> graph, Map<String,
								Integer> gradIntrare, Set<String> toateOrasele) {

		Queue<String> queue = new LinkedList<>();

		for (String city : toateOrasele) {
			if (gradIntrare.get(city) == 0) {
				queue.add(city);
			}
		}

		while (!queue.isEmpty()) {
			String current = queue.poll();
			topoOrder.add(current);

			if (graph.containsKey(current)) {
				for (String neighbor : graph.get(current)) {
					gradIntrare.put(neighbor, gradIntrare.get(neighbor) - 1);
					if (gradIntrare.get(neighbor) == 0) {
						queue.add(neighbor);
					}
				}
			}
		}

	}
}
