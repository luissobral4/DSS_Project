package Business.Armazem;

import Exceptions.*;

import java.util.List;

public interface IGestArmazem {
    void registaPalete (String qrCode) throws PaleteInvalidaException;
    String notRobot(String qrCode) throws RobotNaoDisponivelException, PaleteNaoExisteException, ArmazemCheioException;
    String recolheP(String robotID) throws RobotNaoDisponivelException;
    String entregaP(String robotID) throws RobotNaoDisponivelException, PaleteNaoRecolhidaException;
    List<String> getListagem() throws ListagemVaziaException;
    List<String> paletesPorNotificar() throws ListaPaletesPorNotificarException;
    List<String> robotsPorRecolher() throws ListaRobotsPorRecolherException;
    List<String> robotsPorEntregar() throws ListaRobotsPorEntregarException;
}
