package levelGenerators.FernandesMahanyMatava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

enum Tag {
    ENEMY, // has an enemy of some kind (Goomba, Koopa, etc.)
    GOOMBA, // has one or more Goombas
    KOOPA,
    //PIRANHA,
    //SPIKY,
    //BULLET, // Bullet Bill or Bill Blaster
    WINGED, // any winged enemy
    PIPE, // has pipe tiles
    PIT, // can fall out of this tile
    //SUPER, // has super mushroom
    MUSHROOM_BLOCKS, // mushroom tiles
    START, // has spawn for Mario (M)
    END_STAIRS, // stairs that lead to flag
    FLAG, // has flag
    TAG_A, // "TAG_..." tags are for use testing WeightFactors and such
    TAG_B,
    TAG_C,
    TAG_D,
}

public class Chunk {
    String tiles;

    /**
     * List of possible next chunks, and their weights
     * The WeightFactors are summed to calculate the total weight for that next chunk
     */
    HashMap<String, List<WeightFactor>> weights;
    List<Tag> tags;

    Chunk(String tiles) {
        this.tiles = tiles;
        this.weights = new HashMap<>();
        this.tags = new ArrayList<>();
    }

    /**
     * Recalculates the weights for this Chunk's possible next Chunks
     * @param generator The generator to use for context
     * @return Map of chunk keys to weights
     */
    HashMap<String, Float> calculateWeights(LevelGenerator generator) {
        HashMap<String, Float> calc = new HashMap<>();

        this.weights.forEach((k, weightFactors) -> {
            float v = (float)weightFactors.stream().mapToDouble(wf -> wf.getWeight(generator)).sum();
            calc.put(k, v);
        });

        return calc;
    }

    void addTag(Tag tag){
        //if(!tags.contains(tag)) {
        tags.add(tag);
        //}
    }

    boolean hasTag(Tag tag){
        return tags.contains(tag);
    }

    /**
     * Add a WeightFactor for a possible next chunk
     * @param chunk The chunk key to add the weight to
     * @param weight The WeightFactor to add
     */
    Chunk addWeight(String chunk, WeightFactor weight) {
        // there's definitely a function that does this but I haven't used java in a while so idr what it is
        if(!this.weights.containsKey(chunk)) {
            this.weights.put(chunk, new ArrayList<>());
        }
        this.weights.get(chunk).add(weight);
        return this;
    }

    /**
     * Shortcut for adding a static weight
     */
    Chunk addWeight(String chunk, float weight) {
        this.addWeight(chunk, (_generator) -> weight);
        return this;
    }

    int getWidth() {
        return this.tiles.split("\n")[0].length();
    }

    int getHeight() {
        return this.tiles.split("\n").length;
    }
}
