package devandroid.gabrito.appgaseta.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;

import java.util.List;

import devandroid.gabrito.appgaseta.database.GasEtaDB;
import devandroid.gabrito.appgaseta.model.Combustivel;
import devandroid.gabrito.appgaseta.view.GasEtaActivity;

public class CombustivelController extends GasEtaDB {

    SharedPreferences preferences;
    SharedPreferences.Editor dadosPreferences;

    public static final String  NOME_PREFERENCES = "pref_gaseta";

    public CombustivelController(GasEtaActivity activity) {
        super(activity);

        preferences = activity.getSharedPreferences(NOME_PREFERENCES, 0);
        dadosPreferences = preferences.edit();
    }

    public void salvar(Combustivel combustivel) {

        ContentValues dados = new ContentValues();

        dadosPreferences.putString("combustivel", combustivel.getNomeDoCombustivel());
        dadosPreferences.putFloat("precoDoCombustivel", (float) combustivel.getPrecoDoCombustivel());
        dadosPreferences.putString("recomendacao", combustivel.getRecomendacao());
        dadosPreferences.apply();

        dados.put("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
        dados.put("precoDoCombustivel", combustivel.getPrecoDoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());

        salvarObjeto("combustivel", dados);

    }

    public List<Combustivel> getListaDeDados() {
        return listarDados();
    }

    public void limpar() {
        dadosPreferences.clear();
        dadosPreferences.apply();
    }

    public void alterar(Combustivel combustivel) {

        ContentValues dados = new ContentValues();

        dados.put("id", combustivel.getId());
        dados.put("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
        dados.put("precoDoCombustivel", combustivel.getPrecoDoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());

        alterarObjeto("combustivel", dados);

    }

    public void deletar(int id) {
        deletarObjeto("combustivel", id);
    }

}
