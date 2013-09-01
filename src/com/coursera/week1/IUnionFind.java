package com.coursera.week1;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 9/1/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IUnionFind {
    void union(int p, int q);
    int find(int p);
    boolean connected(int p, int q);
    int count();
}
