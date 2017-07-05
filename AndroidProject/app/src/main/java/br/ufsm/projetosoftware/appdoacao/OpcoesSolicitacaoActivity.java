package br.ufsm.projetosoftware.appdoacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.ufsm.projetosoftware.appdoacao.view.OpcoesSolicitacaoView;
import br.ufsm.projetosoftware.appdoacao.view.OpcoesSolicitacaoViewImpl;

/**
 * Activity da tela de Opcoes de Solicitacao
 */
public class OpcoesSolicitacaoActivity extends AppCompatActivity
        implements OpcoesSolicitacaoView.AvaliacoesButtonListener,
        OpcoesSolicitacaoView.ChatButtonListener,
        OpcoesSolicitacaoView.ConcluiDoacaoButtonListener {

    OpcoesSolicitacaoView opcoesSolicitacaoView;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes_solicitacao);
        opcoesSolicitacaoView = new OpcoesSolicitacaoViewImpl(getWindow().getDecorView().getRootView());
        opcoesSolicitacaoView.setAvaliacoesListener(this);
        opcoesSolicitacaoView.setChatListener(this);
        opcoesSolicitacaoView.setConcluiDoacaoListener(this);
        extras = getIntent().getExtras();
    }

    /**
     * Abre a lista de avaliações do usuário solicitante
     */
    @Override
    public void onAvaliacoesButtonClick() {
        Intent toListAvaliacoes = new Intent(OpcoesSolicitacaoActivity.this, ListAvaliacoesActivity.class);
        toListAvaliacoes.putExtra("idSolicitacao", extras.getInt("idSolicitacao"));
        startActivity(toListAvaliacoes);
    }

    /**
     * Inicia o chat com o usuário solicitante
     */
    @Override
    public void onChatButtonClick() {
        Intent toChat = new Intent(OpcoesSolicitacaoActivity.this, ChatActivity.class);
        toChat.putExtra("idSolicitacao", extras.getInt("idSolicitacao"));
        startActivity(toChat);

    }

    /**
     * Inicia avaliação do usuário solicitante pelo doador
     */
    @Override
    public void onConcluirDoacaoButtonClick() {
        Intent toAvaliacao = new Intent(OpcoesSolicitacaoActivity.this, AvaliacaoActivity.class);
        toAvaliacao.putExtra("idSolicitacao", extras.getInt("idSolicitacao"));
        toAvaliacao.putExtra("idAvaliado", extras.getInt("idAvaliado"));
        toAvaliacao.putExtra("intent", "doador");
        startActivity(toAvaliacao);
    }
}
