
# 2D Gravity Simulation

A real-time 2D gravitational physics simulation built with Java and FXGL. Watch multiple bodies attract each other through Newtonian gravity, collide elastically, and form dynamic orbital systems.

![Java](https://img.shields.io/badge/Java-21-orange) ![FXGL](https://img.shields.io/badge/FXGL-21.1-blue) ![JavaFX](https://img.shields.io/badge/JavaFX-21.0.6-green) ![Maven](https://img.shields.io/badge/Maven-3.x-red)

---

## Preview


![Preview](https://github.com/user-attachments/assets/4be4021c-2dc4-4099-9ed0-e182a0f51ac5)


---


## Features

- **N-body gravitational simulation** — every body attracts every other body using Newton's law of universal gravitation
- **Elastic collision response** — impulse-based collision resolution with overlap correction
- **Real-time rendering** — smooth simulation loop via FXGL's `onUpdate` game tick
- **Configurable initial conditions** — set mass, radius, position, and velocity per body

---

## How It Works

Each frame, the simulation:
1. Calculates gravitational force between every pair of bodies: `F = G * m1 * m2 / r²`
2. Derives acceleration and updates velocity for both bodies
3. Checks for collision (distance < sum of radii)
4. Resolves overlap and applies impulse-based velocity correction
5. Translates each entity by its updated velocity

---

## Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 21 | Core language |
| JavaFX | 21.0.6 | Rendering & windowing |
| FXGL | 21.1 | Game loop & entity system |
| Maven | 3.x | Build & dependency management |

---

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.x

### Run

```bash
git clone https://github.com/ROHIT-NAITHANI/2dGravitySimulation.git
cd 2dGravitySimulation
mvn clean javafx:run
```

Or run directly from IntelliJ IDEA — open the project, let Maven import dependencies, and run `Launcher.java`.

---

## Project Structure

```
src/main/java/com/example/realphysics/
├── App.java          # Main game class — physics loop, entity setup
├── Launcher.java     # Entry point (required for modular JavaFX)
└── PhysicsBody.java  # Body data: mass, radius, velocity, entity reference
```

---

## Configuration

Tweak these values in `App.java` to change simulation behaviour:

| Variable | Default | Effect |
|---|---|---|
| `G` | `8000` | Gravitational constant — higher = stronger pull |
| `mass` | `2000` | Body mass — affects force magnitude |
| `radius` | `15` | Visual + collision radius |
| `velocityX/Y` | varies | Initial velocity per body |

---


