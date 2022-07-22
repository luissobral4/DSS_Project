/**
 * Classe que representa uma Palete
 */
package Business.Armazem;

public class Palete {
    private String qrCode;
    private Localizacao localizacao;

    public Palete(String qrCode, Localizacao l) {
        this.qrCode = qrCode;
        localizacao = l;
    }

    public Palete(String qrCode,int prat,String zona,int id) {
        this.qrCode = qrCode;
        localizacao = new Localizacao(id,prat,zona);
    }

    /**
     * Devolve o Código QR da Palete
     *
     * @return Código QR
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * Devolve a Prateleira em que a Palete está localizada
     *
     * @return  Prateleira onde a Palete se encontra
     */
    public int getPrateleira() {
        return localizacao.getPrateleira();
    }

    /**
     * Altera ID da Zona
     *
     * @param zonaID novo ID da Zona
     */
    public void setZonaID(String zonaID) {
        localizacao.setZonaID(zonaID);
    }

    /**
     * Devolve a Localização da Palete
     *
     * @return LOcalização da Palete
     */
    public Localizacao getLocalizacao() {
        return localizacao;
    }
}
