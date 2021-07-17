package Metodos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lock {
    private final Lock acessLock = new ReentrantLock();
    private final Condition condition = acessLock.newCondition();
    private boolean ocupado = false;

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Lock getAcessLock() {
        return acessLock;
    }

    public Condition getCondition() {
        return condition;
    }
}
