package com.example.task_management;

public class Rectangle implements Shape{

    private double width, height;

    private Type type;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
        this.type = Type.RECTANGLE;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return this.width * this.height;
    }

    @Override
    public double getPerimeter() {
        return 2*(this.width + this.height);
    }

    @Override
    public Type getType() {
        return this.type;
    }
}
