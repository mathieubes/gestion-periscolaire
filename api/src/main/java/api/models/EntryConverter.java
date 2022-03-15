package api.models;

public interface EntryConverter {
    Entry getEntryFromString(String str, String delimiter);
}
