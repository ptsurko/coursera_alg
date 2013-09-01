package com.coursera.week1;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 9/1/13
 * Time: 7:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightedQuickUnion implements IUnionFind {
    private int[] id;
    private int[] weights;
    private int count;
    public WeightedQuickUnion(int N) {
        count = N;
        id = new int[N];
        weights = new int[N];

        for(int i = 0; i < N; i++) {
            id[i] = i;
            weights[i] = 1;
        }
    }

    public void union(int p, int q) {
        int groupToReplaceWith = find(p), groupToReplace = find(q);

        if(groupToReplaceWith != groupToReplace){
            if(weights[groupToReplaceWith] > weights[groupToReplace]) {
                id[q] = groupToReplaceWith;
                weights[groupToReplaceWith] += weights[groupToReplace];
            } else {
                id[p] = groupToReplace;
                weights[groupToReplace] += weights[groupToReplaceWith];
            }

            count--;
        }
    }

    public int find(int p) {
        while(id[p] != p) {
            p = id[p];
        }
        return id[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
