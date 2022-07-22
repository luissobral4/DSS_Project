package Business.Armazem;

/**
 * Classe que representa o Robot
 */

public class Robot {
    private String robotID;
    private boolean disponivel;
    private boolean recolheu;
    private InfoTransporte infoTransporte;
    private Localizacao localizacao;

    /**
     * Construtor Parametrizado do Robot
     * @param id id do Robot
     * @param disp disponibilidade do Robot (boolean)
     * @param recolheu boolean que indica se a palete foi recolhida pelo robot
     * @param infoTransporte informacao do transporte a realizar pelo robot
     * @param l localizacao do Robot
     */

    public Robot(String id, boolean disp, boolean recolheu, InfoTransporte infoTransporte,Localizacao l) {
        robotID = id;
        disponivel = disp;
        this.recolheu = recolheu;
        this.infoTransporte = infoTransporte;
        localizacao = l;
    }

    public Robot(String id) {
        robotID = id;
        disponivel = false;
        this.recolheu = false;
        this.infoTransporte = null;
    }

    /**
     * Devolve a Localizacao do Robot
     *
     * @return Localizacao do Robot
     */

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    /**
     * Altera Localizacao do Robot
     *
     * @param zona zona de acao do robot
     * @param prat prateleira de acao do robot
     */

    public void setLocalizacao(String zona, int prat) {
        this.localizacao.setZonaID(zona);
        this.localizacao.setPrateleira(prat);
    }

    /**
     * Altera informacao do transporte a executar pelo Robot
     *
     * @param id id do robot a transportar
     * @param qr codigo qr da palete a transportar
     * @param prat prateleira referente à palete que vai ser transportada
     */

    public void setInfoTransporte(int id,String qr, int prat) {
        infoTransporte = new InfoTransporte(id,qr,prat);
    }

    /**
     * Devolve a informacao de transporte
     *
     * @return Informacao de transporte
     */

    public InfoTransporte getInfoTransporte() {
        return infoTransporte;
    }

    /**
     * Apaga Informação de Transporte
     */

    public void removeInfo(){ infoTransporte = null;}

    /**
     * Devolve ID do Robot
     *
     * @return ID do Robot
     */

    public String getRobotID() {
        return robotID;
    }

    /**
     * Verifica se o Robot está disponível
     *
     * @return Disponibilidade do Robot
     */

    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Altera disponibilidade do Robot
     *
     * @param disponibilidade Disponobilidade do Robot
     */

    public void setDisponivel(boolean disponibilidade) {
        this.disponivel = disponibilidade;
    }

    /**
     * Verifica se o Robot tem uma palete que recolheu
     *
     * @return Booleano que representa se o robot recolheu a palete
     */

    public boolean isPaleteRecolhida() {
        return recolheu;
    }

    /**
     * Altera se o robot tem palete
     *
     * @param recolheu Robot tem ou não palete
     */

    public void setRecolheu(boolean recolheu) {
        this.recolheu = recolheu;
    }

    /**
     * Devolve prateleira em que o Robot se encontra
     *
     * @return Prateleira em que o Robot se encontra
     */

    public int getPrateleira(){
        if(infoTransporte != null)
            return infoTransporte.getPrateleira();
        else
            return -1;
    }

    /**
     * Devolve o Código QR da palete que o Robot transporta
     *
     * @return Código QR da Palete
     */

    public String getQrCode(){
        if(infoTransporte != null)
            return infoTransporte.getQrCode();
        else
            return null;
    }

    /**
     * Devolve o ID da zona da localizacao do Robot
     *
     * @return ID da zona onde se encontra o Robot
     */

    public String getLocZona(){
        return localizacao.getZonaID();
    }

    /**
     * Devolve a localizacao do robot, tendo como referencia as prateleiras
     *
     * @return localizacao (prateleira) do robot
     */

    public int getLocPrat(){
        return localizacao.getPrateleira();
    }
}
