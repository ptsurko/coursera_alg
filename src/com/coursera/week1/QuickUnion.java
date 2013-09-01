package com.coursera.week1;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 9/1/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuickUnion implements IUnionFind {
    private int[] id;
    private int count;
    public QuickUnion(int N) {
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int groupToReplaceWith = find(p), groupToReplace = find(q);

        if(groupToReplaceWith != groupToReplace){
            id[q] = groupToReplaceWith;
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
