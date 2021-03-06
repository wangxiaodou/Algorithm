package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutionDay19_L0749 {
	
	Set<Integer> vis;
	List<Set<Integer>> frontiers;
	List<Set<Integer>> regions;
	List<Integer> perimeters;
	int[][] grid;
	int n, m;
	
	int[][] dir = {{0, 1},{1, 0},{0, -1},{-1, 0}};
	
	public int containVirus(int[][] grid) {
		this.grid = grid;
		n = grid.length;
		m = grid[0].length;
		int ans = 0;
		while (true) {
			vis = new HashSet<>();
			frontiers = new ArrayList<>();
			regions = new ArrayList<>();
			perimeters = new ArrayList<>();
			// 求区域对应的周长，边界和区域个数
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					if (grid[i][j] ==1 && !vis.contains(i * m + j)) {
						frontiers.add(new HashSet<>());
						regions.add(new HashSet<>());
						perimeters.add(0);
						dfs(i, j);
					}
				}
			}
			
			if (regions.isEmpty()) break;
			int triageIndex = 0;
            for (int i = 0; i < frontiers.size(); ++i) {
                if (frontiers.get(triageIndex).size() < frontiers.get(i).size())
                    triageIndex = i;
            }
            ans += perimeters.get(triageIndex);

            for (int i = 0; i < regions.size(); ++i) {
                if (i == triageIndex) {
                    for (int code: regions.get(i))
                        grid[code / m][code % m] = -1;
                } else {
                    for (int code: regions.get(i)) {
                        int r = code / m, c = code % m;
                        for (int[] d : dir) {
                            int nr = r + d[0], nc = c + d[1];
                            if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 0)
                                grid[nr][nc] = 1;
                        }
                    }
                }
            }
		}
		
		return ans;
	}
	
	public void dfs(int r, int c) {
		if (!vis.contains(r * m + c)) {
			vis.add(r * m + c);
			int N = regions.size();
            regions.get(N - 1).add(r * m + c);
            for (int[] d : dir) {
            	int nr = d[0] + r;
            	int nc = d[1] + r;
            	if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
            		if (grid[nr][nc] == 1) {
            			dfs(nr, nc);
            		}
            		else {
            			frontiers.get(N - 1).add(nr * m + nc);
                        perimeters.set(N - 1, perimeters.get(N - 1) + 1);
            		}
            	}
            }
		}
	}

}
