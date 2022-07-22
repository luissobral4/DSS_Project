/**
 * Classe que representa o acesso aos dados de um Robot
 */
package Data;
import Business.Armazem.InfoTransporte;
import Business.Armazem.Localizacao;
import Business.Armazem.Robot;
import java.sql.*;
import java.util.*;

public class RobotDAO {
    /**
     * Devolve um Robot da base de dados
     *
     * @param key Chave usada para encontrar o Robot prentendido
     * @return Robot escolhido
     */
    public Robot get(Object key) {
        Robot p = null;
        Localizacao l = null;
        InfoTransporte i = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Robot WHERE RobotID='"+key+"'")) {
            if (rs.next()) {
                boolean disp = false, rec = false;
                if(rs.getInt("Disponivel") == 1)
                    disp = true;
                if(rs.getInt("Recolheu") == 1)
                    rec = true;

                String id_rob = rs.getString("RobotID");
                int id_loc = rs.getInt("Localizacao_idLocalizacao");

                if(rs.getString("InfoTransporte_idInfoTransporte") != null) {
                    ResultSet rsI = stm.executeQuery(
                            "SELECT * FROM InfoTransporte WHERE idInfoTransporte=" + rs.getInt("InfoTransporte_idInfoTransporte") + " ");
                    if (rsI.next()) {
                        i = new InfoTransporte(rsI.getInt("idInfoTransporte"),
                                rsI.getString("Palete_qrCode"),
                                rsI.getInt("Prateleira_prateleiraID"));
                    }
                }

                ResultSet rsL = stm.executeQuery(
                        "SELECT * FROM Localizacao WHERE idLocalizacao="+id_loc+" ");
                if(rsL.next()) {
                    if (rsL.getString("Prateleira_prateleiraID") == null)
                        l = new Localizacao
                                (rsL.getInt("idLocalizacao"), 0, rsL.getString("zonaID"));
                    else
                        l = new Localizacao
                                (rsL.getInt("idLocalizacao"), rsL.getInt("Prateleira_prateleiraID"), rsL.getString("zonaID"));
                }

                p = new Robot(id_rob, disp, rec, i, l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    /**
     * Adiciona um RObot à base de dados
     *
     * @param r Robot a ser adicionado
     * @return todo verficar o retorno disto
     */
    public Robot put(Robot r) {
        Robot res = null;
        InfoTransporte i = r.getInfoTransporte();
        Localizacao l = r.getLocalizacao();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {

            int disp = 0, rec = 0;
            if(r.isDisponivel() == true)
                disp = 1;
            if(r.isPaleteRecolhida() == true)
                rec = 1;

            if(l.getPrateleira()==0)
                stm.executeUpdate("INSERT INTO Localizacao VALUES ("+l.getIdLocalizacao()+",'"+l.getZonaID()+"',"+null+")" +
                        "ON DUPLICATE KEY UPDATE zonaID=Values(zonaID), Prateleira_prateleiraID=Values(Prateleira_prateleiraID)");
            else
                stm.executeUpdate("INSERT INTO Localizacao VALUES ("+l.getIdLocalizacao()+",'"+l.getZonaID()+"',"+l.getPrateleira()+")" +
                        "ON DUPLICATE KEY UPDATE zonaID=Values(zonaID), Prateleira_prateleiraID=Values(Prateleira_prateleiraID)");

            if(i != null) {
                stm.executeUpdate("INSERT INTO InfoTransporte VALUES (" + i.getIdinfoTransporte() + ",'" + i.getQrCode() + "', " + i.getPrateleira() + ")" +
                        "ON DUPLICATE KEY UPDATE Palete_qrCode=Values(Palete_qrCode), Prateleira_prateleiraID=Values(Prateleira_prateleiraID)");

                stm.executeUpdate("INSERT INTO Robot VALUES ('" + r.getRobotID() + "'," + disp + "," + rec + ", " +
                        i.getIdinfoTransporte() + ", " + l.getIdLocalizacao() + ") " +
                        "ON DUPLICATE KEY UPDATE Disponivel=Values(Disponivel), InfoTransporte_idInfoTransporte=Values(InfoTransporte_idInfoTransporte)," +
                        "Localizacao_idLocalizacao=Values(Localizacao_idLocalizacao), Recolheu=Values(Recolheu)");
            }
            else {
                stm.executeUpdate("INSERT INTO Robot VALUES ('" + r.getRobotID() + "'," + disp + "," + rec + ", " +
                        null + ", " + l.getIdLocalizacao() + ") " +
                        "ON DUPLICATE KEY UPDATE Disponivel=Values(Disponivel), InfoTransporte_idInfoTransporte=Values(InfoTransporte_idInfoTransporte)," +
                        "Localizacao_idLocalizacao=Values(Localizacao_idLocalizacao), Recolheu=Values(Recolheu)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    /**
     * Devolve todos os Robots da base de dados
     *
     * @return Robots da base de dados
     */
    public Collection<Robot> values() {
        Collection<Robot> col = new HashSet<>();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT RobotID FROM Robot")) {
            while (rs.next()) {
                col.add(this.get(rs.getString("RobotID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return col;
    }

    /**
     * Calcula o número de Informações de Transporte
     *
     * @return Número de Informações de Transporte
     */
    public int sizeInfo() {
        int i = 0;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM InfoTransporte")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    /**
     * Lista dos Robots por Recolher
     *
     * @return Lista dos Robots
     */
    public List<String> robotsPorRecolher() {
        List<String> l = new ArrayList<>();

        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("select robotID from Robot where Disponivel = "+"0"+" and Recolheu = "+"0"+" ;")) {
            while (rs.next()) {
                l.add(rs.getString("robotID"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return l;
    }

    /**
     * Lista dos Robots por Entregar
     *
     * @return Lista dos Robots
     */
    public List<String> robotsPorEntregar() {
        List<String> l = new ArrayList<>();

        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("select robotID from Robot where Disponivel = "+"0"+" and Recolheu = "+"1"+" ;")) {
            while (rs.next()) {
                l.add(rs.getString("robotID"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return l;
    }

}
