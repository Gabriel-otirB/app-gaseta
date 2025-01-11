package devandroid.gabrito.appgaseta.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GasEtaDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "gaseta.db";
    public static final int DB_VERSION = 1;

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

    // Criar métodos para implementar um CRUD
    // C = Create criar o banco de dados e as tabelas
    // Create database nome_do_banco_de_dados.db (SQL)
    // Create table (SQL)



    // R = Retrieve/Read recuperar os dados salvos as tabelas
    // Select * from table (SQL)

    // U = Update alterar os dados que já existem em um registro na tabela
    // Update from table (SQL)

    // D = Delete deletar os dados/registros de uma tabela
    // Delete from (SQL)
}
