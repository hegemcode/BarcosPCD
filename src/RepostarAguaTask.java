public class RepostarAguaTask implements Runnable{
    private BarcoPetrolero petrolero;
    public RepostarAguaTask (BarcoPetrolero petrolero){
        this.petrolero = petrolero;
    }
    @Override
    public void run() {
        while (petrolero.getDeposito_agua() < 5000) {
            ZonaCarga.getInstance().repostarAgua(petrolero);
        }
    }
}
