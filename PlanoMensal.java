public class PlanoMensal extends Plano {
    private double taxaAdesao;

    public PlanoMensal(int id, String nome, double valorBase, double taxaAdesao) {
        super(id, nome, valorBase);
        setTaxaAdesao(taxaAdesao);
    }

    @Override
    public double calcular() { return getValorBase() + taxaAdesao; }

    public double getTaxaAdesao() { return taxaAdesao; }

    public void setTaxaAdesao(double taxaAdesao) {
        if (taxaAdesao < 0)
            throw new IllegalArgumentException("Taxa não pode ser negativa.");
        this.taxaAdesao = taxaAdesao;
    }
}
