package br.ufsm.projetosoftware.appdoacao.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.ufsm.projetosoftware.appdoacao.models.Mensagem;

/**
 * Created by Felipe on 02/07/2017.
 */

public class ChatTableController {
    private SQLiteDatabase db;
    private CreateDatabase database;

    public ChatTableController(Context context) {
        database = new CreateDatabase(context);
    }

    public boolean insertValues(int mid, int idSolicitacao, int idRemetente, String nome, String mensagem){
        ContentValues values;
        long result;
        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateDatabase.CHAT_ID, mid);
        values.put(CreateDatabase.CHAT_SOLICITACAO_ID, idSolicitacao);
        //values.put(CreateDatabase.CHAT_DOADOR_ID, idDoador);
        //values.put(CreateDatabase.CHAT_RECEPTOR_ID, idReceptor);
        values.put(CreateDatabase.CHAT_REMETENTE_ID, idRemetente);
        values.put(CreateDatabase.CHAT_REMETENTE_NOME, nome);
        values.put(CreateDatabase.CHAT_MENSAGEM, mensagem);
        result = db.insert(CreateDatabase.TABLE_CHAT, null, values);
        db.close();
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public void insertFromList(List<Mensagem> mensagemList){
        if(mensagemList!= null) {
            for (Mensagem m : mensagemList) {
                insertValues(m.getMid(), m.getIdSolicitacao(), m.getIdRemetente(), m.getNomeRemetente(), m.getMensagem());
            }
        }
    }

    public Cursor getValues(int idSolicitacao){
        Cursor cursor;
        String[] columns = {CreateDatabase.ID, CreateDatabase.CHAT_REMETENTE_NOME, CreateDatabase.CHAT_MENSAGEM};
        String where = CreateDatabase.CHAT_SOLICITACAO_ID + " = ?";
        String[] whereArgs = {String.valueOf(idSolicitacao)};
        db = database.getReadableDatabase();
        cursor = db.query(CreateDatabase.TABLE_CHAT, columns, where, whereArgs, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Integer getLastMessageId(int idSolicitacao){
        Cursor cursor;
        String[] columns = {CreateDatabase.ID, CreateDatabase.CHAT_ID};
        String where = CreateDatabase.CHAT_SOLICITACAO_ID + " = ?";
        String[] whereArgs = {String.valueOf(idSolicitacao)};
        db = database.getReadableDatabase();
        cursor = db.query(CreateDatabase.TABLE_CHAT, columns, where, whereArgs, null, null, null);
        int messageId;
        if(cursor.getCount() >= 1){
            cursor.moveToLast();
            messageId =  cursor.getInt(1);
        }
        else{
            messageId = -1;
        }
        db.close();
        return messageId;
    }
}
