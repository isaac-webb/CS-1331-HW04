// I worked on the homework assignment alone, using only course materials.

import java.util.Random;

/**
  * Defines the behaviour of MartianPlants, which can grow, shrivel, and have
  * nutrient values and identification numbers.
  *
  * @author iwebb6
  */
public class MartianPlant {
    // Create a static variable to track what the next plant number should be
    private static int currPlantNum = 1;

    private double nutrients;
    private int plantNum;

    /**
      * Creates a MartianPlant with a default nutrient level of 12.5 and a plant
      * number decided by how many MartianPlants have been created thus far.
      */
    public MartianPlant() {
        nutrients = 12.5;
        plantNum = currPlantNum++;
    }

    /**
      * Randomly increments the MartianPlant's nutrients by a number between 1
      * and 5.
      */
    public void grow() {
        Random rand = new Random();
        nutrients += rand.nextInt(5) + 1;
    }

    /**
      * Randomly decrements the MartianPlant's nutrients by a number between 1
      * and 7. Returns whether or not the MartianPlant has died (nutrients are
      * less than or equal to 0).
      *
      * @return Whether or not the MartianPlant has died
      */
    public boolean shrivel() {
        // If the plant is already dead, don't do anything and return false
        if (nutrients == 0) {
            return false;
        }

        Random rand = new Random();
        int damage = rand.nextInt(7) + 1;

        // Account for the randomly generated damage
        nutrients = damage >= nutrients ? 0 : nutrients - damage;
        return nutrients == 0;
    }

    /**
      * Compares the MartianPlant to another MartianPlant, returning true if
      * their plant numbers are the same.
      *
      * @param plant The MartianPlant to compare plant numbers with
      * @return Whether or not the input MartianPlant has the same plant number
      */
    @Override
    public boolean equals(Object plant) {
        if (!(plant instanceof MartianPlant)) {
            return false;
        }

        MartianPlant marPlant = (MartianPlant) plant;

        return plantNum == marPlant.getPlantNum();
    }

    /**
      * Generates the hash purely based on the plant number.
      *
      * @return The hash for the MartianPlant (plant number)
      */
    @Override
    public int hashCode() {
        return plantNum;
    }

    /**
      * Returns the MartianPlant's nutrients.
      *
      * @return The nutrients of the MartianPlant
      */
    public double getNutrients() {
        return nutrients;
    }

    /**
      * Returns the MartianPlant's plant number.
      *
      * @return The number of the MartianPlant
      */
    public int getPlantNum() {
        return plantNum;
    }

    /**
      * Custom implementation of toString that includes more information about
      * the MartianPlant, its nutrients, and its plant number.
      *
      * @return A String containing the MartianPlant's nutrients and plant
      *         number
      */
    public String toString() {
        return String.format("Plant #%d: %f nutrients", plantNum, nutrients);
    }
}
