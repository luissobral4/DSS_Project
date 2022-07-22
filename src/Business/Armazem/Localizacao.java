/**
 * Classe que representa a Localização
 */

package Business.Armazem;

public class Localizacao {
    private int idLocalizacao;
    private int prateleira;
    private String zonaID;

    public Localizacao(int id,int prateleira, String zonaID) {
        idLocalizacao = id;
        this.prateleira = prateleira;
        this.zonaID = zonaID;
    }

    /**
     * Devovle ID da Localização
     *
     * @return ID da Localização
     */
    public int getIdLocalizacao() {
        return idLocalizacao;
    }

    /**
     * Devolve a Prateleira da Localização
     *
     * @return  Inteiro que representa a Prateleira
     */
    public int getPrateleira() {
        return prateleira;
    }

    /**
     * Devolve o da Zona
     *
     * @return ID da Zona
     */
    public String getZonaID() {
        return zonaID;
    }

    /**
     * Altera a Prateleira da Localizção
     *
     * @param prateleira nova Pratelira da Localização
     */
    public void setPrateleira(int prateleira) {
        this.prateleira = prateleira;
    }

    /**
     * Altera ID da Zona
     *
     * @param zonaID novo ID da Zona
     */
    public void setZonaID(String zonaID) {
        this.zonaID = zonaID;
    }
}
