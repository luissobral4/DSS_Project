/**
 * Classe que representa uma Prateleira
 */

package Business.Armazem;
import Data.PaleteDAO;

public class Prateleira {
    private int prateleiraID;
    private int capacidade;
    private int ocupacao;
    private PaleteDAO paletes;

    public Prateleira(int prateleiraID,int capacidade,int ocupacao) {
        this.prateleiraID = prateleiraID;
        this.capacidade = capacidade;
        this.ocupacao = ocupacao;
        paletes = new PaleteDAO();
    }

    /**
     * Devolve o ID da Prateleira
     *
     * @return ID da Prateleira
     */
    public int getPrateleiraID() {
        return prateleiraID;
    }

    /**
     * Devolve a capacidade da Prateleira
     *
     * @return Capacidade da Prateleira
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Devolve a ocupação da Prateleira
     *
     * @return Ocupação da Prateleira
     */
    public int getOcupacao() {
        return ocupacao;
    }

    /**
     * Adiciona uma Palete
     *
     * @param qrCode       Código QR da Palete
     * @param zona         Zona da Prateleira
     */
    public void addPalete (String qrCode, String zona) {
        int n = paletes.sizeLocalizacao();
        paletes.put(new Palete(qrCode, prateleiraID, zona,n+1));
        ocupacao++;
    }
}
