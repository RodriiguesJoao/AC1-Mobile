import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ac1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    EditText etTitulo, etDiretor, etAno;
    Spinner spGenero, spFiltroGenero;
    RatingBar rbNota;
    CheckBox cbCinema, cbFiltroCinema;
    Button btnSalvar, btnOrdenarAno, btnOrdenarNota;
    RecyclerView rvFilmes;

    ArrayList<Filme> listaFilmes = new ArrayList<>();
    FilmeAdapter adapter;
    int editIndex = -1; // para saber se é edição

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitulo = findViewById(R.id.etTitulo);
        etDiretor = findViewById(R.id.etDiretor);
        etAno = findViewById(R.id.etAno);
        spGenero = findViewById(R.id.spGenero);
        spFiltroGenero = findViewById(R.id.spFiltroGenero);
        rbNota = findViewById(R.id.rbNota);
        cbCinema = findViewById(R.id.cbCinema);
        cbFiltroCinema = findViewById(R.id.cbFiltroCinema);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnOrdenarAno = findViewById(R.id.btnOrdenarAno);
        btnOrdenarNota = findViewById(R.id.btnOrdenarNota);
        rvFilmes = findViewById(R.id.rvFilmes);

        String[] generos = {"Todos", "Ação", "Drama", "Comédia", "Ficção", "Terror"};
        ArrayAdapter<String> adapterGenero = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, generos);
        spGenero.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"Ação", "Drama", "Comédia", "Ficção", "Terror"}));
        spFiltroGenero.setAdapter(adapterGenero);

        adapter = new FilmeAdapter(listaFilmes, this::carregarParaEdicao, this::removerFilme);
        rvFilmes.setLayoutManager(new LinearLayoutManager(this));
        rvFilmes.setAdapter(adapter);

        btnSalvar.setOnClickListener(v -> salvarFilme());
        spFiltroGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aplicarFiltros();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        cbFiltroCinema.setOnCheckedChangeListener((buttonView, isChecked) -> aplicarFiltros());

        btnOrdenarAno.setOnClickListener(v -> {
            Collections.sort(listaFilmes, Comparator.comparingInt(Filme::getAno));
            adapter.notifyDataSetChanged();
        });

        btnOrdenarNota.setOnClickListener(v -> {
            Collections.sort(listaFilmes, (f1, f2) -> Float.compare(f2.getNota(), f1.getNota()));
            adapter.notifyDataSetChanged();
        });
    }

    void salvarFilme() {
        String titulo = etTitulo.getText().toString();
        String diretor = etDiretor.getText().toString();
        int ano = Integer.parseInt(etAno.getText().toString());
        String genero = spGenero.getSelectedItem().toString();
        float nota = rbNota.getRating();
        boolean viuNoCinema = cbCinema.isChecked();

        Filme novo = new Filme(titulo, diretor, ano, genero, nota, viuNoCinema);

        if (editIndex == -1) {
            listaFilmes.add(novo);
        } else {
            listaFilmes.set(editIndex, novo);
            editIndex = -1;
        }

        adapter.notifyDataSetChanged();
        limparCampos();
    }

    void carregarParaEdicao(int position) {
        Filme f = listaFilmes.get(position);
        etTitulo.setText(f.getTitulo());
        etDiretor.setText(f.getDiretor());
        etAno.setText(String.valueOf(f.getAno()));
        rbNota.setRating(f.getNota());
        cbCinema.setChecked(f.isViuNoCinema());

        for (int i = 0; i < spGenero.getCount(); i++) {
            if (spGenero.getItemAtPosition(i).equals(f.getGenero())) {
                spGenero.setSelection(i);
                break;
            }
        }

        editIndex = position;
    }

    void removerFilme(int position) {
        listaFilmes.remove(position);
        adapter.notifyDataSetChanged();
    }

    void limparCampos() {
        etTitulo.setText("");
        etDiretor.setText("");
        etAno.setText("");
        rbNota.setRating(0);
        cbCinema.setChecked(false);
        spGenero.setSelection(0);
    }

    void aplicarFiltros() {
        String generoSelecionado = spFiltroGenero.getSelectedItem().toString();
        boolean filtrarCinema = cbFiltroCinema.isChecked();

        ArrayList<Filme> filtrada = new ArrayList<>();

        for (Filme f : listaFilmes) {
            boolean matchGenero = generoSelecionado.equals("Todos") || f.getGenero().equals(generoSelecionado);
            boolean matchCinema = !filtrarCinema || f.isViuNoCinema();

            if (matchGenero && matchCinema) {
                filtrada.add(f);
            }
        }

        adapter.atualizarLista(filtrada);
    }
}
