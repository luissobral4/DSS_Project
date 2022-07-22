package Business.Armazem;

/**
 * Classe que representa a Zona
 */

public class Zona {
    private String zonaID;

    /**
     * Construtor Parametrizado da Zona
     * @param zonaID string que identifica cada zona
     */

    public Zona(String zonaID) {
        this.zonaID = zonaID;
    }

    /**
     * Devolve ID da Zona
     *
     * @return ID da Zona
     */
    public String getZonaID() {
        return zonaID;
    }
}
