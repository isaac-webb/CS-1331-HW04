// I worked on the homework assignment alone, using only course materials.

import java.util.Random;

/**
  * Defines a MartianCrew, which is just an array of Astronauts.
  *
  * @author iwebb6
  * @version 1.0
  */
public class MartianCrew {
    private Astronaut[] astronauts;
    private int crewCount;

    /**
      * Creates a MartianCrew with the names of the Astronauts as inputs. Any
      * Astronauts without names will be named "Anonymous."
      *
      * @param names The names of the Astronauts to be created
      */
    public MartianCrew(String[] names) {
        astronauts = new Astronaut[5];
        crewCount = 5;

        for (int i = 0; i < crewCount; i++) {
            if (names[i].isEmpty()) {
                astronauts[i] = new Astronaut();
            } else {
                astronauts[i] = new Astronaut(names[i]);
            }
        }
    }

    /**
      * Iterates through the MartianCrew and feeds each Astronaut.
      *
      * @param plant The MartianPlant to feed the Astrnoauts
      */
    public void feedCrew(MartianPlant plant) {
        for (Astronaut astronaut : astronauts) {
            if (astronaut.getIsAlive()) {
                astronaut.eat(plant);
            }
        }
    }

    /**
      * Iterates through the MartianCrew and damages each Astronaut randomly by
      * an integer 0-50 (exclusive), returning true if someone was killed
      *
      * @return Whether or not any Astronauts were killed
      */
    public boolean damageCrew() {
        Random rand = new Random();
        boolean astKilled = false;

        for (Astronaut astronaut : astronauts) {
            if (astronaut.getIsAlive() && astronaut.injure(rand.nextInt(50))) {
                astKilled = true;
                crewCount--;
            }
        }

        return astKilled;
    }

    /**
      * Returns the MartianCrew's array of Astronauts.
      *
      * @return The array of Astronauts
      */
    public Astronaut[] getCrewArray() {
        return astronauts;
    }

    /**
      * Returns the number of Astronauts still alive in the MartianCrew
      *
      * @return The number of living Astronauts
      */
    public int getCrewCount() {
        return crewCount;
    }
}
