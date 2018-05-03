package lanQiaoBei;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//问题描述
//﻿队列操作题。根据输入的操作命令，操作队列（1）入队、（2）出队并输出、（3）计算队中元素个数并输出。
//输入格式
//第一行一个数字N。
//下面N行，每行第一个数字为操作命令（1）入队、（2）出队并输出、（3）计算队中元素个数并输出。
//输出格式
//若干行每行显示一个2或3命令的输出结果。注意：2.出队命令可能会出现空队出队（下溢），请输出“no”，并退出。
//样例输入
public class duilie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue que = new LinkedList<>();
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            if (sc.nextInt() == 1) {
                que.offer(sc.nextInt());
            } else if (sc.nextInt() == 2) {
                if (que.size() != 0) {
                    System.out.println(que.poll());
                } else {
                    System.out.println("no");
                }
            } else if (sc.nextInt() == 3) {
                System.out.println(que.size());
            }
        }
    }
    //    public static void main(String[] args) {
    //        Scanner in = new Scanner(System.in);
    //        int n = in.nextInt();
    //        Queue que = new LinkedList();
    //        for (int i = 0; i < n; i++) {
    //            int num = in.nextInt();
    //            if (num == 1) {
    //                int m = in.nextInt();
    //                //进队  
    //                que.offer(m);
    //            } else if (num == 2) {
    //                if (que.isEmpty()) {
    //                    System.out.println("no");
    //                    return;
    //                } else {
    //                    //出队并删除  
    //                    System.out.println(que.poll());
    //                }
    //            } else if (num == 3) {
    //                System.out.println(que.size());
    //            }
    //        }
    //    }
}
