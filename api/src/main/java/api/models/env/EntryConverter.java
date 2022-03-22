package api.models.env;

public interface EntryConverter {
    Entry<String, String> getEntryFromString(String str, String delimiter);
}
