package devandroid.gabrito.appgaseta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GasEtaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "gaseta.db";
    private static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;

    public GasEtaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //QUERY SQL para Criar uma tabela
        String sqlTabelaCombustivel = "CREATE TABLE combustivel " +
                "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nomeDoCombustivel TEXT," +
                " precoDoCombustivel REAL, " +
                " recomendacao TEXT" +
                ")";

        String sqlTabelaVeiculo = "CREATE TABLE veiculo " +
                "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nomeDoCarro TEXT," +
                " placaDoCarro TEXT" +
                ")";

        db.execSQL(sqlTabelaCombustivel);
        db.execSQL(sqlTabelaVeiculo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void salvarObjeto(String tabela, ContentValues dados) {
        db.insert(tabela, null, dados);
    }

}
