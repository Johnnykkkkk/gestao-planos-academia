public abstract class Plano {
    private final int id;
    private String nome;
    private double valorBase;
    private boolean ativo = true;

    public Plano(int id, String nome, double valorBase) {
        setNome(nome);
        setValorBase(valorBase);
        this.id = id;
    }

    public abstract double calcular();

    public void desativar() { ativo = false; }

    public String exibirDados() {
        return String.format("ID: %d | Nome: %s | Tipo: %s | Valor: R$ %.2f | Ativo: %s",
            id, nome, getClass().getSimpleName(), calcular(), ativo ? "Sim" : "Não");
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getValorBase() { return valorBase; }
    public boolean isAtivo() { return ativo; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome obrigatório.");
        this.nome = nome.trim();
    }

    public void setValorBase(double valorBase) {
        if (valorBase <= 0)
            throw new IllegalArgumentException("Valor-base deve ser maior que zero.");
        this.valorBase = valorBase;
    }
}
