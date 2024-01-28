package de.bluecolored.nbtlibtest.libs;

import de.bluecolored.nbtlibtest.Chunk;
import de.bluecolored.nbtlibtest.NBTLibrary;
import dev.cerus.simplenbt.tag.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

public class SimpleNbtLibrary implements NBTLibrary {

    @Override
    public Chunk loadChunk(InputStream in) throws IOException {
        TagCompound data = SimpleNbtUtil.readCompound(in);
        return new ChunkImpl(
                data.<TagInt>get("DataVersion").getValue(),
                data.<TagInt>get("xPos").getValue(),
                data.<TagInt>get("yPos").getValue(),
                data.<TagInt>get("zPos").getValue(),
                data.<TagLong>get("InhabitedTime").getValue(),
                data.<TagString>get("Status").getValue()
        );
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
