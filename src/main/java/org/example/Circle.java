package org.example;

public class Circle {
    private final double radius;
    private final Point center;
    public Circle(double radius, Point center){
        if (radius <= 0){
            throw new IllegalArgumentException("Радиус должен быть больше нуля!");
        }
        this.radius = radius;
        this.center = center;
    }
    public double getArea(){
        return (double) Math.PI * radius * radius;
    }
    public double getPerimeter(){
        return (double) 2 * Math.PI * radius;
    }
    public double getRadius() {
        return radius;
    }
    public Point getCenter(){
        return center;
    }
    @Override
    public String toString() {
        return "Окружность [Центр: " + center + ", Радиус: " + radius + ", Площадь: " + getArea() + "]";
    }
}
