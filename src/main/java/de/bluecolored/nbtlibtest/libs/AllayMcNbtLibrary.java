package de.bluecolored.nbtlibtest.libs;

import de.bluecolored.nbtlibtest.Chunk;
import de.bluecolored.nbtlibtest.NBTLibrary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.IOException;
import java.io.InputStream;

public class AllayMcNbtLibrary implements NBTLibrary {

    @Override
    public Chunk loadChunk(InputStream in) throws IOException {
        try (NBTInputStream stream = NbtUtils.createNetworkReader(in)) {
            NbtMap data = (NbtMap) stream.readTag();
            return new ChunkImpl(
                    data.getInt("DataVersion"),
                    data.getInt("xPos"),
                    data.getInt("yPos"),
                    data.getInt("zPos"),
                    data.getLong("InhabitedTime"),
                    data.getString("Status")
            );
        }
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChunkImpl implements Chunk {
        private int dataVersion;
        private int xPos;
        private int yPos;
        private int zPos;
        private long inhabitedTime;
        private String status;
    }
}
