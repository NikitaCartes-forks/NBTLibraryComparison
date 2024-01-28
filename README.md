# NBT Library Comparison

## Build
Run `./gradlew clean shadowJar`
You'll find the built jar in `./build/libs`.

## Run
To run, put the jar file next to a folder named `regions` containing all the region-files you want to run the test against.
Then run `java jar NBTLibraryComparison-1.0-all.jar <library>`

## Test Setup
Tests were run on Windows 10 | Java 17  
CPU: i7-8700K | RAM: 64GB 4000 MHz  
Disk: Samsung SSD 970 EVO Plus  

The same region-files were used for each test-run.  
209 region files with 164824 chunks.  
The world used is the Hermitcraft S9 world-download.  

The command to run the tests: `java -jar -Xms1G -Xmx1G .\NBTLibraryComparison-1.0-all.jar <library>`

## Test results
| library       | duration (ns) | chunks/s | notes                                                                                 |
|---------------|---------------|----------|---------------------------------------------------------------------------------------|
| bluenbt       | 30995222500   | 5317.7   | -                                                                                     |
| bluenbtDirect | 30590971800   | 5388.0   | BlueNBT using the low-level NBTReader directly                                        |
| bluenbtFull   | 44712561900   | 3686.3   | BlueNBT but loading the entire chunk-data into a Map instead of only the data we want |
| kyori         | 50787246000   | 3245.4   | -                                                                                     |
| querz         | 42590299800   | 3870.0   | -                                                                                     |
| chunky        | 42282843500   | 3898.1   | -                                                                                     |

## Tested libraries
- [OpenNBT](https://github.com/GeyserMC/OpenNBT)
- [JNBT](https://github.com/Morlok8k/JNBT)
- [BitBuf's nbt](https://github.com/BitBuf/nbt)
- [IzzelAliz's nbt](https://github.com/IzzelAliz/nbt)
- [ViaNBT](https://github.com/ViaVersion/ViaNBT)
- [AllayMc's NBT](https://github.com/AllayMC/NBT)
- [Piegamesde's NBT](https://github.com/piegamesde/nbt)
- [simple-nbt](https://github.com/piegamesde/nbt)