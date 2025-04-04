import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FilmeAdapter extends RecyclerView.Adapter {
    public FilmeAdapter(ArrayList<Filme> listaFilmes, Object carregarParaEdicao, Object removerFilme) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void atualizarLista(ArrayList<Filme> filtrada) {

    }
}
