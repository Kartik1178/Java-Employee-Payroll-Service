import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

/**
 * NIOFileAPITest demonstrates and tests Java NIO File Operations.
 * It covers checking file existence, deleting files, creating directories,
 * creating empty files, and listing files with various filters.
 *
 * UC2: JUnit Test Cases for Files API
 *
 * @author Kartikeya
 * @version 1.0
 */
public class NIOFileAPITest {

    /** User home directory path */
    private static String HOME = System.getProperty("user.home");

    /** Name of the playground directory for testing */
    private static String PLAY_WITH_NIO = "TempPlayGround";

    /**
     * Tests various NIO file operations in sequence:
     * Check File Exists, Delete File, Create Directory, Create Empty Files,
     * List Files, List Directories, and List Files with Extension.
     *
     * @throws IOException if any file operation fails
     */
    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {
        // Check File Exists
        Path homePath = Paths.get(HOME);
        Assert.assertTrue(Files.exists(homePath));

        // Delete File and Check File Not Exist
        Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        if (Files.exists(playPath)) {
            deleteFilesAndDirs(playPath.toFile());
        }
        Assert.assertTrue(Files.notExists(playPath));

        // Create Directory
        Files.createDirectory(playPath);
        Assert.assertTrue(Files.exists(playPath));

        // Create Empty Files
        IntStream.range(1, 10).forEach(cntr -> {
            Path tempFile = Paths.get(playPath + "/temp" + cntr);
            Assert.assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            } catch (IOException e) {
                // file may already exist
            }
            Assert.assertTrue(Files.exists(tempFile));
        });

        // List Files (Regular Files only)
        System.out.println("=== Regular Files ===");
        Files.list(playPath)
                .filter(Files::isRegularFile)
                .forEach(System.out::println);

        // List Directories
        System.out.println("=== Directories ===");
        Files.newDirectoryStream(playPath)
                .forEach(System.out::println);

        // List Files with Extension starting with "temp"
        System.out.println("=== Files starting with 'temp' ===");
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile()
                && path.toString().contains("temp"))
                .forEach(System.out::println);
    }

    /**
     * Recursively deletes files and directories from the given file.
     *
     * @param file the root file or directory to delete
     */
    private void deleteFilesAndDirs(java.io.File file) {
        if (file.isDirectory()) {
            for (java.io.File f : file.listFiles()) {
                deleteFilesAndDirs(f);
            }
        }
        file.delete();
    }
}
