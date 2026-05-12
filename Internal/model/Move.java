package model;

public class Move {

    private String name;

    private String type;

    private int power;

    private int accuracy;

    private int pp;

    public Move(String name, String type, int power, int accuracy, int pp) {

        this.name = name;

        this.type = type;

        this.power = power;

        this.accuracy = accuracy;

        this.pp = pp;

    }

    @Override

    public String toString() {

        return "move " + name

                + " type \"" + type + "\""

                + " power " + power

                + " accuracy " + accuracy

                + " pp " + pp;

    }

}
