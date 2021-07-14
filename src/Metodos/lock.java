package Metodos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lock {
    private final Lock acessLock = new ReentrantLock();
    private final Condition canWrite = acessLock.newCondition();
    private final Condition canRead = acessLock.newCondition();
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

    public Condition getCanWrite() {
        return canWrite;
    }

    public Condition getCanRead() {
        return canRead;
    }
}
