package br.ufsm.projetosoftware.appdoacao;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

import br.ufsm.projetosoftware.appdoacao.database.ChatTableController;
import br.ufsm.projetosoftware.appdoacao.database.CreateDatabase;
import br.ufsm.projetosoftware.appdoacao.network.ChatPost;
import br.ufsm.projetosoftware.appdoacao.network.MensagemPost;
import br.ufsm.projetosoftware.appdoacao.network.ChatResponse;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.ChatView;
import br.ufsm.projetosoftware.appdoacao.view.ChatViewImpl;

public class ChatActivity extends AppCompatActivity implements ChatView.SendButtonListener, ChatView.DoarButtonListener{

    private ChatView chatView;
    private ChatTableController chatTableController;
    private Bundle extras;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private final String POSTCHAT = "POSTCHAT";
    private final String POSTMENSAGEM = "POSTMENSAGEM";
    private String chatURL;
    private String mensagemURL;
    private SharedPreferences loginSettings;
    private int idSolicitacao;
    private SimpleCursorAdapter adapter;
    private Handler refreshChatHandler;
    private int intent;
    public final static int IDOADOR = 0;
    public final static int ISOLICITANTE = 1;
    TimerTask timerTask;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatView = new ChatViewImpl(getWindow().getDecorView().getRootView());
        chatView.setSendButtonListener(this);
        initialize();
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        chatURL = getString(R.string.chatURL);
        mensagemURL = getString(R.string.mensagemURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
        chatTableController = new ChatTableController(getBaseContext());
        setChatAdapter();
        startHandler();
        refreshChat();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            timer.cancel();
            timer.purge();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initialize() {
        extras = getIntent().getExtras();
        idSolicitacao = extras.getInt("idSolicitacao");
        intent = extras.getInt("intent");
    }

    private void setChatAdapter(){
        Log.d("idSolicitacao", String.valueOf(idSolicitacao));
        Cursor cursor = chatTableController.getValues(idSolicitacao);
        String[] campos = new String[]{CreateDatabase.CHAT_REMETENTE_NOME, CreateDatabase.CHAT_MENSAGEM};
        int[] idView = new int[]{R.id.chatNome, R.id.chatMensagem};
        adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.chat_list, cursor, campos, idView, 0);
        chatView.setChatAdapter(adapter);
    }

    @Override
    public void onSendButtonClick() {
        MensagemPost mensagemPost = new MensagemPost();
        mensagemPost.setIdRemetente(loginSettings.getInt("uid", 0));
        mensagemPost.setIdSolicitacao(idSolicitacao);
        mensagemPost.setMessage(chatView.getMessage());
        String chatPostString = new Gson().toJson(mensagemPost, MensagemPost.class);
        volleyService.postDataVolley(POSTCHAT, mensagemURL, chatPostString);
    }

    private void postMensagemSuccess(String response){
        //MensagemResponse mensagemResponse = new Gson().fromJson(response, MensagemResponse.class);
    }

    private void postChatSucess(String response){
        if(response != null){
            //System.out.println(response);
            ChatResponse chatResponse = new Gson().fromJson(response, ChatResponse.class);
            if(chatResponse!=null) {
                chatTableController.insertFromList(chatResponse.getMensagemList());
                adapter.getCursor().close();
                Cursor cursor = chatTableController.getValues(idSolicitacao);
                adapter.changeCursor(cursor);
            }
        }
    }

    private void initCallback(){
        resultCallback = new IResultString() {
            @Override
            public void notifySuccess(String requestType, String response) {
                //Log.d("notify", response);
                if(requestType.equals(POSTCHAT)){
                    postChatSucess(response);
                }
                if(requestType.equals(POSTMENSAGEM)){
                    postMensagemSuccess(response);
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("Erro na conexao", error.toString());
                Toast.makeText(ChatActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }

    private void getChat(){
        int lastMessageId = chatTableController.getLastMessageId(idSolicitacao);
        ChatPost chatPost = new ChatPost();
        chatPost.setIdSolicitacao(idSolicitacao);
        chatPost.setLastMessage(chatTableController.getLastMessageId(idSolicitacao));
        String chatPostString = new Gson().toJson(chatPost, ChatPost.class);
        volleyService.postDataVolley(POSTCHAT, chatURL, chatPostString);
    }

    private void startHandler(){
        refreshChatHandler = new Handler(){
            public void handleMessage(Message msg){
                getChat();
            }
        };
    }

    private void refreshChat(){
        //Handler handler = new Handler();
        /*handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getChat();
            }
        }, 500);*/
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                refreshChatHandler.obtainMessage(1).sendToTarget();
            }
        };
        timer.schedule(timerTask, 1, 1000);
    }

    @Override
    public void onDoarButtonClick() {
        //TODO avaliacao
    }
}
