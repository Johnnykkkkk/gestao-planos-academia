import java.util.ArrayList;
import java.util.List;

public class PlanoService {
    private final ArrayList<Plano> planos = new ArrayList<>();
    private int proximoId = 1;

    public Plano cadastrarMensal(String nome, double valor, double taxa) {
        Plano p = new PlanoMensal(proximoId++, nome, valor, taxa);
        planos.add(p);
        return p;
    }

    public Plano cadastrarAnual(String nome, double valor, double desconto) {
        Plano p = new PlanoAnual(proximoId++, nome, valor, desconto);
        planos.add(p);
        return p;
    }

    public List<Plano> listarPlanos() { return new ArrayList<>(planos); }

    public Plano buscarPlano(int id) {
        for (Plano p : planos) if (p.getId() == id) return p;
        return null;
    }

    public void editarPlano(int id, String nome, double valor) {
        Plano p = buscarPlano(id);
        if (p == null) throw new IllegalArgumentException("Plano não encontrado.");
        if (!p.isAtivo()) throw new IllegalStateException("Plano inativo não pode ser editado.");
        p.setNome(nome);
        p.setValorBase(valor);
    }

    public void desativarPlano(int id) {
        Plano p = buscarPlano(id);
        if (p == null) throw new IllegalArgumentException("Plano não encontrado.");
        if (!p.isAtivo()) throw new IllegalStateException("Plano já está inativo.");
        // Na integração, verificar no AcademiaService se há matrícula ATIVA vinculada.
        p.desativar();
    }
}
