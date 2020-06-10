package com.example.signatureview;

public class Line {
    public final float x1;
    public final float y1;
    public final float x2;
    public final float y2;
    float strokeWidth;
    float currentWidth;
    float velocity;
    float lastWidth;
    Point startPoint;
    Point previousPoint;
    Point currentPoint;

    public Line(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void currentWidth(float strokeWidth) {
        this.currentWidth = currentWidth;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public float getCurrentWidth() {
        return currentWidth;
    }

    public void setCurrentWidth(float currentWidth) {
        this.currentWidth = currentWidth;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public void setLastWidth(float lastWidth) {
        this.lastWidth = lastWidth;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getPreviousPoint() {
        return previousPoint;
    }

    public void setPreviousPoint(Point previousPoint) {
        this.previousPoint = previousPoint;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }
}
