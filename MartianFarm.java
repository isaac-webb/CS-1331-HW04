// I worked on the homework assignment alone, using only course materials.

import java.util.Random;

/**
  * Defines a MartianFarm, which deals with planting, growing, and harvesting
  * MartianPlants.
  *
  * @author iwebb6
  */
public class MartianFarm {
    private MartianPlant[] plants;
    private int farmSize;

    /**
      * Creates a MartianFarm with 10 MartianPlants.
      */
    public MartianFarm() {
        plants = new MartianPlant[10];
        for (farmSize = 0; farmSize < 10; farmSize++) {
            plants[farmSize] = new MartianPlant();
        }
    }

    /**
      * Grows all MartianPlants in the MartianFarm.
      */
    public void growFarm() {
        for (int i = 0; i < farmSize; i++) {
            plants[i].grow();
        }
    }

    /**
      * Plants a single plant, doubling the size of your farm if there is no
      * room left.
      */
    public void plantPlant() {
        if (farmSize == plants.length) {
            // Store the old plants and make a new array twice as big
            MartianPlant[] oldPlants = plants;
            plants = new MartianPlant[plants.length * 2];

            // Transfer the old plants to the new array
            for (int i = 0; i < farmSize; i++) {
                plants[i] = oldPlants[i];
            }

            plants[farmSize++] = new MartianPlant();
        } else {
            plants[farmSize++] = new MartianPlant();
        }
    }

    /**
      * Shrivels all of the MartianPlants in the MartianFarm, returning whether
      * or not any MartianPlants were killed.
      *
      * @return Whether or not any MartianPlants died
      */
    public boolean damagePlants() {
        boolean plantDied = false;

        for (int i = 0; i < farmSize; i++) {
            if (plants[i].shrivel()) {
                farmSize--;
                plants[i--] = null;
                closeGaps();
                plantDied = true;
            }
        }

        return plantDied;
    }

    /**
      * Removes the input MartianPlant from the MartianFarm.
      *
      * @param plant The MartianPlant to remove from the MartianFarm
      * @return The MartianPlant removed from the MartianFarm
      */
    public MartianPlant removePlant(MartianPlant plant) {
        for (int i = 0; i < farmSize; i++) {
            if (plants[i].equals(plant)) {
                MartianPlant removedPlant = plants[i];
                plants[i] = null;
                farmSize--;
                closeGaps();
                return removedPlant;
            }
        }

        return null;
    }

    /**
      * Gets a random MartianPlant from the MartianFarm.
      *
      * @return A random MartianPlant
      */
    public MartianPlant getRandPlant() {
        Random rand = new Random();
        return plants[rand.nextInt(farmSize)];
    }

    // Makes sure any MartianPlants in the array are in the lowest inidicies
    // possible for more efficient traversal of the array.
    private void closeGaps() {
        for (int i = 0; i < farmSize && i < plants.length - 1; i++) {
            // If the current index is empty, replace it with the contents of
            // the next and remove the next
            if (plants[i] == null) {
                plants[i] = plants[i + 1];
                plants[i + 1] = null;
            }
        }
    }

    /**
      * Returns the MartianFarm's array of MartianPlants.
      *
      * @return The array of MartianPlants
      */
    public MartianPlant[] getPlantArray() {
        return plants;
    }

    /**
      * Returns the size of the MartianFarm.
      *
      * @return The size of the MartianFarm (number of MartianPlants)
      */
    public int getFarmSize() {
        return farmSize;
    }
}
