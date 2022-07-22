/**
 * Classe que representa a Zona de Armazenamento
 */
package Business.Armazem;
import Data.PrateleiraDAO;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class ZonaArmazenamento extends Zona {
    private PrateleiraDAO prateleiras;

    public ZonaArmazenamento(String zonaID) {
        super(zonaID);
        prateleiras = new PrateleiraDAO();
    }

    /**
     * Devolve a Prateleira escolhida para a qual será transportada um a Palete
     *
     * @return Prateleira destino
     */
    public int escolhePrateleira(){
        return prateleiras.escolhePrateleira();
    }

    /**
     * Arruma uma Palete numa Prateleira atualizando a última
     *
     * @param prateleira Prateleira destino
     * @param qrCode     Código QR da Palete a ser arrumada
     */
    public void arrumaPalete (int prateleira,String qrCode) {
        Prateleira p = prateleiras.get(prateleira);
        p.addPalete(qrCode,"Armazenamento");
        prateleiras.updatePrateleira(p);
    }
}
