package de.bluecolored.nbtlibtest.libs;

import de.bluecolored.nbtlibtest.Chunk;
import de.bluecolored.nbtlibtest.NBTLibrary;
import de.piegames.nbt.CompoundTag;
import de.piegames.nbt.Tag;
import de.piegames.nbt.stream.NBTInputStream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PiegamesdeLibrary implements NBTLibrary {

    @Override
    public Chunk loadChunk(InputStream in) throws IOException {
        try (NBTInputStream stream = new NBTInputStream(in, NBTInputStream.NO_COMPRESSION)) {
            Tag<?> dataRaw = stream.readTag();
            CompoundTag data = dataRaw.getAsCompoundTag().get();
            return new ChunkImpl(
                    data.getIntValue("DataVersion").get(),
                    data.getIntValue("xPos").get(),
                    data.getIntValue("yPos").get(),
                    data.getIntValue("zPos").get(),
                    data.getLongValue("InhabitedTime").get(),
                    data.getStringValue("Status").get()
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
