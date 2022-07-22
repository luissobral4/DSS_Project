/**
 * Classe que "gere" o Armazém
 */
package Business.Armazem;

import Data.RobotDAO;
import Exceptions.*;

import java.util.Iterator;
import java.util.List;

public class GestArmazem implements IGestArmazem {
    private RobotDAO robots;
    private ZonaRececao zonaRececao;
    private ZonaArmazenamento zonaArmazenamento;

    public GestArmazem() {
        this.robots = new RobotDAO();
        zonaRececao = new ZonaRececao("Rececao");
        zonaArmazenamento = new ZonaArmazenamento("Armazenamento");
    }

    /**
     * Verifica se existe uma determinada Palete na ZOna de Receção
     *
     * @param qrCode Código QR da Palete a ser verificada
     * @return Existência da Palete na Zona de Receção
     */
    private boolean existePaleteRececao(String qrCode){
        return zonaRececao.existePalete(qrCode);
    }

    /**
     * Regista uma Palete
     *
     * @param qrCode       Código QR da Palete
     */
    public void registaPalete (String qrCode) throws PaleteInvalidaException {
        if (existePaleteRececao(qrCode))
            throw new PaleteInvalidaException("A Palete já está registada no sistema");

        zonaRececao.acrescentaPalete(qrCode);
    }

    /**
     * Devolve a distância a que um Robot se encontra da Zona de Receção
     *
     * @param r Robot
     * @return  Distância a que o Robot se encontra
     */
    private int distancia(Robot r) {
        String zona = r.getLocZona();
        int prat = r.getLocPrat();

        if(zona.equals("Rececao"))
            return 0;
        else {
            if(prat < 6)
                return 2 + ((prat-1) * 5);
            else
                return 7 + ((prat-6) * 5);
        }
    }

    /**
     * Devolve um Robot disponível
     *
     * @return  Robot disponível
     */
    private Robot getRobotDisponivel (){
        Robot r, res = null;
        int dist, min = Integer.MAX_VALUE;
        Iterator<Robot> it = robots.values().iterator();

        while (it.hasNext()) {
            r = it.next();
            dist = distancia(r);
            if (r.isDisponivel() && dist < min){
                res = r;
                min = dist;
            }
        }
        return res;
    }

    /**
     * Envia informação a um Robot caso haja algum disponível
     *
     * @param qrCode Código QR da Palete a ser transportada
     */
    public String notRobot(String qrCode) throws PaleteNaoExisteException, RobotNaoDisponivelException, ArmazemCheioException {
        Robot r = getRobotDisponivel();
        if (!existePaleteRececao(qrCode))
            throw new PaleteNaoExisteException("A Palete não existe na zona de receção");

        if (r == null)
            throw new RobotNaoDisponivelException("Não existem robots disponivel");

        int n = robots.sizeInfo();
        int p = zonaArmazenamento.escolhePrateleira();

        if(p==0)
            throw new ArmazemCheioException("A capacidade do armazém esgotou");

        r.setInfoTransporte(n + 1,qrCode, p);
        r.setDisponivel(false);
        robots.put(r);
        return r.getRobotID();
    }

    /**
     * Recolher uma Palete
     *
     * @param robotID ID do Robot que recolherá a Palete
     * @return
     */
    public String recolheP(String robotID) throws RobotNaoDisponivelException {
        Robot r = robots.get(robotID);

        if(r == null || r.isDisponivel())
            throw new RobotNaoDisponivelException("O Robot não está disponível para recolher esta palete");

        String qrCode = r.getQrCode();
        zonaRececao.recolhePalete(qrCode);
        r.setRecolheu(true);
        r.setLocalizacao("Rececao", 0);
        robots.put(r);
        return qrCode;
    }

    /**
     * Entregar uma Palete
     *
     * @param robotID ID do Robot que entegará a Palete
     * @return
     */
    public String entregaP(String robotID) throws RobotNaoDisponivelException, PaleteNaoRecolhidaException {
        Robot r = robots.get(robotID);

        if(r == null || r.isDisponivel())
            throw new RobotNaoDisponivelException("O Robot não está disponível para entregar esta palete");

        if(!r.isPaleteRecolhida())
            throw new PaleteNaoRecolhidaException("A Palete não foi recolhida");

        String qrCode = r.getQrCode();
        int prateleira = r.getPrateleira();

        zonaArmazenamento.arrumaPalete(prateleira,qrCode);
        r.setDisponivel(true);
        r.setRecolheu(false);
        r.setLocalizacao("Armazenamento", r.getPrateleira());
        r.removeInfo();
        robots.put(r);

        return qrCode;
    }

    /**
     * Listagem das Localizações das Paletes
     *
     * @return Listagem das Localizações
     */
    public List<String> getListagem() throws ListagemVaziaException {
        List<String> l = zonaRececao.getListagem();

        if(l.isEmpty())
            throw new ListagemVaziaException("Não existem Paletes a Listar");

        return l;
    }

    /**
     * Lista das Paletes por Notificar
     *
     * @return Lista das Paletes por Notifcar
     */
    public List<String> paletesPorNotificar() throws ListaPaletesPorNotificarException {
        List<String> l = zonaRececao.paletesPorNotificar();

        if(l.isEmpty())
            throw new ListaPaletesPorNotificarException("Não existem Paletes paor notificar");

        return l;
    }

    /**
     * Lista dos Robots por Recolher
     *
     * @return Lista dos Robots
     */
    public List<String> robotsPorRecolher() throws ListaRobotsPorRecolherException {
        List<String> l = robots.robotsPorRecolher();

        if(l.isEmpty())
            throw new ListaRobotsPorRecolherException("Não existem Paletes por recolher");

        return l;
    }

    /**
     * Lista dos Robots por Entregar
     *
     * @return Lista dos Robots
     */
    public List<String> robotsPorEntregar() throws ListaRobotsPorEntregarException {
        List<String> l = robots.robotsPorEntregar();

        if(l.isEmpty())
            throw new ListaRobotsPorEntregarException("Não existem Paletes por entregar");

        return l;
    }
}
