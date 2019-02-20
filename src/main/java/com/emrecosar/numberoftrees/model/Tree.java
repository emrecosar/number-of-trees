package com.emrecosar.numberoftrees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Comparator;

public class Tree implements Comparator<Tree> {

    private double x;
    private double y;

    @JsonIgnore
    private Float angle;

    @JsonIgnore
    private long countofTreesTill;

    public Tree() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Float getAngle() {
        return angle;
    }

    public void setAngle(Float angle) {
        this.angle = angle;
    }

    public long getCountofTreesTill() {
        return countofTreesTill;
    }

    public void setCountofTreesTill(long countofTreesTill) {
        this.countofTreesTill = countofTreesTill;
    }

    public void calculateAngle() {
        float angle = (float) Math.toDegrees(Math.atan2(this.y, this.x));

        if (angle < 0) {
            angle += 360;
        }
        this.angle = Float.valueOf(angle);
    }

    @Override
    public int compare(Tree t1, Tree t2) {
        return t1.getAngle().compareTo(t2.getAngle());
    }
}
