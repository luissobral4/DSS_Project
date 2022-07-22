/**
 * Classe que representa a Informação de Transporte
 */
package Business.Armazem;

public class InfoTransporte {
    private int idinfoTransporte;
    private String qrCode;
    private int prateleira;

    public InfoTransporte(int id, String qr, int prat) {
        idinfoTransporte = id;
        qrCode = qr;
        prateleira = prat;
    }

    /**
     * Devolve o ID da Informação de Transporte
     *
     * @return ID da Informção de Transporte
     */
    public int getIdinfoTransporte() {
        return idinfoTransporte;
    }

    /**
     * Devolve o Código QR da Palete a ser transportada
     *
     * @return Código QR da Palete
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * Altera o Código QR, ou seja, a Palete, a ser transportada
     *
     * @param qrCode novo Código QR
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * Devolve a Prateleira para a qual a Palete será transportada
     *
     * @return Prateleira destino
     */
    public int getPrateleira() {
        return prateleira;
    }

    /**
     * Altera a Prateleira destino
     *
     * @param prateleira nova Prateleira
     */
    public void setPrateleira(int prateleira) {
        this.prateleira = prateleira;
    }
}
