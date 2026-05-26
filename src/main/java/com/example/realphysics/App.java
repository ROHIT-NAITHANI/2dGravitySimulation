package com.example.realphysics;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;



public class App extends GameApplication {

    private Entity ball;
    private Entity ball2;
    private Entity ball3;
    PhysicsBody physicsBody;
    PhysicsBody physicsBody2;
    PhysicsBody physicsBody3;
    private List<PhysicsBody> bodies = new ArrayList<>();
    private double G = 8000;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1400);
        settings.setHeight(900);
        settings.setTitle("RealPhysics");
        settings.setProfilingEnabled(true);

    }

    @Override
    protected void initInput() {

       // this runs before initGame, ball is null here

        FXGL.getGameScene().getViewport().setZoom(0.3);
        FXGL.getGameScene().getViewport().setX(-500);
        FXGL.getGameScene().getViewport().setY(-1000);// zoom out a lot

    }

    private void applyGravity(PhysicsBody b1, PhysicsBody b2, double tpf) {
        // Step 1 - use b1 and b2, not ball/ball2
        double dx = b2.getCenterX() - b1.getCenterX();
        double dy = b2.getCenterY() - b1.getCenterY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Step 2 - use b1 and b2
        double force = G * b1.mass * b2.mass / (distance * distance);

        // Step 3
        double nx = dx / distance;
        double ny = dy / distance;

        // Step 4 - use b1 and b2
        b1.velocityX += (force / b1.mass) * nx * tpf;
        b1.velocityY += (force / b1.mass) * ny * tpf;
        b2.velocityX -= (force / b2.mass) * nx * tpf;
        b2.velocityY -= (force / b2.mass) * ny * tpf;

        // collision - use b1 and b2
        if (distance < b1.radius + b2.radius) {
            double overlap = (b1.radius + b2.radius) - distance;
            b1.entity.translateX(-nx * overlap / 2);
            b1.entity.translateY(-ny * overlap / 2);
            b2.entity.translateX(nx * overlap / 2);
            b2.entity.translateY(ny * overlap / 2);

            double relVelX = b2.velocityX - b1.velocityX;
            double relVelY = b2.velocityY - b1.velocityY;
            double relVelAlongNormal = relVelX * nx + relVelY * ny;

            if (relVelAlongNormal < 0) {
                double impulse = -1.0 * relVelAlongNormal;
                b1.velocityX -= impulse * nx;
                b1.velocityY -= impulse * ny;
                b2.velocityX += impulse * nx;
                b2.velocityY += impulse * ny;
            }
        }

        // move
        b1.entity.translateX(b1.velocityX * tpf);
        b1.entity.translateY(b1.velocityY * tpf);
        b2.entity.translateX(b2.velocityX * tpf);
        b2.entity.translateY(b2.velocityY * tpf);
    }

    @Override
    protected void initGame() {
        FXGL.getGameScene().setBackgroundColor(Color.BLACK);

        ball = FXGL.entityBuilder()
                .at(2000, 300)
                .view(new Circle(30, Color.GRAY))
                .buildAndAttach();

        ball2 = FXGL.entityBuilder()
                .at(1000, 300)
                .view(new Circle(30, Color.GRAY))
                .buildAndAttach();

        ball3 = FXGL.entityBuilder()
                .at(3500, 300)
                .view(new Circle(30, Color.GRAY))
                .buildAndAttach();

        // create THEN add
        physicsBody = new PhysicsBody(ball, 2000, 15);
        physicsBody2 = new PhysicsBody(ball2, 2000, 15);
        physicsBody3 = new PhysicsBody(ball3 , 2000 , 15);
        physicsBody.velocityX  =  40;
        physicsBody2.velocityX = -20;
        physicsBody2.velocityY = -35;
        physicsBody3.velocityX = -20;
        physicsBody3.velocityY =  35;

        bodies.add(physicsBody);
        bodies.add(physicsBody2);
        bodies.add(physicsBody3);
    }





    @Override
    protected void onUpdate(double tpf){
        for (int i = 0; i < bodies.size(); i++) {
            for (int j = i + 1; j < bodies.size(); j++) {
                applyGravity(bodies.get(i), bodies.get(j), tpf);
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
