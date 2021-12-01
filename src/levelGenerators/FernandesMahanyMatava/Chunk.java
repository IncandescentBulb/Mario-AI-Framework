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

    Chunk(String tiles) {
        this.tiles = tiles;
        this.weights = new HashMap<>();
    }

    HashMap<String, Float> calculateWeights(LevelGenerator generator) {
        HashMap<String, Float> calc = new HashMap<>();

        this.weights.forEach((k, weightFactors) -> {
            float v = (float)weightFactors.stream().mapToDouble(wf -> wf.getWeight(generator)).sum();
            calc.put(k, v);
        });

        return calc;
    }

    void addWeight(String chunk, WeightFactor weight) {
        // there's definitely a function that does this but I haven't used java in a while so idr what it is
        if(!this.weights.containsKey(chunk)) {
            this.weights.put(chunk, new ArrayList<>());
        }
        this.weights.get(chunk).add(weight);
    }

    void addWeight(String chunk, float weight) {
        this.addWeight(chunk, (prevChunks) -> weight);
    }

    int getWidth() {
        return this.tiles.split("\n")[0].length();
    }

    int getHeight() {
        return this.tiles.split("\n").length;
    }
}
