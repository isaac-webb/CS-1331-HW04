// I worked on the homework assignment alone, using only course materials.

import java.util.Random;

/**
  * Defines the behaviour for a MartianHabitat, which is simply an array of four
  * MartianDefenses.
  *
  * @author iwebb6
  */
public class MartianHabitat {
    private MartianDefense[] defenses;
    private int defenseCount;

    /**
      * Creates a MartianHabitat with four new MartianDefenses, one at each
      * cardinal direction.
      */
    public MartianHabitat() {
        defenseCount = 0;

        defenses = new MartianDefense[4];
        for (Location loc : Location.values()) {
            defenses[defenseCount++] = new MartianDefense(loc);
        }
    }

    /**
      * Resets the MartianDefense's defense rating at the given Location.
      *
      * @param location Location of the MartianDefense to be rebuilt
      */
    public void reBuildDefense(Location location) {
        for (MartianDefense defense : defenses) {
            if (defense.getLocation() == location) {
                defense.resetDefenseRating();
            }
        }
    }

    /**
      * Reinforces all of the MartianDefenses which are not destroyed, returning
      * whether or not any were reinforced.
      *
      * @return Whether or not any MartianDefenses were reinforced
      */
    public boolean reinforceDefenses() {
        for (MartianDefense defense : defenses) {
            if (defense.getDefenseRating() > 0) {
                defense.reinforce();
            }
        }

        return defenseCount > 0;
    }

    // what "different factor" means.
    /**
      * Goes through all of the MartianHabitat's MartianDefenses and damages
      * them 1-10 (inclusive) randomly, returning a String array of the
      * MartianDefenses destroyed in the process.
      *
      * @return A String array containing the Locations of the destroyed
      *         MartianDefenses
      */
    public String[] damageDefenses() {
        Random rand = new Random();
        String[] destroyedDefenses = new String[4];
        int destroyedCount = 0;

        for (MartianDefense defense : defenses) {
            if (defense.getDefenseRating() > 0
                && defense.damage(rand.nextInt(10) + 1)) {
                destroyedDefenses[destroyedCount++] =
                    defense.getLocation().toString();
                defenseCount--;
            }
        }

        return destroyedDefenses;
    }

    /**
      * Returns the array of the MartianHabitat's MartianDefenses.
      *
      * @return The MartianHabitat's MartianDefenses
      */
    public MartianDefense[] getDefenseArray() {
        return defenses;
    }

    /**
      * Returns the number of MartianDefenses still intact in the
      * MartianHabitat.
      *
      * @return The number of MartianDefenses still standing
      */
    public int getDefenseCount() {
        return defenseCount;
    }
}
