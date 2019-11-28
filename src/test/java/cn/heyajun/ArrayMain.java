package cn.heyajun;

import sun.reflect.generics.tree.Tree;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ArrayMain {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Integer num = sc.nextInt();
            Set<Integer> sets = new TreeSet<>();
            for (int i = 0; i < num; i++) {
                sets.add(sc.nextInt());
            }
            for (Integer m : sets) {
                System.out.println(m);
            }
        }
    }
    }
