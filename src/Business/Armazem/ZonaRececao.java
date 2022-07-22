/**
 * Classe que representa a Zona de receção
 */

package Business.Armazem;
import Data.PaleteDAO;

import java.util.*;
import java.util.Collection;

public class ZonaRececao extends Zona {
    private PaleteDAO paletes;

    public ZonaRececao(String zonaID) {
        super(zonaID);
        paletes = new PaleteDAO();
    }

    /**
     * Acrescenta uma Palete à Zona de Receção
     *
     * @param qrCode        Código QR da Palete
     */
    public void acrescentaPalete (String qrCode) {
        int n = paletes.sizeLocalizacao();
        Palete palete = new Palete(qrCode, 0, super.getZonaID(),n + 1);
        paletes.put(palete);
    }

    /**
     * Verifica se uma determinada Palete existe na Zona de receção
     *
     * @param qrCode Código QR da Palete
     * @return  Existência da Palete
     */
    public Boolean existePalete (String qrCode){
        return paletes.containsKey(qrCode);
    }

    /**
     * Recolha de uma Palete da Zona de Receção
     *
     * @param qrCode Código QR da Palete
     */
    public void recolhePalete(String qrCode){
        Palete p = paletes.get(qrCode);
        p.setZonaID(null);
        paletes.put(p);
    }

    /**
     * Listagem das Paletes
     *
     * @return Listagem das Paletes
     */
    public List<String> getListagem(){
        return paletes.listLocalizacoes();
    }

    /**
     * Lista das Paletes por Notificar
     *
     * @return Lista das Paletes por Notifcar
     */
    public List<String> paletesPorNotificar() {
        return paletes.paletesPorNotificar();
    }
}
