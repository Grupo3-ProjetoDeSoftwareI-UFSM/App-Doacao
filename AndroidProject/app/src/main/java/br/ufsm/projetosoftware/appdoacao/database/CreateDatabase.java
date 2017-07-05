package br.ufsm.projetosoftware.appdoacao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Cria banco de dados SQLite e suas tabelas
 */

public class CreateDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    public static final int VERSION = 1;
    public static final String ID = "_id";
    public static final String TABLE_CHAT = "chat";
    public static final String CHAT_ID = "mid";
    public static final String CHAT_SOLICITACAO_ID = "idSolicitacao";
    public static final String CHAT_DOADOR_ID = "idDoador";
    public static final String CHAT_RECEPTOR_ID = "idReceptor";
    public static final String CHAT_REMETENTE_ID = "idRemetente";
    public static final String CHAT_REMETENTE_NOME = "nomeRemetente";
    public static final String CHAT_MENSAGEM = "mensagem";

    /**
     * Construtor da classe CreateDatabase
     * @param context
     */
    public CreateDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * Constroi tabelas quando banco for criado
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateChat = "CREATE TABLE " + TABLE_CHAT + " ("
                + ID + " integer primary key, "
                + CHAT_ID + " int, "
                + CHAT_SOLICITACAO_ID + " int, "
                + CHAT_DOADOR_ID + " int, "
                + CHAT_RECEPTOR_ID + " int, "
                + CHAT_REMETENTE_ID + " int, "
                + CHAT_REMETENTE_NOME + " text, "
                + CHAT_MENSAGEM + " text"
                + ")";
        db.execSQL(sqlCreateChat);
    }

    /**
     * Apaga tabela antiga quando uma nova versão for criada
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDropChat = "DROP TABLE IF EXISTS " + TABLE_CHAT;
        db.execSQL(sqlDropChat);

    }
}
