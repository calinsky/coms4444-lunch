package lunch.g3;

import lunch.sim.Command;
import lunch.sim.CommandType;
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;
import lunch.sim.*;

public class Player implements lunch.sim.Player {
    // Initialization function.
    // members: other family members collborating with your player.
    // members_count: Number of family members.
    // t: Time limit for simulation.
    public void init(ArrayList<Family> members,Integer id, int f,ArrayList<Animal> animals, Integer m, Integer g, double t, Integer s) {
        return;
    };

    // Gets the moves from the player. Number of moves is specified by first parameter.
    public Command getCommand(ArrayList<Family> members, ArrayList<Animal> animals, PlayerState ps) {
        return null;
    };

    public boolean shouldStopEating(ArrayList<Animal> animals, PlayerState ps) {
        int dangerMonkeys = 0;
        for (Animal animal : animals) {
            if(animal.which_animal() == AnimalType.GOOSE) {
                if(ps.get_held_item_type() == FoodType.SANDWICH && distToAnimal(animal, ps) <= 6) {
                    return true;
                }
            } else {
                //monkey
                if(distToAnimal(animal, ps) <= 6) {
                    dangerMonkeys++;
                    if(dangerMonkeys == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean shouldFinishRemoving(ArrayList<Animal> animals, PlayerState ps) {
        int secondsRemaining = ps.time_to_finish_search(); //Will be used later
        int dangerMonkeys = 0;
        for (Animal animal : animals) {
            if(animal.which_animal() == AnimalType.GOOSE) {
                //TODO: check if we're pulling out a sandwich
                if(distToAnimal(animal, ps) <= 6) {
                    return true;
                }
            } else {
                //monkey
                if(distToAnimal(animal, ps) <= 6) {
                    dangerMonkeys++;
                    if(dangerMonkeys == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public double distToAnimal(Animal animal, PlayerState ps) {
        return Math.sqrt(Math.pow(animal.get_location().x - ps.get_location().x, 2) + Math.pow(animal.get_location().y - ps.get_location().y, 2));
    }
}
