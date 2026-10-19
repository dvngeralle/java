package org.example;
public record Employee(String name) {
    @Override
    public String toString(){
        return name;
    }
}