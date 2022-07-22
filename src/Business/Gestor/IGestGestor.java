package Business.Gestor;

import java.util.List;

public interface IGestGestor {
    boolean validaLoginGestor(String password);
    boolean isOnline();
    void setOnline(boolean online);
}
