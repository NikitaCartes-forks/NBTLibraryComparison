package de.bluecolored.nbtlibtest.libs;

import de.bluecolored.nbtlibtest.Chunk;
import de.bluecolored.nbtlibtest.NBTLibrary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jnbt.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JnbtLibrary implements NBTLibrary {

    @Override
    public Chunk loadChunk(InputStream in) throws IOException {
        CompoundTag data = (CompoundTag) new NBTInputStream(new DataInputStream(in)).readTag();
        return new ChunkImpl(
                ((IntTag) data.getValue().get("DataVersion")).getValue(),
                ((IntTag) data.getValue().get("xPos")).getValue(),
                ((IntTag) data.getValue().get("yPos")).getValue(),
                ((IntTag) data.getValue().get("zPos")).getValue(),
                ((LongTag) data.getValue().get("InhabitedTime")).getValue(),
                ((StringTag) data.getValue().get("Status")).getValue()
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
