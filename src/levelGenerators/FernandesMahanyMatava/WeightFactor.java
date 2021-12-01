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
        float w;
        if(generator.chunks.size() < 2){
            return 0.0f;
        } else if(generator.chunks.get(generator.chunks.size() - 2).equals(this.chunk)){
            return this.weight;
        }else{
            return 0.0f;
        }
        /*return generator.chunks.size() < 2 ?
                0.0f
                : (generator.chunks.get(generator.chunks.size() - 2).equals(this.chunk) ?
                this.weight
                : 0.0f);*/
    }
}