package lanQiaoBei;

import java.io.IOException;
import java.util.Scanner;

//编程实现两个复数的运算。设有两个复数 和 ，则他们的运算公式为：
//
//要求：（1）定义一个结构体类型来描述复数。
//（2）复数之间的加法、减法、乘法和除法分别用不用的函数来实现。
//（3）必须使用结构体指针的方法把函数的计算结果返回。
//说明：用户输入：运算符号(+,-,*,/) a b c d.
//输出：a+bi，输出时不管a,b是小于0或等于0都按该格式输出，输出时a,b都保留两位。
//
//输入：
//- 2.5 3.6 1.5 4.9
//输出：
//1.00+-1.30i

public class fuShu {

    public static void main(String[] args) {
        class Complex {
            private double real;
            private double image;
            public Complex() {
            }
            public Complex(double real, double image) {
                this.real = real;
                this.image = image;
            }
            public double getReal() {
                return real;
            }
            public void setReal(double real) {
                this.real = real;
            }
            public double getImage() {
                return image;
            }
            public void setImage(double image) {
                this.image = image;
            }

            //加
            public Complex add(Complex com) {
                double real1 = com.getReal();
                double image1 = com.getImage();
                double m = this.real + real1;
                double n = this.image + image1;
                return new Complex(m, n);
            }

            //减
            public Complex sub(Complex com) {
                double real1 = com.getReal();
                double image1 = com.getImage();
                double m = this.real - real1;
                double n = this.image - image1;
                return new Complex(m, n);
            }

            //乘
            public Complex mul(Complex com) {
                double real1 = com.getReal();
                double image1 = com.getImage();
                double m = this.real * real1 - this.image * image1;
                double n = this.image * real1 + this.real * image1;
                return new Complex(m, n);
            }

            //除
            public Complex div(Complex com) {
                double real1 = com.getReal();
                double image1 = com.getImage();
                double m = (real * real1 + image * image1)
                        / (real1 * real1 + image1 * image1);
                double n = (image * real1 - real * image1)
                        / (real1 * real1 + image1 * image1);
                return new Complex(m, n);
            }
            public void print() {
                System.out.printf("%.2f+%.2fi", real, image);
            }
        }

        Scanner sc = new Scanner(System.in);
        char fuhao = 0;
        try {
            fuhao = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double real = sc.nextDouble();
        double image = sc.nextDouble();
        double real1 = sc.nextDouble();
        double image1 = sc.nextDouble();
        Complex com1 = new Complex(real, image);
        Complex com2 = new Complex(real1, image1);
        Complex com = new Complex();
        switch (fuhao) {
            case '+' :
                com = com1.add(com2);
                com.print();
                break;
            case '-' :
                com = com1.sub(com2);
                com.print();
                break;
            case '*' :
                com = com1.mul(com2);
                com.print();
                break;
            case '/' :
                com = com1.div(com2);
                com.print();
                break;
            default :
                break;
        }
    }

}
