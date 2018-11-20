package tk.dehoghori.dehoghori.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DehoGhoriDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "DehoGhori.db";
    private static final int DB_VERSION = 1;


    public DehoGhoriDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
        insertInitialData(db);
    }

    private void createTable(SQLiteDatabase db) {

    }

    private void insertInitialData(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // manage tables & data onUpgrade
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

}
