package com.example.task_management;

public class Circle implements Shape{

    private double radius;
    private Type type;

    public Circle(double radius) {
        this.radius = radius;
        this.type = Type.CIRCLE;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI*radius*radius;
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public Type getType() {
        return this.type;
    }
}
