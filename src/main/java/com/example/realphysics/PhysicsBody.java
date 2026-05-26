package com.example.realphysics;

import com.almasb.fxgl.entity.Entity;

public class PhysicsBody {
    public Entity entity;
    public double velocityX = 0;
    public double velocityY = 0;
    public double mass;
    public double radius;

    public PhysicsBody(Entity entity, double mass, double radius) {
        this.entity = entity;
        this.mass = mass;
        this.radius = radius;
    }

    public double getCenterX() {
        return entity.getX() + radius;
    }

    public double getCenterY() {
        return entity.getY() + radius;
    }
}