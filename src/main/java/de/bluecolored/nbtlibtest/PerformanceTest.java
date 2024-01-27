package de.bluecolored.nbtlibtest;

import de.bluecolored.nbtlibtest.libs.*;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class PerformanceTest {

    private final NBTLibrary nbtLibrary;
    private final Path regionFilesFolder;

    private int regionCount;
    private int chunkCount;
    private long startTime;

    public synchronized void runPerformanceTest() {
        if (nbtLibrary == null) {
            LinkedHashMap<String, ArrayList<Double>> timings = new LinkedHashMap<>();
            System.out.println("All libraries will be tested");
            for (NBTLibrary library : new NBTLibrary[]{
                    new QuerzLibrary(),
                    new KyoriLibrary(),
                    new ChunkyLibrary(),
                    new BlueNBTLibrary(),
                    new BlueNBTLoadFullChunkLibrary(),
                    new BlueNBTDirectLibrary(),
                    new VanillaLibrary()
            }) {
                String libName = library.getClass().getSimpleName();
                System.out.println("Starting tests for " + libName + " ...");
                timings.put(libName, new ArrayList<>());
                for (int i = 0; i < 10; i++) {
                    double chunksPerSecond = new PerformanceTest(library, regionFilesFolder).runSinglePerformanceTest();
                    timings.get(libName).add(chunksPerSecond);
                }
            }
            System.out.println();
            System.out.println("Average timings:");
            System.out.println();
            System.out.println("Library                     |   Min   |   Max   | Average | Standard Deviation");
            System.out.println("----------------------------|---------|---------|---------|-------------------");
            for (String libName : timings.keySet()) {
                ArrayList<Double> libTimings = timings.get(libName);
                double min = libTimings.stream().mapToDouble(Double::doubleValue).min().orElse(0);
                double max = libTimings.stream().mapToDouble(Double::doubleValue).max().orElse(0);
                double average = libTimings.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                double stdDev = Math.sqrt(libTimings.stream().mapToDouble(t -> Math.pow(t - average, 2)).sum() / libTimings.size());
                System.out.printf("%s | %.2f | %.2f | %.2f | %.2f%n", libName, min, max, average, stdDev);
            }
        } else {
            System.out.println("Starting test for " + nbtLibrary.getClass().getSimpleName() + " ...");
            runSinglePerformanceTest();
        }
    }

    public synchronized double runSinglePerformanceTest() {
        this.chunkCount = 0;
        this.regionCount = 0;
        this.startTime = System.nanoTime();

        try (Stream<Path> regionFiles = Files.list(regionFilesFolder)) {
                regionFiles
                        .sequential()
                        .forEach(this::loadRegionFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        long endTime = System.nanoTime();
        long deltaTime = endTime - this.startTime;

        System.out.println("Library: " + nbtLibrary.getClass().getSimpleName() + "; Loaded " + chunkCount + " chunks from " + regionCount + " region files in " + deltaTime + "ns | " + getChunksPerSecond(endTime) + " chunks per second");
        return getChunksPerSecond(endTime);
    }

    public synchronized void loadRegionFile(Path regionFile) {
        try {
            Collection<Chunk> chunks = nbtLibrary.getChunks(regionFile);
            this.chunkCount += chunks.size();
            this.regionCount += 1;
            // System.out.println("Loaded " + chunks.size() + " chunks from " + regionFile.getFileName().toString() + " | " + getChunksPerSecond(System.nanoTime()) + " chunks per second");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public double getChunksPerSecond(long endTime) {
        long deltaTime = endTime - startTime;
        long nanosPerChunk = deltaTime / this.chunkCount;
        return 1e+9 / (double) nanosPerChunk;
    }

    public static void main(String[] args) {
        Path regionFileFolder = Path.of("regions");

        if (Files.notExists(regionFileFolder)) {
            System.out.println("Please specify create a folder 'regions' with region files in it!");
            return;
        }

        String lib = "";
        if (args.length > 0) lib = args[0];

        NBTLibrary library = switch (lib) {
            case "querz" -> new QuerzLibrary();
            case "kyori" -> new KyoriLibrary();
            case "chunky" -> new ChunkyLibrary();
            case "bluenbt" -> new BlueNBTLibrary();
            case "bluenbtFull" -> new BlueNBTLoadFullChunkLibrary();
            case "bluenbtDirect" -> new BlueNBTDirectLibrary();
            case "vanilla" -> new VanillaLibrary();
            default -> null;
        };

        new PerformanceTest(library, regionFileFolder).runPerformanceTest();
    }

}
