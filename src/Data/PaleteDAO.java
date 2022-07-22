/**
 * Classe que representa o acesso aos dados de uma Palete
 */
package Data;

import Business.Armazem.Localizacao;
import Business.Armazem.Palete;

import java.sql.*;
import java.util.*;

public class PaleteDAO{

    /**
     * Verifiaca se uma determinada Palete existe
     *
     * @param key Chave da Palete a ser verificada
     * @return Boolean que representa a existência da Palete
     */
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT QrCode FROM Palete WHERE QrCode='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    /**
     * Devolve uma Palete
     *
     * @param key Chave identificadora da Palete pretendida
     * @return Palete pretendida
     */
    public Palete get(String key) {
        Palete p = null;
        Localizacao l = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Palete WHERE QrCode='"+key+"'")) {
            if (rs.next()) {
                String qr = rs.getString("qrCode");

                ResultSet rsL = stm.executeQuery("SELECT * FROM Localizacao WHERE idLocalizacao=" + rs.getInt("Localizacao_idLocalizacao") + "");
                if (rsL.next()){
                    l = new Localizacao(rsL.getInt("idLocalizacao"), rsL.getInt("Prateleira_prateleiraID"), rsL.getString("zonaID"));
                }
                p = new Palete(qr, l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    /**
     * Adiciona uma Palete à base de dados
     *
     * @param p Palete a ser adicionada
     * @return Palete adicionada
     */
    public Palete put(Palete p) {
        Palete res = null;
        Localizacao l = p.getLocalizacao();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            if(l.getPrateleira() == 0)
                stm.executeUpdate("INSERT INTO Localizacao VALUES ("+l.getIdLocalizacao()+",'"+l.getZonaID()+"',null)" +
                        "ON DUPLICATE KEY UPDATE zonaID=Values(zonaID), Prateleira_prateleiraID=Values(Prateleira_prateleiraID)");
            else
                stm.executeUpdate("INSERT INTO Localizacao VALUES ("+l.getIdLocalizacao()+",'"+l.getZonaID()+"',"+l.getPrateleira()+")" +
                        "ON DUPLICATE KEY UPDATE zonaID=Values(zonaID), Prateleira_prateleiraID=Values(Prateleira_prateleiraID)");

            stm.executeUpdate("INSERT INTO Palete VALUES ('"+p.getQrCode()+"',"+l.getIdLocalizacao()+")" +
                    "ON DUPLICATE KEY UPDATE Localizacao_idLocalizacao=Values(Localizacao_idLocalizacao)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    /**
     * calcula o número de Localizações
     *
     * @return Número de Localizações
     */
    public int sizeLocalizacao() {
        int i = 0;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Localizacao")) {
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
     * Lista as Localizações das Paletes
     *
     * @return Lista de Localizações
     */
    public List<String> listLocalizacoes(){
        List<String> l = new ArrayList<>();
        String line;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("select p.qrCode, l.zonaID, l.Prateleira_prateleiraID from Palete p, Localizacao l where p.Localizacao_idLocalizacao = l.idLocalizacao;")) {
            while (rs.next()) {
                line = "Qr-Code: " + rs.getString("QrCode") + ", ZonaID: ";

                if (rs.getString("zonaID")== null)
                    line += "A ser transportada.";
                else if (rs.getString("Prateleira_prateleiraID") == null)
                    line += "Receção";
                else
                    line += "Armazém, Prateleira: " + rs.getInt("Prateleira_prateleiraID");

                l.add(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return l;
    }

    /**
     * Lista das Paletes por Notificar
     *
     * @return Lista das Paletes por Notifcar
     */
    public List<String> paletesPorNotificar() {
        List<String> l = new ArrayList<>();

        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("select p.qrCode from Palete p, Localizacao l where p.Localizacao_idLocalizacao = l.idLocalizacao and l.zonaID = '"+ "Rececao" +"' ;")) {
            while (rs.next()) {
                l.add(rs.getString("qrCode"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return l;
    }
}
