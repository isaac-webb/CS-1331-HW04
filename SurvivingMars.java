import java.util.Scanner;
import java.util.Random;

public class SurvivingMars {
    public static void main(String[] args) {
        int daysLeft = 50;
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        MartianCrew martianCrew;
        MartianFarm martianFarm = new MartianFarm();
        MartianHabitat martianHabitat = new MartianHabitat();

        System.out.println(
            "Wake up! We're stranded! Captain? "
            + "Have you forgotten where you are?\n"
            + "Yes we're on Mars? You must have hit your head pretty hard.\n"
            + "Get up! We have work to do.\n"
            + "The rescue ship won't be here for almost 50 days\n");
        System.out.println("First thing's first, "
            + "do you remember everyone's name?\n"
            + "(Please list five names, press enter after each.");

        String[] names = new String[5];
        for (int i = 0; i < names.length; i++) {
            names[i] = scanner.nextLine();
        }
        martianCrew = new MartianCrew(names);

        System.out.println("Okay. Let's get started.\n"
            + "We have storms and aliens to deal with.\n"
            + "What do you think we should do first?\n"
            + "(Please indicate by number your choice.)\n");
        while (daysLeft > 0) {
            System.out.println("Days left: " + daysLeft + "\n");
            System.out.println(
                "1. Feed crew (Health Increase across crew)\n"
                + "2. Reinforce Defenses (Defensive increase, all defenses)\n"
                + "3. Plant (Adds another plant to your farm)\n"
                + "4. Build Defense (Builds a missing defense)\n"
                + "5. Check Crew stats\n"
                + "6. Check Defense stats\n"
                + "7. Check Farm stats");
            int choice = scanner.nextInt();
            switch (choice) {
            case 1:
                System.out.println("Good choice, I think "
                    + "the crew was pretty hungry...\n");
                martianCrew.feedCrew(martianFarm.getRandPlant());
                break;
            case 2:
                boolean reinforced = martianHabitat.reinforceDefenses();
                if (reinforced) {
                    System.out.println("Agreed, defenses "
                        + "were looking rickity...\n");
                } else {
                    System.out.println("Captain all of our defenses"
                        + " are currently destroyed...");
                }
                break;
            case 3:
                System.out.println("Never bad to over prepare...\n");
                martianFarm.plantPlant();
                break;
            case 4:
                if (martianHabitat.getDefenseCount() < 4) {
                    System.out.println("I thought that side "
                        + "was looking exposed as well...\n"
                        + "Looks like the defenses we are missing are the...");
                    for (MartianDefense location
                        : martianHabitat.getDefenseArray()) {
                        if (location.getDefenseRating() <= 0) {
                            System.out.println(location.getLocation());
                        }
                    }
                    System.out.println("...sides.\n"
                        + "Which one would you like to rebuild?\n");
                    scanner.nextLine();
                    String defense = scanner.nextLine();
                    if (defense.equalsIgnoreCase("North")) {
                        martianHabitat.reBuildDefense(Location.NORTH);
                    } else if (defense.equalsIgnoreCase("South")) {
                        martianHabitat.reBuildDefense(Location.SOUTH);
                    } else if (defense.equalsIgnoreCase("East")) {
                        martianHabitat.reBuildDefense(Location.EAST);
                    } else if (defense.equalsIgnoreCase("West")) {
                        martianHabitat.reBuildDefense(Location.WEST);
                    }
                    
                } else {
                    System.out.println("We already have all "
                        + "of our defenses built!\n");
                }
                break;
            case 5:
                System.out.println("Here you go Cap:\n");
                for (Astronaut crewMember : martianCrew.getCrewArray()) {
                    if (crewMember != null) {
                        System.out.println(crewMember);
                    }
                }
                System.out.println();
                break;
            case 6:
                System.out.println("Defense readings:\n");
                for (MartianDefense defense
                    : martianHabitat.getDefenseArray()) {
                    if (defense != null) {
                        System.out.println(defense);
                    }
                }
                System.out.println();
                break;
            case 7:
                System.out.println("Farm info:\n");
                for (MartianPlant plant : martianFarm.getPlantArray()) {
                    if (plant != null) {
                        System.out.println(plant);
                    }
                }
                System.out.println();
                break;
            default:
                System.out.println("Invalid command sir.\n");
                break;

            }
            System.out.println("Cap... there's a bad wind in the air.\n");
            int stormOrAliens = rand.nextInt(90);
            if (stormOrAliens > 30 && stormOrAliens <= 60) {
                System.out.println("ALIEN ATTACK!!!");
                System.out.println("~~ Meep - Beware our Alien-ness ~~\n");
                if (martianHabitat.getDefenseCount() <= 0) {
                    boolean killedInAction = martianCrew.damageCrew();
                    System.out.println("Captain they got to the crew...");
                    if (killedInAction) {
                        System.out.println("We lost a crew member...\n");
                    }
                    alienGameArt(5 - martianCrew.getCrewCount());
                } else {
                    martianHabitat.damageDefenses();
                    System.out.println("Looks like our defenses took a hit... "
                        + "We better check them.\n");
                    alienGameArt(5 - martianCrew.getCrewCount());
                }

            } else if (stormOrAliens > 60 && stormOrAliens < 90) {
                System.out.println("It's a dust storm captain!\n");
                boolean deadPlants = martianFarm.damagePlants();
                if (deadPlants) {
                    System.out.println("We lost some plants Sir.\n");
                }
                stormGameArt(5 - martianCrew.getCrewCount());
            } else {
                System.out.println("Looks like we made it"
                    + " through the night without any damages...\n");
                regGameArt(5 - martianCrew.getCrewCount());
            }
            martianFarm.growFarm();
            daysLeft--;
            if (martianCrew.getCrewCount() <= 0
                || martianFarm.getFarmSize() <= 0) {
                daysLeft = 0;
            }
        }

        if (martianCrew.getCrewCount() > 0
            && martianFarm.getFarmSize() != 0) {
            System.out.println(
                "Captain... We did it. The Rocket arrived.\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         Congragulations!\n");
        } else {
            System.out.println(
                "You didn't make it...\n"
                + "~~~~~~~~~~~~~~~~~\n"
                + "   Game Over\n");
        }
    }

    private static void stormGameArt(int numDead) {
        switch (numDead) {
        case 0:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                             \n"
                + "        /               \\                    __     \n"
                + "       /                 \\                  /       \n"
                + "      /                   \\                 \\_____ \n"
                + "     /                     \\              __        \n"
                + " #  |   O   O   O   O   O   | #          /           \n"
                + " #  |  -|- -|- -|- -|- -|-  | #          \\_____     \n"
                + " #  |  / \\ / \\ / \\ / \\ / \\  | #        __       \n"
                + " #  |                       | #       /              \n"
                + "     \\                     /          \\_____       \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 1:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                      __     \n"
                + "        /               \\                    /      \n"
                + "       /                 \\                   \\_____\n"
                + "      /                   \\               __        \n"
                + "     /                     \\             /          \n"
                + " #  |   O    O    O    O   | #           \\_____     \n"
                + " #  |  -|-  -|-  -|-  -|-  | #        __             \n"
                + " #  |  / \\  / \\  / \\  / \\  | #       /           \n"
                + " #  |                      | #       \\_____         \n"
                + "     \\                    /                         \n"
                + "      \\                  /                          \n"
                + "       \\                /                           \n"
                + "        \\              /                            \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 2:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                             \n"
                + "        /               \\                    __     \n"
                + "       /                 \\                  /       \n"
                + "      /                   \\                 \\_____ \n"
                + "     /                     \\             __         \n"
                + " #  |    O    O     O       | #         /            \n"
                + " #  |   -|-  -|-   -|-      | #         \\_____      \n"
                + " #  |   / \\  / \\   / \\      | #      __           \n"
                + " #  |                       | #     /                \n"
                + "     \\                     /        \\_____         \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 3:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                    __       \n"
                + "        /               \\                  /        \n"
                + "       /                 \\                 \\_____  \n"
                + "      /                   \\              __         \n"
                + "     /                     \\            /           \n"
                + " #  |       O     O         | #         \\_____      \n"
                + " #  |      -|-   -|-        | #       __             \n"
                + " #  |      / \\   / \\        | #      /             \n"
                + " #  |                       | #      \\_____         \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 4:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                     __      \n"
                + "        /               \\                   /       \n"
                + "       /                 \\                  \\_____ \n"
                + "      /                   \\               __        \n"
                + "     /                     \\             /          \n"
                + " #  |          O            | #          \\_____     \n"
                + " #  |         -|-           | #        __            \n"
                + " #  |         / \\           | #       /             \n"
                + " #  |                       | #       \\_____        \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 5:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "            GAME OVER                                \n"
                + "         ###############                             \n"
                + "         ---------------                    __       \n"
                + "        /               \\                  /        \n"
                + "       /                 \\                 \\_____  \n"
                + "      /                   \\              __         \n"
                + "     /                     \\            /           \n"
                + " #  |                       | #         \\_____      \n"
                + " #  |                       | #       __             \n"
                + " #  |                       | #      /               \n"
                + " #  |                       | #      \\_____         \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        default:
            System.out.println("Default");
        }
    }

    private static void alienGameArt(int numDead) {
        switch (numDead) {
        case 0:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                    V        \n"
                + "        /               \\                   O       \n"
                + "       /                 \\                 -|-      \n"
                + "      /                   \\                / \\     \n"
                + "     /                     \\           V            \n"
                + " #  |   O   O   O   O   O   | #        O             \n"
                + " #  |  -|- -|- -|- -|- -|-  | #       -|-            \n"
                + " #  |  / \\ / \\ / \\ / \\ / \\  | #       / \\      \n"
                + " #  |                       | #                      \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 1:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                   V         \n"
                + "        /               \\                  O        \n"
                + "       /                 \\                -|-       \n"
                + "      /                   \\               / \\      \n"
                + "     /                     \\           V            \n"
                + " #  |   O    O    O    O   | #         O             \n"
                + " #  |  -|-  -|-  -|-  -|-  | #        -|-            \n"
                + " #  |  / \\  / \\  / \\  / \\  | #        / \\       \n"
                + " #  |                      | #                       \n"
                + "     \\                    /                         \n"
                + "      \\                  /                          \n"
                + "       \\                /                           \n"
                + "        \\              /                            \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 2:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                   V         \n"
                + "        /               \\                  O        \n"
                + "       /                 \\                -|-       \n"
                + "      /                   \\               / \\      \n"
                + "     /                     \\           V            \n"
                + " #  |    O    O     O       | #        O             \n"
                + " #  |   -|-  -|-   -|-      | #       -|-            \n"
                + " #  |   / \\  / \\   / \\      | #       / \\        \n"
                + " #  |                       | #                      \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 3:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                   V         \n"
                + "        /               \\                  O        \n"
                + "       /                 \\                -|-       \n"
                + "      /                   \\               / \\      \n"
                + "     /                     \\           V            \n"
                + " #  |       O     O         | #        O             \n"
                + " #  |      -|-   -|-        | #       -|-            \n"
                + " #  |      / \\   / \\        | #       / \\         \n"
                + " #  |                       | #                      \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 4:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                   V         \n"
                + "        /               \\                  O        \n"
                + "       /                 \\                -|-       \n"
                + "      /                   \\               / \\      \n"
                + "     /                     \\           V            \n"
                + " #  |          O            | #        O             \n"
                + " #  |         -|-           | #       -|-            \n"
                + " #  |         / \\           | #       / \\          \n"
                + " #  |                       | #                      \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 5:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "            GAME OVER                                \n"
                + "         ###############                             \n"
                + "         ---------------                   V         \n"
                + "        /               \\                  O        \n"
                + "       /                 \\                -|-       \n"
                + "      /                   \\               / \\      \n"
                + "     /                     \\          V             \n"
                + " #  |                       | #       O              \n"
                + " #  |                       | #      -|-             \n"
                + " #  |                       | #      / \\            \n"
                + " #  |                       | #                      \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        default:
            System.out.println("Default");
        }

    }

    private static void regGameArt(int numDead) {
        switch (numDead) {
        case 0:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                             \n"
                + "        /               \\                           \n"
                + "       /                 \\                          \n"
                + "      /                   \\                         \n"
                + "     /                     \\                        \n"
                + " #  |   O   O   O   O   O   | #                      \n"
                + " #  |  -|- -|- -|- -|- -|-  | #                      \n"
                + " #  |  / \\ / \\ / \\ / \\ / \\  | #                 \n"
                + " #  |                       | #                      \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 1:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                             \n"
                + "        /               \\                           \n"
                + "       /                 \\                          \n"
                + "      /                   \\                         \n"
                + "     /                     \\                        \n"
                + " #  |   O    O    O    O   | #                       \n"
                + " #  |  -|-  -|-  -|-  -|-  | #                       \n"
                + " #  |  / \\  / \\  / \\  / \\  | #                   \n"
                + " #  |                      | #                       \n"
                + "     \\                    /                         \n"
                + "      \\                  /                          \n"
                + "       \\                /                           \n"
                + "        \\              /                            \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 2:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                             \n"
                + "        /               \\                           \n"
                + "       /                 \\                          \n"
                + "      /                   \\                         \n"
                + "     /                     \\                        \n"
                + " #  |    O    O     O       | #                      \n"
                + " #  |   -|-  -|-   -|-      | #                      \n"
                + " #  |   / \\  / \\   / \\      | #                   \n"
                + " #  |                       | #                      \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 3:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                             \n"
                + "        /               \\                           \n"
                + "       /                 \\                          \n"
                + "      /                   \\                         \n"
                + "     /                     \\                        \n"
                + " #  |       O     O         | #                      \n"
                + " #  |      -|-   -|-        | #                      \n"
                + " #  |      / \\   / \\        | #                    \n"
                + " #  |                       | #                      \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 4:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "         ###############                             \n"
                + "         ---------------                             \n"
                + "        /               \\                           \n"
                + "       /                 \\                          \n"
                + "      /                   \\                         \n"
                + "     /                     \\                        \n"
                + " #  |          O            | #                      \n"
                + " #  |         -|-           | #                      \n"
                + " #  |         / \\           | #                     \n"
                + " #  |                       | #                      \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        case 5:
            System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "            GAME OVER                                \n"
                + "         ###############                             \n"
                + "         ---------------                             \n"
                + "        /               \\                           \n"
                + "       /                 \\                          \n"
                + "      /                   \\                         \n"
                + "     /                     \\                        \n"
                + " #  |                       | #                      \n"
                + " #  |                       | #                      \n"
                + " #  |                       | #                      \n"
                + " #  |                       | #                      \n"
                + "     \\                     /                        \n"
                + "      \\                   /                         \n"
                + "       \\                 /                          \n"
                + "        \\               /                           \n"
                + "         ---------------                             \n"
                + "         ###############                             \n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            );
            break;
        default:
            System.out.println("default");
        }
    }

}