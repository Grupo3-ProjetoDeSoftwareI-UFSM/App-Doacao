package br.ufsm.projetosoftware.appdoacao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Felipe on 02/07/2017.
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

    public CreateDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDropChat = "DROP TABLE IF EXISTS " + TABLE_CHAT;
        db.execSQL(sqlDropChat);

    }
}
