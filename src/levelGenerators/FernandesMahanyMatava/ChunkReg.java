package levelGenerators.FernandesMahanyMatava;

import java.util.HashMap;

public class ChunkReg {


    /// old unused Chunk data
    /// each row is assumed to be the same width
    /// chunks are aligned by the bottom row if they have different heights

    // TODO: add these into the new Chunk system

    static String BLANK =
            "----" + "\n" +
            "----" + "\n" +
            "----" + "\n" +
            "----";

    static String COIN_BOX=
            "!S" + "\n" +
            "--" + "\n" +
            "--" + "\n" +
            "XX" + "\n" +
            "XX";

    static String PIPE_1 =
            "----" + "\n" +
            "----" + "\n" +
            "-tt-" + "\n" +
            "XXXX" + "\n" +
            "XXXX";

    static String PIPE_6 =
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "XXXX" + "\n" +
            "XXXX";

    static String PYRAMID_FLOOR =
            "---#--#---" + "\n" +
            "--##--##--" + "\n" +
            "-###--###-" + "\n" +
            "####--####" + "\n" +
            "XXXXXXXXXX" + "\n" +
            "XXXXXXXXXX";

    static String PYRAMID_PIT =
            "---##--#---" + "\n" +
            "--###--##--" + "\n" +
            "-####--###-" + "\n" +
            "#####--####" + "\n" +
            "XXXXX--XXXX" + "\n" +
            "XXXXX--XXXX";

    static String RAISED_START =
            "-------\n" +
            "-------\n" +
            "-----SS\n" +
            "-----SS\n" +
            "XX---XX\n" +
            "XX---XX";

    static String RAISED_ENEMIES_1 =
            "------\n" +
            "-r-gg-\n" +
            "SSSSSS\n" +
            "SSSSSS\n" +
            "XXXXXX\n" +
            "XXXXXX";

    static String RAISED_ENEMIES_2 =
            "------\n" +
            "-g--k-\n" +
            "SSSSSS\n" +
            "SSSSSS\n" +
            "XXXXXX\n" +
            "XXXXXX";

    static String RAISED_BRICKS_1 =
            "---@--\n" +
            "------\n" +
            "------\n" +
            "------\n" +
            "--SSS-\n" +
            "------\n" +
            "------\n" +
            "------\n" +
            "SSSSSS\n" +
            "SSSSSS\n" +
            "XXXXXX\n" +
            "XXXXXX";

    static String RAISED_BRICKS_2 =
            "-----g----\n" +
            "----SSS---\n" +
            "----------\n" +
            "----k-----\n" +
            "--SCS-@---\n" +
            "----------\n" +
            "----------\n" +
            "-------g--\n" +
            "SSSSSSSSSS\n" +
            "SSSSSSSSSS\n" +
            "XXXXXXXXXX\n" +
            "XXXXXXXXXX";

    static String RAISED_PIT =
            "------\n" +
            "------\n" +
            "SS---S\n" +
            "SS---S\n" +
            "XX---X\n" +
            "XX---X";

    static String RAISED_END =
            "------\n" +
            "-g----\n" +
            "SSSS--\n" +
            "SSSSS-\n" +
            "XXXXXX\n" +
            "XXXXXX";

    static final HashMap<String, Chunk> CHUNKS;

    // this runs once when this class is loaded
    static {

        CHUNKS = new HashMap<>();

        // TODO: might want to move all the addWeights after the HashMap is filled otherwise we won't be able to use tags here in the future
        //       (or if we make this deferred somehow it could allow the layout to stay the same?)

        CHUNKS.put("START", new Chunk("""
            -------
            --M----
            XXXXXXX
            XXXXXXX
            """));
        // TODO: this repetition is kind of gross, could refactor addWeight to be chainable
        CHUNKS.get("START").addWeight("BRICKS_1", 0.75f);
        CHUNKS.get("START").addWeight("BRICKS_2", 0.75f);
        CHUNKS.get("START").addWeight("FOUR_QUESTION_BLOCKS", 1.25f);
        CHUNKS.get("START").addWeight("PIT", 1.25f);
        CHUNKS.get("START").addWeight("ENEMIES_1", 1.0f);
        CHUNKS.get("START").addWeight("PIPE_2", 0.5f);

        CHUNKS.put("BRICKS_1", new Chunk("""
            -SS@S-----
            ----------
            ----------
            ------k-gg
            XXXXXXXXXX
            XXXXXXXXXX
            """));
        CHUNKS.get("BRICKS_1").addWeight("START_HIGH_SECTION", 0.1f);
        CHUNKS.get("BRICKS_1").addWeight("BRICKS_2", 0.1f);
        CHUNKS.get("BRICKS_1").addWeight("FOUR_QUESTION_BLOCKS", 0.2f);
        CHUNKS.get("BRICKS_1").addWeight("PIT", 0.25f);
        CHUNKS.get("BRICKS_1").addWeight("ENEMIES_1", 0.25f);
        CHUNKS.get("BRICKS_1").addWeight("PIPE_2", 0.1f);

        // some examples of things you can do
        //        allChunks.get("BRICKS_1").addWeight("END_STAIRS", new PrevChunkFactor("ENEMIES_1", 100.0f));
        //        allChunks.get("BRICKS_1").addWeight("END_STAIRS", new PrevChunkFactor("BRICKS_2", 100.0f));
        //        allChunks.get("BRICKS_1").addWeight("END_STAIRS", generator -> {
        //            var nChunks = generator.chunks.size();
        //            return nChunks > 2 ? 100.0f : 0.0f;
        //        });

        CHUNKS.put("BRICKS_2", new Chunk("""
            ------!---
            ----------
            ----------
            --!--SSS--
            ----------
            ----------
            ---g--g---
            XXXXXXXXXX
            XXXXXXXXXX
            """));
        CHUNKS.get("BRICKS_2").addWeight("START_HIGH_SECTION", 0.1f);
        CHUNKS.get("BRICKS_2").addWeight("BRICKS_1", 0.1f);
        CHUNKS.get("BRICKS_2").addWeight("FOUR_QUESTION_BLOCKS", 0.1f);
        CHUNKS.get("BRICKS_2").addWeight("PIT", 0.3f);
        CHUNKS.get("BRICKS_2").addWeight("ENEMIES_1", 0.3f);
        CHUNKS.get("BRICKS_2").addWeight("PIPE_2", 0.1f);

        CHUNKS.put("FOUR_QUESTION_BLOCKS", new Chunk("""
            -----@-----
            -----------
            -----------
            -----------
            --!--!--!--
            -----------
            -----------
            --k--------
            XXXXXXXXXXX
            XXXXXXXXXXX
            """));
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("START_HIGH_SECTION", 0.1f);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("BRICKS_1", 0.15f);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("BRICKS_2", 0.15f);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("PIT", 0.25f);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("ENEMIES_1", 0.25f);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("PIPE_2", 0.1f);

        CHUNKS.put("PIT", new Chunk("""
            -------
            XX---XX
            XX---XX
            """));
        CHUNKS.get("PIT").addWeight("START_HIGH_SECTION", 0.1f);
        CHUNKS.get("PIT").addWeight("BRICKS_1", 0.15f);
        CHUNKS.get("PIT").addWeight("BRICKS_2", 0.15f);
        CHUNKS.get("PIT").addWeight("FOUR_QUESTION_BLOCKS", 0.25f);
        CHUNKS.get("PIT").addWeight("ENEMIES_1", 0.25f);
        CHUNKS.get("PIT").addWeight("PIPE_2", 0.1f);

        CHUNKS.put("ENEMIES_1", new Chunk("""
            -r-g-g-
            XXXXXXX
            XXXXXXX
            """));
        CHUNKS.get("ENEMIES_1").addWeight("START_HIGH_SECTION", 0.1f);
        CHUNKS.get("ENEMIES_1").addWeight("BRICKS_1", 0.15f);
        CHUNKS.get("ENEMIES_1").addWeight("BRICKS_2", 0.15f);
        CHUNKS.get("ENEMIES_1").addWeight("FOUR_QUESTION_BLOCKS", 0.25f);
        CHUNKS.get("ENEMIES_1").addWeight("PIT", 0.25f);
        CHUNKS.get("ENEMIES_1").addWeight("PIPE_2", 0.1f);

        CHUNKS.put("PIPE_2", new Chunk("""
            ----
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_2").addWeight("PIPE_INBETWEEN_1", 0.5f);
        CHUNKS.get("PIPE_2").addWeight("PIPE_INBETWEEN_2", 0.5f);

        CHUNKS.put("PIPE_3", new Chunk("""
            -tt-
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_3").addWeight("PIPE_INBETWEEN_1", 0.5f);
        CHUNKS.get("PIPE_3").addWeight("PIPE_INBETWEEN_2", 0.5f);

        CHUNKS.put("PIPE_4", new Chunk("""
            -tt-
            -tt-
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_4").addWeight("PIPE_INBETWEEN_1", 0.5f);
        CHUNKS.get("PIPE_4").addWeight("PIPE_INBETWEEN_2", 0.5f);

        CHUNKS.put("PIPE_5", new Chunk("""
            -tt-
            -tt-
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_5").addWeight("PIPE_INBETWEEN_1", 0.5f);
        CHUNKS.get("PIPE_5").addWeight("PIPE_INBETWEEN_2", 0.5f);

        CHUNKS.put("PIPE_INBETWEEN_1", new Chunk("""
            ----
            ----
            -g-g
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIPE_3", 0.25f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIPE_4", 0.25f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIPE_5", 0.20f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("START_HIGH_SECTION", 0.05f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("BRICKS_1", 0.05f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("BRICKS_2", 0.05f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("FOUR_QUESTION_BLOCKS", 0.05f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIT", 0.05f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("ENEMIES_1", 0.05f);

        CHUNKS.put("PIPE_INBETWEEN_2", new Chunk("""
            ----
            ----
            -kg-
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIPE_3", 0.25f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIPE_4", 0.25f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIPE_5", 0.20f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("START_HIGH_SECTION", 0.05f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("BRICKS_1", 0.05f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("BRICKS_2", 0.05f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("FOUR_QUESTION_BLOCKS", 0.05f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIT", 0.05f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("ENEMIES_1", 0.05f);

        CHUNKS.put("START_HIGH_SECTION", new Chunk("""
            -------g----
            ---SSSSSSSSS
            ------------
            ------------
            --g---------
            S@S-----1---
            ------------
            ------------
            ------------
            XXXXXXXXX---
            XXXXXXXXX---
            """));
        CHUNKS.get("START_HIGH_SECTION").addWeight("HIGH_SECTION_ENEMY_PIT", 0.4f);
        CHUNKS.get("START_HIGH_SECTION").addWeight("HIGH_SECTION_GAP", 0.4f);
        CHUNKS.get("START_HIGH_SECTION").addWeight("HIGH_SECTION_END", 0.2f);

        CHUNKS.put("HIGH_SECTION_ENEMY_PIT", new Chunk("""
            --k--------r
            SSSS-g-ggkSS
            ---SSSSSSSS-
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            """));
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_GAP", 0.6f);
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_END", 0.4f);

        CHUNKS.put("HIGH_SECTION_GAP", new Chunk("""
            -----------g
            SS-----SSSSS
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            """));
        CHUNKS.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_GAP", 0.6f);
        CHUNKS.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_END", 0.4f);

        CHUNKS.put("HIGH_SECTION_END", new Chunk("""
            ------------
            SS----------
            ----S-------
            ------------
            ------------
            -------CSS--
            ------------
            ------------
            -----------k
            ---XXXXXXXXX
            ---XXXXXXXXX
            """));
        CHUNKS.get("HIGH_SECTION_END").addWeight("BRICKS_1", 0.1f);
        CHUNKS.get("HIGH_SECTION_END").addWeight("BRICKS_2", 0.2f);
        CHUNKS.get("HIGH_SECTION_END").addWeight("FOUR_QUESTION_BLOCKS", 0.2f);
        CHUNKS.get("HIGH_SECTION_END").addWeight("PIT", 0.2f);
        CHUNKS.get("HIGH_SECTION_END").addWeight("ENEMIES_1", 0.2f);

        CHUNKS.put("END_STAIRS", new Chunk("""
            --------##-------
            -------###-------
            ------####-------
            -----#####-------
            ----######-------
            ---#######-------
            --########-------
            -#########-------
            XXXXXXXXXXXXXXXXX
            XXXXXXXXXXXXXXXXX
            """));
        CHUNKS.get("END_STAIRS").addWeight("END", 1.0f);

        CHUNKS.put("END", new Chunk("""
            ----
            -F--
            -#--
            XXXX
            XXXX
            """));

    }

}
