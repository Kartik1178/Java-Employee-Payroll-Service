import java.io.IOException;
import java.nio.file.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Java8WatchServiceExample demonstrates Java 8's WatchService API.
 * It watches a given directory along with all its files and sub-directories,
 * printing events (ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY) as they occur.
 *
 * UC3: Create a Watch Service to watch a directory with all Files and
 * Sub-Directories
 *
 * @author Kartikeya
 * @version 1.0
 */
public class Java8WatchServiceExample {

    /** The WatchService instance that monitors the filesystem */
    private final WatchService watcher;

    /** Maps WatchKey to the corresponding Path being watched */
    private final Map<WatchKey, Path> dirWatchers;

    /**
     * Creates a WatchService and registers the given directory and all
     * sub-directories.
     *
     * @param dir the root directory to watch
     * @throws IOException if the WatchService cannot be created or directory cannot
     *                     be registered
     */
    public Java8WatchServiceExample(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.dirWatchers = new HashMap<>();
        scanAndRegisterDirectories(dir);
    }

    /**
     * Registers the given directory with the WatchService for CREATE, DELETE, and
     * MODIFY events.
     *
     * @param dir the directory to register
     * @throws IOException if the directory cannot be registered
     */
    private void registerDirWatchers(Path dir) throws IOException {
        WatchKey key = dir.register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        dirWatchers.put(key, dir);
    }

    /**
     * Recursively scans and registers the given directory and all its
     * sub-directories
     * with the WatchService.
     *
     * @param start the starting directory path
     * @throws IOException if scanning or registration fails
     */
    private void scanAndRegisterDirectories(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            /**
             * Visits each directory node and registers it with the WatchService.
             *
             * @param dir   the directory being visited
             * @param attrs basic file attributes of the directory
             * @return CONTINUE to proceed to the next entry
             * @throws IOException if registration fails
             */
            @Override
            public FileVisitResult preVisitDirectory(Path dir, java.nio.file.attribute.BasicFileAttributes attrs)
                    throws IOException {
                registerDirWatchers(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Processes all events for keys queued to the watcher.
     * Runs in an infinite loop until all directories are inaccessible.
     * Handles new sub-directory registration for ENTRY_CREATE events.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    void processEvents() {
        while (true) {
            WatchKey key; // wait for key to be signalled
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = dirWatchers.get(key);
            if (dir == null)
                continue;

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);
                System.out.format("%s: %s%n", event.kind().name(), child); // print event

                // If directory is created, then register it and its sub-directories
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child))
                            scanAndRegisterDirectories(child);
                    } catch (IOException x) {
                        /* ignore */ }
                } else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                    if (Files.isDirectory(child))
                        dirWatchers.remove(key);
                }
            }

            // Reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                dirWatchers.remove(key);
                if (dirWatchers.isEmpty())
                    break; // all directories are inaccessible
            }
        }
    }
}
