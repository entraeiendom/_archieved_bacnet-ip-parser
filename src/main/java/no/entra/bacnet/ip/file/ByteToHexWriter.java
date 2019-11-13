package no.entra.bacnet.ip.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static no.entra.bacnet.ip.utils.HexParser.toHexString;

public class ByteToHexWriter {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger( ByteToHexWriter.class );
    private static final int MAX_LINE_LENGTH = 2048;

    private final File byteFile;
    private final File hexStringFile;

    public ByteToHexWriter(File byteFile, File hexStringFile) {
        this.byteFile = byteFile;
        this.hexStringFile = hexStringFile;
    }

    public void convertByteToHex() throws IOException {
        if (byteFile.canRead()) {
            byte[] allBytes = readFromFile(byteFile.getAbsolutePath());
            int hexLineLength = 0;
            String hexLine = "";
            String hexValue = "";
            for (byte octet : allBytes) {
                hexValue = toHexString(octet);
                if (hexValue.equals("81")) {
                    writeHexString(hexLine);
                    hexLine = "";
                }
                hexLine += hexValue;
            }
            System.out.println("HexValue: " + hexValue);
        }

    }

    public byte[] readFromFile(String filename) throws IOException {
        byte[] content = Files.readAllBytes(Paths.get(filename));
        return content;
    }

    public void writeHexString(String hexString) throws IOException {
        boolean append = true;
        FileWriter fw = new FileWriter(hexStringFile.getAbsoluteFile(), append);
        fw.write(hexString);
        fw.write("\n");
        System.out.println("Wrote: " + hexString);
        fw.close();
    }

    public static void main(String[] args) {
        File byteFile = new File("C:\\Users\\gp694\\examples\\bacnet\\bacnet4j-wrapper\\packetdata");
        File hexStringFile = new File("C:\\Users\\gp694\\examples\\bacnet\\bacnet4j-wrapper\\packetdata-hexstring");
        ByteToHexWriter writer = new ByteToHexWriter(byteFile, hexStringFile);
        try {
            writer.convertByteToHex();
        } catch (IOException e) {
            log.error("Failed to write. From {}, To {}, Reason {}", byteFile, hexStringFile, e.getMessage());
        }
    }


}
