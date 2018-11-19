package tk.dehoghori.dehoghori.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

public class DehoGhoriContentProvider extends ContentProvider {

    private DehoGhoriDB mDehoGhoriDB;

    /*
    * this is a helper class, written by google engineers. you first register Uris in this
    * matcher with match codes. then when you pass an unknown uri to this matcher, it can tell which
    * match code it matches, thus telling your the Uri, so you can take different action based on the
    * given Uri as we will see
    * */
    private static final UriMatcher mUriMatcher;

    static {
        // here we add Uris and their corresponding match codes
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    }

    @Override
    public boolean onCreate() {
        // initialize content provider on startup.
        mDehoGhoriDB = new DehoGhoriDB(getContext());
        return true;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        // handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(@NonNull Uri uri) {
        // handle requests for the MIME type of the data at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        // handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        // handle huge data efficiently with bulk insert mechanism of SQL here
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
