package com.example.task_management;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Rectangle r1 = new Rectangle(3,4);
        Rectangle r2 = new Rectangle(7,2);

        Circle c1=new Circle(5);
        Circle c2=new Circle(9);

        ArrayList<Shape> shapes = new ArrayList<Shape>();

        shapes.add(r1);
        shapes.add(c1);
        shapes.add(r2);
        shapes.add(c2);

        for(Shape sh: shapes){
            System.out.println(sh.getType() + ": "+sh.getArea());
        }
    }
}