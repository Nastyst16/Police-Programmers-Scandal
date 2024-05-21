import java.io.*;
import java.util.*;

public class Trenuri {

	static List<String> topoOrder = new ArrayList<>();

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("trenuri.in"));

		String fileName = "C:\\Users\\crist\\Desktop\\An2_Sem2\\PA\\tema2\\Police-Programmers-Scandal\\Trenuri\\src\\trenuri.in";
//		String fileName = "trenuri.in";

		MyScanner br = new MyScanner(new FileReader(fileName));

		PrintWriter pw = new PrintWriter(new FileWriter("trenuri.out"));

		String startCity = br.next();
		String endCity = br.next();

		int M = br.nextInt();

		Map<String, List<String>> graph = new HashMap<>();
		Map<String, Integer> inDegree = new HashMap<>();
		Set<String> allCities = new HashSet<>();

		for (int i = 0; i < M; i++) {
			String from = br.next();
			String to = br.next();

			graph.putIfAbsent(from, new ArrayList<>());
			graph.get(from).add(to);

			inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
			inDegree.putIfAbsent(from, 0);

			allCities.add(from);
			allCities.add(to);
		}

		topologicalSort(graph, inDegree, allCities);


		// Longest Path in DAG
		Map<String, Integer> distances = new HashMap<>();
		for (String city : allCities) {
			distances.put(city, 0);
		}

		for (String city : topoOrder) {
			if (graph.containsKey(city)) {
				for (String neighbor : graph.get(city)) {
					if (distances.get(neighbor) < distances.get(city) + 1) {
						distances.put(neighbor, distances.get(city) + 1);
					}
				}
			}
		}

		int maxDistance = distances.get(endCity) + 1;

		String outFile = "trenuri.out";

		try {
			pw.println(maxDistance);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}


	}


	// Topological Sort
	static void topologicalSort(Map<String, List<String>> graph, Map<String, Integer> inDegree, Set<String> allCities) {

		Queue<String> queue = new LinkedList<>();

		for (String city : allCities) {
			if (inDegree.get(city) == 0) {
				queue.offer(city);
			}
		}

		while (!queue.isEmpty()) {
			String current = queue.poll();
			topoOrder.add(current);

			if (graph.containsKey(current)) {
				for (String neighbor : graph.get(current)) {
					inDegree.put(neighbor, inDegree.get(neighbor) - 1);
					if (inDegree.get(neighbor) == 0) {
						queue.offer(neighbor);
					}
				}
			}
		}

	}
}
