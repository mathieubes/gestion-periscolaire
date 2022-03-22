package api.models.env;

public interface EntryConverter {
    Entry getEntryFromString(String str, String delimiter);
}
