package api.services.env;

import api.models.enums.EnvKey;
import api.models.env.Entry;
import api.models.env.EntryConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EnvService implements EntryConverter {
    private Map<EnvKey, String> env;

    public EnvService(String path) {
        this.env = new HashMap<>();

        try {
            final var reader = getFileReader(path);
            final var entries = readLinesAndGetEntries(reader);
            entries.forEach(entry -> this.env.put((EnvKey) entry.getKey(), (String) entry.getValue()));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private Scanner getFileReader(String path) throws FileNotFoundException {
        try {
            final var file = new File(".env");
            final var reader = new Scanner(file);
            return reader;
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    private List<Entry> readLinesAndGetEntries(Scanner reader) {
        final List<Entry> entries = new ArrayList<>();
        while (reader.hasNextLine()) {
            final var entry = this.getEntryFromString(reader.nextLine(), "=");
            entries.add(entry);
        }
        return entries;
    }

    @Override
    public Entry getEntryFromString(String str, String delimiter) {
        final var keyValue = str.split(delimiter);
        return new Entry(EnvKey.valueOf(keyValue[0]), keyValue[1]);
    }

    public Map<EnvKey, String> getEnv() {
        return this.env;
    }
}
