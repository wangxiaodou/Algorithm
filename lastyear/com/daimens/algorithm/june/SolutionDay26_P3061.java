package com.daimens.algorithm.june;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay26_P3061 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/3061.txt";
	
//	void solve() {
//		int N = ni();
//		for (int i = 0; i < N; ++i){
//			int n = ni();
//			int s = ni();
//			int[] a = na(n);
//			int[] sum = new int[n+1];
//			for (int j = 0; j < n; ++j){
//				sum[j+1] = sum[j] + a[j];
//			}
//			
//			if (sum[n] < s){
//				out.println(0);
//				continue;
//			}
//			
//			int min = 1 << 30;
//			for (int j = 1; j < n + 1; ++j){
//				int l = binarySearch(sum, 0, j - 1, sum[j] - s);
//				if (l != -1){
//					min = Math.min(min, j - l);
//				}
//			}
//			out.println(min);
//		}
//	}
	
	void solve() {
		int N = ni();
		for (int i = 0; i < N; ++i){
			int n = ni();
			int s = ni();
			int[] a = na(n);
			
			int l = 0, j = 0, sum = 0;
			int min = n + 1;
			for (;;){
				while (j < n && sum < s){
					sum += a[j++];
				}
				if (sum < s) break;
				min = Math.min(min, j - l);
				sum -= a[l++];
			}
			out.println(min > n ? 0 : min);
		}
	}
	
	public int binarySearch(int[] sum, int lf, int rt, int key){
		while (lf < rt){
			int mid = lf + (rt - lf + 1) / 2;
			if (sum[mid] > key) rt = mid - 1;
			else lf = mid;
		}
		if (sum[lf] <= key) return lf;
		return -1;
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
		new SolutionDay26_P3061().run();
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

//void solve() {
//	int N = ni();
//	for (int i = 0; i < N; ++i){
//		int n = ni();
//		int s = ni();
//		int[] a = na(n);
//		int[] sum = new int[n+1];
//		for (int j = 0; j < n; ++j){
//			sum[j+1] = sum[j] + a[j];
//		}
//		
//		if (sum[n] < s){
//			out.println(0);
//			continue;
//		}
//		
//		int min = 1 << 30;
//		for (int j = 1, k = 0; j < n + 1; ++j){
//			int key = sum[j] - s;
//			if (key >= 0){
//				while (k < j && sum[k] <= key) k++;
//				min = Math.min(min, j - k + 1);
//			}
//		}
//		out.println(min);
//	}
//}
