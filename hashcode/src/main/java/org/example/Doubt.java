package org.example;
class Doubt1 {

    public int m1()
    {
        System.out.println("m1");
        return 1;
    }
}
class Doubt extends Doubt1
{
    public String m1()
    {
        System.out.println("m2");
        return "bg";

    }
    public static void main(String[] args) {

    }
}
