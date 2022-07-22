/**
 * Classe que representa o Sistema
 */
package Business;
import Business.Armazem.GestArmazem;
import Business.Armazem.IGestArmazem;
import Business.Gestor.GestGestor;
import Business.Gestor.IGestGestor;
import Exceptions.*;

import java.util.List;

public class SGS implements ISGS {
    private IGestArmazem gestArmazem;
    private IGestGestor gestGestor;

    public SGS() {
        gestArmazem = new GestArmazem();
        gestGestor = new GestGestor();
    }

    /**
     * Regista uma Palete
     *
     * @param qrCode        Código QR da Palete
     * @return Boolean que indica se a Palete foi registada ou não
     */
    public void registarPalete(String qrCode) throws PaleteInvalidaException {
        gestArmazem.registaPalete(qrCode);
    }

    /**
     * Envia informação de transporte a um RObot
     *
     * @param qrCode Código QR da Palete que será transportada
     * @return ID do Robot que recebeu a informação
     */
    public String notificarRobot(String qrCode) throws RobotNaoDisponivelException, PaleteNaoExisteException, ArmazemCheioException {
        String res = gestArmazem.notRobot(qrCode);
        return res;
    }

    /**
     * Recolher uma Palete
     *
     * @param robotID ID do Robot que recolhe a Palete
     * @return Código QR da Palete recolhida
     */
    public String recolherPalete(String robotID) throws RobotNaoDisponivelException {
        String res = gestArmazem.recolheP(robotID);
        return res;
    }

    /**
     * Entregat uma Palete
     *
     * @param robotID ID do Robot que entrega a Palete
     * @return Código QR da Palete entregue
     */
    public String entregarPalete(String robotID) throws RobotNaoDisponivelException, PaleteNaoRecolhidaException {
        String res = gestArmazem.entregaP(robotID);
        return res;
    }

    /**
     * Devolve a Listagem das Localizações das Paletes do Armazém
     *
     * @return Lista com as Localizações das Paletes
     */
    public List<String> consultarListagem() throws ListagemVaziaException {
        return gestArmazem.getListagem();
    }

    /**
     * Verifica se o Gestor está online
     *
     * @return Estado de conexão do Gestor
     */
    public boolean isOnline() {
        return gestGestor.isOnline();
    }

    /**
     * Altera o estado de conexão do Gestor
     *
     * @param online novo estado de conexão
     */
    public void setOnline(boolean online) {
        gestGestor.setOnline(online);
    }

    /**
     * Valida o Login efetuado pelo Gestor
     *
     * @param password password introduzida pelo Gestor
     * @return Boolean que indica se o Login é válido
     */
    public boolean validaLoginGestor (String password) { return gestGestor.validaLoginGestor(password); }

    /**
     * Lista das Paletes por Notificar
     *
     * @return Lista das Paletes por Notifcar
     */
    public List<String> paletesPorNotificar() throws ListaPaletesPorNotificarException {
        return gestArmazem.paletesPorNotificar();
    }

    /**
     * Lista dos Robots por Recolher
     *
     * @return Lista dos Robots
     */
    public List<String> robotsPorRecolher() throws ListaRobotsPorRecolherException {
        return gestArmazem.robotsPorRecolher();
    }

    /**
     * Lista dos Robots por Entregar
     *
     * @return Lista dos Robots
     */
    public List<String> robotsPorEntregar() throws ListaRobotsPorEntregarException {
        return gestArmazem.robotsPorEntregar();
    }
}
