package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by usuario on 7/4/2016.
 */
public class Connection extends SQLiteOpenHelper {

    public Connection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            final String sqlCreatePlace;
            final String sqlCreateAnimal;
            final String sqlInsertUsers;
            final String sqlInsertAnimal;

            //create
            sqlCreatePlace = "CREATE TABLE " + "users " +
                    "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, password TEXT," + " user TEXT);";

            sqlCreateAnimal = "CREATE TABLE " + "animal " +
                    "(" + " id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT," + " category TEXT," + " weight INTEGER,"
                        +" population INTEGER);";

            sqlInsertUsers = "INSERT INTO users (user, password) Values ('hsolicha', '12345');";

            sqlInsertAnimal = "INSERT INTO animal (name, category, weight, population) Values ('Venado', 'Mamífero', 100, 50);";

            db.execSQL(sqlCreatePlace);
            db.execSQL(sqlCreateAnimal);
            db.execSQL(sqlInsertUsers);
            db.execSQL(sqlInsertAnimal);
        }catch (Exception error){
            Log.d("error", error.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            StringBuilder sql = new StringBuilder();
            StringBuilder sqla = new StringBuilder();
            for (int indiceVersion = oldVersion; indiceVersion < newVersion; indiceVersion++){
                int nextVersion = indiceVersion + 1;
                switch (nextVersion){
                    case 1:
                        String sqlDropPlace = "DROP TABLE IF EXISTS users";
                        sql.append(sqlDropPlace);

                        String sqlDropAnimal = "DROP TABLE IF EXISTS animal";
                        sqla.append(sqlDropAnimal);
                        break;
                }
                db.execSQL(sql.toString());
                onCreate(db);
            }
        }catch(Exception error){
            Log.d("error", error.getMessage());
        }
    }
}
