package levelGenerators.FernandesMahanyMatava;

interface WeightFactor {
    float getWeight(LevelGenerator generator);
}

class PrevChunkFactor implements WeightFactor {
    String chunk;
    float weight;

    PrevChunkFactor(String chunk, float weight) {
        this.chunk = chunk;
        this.weight = weight;
    }

    @Override
    public float getWeight(LevelGenerator generator) {
        return generator.chunks.size() < 2 ?
                0.0f
                : (generator.chunks.get(generator.chunks.size() - 2).equals(this.chunk) ?
                this.weight
                : 0.0f);
    }
}