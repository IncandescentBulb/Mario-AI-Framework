package levelGenerators.FernandesMahanyMatava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chunk {
    String tiles;

    /**
     * List of possible next chunks, and their weights
     * The WeightFactors are summed to calculate the total weight for that next chunk
     */
    HashMap<String, List<WeightFactor>> weights;
    List<String> tags;

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

    void addTag(String tag){
        //if(!tags.contains(tag)) {
        tags.add(tag);
        //}
    }

    boolean hasTag(String tag){
        return tags.contains(tag);
    }

    /**
     * Add a WeightFactor for a possible next chunk
     * @param chunk The chunk key to add the weight to
     * @param weight The WeightFactor to add
     */
    void addWeight(String chunk, WeightFactor weight) {
        // there's definitely a function that does this but I haven't used java in a while so idr what it is
        if(!this.weights.containsKey(chunk)) {
            this.weights.put(chunk, new ArrayList<>());
        }
        this.weights.get(chunk).add(weight);
    }

    /**
     * Shortcut for adding a static weight
     */
    void addWeight(String chunk, float weight) {
        this.addWeight(chunk, (_generator) -> weight);
    }

    int getWidth() {
        return this.tiles.split("\n")[0].length();
    }

    int getHeight() {
        return this.tiles.split("\n").length;
    }
}
