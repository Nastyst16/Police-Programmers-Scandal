import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Numarare {
	static final int MOD = 1000000007;
	static int N, M;
	static ArrayList<Integer>[] graph1, graph2;

	public static void main(String[] args) throws IOException {

		String fileName = "numarare.in";

		MyScanner sc = new MyScanner(new FileReader(fileName));

		N = sc.nextInt();
		M = sc.nextInt();

		graph1 = new ArrayList[N + 1];
		graph2 = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph1[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}

		// reading the first graph
		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();

			graph1[u].add(v);
		}

		// reading the second graph
		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();

			graph2[u].add(v);
		}

		// dp[i] = number of ways to reach node i
		int[] dp = new int[N + 1];
		dp[1] = 1;

		for (int x = 1; x <= N; x++) {
			for (int i : graph1[x]) {
				for (int j : graph2[x]) {
					// for optimization
					if (i < j) {
						break;
					}

					if (i == j) {
						dp[i] = (dp[i] + dp[x]) % MOD;
						break;
					}
				}
			}
		}


		// writing to file
		String fileNameOut = "numarare.out";

		try {
			PrintWriter fout = new PrintWriter(fileNameOut);
			fout.println(dp[N]);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

