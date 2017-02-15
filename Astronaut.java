// I worked on the homework assignment alone, using only course materials.

/**
  * Defines the behaviour of an Astronaut, which has a name, health, and is
  * either dead or alive.
  *
  * @author iwebb6
  * @version 1.0
  */
public class Astronaut {
    private String name;
    private int health;
    private boolean isAlive;

    /**
      * Creates an Astronaut with the name "Anonymous" using a call to the
      * second constructor.
      */
    public Astronaut() {
        this("Anonymous");
    }

    /**
      * Creates an Astronaut with the given name, 100 health, and isAlive set to
      * true.
      *
      * @param astName The desired name for the astronaut
      */
    public Astronaut(String astName) {
        name = astName;
        health = 100;
        isAlive = true;
    }

    /**
      * Increments the Astronaut's health by the amount of nutrients found in
      * the input plant.
      *
      * @param plant The plant to increment the Astronaut's health with
      */
    public void eat(MartianPlant plant) {
        health += (int) plant.getNutrients();
    }

    /**
      * Decrements the Astronaut's health by the amount of damage specified in
      * the input. Returns true if the Astronaut is killed (health is less than
      * or equal to 0 after accounting for damage).
      *
      * @param damage The amount of damage to be done to the Astronaut
      * @return Whether or not the Astronaut was killed by applying the damage
      */
    public boolean injure(int damage) {
        // If the player is already dead, don't do anything and return false
        if (health == 0) {
            return false;
        }

        isAlive = damage < health;
        health = isAlive ? health - damage : 0;
        return !isAlive;
    }

    /**
      * Returns the name of this Astronaut.
      *
      * @return The name of the Astronaut
      */
    public String getName() {
        return name;
    }

    /**
      * Returns the health of the Astronaut.
      *
      * @return The health of the Astronaut (&gt;= 0)
      */
    public int getHealth() {
        return health;
    }

    /**
      * Returns the Astronaut's life status.
      *
      * @return Whether or not the Astronaut is alive
      */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
      * Custom implementation of toString that includes more information about
      * the Astronaut.
      *
      * @return A String containing the Astronaut's name, health, and life
      *         status
      */
    public String toString() {
        return String.format("%s: %d HP, %s",
                             name,
                             health,
                             isAlive ? "Alive" : "Dead");
    }
}
