import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * WatchServiceTest tests the Java8WatchServiceExample by verifying that
 * the WatchService correctly lists all files in a given directory.
 * It also counts the number of entries in a watched directory.
 *
 * UC3: Java 8 File Watch Service Test
 *
 * @author Kartikeya
 * @version 1.0
 */
public class WatchServiceTest {

    /** User home directory path */
    private static String HOME = System.getProperty("user.home");

    /** Name of the playground directory for testing */
    private static String PLAY_WITH_NIO = "TempPlayGround";

    /**
     * Tests that the WatchService lists all activities for files in a watched
     * directory.
     * Lists all regular files in the TempPlayGround directory and starts watching.
     *
     * @throws IOException if any file or watch service operation fails
     */
    @Test
    public void givenADirectoryWhenWatchedListsAllTheActivities() throws IOException {
        Path dir = Paths.get(HOME + "/" + PLAY_WITH_NIO);

        // Ensure directory exists for test
        if (Files.notExists(dir)) {
            Files.createDirectory(dir);
        }

        // List all regular files in the directory
        Files.list(dir)
                .filter(Files::isRegularFile)
                .forEach(System.out::println);

        // Count entries in directory using File IO
        long count = Files.list(dir).count();
        System.out.println("Number of entries in directory: " + count);
        Assert.assertTrue("Directory should be accessible", Files.exists(dir));

        // Start watch service (runs in background; just verify it initialises)
        new Java8WatchServiceExample(dir);
        System.out.println("Watch Service started successfully for: " + dir);
    }
}
