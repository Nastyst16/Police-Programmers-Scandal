import java.io.*;
import java.util.*;

public class Numarare {
	static final int MOD = 1000000007;
	static int N, M;
	static List<Integer>[] graph1, graph2;
	static int[] dp;

	public static void main(String[] args) throws IOException {

//		String fileName = "C:\\Users\\crist\\Desktop\\An2_Sem2\\PA\\tema2\\Police-Programmers-Scandal\\Numarare\\src\\numarare.in";
        String fileName = "numarare.in";

		MyScanner sc = new MyScanner(new FileReader(fileName));


		String[] line = sc.nextLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		graph1 = new ArrayList[N+1];
		graph2 = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			graph1[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			line = sc.nextLine().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			graph1[u].add(v);
		}

		for (int i = 0; i < M; i++) {
			line = sc.nextLine().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			graph2[u].add(v);
		}

		dp = new int[N+1];
		Arrays.fill(dp, -1);


		// writing to file
		String fileNameOut = "numarare.out";

		int rez = solve(1);

		try {
			PrintWriter fout = new PrintWriter(fileNameOut);
			fout.println(rez);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static int solve(int u) {
		int[] dp = new int[N+1];
		dp[u] = 1;

		for (int x = u; x <= N; x++) {
			for (int i : graph1[x]) {
				for (int j : graph2[x]) {
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

		return dp[N];
	}



}

