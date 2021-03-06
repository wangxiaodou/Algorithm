package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class SolutionDay24_P3068 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/3068.txt";
	
	void solve() {
		
		int cnt = 0;
		while (true){
			int N = ni();
			int M = ni();
			if (N + M == 0) break;
			init (N + 2);
			int s = 0;
			int t = N + 1;
			addEdge(s, 1, 2, 0);
			addEdge(N, t, 2, 0);
			for (int i = 0; i < M; ++i){
				int from = ni();
				int to = ni();
				int cost = ni();
				addEdge(from + 1, to + 1, 1, cost);
			}
			int minCost = minCostFlow(s, t, 2);
			if (minCost == -1){
				out.println("Instance #" + (++cnt) + ": Not possible");
			}
			else{
				out.println("Instance #" + (++cnt) + ": " + minCost);
			}
		}
	}
	
	//最小费用流 Bellman Ford
	class Edge{
		int from;
		int to;
		int cost;
		int cap;
		int rev;
		public Edge(int from, int to, int cap, int cost, int rev){
			this.from = from;
			this.to = to;
			this.cap = cap;
			this.cost = cost;
			this.rev = rev;
		}
	}
	
	static final int INF = 1 << 29;
	List<Edge>[] g;
	int V;
	int[] prevv;
	int[] preve;
	int[] distance;
	public void init(int n){
		V = n;
		g = new ArrayList[V];
		for (int i = 0; i < V; ++i) g[i] = new ArrayList<Edge>();
		prevv = new int[V];
		preve = new int[V];
		distance = new int[V];
	}
	
	public void addEdge(int from, int to, int cap, int cost){
		g[from].add(new Edge(from, to, cap, cost, g[to].size()));
		g[to].add(new Edge(to, from, 0, -cost, g[from].size() - 1));
	}
	
	public int minCostFlow(int s, int t, int f){
		int res = 0;
		while (f > 0){
			boolean update = true;
			Arrays.fill(distance, INF);
			distance[s] = 0;
			while (update){
				update = false;
				for (int v = 0; v < V; ++v){
					if (distance[v] == INF) continue;
					for (int i = 0; i < g[v].size(); ++i){
						Edge e = g[v].get(i);
						if (e.cap > 0 && distance[e.to] > distance[e.from] + e.cost){
							distance[e.to] = distance[e.from] + e.cost;
							prevv[e.to] = v;
							preve[e.to] = i;
							update = true;
						}
					}
				}
			}
			
			if (distance[t] == INF){
				return -1;
			}
			
			int d = f;
			for (int v = t; v != s; v = prevv[v]){
				d = Math.min(d, g[prevv[v]].get(preve[v]).cap);
			}
			
			f -= d;
			res += d * distance[t];
			for (int v = t; v != s; v = prevv[v]){
				Edge e = g[prevv[v]].get(preve[v]);
				e.cap -= d;
				g[v].get(e.rev).cap += d;
			}
		}
		return res;
	}
	
	void run() throws Exception {
		is = oj ? System.in : new FileInputStream(new File(INPUT));
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay24_P3068().run();
	}

	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private double nd() {
		return Double.parseDouble(ns());
	}

	private char nc() {
		return (char) skip();
	}

	private String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != '
									// ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	private void tr(Object... o) {
		if (!oj)
			System.out.println(Arrays.deepToString(o));
	}
}


