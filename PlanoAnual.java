public class PlanoAnual extends Plano {
    private double percentualDesconto;

    public PlanoAnual(int id, String nome, double valorBase, double percentualDesconto) {
        super(id, nome, valorBase);
        setPercentualDesconto(percentualDesconto);
    }

    @Override
    public double calcular() {
        return getValorBase() * (1 - percentualDesconto / 100.0);
    }

    public double getPercentualDesconto() { return percentualDesconto; }

    public void setPercentualDesconto(double percentualDesconto) {
        if (percentualDesconto < 0 || percentualDesconto > 30)
            throw new IllegalArgumentException("Desconto deve estar entre 0% e 30%.");
        this.percentualDesconto = percentualDesconto;
    }
}
