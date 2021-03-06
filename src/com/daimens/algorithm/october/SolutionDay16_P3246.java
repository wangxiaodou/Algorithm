package com.daimens.algorithm.october;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SolutionDay16_P3246 {
	
	String INPUT = "./data/judge/201710/P3246.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay16_P3246().run();
	}
	
	class P implements Comparable<P>{
		int x;
		int y;
		
		int id;
		
		P(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		P sub(P a) {
			return new P(x - a.x, y - a.y);
		}
		
		int det(P a) {
			return x * a.y - y * a.x;
		}

		@Override
		public int compareTo(P o) {
			return x != o.x ? x - o.x : y - o.y;
		}
	}
	
	int N;
	List<P> ps;
	
	double area(P a, P b, P c) {
		double ans = (c.x - a.x) * (b.y - a.y) - (c.y - a.y) * (b.x - a.x);
		return 0.5 * Math.abs(ans);
	}
	
	P[] convexHull(P[] p, int n) {
		Arrays.sort(p, 0, n);
		
		P[] qs = new P[n * 2];
		
		int k = 0;
		for (int i = 0; i < n; ++i) {
			while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(p[i].sub(qs[k - 1])) < 0) k--;
			qs[k++] = p[i];
		}
		
		for (int i = n - 2, t = k; i >= 0; --i) {
			while (k > t && qs[k - 1].sub(qs[k - 2]).det(p[i].sub(qs[k - 1])) < 0) k--;
			qs[k++] = p[i];
		}
		
		P[] res = new P[k - 1];
		System.arraycopy(qs, 0, res, 0, k - 1);
		return res;
	}
	
	double polygonArea(P[] qs) {
		double sum = 0;
		int n = qs.length;
		for (int i = 1; i < n - 1; ++i) {
			sum += area(qs[0], qs[i], qs[i + 1]);
		}
		return sum;
	}
	
	void solve(P[] p) {
		P[] qs = convexHull(p, N);
		int n = qs.length;
		
		double min = Double.MAX_VALUE;
		for (int i = 0; i < n; ++i) {
			swap(p, i, N - 1);
			min = Math.min(min, polygonArea(convexHull(p, N - 1)));
		}
		out.printf("%.2f\n", min);
	}
	
	void swap(P[] p, int i, int j) {
		P tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
	}
	
	void read() {
		while (true) {
			N = ni();
			if (N == 0) break;
			P[] p = new P[N];
			ps = new ArrayList<P>();
			for (int i = 0; i < N; ++i) {
				p[i] = new P(ni(), ni());
				p[i].id = i;
				ps.add(p[i]);
			}
			solve(p);
		}
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\oxygen_workspace\\Algorithm");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}
		
		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		read();
		out.flush();
		if (!oj){
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}
	
	public boolean more(){
		return in.hasNext();
	}
	
	public int ni(){
		return in.nextInt();
	}
	
	public long nl(){
		return in.nextLong();
	}
	
	public double nd(){
		return in.nextDouble();
	}
	
	public String ns(){
		return in.nextString();
	}
	
	public char nc(){
		return in.nextChar();
	}
	
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean hasNext;

		public FastScanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			hasNext = true;
		}

		public String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					hasNext = false;
					return "##";
				}
			}
			return st.nextToken();
		}
		
		String next = null;
		public boolean hasNext(){
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}
		
		public String nextString(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}
		
		public char nextChar(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}
}

