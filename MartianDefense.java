// I worked on the homework assignment alone, using only course materials.

/**
  * Defines the behaviours of a MartianDefense, which has a defense rating,
  * defense direction, and can deal with being damaged, repaired, etc.
  *
  * @author iwebb6
  * @version 1.0
  */
public class MartianDefense {
    private int defenseRating;
    private Location defenseLocation;

    /**
      * Creates a MartianDefense using the second constructor with a default
      * Location of north.
      */
    public MartianDefense() {
        this(Location.NORTH);
    }

    /**
      * Creates a MartianDefense with the given Location and a defense rating of
      * 20.
      *
      * @param defenseLocation The Location of the MartianDefense
      */
    public MartianDefense(Location defenseLocation) {
        this.defenseLocation = defenseLocation;
        defenseRating = 20;
    }

    /**
      * Decrements the MartianDefense's damage rating by the given amount,
      * returning whether or not it was destroyed (rating &lt;= 0).
      *
      * @param defenseDamage The damage to be done to the MartianDefense
      * @return Whether or not the MartianDefense is destroyed
      */
    public boolean damage(int defenseDamage) {
        // If the defense rating is already 0, don't do anything
        if (defenseRating == 0) {
            return false;
        }

        defenseRating = defenseDamage
                        >= defenseRating ? 0 : defenseRating - defenseDamage;
        return defenseRating == 0;
    }

    /**
      * Increments the MartianDefense's defense rating by 5.
      */
    public void reinforce() {
        defenseRating += 5;
    }

    /**
      * Resets the MartianDefense's defense rating back to the default of 20.
      */
    public void resetDefenseRating() {
        defenseRating = 20;
    }

    /**
      * Returns the defense rating of the MartianDefense.
      *
      * @return The MartianDefense's defense rating
      */
    public int getDefenseRating() {
        return defenseRating;
    }

    /**
      * Returns the location of the MartianDefense in relation to the base.
      *
      * @return The location of the MartianDefense
      */
    public Location getLocation() {
        return defenseLocation;
    }

    /**
      * Custom implementation of toString that includes information on the
      * MartianDefense.
      *
      * @return A String containing the MartianDefense's Location and defense
      *         rating
      */
    public String toString() {
        return String.format("Defense (%s): %d HP",
                             defenseLocation,
                             defenseRating);
    }
}
